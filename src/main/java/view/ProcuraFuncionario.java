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
import negocios.FuncionarioNegocios;
import pojos.Funcionario;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProcuraFuncionario implements Initializable{

    @FXML
    private TextField textFieldCpf;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn funcaoColumn;

    @FXML
    private void procurarFuncionario(){
        FuncionarioNegocios funcionarioNegocios = new FuncionarioNegocios();
        String cpf = textFieldCpf.getText();


        try{

            Funcionario funcionario = new Funcionario(cpf);
            funcionario = funcionarioNegocios.pesquisar(funcionario);
            System.out.println("Nome do Funcionario: " + funcionario.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(funcionario.getNome(),funcionario.getFuncao())
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            funcaoColumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void voltar(){
        try {
            App.setRoot("menuConsulta");
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
                procurarFuncionario();
            }
        });

    }
}
