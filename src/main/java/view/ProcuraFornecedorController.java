package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import negocios.FornecedorNegocios;
import negocios.UsuarioNegocios;
import pojos.Fornecedor;
import pojos.Usuario;
import utils.Campos;

import javax.persistence.Table;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class ProcuraFornecedorController implements Initializable {

    @FXML
    private TextField textFieldCnpj;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn telefoneCollumn;
    @FXML
    private TableColumn cnpjCollumn;
    @FXML
    private Button deletarFornecedor;

    private Fornecedor fornecedorToDelete = new Fornecedor();


    @FXML
    private void listarFornecedor() {

        FornecedorNegocios fornecedorNegocios = new FornecedorNegocios();
        textFieldCnpj.setText("");

        try{

            List<Fornecedor> list = fornecedorNegocios.listarFornecedores();
            Collection<Campos> listCampos = new ArrayList<>();

            for(Fornecedor a: list){

                listCampos.add(new Campos(a.getNome(),a.getTelefone(),a.getCnpj()));
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(listCampos);

            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            telefoneCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            cnpjCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));

            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



        }catch (Exception e){

        }
    }

    @FXML
    private void procurarFornecedor(){
        FornecedorNegocios fornecedorNegocios = new FornecedorNegocios();
        String cnpj = textFieldCnpj.getText();


        if(cnpj == ""){
            this.listarFornecedor();
            return;
        }

        try{

            Fornecedor fornecedor = new Fornecedor(cnpj);
            fornecedor = fornecedorNegocios.pesquisar(fornecedor);
            System.out.println("Nome do Fornecedor: " + fornecedor.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(fornecedor.getNome(),fornecedor.getTelefone(),fornecedor.getCnpj())
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            telefoneCollumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            cnpjCollumn.setCellValueFactory(new PropertyValueFactory("campo3"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(
                    new Image("/img/syscondLogo.png"));
            alert.setTitle("Erro");
            alert.setHeaderText("Fornecedor não encontrado");
            alert.setContentText("O Fornecedor: " + cnpj + " não foi encontrado na base de dados " +
                    "caso queira casdastra-lo, basta ir em Fornecedor, no menu de cadastros.");
            alert.show();
            textFieldCnpj.setText("");
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
    private void deletarFornecedor() {
        FornecedorNegocios fornecedorNegocios = new FornecedorNegocios();
        fornecedorNegocios.deletar(fornecedorToDelete);
        listarFornecedor();
        this.textFieldCnpj.setText("");
        this.deletarFornecedor.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarFornecedor();
        mainPane.setOnMousePressed(event -> {
        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarFornecedor();
            }
        });

        mainPane.setOnMousePressed((MouseEvent)->{
            deletarFornecedor.setDisable(true);
            mainPane.requestFocus();
        });

        tableView.setOnMouseClicked((MouseEvent e) -> {

            deletarFornecedor.setDisable(false);
            String FornecedorCnpjToDelete = tableView.getSelectionModel().getSelectedItem().getCampo3();
            this.fornecedorToDelete.setCnpj(FornecedorCnpjToDelete);

        });

    }


}
