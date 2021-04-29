package view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import inutils.MaskFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroFornecedorController extends MenuController{

    @FXML
    private TextField textFieldCNPJ;


    MaskFormatter maskFormatter = new MaskFormatter(textFieldCNPJ);

    public void cadastrarFornecedor(){

    }


}
