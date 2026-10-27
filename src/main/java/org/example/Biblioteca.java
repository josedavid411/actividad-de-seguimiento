package org.example;

import java.io.File;
import java.io.FileInputStream;  // este lo puse para Leer archivo de manera mas sencilla santiagoooo
import java.io.FileOutputStream; // Escribir archivo
import java.io.IOException;// Manejo de excepciones
import java.io.ObjectInputStream;// Leer un objeto de manera mas sencilla santiagoooo
import java.io.ObjectOutputStream;// Escribir un objeto de manera mas sencilla santiagoooo
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

public class Biblioteca implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ARCHIVO_DATOS = "biblioteca.dat";

    private final ArrayList<Cliente> clientes;
    private final InventarioLibros inventario;
    private final ArrayList<Prestamo> prestamos;

    public Biblioteca() {
        Biblioteca bibliotecaGuardada = cargarDatos();
        if (bibliotecaGuardada == null) {
            clientes = new ArrayList<>();
            inventario = new InventarioLibros();
            prestamos = new ArrayList<>();
        } else {
            clientes = bibliotecaGuardada.clientes;
            inventario = bibliotecaGuardada.inventario;
            prestamos = bibliotecaGuardada.prestamos;
        }
    }

    public void registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDocumento()) != null) {
            System.out.println("Ya existe un cliente con ese documento.");
            return;
        }
        clientes.add(cliente);
        guardarDatos();
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
            guardarDatos();
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
            guardarDatos();
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

        Cliente siguienteReserva = libro.getSiguienteReserva();
        if (siguienteReserva != null && !siguienteReserva.getDocumento().equals(documento)) {
            System.out.println("El libro esta reservado para el siguiente cliente: "
                    + siguienteReserva.getNombre() + " (" + siguienteReserva.getDocumento() + ").");
            return;
        }

        Prestamo prestamo = new Prestamo(libro, cliente, LocalDate.now());
        if (prestamo.validacionPrestamo()) {
            prestamo.realizarPrestamo();
            prestamos.add(prestamo);
            libro.registrarPrestamo();
            libro.retirarReserva(cliente);
            guardarDatos();
        }
    }

    public void realizarDevolucion(String identificador) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().getIdentificador().equals(identificador) && prestamo.estaActivo()) {
                if (!prestamo.devolverLibro(LocalDate.now())) {
                    return;
                }
                Cliente siguienteReserva = prestamo.getLibro().getSiguienteReserva();
                if (siguienteReserva != null) {
                    System.out.println("Siguiente cliente en la lista de espera: "
                            + siguienteReserva.getNombre() + " (" + siguienteReserva.getDocumento() + ").");
                }
                guardarDatos();
                return;
            }
        }
        System.out.println("El libro no figura como prestado.");
    }

    public void registrarReserva(String identificador, String documento) {
        Libro libro = inventario.buscarLibro(identificador);
        Cliente cliente = buscarCliente(documento);
        if (libro == null || cliente == null) {
            System.out.println("No se encontro el libro o el cliente.");
            return;
        }
        if (libro.getEstado() || libro.estaRetirado()) {
            System.out.println(libro.estaRetirado()
                    ? "No se puede reservar un libro retirado."
                    : "El libro esta disponible; no es necesario reservarlo.");
            return;
        }
        if (libro.agregarReserva(cliente)) {
            guardarDatos();
            System.out.println("Reserva registrada correctamente.");
        } else {
            System.out.println("El cliente ya esta en la lista de reservas.");
        }
    }

    public void consultarReservas(String identificador) {
        Libro libro = inventario.buscarLibro(identificador);
        if (libro == null) {
            System.out.println("No se encontro el libro.");
            return;
        }
        System.out.println("Reservas para \"" + libro.getTitulo() + "\":");
        if (libro.getReservas().isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        int posicion = 1;
        for (Cliente cliente : libro.getReservas()) {
            System.out.println(posicion + ". " + cliente.getNombre() + " - " + cliente.getDocumento());
            posicion++;
        }
    }

    public void consultarMultas(String documento) {
        Cliente cliente = buscarCliente(documento);
        if (cliente == null) {
            System.out.println("No se encontro el cliente.");
            return;
        }
        System.out.println("Multas pendientes de " + cliente.getNombre() + ": $" + cliente.getMultasPendientes());
        boolean tieneMultas = false;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().getDocumento().equals(documento) && prestamo.getMulta() > 0) {
                System.out.println("- " + prestamo.getLibro().getTitulo() + ": $" + prestamo.getMulta()
                        + " (devolucion: " + prestamo.getFechaDevolucion() + ")");
                tieneMultas = true;
            }
        }
        if (!tieneMultas) {
            System.out.println("El cliente no tiene multas registradas.");
        }
    }

    public void mostrarReporteGeneral() {
        int prestamosActivos = 0;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.estaActivo()) {
                prestamosActivos++;
            }
        }

        System.out.println("\nREPORTE GENERAL");
        System.out.println("Total de libros registrados: " + inventario.getLibros().size());
        System.out.println("Numero de clientes: " + clientes.size());
        System.out.println("Prestamos activos: " + prestamosActivos);
        System.out.println("Prestamos historicos: " + prestamos.size());

        Cliente clienteMasPrestamos = null;
        int mayorCantidadPrestamos = 0;
        for (Cliente cliente : clientes) {
            int cantidadPrestamosCliente = 0;
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getCliente().getDocumento().equals(cliente.getDocumento())) {
                    cantidadPrestamosCliente++;
                }
            }
            if (cantidadPrestamosCliente > mayorCantidadPrestamos) {
                mayorCantidadPrestamos = cantidadPrestamosCliente;
                clienteMasPrestamos = cliente;
            }
        }
        if (clienteMasPrestamos == null) {
            System.out.println("Cliente con mas prestamos: No hay prestamos registrados.");
        } else {
            System.out.println("Cliente con mas prestamos: " + clienteMasPrestamos.getNombre()
                    + " (" + mayorCantidadPrestamos + ")");
        }

        Libro menosSolicitado = null;
        for (Libro libro : inventario.getLibros()) {
            if (menosSolicitado == null || libro.getCantidadPrestamos() < menosSolicitado.getCantidadPrestamos()) {
                menosSolicitado = libro;
            }
        }
        if (menosSolicitado == null) {
            System.out.println("Libro menos solicitado: No hay libros registrados.");
        } else {
            System.out.println("Libro menos solicitado: " + menosSolicitado.getTitulo()
                    + " (" + menosSolicitado.getCantidadPrestamos() + " prestamos)");
        }
    }

    private Cliente buscarCliente(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    private static Biblioteca cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);
        if (!archivo.exists()) {
            return null;
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            Object datos = entrada.readObject();
            if (!(datos instanceof Biblioteca)) {
                throw new IllegalStateException("El archivo de datos no contiene una biblioteca valida.");
            }
            return (Biblioteca) datos;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("No se pudieron cargar los datos de la biblioteca.", e);
        }
    }

    private void guardarDatos() {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            salida.writeObject(this);
        } catch (IOException e) {
            System.err.println("No se pudieron guardar los datos de la biblioteca: " + e.getMessage());
        }
    }
}