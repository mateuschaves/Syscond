package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Cylinder;
import javafx.stage.*;
import javafx.scene.control.PopupControl;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MenuController {

    @FXML
    private BorderPane borderPane;

    public void exit(){
        System.exit(0);
    }



    public void showPopup(){

        try {
            App.setRoot("teste");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void voltar(){

        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
