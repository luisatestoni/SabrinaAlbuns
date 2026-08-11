package com.template.validator;

import static com.template.util.DialogUtil.exibirAlerta;

import javafx.scene.control.TextField;

import java.time.Year;

public class AlbumValidator {

    public static boolean validarCampos(
            TextField txtNomeAlbum,
            TextField txtAnoLancamento,
            TextField txtGravadora,
            TextField txtGenero,
            TextField txtNumeroFaixas) {

        // 1. Verifica se algum campo está em branco
        if (txtNomeAlbum.getText().trim().isEmpty() ||
                txtAnoLancamento.getText().trim().isEmpty() ||
                txtGravadora.getText().trim().isEmpty() ||
                txtGenero.getText().trim().isEmpty() ||
                txtNumeroFaixas.getText().trim().isEmpty()) {

            exibirAlerta(
                    "Campos Obrigatórios",
                    "Aviso de Validação",
                    "Por favor, preencha todos os campos antes de continuar!"
            );

            return false;
        }

        // 2. Verifica se o ano de lançamento é válido
        int ano = Integer.parseInt(txtAnoLancamento.getText());

        if (ano < 1900 || ano > Year.now().getValue()) {

            exibirAlerta(
                    "Ano Inválido",
                    "Aviso de Validação",
                    "Informe um ano de lançamento válido."
            );

            return false;
        }

        return true;
    }
}

