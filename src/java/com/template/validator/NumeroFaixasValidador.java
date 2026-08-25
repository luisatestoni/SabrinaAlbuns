package com.template.validator;

public class NumeroFaixasValidador implements Validador<String> {
    private final String valor;
    private String mensagemErro;

    public NumeroFaixasValidador(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        try {
            int faixas = Integer.parseInt(valor.trim());
            if (faixas <= 0) {
                this.mensagemErro = "O álbum deve conter pelo menos 1 faixa.";
                return false;
            }
        } catch (Exception e) {
            this.mensagemErro = "Informe um número válido de faixas.";
            return false;
        }
        return true;
    }

    @Override
    public String getMensagemErro() {
        return this.mensagemErro;
    }

    @Override
    public String getValor() {
        return this.valor;
    }
}