package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 *  descricao:
 *  pojo de usuario com seus atributos mapeados, entidades, colouna, chave primaria;
 *  Alem de seus construtores gets e sets;
 *  nome deve ser obrigatorio e deve aceitar apenas letras;
 *  login e senha sera utilizado para um usuario conseguir ter acesso ao sistema (fazer login).
 */
@Entity
public class Usuario {

    @Id
    @NotNull
    @Pattern(message = "Nome de usuario Invelido", regexp = "[^ ]")
    private String login;
    @Column
    @NotNull
    private String senha;
    @Column
    @NotNull
    @Pattern(message = "Entrada invalida!, Apenas Letras são permitidas", regexp = "[a-zA-Z\\wÀ-ú'çÇ ]+")
    private String nome;

    public Usuario() {
    }

    /**
     *
     * @param login parametro utilizado nesse segundo construtor de usuario, pois o primeiro construtor eh vazio.
     */
    public Usuario(String login){
        this.login = login;
    }

    /**
     *
     * @param login e
     * @param senha sao os parametros usados nesse terceiro construtor de usuario.
     */
    public Usuario(@NotNull @Pattern(message = "Nome de usuario Invelido", regexp = "[^ ]") String login, @NotNull String senha) {
        this.login = login;
        this.senha = senha;
    }

    /**
     *
     * @param nome ,
     * @param login e
     * @param senha sao os parametros usados nesse quarto construtor de usuario.
     */
    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    /**
     *
     * @return retorna o nome de um usuario.
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome eh atribuido um novo nome para o usuario.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return retorna um login de um usuario.
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login eh atribuido um novo login para o usuario.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return retorna uma senha de um usuario.
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha eh atribuido uma nova senha para o usuario.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
