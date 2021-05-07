package view;

import dao.MoradorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import negocios.FuncionarioNegocios;
import negocios.MoradorNegocios;
import pojos.Carro;
import pojos.Funcionario;
import pojos.Morador;
import pojos.Visitante;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ProcurarMoradorController implements Initializable {

    @FXML
    private TextField textFieldCpf;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn apartamentoColumn;
    @FXML
    private TableColumn carroColumn;
    @FXML
    private TableColumn visitanteColumn;

    @FXML
    private Button deletarMorador;

    private Morador moradorToDelete = new Morador();

    @FXML
    private void voltar(){
        try {
            App.setRoot("menuConsulta");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void procurarMorador(){
        MoradorNegocios moradorNegocios = new MoradorNegocios();
        String cpf = textFieldCpf.getText();

        try{
            Morador morador = new Morador(cpf);
            morador = moradorNegocios.pesquisar(morador);
            if (morador == null) {
                tableView.getItems().clear();
                return;
            }
            System.out.println("Nome do Morador: " + morador.getNome());

            String carros = "";
            String visitantes = "";

            for(Iterator<Carro> iterator = morador.getCarros().iterator(); iterator.hasNext();) {
                carros = carros + iterator.next().getModelo() + ", ";
            }

            for(Iterator<Visitante> iterator = morador.getVisitantesList().iterator(); iterator.hasNext();) {
                visitantes = visitantes + iterator.next().getNome() + ", ";
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(morador.getNome(), morador.getApartamento().getNumero(), carros, visitantes)
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            apartamentoColumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            carroColumn.setCellValueFactory(new PropertyValueFactory("campo3"));
            visitanteColumn.setCellValueFactory(new PropertyValueFactory("campo4"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                this.deletarMorador.setDisable(false);
                this.moradorToDelete.setCpf(cpf);
            });

            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void deletarMorador() {
        MoradorDAO moradorDAO = new MoradorDAO();
        this.moradorToDelete = moradorDAO.procurar(this.moradorToDelete.getCpf());
        moradorDAO.remover(this.moradorToDelete);
        this.tableView.getItems().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainPane.setOnMousePressed(event -> {

        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarMorador();
            }
        });

    }
}
