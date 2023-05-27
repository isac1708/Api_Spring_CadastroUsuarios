package com.api.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cliente.entity.Cliente;
import com.api.cliente.repository.ClienteRepository;

/*CRIADA USANDO O REPOSITORIO ClienteRepository */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){  /* salva todos os cliente cadastrados */
        return clienteRepository.save(cliente);  
    }

    public List<Cliente> listaCliente(){ /* Lista todos os cliente cadastrados */
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){  /* retorna um Optional de clientes se tiver algo ele reortona se não tiver nada ele não retorna nada */
        return clienteRepository.findById(id);
    }

    public void removerPorId(Long id ){   /* deletará cadastro pelo id do cleinte */
         clienteRepository.deleteById(id);
    }
}
