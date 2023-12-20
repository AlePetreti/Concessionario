package concessionario;

import concessionario.controller.AnagraficaClientiController;
import concessionario.controller.ConcessionarioController;
import concessionario.model.CercatoreAuto;
import concessionario.model.Concessionario;
import concessionario.model.Filtro;
import concessionario.model.Listino;
import concessionario.model.VenditorePrivato;
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

public class Main {
    public static void main(String[] args) throws Exception {


    AnagraficaClienti anagrafica = new AnagraficaClienti();
    FactoryCliente clientefactory = new FactoryCliente();
    FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
    FactoryAutomobili factoryAutomobili = new FactoryAutomobili();
    Listino listinoAuto = new Listino();
    Concessionario concessionario = new Concessionario(listinoAuto);
    Filtro filtro = new Filtro();
    CercatoreAuto cercatoreAuto = new CercatoreAuto();

    Cliente cliente = new Cliente("porco", "dio", null, null, null);
    Automobile autoUsata  = new Automobile("ClasseA", "MERCEDES", 0, 5, 0, 0, StatoMacchina.USATO);
    VenditorePrivato privato = new VenditorePrivato(cliente, autoUsata, 2000);


    // VIEW
    final ConcessionarioView view = new ConcessionarioViewImpl();
    final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
    final ConcessionarioViewObserver controller = new ConcessionarioController(view, viewAnagrafica);
    final AnagraficaClientiController controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
    view.show();


    // Inizializzazione di automobili e clienti
    inizializzaAutomobili(listinoAuto, factoryAutomobili);
    inizializzaClienti(anagrafica, anagraficaFile, clientefactory);
}

private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobili factoryAutomobili) {
    for(int i = 0; i < 2; i++) {
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