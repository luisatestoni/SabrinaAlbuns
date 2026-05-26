package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController
{
    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
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
}