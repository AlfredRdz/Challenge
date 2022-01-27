package com.darkzy.disney.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class PeliculaDto {

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Integer id_pelicula;

    private String titulo;

    private String imagen;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha_creacion;

    private Integer calificacion;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PersonajeListado> personajes;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GeneroListado> generos;

    public Integer getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Integer id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<PersonajeListado> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeListado> personajes) {
        this.personajes = personajes;
    }

    public List<GeneroListado> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroListado> generos) {
        this.generos = generos;
    }
}
