package concessionario.controller;

import concessionario.view.ConcessionarioView;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;

public class ConcessionarioController implements ConcessionarioViewObserver {

    private ConcessionarioView view;

    public ConcessionarioController(ConcessionarioView view) {
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case GESTIONE_CLIENTI:
                view.mostraGestioneClienti();
            break;
            case REGISTRA_CLIENTI:
            System.out.println("porco dio funziona");
            break;
        }
    }
    
}
