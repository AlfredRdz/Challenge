package com.darkzy.disney.Repository;

import com.darkzy.disney.Model.Genero;
import com.darkzy.disney.Model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    @Query("SELECT g FROM Genero g WHERE g.id_genero = :id_genero")
    Genero findById_genero(Integer id_genero);
    //List<Genero> findAllById_genero(Integer id_genero);

    //Genero getById_genero(Integer id_genero);

    //List<Genero> findAllById_genero(Integer id_genero);
}