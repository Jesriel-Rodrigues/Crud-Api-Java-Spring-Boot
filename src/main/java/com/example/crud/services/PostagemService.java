package com.example.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Entities.Usuario;
import com.example.crud.model.Entities.Postagem;
import com.example.crud.repository.PostagemRepository;
import com.example.crud.shared.UsuarioDTO;

@Service
public class PostagemService {
    
    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioService usuarioService;

    public List<Postagem> obterPostagens() {
        List<Postagem> postagens = postagemRepository.findAll();
        return postagens;
    }

    public Postagem publicaPostagem(Postagem post, Integer id) {
        Optional<UsuarioDTO> usuarioDto = usuarioService.obterPorId(id);
        Usuario usuario = usuarioService.mapper.map(usuarioDto.get(), Usuario.class);
        post.setUsuario(usuario);
        postagemRepository.save(post);
        return post;
    }
}
