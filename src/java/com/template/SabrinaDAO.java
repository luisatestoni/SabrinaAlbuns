package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SabrinaDAO {
    ArrayList<SabrinaDTO> listaAlbuns = new ArrayList<>();
    public ArrayList<SabrinaDTO> selecionarAlbuns(){

        String sql = "SELECT * FROM albuns_sabrina_carpenter";


        // try-with-resources: fecha automaticamente Connection, PreparedStatement e ResultSet

        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            // Executa a consulta e percorre os resultados
            while (rs.next()) {

                SabrinaDTO album = new SabrinaDTO();

                album.setId(rs.getInt("id"));
                album.setNomeAlbum(rs.getString("nome_album"));
                album.setAnoLancamento(rs.getInt("ano_lancamento"));
                album.setGravadora(rs.getString("gravadora"));
                album.setGenero(rs.getString("genero"));
                album.setNumeroFaixas(rs.getInt("numero_faixas"));

                listaAlbuns.add(album);
            }


        } catch (SQLException ex) {
            Logger.getLogger(SabrinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlbuns;
    }

    public void cadastrarAlbum(SabrinaDTO album) {

        // SQL com parâmetros (evita SQL Injection)
        String sql = "INSERT INTO albuns_sabrina_carpenter " +
                "(nome_album, ano_lancamento, gravadora, genero, numero_faixas) " +
                "VALUES (?, ?, ?, ?, ?)";

        // try-with-resources: fecha automaticamente Connection e PreparedStatement
        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            // Preenchendo os parâmetros do SQL
            ps.setString(1, album.getNomeAlbum());
            ps.setInt(2, album.getAnoLancamento());
            ps.setString(3, album.getGravadora());
            ps.setString(4, album.getGenero());
            ps.setInt(5, album.getNumeroFaixas());

            ps.executeUpdate();



        } catch (SQLException e) {
            Logger.getLogger(SabrinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarAlbum(SabrinaDTO album) {

        String sql = "UPDATE albuns_sabrina_carpenter SET " +
                "nome_album = ?, ano_lancamento = ?, gravadora = ?, genero = ?, numero_faixas = ? " +
                "WHERE id = ?";

        // try-with-resources: fecha automaticamente Connection e PreparedStatement
        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            // Preenchendo parâmetros do UPDATE
            ps.setString(1, album.getNomeAlbum());
            ps.setInt(2, album.getAnoLancamento());
            ps.setString(3, album.getGravadora());
            ps.setString(4, album.getGenero());
            ps.setInt(5, album.getNumeroFaixas());
            ps.setInt(6, album.getId());

            ps.executeUpdate();



        } catch (SQLException e) {
            Logger.getLogger(SabrinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirAlbum(int id) {

        String sql = "DELETE FROM albuns_sabrina_carpenter WHERE id = ?";

        // try-with-resources: fecha automaticamente Connection e PreparedStatement
        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            // Definindo o ID para exclusão
            ps.setInt(1, id);

            ps.executeUpdate();



        } catch (SQLException e) {
            Logger.getLogger(SabrinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
