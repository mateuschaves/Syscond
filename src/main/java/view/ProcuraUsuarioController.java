package view;

import dao.UsuarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import negocios.UsuarioNegocios;
import pojos.Usuario;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Button deletarUsuario;

    private Usuario usuarioToDelete = new Usuario();

    @FXML
    private void procurarUsuario(){
        UsuarioNegocios usuarioNegocios  = new UsuarioNegocios();
        String login = textFieldLogin.getText();


        try{

            Usuario usuario = new Usuario(login);
            usuario = usuarioNegocios.pesquisar(usuario);
            if(usuario == null) {
                return;
            }
            System.out.println("Nome do Usuário: " + usuario.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(usuario.getNome(),usuario.getSenha())
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            senhaCollumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());

                this.deletarUsuario.setDisable(false);

                this.usuarioToDelete.setLogin(login);
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void voltar(){
        try {
            App.setRoot("menuConsulta");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deletarUsuario() {
        UsuarioDao usuarioDao = new UsuarioDao();
        this.usuarioToDelete = usuarioDao.procurar(this.usuarioToDelete.getLogin());
        usuarioDao.remover(this.usuarioToDelete);
        this.tableView.getItems().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainPane.setOnMousePressed(event -> {

        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarUsuario();
            }
        });

    }



}
