package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import view.auxiliar.CurrentUserWriter;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label labelUsuario;

    @FXML
    private void logOut(){
        try{
            App.setRoot("loginInicial");
        }catch (Exception e){
            e.printStackTrace();
        }

    };

    public void systemExit(){
        System.exit(0);
    }

    public void setRoot(String fxml){

        try {
            App.setRoot(fxml);
        }catch (Exception e){
            e.printStackTrace();
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

    public void callCadastros(){
        this.setRoot("menuCadastro");
    }

    public void callConsulta(){this.setRoot("menuConsulta");}

    /*
    public void callCadastrarFornecedor(){
        this.frameHandler("cadastroFornecedor.fxml",borderPane);
    }


    public void callCadastrarMorador(){
        this.frameHandler("cadastroMorador.fxml",borderPane);
    }
     */
    public void writeUser(Label label){
        CurrentUserWriter currentUserWriter = new CurrentUserWriter();
        label.setText("Usuário: " + currentUserWriter.readUser());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        writeUser(labelUsuario);
    }
}
