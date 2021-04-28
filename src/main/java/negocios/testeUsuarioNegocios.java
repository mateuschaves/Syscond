package negocios;

import pojos.Usuario;

public class testeUsuarioNegocios {

    public static void main(String [] args){

        Usuario user = new Usuario("mateus","mateus","breno");
        UsuarioNegocios negocios = new UsuarioNegocios();

        negocios.cadastrar(user);
    }


}
