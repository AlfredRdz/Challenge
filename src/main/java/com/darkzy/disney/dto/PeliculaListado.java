package com.darkzy.disney.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PeliculaListado {

    private String imagen;
    private String titulo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha_creacion;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
