package com.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.util.ArrayList;

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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeAlbum.setCellValueFactory(new PropertyValueFactory<>("nomeAlbum"));
        colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colGravadora.setCellValueFactory(new PropertyValueFactory<>("gravadora"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colNumeroFaixas.setCellValueFactory(new PropertyValueFactory<>("numeroFaixas"));

        configurarCampoNumerico(txtAnoLancamento);
        configurarCampoNumerico(txtNumeroFaixas);

        tblAlbuns.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                carregarCampos();
            }
        });

        carregarAlbuns();
    }

    private void configurarCampoNumerico(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    // NOVA MELHORIA: Método auxiliar para validar se todos os campos estão preenchidos
    private boolean validarCampos() {
        if (txtNomeAlbum.getText().trim().isEmpty() ||
                txtAnoLancamento.getText().trim().isEmpty() ||
                txtGravadora.getText().trim().isEmpty() ||
                txtGenero.getText().trim().isEmpty() ||
                txtNumeroFaixas.getText().trim().isEmpty()) {

            // Cria uma caixinha de alerta amigável na tela (Padrão do JavaFX)
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Campos Obrigatórios");
            alerta.setHeaderText("Aviso de Validação");
            alerta.setContentText("Por favor, preencha todos os campos antes de salvar ou editar!");
            alerta.showAndWait();

            return false; // Retorna falso porque faltam dados
        }
        return true; // Todos os campos estão preenchidos corretamente
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        // Verifica se passou na validação. Se não passou, para a execução aqui!
        if (!validarCampos()) {
            return;
        }

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
            if (!validarCampos()) {
                return;
            }

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