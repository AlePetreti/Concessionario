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
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.FactoryAnagraficaFile;
import concessionario.model.cliente.FactoryCliente;
import concessionario.view.anagraficaView.AnagraficaClientiView;
import concessionario.view.anagraficaView.AnagraficaClientiViewImpl;
import concessionario.view.anagraficaView.AnagraficaClientiViewObserver;
import concessionario.view.concessionario.ConcessionarioView;
import concessionario.view.concessionario.ConcessionarioViewImpl;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.gestioneAuto.GestioneAutoView;
import concessionario.view.gestioneAuto.GestioneAutoViewImpl;
import concessionario.view.gestioneAuto.GestioneAutoViewObserver;
import concessionario.view.preventivo.PreventivoView;
import concessionario.view.preventivo.PreventivoViewImpl;
import concessionario.view.preventivo.PreventivoViewObserver;

public class Main {
    public static void main(String[] args) throws Exception {


    AnagraficaClienti anagrafica = new AnagraficaClienti();
    FactoryCliente clientefactory = new FactoryCliente();
    FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
    FactoryAutomobile factoryAutomobili = new FactoryAutomobile();
    FactoryPrezzo factoryPrezzo= new FactoryPrezzo();
    Listino listinoAuto = new Listino();
    RegistroVendite registroVendite = new RegistroVendite();
    Concessionario concessionario = new Concessionario(listinoAuto, anagrafica, registroVendite);

    // VIEW
    final ConcessionarioView view = new ConcessionarioViewImpl();
    final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
    final GestioneAutoView viewGestioneAuto = new GestioneAutoViewImpl();
    final PreventivoView viewPreventivo = new PreventivoViewImpl();
    final ConcessionarioViewObserver controller = new ConcessionarioController(view, viewAnagrafica, viewGestioneAuto, registroVendite);
    final AnagraficaClientiViewObserver controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
    final PreventivoController controllerPreventivo = new PreventivoController(viewPreventivo, anagrafica, concessionario);
    final GestioneAutoViewObserver controllerGestioneAuto = new GestioneAutoController(viewGestioneAuto, listinoAuto, anagrafica, concessionario, controllerPreventivo);
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