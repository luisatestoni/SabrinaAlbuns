
package com.template.validator;

import com.template.model.dto.SabrinaDTO;
import java.util.ArrayList;

public interface IAlbumService {
    ArrayList<SabrinaDTO> selecionarAlbuns();
    void cadastrarAlbum(String nome, String ano, String gravadora, String genero, String faixas);
    void atualizarAlbum(Integer id, String nome, String ano, String gravadora, String genero, String faixas);
    void excluirAlbum(Integer id);
}
