package com.template.controller;

import com.template.model.dto.SabrinaDTO;
import com.template.validator.IAlbumService;
import com.template.services.IUServices;
import com.template.validator.IAlbumValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.util.ArrayList;

public class MainController {

    @FXML private TextField txtId;
    @FXML private TextField txtNomeAlbum;
    @FXML private TextField txtAnoLancamento;
    @FXML private TextField txtGravadora;
    @FXML private TextField txtGenero;
    @FXML private TextField txtNumeroFaixas;

    @FXML private TableView<SabrinaDTO> tblAlbuns;
    @FXML private TableColumn<SabrinaDTO, Integer> colId;
    @FXML private TableColumn<SabrinaDTO, String> colNomeAlbum;
    @FXML private TableColumn<SabrinaDTO, Integer> colAnoLancamento;
    @FXML private TableColumn<SabrinaDTO, String> colGravadora;
    @FXML private TableColumn<SabrinaDTO, String> colGenero;
    @FXML private TableColumn<SabrinaDTO, Integer> colNumeroFaixas;

    @FXML private Button btnEditar;
    @FXML private Button btnDeletar;

    // Instâncias das classes de serviço e validação (Declaradas dentro da classe)
    private final IAlbumService albumService;
    private final IAlbumValidator albumValidator;

    public MainController(IAlbumService albumService, IAlbumValidator albumValidator) {
        this.albumService = albumService;
        this.albumValidator = albumValidator;
    }

    private void carregarAlbuns() {
        ArrayList<SabrinaDTO> lista = albumService.selecionarAlbuns();
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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeAlbum.setCellValueFactory(new PropertyValueFactory<>("nomeAlbum"));
        colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colGravadora.setCellValueFactory(new PropertyValueFactory<>("gravadora"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colNumeroFaixas.setCellValueFactory(new PropertyValueFactory<>("numeroFaixas"));

        tblAlbuns.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                carregarCampos();
            }
        });

        IUServices.configurarCampoNumerico(txtAnoLancamento);
        IUServices.configurarCampoNumerico(txtNumeroFaixas);

        btnEditar.disableProperty().bind(tblAlbuns.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(tblAlbuns.getSelectionModel().selectedItemProperty().isNull());

        carregarAlbuns();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        boolean valido = albumValidator.validarAlbum(
                txtNomeAlbum.getText(),
                txtAnoLancamento.getText(),
                txtGravadora.getText(),
                txtGenero.getText(),
                txtNumeroFaixas.getText()
        );

        if (!valido) {
            return;
        }

        albumService.cadastrarAlbum(
                txtNomeAlbum.getText(),
                txtAnoLancamento.getText(),
                txtGravadora.getText(),
                txtGenero.getText(),
                txtNumeroFaixas.getText()
        );

        carregarAlbuns();
        limparCampos();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        SabrinaDTO albumSelecionado = tblAlbuns.getSelectionModel().getSelectedItem();

        if (albumSelecionado != null) {
            boolean valido = albumValidator.validarAlbum(
                    txtNomeAlbum.getText(),
                    txtAnoLancamento.getText(),
                    txtGravadora.getText(),
                    txtGenero.getText(),
                    txtNumeroFaixas.getText()
            );

            if (!valido) {
                return;
            }

            albumService.atualizarAlbum(
                    albumSelecionado.getId(),
                    txtNomeAlbum.getText(),
                    txtAnoLancamento.getText(),
                    txtGravadora.getText(),
                    txtGenero.getText(),
                    txtNumeroFaixas.getText()
            );

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
            albumService.excluirAlbum(albumSelecionado.getId());

            carregarAlbuns();
            limparCampos();
        }
    }
}