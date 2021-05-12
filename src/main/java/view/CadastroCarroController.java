package view;


import dao.MoradorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import negocios.CarroNegocios;
import negocios.MoradorNegocios;
import pojos.Carro;
import pojos.Morador;
import utils.Campos;

import java.io.IOException;
import java.util.Optional;

/**
 * Responsavel por gerenciar e controlar a tela de cadastroCarro.fxml;
 */
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


    /**
     * metodo usado para cadastrar um carro, esse controlador recebe valores da tela de cadastroCarro.fxml
     * como exemplo os textFields, instancia Carro, CarroNegocios, Morador e MoradorDAO, pois carro depende de
     * morador para existir, depois coloca os valores dos textFields em Carro e depois salva em
     * CarroNegocios, além de procurar um morador, para associar com um carro
     * depois exibe uma mensagem, se o cadastro foi realizado com sucesso.
     */
    public void cadastrarCarro() throws IOException {

        MoradorDAO moradorDAO  = new MoradorDAO();
        Morador donoDoCarro = moradorDAO.procurar(responsavel.getText());

        String  placa = textFieldPlaca.getText(),
                modelo = textFieldModelo.getText(),
                cor = textFieldCor.getText();

        CarroNegocios carroNegocios = new CarroNegocios();

        Carro carro = new Carro(placa,modelo,cor, donoDoCarro);

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

    /**
     * metodo usado para ir para uma tela anterior.
     */
    public void voltar(){
        try {
            App.setRoot("menuCadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * nesse metodo a ideia eh procurar por um morador, saber se ele existe, para depois associar com carros
     * no metodo de cadastrarCarro.
     */
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
