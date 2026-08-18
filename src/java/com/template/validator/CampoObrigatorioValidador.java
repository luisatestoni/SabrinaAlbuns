package com.template.validator;


public class CampoObrigatorioValidador implements Validador<String>{
    private final String nomeCampo;
    private final String valor;

    public CampoObrigatorioValidador(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }


    @Override
    public boolean validar(String valor) {
        return false;
    }

    @Override
    public String getMensagemErro() {
        return "";
    }

    @Override
    public String getValor() {
        return "";
    }
}




