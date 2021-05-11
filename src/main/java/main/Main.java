package main;

import dao.*;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import exceptions.funcionario.FuncionarioJaExistente;
import exceptions.funcionario.FuncionarioNaoEncontrado;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Fornecedor;
import pojos.Funcionario;
import pojos.Morador;
import pojos.Produto;
import pojos.Visitante;
import enums.EntityEnumType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * descricao: essa main procura testar a maioria das classes do sistema com suas
 * funcionalidades e claro no nosso pc rodava professor! kkkk :)
 */

public class Main {

    static MoradorDaoInterface moradorDAO = new MoradorDAO();
    static ApartamentoDaoInterface apartamentoDAO = new ApartamentoDAO();
    static CarroDaoInterface carroDAO = new CarroDAO();
    static ProdutoDaoInterface produtoDao = new ProdutoDAO();
    static FornecedorDaoInterface fornecedorDao = new FornecedorDAO();
    static FuncionarioDaoInterface funcionarioDao = new FuncionarioDAO();
    static VisitanteDaoInterface visitanteDAO = new VisitanteDAO();

    public static void main(String[] args) throws FornecedorNaoEncontrado, IOException {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);

        EntityEnumType entityToTest = EntityEnumType.APARTAMENTO;

        switch (entityToTest) {
            case APARTAMENTO:
                testApartamentoEntity();
                break;
            case MORADOR:
                testMoradorEntity();
                break;
            case VISITANTE:
                testVisitanteEntity();
                break;
            case CARRO:
                testCarroEntity();
                break;
            case FORNECEDOR:
                testFornecedorEntity();
                break;
            case PRODUTO:
                testProdutoEntity();
                break;
            default:
                break;
        }


