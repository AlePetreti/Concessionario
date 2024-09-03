package concessionario.controller;

import java.util.List;
import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;
import concessionario.view.leasing.LeasingAutoView;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.concessionario.EventoConcessionario;

public class LeasingController implements ConcessionarioViewObserver {

    private LeasingAutoView leasingAutoView;
    private FactoryAutomobiliNoleggio factoryAutomobiliNoleggio;

    public LeasingController(LeasingAutoView leasingAutoView, FactoryAutomobiliNoleggio factoryAutomobiliNoleggio) {
        this.leasingAutoView = leasingAutoView;
        this.factoryAutomobiliNoleggio = factoryAutomobiliNoleggio;
    }

    @Override
    public void eventNotified(EventoConcessionario e) {
        switch (e) {
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








