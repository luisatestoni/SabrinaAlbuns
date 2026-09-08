package com.template;

import com.template.controller.MainController;
import com.template.model.dao.SabrinaDAO;
import com.template.services.AlbumService;
import com.template.validator.AlbumValidator;
import com.template.validator.IAlbumService;
import com.template.validator.IAlbumValidator;
import com.template.validator.ISabrinaDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));

        ISabrinaDAO dao = new SabrinaDAO();
        IAlbumService albumService = new AlbumService(dao);
        IAlbumValidator albumValidator = new AlbumValidator();

        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == MainController.class) {
                return new MainController(albumService, albumValidator);
            }
            try {
                return controllerClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Parent root = loader.load();

        Scene scene = new Scene(root,600,400);

        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}