package com.darkzy.disney.Controller;

import com.darkzy.disney.dto.PeliculaDto;
import com.darkzy.disney.dto.PeliculaListado;
import com.darkzy.disney.Model.Pelicula;
import com.darkzy.disney.Service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@SecurityRequirement(name = "bearerAuth")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @Operation(summary = "Obtener todas las peliculas")
    @GetMapping
    public List<PeliculaListado> obtenerPeliculasSeries() {
        return peliculaService.obtenerPeliculasSeries();
    }

    @Operation(summary = "Obtener detalles de una pelicula por Id")
    @ApiResponse(responseCode = "200", description = "Detalles de pelicula", content = { @Content(mediaType = "application/json", schema =  @Schema(implementation = PeliculaDto.class))})
    @GetMapping("/{id_pelicula}")
    public PeliculaDto obtenerDetalles(@PathVariable("id_pelicula") Integer id_pelicula) {
        return peliculaService.obtenerDetalles(id_pelicula);
    }

    @Operation(summary = "Obtener peliculas por nombre")
    @GetMapping(params = "name")
    public List<Pelicula> obtenerByTitulo(@RequestParam(value = "name", required = false) String name) {
        return peliculaService.obtenerByTitulo(name);
    }

    @Operation(summary = "Obtener peliculas por genero")
    @GetMapping(params = "genre")
    public List<Pelicula> obtenerByGenero(@RequestParam(value = "genre", required = false) Integer genre) {
        return peliculaService.obtenerByGenero(genre);
    }

    @Operation(summary = "Obtener peliculas por orden")
    @GetMapping(params = "order")
    public List<Pelicula> obtenerByOrder(@RequestParam(value = "order", required = false) String order) {
        return peliculaService.obtenerPeliculasByOrder(order);
    }

    @Operation(summary = "Crear objeto pelicula")
    @PostMapping
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.crearPelicula(pelicula);
    }

    @Operation(summary = "Actualizar objeto pelicula")
    @PutMapping("/{id_pelicula}")
    public Pelicula actualizarPelicula(@PathVariable("id_pelicula") Integer id_pelicula, @RequestBody Pelicula pelicula) {
        return peliculaService.actualizarPelicula(id_pelicula, pelicula);
    }

    @Operation(summary = "Eliminar pelicula por Id")
    @DeleteMapping("/{id_pelicula}")
    public String eliminarPelicula(@PathVariable("id_pelicula") Integer id_pelicula) {
        try {
            peliculaService.eliminarPelicula(id_pelicula);
            return "Pelicula eliminada";
        } catch (Exception e) {
            return "Hubo un error";
        }
    }
}
