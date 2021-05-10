package view;

import exceptions.apartamento.ApartamentoJaExistente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import negocios.ApartamentoNegocios;
import pojos.Apartamento;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CadastroApartamentoController implements Initializable {

    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldAndar;
    @FXML
    private TextField textFieldBloco;
    @FXML
    private FlowPane mainPane;


    public void cadastrarApartamento(){
        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        String numero = textFieldNumero.getText(),
                andar = textFieldAndar.getText(),
                bloco = textFieldBloco.getText();

        Apartamento apartamento = new Apartamento(numero,andar,bloco);

        try {
            apartamentoNegocios.cadastrar(apartamento);

            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            ((Stage)dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/syscondLogo.png"));
            dialog.setHeaderText("Apartamento adicionado com sucesso.");
            dialog.setContentText("Deseja adicionar outro Apartamento?");
            ButtonType oneMore = new ButtonType("Quero adicionar +1 Apartamento");
            ButtonType enoughCars = new ButtonType("Não, obrigado", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getButtonTypes().setAll(oneMore, enoughCars);
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == oneMore){
                textFieldNumero.setText("");
            }else{
                voltar();
                dialog.close();

            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(
                    new Image("/img/syscondLogo.png"));
            alert.setTitle("Apartamento não cadastrado");
            alert.setHeaderText("Apartamento não cadastrado!");
            alert.setContentText("ocorreu algum erro, verifique os dados e tente novamente!");
            alert.show();
        }


    }

    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainPane.setOnKeyPressed((keyEvent)->{
            if(keyEvent.getCode() == KeyCode.ENTER){
                cadastrarApartamento();
            }
        });

    }
}
