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
            System.out.println("3. Salir");
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

                    System.out.println("Saliendo del sistema...");

                    break;

                default:

                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 3);

        scanner.close();

    }
}