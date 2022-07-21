package com.example.crud.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Postagem;
import com.example.crud.services.ClienteService;
import com.example.crud.services.PostagemService;
import com.example.crud.shared.ClienteDTO;
import com.example.crud.view.model.ClienteRequest;
import com.example.crud.view.model.ClienteResponse;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @Autowired
    PostagemService postagemService;

    ModelMapper mapper = new ModelMapper();
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obterClientes() {
        List<ClienteDTO> clientes = clienteService.obterClientes();

        List<ClienteResponse> clienteResposta = clientes.stream()
        .map( clienteDto -> mapper.map(clienteDto, ClienteResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(clienteResposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClienteResponse>> obterPorId(@PathVariable Integer id) {
        Optional<ClienteDTO> clienteDto = clienteService.obterPorId(id);
        ClienteResponse ClienteRes = mapper.map(clienteDto.get(), ClienteResponse.class);

        return new ResponseEntity<>(Optional.of(ClienteRes), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody ClienteRequest clienteReq) {
        ClienteDTO clienteDto = mapper.map(clienteReq, ClienteDTO.class);

        clienteDto = clienteService.cadastrar(clienteDto);

        ClienteResponse clienteRes = mapper.map(clienteDto, ClienteResponse.class);

        return new ResponseEntity<>(clienteRes, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCliente(@PathVariable Integer id) {
        clienteService.excluirCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@RequestBody ClienteRequest clienteReq, @PathVariable Integer id) {
        ClienteDTO clienteDto = mapper.map(clienteReq, ClienteDTO.class);

        clienteDto = clienteService.atualizarCliente(clienteDto, id);

        ClienteResponse clienteRes = mapper.map(clienteDto, ClienteResponse.class);

        return new ResponseEntity<>(clienteRes, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public List<Postagem> obterPostagens() {
        return postagemService.obterPostagens();
    }

    @PostMapping("/posts/{id}")
    public Postagem publicaPostagem(@RequestBody Postagem post, @PathVariable Integer id) {
        return postagemService.publicaPostagem(post,id);
    }
}
