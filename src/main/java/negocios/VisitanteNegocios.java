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

    public void listarVisitante(){
        int index = 0;
        List<Visitante> listaVisitante = null;

        try{
            listaVisitante = visitanteDao.listar();
            System.out.println("Listando Visitantes: ");
            for (Visitante a:listaVisitante) {
                index++;
                System.out.println("");
                System.out.println(index + " Visitante:");
                System.out.println("Nome: " + a.getNome());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("Nome do Morador a ser visitado!: " + a.getCpfMoradorResponsavel().getNome());
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Visitante Negocios: Erro: " + e.getMessage());
        }

    }

    public Visitante pesquisar(Visitante visitante){
        return (visitanteDao.procurar(visitante));
    }

}
