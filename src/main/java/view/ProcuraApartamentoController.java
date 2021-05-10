package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import negocios.ApartamentoNegocios;
import negocios.CarroNegocios;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class ProcuraApartamentoController implements Initializable {

    @FXML
    private TextField textFieldNumero;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn blocoCollumn;
    @FXML
    private TableColumn andarCollumn;
    @FXML
    private TableColumn numeroCollumn;
    @FXML
    private Button btApagar;

    private Apartamento apartamentoDelete = new Apartamento();

    @FXML
    private void listarApartamentos() {

        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        try{

            List<Apartamento> list = apartamentoNegocios.listarApartamentos();
            Collection<Campos> listCampos = new ArrayList<>();

            for(Apartamento a: list){

                listCampos.add(new Campos(a.getNumero(),a.getAndar(),a.getBloco()));
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(listCampos);

            numeroCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            andarCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            blocoCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));

            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){

        }
    }

    @FXML
    private void procurarApartamento(){
        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();
        String numero = textFieldNumero.getText();


        try{

            Apartamento apartamento = new Apartamento(numero);
            apartamento = apartamentoNegocios.pesquisar(apartamento);
            System.out.println(" Número do Apartamento: " + apartamento.getNumero());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(apartamento.getNumero(),apartamento.getAndar(),apartamento.getBloco())
            );
            //Creating columns
            numeroCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            andarCollumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            blocoCollumn.setCellValueFactory(new PropertyValueFactory("campo3"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void deletarApartamento(){
        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        apartamentoDelete = apartamentoNegocios.pesquisar(apartamentoDelete);

        System.out.println("APARTAMENTO: " + apartamentoDelete.getNumero());

        apartamentoNegocios.deletar(apartamentoDelete);

        this.listarApartamentos();
    }

    @FXML
    private void voltar() {
        try {
            App.setRoot("menuConsulta");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.listarApartamentos();
        mainPane.setOnMousePressed(event -> {

        });

        btApagar.setDisable(true);

        tableView.setOnMouseClicked((MouseEvent e) -> {
            btApagar.setDisable(false);
            String numero = tableView.getSelectionModel().getSelectedItem().getCampo1();
            apartamentoDelete = new Apartamento(numero);
        });
    }
}
