package main;

import dao.*;
import pojos.*;

import java.util.ArrayList;
import java.util.List;

public class MainMetodos {
    public static void main(String[] args) {
        //todas as dao, instanciadas:

        ApartamentoDaoInterface apartamentoDao = new ApartamentoDAO();
        MoradorDaoInterface moradorDao = new MoradorDAO();
        //FuncionarioDaoInterface funcionarioDAO = new FuncionarioDAO();
        FornecedorDaoInterface fornecdorDAO = new FornecedorDAO();
        CarroDaoInterface carroDao = new CarroDAO();
        //ProdutoDaoInterface produtoDaoInterface = new ProdutoDAO();
        VisitanteDaoInterface visitanteDao = new VisitanteDAO();

        // primeiro cadastramos apartamentos:

        //para cadastar, vamos criar os objetos apartamento:

        Apartamento ap1 = new Apartamento("1", "Primeiro", "A");
        Apartamento ap2 = new Apartamento("2", "Primeiro", "A");
        Apartamento ap3 = new Apartamento("3", "Segundo", "B");
        Apartamento ap4 = new Apartamento("4", "Terceiro", "B");
        Apartamento ap5 = new Apartamento("5", "Segundo", "B");

        try {
            apartamentoDao.adicionar(ap1);
            apartamentoDao.adicionar(ap2);
            apartamentoDao.adicionar(ap3);
            apartamentoDao.adicionar(ap4);
            apartamentoDao.adicionar(ap5);
        } catch (Exception e) {
            System.out.println("Deu erro, no cadastro dos aps");
        }// apartamentos adicionados;

        //para exibir apartamento:
        try {
            List<Apartamento> listaApartamento = apartamentoDao.listar();

            for (Apartamento a : listaApartamento) {
                System.out.println("Apartamento numero: " + a.getNumero() + " Listado");
            }
        } catch (Exception e) {

            System.out.println("erro ao listar apartamentos");
        }// pronto! a partir daqui, todos foram listados e printados no Log

        //para alterar algum dado: passa um objeto com o mesmo id do que vc quer alterar com as modificações
        try {
            apartamentoDao.alterar(new Apartamento("1", "Decimo", "C"));
        } catch (Exception e) {
            System.out.println("Nao foi possivel alterar os dados do apartamento");
        }//pode substituir alguns dados ai pra ver a alteração; pronto

        //para deletar um apartamento já cadastrado
        try {
            apartamentoDao.remover(ap3);
        } catch (Exception e) {
            System.out.println("Não foi possivel remover o apartamento");
        }// pronto pra remover o apartamento é só passar o objeto;;

        //já que temos apartamentos cadastrados, podemos cadastrar moradores;

        Morador m1 = new Morador("123.456.789-25", "Mateus", ap5);
        Morador m2 = new Morador("129.467.792-35", "Breno", ap2);
        Morador m3 = new Morador("133.564.897-52", "Kaifuku", ap4);
        Morador m4 = new Morador("213.645.983-19", "Darth-Vader", ap1);

        try {
            moradorDao.adicionar(m1);
            moradorDao.adicionar(m2);
            moradorDao.adicionar(m3);
            moradorDao.adicionar(m4);
        } catch (Exception e) {
            System.out.println("Deu erro, no cadastro dos moradores");
        }

        // para exibir moradores temos:
        try {
            List<Morador> listaMoradores = moradorDao.listar();

            for (Morador a : listaMoradores) {
                System.err.println("Morador nome: " + a.getNome() + " Listado");
            }

        } catch (Exception e) {
            System.out.println("Deu erro, na listagem dos moradores");
        }

        // para alterar algum dado: passa um objeto com o mesmo id do que vc quer alterar com as modificações

        try {
            moradorDao.alterar(new Morador("129.467.792-35", "F", ap2));
        } catch (Exception e) {
            System.out.println("Deu erro, na mudança de dados dos moradores");
        }

        // agora vamos deletar!

        try {
            moradorDao.remover(m2);
        } catch (Exception e){
            System.out.println("Deu erro, na remoção dos moradores");
        }
        /* vamos adicionar moradores que possuem veiculos e visitantes, eles são independentes
        porem o princio é o mesmo;
         */

        // morador com muitos carros e com muitos visitantes;


        //para cadastrar, eu preciso passar um List<> com os carros e os visitantes:

        List<Carro> carrosList = new ArrayList<>();
        List<Visitante> visitanteList = new ArrayList<>();

        Morador m5 = new Morador("761.417.961-69","Paulemir",ap5,carrosList,visitanteList);

        Carro c1 = new Carro("TMX-3693", "Gol_Quadrado", "verde",m5);
        Carro c2 = new Carro("ZVW-3639", "Gol", "vermelha", m5);

        Visitante v1 = new Visitante("345.231.234-99","Breno",m5);
        Visitante v2 = new Visitante("364.253.124-79","Rogger",m5);

        carrosList.add(c1);
        carrosList.add(c2);

        visitanteList.add(v1);
        visitanteList.add(v2);

        //morador

        try{
            moradorDao.adicionar(m5);
            for(Carro a: carrosList){
                carroDao.adicionar(a);
            }
            for(Visitante b: visitanteList){
                visitanteDao.adicionar(b);
            }

        }catch (Exception e){
            System.out.println("Deu merda ao cadastrar o morador 5");
        }

        /* pronto, morador, seus carros e visitantes foram cadastrados com sucesso;
        vamos agora cadastrar um funcionario;

        Para fornecedor, produto e funcionario, o processo
        é o mesmo; eles não tem nenhuma relação com outros objetos do banco de dados
        por isso é basicamente a mesma coisa.

         */

        Fornecedor f1 = new Fornecedor(

                "99.999.999/0001-99",

                "Netinho Presentes",

                "(81) 9 9999-0999"
        );

        try {
            fornecdorDAO.adicionar(f1);
        }catch (Exception e){
            System.out.println("Houve algum problema ao cadastrar um fornecedor");
        }

        //fornecedores tem metodos, de apagar e
        // alterar parecidos com os metodos do apartamento;


    }
}

