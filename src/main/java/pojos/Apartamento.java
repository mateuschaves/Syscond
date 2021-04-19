/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus Martins
 * @author Breno Araujo
 * @author Eduardo Marinho
 * @author Mateus Henrique
 *
 * pojo de apartamaneto com seus atributos mapeados, entidades, colounas, chave primaria
 * com relacionamento com morador, um apartamento para varios moradores (1 para n).
 * Alem de seus construtores gets e sets.
 */
@Entity
@Table(name = "apartamento",schema = "syscond")
public class Apartamento {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String numero;
    @Column
    private String andar;
    @Column
    private String bloco;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "apartamento")
    private List<Morador> moradores = new ArrayList<>();

    public Apartamento() {
    }

    public Apartamento(String numero) {
        this.numero = numero;
    }

    public Apartamento(String numero, List<Morador> moradores) {
        this.numero = numero;
        this.moradores = moradores;
    }

    public Apartamento(String numero, String andar, String bloco) {
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;
    }

    public Apartamento(String numero, String andar, String bloco, List<Morador> moradores) {
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;
        this.moradores = moradores;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public List<Morador> getMorador() {
        return moradores;
    }

    public void setMorador(List<Morador> morador) {
        this.moradores = morador;
    }
}
