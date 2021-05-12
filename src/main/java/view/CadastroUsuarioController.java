package view;

import dao.UsuarioDao;
import dao.UsuarioDaoInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocios.UsuarioNegocios;
import pojos.Usuario;

import javax.swing.text.html.Option;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Responsavel por gerenciar e controlar a tela de cadastroUsuario.fxml;
 */
public class CadastroUsuarioController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private PasswordField confirmarSenha;

    /**
     * metodo usado para cadastrar um usuario, esse controlador recebe valores da tela de cadastroUsuario.fxml
     * como exemplo os textFields, instancia Usuario e UsuarioNegocios, coloca os valores dos textFields em
     * Usuario, depois verificia se a senha e confirmar senhas sao iguais e depois salva em
     * UsuarioNegocios, depois exibe uma mensagem, se o cadastro foi realizado com
     * sucesso.
     */
    @FXML
    public void cadastrar(){
        UsuarioNegocios usuarioNegocios = new UsuarioNegocios();

        String nome = textFieldNome.getText(),
                login = textFieldLogin.getText(),
                senha = passwordFieldSenha.getText(),
                confirmarSenha = this.confirmarSenha.getText();

        if(!senha.equals(confirmarSenha)){
            System.out.println("Erro ao logar;");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(
                    new Image("/img/syscondLogo.png"));
            alert.setTitle("Erro");
            alert.setHeaderText("As senhas não batem");
            alert.setContentText("As senhas devem combinar, por favor verifique seus dados.");
            alert.show();
            return;
        }

        Usuario usuario = new Usuario(nome,login,senha);

        System.out.println("Usuario: ");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Login: " + usuario.getLogin());
        System.out.println("Senha: " + usuario.getSenha());
        System.out.println("Senha c:" + confirmarSenha);
        UsuarioDaoInterface usuarioDao = new UsuarioDao();
        try {
            usuarioDao.adicionar(usuario);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(
                    new Image("/img/syscondLogo.png"));
            alert.setTitle("Sucesso!");
            alert.setHeaderText("Novo usuario de systema cadastrado.");
            alert.setContentText("Novo usuario: " + nome +
                    " cadastrado, use seu login e senha para entrar no Syscond");

            ButtonType logar = new ButtonType("Fazer Login");
            ButtonType cadastrarNovo = new ButtonType("Cadastrar Novo Usuario");

            alert.getButtonTypes().setAll(logar,cadastrarNovo);
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == logar){
                voltar();
            }

            return;

        }catch (Exception e){
            System.out.println("ERRO");
        }
    }

    /**
     * metodo usado para ir para uma tela anterior, que nesse caso eh a mesma da tela principal.
     */
    public void voltar(){
        try {
            App.setRoot("loginInicial");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * metodo usado para ir para uma tela anterior.
     */
    public void voltartela(){
        try {
            view.App.setRoot("menuCadastro");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
