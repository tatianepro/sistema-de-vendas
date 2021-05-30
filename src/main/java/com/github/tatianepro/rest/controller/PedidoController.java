package com.github.tatianepro.rest.controller;

import com.github.tatianepro.domain.entity.ItemPedido;
import com.github.tatianepro.domain.entity.Pedido;
import com.github.tatianepro.domain.enums.StatusPedido;
import com.github.tatianepro.rest.dto.AtualizacaoStatusPedidoDto;
import com.github.tatianepro.rest.dto.InformacoesItemPedidoDto;
import com.github.tatianepro.rest.dto.InformacoesPedidoDto;
import com.github.tatianepro.rest.dto.PedidoDto;
import com.github.tatianepro.rest.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDto pedidoDto) {
        Pedido pedido = pedidoService.salvar(pedidoDto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDto getById(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id)
                .map( pedido -> converter(pedido) )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    private InformacoesPedidoDto converter(Pedido p) {
        return new InformacoesPedidoDto
                .Builder()
                .codigo(p.getId())
                .cpf(p.getCliente().getCpf())
                .nomeCliente(p.getCliente().getNome())
                .data(p.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .total(p.getTotal())
                .status(p.getStatus().name())
                .items(converter(p.getItens()))
                .build();

    }

    private List<InformacoesItemPedidoDto> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        };

        return itens
                .stream()
                .map(itemPedido -> {
                    return new InformacoesItemPedidoDto
                            .Builder()
                            .descricaoProduto(itemPedido.getProduto().getDescricao())
                            .precoUnitario(itemPedido.getProduto().getPreco())
                            .quantidade(itemPedido.getQuantidade())
                            .build();
                }).collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id
                            , @RequestBody AtualizacaoStatusPedidoDto dto) {
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

}
