package concessionario.controller;

import java.util.List;

import concessionario.model.autonoleggio.Autonoleggio;
import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;

public class AutonoleggioController {
    private Autonoleggio autonoleggio;

    public AutonoleggioController(Autonoleggio autonoleggio) {
        this.autonoleggio = autonoleggio;
    }

    public List<AutomobileNoleggio> getAutomobili() {
        return autonoleggio.getAutomobili();
    }

    public void noleggiaAuto(AutomobileNoleggio auto, int giorni) {
        autonoleggio.noleggiaAuto(auto, giorni);
    }
}


