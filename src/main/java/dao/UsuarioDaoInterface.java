package dao;

import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import pojos.Fornecedor;
import pojos.Usuario;

import java.util.List;

public interface UsuarioDaoInterface {

    /**
     * @return retorna um Usuario;
     * @param login utilizado para procurar um usuario;
     */
    public Usuario procurar(String login);

    /**
     * @return retorna um Usuario;
     * @param usuario utilizado para procurar um usuario;
     */
    public Usuario procurar(Usuario usuario);

    /**
     * @param usuario utilizado para adicionar um usuario.
     */
    public void adicionar(Usuario usuario);

    /**
     * @param usuario utilizado para apagar um usuario.
     */
    public void remover(Usuario usuario);

    /**
     * @return retorna uma lista de Usuarios;
     */

    public List<Usuario> listar();
    /**
     * nao existe;
     * @param usuario utilizado para alterar um usuario.
     */
    public void alterar(Usuario usuario);


}
