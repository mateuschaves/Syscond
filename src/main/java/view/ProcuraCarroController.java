package view;

import dao.CarroDAO;
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
import negocios.CarroNegocios;
import negocios.FuncionarioNegocios;
import pojos.Carro;
import pojos.Funcionario;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Responsavel por gerenciar e controlar a tela de procurarCarro.fxml;
 */
public class ProcuraCarroController implements Initializable {

    @FXML
    private TextField textFieldPlaca;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn modeloCollumn;
    @FXML
    private TableColumn corColumn;
    @FXML
    private TableColumn proprietarioColumn;
    @FXML
    private TableColumn placaCollumn;
    @FXML
    private Button deletarCarro;

    private Carro carroToDelete = new Carro();


    /**
     * metodo que pega uma lista de carros passa para a classe Campos e essa classe vai exibir os dados na
     * tableview da tela de consulta de carros.
     */
    @FXML
    private void listarCarro() {

        CarroNegocios carroNegocios = new CarroNegocios();
        textFieldPlaca.setText("");
        try{

            List<Carro> list = carroNegocios.listarCarros();
            Collection<Campos> listCampos = new ArrayList<>();

            for(Carro a: list){

                listCampos.add(new Campos(a.getModelo(),a.getCor(),a.getProprietario().getNome(),a.getPlaca()));
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(listCampos);

            modeloCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            corColumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            proprietarioColumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));
            placaCollumn.setCellValueFactory(new PropertyValueFactory<>("campo4"));
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                deletarCarro.setDisable(false);
                String CarroModeloToDelete = tableView.getSelectionModel().getSelectedItem().getCampo1();
                String CarroCorToDelete = tableView.getSelectionModel().getSelectedItem().getCampo2();
                String CarroPlacaToDelete = tableView.getSelectionModel().getSelectedItem().getCampo4();
                this.carroToDelete.setModelo(CarroModeloToDelete);
                this.carroToDelete.setCor(CarroCorToDelete);
                this.carroToDelete.getProprietario();
                this.carroToDelete.setPlaca(CarroPlacaToDelete);

            });

        }catch (Exception e){

        }
    }


    /**
     * metodo que pega um textField e passa para um carro e depois verifica se esse carro existe e passa
     * para a classe Campos para exibir os dados na tableview da consulta de carros.
     */
    @FXML
    private void procurarCarro(){
        CarroNegocios carroNegocios = new CarroNegocios();
        String placa = textFieldPlaca.getText();

        if(placa == ""){
            this.listarCarro();
            return;
        }

        try{

            Carro carro = new Carro(placa);
            carro = carroNegocios.pesquisar(carro);

            System.out.println(" Modelo do Carro: " + carro.getModelo());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(carro.getModelo(),carro.getCor(),carro.getProprietario().getNome(),carro.getPlaca())
            );
            //Creating columns
            modeloCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            corColumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            proprietarioColumn.setCellValueFactory(new PropertyValueFactory("campo3"));
            placaCollumn.setCellValueFactory(new PropertyValueFactory("campo4"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(
                    new Image("/img/syscondLogo.png"));
            alert.setTitle("Erro");
            alert.setHeaderText("Veículo não encontrado");
            alert.setContentText("O Veiculo: " + placa + " não foi encontrado na base de dados " +
                    "caso queira casdastra-lo, basta ir em Veiculos, no menu de cadastros.");
            alert.show();
            textFieldPlaca.setText("");
            //e.printStackTrace();
        }
    }

    /**
     * metodo que volta para a tela anterior.
     */
    @FXML
    private void voltar(){
        try {
            App.setRoot("menuConsulta");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo que deleta um carro atraves de um objeto.
     */
    @FXML
    private void deletarCarro() {
        CarroNegocios carroNegocios = new CarroNegocios();


        carroNegocios.deletar(this.carroToDelete);
        this.listarCarro();
        this.textFieldPlaca.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarCarro();
        mainPane.setOnMousePressed(event -> {
            deletarCarro.setDisable(true);
            mainPane.requestFocus();
        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarCarro();
            }
        });

        tableView.setOnMouseClicked((MouseEvent e) -> {

            this.deletarCarro.setDisable(false);

            String placa = tableView.getSelectionModel().getSelectedItem().getCampo4();

            this.carroToDelete.setPlaca(placa);
        });

    }

}
