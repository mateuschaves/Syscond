package view;

import dao.UsuarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import negocios.ApartamentoNegocios;
import negocios.FornecedorNegocios;
import negocios.UsuarioNegocios;
import pojos.Apartamento;
import pojos.Usuario;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Responsavel por gerenciar e controlar a tela de procurarUsuario.fxml;
 */
public class ProcuraUsuarioController implements Initializable {

    @FXML
    private TextField textFieldLogin;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn senhaCollumn;
    @FXML
    private TableColumn loginCollumn;
    @FXML
    private Button deletarUsuario;

    private Usuario usuarioToDelete = new Usuario();

    /**
     * metodo que pega uma lista de usuario passa para a classe Campos e essa classe vai exibir os dados na
     * tableview da tela de consulta de usuario.
     */
    @FXML
    private void listarUsuario() {

        UsuarioNegocios usuarioNegocios = new UsuarioNegocios();
        this.textFieldLogin.setText("");

        try{

            List<Usuario> list = usuarioNegocios.listarUsuarios();
            Collection<Campos> listCampos = new ArrayList<>();

            for(Usuario a: list){

                listCampos.add(new Campos(a.getNome(),a.getSenha(), a.getLogin()));
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(listCampos);

            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            senhaCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            loginCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));

            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){

        }
    }


    /**
     * metodo que pega um textField e passa para um usuario e depois verifica se esse usuario existe e passa
     * para a classe Campos para exibir os dados na tableview da consulta de usuario.
     */
    @FXML
    private void procurarUsuario(){
        UsuarioNegocios usuarioNegocios  = new UsuarioNegocios();
        String login = textFieldLogin.getText();

        if(login == ""){
            this.listarUsuario();
            return;
        }

        try{

            Usuario usuario = new Usuario(login);
            usuario = usuarioNegocios.pesquisar(usuario);
            System.out.println("Nome do Usuário: " + usuario.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(usuario.getNome(),usuario.getSenha(),usuario.getLogin())
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            senhaCollumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            loginCollumn.setCellValueFactory(new PropertyValueFactory("campo3"));

            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(
                    new Image("/img/syscondLogo.png"));
            alert.setTitle("Erro");
            alert.setHeaderText("Usuario de sistema não encontrado");
            alert.setContentText("O Usuario: " + login + " não foi encontrado na base de dados " +
                    "caso queira casdastra-lo, basta ir em Usuário, no menu de cadastros.");
            alert.show();
            textFieldLogin.setText("");
        }
    }
    /**
     * metodo que volta para a tela anterior.
     */
    @FXML
    private void voltar(){
        try {
            App.setRoot("menuConsulta");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo que deleta um usuario atraves de um objeto.
     */
    @FXML
    private void deletarUsuario() {
        UsuarioNegocios usuarioNegocios = new UsuarioNegocios();
        usuarioToDelete = usuarioNegocios.pesquisar(usuarioToDelete);//completa todos os atributos através da pesquisa

        usuarioNegocios.deletar(this.usuarioToDelete);
        this.listarUsuario();
        deletarUsuario.setDisable(true);
        this.textFieldLogin.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarUsuario();
        mainPane.setOnMousePressed(event -> {
        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarUsuario();
            }
        });

        mainPane.setOnMousePressed((MouseEvent)->{
            deletarUsuario.setDisable(true);
            mainPane.requestFocus();
        });

        tableView.setOnMouseClicked((MouseEvent e) -> {

            this.deletarUsuario.setDisable(false);
            String login = tableView.getSelectionModel().getSelectedItem().getCampo3();
            this.usuarioToDelete.setLogin(login);

        });
    }
}
