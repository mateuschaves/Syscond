package view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import inutils.MaskFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroFornecedorController implements Initializable {

    @FXML
    private TextField textFieldCNPJ;


    MaskFormatter maskFormatter = new MaskFormatter(textFieldCNPJ);

    public void teste(){
        maskFormatter.setMask(MaskFormatter.CPF);
        maskFormatter.showMask();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        maskFormatter.setMask(MaskFormatter.CPF);
        maskFormatter.showMask();
    }
}
