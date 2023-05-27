package com.api.cliente.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.cliente.entity.Cliente;



/* CRIADA USANDO A ENTIDADE CLIENTE */
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    
}
