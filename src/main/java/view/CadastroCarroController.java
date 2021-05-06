package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import negocios.CarroNegocios;
import negocios.MoradorNegocios;
import pojos.Carro;
import pojos.Morador;
import utils.Campos;

import java.io.IOException;
import java.util.Optional;

public class CadastroCarroController{

    @FXML
    private TextField textFieldPlaca;
    @FXML
    private TextField textFieldModelo;
    @FXML
    private TextField textFieldCor;
    @FXML
    private TextField responsavel;
    @FXML
    private TableView<Campos> tableView = new TableView<Campos>();
    @FXML
    private TableColumn nomeCollumn;
    @FXML
    private TableColumn apartamentoCollumn;


    public void cadastrarCarro() throws IOException {

        String  placa = textFieldPlaca.getText(),
                modelo = textFieldModelo.getText(),
                cor = textFieldCor.getText();

        CarroNegocios carroNegocios = new CarroNegocios();

        Carro carro = new Carro(placa,modelo,cor);

        carroNegocios.cadastrar(carro);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setHeaderText("Carro adicionado com sucesso.");
        dialog.setContentText("Deseja adicionar outro carro?");
        ButtonType oneMore = new ButtonType("Quero adicionar +1 carro");
        ButtonType enoughCars = new ButtonType("Não, obrigado", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getButtonTypes().setAll(oneMore, enoughCars);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == oneMore){
            textFieldCor.setText("");
            textFieldPlaca.setText("");
            textFieldModelo.setText("");
            textFieldPlaca.requestFocus();
        }else{
            dialog.close();
            App.setRoot("menuCadastro");
        }
    }

    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            apartamentoCollumn.setCellValueFactory(new PropertyValueFactory("campo2"));
            //Adding data to the table
            ObservableList<String> list = FXCollections.observableArrayList();
            tableView.setItems(dataCampos);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("O Morador buscado não existe! ou não está associado!");
            alert.setTitle("Morador não encontrado");
            alert.setContentText("não foi possivel encontrar este Morador," +
                    " Caso queira cadastrar-lo, " +
                    "você pode ir no menu cadastros e adicionar!");
            alert.show();
        }


    }


}
