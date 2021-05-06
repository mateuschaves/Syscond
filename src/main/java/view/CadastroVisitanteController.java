package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import negocios.MoradorNegocios;
import negocios.VisitanteNegocios;
import pojos.Morador;
import pojos.Visitante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroVisitanteController implements Initializable{

    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField responsavel;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Objeto> tableView;
    @FXML
    private TableColumn<Morador,String> nomeCollumn;
    @FXML
    private TableColumn<Morador,String> apartamentoColumn;

    private class Objeto{//Objeto criado só pra receber os dados de apartamento;
        // que vão ser exibidos na tabela;
        String nome;
        String numeroAp;

        public Objeto(String nome,String numeroAP) {
            this.nome = nome;
            this.numeroAp = numeroAP;
        }
    }

    private ObservableList<Objeto> listaMorador(){
        return FXCollections.observableArrayList(new Objeto("hehe","fa"));
    }

    @FXML
    private void procurar (){
        MoradorNegocios moradorNegocios = new MoradorNegocios();
        String cpfResponsavel = responsavel.getText();
        Morador morador;

        try{
            morador = moradorNegocios.pesquisar(new Morador(cpfResponsavel));
            System.out.println("Encontrado :" + morador.getNome());

            tableView.setItems(listaMorador());
        }catch (Exception e){

            e.printStackTrace();
        }


    }

    @FXML
    private void cadastrarVisitante(){

        String  cpf = textFieldCpf.getText(),
                nome = textFieldNome.getText();


        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();

        Visitante visitante = new Visitante(cpf,nome);

        visitanteNegocios.cadastrar(visitante);
    }

    @FXML
    private void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainPane.setOnMousePressed(event -> {

        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurar();
            }
        });

    }

}
