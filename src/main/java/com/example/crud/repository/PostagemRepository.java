package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer>{
    
}
