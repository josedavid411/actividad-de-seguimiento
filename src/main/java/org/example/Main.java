package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcion;

        do {

            System.out.println("\n BIBLIOTECA LIBROSYMAS");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Consultar clientes");
            System.out.println("3. Registrar libro");
            System.out.println("4. Consultar libros");
            System.out.println("5. Prestar libro");
            System.out.println("6. Devolver libro");
            System.out.println("7. Retirar libro");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println("\n REGISTRAR CLIENTE");

                    System.out.print("Documento: ");
                    String documento = scanner.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine();

                    System.out.print("Direccion: ");
                    String direccion = scanner.nextLine();

                    Cliente cliente = new Cliente(
                            documento,
                            nombre,
                            telefono,
                            direccion
                    );

                    biblioteca.registrarCliente(cliente);

                    break;

                case 2:

                    biblioteca.consultarClientes();

                    break;

                case 3:

                    System.out.println("\n REGISTRAR LIBRO");
                    System.out.print("Identificador: ");
                    String identificador = scanner.nextLine();
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Editorial: ");
                    String editorial = scanner.nextLine();
                    System.out.print("Año de publicacion: ");
                    int anioPublicacion = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();

                    biblioteca.registrarLibro(new Libro(
                            identificador, titulo, autor, editorial,
                            anioPublicacion, categoria, true));
                    break;

                case 4:

                    biblioteca.consultarLibros();
                    break;

                case 5:

                    System.out.println("\n PRESTAR LIBRO");
                    System.out.print("Identificador del libro: ");
                    String libroPrestamo = scanner.nextLine();
                    System.out.print("Documento del cliente: ");
                    String clientePrestamo = scanner.nextLine();
                    biblioteca.realizarPrestamo(libroPrestamo, clientePrestamo);
                    break;

                case 6:

                    System.out.println("\n DEVOLVER LIBRO");
                    System.out.print("Identificador del libro: ");
                    String libroDevolucion = scanner.nextLine();
                    biblioteca.realizarDevolucion(libroDevolucion);
                    break;

                case 7:

                    System.out.println("\n RETIRAR LIBRO");
                    System.out.print("Identificador del libro: ");
                    String libroRetiro = scanner.nextLine();
                    biblioteca.retirarLibro(libroRetiro);
                    break;

                case 8:

                    System.out.println("Saliendo del sistema...");

                    break;

                default:

                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 8);

        scanner.close();

    }
}