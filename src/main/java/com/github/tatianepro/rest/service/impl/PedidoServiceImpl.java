package com.github.tatianepro.rest.service.impl;

import com.github.tatianepro.domain.entity.Cliente;
import com.github.tatianepro.domain.entity.ItemPedido;
import com.github.tatianepro.domain.entity.Pedido;
import com.github.tatianepro.domain.entity.Produto;
import com.github.tatianepro.domain.repository.ClienteRepository;
import com.github.tatianepro.domain.repository.ItemPedidoRepository;
import com.github.tatianepro.domain.repository.PedidoRepository;
import com.github.tatianepro.domain.repository.ProdutoRepository;
import com.github.tatianepro.exception.RegraDeNegocioException;
import com.github.tatianepro.rest.dto.ItemPedidoDto;
import com.github.tatianepro.rest.dto.PedidoDto;
import com.github.tatianepro.rest.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ClienteRepository clienteRepository,
                             ProdutoRepository produtoRepository,
                             ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }


    @Override
    @Transactional
    public Pedido salvar(@RequestBody PedidoDto pedidoDto) {
        Integer clienteId = pedidoDto.getCliente();
        Cliente clienteExistente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setCliente(clienteExistente);
        pedido.setTotal(pedidoDto.getTotal());
        pedido.setDataPedido(LocalDate.now());

        List<ItemPedido> itensPedido = converterItensPedido(pedidoDto.getItens(), pedido);
        pedido.setItens(itensPedido);

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItensPedido(List<ItemPedidoDto> itensPedidoDto, Pedido pedido) {
        if (itensPedidoDto.isEmpty()) {
            throw new RegraDeNegocioException("Não é possível realizar um pedido sem itens");
        }

        return itensPedidoDto
                .stream()
                .map(itemPedidoDto -> {
                    Integer produtoId = itemPedidoDto.getProduto();
                    Produto produtoExistente = produtoRepository
                            .findById(produtoId)
                            .orElseThrow(() -> new RegraDeNegocioException("Código de Produto inválido -> " + produtoId));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produtoExistente);
                    itemPedido.setQuantidade(itemPedidoDto.getQuantidade());
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
