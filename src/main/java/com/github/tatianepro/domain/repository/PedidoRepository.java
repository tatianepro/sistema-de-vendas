package com.github.tatianepro.domain.repository;

import com.github.tatianepro.domain.entity.Cliente;
import com.github.tatianepro.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
