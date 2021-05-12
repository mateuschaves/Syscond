package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import view.auxiliar.CurrentUserWriter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsavel por gerenciar e controlar a tela de menu.fxml;
 */
public class MenuController implements Initializable {

    @FXML
    private FlowPane mainPane;

    @FXML
    private Label labelUsuario;

    /**
     * metodo usado para fazer o logoff e voltar para a tela inicial
     */
    @FXML
    private void logOut(){
        try{
            App.setRoot("loginInicial");
        }catch (Exception e){
            e.printStackTrace();
        }

    };

    /**
     * metodo usado para quando o usuario aperta em sair e o programa eh finalizado.
     */
    public void systemExit(){
        System.exit(0);
    }

    /**
     *
     * @param fxml utilizado para colocar uma tela.
     */
    public void setRoot(String fxml){

        try {
            App.setRoot(fxml);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void frameHandler (String frame, FlowPane pane){
        Node node = null;
        try{
            node = FXMLLoader.load(getClass().getResource(frame));

        }catch (Exception e){
            e.printStackTrace();
        }

        pane.getChildren().clear();
        pane.getChildren().add(node);
    }

    /**
     * metodo que chama a tela de cadastros.
     */
    public void callCadastros(){
        this.setRoot("menuCadastro");
    }

    /**
     * metodo que chama a tela de consulta.
     */
    public void callConsulta(){this.setRoot("menuConsulta");}

    /**
     * metodo que chama a tela de sobre, o senhor vai gostar da surpresa professor! :)
     */
    public void callSobre(){this.frameHandler("sobre.fxml",this.mainPane);};

    /*
    public void callCadastrarFornecedor(){
        this.frameHandler("cadastroFornecedor.fxml",borderPane);
    }


    public void callCadastrarMorador(){
        this.frameHandler("cadastroMorador.fxml",borderPane);
    }
     */

    /**
     *
     * @param label utilizado para escrever na tela o nome do usuario logado no sistema.
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
