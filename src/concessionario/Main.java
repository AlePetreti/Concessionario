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
import concessionario.model.officina.OfficinaModel;
import concessionario.model.repartoVendita.RegistroVendite;
import concessionario.model.repartoVendita.ServizioVendite;
import concessionario.model.suggerimenti.Suggeritore;
import concessionario.model.suggerimenti.SuggeritoreAuto;
import concessionario.view.anagrafica.AnagraficaClientiView;
import concessionario.view.anagrafica.AnagraficaClientiViewImpl;
import concessionario.view.anagrafica.AnagraficaClientiViewObserver;
import concessionario.view.auto.GestioneAutoView;
import concessionario.view.auto.GestioneAutoViewImpl;
import concessionario.view.concessionario.ConcessionarioView;
import concessionario.view.concessionario.ConcessionarioViewImpl;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.leasing.LeasingAutoView;
import concessionario.view.leasing.LeasingAutoViewImpl;
import concessionario.view.officina.OfficinaView;
import concessionario.view.preventivo.PreventivoView;
import concessionario.view.preventivo.PreventivoViewImpl;
import concessionario.controller.LeasingController;
import concessionario.controller.OfficinaController;

public class Main {

    public static void main(String[] args) throws Exception {

        // Creazione delle factory
        FactoryCliente factoryCliente = new FactoryCliente();
        FactoryAutomobile factoryAutomobile = new FactoryAutomobile();
        
        // Creazione dei modelli per anagrafica, listini e vendite
        AnagraficaClienti anagrafica = new AnagraficaClientiImpl();
        Listino listinoAuto = new Listino();
        Listino listinoUsato = new Listino();
        RegistroVendite registroVendite = new RegistroVendite();
        ServizioVendite concessionario = new ServizioVendite(listinoAuto, listinoUsato, anagrafica, registroVendite);
        OfficinaModel officina = new OfficinaModel(listinoUsato);
        Suggeritore suggeritoreAuto = new SuggeritoreAuto(listinoAuto, listinoUsato);
        
        // Creazione delle viste
        final OfficinaView officinaView = new OfficinaView();
        final ConcessionarioView view = new ConcessionarioViewImpl();
        final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
        final GestioneAutoView viewGestioneAuto = new GestioneAutoViewImpl();
        final LeasingAutoView viewLeasingAuto = new LeasingAutoViewImpl(new FactoryAutomobiliNoleggio());
        final PreventivoView viewPreventivo = new PreventivoViewImpl();
        
        // Creazione dei controller
        final ConcessionarioViewObserver controller = new ConcessionarioController(view, viewAnagrafica, viewGestioneAuto, registroVendite, viewLeasingAuto, officinaView, officina);
        final AnagraficaClientiViewObserver controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
        final PreventivoController controllerPreventivo = new PreventivoController(viewPreventivo, anagrafica, concessionario);
        final GestioneAutoController controllerGestioneAuto = new GestioneAutoController(viewGestioneAuto, listinoAuto, listinoUsato, anagrafica, suggeritoreAuto ,controllerPreventivo);
        final LeasingController leasingController = new LeasingController(viewLeasingAuto, new FactoryAutomobiliNoleggio());
        final OfficinaController officinaController = new OfficinaController(officina, officinaView);

        // Visualizzazione dell'interfaccia principale
        view.show();

        // Inizializzazione dei dati (auto e clienti)
        inizializzaAutomobili(listinoAuto, factoryAutomobile);
        inizializzaAutoUsate(listinoUsato, factoryAutomobile);
        inizializzaClienti(anagrafica, factoryCliente);

    }

    /**
     * Inizializza una lista di automobili nuove nel listino.
     * 
     * @param listinoAuto il listino dove aggiungere le auto
     * @param factoryAutomobile la factory per generare auto casuali
     */
    private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobile factoryAutomobili) {
        for (int i = 0; i < 10; i++) {
            listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom(), GeneratorePrezzo.generaPrezzo());
        }
    }

    /**
     * Inizializza una lista di automobili usate nel listino.
     * 
     * @param listinoUsato il listino dove aggiungere le auto usate
     * @param factoryAutomobile la factory per generare auto casuali
     */
    private static void inizializzaAutoUsate(Listino listinoUsato, FactoryAutomobile factoryAutomobile) {
        for (int i = 0; i < 10; i++) {
            listinoUsato.aggiungiAuto(factoryAutomobile.creaAutoRandomUsata(), GeneratorePrezzo.generaPrezzo());
        }
    }

    /**
     * Inizializza una lista di clienti casuali nell'anagrafica clienti.
     * 
     * @param anagrafica l'anagrafica clienti
     * @param factoryCliente la factory per generare clienti casuali
     */
    private static void inizializzaClienti(AnagraficaClienti anagrafica, FactoryCliente factoryCliente) {
        for (int i = 0; i < 10; i++) {
            anagrafica.registraCliente(factoryCliente.creaClienteRandom());
        }
    }
}
