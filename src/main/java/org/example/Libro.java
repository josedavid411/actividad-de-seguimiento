package org.example;

public class Libro {
    private String identificador;
    private String titulo;
    private String autor;
    private String editorial;
    private int anioPublicacion;
    private Categoria categoria;
    private EstadoLibro estado;

    public Libro(String identificador, String titulo, String autor, String editorial,
                 int anioPublicacion, Categoria categoria) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
        this.estado = EstadoLibro.disponible;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

}
