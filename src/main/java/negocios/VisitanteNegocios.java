package negocios;

import dao.VisitanteDAO;
import dao.VisitanteDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;
import pojos.Morador;
import pojos.Visitante;

import java.util.List;

/**
 * Nessa classe de VisitanteNegocios sera chamada a VisitanteDAO para fazer um CRUD que tenha relacao com um BD.
 */
public class VisitanteNegocios {

    VisitanteDaoInterface visitanteDao = new VisitanteDAO();
    MoradorDaoInterface moradorDao = new MoradorDAO();

    /**
     *
     * @param visitante eh o parametro usado para cadastrar um visitante.
     */
    public void cadastrar(Visitante visitante){
        try {
            visitanteDao.adicionar(visitante);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Visitante Negocios: Erro ao adicionar visitante!" + e.getMessage());
        }
    }

    /**
     *
     * @param visitante eh o parametro usado para apagar um visitante.
     */
    public void deletar(Visitante visitante){
        try {
            visitanteDao.remover(visitante);
        }catch (Exception e){
            System.out.println("Visitante Negocios: Erro ao deletar um visitante!" + e.getMessage());
        }
    }

    /**
     *
     * @param visitante eh o parametro usado para alterar um visitante.
     */
    public void alterar(Visitante visitante){

        try {
            visitanteDao.alterar(visitante);
        }catch (Exception e){
            System.out.println("Visitante Negocios: Erro ao alterar um visitante!" + e.getMessage());
        }
    }

    /**
     *
     * @return retorna uma lista de visitantes.
     */
    public List<Visitante> listarVisitante(){
        int index = 0;
        List<Visitante> listaVisitante = null;

        try{
            return listaVisitante = visitanteDao.listar();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Visitante Negocios: Erro: " + e.getMessage());
            return null;
        }

    }

    /**
     *
     * @param visitante eh o parametro usado para procurar um visitante, quando encontra
     * @return retorna um visitante pesquisado.
     */
    public Visitante pesquisar(Visitante visitante){
        return (visitanteDao.procurar(visitante));
    }

}
