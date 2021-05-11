package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 *   descricao:
 *   pojo de funcionario com seus atributos mapeados, entidades, colouna, chave primaria;
 *   Alem de seus construtores gets e sets;
 *   cpf deve ser obrigatorio, unico e no formato: 999.999.999-99;
 *   nome deve ser obrigatorio e deve aceitar apenas letras;
 *   funcao deve ser obrigatoria.
 *
*/

@NamedQueries({ @NamedQuery(name = "Funcionario.buscaPorCPF", query = "select f from Funcionario f where f.cpf = :cpf"),
                @NamedQuery(name = "Funcionario.buscaTodos", query = "select f from Funcionario f") })
@Entity
public class Funcionario {

        @Id
        @NotNull(message = "o cpf do funcionario nao pode ser nullo!")
        @Pattern(message = "Entrada invalida o cpf deve seguir o formato!", regexp = "^[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}$")
        private String cpf;
        @Column(nullable = false)
        @NotNull(message = "o nome do funcionario nao pode ser nullo!")
        @Pattern(message="Entrada invalida!, São permitidos apenas letras!", regexp = "[a-zA-Z çÇ]*")
        private String nome;
        @Column(nullable = false)
        @NotNull(message = "um funcionario precisar ter uma função!")
        private String funcao;

        public Funcionario() {
        }

        /**
         * @param nome ,
         * @param cpf e
         * @param funcao sao os parametros usados nesse segundo construtor de funcionario, pois o primeiro construtor eh vazio.
         */
        public Funcionario(String nome, String cpf, String funcao) {
                this.nome = nome;
                this.cpf = cpf;
                this.funcao = funcao;
        }

        /**
         *
         * @param cpf é o parametro usado nesse terceiro de funcionario.
         */
        public Funcionario(String cpf){
                this.cpf = cpf;
        }

        /**
         * @return retorna o nome do funcionario.
         */
        public String getNome() {
                return this.nome;
        }

        /**
         * @param nome eh atribuido um novo nome para o funcionario.
         */
        public void setNome(String nome) {
                this.nome = nome;
        }

        /**
         * @return retorna o cpf do funcionario.
         */
        public String getCpf() {
                return this.cpf;
        }

        /**
         * @param cpf eh atribuido um novo cpf para o funcionario.
         */
        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        /**
         * @return retorna a funcao do funcionario.
         */
        public String getFuncao() {
                return this.funcao;
        }

        /**
         * @param funcao eh atribuido uma nova funcao para o funcionario.
         */
        public void setFuncao(String funcao) {
                this.funcao = funcao;
        }

}
