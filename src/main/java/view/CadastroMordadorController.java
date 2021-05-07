package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import negocios.ApartamentoNegocios;
import negocios.CarroNegocios;
import negocios.MoradorNegocios;
import negocios.VisitanteNegocios;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;
import pojos.Visitante;
import utils.Campos;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroMordadorController implements Initializable {

    @FXML
    private FlowPane mainPane;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private TextField textCpf;

    @FXML
    private TextField textNome;

    @FXML
    private TableView<Carro> tableViewCarro;
    @FXML
    private TableColumn placaCollumn;
    @FXML
    private TableColumn modeloCollumn;
    @FXML
    private TableColumn corCollumn;
    @FXML
    private TableView<Apartamento> tableViewDisponiveis;
    @FXML
    private TableColumn numeroCollumn;
    @FXML
    private TableColumn blocoCollumn;
    @FXML
    private TableColumn andarCollumn;
    @FXML
    private TextField textPlaca;

    @FXML
    private TextField textModelo;

    @FXML
    private TextField textCor;

    @FXML
    private TableView<Visitante> tableViewVisitante;

    @FXML
    private TableColumn<?, ?> nomeCollumn;

    @FXML
    private TableColumn<?, ?> cpfCollumn;

    @FXML
    private TextField textApartamento;


    public void listarDisponiveis(){

        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        try{
            List<Apartamento> list = apartamentoNegocios.listarApartamentos();
            Collection<Apartamento> camposCollection = new ArrayList<>();

            for(Apartamento a: list){

                try{
                    a.getMorador().size();

                }catch (Exception e){
                    //e.printStackTrace();
                    camposCollection.add(new Apartamento(a.getNumero(),a.getAndar(),a.getBloco()));
                }
            }

            final ObservableList<Apartamento> dataCampos = FXCollections.observableArrayList(camposCollection);

            numeroCollumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
            andarCollumn.setCellValueFactory(new PropertyValueFactory<>("andar"));
            blocoCollumn.setCellValueFactory(new PropertyValueFactory<>("bloco"));

            tableViewDisponiveis.setItems(dataCampos);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void listarTodosApartamentos(){

        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        try{
            Collection<Apartamento> list = apartamentoNegocios.listarApartamentos();
            final ObservableList<Apartamento> dataCampos = FXCollections.observableArrayList(list);

            numeroCollumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
            andarCollumn.setCellValueFactory(new PropertyValueFactory<>("andar"));
            blocoCollumn.setCellValueFactory(new PropertyValueFactory<>("bloco"));

            tableViewDisponiveis.setItems(dataCampos);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    final ObservableList<Carro> list = FXCollections.observableArrayList();

    public void adicionarVeiculo(){
        String placa = textPlaca.getText();
        String modelo = textModelo.getText();
        String cor = textCor.getText();

        list.add(new Carro(placa,modelo,cor));

        placaCollumn.setCellValueFactory(new PropertyValueFactory<>("placa"));
        modeloCollumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        corCollumn.setCellValueFactory(new PropertyValueFactory<>("cor"));

        tableViewCarro.setItems(list);

        textCor.setText("");
        textModelo.setText("");
        textPlaca.setText("");

    }

    final ObservableList<Visitante> listVisitante = FXCollections.observableArrayList();

    public void adicionarVisitante(){
        String cpf = textCpf.getText();
        String nome = textNome.getText();


        listVisitante.add(new Visitante(cpf,nome));

        nomeCollumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfCollumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        tableViewVisitante.setItems(listVisitante);

        textNome.setText("");
        textCpf.setText("");
    }

    public void pesquisarApartamento(){

        String numero = textApartamento.getText();

        Apartamento apartamento = new Apartamento(numero);
        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();
        apartamento = apartamentoNegocios.pesquisar(apartamento);
        try {

            final ObservableList<Apartamento> dataApartamento = FXCollections.observableArrayList(
                    new Apartamento(apartamento.getNumero(), apartamento.getAndar(), apartamento.getBloco())
            );

            numeroCollumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
            andarCollumn.setCellValueFactory(new PropertyValueFactory("andar"));
            blocoCollumn.setCellValueFactory(new PropertyValueFactory("bloco"));

            ObservableList<String> list = FXCollections.observableArrayList();
            tableViewDisponiveis.setItems(dataApartamento);
            tableViewDisponiveis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }catch (Exception e){
            this.listarDisponiveis();
        }
    }

    public void cadastrar(){

        String cpf = textFieldCpf.getText();
        String nome = textFieldNome.getText();

        List<Carro> carros = tableViewCarro.getItems();
        List<Visitante> visitantes = tableViewVisitante.getItems();
        Apartamento apartamento = tableViewDisponiveis.getItems().get(0);

        MoradorNegocios moradorNegocios = new MoradorNegocios();
        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();
        CarroNegocios carroNegocios = new CarroNegocios();

        Morador morador = new Morador(cpf,nome,apartamento,carros,visitantes);

        moradorNegocios.cadastrar(morador);

        if((carros.size() != 0)){
            for(Carro a: carros){
                a.setProprietario(morador);
                carroNegocios.cadastrar(a);
            }
        }
        if((visitantes.size() != 0)){
            for(Visitante a:visitantes){
                a.setCpfMoradorResponsavel(morador);
                visitanteNegocios.cadastrar(a);
            }
        }

        try{
            moradorNegocios.pesquisar(morador).getCpf();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Morador Cadastrado");
            alert.setHeaderText("Morador cadastrado com sucesso!");
            alert.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Morador não cadastrado");
            alert.setHeaderText("Morador não cadastrado!");
            alert.setContentText("ocorreu algum erro, verifique os dados e tente novamente!");
            alert.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewCarro.setEditable(true);
        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        List<Apartamento> listaApartamentos = apartamentoNegocios.listarApartamentos();

        Collection<String> numeros = new ArrayList<String>();

        int index = 0;
        for (Apartamento a:listaApartamentos) {
            numeros.add(listaApartamentos.get(index).getNumero());
            index++;
        }
        listarDisponiveis();


    }
}
