package com.template.validator;
import com.template.model.dao.SabrinaDAO;

import com.template.model.dto.SabrinaDTO;
import java.util.ArrayList;

public interface ISabrinaDAO {
    ArrayList<SabrinaDTO> selecionarAlbuns();
    void cadastrarAlbum(SabrinaDTO album);
    void atualizarAlbum(SabrinaDTO album);
    void excluirAlbum(int id);
}
