package com.template.controller;

import com.template.model.dao.SabrinaDAO;
import com.template.model.dto.SabrinaDTO;
import com.template.validator.AlbumValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import static com.template.util.DialogUtil.exibirAlerta;


public class MainController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNomeAlbum;

    @FXML
    private TextField txtAnoLancamento;

    @FXML
    private TextField txtGravadora;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtNumeroFaixas;

    @FXML
    private TableView<SabrinaDTO> tblAlbuns;

    @FXML
    private TableColumn<SabrinaDTO, Integer> colId;

    @FXML
    private TableColumn<SabrinaDTO, String> colNomeAlbum;

    @FXML
    private TableColumn<SabrinaDTO, Integer> colAnoLancamento;

    @FXML
    private TableColumn<SabrinaDTO, String> colGravadora;

    @FXML
    private TableColumn<SabrinaDTO, String> colGenero;

    @FXML
    private TableColumn<SabrinaDTO, Integer> colNumeroFaixas;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;


    private void carregarAlbuns() {
        SabrinaDAO objDAO = new SabrinaDAO();
        ArrayList<SabrinaDTO> lista = objDAO.selecionarAlbuns();
        tblAlbuns.setItems(FXCollections.observableArrayList(lista));
    }


    @FXML
    private void limparCampos() {
        if (txtId != null) txtId.clear();
        txtNomeAlbum.clear();
        txtAnoLancamento.clear();
        txtGravadora.clear();
        txtGenero.clear();
        txtNumeroFaixas.clear();
        tblAlbuns.getSelectionModel().clearSelection();
        txtNomeAlbum.requestFocus();
    }


    @FXML
    private void carregarCampos() {
        SabrinaDTO albumDto = tblAlbuns.getSelectionModel().getSelectedItem();

        if (albumDto != null) {
            if (txtId != null) txtId.setText(String.valueOf(albumDto.getId()));
            txtNomeAlbum.setText(albumDto.getNomeAlbum());
            txtAnoLancamento.setText(String.valueOf(albumDto.getAnoLancamento()));
            txtGravadora.setText(albumDto.getGravadora());
            txtGenero.setText(albumDto.getGenero());
            txtNumeroFaixas.setText(String.valueOf(albumDto.getNumeroFaixas()));
        }
    }


    @FXML
    private void initialize() {
        // Vincula as colunas da tabela com o DTO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeAlbum.setCellValueFactory(new PropertyValueFactory<>("nomeAlbum"));
        colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colGravadora.setCellValueFactory(new PropertyValueFactory<>("gravadora"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colNumeroFaixas.setCellValueFactory(new PropertyValueFactory<>("numeroFaixas"));

        // Adiciona o ouvinte para carregar os campos ao clicar em uma linha
        tblAlbuns.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                carregarCampos();
            }
        });


        configurarCampoNumerico(txtAnoLancamento);
        configurarCampoNumerico(txtNumeroFaixas);

        // O botão Editar fica desabilitado SE a propriedade "selectedItem" da tabela estiver vazia
        btnEditar.disableProperty().bind(tblAlbuns.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(tblAlbuns.getSelectionModel().selectedItemProperty().isNull());

        carregarAlbuns();
    }

    // MELHORIA: Valida campos vazios E se os campos numéricos possuem apenas números



    private void configurarCampoNumerico(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (!AlbumValidator.validarCampos( txtNomeAlbum, txtAnoLancamento, txtGravadora, txtGenero, txtNumeroFaixas)) { return; }

        SabrinaDTO albumDto = new SabrinaDTO();
        albumDto.setNomeAlbum(txtNomeAlbum.getText());
        albumDto.setAnoLancamento(Integer.parseInt(txtAnoLancamento.getText()));
        albumDto.setGravadora(txtGravadora.getText());
        albumDto.setGenero(txtGenero.getText());
        albumDto.setNumeroFaixas(Integer.parseInt(txtNumeroFaixas.getText()));

        SabrinaDAO dao = new SabrinaDAO();
        dao.cadastrarAlbum(albumDto);

        carregarAlbuns();
        limparCampos();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        SabrinaDTO albumSelecionado = tblAlbuns.getSelectionModel().getSelectedItem();

        if (albumSelecionado != null) {
            // Verifica se passou na validação antes de editar
            if (!AlbumValidator.validarCampos( txtNomeAlbum, txtAnoLancamento, txtGravadora, txtGenero, txtNumeroFaixas)) { return; }

            SabrinaDTO albumDto = new SabrinaDTO();
            albumDto.setId(albumSelecionado.getId());
            albumDto.setNomeAlbum(txtNomeAlbum.getText());
            albumDto.setAnoLancamento(Integer.parseInt(txtAnoLancamento.getText()));
            albumDto.setGravadora(txtGravadora.getText());
            albumDto.setGenero(txtGenero.getText());
            albumDto.setNumeroFaixas(Integer.parseInt(txtNumeroFaixas.getText()));

            SabrinaDAO dao = new SabrinaDAO();
            dao.atualizarAlbum(albumDto);

            carregarAlbuns();
            limparCampos();
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        SabrinaDTO albumSelecionado = tblAlbuns.getSelectionModel().getSelectedItem();

        if (albumSelecionado != null) {
            SabrinaDAO dao = new SabrinaDAO();
            dao.excluirAlbum(albumSelecionado.getId());

            carregarAlbuns();
            limparCampos();
        }
    }
}