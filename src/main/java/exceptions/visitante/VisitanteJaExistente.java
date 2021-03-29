package exceptions.visitante;

public class VisitanteJaExistente extends Exception{
    public VisitanteJaExistente(String cpf){
        super(cpf);
    }

    public VisitanteJaExistente(){
        super();
    }
}