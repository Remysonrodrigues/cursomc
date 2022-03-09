package com.udemy.cursomc.services;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscar(Integer id) {

        Optional<Categoria> obj = repository.findById(id);
        return obj.orElse(null);

    }

}
