package com.escola.controller;



import com.escola.model.Categoria;
import com.escola.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private CategoriaRepository categoriaRepository;


    @ModelAttribute("todasCategoriasNav")
    public List<Categoria> addCategoriasToModel() {
        return categoriaRepository.findAll();
    }
}