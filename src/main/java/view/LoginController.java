package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import negocios.UsuarioNegocios;

import javafx.scene.control.PasswordField;
import pojos.Usuario;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passwordField;


    @FXML
    private void autenticar(){

        UsuarioNegocios usuarioNegocios = new UsuarioNegocios();

        String login = textFieldLogin.getText(), senha = passwordField.getText();

        Usuario user = new Usuario(login,senha);

        if(usuarioNegocios.autenticar(user)){
            System.out.println(usuarioNegocios.autenticar(user));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Sucesso!");
            alert.setHeaderText("Bem vindo ao Syscond");
            alert.show();
            try {
                view.App.setRoot("menu");
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
        view.App.setRoot("cadastrar");
        //view.App.setRoot("cadastrar");
    }
    @FXML
    private void switchToSecondary() throws IOException {
        view.App.setRoot("secondary");
    }

}
