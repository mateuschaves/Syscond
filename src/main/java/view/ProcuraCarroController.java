package view;

import dao.CarroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
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


    @FXML
    private void listarCarro() {

        CarroNegocios carroNegocios = new CarroNegocios();

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
            if(carro == null) {
                return;
            }
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
            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                this.deletarCarro.setDisable(false);
                String modelo = tableView.getSelectionModel().getSelectedItem().getCampo1();
                String cor =    tableView.getSelectionModel().getSelectedItem().getCampo2();
                String placa1 = tableView.getSelectionModel().getSelectedItem().getCampo4();

                this.carroToDelete.setModelo(modelo);
                this.carroToDelete.setCor(cor);
                this.carroToDelete.getProprietario();
                this.carroToDelete.setPlaca(placa1);
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
    private void deletarCarro() {
        CarroNegocios carroNegocios = new CarroNegocios();
        carroNegocios.deletar(this.carroToDelete);
        this.listarCarro();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarCarro();
        mainPane.setOnMousePressed(event -> {
        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarCarro();
            }
        });

    }

}
