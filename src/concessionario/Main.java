package concessionario;

import concessionario.controller.AnagraficaClientiController;
import concessionario.controller.ConcessionarioController;
import concessionario.controller.GestioneAutoController;
import concessionario.controller.PreventivoController;
import concessionario.model.automobile.FactoryAutomobile;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.AnagraficaClientiImpl;
import concessionario.model.cliente.FactoryCliente;
import concessionario.model.generatorePrezzo.GeneratorePrezzo;
import concessionario.model.listino.Listino;
import concessionario.model.repartoVendita.RegistroVendite;
import concessionario.model.repartoVendita.ServizioVendite;
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

        // Creazione delle istanze utilizzando il Singleton Pattern
        FactoryCliente factoryCliente = FactoryCliente.getInstance();
        FactoryAutomobile factoryAutomobile = new FactoryAutomobile();
        
        // Inizializzazione dei modelli
        AnagraficaClienti anagrafica = new AnagraficaClientiImpl();
        Listino listinoAuto = new Listino();
        RegistroVendite registroVendite = new RegistroVendite();
        ServizioVendite concessionario = new ServizioVendite(listinoAuto, anagrafica, registroVendite);

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
        inizializzaAutomobili(listinoAuto, factoryAutomobile);
        inizializzaClienti(anagrafica, factoryCliente);
    }

    private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobile factoryAutomobili) {
        for (int i = 0; i < 10; i++) {
            listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom(), GeneratorePrezzo.generaPrezzo());
        }
    }

    private static void inizializzaClienti(AnagraficaClienti anagrafica, FactoryCliente factoryCliente) {
        for (int i = 0; i < 10; i++) {
            anagrafica.registraCliente(factoryCliente.creaClienteRandom());
        }
    }
}
