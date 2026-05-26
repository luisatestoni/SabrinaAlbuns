package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    static String conexao = "jdbc:postgresql://localhost:5432/sabrina";
    static String usuario = "postgres";
    static String senha = "postgres";

    /**
     * Abre e retorna uma conexão com o banco de dados.
     * Em caso de falha, lança RuntimeException com mensagem padronizada.
     */

    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(conexao, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());

        }
    }
}
