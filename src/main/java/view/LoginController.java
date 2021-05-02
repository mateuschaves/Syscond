package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import negocios.UsuarioNegocios;

import pojos.Usuario;
import view.auxiliar.CurrentUserWriter;
import view.auxiliar.RememberMe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    UsuarioNegocios usuarioNegocios = new UsuarioNegocios();

    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passwordFieldSenha;
    @FXML
    private CheckBox rememberMeBox;

    @FXML
    private BorderPane pane;
    private CurrentUserWriter currentUserWriter = new CurrentUserWriter();
    private RememberMe rememberMe = new RememberMe();

    @FXML
    private void autenticar(){

        String login = textFieldLogin.getText(), senha = passwordFieldSenha.getText();

        Usuario user = new Usuario(login,senha);

        Usuario autenticado = usuarioNegocios.autenticar(user);

        if(autenticado != null){// se o usuario conseguir entrar;

            System.out.println(usuarioNegocios.autenticar(user));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText("Bem vindo ao Syscond");
            alert.show();
            try {
                currentUserWriter.writeUser(autenticado.getNome());
                rememberMeRegister();
                view.App.setRoot("menu");
                //System.out.println("login: " + login);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        else{
            System.out.println(usuarioNegocios.autenticar(user));
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Erro!");
            alert.setHeaderText("Não foi possivel logar.");
            alert.setContentText("ouve um erro ao tentar logar no sistema," +
                    " por favor, verifique seu login e senha!");
            alert.show();
        }

    }
    @FXML
    private void cadastrar() throws IOException{
        //System.out.println("Cadastrar");
        view.App.setRoot("cadastroUsuarioLogin");
        //view.App.setRoot("cadastrar");
    }

    private void rememberMeRegister(){
        if(this.rememberMeBox.isSelected()){
            System.out.println("SELECTED");
            try {
                rememberMe.writeUser(textFieldLogin.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            rememberMe.deleteFile();
        }
    }

    private void rememberMeStart(){

        try{
            rememberMe.readUser().equals(null);
            String login = rememberMe.readUser();

            Usuario current = usuarioNegocios.pesquisar(login);

            rememberMeBox.setSelected(true);

            this.textFieldLogin.setText(current.getLogin());
            this.passwordFieldSenha.setText(current.getSenha());
        }catch (NullPointerException e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rememberMeStart();
        pane.setOnKeyPressed( (keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER)
                autenticar();
        } );
    }
}
