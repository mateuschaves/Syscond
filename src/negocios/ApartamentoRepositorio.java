/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classeauxiliar.TipoMorador;
import java.util.ArrayList;
import pojos.Apartamento;
import pojos.Morador;
import interfacedecodigo.ApartamentoRepositorioInterface;

/**
 *
 * @author Mattskywalker
 */
public class ApartamentoRepositorio implements ApartamentoRepositorioInterface {

    private ArrayList<Apartamento> todosApartamentos = new ArrayList<>();

    public Apartamento procurar(int numero) {

        Apartamento apartamento = null;

        for (int i = 0; i < this.todosApartamentos.size(); i++) {

            if (this.todosApartamentos.get(i).getNumero() == numero) {

                // System.out.println("Apartamento encontrado");
                apartamento = this.todosApartamentos.get(i);
                break;
            }

        }
        if (apartamento == null) {
            // System.out.println("Repositorio: Apartamento não encontrado");
        }
        return apartamento;
    }

    public void remover(Morador morador, int numero) {

        this.procurar(numero).getMorador().remove(morador);

    }

    public void alterar(Apartamento apartamento) {
        for (Apartamento apt : this.todosApartamentos) {
            if (apartamento.getNumero() == apt.getNumero()) {
                Apartamento apartamentoModificado = new Apartamento(apartamento.getNumero(), apartamento.getAndar(),
                        apartamento.getBloco());
                apartamentoModificado.setMorador(apartamento.getMorador());

                int index = this.todosApartamentos.indexOf(apartamento);
                this.todosApartamentos.set(index, apartamentoModificado);
            }
        }
    }

    public TipoMorador procurarStatus(int numero) {

        TipoMorador tipoMorador = null;
        Apartamento apartamento = this.procurar(numero);

        if (apartamento == null) {
            // System.out.println("RepositorioApartamento: Apartamento não existe");
        } else {
            for (int i = 0; i < apartamento.getMorador().size(); i++) {

                if (apartamento.getMorador().get(i).getStatus() == TipoMorador.PROPIETARIO) {
                    tipoMorador = TipoMorador.PROPIETARIO;
                    // System.out.println("RepositorioApartamento: Proprietario já existente");
                    break;
                }
            }
        }

        return tipoMorador;
    }

    public void adicionar(int numero, String andar, String bloco, Morador morador) {

        if (this.procurar(numero) == null) {
            // System.out.println("pronto para cadastrar");
            Apartamento aux = new Apartamento(numero, andar, bloco);
            this.todosApartamentos.add(aux);
            // System.out.println("cadastrado: "+ numero);
        } else {
            System.out.println("RepositorioApartamento: Apartamento numero: " + numero + " já foi cadastrado");
        }
    }

    public ArrayList<Apartamento> listar() {
        return this.todosApartamentos;
    }
}
