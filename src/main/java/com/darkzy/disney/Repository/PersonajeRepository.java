package com.darkzy.disney.Repository;

import com.darkzy.disney.Model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    @Query("SELECT p FROM Personaje p WHERE p.id_personaje = :id_personaje")
    Optional<Personaje> findById_personaje(Integer id_personaje);

    List<Personaje> findAllByNombre(String nombre);

    List<Personaje> findAllByEdad(Integer edad);

    @Query(value = "SELECT p FROM Personaje p INNER JOIN p.peliculas pe WHERE pe.id_pelicula = :pelicula")
    List<Personaje> findByPeliculas(Integer pelicula);
}