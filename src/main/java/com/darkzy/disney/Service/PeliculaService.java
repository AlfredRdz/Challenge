package com.darkzy.disney.Service;

import com.darkzy.disney.dto.GeneroListado;
import com.darkzy.disney.dto.PeliculaDto;
import com.darkzy.disney.dto.PeliculaListado;
import com.darkzy.disney.dto.PersonajeListado;
import com.darkzy.disney.Model.Genero;
import com.darkzy.disney.Model.Pelicula;
import com.darkzy.disney.Model.Personaje;
import com.darkzy.disney.Repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    public PeliculaDto obtenerDetalles(Integer id_pelicula) {
        Pelicula pelicula = peliculaRepository.findById(id_pelicula).orElseThrow(() -> new IllegalStateException("Pelicula no encontrada"));

        PeliculaDto peliculaDto = new PeliculaDto();

        peliculaDto.setId_pelicula( pelicula.getId_pelicula() );
        peliculaDto.setTitulo( pelicula.getTitulo() );
        peliculaDto.setImagen( pelicula.getImagen() );
        peliculaDto.setFecha_creacion( pelicula.getFecha_creacion() );
        peliculaDto.setCalificacion( pelicula.getCalificacion() );
        peliculaDto.setPersonajes( personajesAPersonajeListado( pelicula.getPersonajes() ) );
        peliculaDto.setGeneros( generosAGenerosListados( pelicula.getGeneros() ) );

        return peliculaDto;
    }

    public List<PersonajeListado> personajesAPersonajeListado(List<Personaje> personajes) {
        if ( personajes == null ) {
            return null;
        }

        List<PersonajeListado> list = new ArrayList<PersonajeListado>( personajes.size() );
        for ( Personaje personaje : personajes ) {
            list.add( personajeAPersonajeListado( personaje ) );
        }

        return list;
    }

    public PersonajeListado personajeAPersonajeListado(Personaje personaje) {
        if ( personaje == null ) {
            return null;
        }

        PersonajeListado personajeListado = new PersonajeListado();

        personajeListado.setImagen( personaje.getImagen() );
        personajeListado.setNombre( personaje.getNombre() );

        return personajeListado;
    }

    public List<GeneroListado> generosAGenerosListados(List<Genero> generos) {
        if ( generos == null ) {
            return null;
        }

        List<GeneroListado> list = new ArrayList<GeneroListado>( generos.size() );
        for ( Genero genero : generos ) {
            list.add( generoToGeneroSlimDto( genero ) );
        }

        return list;
    }

    protected GeneroListado generoToGeneroSlimDto(Genero genero) {
        if ( genero == null ) {
            return null;
        }

        GeneroListado generoListado = new GeneroListado();

        generoListado.setId_genero( genero.getId_genero() );
        generoListado.setNombre( genero.getNombre() );
        generoListado.setImagen( genero.getImagen() );

        return generoListado;
    }

    public List<PeliculaListado> obtenerPeliculasSeries() {
        List<Pelicula> peliculas = peliculaRepository.findAll();

        if (peliculas == null) {
            return null;
        }

        List<PeliculaListado> listslim = new ArrayList<PeliculaListado>( peliculas.size() );
        for (Pelicula pelicula : peliculas) {
            listslim.add(peliculaAPeliculaListado(pelicula));
        }

        return listslim;
    }

    public PeliculaListado peliculaAPeliculaListado(Pelicula pelicula) {
        if (pelicula == null) {
            return null;
        }

        PeliculaListado peliculaListado = new PeliculaListado();

        peliculaListado.setImagen(pelicula.getImagen());
        peliculaListado.setTitulo(pelicula.getTitulo());
        peliculaListado.setFecha_creacion(pelicula.getFecha_creacion());

        return peliculaListado;
    }

    public List<Pelicula> obtenerByTitulo(String titulo) {
        return peliculaRepository.findAllByTitulo(titulo);
    }

    public List<Pelicula> obtenerByGenero(Integer genre) {
        return peliculaRepository.findByGeneros(genre);
    }

    public List<Pelicula> obtenerPeliculasByOrder(String order) {
        if (order.equalsIgnoreCase("ASC")) {
            return peliculaRepository.findAllByOrderByFecha_creacionAsc();
        } else if (order.equalsIgnoreCase("DESC")){
            return peliculaRepository.findAllByOrderByFecha_creacionDesc();
        }
        return null;
    }

    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public Pelicula actualizarPelicula(Integer id_pelicula, Pelicula pelicula) {
        Pelicula pelicula1 = peliculaRepository.findById(id_pelicula)
                .orElseThrow(()-> new IllegalStateException("La pelicula no existe"));

        pelicula1.setImagen(pelicula.getImagen());
        pelicula1.setTitulo(pelicula.getTitulo());
        pelicula1.setFecha_creacion(pelicula.getFecha_creacion());
        pelicula1.setCalificacion(pelicula.getCalificacion());

        pelicula1.setPersonajes(pelicula.getPersonajes());
        pelicula1.setGeneros(pelicula.getGeneros());

        return peliculaRepository.save(pelicula1);
    }

    public void eliminarPelicula(Integer id_pelicula) {
        peliculaRepository.deleteById(id_pelicula);
    }
}
