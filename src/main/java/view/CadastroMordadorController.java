package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocios.ApartamentoNegocios;
import pojos.Apartamento;
import utils.Campos;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroMordadorController implements Initializable {

    @FXML
    private ComboBox comboBoxApartamentos;
    @FXML
    private TableView tableViewCarro;
    @FXML
    private TableView tableViewVisitante;
    @FXML
    private TableView tableViewDisponiveis;
    @FXML
    private TableColumn numeroCollumn;
    @FXML
    private TableColumn blocoCollumn;
    @FXML
    private TableColumn andarCollumn;



    public void listarDisponiveis(){

        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        try{
            List<Apartamento> list = apartamentoNegocios.listarApartamentos();
            Collection<Campos> camposCollection = new ArrayList<>();

            for(Apartamento a: list){

                try{
                    a.getMorador().size();
                    camposCollection.add(null);


                }catch (Exception e){
                    //e.printStackTrace();
                    camposCollection.add(new Campos(a.getNumero(),a.getAndar(),a.getBloco()));
                }
            }

            final ObservableList<Campos> dataCampos = FXCollections.observableArrayList(camposCollection);

            numeroCollumn.setCellValueFactory(new PropertyValueFactory<>("campo1"));
            andarCollumn.setCellValueFactory(new PropertyValueFactory<>("campo2"));
            blocoCollumn.setCellValueFactory(new PropertyValueFactory<>("campo3"));

            tableViewDisponiveis.setItems(dataCampos);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
