package view.auxiliar;

import java.io.*;
import java.util.Scanner;

public class CurrentUserWriter {
    private File file = new File("src/main/resources/userUtil/CurrentUser.txt");

    public void writeUser(String userLogin) throws IOException {
        int i;

        FileWriter arq = new FileWriter(file);
        PrintWriter printUser = new PrintWriter(arq);

        printUser.printf(userLogin);

        arq.close();
    }

    public String readUser(){

        String user;

        try {

            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);

            user = lerArq.readLine(); // lê a primeira linha
            arq.close();
            file.delete();
            return user;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
            return null;
        }
    }

}
