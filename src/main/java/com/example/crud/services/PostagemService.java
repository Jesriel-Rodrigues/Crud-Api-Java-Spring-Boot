package com.example.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Postagem;
import com.example.crud.repository.PostagemRepository;

@Service
public class PostagemService {
    
    @Autowired
    PostagemRepository postagemRepository;


    public List<Postagem> obterPostagens() {
        List<Postagem> postagens = postagemRepository.findAll();
        return postagens;
    }
}
