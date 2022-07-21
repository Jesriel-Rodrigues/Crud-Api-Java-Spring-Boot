package com.example.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Cliente;
import com.example.crud.model.Postagem;
import com.example.crud.repository.PostagemRepository;
import com.example.crud.shared.ClienteDTO;

@Service
public class PostagemService {
    
    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    ClienteService clienteService;

    public List<Postagem> obterPostagens() {
        List<Postagem> postagens = postagemRepository.findAll();
        return postagens;
    }

    public Postagem publicaPostagem(Postagem post, Integer id) {
        Optional<ClienteDTO> clienteDto = clienteService.obterPorId(id);
        Cliente cliente = clienteService.mapper.map(clienteDto.get(), Cliente.class);
        post.setCliente(cliente);
        postagemRepository.save(post);
        return post;
    }
}
