package com.darkzy.disney.Service;

import com.darkzy.disney.Model.Genero;
import com.darkzy.disney.Repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public Optional<Genero> obtenerGenerosById(Integer id_genero) {
        return generoRepository.findById(id_genero);
    }
}
