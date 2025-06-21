package com.ximenkaa.literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class Libro {
    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String titulo;

    @ElementCollection
    @JsonProperty("subjects")
    private List<String> temas;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("authors")
    private List<Autor> autores;

    @JsonProperty("download_count")
    private Integer descargas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }


    
}