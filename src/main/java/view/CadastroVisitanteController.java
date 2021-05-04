package view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import negocios.VisitanteNegocios;
import pojos.Visitante;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroVisitanteController{

    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldNome;


    public void cadastrarVisitante(){

        String  cpf = textFieldCpf.getText(),
                nome = textFieldNome.getText();


        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();

        Visitante visitante = new Visitante(cpf,nome);

        visitanteNegocios.cadastrar(visitante);
    }

    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
