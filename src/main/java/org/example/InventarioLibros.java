package org.example;

import java.util.ArrayList;
import java.text.Normalizer;

public class InventarioLibros {
	private final ArrayList<Libro> libros = new ArrayList<>();

	public boolean registrarLibro(Libro libro) {
		if (!categoriaValida(libro.getCategoria())) {
			return false;
		}
		if (buscarLibro(libro.getIdentificador()) != null) {
			return false;
		}
		libros.add(libro);
		return true;
	}

	private boolean categoriaValida(String categoria) {
		String categoriaNormalizada = Normalizer.normalize(categoria, Normalizer.Form.NFD)
				.replaceAll("\\p{M}", "");

		return categoriaNormalizada.equalsIgnoreCase("Literatura")
				|| categoriaNormalizada.equalsIgnoreCase("Ciencia")
				|| categoriaNormalizada.equalsIgnoreCase("Historia")
				|| categoriaNormalizada.equalsIgnoreCase("Tecnologia");
	}

	public Libro buscarLibro(String identificador) {
		for (Libro libro : libros) {
			if (libro.getIdentificador().equals(identificador)) {
				return libro;
			}
		}
		return null;
	}

	public boolean retirarLibro(String identificador) {
		Libro libro = buscarLibro(identificador);
		if (libro == null || !libro.getEstado()) {
			return false;
		}

		libro.retirar();
		return true;
	}

	public void mostrarLibros() {
		if (libros.isEmpty()) {
			System.out.println("No hay libros registrados.");
			return;
		}

		System.out.println("\n LIBROS REGISTRADOS");
		for (Libro libro : libros) {
			libro.mostrarInformacion();
			System.out.println("-----------------------------");
		}
	}

}
