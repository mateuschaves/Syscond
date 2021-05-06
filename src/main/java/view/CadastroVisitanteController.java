package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import negocios.MoradorNegocios;
import negocios.VisitanteNegocios;
import pojos.Morador;
import pojos.Visitante;
import utils.Campos;

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
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn apartamentoColumn;

    @FXML
    private void procurar (){
        MoradorNegocios moradorNegocios = new MoradorNegocios();
        String cpf = responsavel.getText();


        try{

            Morador morador = new Morador(cpf);
            morador = moradorNegocios.pesquisar(morador);
            System.out.println("Morador: " + morador.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(morador.getNome(),morador.getApartamento().getNumero())
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            apartamentoColumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