        /*
         
        */
        JPAUtil.close();

    }


    public static void testApartamentoEntity() {
        try{
            List<Carro> listaCarros = new ArrayList<>();
            Apartamento ap1 = new Apartamento("11111111", "Primeiro", "B");
            Morador m1 = new Morador("123.123.123-12", "Hamazura", ap1, listaCarros);
            Morador m2 = new Morador("123.123.123-19", "Mateus", ap1, listaCarros);
            apartamentoDAO.adicionar(ap1);
            moradorDAO.adicionar(m1);
            moradorDAO.adicionar(m2);
            apartamentoDAO.remover(ap1);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void testMoradorEntity() {

        try {
            // É necessário criar os apartamentos para cadastrar um morador
            testApartamentoEntity();


            Apartamento ap1 = apartamentoDAO.procurar("1");
            Apartamento ap2 = apartamentoDAO.procurar("2");
            Apartamento ap3 = apartamentoDAO.procurar("3");
            Apartamento ap4 = apartamentoDAO.procurar("4");

            List<Carro> listaCarros = new ArrayList<>();
            Morador m1 = new Morador("1", "Hamazura", ap1, listaCarros);
            Morador m2 = new Morador("2", "Mateus", ap2, listaCarros);
            Morador m3 = new Morador("3", "Eduardo", ap3, listaCarros);
            Morador m4 = new Morador("4", "Breno", ap4, listaCarros);
            Morador m5 = new Morador("5", "Você sabe quem", ap4, listaCarros);

            moradorDAO.adicionar(m1);
            moradorDAO.adicionar(m2);
            moradorDAO.adicionar(m3);
            moradorDAO.adicionar(m4);
            moradorDAO.adicionar(m5);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void testFornecedorEntity() {
        Fornecedor fornecedor = new Fornecedor("1234","Breno","indefinido");

        try {
            fornecedorDao.adicionar(fornecedor);
            fornecedorDao.adicionar(new Fornecedor("12345","Luana","indefinido"));
            System.out.println("Fornecedor procurado: " + fornecedorDao.procurar("1235").getNome());
       

            System.out.println("Lista de fornecedores: ");

            for (Fornecedor a: fornecedorDao.listar()) {
                System.out.println("Fornecedor: " + a.getNome());
            }

            System.out.println("Fornecedor encontrado: " + fornecedorDao.procurar("123").getNome());

            fornecedorDao.remover(fornecedor);

            fornecedorDao.alterar(new Fornecedor("12345","Luana Martins de Andrade Lima","indefinido"));

            System.out.println("Lista de fornecedores: ");
            for (Fornecedor a: fornecedorDao.listar()) {
                System.out.println("Fornecedor: " + a.getNome());
            }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
    }

    public static void testCarroEntity() {

        // É necessário criar os moradores para cadastrar um carro
        testMoradorEntity();
        Morador m1 = moradorDAO.procurar("1");
        Morador m2 = moradorDAO.procurar("2");
        Morador m3 = moradorDAO.procurar("3");
        

        try {
            testMoradorEntity();

            Carro carro1 = new Carro("M4-A31","Fiat Uno","Branco",m1);
            Carro carro2 = new Carro("M2-A1","Fiat Uno com escadinha","Preto",m2);
            Carro carro3 = new Carro("M4-A1","Pálio rebaixado","Vermelho",m2);
            Carro carro4 = new Carro("M4-A0","Honda civic","Rosa",m3);

            carroDAO.adicionar(carro1);
            carroDAO.adicionar(carro2);
            carroDAO.adicionar(carro3);
            carroDAO.adicionar(carro4);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }

    public static void testVisitanteEntity() {

        // É necessário criar os moradores para cadastrar um visitante
        testMoradorEntity();

        try {
            Morador m1 = moradorDAO.procurar("1");
            Morador m2 = moradorDAO.procurar("2");

            Visitante v1 = new Visitante("1999","Kaifuku", m1);
            Visitante v2 = new Visitante("4","Touma", m2);

            visitanteDAO.adicionar(v1);
            visitanteDAO.adicionar(v2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static void testProdutoEntity() {
        try {
            produtoDao.adicionar(new Produto("Sabão", 10, "1", 4));
            produtoDao.adicionar(new Produto("Detergente", 2, "2", 10));
            produtoDao.adicionar(new Produto("Luvas", 50, "3", 2));
            produtoDao.adicionar(new Produto("Sabão em barra", 5, "4", 6));

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o produto");

        }
    }

    public static void testFuncionarioEntity() {

        try {
            funcionarioDao.adicionar(new Funcionario("Alexandre", "37455002025", "Contabilidade"));
            funcionarioDao.adicionar(new Funcionario("Fernando", "13975666048", "Estoque"));
            funcionarioDao.adicionar(new Funcionario("Luciana", "88859826039", "Porteiro"));
            System.out.println("Funcionários adicionados com sucesso!");
        } catch (FuncionarioJaExistente e) {
            System.out.println("Funcionário com CPF " + e.getMessage() + " já foi cadastrado no sistema");
        }

        try {
            System.out.println("Oi, eu sou o " + funcionarioDao.procurar("37455002025").getNome());
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("Funcionário com CPF " + e.getMessage() + " não foi encontrado no sistema");
        }

        try {
            for(Funcionario funcionario: funcionarioDao.listar()){
                System.out.println("Nome: " + funcionario.getNome());
            }
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("A lista de funcionários está vazia.");
        }

        try {
            funcionarioDao.remover("37455002025");
            System.out.println("Funcionário removido com sucesso!");
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("Funcionário com CPF " + e.getMessage() + " não foi encontrado no sistema");
        }

        try {
            for(Funcionario funcionario: funcionarioDao.listar()){
                System.out.println("Nome: " + funcionario.getNome());
            }
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("A lista de funcionários está vazia.");
        }

        try {
            funcionarioDao.alterar(new Funcionario("João", "13975666048", "Faxineiro"));
            System.out.println("Funcionário alterado com sucesso!");
        } catch (FuncionarioNaoEncontrado | FuncionarioJaExistente e1) {
            System.out.println("Deu a mulesta");
        }

        try {
            for(Funcionario funcionario: funcionarioDao.listar()){
                System.out.println("Nome: " + funcionario.getNome());
            }
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("A lista de funcionários está vazia.");
        }
    }

}
