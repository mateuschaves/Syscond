package view.auxiliar;

import java.io.*;

/**
 * Nessa classe, operações de escrita e leitura são realizadas para mostrar na tela o usuario que  logou no sistema.
 */
public class CurrentUserWriter {
    private File file = new File("src/main/resources/userUtil/CurrentUser.txt");

    /**
     *
     * @param userLogin eh o parametro usado para escrever em arquivo o usuario;
     * @throws IOException Pode retorna uma exeção de entrada e saida.
     */
    public void writeUser(String userLogin) throws IOException {
        int i;

        FileWriter arq = new FileWriter(file);
        PrintWriter printUser = new PrintWriter(arq);

        printUser.printf(userLogin);

        arq.close();
    }

    /**
     *
     * @return le um arquivo e retorna um usuario.
     */
    public String readUser(){

        String user;

        try {

            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);

            user = lerArq.readLine(); // lê a primeira linha
            arq.close();

            return user;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
            return null;
        }
    }

}
