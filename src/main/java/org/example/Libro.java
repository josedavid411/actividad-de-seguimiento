package org.example;

public class Libro {
    private String identificador;
    private String titulo;
    private String autor;
    private String editorial;
    private int anioPublicacion;
    private String categoria;
    private boolean estado = true;
    private boolean retirado;

    public Libro(String identificador, String titulo, String autor, String editorial,
                 int anioPublicacion, String categoria, boolean estado) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
        this.estado = estado;
        this.retirado = false;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean getEstado() {
        return estado;

    }

    public void setEstado(boolean estado) {
        if (!retirado) {
            this.estado = estado;
        }
    }

    public boolean estaRetirado() {
        return retirado;
    }

    public void retirar() {
        retirado = true;
        estado = false;
    }

    public void mostrarInformacion() {
        String estadoActual = retirado ? "Retirado" : (estado ? "Disponible" : "Prestado");
        System.out.println("Identificador: " + identificador);
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Editorial: " + editorial);
        System.out.println("Año de publicacion: " + anioPublicacion);
        System.out.println("Categoria: " + categoria);
        System.out.println("Estado: " + estadoActual);
    }
}
