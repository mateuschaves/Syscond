package view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import negocios.CarroNegocios;
import pojos.Carro;

import java.io.IOException;
import java.util.Optional;

public class CadastroCarroController{

    @FXML
    private TextField textFieldPlaca;
    @FXML
    private TextField textFieldModelo;
    @FXML
    private TextField textFieldCor;


    public void cadastrarCarro() throws IOException {

        String  placa = textFieldPlaca.getText(),
                modelo = textFieldModelo.getText(),
                cor = textFieldCor.getText();

        CarroNegocios carroNegocios = new CarroNegocios();

        Carro carro = new Carro(placa,modelo,cor);

        carroNegocios.cadastrar(carro);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setHeaderText("Carro adicionado com sucesso.");
        dialog.setContentText("Deseja adicionar outro carro?");
        ButtonType oneMore = new ButtonType("Quero adicionar +1 carro");
        ButtonType enoughCars = new ButtonType("Não, obrigado", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getButtonTypes().setAll(oneMore, enoughCars);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == oneMore){
            textFieldCor.setText("");
            textFieldPlaca.setText("");
            textFieldModelo.setText("");
            textFieldPlaca.requestFocus();
        }else{
            dialog.close();
            App.setRoot("menuCadastro");
        }
    }

    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
