package exceptions.visitante;

public class VisitanteNaoEncontrado extends Exception{
    public VisitanteNaoEncontrado(String cpf){
        super(cpf);
    }

    public VisitanteNaoEncontrado(){
        super();
    }
}
