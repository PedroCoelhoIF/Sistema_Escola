package com.escola.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L; /*Ajuda a controlar a versão do objeto, evitando erros caso a classe seja modificada no futuro.*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	private String imagem;

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
