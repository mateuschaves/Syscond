package negocios;

import pojos.Carro;
import pojos.Morador;
import dao.CarroDAO;
import dao.CarroDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;

public class CadastroMorador {


    public void cadastrarMorador(Morador morador){


        CarroDaoInterface carrosDao = new CarroDAO();
        MoradorDaoInterface moradorDao = new MoradorDAO();


        moradorDao.adicionar(morador);

        for (Carro a:
                morador.getCarros()) {

            carrosDao.adicionar(a);


        }




    }
}
