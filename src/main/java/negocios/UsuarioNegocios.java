package negocios;

import dao.*;
import pojos.Morador;
import pojos.Usuario;

import java.util.List;

public class UsuarioNegocios {

    private UsuarioDaoInterface usuarioDao= new UsuarioDao();

    public void cadastrar(Usuario usuario){

        try{
            usuarioDao.adicionar(usuario);

        }catch(Exception e){
            System.out.println("UsuarioNegocios: Erro: " + e.getMessage());
        }

    }
    public void deletar(Usuario usuario){
        try{
            usuarioDao.remover(usuario);
        }catch(Exception e){
            System.out.println("UsuarioNegocios: Erro: " + e.getMessage());
        }
    }
    public void listarUsuarios(){
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
        }catch(Exception e){
            System.out.println("UsuarioNegocios: Erro: " + e.getMessage());
        }

    }
    public void alterar(Usuario usuario){

        usuarioDao.alterar(usuario);

    }
    public Usuario pesquisar(Usuario usuario){
       return usuarioDao.procurar(usuario);
    }

    public void autenticar(){

    }



}
