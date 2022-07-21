package com.example.crud.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    //#region Atributos
    @Id //Transforma essa coluna em primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private String email;

    private Integer idade;

    private String EstadoCivil;

    @OneToMany
    @JoinColumn(name = "ID_CLIENTE")
    private List<Postagem> postagens;
    //#endregion

    
    
    //#region Metodos Getters e Setters
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
    
    public Integer getIdade() {
        return idade;
    }
    
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    

    public String getEstadoCivil() {
        return EstadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
        EstadoCivil = estadoCivil;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }
    //#endregion
    
}
