package concessionario.controller;

import concessionario.model.autonoleggio.Autonoleggio;
import concessionario.view.AutonoleggioViewImpl;

public class AutonoleggioController {

    private AutonoleggioViewImpl view;

    public AutonoleggioController(Autonoleggio autonoleggio, AutonoleggioViewImpl view) {
        this.view = view;
    }

    public void gestisciAutonoleggio() {
        view.AutonoleggioViewImpl();
    }

    // Altri metodi del controller
}


