package com.ximenkaa.literalura.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoaFallecimiento() {
        return anoaFallecimiento;
    }

    public void setAnoaFallecimiento(Integer anoaFallecimiento) {
        this.anoaFallecimiento = anoaFallecimiento;
    }

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("birth_year")
    private Integer anoNacimiento;

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anoNacimiento=" + anoNacimiento +
                ", anoaFallecimiento=" + anoaFallecimiento +
                '}';
    }

    @JsonProperty("death_year")
    private Integer anoaFallecimiento;
}



