package concessionario.controller;

import concessionario.view.AnagraficaClientiView;
import concessionario.view.ConcessionarioView;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;

public class ConcessionarioController implements ConcessionarioViewObserver {

    private ConcessionarioView view;
    private AnagraficaClientiView viewAnagrafica;

    public ConcessionarioController(ConcessionarioView view, AnagraficaClientiView viewAnagrafica) {
        this.view = view;
        this.viewAnagrafica = viewAnagrafica;
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case ANAGRAFICA_CLIENTI:
                viewAnagrafica.mostraAnagraficaClienti();
            break;
            default:
            break;   
        }
    }
    
}
