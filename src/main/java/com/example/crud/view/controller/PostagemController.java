// package com.example.crud.view.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.crud.model.Postagem;
// import com.example.crud.services.PostagemService;

// @RestController
// @RequestMapping("/api/post")
// public class PostagemController {
    
//     PostagemService postagemService;

//     @GetMapping
//     public List<Postagem> obterPostagens() {
//         return postagemService.obterPostagens();
//     }

//     @PostMapping("/{id}")
//     public Postagem publicaPostagem(@RequestBody Postagem post, @PathVariable Integer id) {
//         return publicaPostagem(post,id);
//     }
// }
