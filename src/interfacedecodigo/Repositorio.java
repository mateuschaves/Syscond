/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacedecodigo;
import java.util.ArrayList;
/**
 *
 * @author Mattskywalker
 */
public interface Repositorio {
    
    public void adicionar(Object objeto);
    public void remover(Object objeto);
    public ArrayList<Object> listar(Object objeto);
    public void alterar();
    public Object procurar(Object objeto);
    
}
