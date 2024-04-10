package concessionario;

import concessionario.controller.AnagraficaClientiController;
import concessionario.controller.ConcessionarioController;
import concessionario.controller.GestioneAutoController;
import concessionario.model.CercatoreAuto;
import concessionario.model.Concessionario;
import concessionario.model.FactoryPrezzo;
import concessionario.model.Filtro;
import concessionario.model.Listino;
import concessionario.model.RegistroVendite;
import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.FactoryAutomobili;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;
import concessionario.model.cliente.FactoryAnagraficaFile;
import concessionario.model.cliente.FactoryCliente;
import concessionario.view.AnagraficaClientiView;
import concessionario.view.AnagraficaClientiViewImpl;
import concessionario.view.ConcessionarioView;
import concessionario.view.ConcessionarioViewImpl;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.GestioneAutoViewImpl;
import concessionario.view.GestioneAutoView;

public class Main {
    public static void main(String[] args) throws Exception {


    AnagraficaClienti anagrafica = new AnagraficaClienti();
    FactoryCliente clientefactory = new FactoryCliente();
    FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
    FactoryAutomobili factoryAutomobili = new FactoryAutomobili();
    FactoryPrezzo factoryPrezzo= new FactoryPrezzo();
    Listino listinoAuto = new Listino();
    RegistroVendite registroVendite = new RegistroVendite();
    Concessionario concessionario = new Concessionario(listinoAuto, anagrafica, registroVendite);
    Filtro filtro = new Filtro();
    CercatoreAuto cercatoreAuto = new CercatoreAuto();

    // VIEW
    final ConcessionarioView view = new ConcessionarioViewImpl();
    final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
    final GestioneAutoView viewGestioneAuto = new GestioneAutoViewImpl();
    final ConcessionarioViewObserver controller = new ConcessionarioController(view, viewAnagrafica, viewGestioneAuto, registroVendite);
    final AnagraficaClientiController controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
    final GestioneAutoController controllerGestioneAuto = new GestioneAutoController(viewGestioneAuto, listinoAuto, anagrafica, concessionario);
    view.show();


    // Inizializzazione di automobili e clienti
    inizializzaAutomobili(listinoAuto, factoryAutomobili);
    inizializzaClienti(anagrafica, anagraficaFile, clientefactory);
}

private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobili factoryAutomobili) {
    for(int i = 0; i < 10; i++) {
        listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom(), FactoryPrezzo.generaPrezzo());
    }
}

private static void inizializzaClienti(AnagraficaClienti anagrafica, FactoryAnagraficaFile anagraficaFile, FactoryCliente clientefactory) {
    for (int i = 0; i < 10; i++) {
        anagrafica.registraCliente(clientefactory.creaClienteRandom());
    }
}
}