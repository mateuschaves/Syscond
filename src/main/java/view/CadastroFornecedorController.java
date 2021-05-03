package view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import inutils.MaskFormatter;
import negocios.FornecedorNegocios;
import pojos.Fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroFornecedorController{

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCNPJ;
    @FXML
    private TextField textFieldTelefone;



    public void cadastrarFornecedor(){

        String nome = textFieldNome.getText(),
                cnpj = textFieldCNPJ.getText(),
                telefone = textFieldTelefone.getText();

        FornecedorNegocios fornecedorNegocios = new FornecedorNegocios();

        Fornecedor fornecedor = new Fornecedor(cnpj,nome,telefone);

        fornecedorNegocios.cadastrar(fornecedor);
    }

    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
