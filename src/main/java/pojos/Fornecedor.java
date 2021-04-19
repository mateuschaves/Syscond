package pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *   @author Mateus Martins
 *   @author Breno Araujo
 *   @author Eduardo Marinho
 *   @author Mateus Henrique
 *
 *   pojo de fornecedor com seus atributos mapeados, entidades, colouna, chave primaria e
 *   com relacionamento com produto (falta fazer),  (1 para n).
 *   Alem de seus construtores gets e sets.
*/
@NamedQueries({ 
    @NamedQuery(name = "Fornecedor.buscaPorCNPJ", query = "select f from Fornecedor f where f.cnpj = :cnpj"),
    @NamedQuery(name = "Fornecedor.buscaTodos", query = "select f from Fornecedor f") })

@Entity
public class Fornecedor {
    @Id
    String cnpj;
    @Column(nullable = false)
    String nome;
    @Column
    String telefone;


    public Fornecedor() {
    }

    public Fornecedor(String cnpj, String nome, String telefone) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
