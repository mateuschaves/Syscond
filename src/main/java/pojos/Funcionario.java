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
 *   pojo de funcionario com seus atributos mapeados, entidades, colouna, chave primaria.
 *   Alem de seus construtores gets e sets.
*/

@NamedQueries({ @NamedQuery(name = "Funcionario.buscaPorCPF", query = "select f from Funcionario f where f.cpf = :cpf"),
                @NamedQuery(name = "Funcionario.buscaTodos", query = "select f from Funcionario f") })
@Entity
public class Funcionario {

        @Id
        private String cpf;
        @Column(nullable = false)
        private String nome;
        @Column(nullable = false)
        private String funcao;

        public Funcionario() {
        }

        public Funcionario(String nome, String cpf, String funcao) {
                this.nome = nome;
                this.cpf = cpf;
                this.funcao = funcao;
        }

        public String getNome() {
                return this.nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getCpf() {
                return this.cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public String getFuncao() {
                return this.funcao;
        }

        public void setFuncao(String funcao) {
                this.funcao = funcao;
        }

}
