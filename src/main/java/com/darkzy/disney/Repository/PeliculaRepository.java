package com.darkzy.disney.Repository;

import com.darkzy.disney.Model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

    @Query("SELECT p FROM Pelicula p LEFT JOIN p.personajes pe WHERE p.id_pelicula = :id_pelicula")
    Optional<Pelicula> findById_pelicula(Integer id_pelicula);


    Optional<Pelicula> findById(Integer id_pelicula);

    List<Pelicula> findAllByTitulo(String titulo);

    @Query("SELECT p FROM Pelicula p INNER JOIN p.generos g WHERE g.id_genero = :generos")
    List<Pelicula> findByGeneros(Integer generos);

    @Query("SELECT p FROM Pelicula p ORDER BY p.fecha_creacion ASC")
    List<Pelicula> findAllByOrderByFecha_creacionAsc();

    @Query("SELECT p FROM Pelicula p ORDER BY p.fecha_creacion DESC")
    List<Pelicula> findAllByOrderByFecha_creacionDesc();
}