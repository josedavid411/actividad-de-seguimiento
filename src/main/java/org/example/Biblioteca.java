package org.example;

import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Cliente> clientes;
    private ArrayList<Libro> libros;

    public Biblioteca() {
        clientes = new ArrayList<>();
        libros = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
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
        libros.add(libro);
        System.out.println("Libro registrado correctamente.");
    }
}