package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import view.auxiliar.CurrentUserWriter;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCadastroController extends MenuController implements Initializable {

    @FXML
    private BorderPane pane;
    @FXML
    private Label labelUsuario;
    @FXML
    private FlowPane formularyPane;
    @FXML
    private void backMenu(){
        this.setRoot("menu");
    }

    public void exibirCadastroAp(){

        Node node;

        try{
            node = FXMLLoader.load(getClass().getResource(
                    "cadastroApartamentoPrototype.fxml"));
            formularyPane.getChildren().clear();
            formularyPane.getChildren().add(node);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void exibirCadastroFornecedor(){

        Node node;

        try{
            node = FXMLLoader.load(getClass().getResource(
                    "cadastroFornecedor.fxml"));
            formularyPane.getChildren().clear();
            formularyPane.getChildren().add(node);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void exibirCadastroFuncionario(){

        Node node;

        try{
            node = FXMLLoader.load(getClass().getResource("cadastroFuncionario.fxml"));
            formularyPane.getChildren().clear();
            formularyPane.getChildren().add(node);
        }catch (Exception e){
            e.printStackTrace();
        }

    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        writeUser(labelUsuario);
        pane.setOnKeyPressed( (keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ESCAPE)
                backMenu();
        } );
    }
}
