package com.github.tatianepro.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class InformacoesPedidoDto {
    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private String data;
    private BigDecimal total;
    private List<InformacoesItemPedidoDto> items;

    private InformacoesPedidoDto(Builder builder) {
        this.codigo = builder.codigo;
        this.cpf = builder.cpf;
        this.nomeCliente = builder.nomeCliente;
        this.data = builder.data;
        this.total = builder.total;
        this.items = builder.items;
    }

    public static class Builder {
        private Integer codigo  ;
        private String cpf;
        private String nomeCliente;
        private String data;
        private BigDecimal total;
        private List<InformacoesItemPedidoDto> items;

        public Builder codigo(Integer codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder nomeCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
            return this;
        }

        public Builder data(String data) {
            this.data = data;
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Builder items(List<InformacoesItemPedidoDto> items) {
            this.items = items;
            return this;
        }

        public InformacoesPedidoDto build() {
            return new InformacoesPedidoDto(this);
        }

    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getData() {
        return data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<InformacoesItemPedidoDto> getItems() {
        return items;
    }
}
