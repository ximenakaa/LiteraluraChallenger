package com.ximenkaa.literalura.service;





import com.ximenkaa.literalura.model.Libro;

import java.util.List;

public interface LibroService {
    List<Libro> buscarPorTitulo(String titulo);
    List<Libro> buscarPorAutor(String autor);
    List<Libro> top10Descargas();
    List<Libro> listarTodos();
    List<String> listarAutores();
}