package exceptions.morador;

public class MoradorJaExistente extends Exception{
    public MoradorJaExistente(String cpf){
        super(cpf);
    }

    public MoradorJaExistente(){
        super();
    }
}