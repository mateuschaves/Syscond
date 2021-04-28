package view;

import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {

    @FXML
    private void autenticar(){
        System.out.println("Autenticando........");

    }
    @FXML
    private void cadastrar() throws IOException{
        //System.out.println("Cadastrar");
        view.App.setRoot("cadastrar");
    }
    @FXML
    private void switchToSecondary() throws IOException {
        view.App.setRoot("secondary");
    }

}
