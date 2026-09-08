package com.template.validator;

import java.util.ArrayList;
import java.util.List;
import static com.template.util.DialogUtil.exibirAlerta;

public class AlbumValidator implements IAlbumValidator{

    public boolean validarAlbum(String nomeAlbum, String anoLancamento, String gravadora, String genero, String numeroFaixas) {
        // Lista de validadores que serão aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("Nome do Álbum", nomeAlbum));
        validadores.add(new CampoObrigatorioValidador("Ano de Lançamento", anoLancamento));
        validadores.add(new CampoObrigatorioValidador("Gravadora", gravadora));
        validadores.add(new CampoObrigatorioValidador("Gênero", genero));
        validadores.add(new CampoObrigatorioValidador("Número de Faixas", numeroFaixas));

        // Adicionando o validador específico (aplicado ao campo numeroFaixas)
        validadores.add(new NumeroFaixasValidador(numeroFaixas));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {
                exibirAlerta("Aviso de Validação", null, validador.getMensagemErro());
                return false; // Retorna falso na primeira falha de validação
            }
        }
        return true; // Todos os validadores passaram
    }
}