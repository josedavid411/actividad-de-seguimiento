package org.example;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int DIAS_PRESTAMO = 14;
    public static final int MULTA_POR_DIA = 1000;

    private final Libro libro;
    private final Cliente cliente;
    private final LocalDate fechaPrestamo;
    private final LocalDate fechaLimite;
    private LocalDate fechaDevolucion;
    private int multa;

    public Prestamo(Libro libro, Cliente cliente, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.cliente = cliente;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaPrestamo.plusDays(DIAS_PRESTAMO);
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
            System.out.println("Préstamo realizado con éxito. Fecha límite: " + fechaLimite);
        } else {
            System.out.println("No se pudo realizar el préstamo.");
        }
    }

    public void devolverLibro() {
        devolverLibro(LocalDate.now());
    }

    public boolean devolverLibro(LocalDate fechaDevolucion) {
        if (!estaActivo()) {
            System.out.println("El libro no figura como prestado.");
            return false;
        }

        this.fechaDevolucion = fechaDevolucion;
        long diasRetraso = Math.max(0, ChronoUnit.DAYS.between(fechaLimite, fechaDevolucion));
        this.multa = (int) (diasRetraso * MULTA_POR_DIA);
        cliente.agregarMulta(multa);
        libro.setEstado(true);
        cliente.setTieneLibroPrestado(false);

        System.out.println("El libro ha sido devuelto.");
        if (multa > 0) {
            System.out.println("Multa por " + diasRetraso + " dia(s) de retraso: $" + multa);
        } else {
            System.out.println("Devolucion realizada a tiempo. No se genero multa.");
        }
        return true;
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
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

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public int getMulta() {
        return multa;
    }
}