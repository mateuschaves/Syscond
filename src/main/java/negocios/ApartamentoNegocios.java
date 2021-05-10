package negocios;

import dao.ApartamentoDAO;
import dao.ApartamentoDaoInterface;
import exceptions.apartamento.ApartamentoJaExistente;
import pojos.Apartamento;
import pojos.Morador;

import java.util.List;

public class ApartamentoNegocios {


    ApartamentoDaoInterface apartamentoDao = new ApartamentoDAO();

    public void cadastrar(Apartamento apartamento) throws ApartamentoJaExistente {

        try{
            apartamentoDao.adicionar(apartamento);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deletar(Apartamento apartamento){
        try{
            System.out.println("Deletando apartamento:" + apartamento.getNumero());
            apartamentoDao.remover(apartamento);
        }catch(Exception e){
            //e.printStackTrace();
        }
    }

    public List<Apartamento> listarApartamentos(){
        int index = 0;
        List<Apartamento> listaApartamentos = null;

        try{
            listaApartamentos = apartamentoDao.listar();
            System.out.println("Listando Apartamentos: ");
            for (Apartamento a:listaApartamentos) {
                index++;
                System.out.println("");
                System.out.println(index + "º Apartamento:");
                System.out.println("Numero: " + a.getNumero());
                System.out.println("Andar: " + a.getAndar());
                System.out.println("Bloco: " + a.getBloco());
            }
            return listaApartamentos;
        }catch(Exception e){
            System.out.println("ApartamentoNegocios: Erro: " + e.getMessage());
        }

        return listaApartamentos;

    }

    public void alterarID(Apartamento apartamento, String novoNumero){
        Apartamento target = null;
        try{
            target = apartamentoDao.procurar(apartamento);
            apartamentoDao.remover(target);
            target.setNumero(novoNumero);
            apartamentoDao.adicionar(target);

        }catch (Exception e){
            System.out.println("ApartamentoNegocios: Erro: " + e.getMessage());
        }

    }

    public void alterar(Apartamento apartamento){

        try {
            apartamentoDao.alterar(apartamento);
        }catch (Exception e){
            System.out.println("ApartamerntoNegocios: Erro: " + e.getMessage());
        }
    }

    public Apartamento pesquisar(Apartamento apartamento){

        return(apartamentoDao.procurar(apartamento));

    }



}
