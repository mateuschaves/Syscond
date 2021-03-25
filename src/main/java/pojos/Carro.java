/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/*
 *
 * @author Mattskywalker
 */

import javax.persistence.*;

@Entity
@Table(name = "carro",schema = "syscond")
public class Carro {
    @Id
    private String placa;
    @Column
    private String modelo;
    @Column
    private String cor;
    @ManyToOne(fetch = FetchType.LAZY)
    private Morador proprietario;

    public Carro() {
    }

    public Carro(String placa, String modelo, String cor, Morador proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.proprietario = proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


    @JoinColumn(name = "proprietarioCpf")
    public Morador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Morador proprietario) {
        this.proprietario = proprietario;
    }
}
