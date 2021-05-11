package view;

import com.jfoenix.controls.JFXButton;
import dao.MoradorDAO;
import dao.VisitanteDAO;
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
import negocios.VisitanteNegocios;
import pojos.Funcionario;
import pojos.Visitante;
import utils.Campos;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class ProcuraVisitanteController implements Initializable {

    @FXML
    private TextField textFieldCpf;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn moradorColumn;
    @FXML
    private TableColumn cpfColumn;
    @FXML
    private Button deletarVisitante;
    @FXML
    private JFXButton mostrarTodosButton;

    private Visitante visitanteToDelete = new Visitante();


    @FXML
    private void listarVisitante() {

        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();
        textFieldCpf.setText("");

        try{

            List<Visitante> list = visitanteNegocios.listarVisitante();
            Collection<Campos> listCampos = new ArrayList<>();

            for(Visitante a: list){

                listCampos.add(new Campos(a.getNome(),a.getCpf(),a.getCpfMoradorResponsavel().getNome()));
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(listCampos);

            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            cpfColumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            moradorColumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));

            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());
                deletarVisitante.setDisable(false);
                String VisitanteNomeToDelete = tableView.getSelectionModel().getSelectedItem().getCampo1();
                String VisitanteCpfToDelete = tableView.getSelectionModel().getSelectedItem().getCampo2();
                String VisitanteMoradorToDelete = tableView.getSelectionModel().getSelectedItem().getCampo3();
                this.visitanteToDelete.setNome(VisitanteNomeToDelete);
                this.visitanteToDelete.setCpf(VisitanteCpfToDelete);
                this.visitanteToDelete.getCpfMoradorResponsavel();

            });

        }catch (Exception e){

        }
    }


    @FXML
    private void procurarVisitante(){
        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();
        String cpf = textFieldCpf.getText();
        Visitante visitante;

        if(cpf == ""){
            this.listarVisitante();
            return;
        }

        try{

            visitante = new Visitante(cpf);
            visitante = visitanteNegocios.pesquisar(visitante);
            if(visitante == null)
                return;
            System.out.println("Visitante: " + visitante.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(
                            visitante.getNome(),
                            visitante.getCpf(),
                            visitante.getCpfMoradorResponsavel().getNome()
                    )
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            cpfColumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            moradorColumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            System.out.println(e.getMessage());
            //e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("O visitante buscado não existe! ou não está associado!");
            alert.setTitle("Visitante não encontrado");
            alert.setContentText("não foi possivel encontrar este visitante, Caso queira cadastrar este visitante, " +
                    "você pode ir no menu cadastros e adicionar!");
            alert.show();
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
    private void deletarVisitante() {
        try {
            VisitanteNegocios visitanteNegocios = new VisitanteNegocios();
            visitanteToDelete = visitanteNegocios.pesquisar(visitanteToDelete);

            visitanteNegocios.deletar(this.visitanteToDelete);
            this.listarVisitante();
            deletarVisitante.setDisable(true);
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarVisitante();
        mainPane.setOnMousePressed(event -> {
        });

        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarVisitante();
            }
        });

        mainPane.setOnMousePressed((MouseEvent)->{
            deletarVisitante.setDisable(true);
            mainPane.requestFocus();
        });

        tableView.setOnMouseClicked((MouseEvent e) -> {
            this.deletarVisitante.setDisable(false);
            String cpfVisitante = tableView.getSelectionModel().getSelectedItem().getCampo2();
            this.visitanteToDelete.setCpf(cpfVisitante);

        });

    }

}
