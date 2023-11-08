package concessionario;
import java.util.List;

import concessionario.cliente.AnagraficaClienti;
import concessionario.cliente.Cliente;
import concessionario.cliente.FactoryAnagraficaFile;
import concessionario.cliente.FactoryCliente;

public class Main {
    public static void main(String[] args) throws Exception {

        AnagraficaClienti anagrafica = new AnagraficaClienti();
        FactoryCliente clientefactory = new FactoryCliente();
        FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");

        List<Cliente> clienti;

        for (int i = 0; i < 2; i++) {
            anagrafica.registraCliente(clientefactory.creaClienteRandom());
        }

        Cliente clienteTrovato = anagrafica.cercaCliente("");
        System.out.println(clienteTrovato);

        clienti = anagrafica.cercaClienti("Alessandro");
        System.out.println(clienti);

        clienti = anagrafica.getClienti();
        System.out.println(clienti);

        anagrafica = anagraficaFile.creaAnagraficaClienti();
        clienti = anagrafica.getClienti();
        System.out.println(clienti);
        
        anagrafica = anagraficaFile.creaAnagraficaClienti();
        
    }
}
