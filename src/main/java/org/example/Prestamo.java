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