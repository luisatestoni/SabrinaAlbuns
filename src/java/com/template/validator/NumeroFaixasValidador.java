package com.template.validator;

public class NumeroFaixasValidador implements Validador<Integer> {

    private Integer valor;
    private String mensagemErro;

    @Override
    public boolean validar(Integer valor) {
        this.valor = valor;

        if (valor == null || valor <= 0) {
            this.mensagemErro = "O álbum deve conter pelo menos 1 faixa.";
            return false;
        }

        return true;
    }

    @Override
    public String getMensagemErro() {
        return this.mensagemErro;
    }

    @Override
    public Integer getValor() {
        return this.valor;
    }
}