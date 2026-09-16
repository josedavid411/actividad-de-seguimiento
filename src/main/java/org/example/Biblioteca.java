package org.example;

import java.util.ArrayList;
import java.time.LocalDate;

public class Biblioteca {

    private final ArrayList<Cliente> clientes;
    private final InventarioLibros inventario;
    private final ArrayList<Prestamo> prestamos;

    public Biblioteca() {
        clientes = new ArrayList<>();
        inventario = new InventarioLibros();
        prestamos = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDocumento()) != null) {
            System.out.println("Ya existe un cliente con ese documento.");
            return;
        }
        clientes.add(cliente);
        System.out.println("Cliente registrado correctamente.");
    }

    public void consultarClientes() {

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("\n CLIENTES REGISTRADOS");

        for (Cliente cliente : clientes) {
            cliente.mostrarInformacion();
            System.out.println("-----------------------------");
        }
    }

    public void registrarLibro(Libro libro) {
        if (inventario.registrarLibro(libro)) {
            System.out.println("Libro registrado correctamente.");
        } else {
            System.out.println("Categoria no valida o identificador repetido.");
        }
    }

    public void consultarLibros() {
        inventario.mostrarLibros();
    }

    public void retirarLibro(String identificador) {
        if (inventario.retirarLibro(identificador)) {
            System.out.println("Libro retirado del inventario.");
        } else {
            System.out.println("No se pudo retirar el libro. Verifique que exista y este disponible.");
        }
    }

    public void realizarPrestamo(String identificador, String documento) {
        Libro libro = inventario.buscarLibro(identificador);
        Cliente cliente = buscarCliente(documento);

        if (libro == null || cliente == null) {
            System.out.println("No se encontro el libro o el cliente.");
            return;
        }

        Prestamo prestamo = new Prestamo(libro, cliente, LocalDate.now());
        if (prestamo.validacionPrestamo()) {
            prestamo.realizarPrestamo();
            prestamos.add(prestamo);
        }
    }

    public void realizarDevolucion(String identificador) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().getIdentificador().equals(identificador)) {
                prestamo.devolverLibro();
                prestamos.remove(prestamo);
                return;
            }
        }
        System.out.println("El libro no figura como prestado.");
    }

    private Cliente buscarCliente(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }
}