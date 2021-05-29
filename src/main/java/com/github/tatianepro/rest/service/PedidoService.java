package com.github.tatianepro.rest.service;

import com.github.tatianepro.domain.entity.Pedido;
import com.github.tatianepro.domain.enums.StatusPedido;
import com.github.tatianepro.rest.dto.PedidoDto;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDto pedidoDto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido novoStatus);
}
