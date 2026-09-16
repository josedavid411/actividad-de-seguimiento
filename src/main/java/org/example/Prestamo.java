package org.example;
import java.time.LocalDate;

public class Prestamo {
    private final Libro libro;
    private final Cliente cliente;
    private final LocalDate fechaPrestamo;

    public Prestamo(Libro libro, Cliente cliente, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.cliente = cliente;
        this.fechaPrestamo = fechaPrestamo;
    }

public boolean validacionPrestamo() {
    if (libro.estaRetirado()) {
        System.out.println("El libro está retirado del inventario.");
        return false;
    }
        if (libro.getEstado() == false) {
            System.out.println("El libro ya está prestado.");
            return false;
        }
        if (cliente.tieneLibroPrestado()) {
            System.out.println("El cliente ya tiene un libro prestado.");
            return false;
        }
        return true;
    }

    public void realizarPrestamo() {
        if (validacionPrestamo()) {
            libro.setEstado(false);
            cliente.setTieneLibroPrestado(true);

            System.out.println("Préstamo realizado con éxito.");
        } else {
            System.out.println("No se pudo realizar el préstamo.");
        }
    }
    public void devolverLibro() {
        if (!libro.getEstado()) {
            libro.setEstado(true);
            cliente.setTieneLibroPrestado(false);
            System.out.println("El libro ha sido devuelto.");
        } else {
            System.out.println("El libro no figura como prestado.");
        }
    }

    public Libro getLibro() {
        return libro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
}