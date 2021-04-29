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

    public void frameHandler (String frame, BorderPane pane ){
        Node node = null;
        try{
            node = FXMLLoader.load(getClass().getResource(frame));

        }catch (Exception e){
            e.printStackTrace();
        }

        pane.getChildren().clear();
        pane.getChildren().add(node);
    }

    public void cadastrarFornecedor(){
        this.frameHandler("cadastroFornecedor.fxml",borderPane);
    }
    public void cadastrarApartamento(){
        this.frameHandler("cadastroApartamento.fxml",borderPane);
    }

    public void voltar(){

        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
