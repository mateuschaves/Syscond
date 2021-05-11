package negocios;

import dao.ApartamentoDAO;
import dao.ApartamentoDaoInterface;
import exceptions.apartamento.ApartamentoJaExistente;
import pojos.Apartamento;
import pojos.Morador;

import java.util.List;

/**
 * Nessa classe de ApartamentoNegocios sera chamada a ApartamentoDAO para fazer um CRUD que tenha relacao com um BD.
 */
public class ApartamentoNegocios {


    ApartamentoDaoInterface apartamentoDao = new ApartamentoDAO();

    /**
     *
     * @param apartamento parametro utilizado para cadastrar um apartamento;
     * @throws ApartamentoJaExistente confere se esse apartamento ja existe no BD, se nao existir pode cadastrar.
     */
    public void cadastrar(Apartamento apartamento) throws ApartamentoJaExistente {

        try{
            apartamentoDao.adicionar(apartamento);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     *
     * @param apartamento parametro utilizado para apagar um apartamento.
     */
    public void deletar(Apartamento apartamento){
        try{
            MoradorNegocios moradorNegocios = new MoradorNegocios();

            List<Morador> moradorList  = moradorNegocios.listarMoradores();

            String numeroApartamento = apartamento.getNumero();
            System.out.println("Numero do apartamento: " + numeroApartamento);
            for(Morador a : moradorList ){// deletando os diferentes;
                String numeroMorador = a.getApartamento().getNumero();
                System.out.println("Nome: " + a.getNome() + " Numero: "+ numeroMorador);
                if(numeroMorador.equals(numeroApartamento)){// se o morador tiver o mesmo numero, significa qu ele mora lá

                    moradorNegocios.deletar(a);
                    System.out.println("IF: " + a.getNome());
                }
            }

            System.out.println("Deletando apartamento:" + apartamento.getNumero());
            apartamentoDao.remover(apartamento);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return retorna uma lista de apartamentos.
     */
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

    /**
     *
     * @param apartamento utilizado para encontrar um apartamento e
     * @param novoNumero utilizado para colocar um novo numero no apartamento.
     */
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

    /**
     *
     * @param apartamento parametro usado para alterar um apartamento.
     */
    public void alterar(Apartamento apartamento){

        try {
            apartamentoDao.alterar(apartamento);
        }catch (Exception e){
            System.out.println("ApartamerntoNegocios: Erro: " + e.getMessage());
        }
    }

    /**
     *
     * @param apartamento parametro usado para encontrar um apartamento e quando encontra
     * @return retorna o apartamento pesquisado.
     */
    public Apartamento pesquisar(Apartamento apartamento){

        return(apartamentoDao.procurar(apartamento));

    }



}
