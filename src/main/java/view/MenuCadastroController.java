package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsavel por gerenciar e controlar a tela de menuCadastro.fxml;
 */
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

    /**
     * metodo usado para chamar a tela de cadastro de Apartamento.
     */
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

    /**
     * metodo usado para chamar a tela de cadastro de Fornecedor.
     */
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

    /**
     * metodo usado para chamar a tela de cadastro de Funcionario.
     */
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

    /**
     * metodo usado para chamar a tela de cadastro de Usuario.
     */
    public void exibirCadastroUsuario() {

        Node node;

        try {
            node = FXMLLoader.load(getClass().getResource("cadastroUsuario.fxml"));
            formularyPane.getChildren().clear();
            formularyPane.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo usado para chamar a tela de cadastro de Carro.
     */
    public void exibirCadastroCarro(){

        Node node;

        try {
            node = FXMLLoader.load(getClass().getResource("cadastroCarro.fxml"));
            formularyPane.getChildren().clear();
            formularyPane.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo usado para chamar a tela de cadastro de Visitante.
     */
    public void exibirCadastroVisitante(){

        Node node;

        try {
            node = FXMLLoader.load(getClass().getResource("cadastroVisitante.fxml"));
            formularyPane.getChildren().clear();
            formularyPane.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo usado para chamar a tela de cadastro de Morador.
     */
    public void exibirCadastroMorador(){

        Node node;

        try {
            node = FXMLLoader.load(getClass().getResource("cadastroMorador.fxml"));
            formularyPane.getChildren().clear();
            formularyPane.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        writeUser(labelUsuario);// ecreve na tela o nome do usurio atual logado;

        pane.setOnKeyPressed( (keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ESCAPE)
                backMenu();
        } );
    }
}
