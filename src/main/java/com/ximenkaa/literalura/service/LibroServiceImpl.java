package com.ximenkaa.literalura.service;


import com.ximenkaa.literalura.model.Libro;
import com.ximenkaa.literalura.repository.LibroRepository;
import com.ximenkaa.literalura.util.ApiGutendexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ApiGutendexUtil apiGutendexUtil;


    @Override
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> libros = apiGutendexUtil.obtenerLibrosDesdeApi();
        return libros.stream()
                .filter(l -> l.getAutores().stream()
                        .anyMatch(a -> a.getNombre().toLowerCase().contains(autor.toLowerCase())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> top10Descargas() {
        List<Libro> libros = apiGutendexUtil.obtenerLibrosDesdeApi();
        return libros.stream()
                .sorted(Comparator.comparingInt(Libro::getDescargas).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> libros = apiGutendexUtil.obtenerLibrosDesdeApi();
        if (libros == null) {
            // Puedes loguear o lanzar una excepción personalizada si lo prefieres
            return Collections.emptyList();
        }
        return libros.stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Libro> listarTodos() {
        return apiGutendexUtil.obtenerLibrosDesdeApi();
    }

    @Override
    public List<String> listarAutores() {
        List<Libro> libros = apiGutendexUtil.obtenerLibrosDesdeApi();
        return libros.stream()
                .flatMap(l -> l.getAutores().stream())
                .map(a -> a.getNombre())
                .distinct()
                .collect(Collectors.toList());
    }
}