package com.ximenkaa.literalura.util;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ximenkaa.literalura.model.Autor;
import com.ximenkaa.literalura.model.Libro;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ApiGutendexUtil {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private final HttpClient httpClient;

    public ApiGutendexUtil() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public JsonObject getBooks(String query) throws Exception {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(BASE_URL + "?search=" + encodedQuery);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    public List<Libro> obtenerLibrosDesdeApi() {
        try {
            JsonObject json = getBooks(""); // puedes pasar una búsqueda vacía o un término
            JsonArray resultados = json.getAsJsonArray("results");

            List<Libro> libros = new ArrayList<>();
            for (JsonElement elemento : resultados) {
                final Libro libro = getLibro(elemento);

                libros.add(libro);
            }

            return libros;
        } catch (Exception e) {
            // Puedes loguear el error si quieres
            return Collections.emptyList();
        }
    }

    private static Libro getLibro(JsonElement elemento) {
        JsonObject obj = elemento.getAsJsonObject();

        Libro libro = new Libro();
        libro.setTitulo(obj.get("title").getAsString());
        libro.setDescargas(obj.get("download_count").getAsInt());

        // Autores
        List<Autor> autores = new ArrayList<>();
        JsonArray autoresJson = obj.getAsJsonArray("authors");
        for (JsonElement autorElem : autoresJson) {
            JsonObject autorObj = autorElem.getAsJsonObject();
            Autor autor = new Autor();
            autor.setNombre(autorObj.get("name").getAsString());
            autores.add(autor);
        }
        libro.setAutores(autores);
        return libro;
    }
}



