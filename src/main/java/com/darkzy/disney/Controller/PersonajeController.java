package com.darkzy.disney.Controller;

import com.darkzy.disney.dto.PersonajeListado;
import com.darkzy.disney.Model.Personaje;
import com.darkzy.disney.Service.PersonajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/characters")
@SecurityRequirement(name = "bearerAuth")
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;

    @Operation(summary = "Obtener todas los personajes")
    @GetMapping
    public List<PersonajeListado> obtenerPersonajes() {
        return personajeService.obtenerPersonajes();
    }

    @Operation(summary = "Obtener detalles de personajes por Id")
    @GetMapping("/{id_personaje}")
    public Personaje obtenerDetalles(@PathVariable("id_personaje") Integer id_personaje) {
        return personajeService.obtenerDetalles(id_personaje);
    }

    @Operation(summary = "Obtener personajes por nombre")
    @GetMapping(params = "name")
    public List<Personaje> obtenerPorNombre(@RequestParam(value = "name", required = false) String name) {
        return personajeService.obtenerPorNombre(name);
    }

    @Operation(summary = "Obtener personajes por año")
    @GetMapping(params = "age")
    public List<Personaje> obtenerPorEdad(@RequestParam(value = "age", required = false) Integer age) {
        return personajeService.obtenerPorEdad(age);
    }

    @Operation(summary = "Obtener personajes por pelicula")
    @GetMapping(params = "movies")
    public List<Personaje> obtenerPorPelicula(@RequestParam(value = "movies", required = false) Integer movies) {
        return personajeService.obtenerPorPelicula(movies);
    }

    @Operation(summary = "Crear objeto personaje")
    @PostMapping
    public Personaje guardarPersonaje(@RequestBody Personaje personaje) {
        return personajeService.crearPersonaje(personaje);
    }

    @Operation(summary = "Actualizar objeto personaje")
    @PutMapping("/{id_personaje}")
    public Personaje actualizarPersonaje(@PathVariable("id_personaje") Integer id_personaje, @RequestBody Personaje personaje) {
        return personajeService.actualizarPersonaje(id_personaje, personaje);
    }

    @Operation(summary = "Eliminar personaje por Id")
    @DeleteMapping("/{id_personaje}")
    public String eliminarPersonaje(@PathVariable("id_personaje") Integer id_personaje){
        try {
            personajeService.eliminarPersonaje(id_personaje);
            return "Personaje eliminado";
        }catch (Exception e) {
            return "Personaje no eliminado";
        }

    }
}
