package com.github.tatianepro.rest.service;

import com.github.tatianepro.domain.entity.Pedido;
import com.github.tatianepro.rest.dto.PedidoDto;

public interface PedidoService {
    Pedido salvar(PedidoDto pedidoDto);
}
