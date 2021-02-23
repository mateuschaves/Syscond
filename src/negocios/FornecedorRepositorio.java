/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;
import java.util.ArrayList;
import pojos.Fornecedor;
/**
 *
 * @author Mattskywalker
 */
public class FornecedorRepositorio {
    
    private ArrayList<Fornecedor> todosFornecedores = new ArrayList<>();
    
    public Fornecedor procurar(String chavePrimaria){
        
        Fornecedor a = null;
        
        
        for(int i = 0;i < this.todosFornecedores.size();i++){
            
            if(this.todosFornecedores.get(i).getCnpj().equals(chavePrimaria)){
                System.out.println("Fornecedor encontrado!");
                a = this.todosFornecedores.get(i);
                break;
            }
            else{
                System.out.println("Fornecedor não encontrado");
                a = null;
            }
            
        }
        
        
        return a;
    }
    
    
    public void cadastrar(String nome, String cnpj, String telefone){
        //verificar se o fornecedor já existe no sistema;
        if(this.procurar(cnpj) == null){
            Fornecedor a = new Fornecedor(nome, cnpj, telefone);
            this.todosFornecedores.add(a);
            
            System.out.println("Cadastro Realizado");
            System.out.println("bem vindo "+ a.getNome());

        }
        else{
            System.out.println("Usuario Já existe");
        }
    }
    public void cadastrar(String nome, String cnpj){
        //verificar se o fornecedor já existe no sistema;
        if(this.procurar(cnpj) == null){
            Fornecedor a = new Fornecedor(nome, cnpj);
            this.todosFornecedores.add(a);
            
            System.out.println("Cadastro Realizado");
            System.out.println("bem vindo "+ a.getNome());
        }
        else{
            System.out.println("Usuario Já existe");
        }
    }
    
    public void deletar(String cnpj){
        
        if(this.procurar(cnpj) == null){
            System.out.println("Fornecedor não encontrado!");
        }
        else{
            this.todosFornecedores.remove(this.procurar(cnpj));
        }
            
    }
    
    public void alterarNome(String cnpj,String nome){
        
        if(this.procurar(cnpj) == null){
            System.out.println("Fornecedor não encontrado!");
        }
        else{
            for(int i = 0;i < this.todosFornecedores.size();i++){
                this.todosFornecedores.get(i).setNome(nome);
            }
        }
        
    }
    
    public void alterarTelefone(String cnpj,String telefone){
        
        if(this.procurar(cnpj) == null){
            System.out.println("Fornecedor não encontrado!");
        }
        else{
            for(int i = 0;i < this.todosFornecedores.size();i++){
                this.todosFornecedores.get(i).setNome(telefone);
            }
        }
        
    }
    
    
}
