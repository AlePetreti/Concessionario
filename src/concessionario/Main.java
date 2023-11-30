package concessionario;

import java.util.List;
import java.util.ArrayList;

import concessionario.automobile.Automobile;
import concessionario.automobile.FactoryAutomobili;
import concessionario.automobile.StatoMacchina;
import concessionario.cliente.AnagraficaClienti;
import concessionario.cliente.Cliente;
import concessionario.cliente.FactoryAnagraficaFile;
import concessionario.cliente.FactoryCliente;

public class Main {
    public static void main(String[] args) throws Exception {

        AnagraficaClienti anagrafica = new AnagraficaClienti();
        FactoryCliente clientefactory = new FactoryCliente();
        FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
        FactoryAutomobili factoryAutomobili = new FactoryAutomobili();
        Listino listinoAuto = new Listino();
        
        for(int i = 0; i < 10; i++) {
            listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom1(), 0);
        }
        listinoAuto.aggiungiAuto(new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, null), 0);
        
        // listinoAuto.stampaListino(factoryAutomobili.creaAutoRandom());

        Filtro filtro = new Filtro();
        filtro.setMarca("MERCEDES");

        CercatoreAuto cercatoreAuto = new CercatoreAuto();
        List<Automobile> autoTrovate = cercatoreAuto.cercaAuto(filtro, listinoAuto);
        System.out.println(autoTrovate);

        // creazione clienti e prove sulla ricerca 
       /*List<Cliente> clienti;

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
        
        anagrafica = anagraficaFile.creaAnagraficaClienti(); */

        
        
    }
}
