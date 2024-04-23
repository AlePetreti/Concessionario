package concessionario;

import concessionario.controller.AnagraficaClientiController;
import concessionario.controller.ConcessionarioController;
import concessionario.controller.GestioneAutoController;
import concessionario.controller.PreventivoController;
import concessionario.model.Concessionario;
import concessionario.model.FactoryPrezzo;
import concessionario.model.Listino;
import concessionario.model.RegistroVendite;
import concessionario.model.automobile.FactoryAutomobile;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.FactoryAnagraficaFile;
import concessionario.model.cliente.FactoryCliente;
import concessionario.view.anagrafica.AnagraficaClientiView;
import concessionario.view.anagrafica.AnagraficaClientiViewImpl;
import concessionario.view.anagrafica.AnagraficaClientiViewObserver;
import concessionario.view.auto.GestioneAutoView;
import concessionario.view.auto.GestioneAutoViewImpl;
import concessionario.view.auto.GestioneAutoViewObserver;
import concessionario.view.concessionario.ConcessionarioView;
import concessionario.view.concessionario.ConcessionarioViewImpl;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.preventivo.PreventivoView;
import concessionario.view.preventivo.PreventivoViewImpl;
import concessionario.view.LeasingAutoView;
import concessionario.view.LeasingAutoViewImpl;
import concessionario.controller.LeasingController;

public class Main {

    public static void main(String[] args) throws Exception {


    AnagraficaClienti anagrafica = new AnagraficaClienti();
    FactoryCliente clientefactory = new FactoryCliente();
    FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
    FactoryAutomobile factoryAutomobili = new FactoryAutomobile();
    Listino listinoAuto = new Listino();
    RegistroVendite registroVendite = new RegistroVendite();
    Concessionario concessionario = new Concessionario(listinoAuto, anagrafica, registroVendite);

    // VIEW
    final ConcessionarioView view = new ConcessionarioViewImpl();
    final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
    final GestioneAutoView viewGestioneAuto = new GestioneAutoViewImpl();
    final LeasingAutoView viewLeasingAuto = new LeasingAutoViewImpl(new FactoryAutomobiliNoleggio());
    final PreventivoView viewPreventivo = new PreventivoViewImpl();
    final ConcessionarioViewObserver controller = new ConcessionarioController(view, viewAnagrafica, viewGestioneAuto, registroVendite, viewLeasingAuto);
    final AnagraficaClientiViewObserver controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
    final PreventivoController controllerPreventivo = new PreventivoController(viewPreventivo, anagrafica, concessionario);
    final GestioneAutoViewObserver controllerGestioneAuto = new GestioneAutoController(viewGestioneAuto, listinoAuto, controllerPreventivo);
    final LeasingController leasingController = new LeasingController(viewLeasingAuto, new FactoryAutomobiliNoleggio());
    view.show();

    
    // Inizializzazione di automobili e clienti
    inizializzaAutomobili(listinoAuto, factoryAutomobili);
    inizializzaClienti(anagrafica, anagraficaFile, clientefactory);
}

private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobile factoryAutomobili) {
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



