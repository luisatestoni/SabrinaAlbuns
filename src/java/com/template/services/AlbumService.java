package com.template.services;
import com.template.validator.ISabrinaDAO;
import com.template.model.dto.SabrinaDTO;
import com.template.validator.IAlbumService;

import java.util.ArrayList;

public class AlbumService implements IAlbumService {
    private final ISabrinaDAO dao; // era SabrinaDAO concreto — trocar pela interface

    public AlbumService(ISabrinaDAO dao) {
        this.dao = dao;
    }

    public ArrayList<SabrinaDTO> selecionarAlbuns() {
        return dao.selecionarAlbuns();
    }

    public void cadastrarAlbum(String nome, String ano, String gravadora, String genero, String faixas) {
        SabrinaDTO dto = criarDTO(null, nome, ano, gravadora, genero, faixas);
        dao.cadastrarAlbum(dto);
    }

    public void atualizarAlbum(Integer id, String nome, String ano, String gravadora, String genero, String faixas) {
        SabrinaDTO dto = criarDTO(id, nome, ano, gravadora, genero, faixas);
        dao.atualizarAlbum(dto);
    }

    public void excluirAlbum(Integer id) {
        dao.excluirAlbum(id);
    }

    private SabrinaDTO criarDTO(Integer id, String nome, String ano, String gravadora, String genero, String faixas) {
        SabrinaDTO dto = new SabrinaDTO();
        if (id != null) dto.setId(id);
        dto.setNomeAlbum(nome);
        dto.setAnoLancamento(Integer.parseInt(ano));
        dto.setGravadora(gravadora);
        dto.setGenero(genero);
        dto.setNumeroFaixas(Integer.parseInt(faixas));
        return dto;
    }
}
