package utils;
import javafx.beans.property.SimpleStringProperty;

/**
 * Essa classe serve para conseguir buscar os dados de outras classes, por exemplo, morador tem uma lista de visitantes
 * atraves de morador conseguimos acessar os dados de visitantes, pois pegamos essa lista de visitante e a partir
 * dessa lista acessamos os dados de visitantes, de forma parecida acontece nessa classe. Alem disso, essa classe contem
 * atributos e metodos especiais (gets,sets e construtores), que serao utilizados para exibir dados nas classes de Procura
 * Controller.
 *
 */
public class Campos {

    SimpleStringProperty campo1;
    SimpleStringProperty campo2;
    SimpleStringProperty campo3;
    SimpleStringProperty campo4;

    /**
     *
     * @param campo1 ,
     * @param campo2 ,
     * @param campo3 e
     * @param campo4 sao os parametros utilizados nesse construtor da classe Campos.
     */
    public Campos(String campo1, String campo2,
                  String campo3, String campo4) {
        this.campo1 = new SimpleStringProperty(campo1);
        this.campo2 = new SimpleStringProperty(campo2);
        this.campo3 = new SimpleStringProperty(campo3);
        this.campo4 = new SimpleStringProperty(campo4);
    }

    /**
     *
     * @param campo1 ,
     * @param campo2 e
     * @param campo3 sao os parametros usados nesse outro construtor da classe Campos.
     */
    public Campos(String campo1, String campo2,
                  String campo3) {
        this.campo1 = new SimpleStringProperty(campo1);
        this.campo2 = new SimpleStringProperty(campo2);
        this.campo3 = new SimpleStringProperty(campo3);
    }

    /**
     *
     * @param campo1 e
     * @param campo2 sao os parametros usados nesse outro construtor da classe Campos.
     */
    public Campos(String campo1, String campo2) {
        this.campo1 = new SimpleStringProperty(campo1);
        this.campo2 = new SimpleStringProperty(campo2);
    }

    /**
     *
     * @return retorna o primeiro campo.
     */
    public String getCampo1() {
        return campo1.get();
    }

    /**
     *
     * @return retorna o campo1.
     */
    public SimpleStringProperty campo1Property() {
        return campo1;
    }

    /**
     *
     * @param campo1 eh o parametro atribuido para o campo1.
     */
    public void setCampo1(String campo1) {
        this.campo1.set(campo1);
    }

    /**
     *
     * @return retorna o segundo campo.
     */
    public String getCampo2() {
        return campo2.get();
    }

    /**
     *
     * @return retorna o campo2.
     */
    public SimpleStringProperty campo2Property() {
        return campo2;
    }

    /**
     *
     * @param campo2 eh o parametro atribuido para o campo2.
     */
    public void setCampo2(String campo2) {
        this.campo2.set(campo2);
    }

    /**
     *
     * @return retorna o terceiro campo.
     */
    public String getCampo3() {
        return campo3.get();
    }

    /**
     *
     * @return retorna o campo3.
     */
    public SimpleStringProperty campo3Property() {
        return campo3;
    }

    /**
     *
     * @param campo3 eh o parametro atribuido para o campo3.
     */
    public void setCampo3(String campo3) {
        this.campo3.set(campo3);
    }

    /**
     *
     * @return retorna o quarto campo.
     */
    public String getCampo4() {
        return campo4.get();
    }

    /**
     *
     * @return retorna o campo4.
     */
    public SimpleStringProperty campo4Property() {
        return campo4;
    }

    /**
     *
     * @param campo4 eh o paremetro atribuido para o campo4.
     */
    public void setCampo4(String campo4) {
        this.campo4.set(campo4);
    }
}