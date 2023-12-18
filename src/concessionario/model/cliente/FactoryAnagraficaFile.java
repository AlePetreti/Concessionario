package concessionario.model.cliente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FactoryAnagraficaFile implements FactoryAnagraficaCliente{

    private String file;

    public FactoryAnagraficaFile(String file) throws FileNotFoundException {
        this.file = file;
    }

    @Override
    public AnagraficaClienti creaAnagraficaClienti() {

        AnagraficaClienti anagraficaClienti = new AnagraficaClienti();
        String linea = new String();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((linea = bufferedReader.readLine()) != null) {
                String[] parti = linea.split(",");

                if (parti.length == 5) {
                    String nome = parti[0];
                    String cognome = parti[1];
                    String email = parti[2];
                    String telefono = parti[3];
                    String codiceFiscale = parti[4];
                    
                    Cliente cliente = new Cliente(nome, cognome, email, telefono, codiceFiscale);
                    anagraficaClienti.registraCliente(cliente);
                }   
            }
        }catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return anagraficaClienti;
    }

}
