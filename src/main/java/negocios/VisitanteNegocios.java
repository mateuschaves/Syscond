package negocios;

import dao.VisitanteDAO;
import dao.VisitanteDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;
import pojos.Morador;
import pojos.Visitante;

import java.util.List;

public class VisitanteNegocios {

    VisitanteDaoInterface visitanteDao = new VisitanteDAO();
    MoradorDaoInterface moradorDao = new MoradorDAO();

    public void cadastrar(Visitante visitante){
        try {
            visitanteDao.adicionar(visitante);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Visitante Negocios: Erro ao adicionar visitante!" + e.getMessage());
        }
    }

    public void deletar(Visitante visitante){
        try {
            visitanteDao.remover(visitante);
        }catch (Exception e){
            System.out.println("Visitante Negocios: Erro ao deletar um visitante!" + e.getMessage());
        }
    }

    public void alterar(Visitante visitante){

        try {
            visitanteDao.alterar(visitante);
        }catch (Exception e){
            System.out.println("Visitante Negocios: Erro ao alterar um visitante!" + e.getMessage());
        }
    }

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

    public Visitante pesquisar(Visitante visitante){
        return (visitanteDao.procurar(visitante));
    }

}
