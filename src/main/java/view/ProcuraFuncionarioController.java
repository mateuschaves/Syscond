package view;

import dao.FuncionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import negocios.FornecedorNegocios;
import negocios.FuncionarioNegocios;
import pojos.Fornecedor;
import pojos.Funcionario;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class ProcuraFuncionarioController implements Initializable{

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
    private TableColumn cpfCollumn;
    @FXML
    private Button deletarFuncionario;

    private Funcionario funcionarioToDelete = new Funcionario();



    @FXML
    private void listarFuncionario() {

        FuncionarioNegocios funcionarioNegocios = new FuncionarioNegocios();

        try{

            List<Funcionario> list = funcionarioNegocios.listarFuncionarios();
            Collection<Campos> listCampos = new ArrayList<>();

            for(Funcionario a: list){

                listCampos.add(new Campos(a.getNome(),a.getFuncao(),a.getCpf()));
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(listCampos);

            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            funcaoColumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            cpfCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));

            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                deletarFuncionario.setDisable(false);
                String FuncionarioNomeToDelete = tableView.getSelectionModel().getSelectedItem().getCampo1();
                String FuncionarioFuncaoToDelete = tableView.getSelectionModel().getSelectedItem().getCampo2();
                String FuncionarioCpfToDelete = tableView.getSelectionModel().getSelectedItem().getCampo3();
                this.funcionarioToDelete.setNome(FuncionarioNomeToDelete);
                this.funcionarioToDelete.setFuncao(FuncionarioFuncaoToDelete);
                this.funcionarioToDelete.setCpf(FuncionarioCpfToDelete);

            });

        }catch (Exception e){

        }
    }


    @FXML
    private void procurarFuncionario(){
        FuncionarioNegocios funcionarioNegocios = new FuncionarioNegocios();
        String cpf = textFieldCpf.getText();

        if(cpf == ""){
            this.listarFuncionario();
            return;
        }

        try{

            Funcionario funcionario = new Funcionario(cpf);
            funcionario = funcionarioNegocios.pesquisar(funcionario);
            if (funcionario == null) {
                tableView.getItems().clear();
                return;
            }
            System.out.println("Nome do Funcionario: " + funcionario.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(funcionario.getNome(),funcionario.getFuncao(),funcionario.getCpf())
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            funcaoColumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            cpfCollumn.setCellValueFactory(new PropertyValueFactory("campo3"));

            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tableView.setOnMouseClicked((MouseEvent e) -> {
                    System.out.println(e.getSource().toString());
                    this.deletarFuncionario.setDisable(false);
                    String nome = tableView.getSelectionModel().getSelectedItem().getCampo1();
                    String funcao = tableView.getSelectionModel().getSelectedItem().getCampo2();
                    String cpf1 = tableView.getSelectionModel().getSelectedItem().getCampo3();

                    this.funcionarioToDelete.setNome(nome);
                    this.funcionarioToDelete.setFuncao(funcao);
                    this.funcionarioToDelete.setCpf(cpf1);
            });
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

    @FXML
    private void deletarFuncionario() {
        try {
            FuncionarioNegocios funcionarioNegocios = new FuncionarioNegocios();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            this.funcionarioToDelete = funcionarioDAO.procurar(textFieldCpf.getText());
            funcionarioNegocios.deletar(this.funcionarioToDelete);
            this.listarFuncionario();
            this.textFieldCpf.setText("");
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarFuncionario();
        mainPane.setOnMousePressed(event -> {
        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarFuncionario();
            }
        });

    }
}
