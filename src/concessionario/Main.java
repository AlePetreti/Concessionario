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
import concessionario.view.anagrafica.AnagraficaClientiView;
import concessionario.view.anagrafica.AnagraficaClientiViewImpl;
import concessionario.view.anagrafica.AnagraficaClientiViewObserver;
import concessionario.view.auto.GestioneAutoView;
import concessionario.view.auto.GestioneAutoViewImpl;
import concessionario.view.auto.GestioneAutoViewObserver;
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

        FactoryCliente factoryCliente = FactoryCliente.getInstance();
        FactoryAutomobile factoryAutomobile = new FactoryAutomobile();
        
        AnagraficaClienti anagrafica = new AnagraficaClientiImpl();
        Listino listinoAuto = new Listino();
        Listino listinoUsato = new Listino();
        RegistroVendite registroVendite = new RegistroVendite();
        ServizioVendite concessionario = new ServizioVendite(listinoAuto, listinoUsato, anagrafica, registroVendite);
        OfficinaModel officina = new OfficinaModel(listinoUsato);
        
        // VIEW
        final OfficinaView officinaView = new OfficinaView();
        final ConcessionarioView view = new ConcessionarioViewImpl();
        final AnagraficaClientiView viewAnagrafica = new AnagraficaClientiViewImpl();
        final GestioneAutoView viewGestioneAuto = new GestioneAutoViewImpl();
        final LeasingAutoView viewLeasingAuto = new LeasingAutoViewImpl(new FactoryAutomobiliNoleggio());
        final PreventivoView viewPreventivo = new PreventivoViewImpl();
        final ConcessionarioViewObserver controller = new ConcessionarioController(view, viewAnagrafica, viewGestioneAuto, registroVendite, viewLeasingAuto, officinaView, officina);
        final AnagraficaClientiViewObserver controllerAnagrafica = new AnagraficaClientiController(viewAnagrafica, anagrafica);
        final PreventivoController controllerPreventivo = new PreventivoController(viewPreventivo, anagrafica, concessionario);
        final GestioneAutoController controllerGestioneAuto = new GestioneAutoController(viewGestioneAuto, listinoAuto, listinoUsato, controllerPreventivo);
        final LeasingController leasingController = new LeasingController(viewLeasingAuto, new FactoryAutomobiliNoleggio());
        final OfficinaController officinaController = new OfficinaController(officina, officinaView);
        view.show();

        // Inizializzazione di automobili e clienti
        inizializzaAutomobili(listinoAuto, factoryAutomobile);
        inizializzaAutoUsate(listinoUsato, factoryAutomobile);
        inizializzaClienti(anagrafica, factoryCliente);
    }

    private static void inizializzaAutomobili(Listino listinoAuto, FactoryAutomobile factoryAutomobili) {
        for (int i = 0; i < 10; i++) {
            listinoAuto.aggiungiAuto(factoryAutomobili.creaAutoRandom(), GeneratorePrezzo.generaPrezzo());
        }
    }

    private static void inizializzaAutoUsate(Listino listinoUsato, FactoryAutomobile factoryAutomobile) {
        for (int i = 0; i < 10; i++) {
            listinoUsato.aggiungiAuto(factoryAutomobile.creaAutoRandomUsata(), GeneratorePrezzo.generaPrezzo());
        }
    }

    private static void inizializzaClienti(AnagraficaClienti anagrafica, FactoryCliente factoryCliente) {
        for (int i = 0; i < 10; i++) {
            anagrafica.registraCliente(factoryCliente.creaClienteRandom());
        }
    }
}
