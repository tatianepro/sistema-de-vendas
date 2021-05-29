package com.github.tatianepro.rest.dto;

import com.github.tatianepro.domain.entity.Produto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProdutoDto {
    @NotEmpty(message = "Campo descrição é obrigatório.")
    private String descricao;
    @NotNull(message = "Campo preço é obrigatório.")
    @PositiveOrZero(message = "Campo preço tem valor positivo.")
    private BigDecimal preco;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Produto toProduto() {
        return new Produto(this.descricao, this.preco);
    }
}
