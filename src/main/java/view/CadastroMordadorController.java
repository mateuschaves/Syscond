package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import negocios.ApartamentoNegocios;
import pojos.Apartamento;

import java.net.URL;
import java.text.CollationElementIterator;
import java.util.*;

public class CadastroMordadorController implements Initializable {

    @FXML
    private ComboBox comboBoxApartamentos;


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

        comboBoxApartamentos.getItems().addAll(numeros);
    }
}
