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

    @FXML
    private void listarUsuario() {

        UsuarioNegocios usuarioNegocios = new UsuarioNegocios();

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

            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                deletarUsuario.setDisable(false);
                String UsuarioNomeToDelete = tableView.getSelectionModel().getSelectedItem().getCampo1();
                String UsuarioSenhaToDelete = tableView.getSelectionModel().getSelectedItem().getCampo2();
                String UsuarioLoginToDelete = tableView.getSelectionModel().getSelectedItem().getCampo2();
                this.usuarioToDelete.setNome(UsuarioNomeToDelete);
                this.usuarioToDelete.setSenha(UsuarioSenhaToDelete);
                this.usuarioToDelete.setLogin(UsuarioLoginToDelete);
            });

        }catch (Exception e){

        }
    }


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
            if(usuario == null) {
                return;
            }
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
            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                this.deletarUsuario.setDisable(false);
                String nome = tableView.getSelectionModel().getSelectedItem().getCampo1();
                String senha = tableView.getSelectionModel().getSelectedItem().getCampo2();
                String login1 = tableView.getSelectionModel().getSelectedItem().getCampo3();
                this.usuarioToDelete.setNome(nome);
                this.usuarioToDelete.setSenha(senha);
                this.usuarioToDelete.setLogin(login1);

            });
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
        UsuarioNegocios usuarioNegocios = new UsuarioNegocios();
        usuarioNegocios.deletar(this.usuarioToDelete);
        this.listarUsuario();
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
    }
}
