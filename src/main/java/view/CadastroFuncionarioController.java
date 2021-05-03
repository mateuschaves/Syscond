package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import negocios.FuncionarioNegocios;
import pojos.Funcionario;

import java.io.IOException;

public class CadastroFuncionarioController {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldFuncao;


    public void cadastrarFuncionario(){

        FuncionarioNegocios funcionarioNegocios = new FuncionarioNegocios();

        String  nome = textFieldNome.getText(),
                cpf = textFieldCpf.getText(),
                funcao = textFieldFuncao.getText();

        Funcionario funcionario = new Funcionario(nome,cpf,funcao);

        funcionarioNegocios.cadastrar(funcionario);
    }

    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

