package view;

import com.mysql.cj.conf.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import negocios.ApartamentoNegocios;
import pojos.Apartamento;

import javax.persistence.Table;
import java.net.URL;
import java.text.CollationElementIterator;
import java.util.*;

public class CadastroMordadorController implements Initializable {

    @FXML
    private ComboBox comboBoxApartamentos;
    @FXML
    private TableView tableViewCarro;
    @FXML
    private TableView tableViewVisitante;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableViewCarro.setEditable(true);
        tableViewVisitante.setEditable(true);

        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

        List<Apartamento> listaApartamentos = apartamentoNegocios.listarApartamentos();

        Collection<String> numeros = new ArrayList<String>();

        int index = 0;
        for (Apartamento a:listaApartamentos) {
            numeros.add(listaApartamentos.get(index).getNumero());
            index++;
        }

        comboBoxApartamentos.getItems().addAll(numeros);
    }
}
