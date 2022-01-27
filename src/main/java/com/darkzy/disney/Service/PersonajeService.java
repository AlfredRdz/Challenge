package com.darkzy.disney.Service;

import com.darkzy.disney.dto.PersonajeListado;
import com.darkzy.disney.Model.Personaje;
import com.darkzy.disney.Repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonajeService {
    @Autowired
    private PersonajeRepository personajeRepository;

    public Personaje obtenerDetalles(Integer id_personaje) {
        return personajeRepository.findById_personaje(id_personaje).orElseThrow(() -> new IllegalStateException("Personaje no encontrado"));
    }

    public List<PersonajeListado> obtenerPersonajes() {
        List<Personaje> personajes = personajeRepository.findAll();

        if (personajes == null) {
            return null;
        }

        List<PersonajeListado> listados = new ArrayList<>(personajes.size());

        for (Personaje personaje : personajes) {
            listados.add(personajeAPersonajeListado(personaje));
        }

        return listados;
    }

    public PersonajeListado personajeAPersonajeListado(Personaje personaje) {
        if (personaje == null) {
            return null;
        }

        PersonajeListado personajeListado = new PersonajeListado();

        personajeListado.setImagen(personaje.getImagen());
        personajeListado.setNombre(personaje.getNombre());

        return personajeListado;
    }

    public List<Personaje> obtenerPorNombre(String nombre) {
        return personajeRepository.findAllByNombre(nombre);
    }

    public List<Personaje> obtenerPorEdad(Integer edad) {
        return personajeRepository.findAllByEdad(edad);
    }

    public List<Personaje> obtenerPorPelicula(Integer id_movie) {
        return personajeRepository.findByPeliculas(id_movie);
    }

    public Personaje crearPersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    public Personaje actualizarPersonaje(Integer id_personaje, Personaje personaje){
        Personaje personaje1 = personajeRepository.findById(id_personaje)
                .orElseThrow(() -> new IllegalStateException("El personaje no existe"));

        personaje1.setImagen(personaje.getImagen());
        personaje1.setNombre(personaje.getNombre());
        personaje1.setEdad(personaje.getEdad());
        personaje1.setPeso(personaje.getPeso());
        personaje1.setHistoria(personaje.getHistoria());

        personaje1.setPeliculas(personaje.getPeliculas());

        return personajeRepository.save(personaje1);
    }

    public void eliminarPersonaje(Integer id_personaje) {
        personajeRepository.deleteById(id_personaje);
    }
}
