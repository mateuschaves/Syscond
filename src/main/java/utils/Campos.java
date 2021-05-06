package utils;
import javafx.beans.property.SimpleStringProperty;

public class Campos {

    SimpleStringProperty campo1;
    SimpleStringProperty campo2;
    SimpleStringProperty campo3;
    SimpleStringProperty campo4;

    public Campos(String campo1, String campo2,
                  String campo3, String campo4) {
        this.campo1 = new SimpleStringProperty(campo1);
        this.campo2 = new SimpleStringProperty(campo2);
        this.campo3 = new SimpleStringProperty(campo3);
        this.campo4 = new SimpleStringProperty(campo4);
    }

    public Campos(String campo1, String campo2,
                  String campo3) {
        this.campo1 = new SimpleStringProperty(campo1);
        this.campo2 = new SimpleStringProperty(campo2);
        this.campo3 = new SimpleStringProperty(campo3);
    }

    public Campos(String campo1, String campo2) {
        this.campo1 = new SimpleStringProperty(campo1);
        this.campo2 = new SimpleStringProperty(campo2);
    }

    public String getCampo1() {
        return campo1.get();
    }

    public SimpleStringProperty campo1Property() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1.set(campo1);
    }

    public String getCampo2() {
        return campo2.get();
    }

    public SimpleStringProperty campo2Property() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2.set(campo2);
    }

    public String getCampo3() {
        return campo3.get();
    }

    public SimpleStringProperty campo3Property() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3.set(campo3);
    }

    public String getCampo4() {
        return campo4.get();
    }

    public SimpleStringProperty campo4Property() {
        return campo4;
    }

    public void setCampo4(String campo4) {
        this.campo4.set(campo4);
    }
}