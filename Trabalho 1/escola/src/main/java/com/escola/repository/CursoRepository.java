package com.escola.repository;


import com.escola.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {


    List<Curso> findCursoByNomeLike(String nome);

    List<Curso> findByCategoriaId(Integer categoriaId);
}