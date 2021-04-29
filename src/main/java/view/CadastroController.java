package view;

import dao.UsuarioDao;
import dao.UsuarioDaoInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

import negocios.UsuarioNegocios;
import pojos.Usuario;

public class CadastroController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private PasswordField PsConfirmarSenha;

    @FXML
    public void cadastrar(){
        UsuarioNegocios usuarioNegocios = new UsuarioNegocios();

        String nome = textFieldNome.getText(),
                login = textFieldLogin.getText(),
                senha = passwordFieldSenha.getText(),
                confirmarSenha = PsConfirmarSenha.getText();


        if(!senha.equals(confirmarSenha)){
            System.out.println("FUDEU");
            return;
        }

        Usuario usuario = new Usuario(nome,login,senha);

        System.out.println("Usuario: ");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Login: " + usuario.getLogin());
        System.out.println("Senha: " + usuario.getSenha());
        System.out.println("Senha c:" + confirmarSenha);
        UsuarioDaoInterface usuarioDao = new UsuarioDao();
        try {
            usuarioDao.adicionar(usuario);
        }catch (Exception e){
            System.out.println("ERRO");
        }
    }

    public void voltar(){
        try {
            view.App.setRoot("login");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
