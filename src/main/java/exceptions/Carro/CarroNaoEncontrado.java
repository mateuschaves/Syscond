package exceptions.carro;

public class CarroNaoEncontrado extends Exception{
    public CarroNaoEncontrado(String placa){
        super(placa);
    }

    public CarroNaoEncontrado(){
        super();
    }
}
