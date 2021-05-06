package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import negocios.MoradorNegocios;
import negocios.VisitanteNegocios;
import pojos.Morador;
import pojos.Visitante;
import utils.Campos;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CadastroVisitanteController implements Initializable{

    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField responsavel;
    @FXML
    private FlowPane mainPane;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn apartamentoColumn;

    @FXML
    private void procurar (){
        MoradorNegocios moradorNegocios = new MoradorNegocios();
        String cpf = responsavel.getText();


        try{

            Morador morador = new Morador(cpf);
            morador = moradorNegocios.pesquisar(morador);
            System.out.println("Morador: " + morador.getNome());
            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(
                    new Campos(morador.getNome(),morador.getApartamento().getNumero())
            );
            //Creating columns
            nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            apartamentoColumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    private void cadastrarVisitante() throws IOException {

        String  cpf = textFieldCpf.getText(),
                nome = textFieldNome.getText();


        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();

        Visitante visitante = new Visitante(cpf,nome);

        visitanteNegocios.cadastrar(visitante);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage)dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/syscondLogo.png"));
        dialog.setHeaderText("Visitante adicionado com sucesso.");
        dialog.setContentText("Deseja adicionar outro visitante?");
        ButtonType oneMore = new ButtonType("Quero adicionar +1 visitante");
        ButtonType enoughCars = new ButtonType("Não, obrigado", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getButtonTypes().setAll(oneMore, enoughCars);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == oneMore){
            textFieldCpf.setText("");
            textFieldNome.setText("");
            responsavel.setText("");
            textFieldCpf.requestFocus();
        }else{
            dialog.close();
            App.setRoot("menuCadastro");
        }
    }

    @FXML
    private void voltar(){
        try {
            App.setRoot("menuCadastro");
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
                procurar();
            }
        });

    }

}
