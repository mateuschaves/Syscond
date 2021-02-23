/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syscond;
import negocios.FornecedorRepositorio;
import pojos.Fornecedor;
/**
 *
 * @author Mattskywalker
 */
public class Main {
    
    public static void main(String[] arg){
        
        FornecedorRepositorio cadastroFornecedor = new FornecedorRepositorio();
        
        cadastroFornecedor.cadastrar("Breno", "1234567890", "81 91992517");
        
        
        
       
    }
    
}
