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
    
    private Fornecedor procurar(String chavePrimaria){
        
        Fornecedor a = null;
        
        
        for(int i = 0;i < this.todosFornecedores.size();i++){
            
            if(this.todosFornecedores.get(i).getCnpj().equals(chavePrimaria)){
                //System.out.println("Fornecedor encontrado!");
                a = this.todosFornecedores.get(i);
                break;
            }
            else{
                a = null;
            }
            
        }
        
        if(a == null){
            //System.out.println("Fornecedor não encontrado");
        }
        
        return a;
    }
    
    public void procurarFornecedor(String chavePrimaria){
        
        Fornecedor a = this.procurar(chavePrimaria);
        
        if(a == null){
            System.out.println("Usuario não encontrado!");
        }
        else{
            System.out.println("Usuario encontrado: " + a.getNome());
        }
        
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
            Fornecedor a = this.procurar(cnpj);
            this.todosFornecedores.remove(this.procurar(cnpj));
            System.out.println(a.getNome() +" Deletado!");
        }
            
    }
    
    public void alterarNome(String cnpj,String nome){
        
        if(this.procurar(cnpj) == null){
            System.out.println("Fornecedor não encontrado!");
        }
        else{
            for(int i = 0;i < this.todosFornecedores.size();i++){
                if(this.procurar(cnpj).getCnpj().equals(cnpj)){
                    //achamos quem nós queremos alterar
                    String guardarnome = this.todosFornecedores.get(i).getNome();
                    this.todosFornecedores.get(i).setNome(nome);
                    System.out.println("Repositorio: Nome alterado");
                    System.out.println("Repositorio: Nome antigo: "+guardarnome);
                    System.out.println("Repositorio: Nome novo: " + this.todosFornecedores.get(i).getNome());
                    break;
                    
                }
            }
        }
        
    }
    
    public void alterarTelefone(String cnpj,String telefone){
        
        if(this.procurar(cnpj) == null){
            System.out.println("Fornecedor não encontrado!");
        }
        else{
            
            for(int i = 0;i < this.todosFornecedores.size();i++){
                if(this.procurar(cnpj).getCnpj().equals(cnpj)){
                    //achamos quem nós queremos alterar
                    String guardarTelefone= this.todosFornecedores.get(i).getTelefone();
                    this.todosFornecedores.get(i).setTelefone(telefone);
                    System.out.println("Repositorio: Telefone de: " + this.todosFornecedores.get(i).getNome() + " alterado");
                    System.out.println("Repositorio: Telefone antigo: "+ guardarTelefone);
                    System.out.println("Repositorio: Telefone novo: " + this.todosFornecedores.get(i).getTelefone());
                    break;
                    
                }
            }
        }
        
    }
    
    
}
