package concessionario.controller;

import java.util.List;
import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;
import concessionario.view.LeasingAutoView;

public class LeasingController implements ConcessionarioViewObserver {

    private LeasingAutoView leasingAutoView;
    private FactoryAutomobiliNoleggio factoryAutomobiliNoleggio;

    public LeasingController(LeasingAutoView leasingAutoView, FactoryAutomobiliNoleggio factoryAutomobiliNoleggio) {
        this.leasingAutoView = leasingAutoView;
        this.factoryAutomobiliNoleggio = factoryAutomobiliNoleggio;
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case LEASING_AUTO:
                mostraAutoDisponibili();
                break;
            default:
                break;
        }
    }

    private void mostraAutoDisponibili() {
        List<AutomobileNoleggio> autoDisponibili = factoryAutomobiliNoleggio.creaAutoRandom();
        leasingAutoView.mostraAuto(autoDisponibili);
    }

}








