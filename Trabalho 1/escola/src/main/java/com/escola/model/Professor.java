package com.escola.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "professor")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L; /*Ajuda a controlar a versão do objeto, evitando erros caso a classe seja modificada no futuro.*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String email;
    private String imagem;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}