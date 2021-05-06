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
import negocios.CarroNegocios;
import pojos.Carro;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
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
    private void procurarCarro(){
        CarroNegocios carroNegocios = new CarroNegocios();
        String placa = textFieldPlaca.getText();


        try{

            Carro carro = new Carro(placa);
            carro = carroNegocios.pesquisar(carro);
            System.out.println(" Modelo do Carro: " + carro.getModelo());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(carro.getModelo(),carro.getCor(),carro.getProprietario().getNome())
            );
            //Creating columns
            modeloCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            corColumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            proprietarioColumn.setCellValueFactory(new PropertyValueFactory("campo3"));
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
                procurarCarro();
            }
        });

    }

}
