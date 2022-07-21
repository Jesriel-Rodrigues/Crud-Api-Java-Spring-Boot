package com.example.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crud.model.Cliente;
import com.example.crud.model.exception.ResourceNotFoundException;
import com.example.crud.repository.ClienteRepository;
import com.example.crud.shared.ClienteDTO;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    ModelMapper mapper = new ModelMapper();

    /**
     * Metodo que devolve todos os clientes cadastrados no banco!
     * @return Lista de clientes
     */
    public List<ClienteDTO> obterClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDto = clientes.stream()
        .map(cliente -> mapper.map(cliente, ClienteDTO.class))
        .collect(Collectors.toList());
        
        return clienteDto;
    }

    /**
     * Metodo que devolve o cliente obtido pelo Id!
     * @param id do cliente a localizar!
     * @return Cliente encontrado!
     */
    public Optional<ClienteDTO> obterPorId(Integer id ) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel encontrar cliente pois ele nao existe!");
        }
        ClienteDTO clientedto = mapper.map(cliente.get(), ClienteDTO.class);
        return Optional.of(clientedto);
    }

    /**
     * Metodo que cadastra o cliente no banco de dados!
     * @param clienteDto dados do cliente a ser cadastrado!
     * @return Cliente cadastrado!
     */
    public ClienteDTO cadastrar(ClienteDTO clienteDto) {
        clienteDto.setId(null);
        // clienteDto.setPostagens(null);

        Cliente cliente = mapper.map(clienteDto, Cliente.class);

        clienteRepository.save(cliente);

        clienteDto.setId(cliente.getId());

        return clienteDto;
    }

    /**
     * Metodo que exclui o cliente obtido pelo Id!
     * @param id do cliente a ser excluido!
     */
    public void excluirCliente(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel excluir cliente pois ele nao existe!");
        }
        clienteRepository.deleteById(id);
    }

    /**
     * Metodo que atualiza os dados do cliente no banco de dados!
     * @param clienteDto dados do cliente a ser atualizado!
     * @param id do cliente a ser atualizado!
     * @return Cliente atualizado!
     */
    public ClienteDTO atualizarCliente(ClienteDTO clienteDto, Integer id) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);

        if (clienteEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel atualizar cliente pois ele nao existe!");
        }

        clienteDto.setId(id);
        Cliente cliente = mapper.map(clienteDto, Cliente.class);
        
        clienteRepository.save(cliente);
        return clienteDto;
    }
}
