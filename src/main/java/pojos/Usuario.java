package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    public Usuario(@NotNull @Pattern(message = "Nome de usuario Invelido", regexp = "[^ ]") String login, @NotNull String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
