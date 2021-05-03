package view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import negocios.CarroNegocios;
import pojos.Carro;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroCarroController{

    @FXML
    private TextField textFieldPlaca;
    @FXML
    private TextField textFieldModelo;
    @FXML
    private TextField textFieldCor;


    public void cadastrarCarro(){

        String  placa = textFieldPlaca.getText(),
                modelo = textFieldModelo.getText(),
                cor = textFieldCor.getText();

        CarroNegocios carroNegocios = new CarroNegocios();

        Carro carro = new Carro(placa,modelo,cor);

        carroNegocios.cadastrar(carro);
    }

    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
