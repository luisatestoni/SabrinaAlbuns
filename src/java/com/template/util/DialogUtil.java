package com.template.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class DialogUtil {

    public static void exibirAlerta(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.showAndWait();
    }

    public static void mostrarErro(String mensagem) {
        Alert alert =  new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
