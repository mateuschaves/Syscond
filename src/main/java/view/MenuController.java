package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Cylinder;
import javafx.stage.*;
import javafx.scene.control.PopupControl;
import view.auxiliar.CurrentUserWriter;

import javax.el.LambdaExpression;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class MenuController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label labelUsuario;


    public void systemExit(){
        System.exit(0);
    }

    private void setRoot(String fxml){

        try {
            view.App.setRoot(fxml);
        }catch (Exception e){

        }

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

    /*
    public void callCadastrarFornecedor(){
        this.frameHandler("cadastroFornecedor.fxml",borderPane);
    }
    public void callCadastrarApartamento(){
        this.frameHandler("cadastroApartamento.fxml",borderPane);
    }

    public void callCadastrarMorador(){
        this.frameHandler("cadastroMorador.fxml",borderPane);
    }
     */
    public void voltar(){

        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CurrentUserWriter currentUserWriter = new CurrentUserWriter();
        labelUsuario.setText("Usuario: " + currentUserWriter.readUser());
    }
}
