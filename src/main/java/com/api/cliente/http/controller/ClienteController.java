package com.api.cliente.http.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.cliente.entity.Cliente;
/*CRIADA USANDO O SERVICE ClienteService */
import com.api.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController { 
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping            /* pois a requisição é de salvar um cliente entao @PostMapping */
    @ResponseStatus(HttpStatus.CREATED)          /* se for feito com sucesso a requisição retornar HttpStatus.CREATED */
    public Cliente salvar(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @GetMapping  /* VAI RETORNAR UMA LISTA DE CLIENTE CADASTRADOS NA BASE */
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listaCliente(){
        return clienteService.listaCliente();
    }

    @GetMapping("/{id}") /*listar quando "id" for digitado no url */
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarClientePorId(@PathVariable("id") Long id){
         return clienteService.buscarPorId(id)
         .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @DeleteMapping("/{id}") 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCliente(@PathVariable("id") Long id) {
    clienteService.buscarPorId(id)
        .map(cliente -> {
            clienteService.removerPorId(cliente.getId());
            return Void.TYPE;
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(Long id, Cliente cliente) {// Realize a operação de atualização do cliente aqui, por exemplo:clienteService.atualizar(cliente);
        clienteService.buscarPorId(id)
            .map(clienteBase -> {
                modelMapper.map(cliente, clienteBase);
                return clienteBase;
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    
        
    }


}
