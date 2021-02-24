/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;
import java.util.ArrayList;
import pojos.Funcionario;

/**
 *
 * @author Mattskywalker
 */
public class FuncionarioRepositorio { 
    
    private ArrayList<Funcionario> todosfuncionarios = new ArrayList();
    
    private Funcionario procurar (String cpf) {
        
        Funcionario a = null;
    
        for(int i=0;i<this.todosfuncionarios.size();i++){
            
            if(this.todosfuncionarios.get(i).getCpf().equals(cpf)){
                
                a = this.todosfuncionarios.get(i);
                break;
            }
            else{
                a = null;
            }
        }
        return a;
    }
    
    public void procurarFuncionario(String cpf){
        
        Funcionario a = this.procurar(cpf);
        
        if(a == null){
           
            System.out.println("Repositorio: Funcionario nao encontrado!!!");
        }
        
        else{
        
            System.out.println("Repositorio: Funcionario encontrado: " + a.getNome());
        }
    
    }
    
    public void cadastrar(String nome, String cpf, String funcao){
        
        if(this.procurar(cpf) == null){
        
            Funcionario a = new Funcionario(nome, cpf, funcao);
            this.todosfuncionarios.add(a);
            System.out.println("Repositorio: Cadastro Realizado com Sucesso!!!");
            System.out.println("Repositorio: Bem vindo Funcionario: " + a.getNome());
        }
        
        else{
            System.out.println("Repositorio: Funcionario nao pode ser cadastrado pois ja existe no sistema");
        }
    }
    
    public void deletar(String cpf){
        
        if(this.procurar(cpf) == null){
        
            System.out.println("Repositorio: Funcionario nao encontrado!!!");
        }
        else{
            Funcionario a = this.procurar(cpf);
            this.todosfuncionarios.remove(a);
            System.out.println("Repositorio: Funcionario deletado com Sucesso!: "+ a.getNome());
        }
    }
    
    public void alterarFuncao(String cpf, String fun){
    
        if(this.procurar(cpf) == null){
            System.out.println("Repositorio: Funcionario nao encontrado!!!");
        }
        else{
            for(int i = 0;i < this.todosfuncionarios.size();i++){
                if(this.procurar(cpf).getCpf().equals(cpf)){
                    //achamos quem nós queremos alterar
                    
                    String guardarNome = this.todosfuncionarios.get(i).getNome();
                    String guardarFun = this.todosfuncionarios.get(i).getFuncao();
                    
                    this.todosfuncionarios.get(i).setFuncao(fun);
                    
                    System.out.println("Repositorio: Função de: " + guardarNome + " alterada");
                    System.out.println("Repositorio: Função antiga: " + guardarFun);
                    System.out.println("Repositorio: Função nova: " + this.todosfuncionarios.get(i).getFuncao());
                    break;
                    
                }
            }
        }
    }
    
        public void alterarNome(String cpf, String nome){
    
        if(this.procurar(cpf) == null){
            System.out.println("Repositorio: Funcionario nao encontrado!!!");
        }
        else{
            
            for(int i = 0;i < this.todosfuncionarios.size();i++){
                if(this.procurar(cpf).getCpf().equals(cpf)){
                    //achamos quem nós queremos alterar
                    String guardarnome = this.todosfuncionarios.get(i).getNome();
                    this.todosfuncionarios.get(i).setNome(nome);
                    System.out.println("Repositorio: Nome alterado");
                    System.out.println("Repositorio: Nome antigo: "+guardarnome);
                    System.out.println("Repositorio: Nome novo: " + this.todosfuncionarios.get(i).getNome());
                    break;
                    
                }
            }
            
        }
    }
    
    
}
    
    
    
    
    
    

