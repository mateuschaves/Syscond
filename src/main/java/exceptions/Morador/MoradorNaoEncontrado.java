package exceptions.morador;

public class MoradorNaoEncontrado extends Exception{
    public MoradorNaoEncontrado(String cpf){
        super(cpf);
    }

    public MoradorNaoEncontrado(){
        super();
    }
}
