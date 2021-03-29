package exceptions.carro;

public class CarroJaExistente extends Exception{
    public CarroJaExistente(String placa){
        super(placa);
    }

    public CarroJaExistente(){
        super();
    }
}