package concessionario;

import concessionario.controller.AnagraficaClientiController;
import concessionario.controller.ConcessionarioController;
import concessionario.controller.GestioneAutoController;
import concessionario.controller.LeasingController;
import concessionario.model.*;
import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.FactoryAutomobili;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.FactoryAnagraficaFile;
import concessionario.model.cliente.FactoryCliente;
import concessionario.model.cliente.Cliente;
import concessionario.view.*;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // Inizializzazione dei dati
        AnagraficaClienti anagrafica = new AnagraficaClienti();
        FactoryCliente clientefactory = new FactoryCliente();
        FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
        FactoryAutomobili factoryAutomobili = new FactoryAutomobili();
        Listino listinoAuto = new Listino();
        Concessionario concessionario = new Concessionario(listinoAuto);
        Filtro filtro = new Filtro();
        CercatoreAuto cercatoreAuto = new CercatoreAuto();

        // Cliente e auto per il leasing
        Cliente cliente = new Cliente("porco", "dio", null, null, null);
        Automobile autoUsata = new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, StatoMacchina.USATO);
        VenditorePrivato privato = new VenditorePrivato(cliente, autoUsata, 2000);

        // VIEW
        final ConcessionarioView view = new ConcessionarioViewImpl();
        final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
        final GestioneAutoView viewGestioneAuto = new GestioneAutoImpl();
        final LeasingAutoView viewLeasingAuto = new LeasingAutoViewImpl(new FactoryAutomobiliNoleggio());
        final ConcessionarioViewObserver controller = new ConcessionarioController(view, viewAnagrafica, viewGestioneAuto, viewLeasingAuto);
        final AnagraficaClientiController controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
        final GestioneAutoController controllerGestioneAuto = new GestioneAutoController(viewGestioneAuto, listinoAuto);
        final LeasingController leasingController = new LeasingController(viewLeasingAuto, new FactoryAutomobiliNoleggio());
        view.show();

        // Inizializzazione di automobili e clienti
        inizializzaAutomobili(listinoAuto, factoryAutomobili);
        inizializzaClienti(anagrafica, anagraficaFile, clientefactory);
    }

    private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobili factoryAutomobili) {
        for (int i = 0; i < 10; i++) {
            listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom1(), 0);
        }
        listinoAuto.aggiungiAuto(new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, StatoMacchina.NUOVO), 0);
    }

    private static void inizializzaClienti(AnagraficaClienti anagrafica, FactoryAnagraficaFile anagraficaFile, FactoryCliente clientefactory) {
        for (int i = 0; i < 10; i++) {
            anagrafica.registraCliente(clientefactory.creaClienteRandom());
        }
    }
}



