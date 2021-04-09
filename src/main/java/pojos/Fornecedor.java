package pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
