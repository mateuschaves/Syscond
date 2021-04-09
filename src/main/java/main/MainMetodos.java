package main;

import dao.*;
import pojos.Apartamento;
import pojos.Morador;

import java.util.List;

public class MainMetodos {
    public static void main(String[] args){
        //todas as dao, instanciadas:

        ApartamentoDaoInterface apartamentoDao = new ApartamentoDAO();
        MoradorDaoInterface moradorDao = new MoradorDAO();
        FuncionarioDaoInterface funcionarioDAO = new FuncionarioDAO();
        FornecedorDaoInterface fornecdorDAO = new FornecedorDAO();
        CarroDaoInterface carroDaoInterface = new CarroDAO();
        ProdutoDaoInterface produtoDaoInterface = new ProdutoDAO();
        VisitanteDaoInterface visitanteDaoInterface = new VisitanteDAO();

        // primeiro cadastramos apartamentos:

        //para cadastar, vamos criar os objetos apartamento:

        Apartamento ap1 = new Apartamento("1","Primeiro","A");
        Apartamento ap2 = new Apartamento("2","Primeiro","A");
        Apartamento ap3 = new Apartamento("3","Segundo","B");
        Apartamento ap4 = new Apartamento("4","Terceiro","B");
        Apartamento ap5 = new Apartamento("5","Segundo","B");

        try{
            apartamentoDao.adicionar(ap1);
            apartamentoDao.adicionar(ap2);
            apartamentoDao.adicionar(ap3);
            apartamentoDao.adicionar(ap4);
            apartamentoDao.adicionar(ap5);
        }catch (Exception e){
            System.out.println("Deu erro, no cadastro dos aps");
        }// apartamentos adicionados;

        //para exibir apartamento:
        try{
            List<Apartamento> listaApartamento =  apartamentoDao.listar();

            for(Apartamento a: listaApartamento){
                System.out.println("Apartamento numero: " + a.getNumero() + " Listado");
            }
        }catch(Exception e){
            e.getMessage();
        }// pronto! a partir daqui, todos foram listados e printados no Log

        //para alterar algum dado: passa um objeto com o mesmo id do que vc quer alterar com as modificações
        try {
            apartamentoDao.alterar(new Apartamento("1", "Decimo", "C"));
        }catch (Exception e){
            System.out.println("Nao foi possivel alterar os dados do apartamento");
        }//pode substituir alguns dados ai pra ver a alteração; pronto

        //para deletar um apartamento já cadastrado
        try{
            apartamentoDao.remover(ap3);
        }catch (Exception e){
            System.out.println("Não foi possivel remover o apartamento");
        }// pronto pra remover o apartamento é só passar o objeto;;

        //já que temos apartamentos cadastrados, podemos cadastrar moradores;




    }
}
