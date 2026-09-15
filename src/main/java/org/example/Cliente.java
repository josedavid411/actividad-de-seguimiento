package org.example;

public class Cliente {

    private String documento;
    private String nombre;
    private String telefono;
    private String direccion;
    private boolean tieneLibroPrestado;

    public Cliente(String documento, String nombre, String telefono, String direccion) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tieneLibroPrestado = false; //false no tiene libro prestado, true si tiene libro prestado
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean tieneLibroPrestado() {
        return tieneLibroPrestado;
    }

    public void setTieneLibroPrestado(boolean tieneLibroPrestado) {
        this.tieneLibroPrestado = tieneLibroPrestado;
    }

    public void mostrarInformacion() {
        System.out.println("Documento: " + documento);
        System.out.println("Nombre: " + nombre);
        System.out.println("Telefono: " + telefono);
        System.out.println("Direccion: " + direccion);
        System.out.println("Tiene libro prestado: " +
                (tieneLibroPrestado ? "Si" : "No"));
    }
}

