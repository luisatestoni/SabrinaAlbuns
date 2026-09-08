package com.template.model.dao;

import com.template.model.Conexao;
import com.template.model.dto.SabrinaDTO;
import com.template.validator.ISabrinaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.template.util.DialogUtil.mostrarErro;

public class SabrinaDAO implements ISabrinaDAO {
    public ArrayList<SabrinaDTO> selecionarAlbuns(){

        ArrayList<SabrinaDTO> listaAlbuns = new ArrayList<>();
        String sql = "SELECT * FROM albuns_sabrina_carpenter";

        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

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
            mostrarErro("Por favor, preencha todos os campos.");

        }
        return listaAlbuns;
    }

    public void cadastrarAlbum(SabrinaDTO album) {
        String sql = "INSERT INTO albuns_sabrina_carpenter " +
                "(nome_album, ano_lancamento, gravadora, genero, numero_faixas) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1, album.getNomeAlbum());
            ps.setInt(2, album.getAnoLancamento());
            ps.setString(3, album.getGravadora());
            ps.setString(4, album.getGenero());
            ps.setInt(5, album.getNumeroFaixas());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SabrinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            mostrarErro("Por favor, preencha todos os campos.");
        }
    }

    public void atualizarAlbum(SabrinaDTO album) {
        String sql = "UPDATE albuns_sabrina_carpenter SET " +
                "nome_album = ?, ano_lancamento = ?, gravadora = ?, genero = ?, numero_faixas = ? " +
                "WHERE id = ?";

        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1, album.getNomeAlbum());
            ps.setInt(2, album.getAnoLancamento());
            ps.setString(3, album.getGravadora());
            ps.setString(4, album.getGenero());
            ps.setInt(5, album.getNumeroFaixas());
            ps.setInt(6, album.getId());

            ps.executeUpdate();

        } catch (SQLException ex) { // Corrigido de 'e' para 'ex'
            Logger.getLogger(SabrinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            mostrarErro("Por favor, preencha todos os campos.");
        }
    }

    public void excluirAlbum(int id) {
        String sql = "DELETE FROM albuns_sabrina_carpenter WHERE id = ?";

        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SabrinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            mostrarErro("Por favor, preencha todos os campos.");
        }
    }
}