package negocios;

import dao.*;
import pojos.Morador;
import pojos.Usuario;

import java.util.List;

/**
 * Nessa classe de UsuarioNegocios sera chamada a UsuarioDAO para fazer um CRUD que tenha relacao com um BD.
 */
public class UsuarioNegocios {

    private UsuarioDaoInterface usuarioDao= new UsuarioDao();

    /**
     *
     * @param usuario parametro usado para cadastrar um usuario.
     */
    public void cadastrar(Usuario usuario){

        try{
            usuarioDao.adicionar(usuario);

        }catch(Exception e){
            System.out.println("UsuarioNegocios: Erro: " + e.getMessage());
        }

    }

    /**
     *
     * @param usuario parametro usado para apagar um usuario.
     */
    public void deletar(Usuario usuario){
        try{
            usuarioDao.remover(usuario);
        }catch(Exception e){
            System.out.println("UsuarioNegocios: Erro: " + e.getMessage());
        }
    }

    /**
     *
     * @return retorna uma lista de usuarios.
     */
    public List<Usuario> listarUsuarios(){
        int index = 0;
        List<Usuario> listaUsuarios = null;

        try{
            listaUsuarios = usuarioDao.listar();
            System.out.println("Listando Usuarios de sistemas: ");
            for (Usuario a:listaUsuarios) {
                index++;
                System.out.println("");
                System.out.println(index + "º Usuario:");
                System.out.println("Nome: " + a.getNome());
            }
            return listaUsuarios;
        }catch(Exception e){
            System.out.println("UsuarioNegocios: Erro: " + e.getMessage());
        }
        return listaUsuarios;
    }

    /**
     *
     * @param usuario parametro usado para alterar um usuario.
     */
    public void alterar(Usuario usuario){

        usuarioDao.alterar(usuario);

    }

    /**
     *
     * @param login parametro usado para procurar um usuario, quando acha
     * @return retorna um usuario pesquisado.
     */
    public Usuario pesquisar(String login){
       return usuarioDao.procurar(login);
    }

    /**
     *
     * @param usuario parametro usado para procurar um usuario, quando acha
     * @return retorna um usuario pesquisado.
     */
    public Usuario pesquisar(Usuario usuario){
        return usuarioDao.procurar(usuario);
    }

    /**
     *
     * @param usuario parametro usado para procurar por um usuario, quando encontra retorna um usuario,
     * com isso, esse metodo verifica se o login e a senha que o usuario digitou eh a mesma que esta salva no
     * BD do sistema, se for a mesma,
     * @return retorna um usuario e a mensagem de bem vindo, se for diferente retorna null e uma mensagem de
     * erro pedindo que o usario verifique seus dados.
     *
     */
    public Usuario autenticar(Usuario usuario){

        Usuario user = pesquisar(usuario);

        try{
            user.getLogin();
            if(usuario.getLogin().equals(user.getLogin())
                    &&usuario.getSenha().equals(user.getSenha())){

                return user;
            }else{
                return null;
            }

        }catch(Exception e){

            System.out.println("ERRO: "  + e.getMessage());
            return null;
        }

    }



}
