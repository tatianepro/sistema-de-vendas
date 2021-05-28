package com.github.tatianepro.rest.dto;

import java.math.BigDecimal;
import java.util.List;

/*
{
	"cliente" : 1,
	"total" : 100.00,
	"itens" : [
		{
			"produto" : 1,
			"quantidade" : 100
		},
		{
			"produto" : 2,
			"quantidade" : 5
		}
	]
}
 */

public class PedidoDto {
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDto> itens;

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemPedidoDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDto> itens) {
        this.itens = itens;
    }
}
