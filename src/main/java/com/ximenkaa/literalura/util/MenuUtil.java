package com.ximenkaa.literalura.util;

import com.ximenkaa.literalura.model.Libro;
import com.ximenkaa.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class MenuUtil implements CommandLineRunner {

    @Autowired
    private LibroService libroService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarPorTitulo(scanner);
                    break;
                case 2:
                    buscarPorAutor(scanner);
                    break;
                case 3:
                    mostrarTop10();
                    break;
                case 4:
                    listarLibros();
                    break;
                case 5:
                    listarAutores();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("\n=== CATÁLOGO DE LIBROS ===");
        System.out.println ("1. Buscar libros por título");
        System.out.println("2. Buscar libros por autor");
        System.out.println("3. Top 10 libros más descargados");
        System.out.println("4. Listar todos los libros");
        System.out.println("5. Listar autores registrados");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void buscarPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título a buscar: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = libroService.buscarPorTitulo(titulo);
        mostrarLibros(libros);
    }

    private void buscarPorAutor(Scanner scanner) {
        System.out.print("Ingrese el nombre del autor: ");
        String autor = scanner.nextLine();
        List<Libro> libros = libroService.buscarPorAutor(autor);
        mostrarLibros(libros);
    }

    private void mostrarTop10() {
        List<Libro> top10 = libroService.top10Descargas();
        System.out.println("\n=== TOP 10 LIBROS MÁS DESCARGADOS ===");
        mostrarLibros(top10);
    }

    private void listarLibros() {
        List<Libro> libros = libroService.listarTodos();
        System.out.println("\n=== TODOS LOS LIBROS REGISTRADOS ===");
        mostrarLibros(libros);
    }

    private void listarAutores() {
        List<String> autores = libroService.listarAutores();
        System.out.println("\n=== AUTORES REGISTRADOS ===");
        autores.forEach(System.out::println);
    }

    private void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros.");
        } else {
            libros.forEach(libro -> {
                System.out.println("\nTítulo: " + libro.getTitulo());
                System.out.println("ID: " + libro.getId());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("Autores: " +
                        libro.getAutores().stream()
                                .map(a -> a.getNombre())
                                .collect(Collectors.joining(", ")));
            });
        }
    }
}