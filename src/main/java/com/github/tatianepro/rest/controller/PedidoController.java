package com.github.tatianepro.rest.controller;

import com.github.tatianepro.domain.entity.Pedido;
import com.github.tatianepro.rest.dto.PedidoDto;
import com.github.tatianepro.rest.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDto pedidoDto) {
        Pedido pedido = pedidoService.salvar(pedidoDto);
        return pedido.getId();
    }

}
