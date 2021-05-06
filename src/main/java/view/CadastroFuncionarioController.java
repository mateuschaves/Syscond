package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import negocios.FuncionarioNegocios;
import pojos.Funcionario;

import java.io.IOException;
import java.util.Optional;

public class CadastroFuncionarioController {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldFuncao;


    public void cadastrarFuncionario() throws IOException {

        FuncionarioNegocios funcionarioNegocios = new FuncionarioNegocios();

        String  nome = textFieldNome.getText(),
                cpf = textFieldCpf.getText(),
                funcao = textFieldFuncao.getText();

        Funcionario funcionario = new Funcionario(nome,cpf,funcao);

        funcionarioNegocios.cadastrar(funcionario);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage)dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/syscondLogo.png"));
        dialog.setHeaderText("Funcionário adicionado com sucesso.");
        dialog.setContentText("Deseja adicionar outro funcionario?");
        ButtonType oneMore = new ButtonType("Quero adicionar +1 funcionário");
        ButtonType enoughCars = new ButtonType("Não, obrigado", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getButtonTypes().setAll(oneMore, enoughCars);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == oneMore){
            textFieldCpf.setText("");
            textFieldNome.setText("");
            textFieldFuncao.setText("");
            textFieldCpf.requestFocus();
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

