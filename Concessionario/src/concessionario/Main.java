package concessionario;

import concessionario.controller.AnagraficaClientiController;
import concessionario.controller.ConcessionarioController;
import concessionario.controller.GestioneAutoController;
import concessionario.controller.LeasingController;
import concessionario.controller.OfficinaController;
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
import concessionario.model.officina.OfficinaModel;
import concessionario.view.anagrafica.AnagraficaClientiView;
import concessionario.view.anagrafica.AnagraficaClientiViewImpl;
import concessionario.view.anagrafica.AnagraficaClientiViewObserver;
import concessionario.view.auto.GestioneAutoView;
import concessionario.view.auto.GestioneAutoViewImpl;
import concessionario.view.auto.GestioneAutoViewObserver;
import concessionario.view.concessionario.ConcessionarioView;
import concessionario.view.concessionario.ConcessionarioViewImpl;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.officina.OfficinaView;
import concessionario.view.preventivo.PreventivoView;
import concessionario.view.preventivo.PreventivoViewImpl;
import concessionario.view.LeasingAutoView;
import concessionario.view.LeasingAutoViewImpl;

public class Main {

    public static void main(String[] args) throws Exception {

        // Modelli
        AnagraficaClienti anagrafica = new AnagraficaClienti();
        FactoryCliente clientefactory = new FactoryCliente();
        FactoryAnagraficaFile anagraficaFile = new FactoryAnagraficaFile("Elenco_clienti.txt");
        FactoryAutomobile factoryAutomobili = new FactoryAutomobile();
        Listino listinoAuto = new Listino();
        Listino listinoAutoUsate = new Listino();
        RegistroVendite registroVendite = new RegistroVendite();
        Concessionario concessionario = new Concessionario(listinoAuto, anagrafica, registroVendite);
        OfficinaModel officina = new OfficinaModel(listinoAutoUsate);

        // Viste
        OfficinaView officinaView = new OfficinaView();
        final ConcessionarioView view = new ConcessionarioViewImpl();
        final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
        final GestioneAutoView viewGestioneAuto = new GestioneAutoViewImpl();
        final LeasingAutoView viewLeasingAuto = new LeasingAutoViewImpl(new FactoryAutomobiliNoleggio());
        final PreventivoView viewPreventivo = new PreventivoViewImpl();

        // Controller
        final ConcessionarioViewObserver controller = new ConcessionarioController(
            view, viewAnagrafica, viewGestioneAuto, registroVendite, viewLeasingAuto, officinaView, officina
        );
        final AnagraficaClientiViewObserver controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
        final PreventivoController controllerPreventivo = new PreventivoController(viewPreventivo, anagrafica, concessionario);
        final GestioneAutoViewObserver controllerGestioneAuto = new GestioneAutoController(viewGestioneAuto, listinoAuto, controllerPreventivo);
        final LeasingController leasingController = new LeasingController(viewLeasingAuto, new FactoryAutomobiliNoleggio());
        final OfficinaController controllerOfficina = new OfficinaController(officina, officinaView);

        // Mostra la vista principale
        view.show();

        // Inizializzazione di automobili e clienti
        inizializzaAutomobili(listinoAuto, factoryAutomobili);
        inizializzaClienti(anagrafica, anagraficaFile, clientefactory);
        inizializzaAutoUsate(listinoAutoUsate, factoryAutomobili);
    }

    private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobile factoryAutomobili) {
        for (int i = 0; i < 10; i++) {
            listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom(), FactoryPrezzo.generaPrezzo());
        }
    }

    private static void inizializzaAutoUsate(Listino listinoUsato, FactoryAutomobile factoryAutomobile) {
        for (int i = 0; i < 10; i++) {
            listinoUsato.aggiungiAuto(factoryAutomobile.creaAutoRandomUsata(), FactoryPrezzo.generaPrezzo());
        }
    }

    private static void inizializzaClienti(AnagraficaClienti anagrafica, FactoryAnagraficaFile anagraficaFile, FactoryCliente clientefactory) {
        for (int i = 0; i < 10; i++) {
            anagrafica.registraCliente(clientefactory.creaClienteRandom());
        }
    }
}
