package com.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escola.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	List<Usuario> findUsuarioByNomeLike(String nome);
}
