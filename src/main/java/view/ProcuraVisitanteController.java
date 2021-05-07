package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import negocios.VisitanteNegocios;
import pojos.Visitante;
import utils.Campos;
import java.io.IOException;
import java.net.URL;
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

    private Visitante visitanteToDelete = new Visitante();

    @FXML
    private void procurarVisitante(){
        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();
        String cpf = textFieldCpf.getText();
        Visitante visitante;

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

            tableView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println(e.getSource().toString());

                this.deletarVisitante.setDisable(false);

                String nome = tableView.getSelectionModel().getSelectedItem().getCampo1();
                String cpfVisitante = tableView.getSelectionModel().getSelectedItem().getCampo2();

                this.visitanteToDelete.setNome(nome);
                this.visitanteToDelete.setCpf(cpfVisitante);
            });

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainPane.setOnMousePressed(event -> {

        });
        mainPane.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                procurarVisitante();
            }
        });

    }


    @FXML
    private void deletarVisitante() {
        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();
        visitanteNegocios.deletar(this.visitanteToDelete);
        this.procurarVisitante();
    }

}
