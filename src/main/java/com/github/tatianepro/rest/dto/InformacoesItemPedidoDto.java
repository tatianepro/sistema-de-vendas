package com.github.tatianepro.rest.dto;

import java.math.BigDecimal;

public class InformacoesItemPedidoDto {
    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;

    private InformacoesItemPedidoDto(Builder builder) {
        this.descricaoProduto = builder.descricaoProduto;
        this.precoUnitario = builder.precoUnitario;
        this.quantidade = builder.quantidade;
    }

    public static class Builder {
        private String descricaoProduto;
        private BigDecimal precoUnitario;
        private Integer quantidade;

        public Builder descricaoProduto(String descricaoProduto) {
            this.descricaoProduto = descricaoProduto;
            return this;
        }

        public Builder precoUnitario(BigDecimal precoUnitario) {
            this.precoUnitario = precoUnitario;
            return this;
        }

        public Builder quantidade(Integer quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public InformacoesItemPedidoDto build() {
            return new InformacoesItemPedidoDto(this);
        }
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
