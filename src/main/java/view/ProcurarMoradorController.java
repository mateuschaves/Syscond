package view;

import com.jfoenix.controls.JFXButton;
import dao.MoradorDAO;
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
import negocios.MoradorNegocios;
import negocios.VisitanteNegocios;
import pojos.Carro;
import pojos.Funcionario;
import pojos.Morador;
import pojos.Visitante;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ProcurarMoradorController implements Initializable {


    @FXML
    private FlowPane mainPane;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private TableView<Campos> tableView;

    @FXML
    private TableColumn nomeCollumn;

    @FXML
    private TableColumn cpfCollumn;

    @FXML
    private TableColumn numeroApMoradorCollumn;

    @FXML
    private TableView tableCarro;

    @FXML
    private TableColumn placaCollumn;

    @FXML
    private TableColumn modeloCollumn;

    @FXML
    private TableColumn corCollumn;

    @FXML
    private TableColumn responsavelCarroCollumn;

    @FXML
    private TableView<Campos> visitanteTable;

    @FXML
    private TableColumn nomeVisitanteCollumn;

    @FXML
    private TableColumn cpfVisitanteCollumn;

    @FXML
    private TableColumn responsavelVisitanteCollumn;

    @FXML
    private TableColumn apartamentoVisitanteCollumn;

    @FXML
    private JFXButton deletarMorador;



    @FXML
    private void voltar(){
        try {
            App.setRoot("menuConsulta");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Morador moradorToDelete = new Morador();

    @FXML
    private void procurarMorador(){
        MoradorNegocios moradorNegocios = new MoradorNegocios();

        String cpf = textFieldCpf.getText();

        if(cpf == ""){
            this.listarTodos();
            return;
        }

        try{

            Morador morador = moradorNegocios.pesquisar(new Morador(cpf));
            Collection<Visitante> visitantes = morador.getVisitantesList();
            Collection<Carro> carroCollection = morador.getCarros();

            Collection<Campos> visitantesCampos = new ArrayList<>();
            Collection<Campos> carroCampos = new ArrayList<>();

            //carros;

            for(Carro a : carroCollection){
                String proprietario = morador.getNome();
                carroCampos.add(new Campos(
                        a.getPlaca(),
                        a.getModelo(),
                        a.getCor(),
                        proprietario)
                );

            }

            // visitantes
            for(Visitante a: visitantes){
                String numero = a.getCpfMoradorResponsavel().getApartamento().getNumero();
                visitantesCampos.add(
                        new Campos(
                                a.getNome(),
                                a.getCpf(),
                                a.getCpfMoradorResponsavel().getNome(),
                                numero)
                );

            }
            // exibir morador;
            final ObservableList<Campos> dataCampos = FXCollections.
                    observableArrayList(
                            new Campos(
                                    morador.getNome(),
                                    morador.getCpf(),
                                    morador.getApartamento().getNumero()
                            )
                    );

            final ObservableList<Campos> dataCarros = FXCollections.
                    observableArrayList(carroCampos);

            final ObservableList<Campos> dataVisitantes = FXCollections.
                    observableArrayList(visitantesCampos);


            //Colunas de morador:
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            cpfCollumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            numeroApMoradorCollumn.setCellValueFactory(new PropertyValueFactory("campo3"));

            //Colunas de Carros:
            placaCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            modeloCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            corCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));
            responsavelCarroCollumn.setCellValueFactory(new PropertyValueFactory<>("campo4"));

            //colunas de visitantes;
            cpfVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            nomeVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            responsavelVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));
            apartamentoVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo4"));

            //adicionando dados;
            visitanteTable.setItems(dataVisitantes);
            tableCarro.setItems(dataCarros);
            tableView.setItems(dataCampos);
            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                this.deletarMorador.setDisable(false);
                this.moradorToDelete.setCpf(cpf);
            });

            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(
                    new Image("/img/syscondLogo.png"));
            alert.setTitle("Erro");
            alert.setHeaderText("Morador não encontrado");
            alert.setContentText("O Morador: " + cpf + " não foi encontrado na base de dados " +
                    "caso queira casdastra-lo, basta ir em Morador, no menu de cadastros.");
            alert.show();
            textFieldCpf.setText("");
        }
    }

    @FXML
    private void listarTodos(){
        MoradorNegocios moradorNegocios = new MoradorNegocios();
        CarroNegocios carroNegocios = new CarroNegocios();
        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();

        textFieldCpf.setText("");

        try{
            List<Morador> moradores = moradorNegocios.listarMoradores();
            List<Visitante> visitantes = visitanteNegocios.listarVisitante();
            Collection<Carro> carroCollection = carroNegocios.listarCarros();

            Collection<Campos> camposMoradorCollection = new ArrayList<>();
            Collection<Campos> camposCarroCollection = new ArrayList<>();
            Collection<Campos> camposVisitanteCollection = new ArrayList<>();

            for(Carro a : carroCollection){
                String proprietario = a.getProprietario().getNome();
                camposCarroCollection.add(new Campos(
                        a.getPlaca(),
                        a.getModelo(),
                        a.getCor(),
                        proprietario)
                );

            }

            for(Morador a: moradores){
                String numero = a.getApartamento().getNumero().toString();
                camposMoradorCollection.
                        add(new Campos(a.getNome(),a.getCpf(),numero));
                System.out.println(" ---> " + numero);
            }

            for(Visitante a: visitantes){
                camposVisitanteCollection.
                        add(new Campos(
                                a.getNome(),
                                a.getCpf(),
                                a.getMorador().getNome(),
                                a.getCpfMoradorResponsavel().getApartamento().getNumero()
                        ));
            }


            final ObservableList<Campos> dataVisitantes = FXCollections.
                    observableArrayList(camposVisitanteCollection);

            final ObservableList<Campos> dataCampos = FXCollections.
                    observableArrayList(camposMoradorCollection);

            final ObservableList<Campos> dataCarros = FXCollections.observableArrayList(camposCarroCollection);
            System.out.println("----------->");

            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            cpfCollumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            numeroApMoradorCollumn.setCellValueFactory(new PropertyValueFactory("campo3"));

            //Colunas de Carros:
            placaCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            modeloCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            corCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));
            responsavelCarroCollumn.setCellValueFactory(new PropertyValueFactory<>("campo4"));

            //colunas de visitantes;
            cpfVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            nomeVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            responsavelVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));
            apartamentoVisitanteCollumn.setCellValueFactory(new PropertyValueFactory<>("campo4"));

            //Adding data to the table
            visitanteTable.setItems(dataVisitantes);
            tableCarro.setItems(dataCarros);
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void deletarMorador() {

        MoradorNegocios moradorNegocios = new MoradorNegocios();
        moradorNegocios.deletar(moradorToDelete);
        listarTodos();
        textFieldCpf.setText("");
        deletarMorador.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarTodos();
        mainPane.setOnMousePressed(event -> {

        });
        mainPane.setOnKeyPressed((keyEvent)->{
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarMorador();
            }
        });

        mainPane.setOnMousePressed((MouseEvent)->{
            deletarMorador.setDisable(true);
            mainPane.requestFocus();
        });

        tableView.setOnMouseClicked((MouseEvent e)->{

            deletarMorador.setDisable(false);
            moradorToDelete.setCpf(tableView.getSelectionModel().getSelectedItem().getCampo2());
            //System.out.println(moradorToDelete.getCpf());

        });

        deletarMorador.setDisable(true);

    }
}
