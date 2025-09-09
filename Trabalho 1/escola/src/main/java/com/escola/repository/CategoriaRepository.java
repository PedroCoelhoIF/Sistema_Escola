package com.escola.repository;


import com.escola.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


    List<Categoria> findCategoriaByNomeLike(String nome);
}