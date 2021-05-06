package view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import negocios.FornecedorNegocios;
import pojos.Fornecedor;

import java.io.IOException;
import java.util.Optional;

public class CadastroFornecedorController{

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCNPJ;
    @FXML
    private TextField textFieldTelefone;



    public void cadastrarFornecedor() throws IOException {

        String nome = textFieldNome.getText(),
                cnpj = textFieldCNPJ.getText(),
                telefone = textFieldTelefone.getText();

        FornecedorNegocios fornecedorNegocios = new FornecedorNegocios();

        Fornecedor fornecedor = new Fornecedor(cnpj,nome,telefone);

        fornecedorNegocios.cadastrar(fornecedor);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage)dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/syscondLogo.png"));
        dialog.setHeaderText("Fornecedor adicionado com sucesso.");
        dialog.setContentText("Deseja adicionar outro fornecedor?");
        ButtonType oneMore = new ButtonType("Quero adicionar +1 fornecedor");
        ButtonType enoughCars = new ButtonType("Não, obrigado", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getButtonTypes().setAll(oneMore, enoughCars);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == oneMore){
            textFieldCNPJ.setText("");
            textFieldNome.setText("");
            textFieldTelefone.setText("");
            textFieldCNPJ.requestFocus();
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
