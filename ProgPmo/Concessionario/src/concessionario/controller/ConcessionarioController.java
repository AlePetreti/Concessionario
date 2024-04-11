package concessionario.controller;

import concessionario.view.AnagraficaClientiView;
import concessionario.view.ConcessionarioView;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;
import concessionario.view.GestioneAutoView;

public class ConcessionarioController implements ConcessionarioViewObserver {

    private ConcessionarioView view;
    private AnagraficaClientiView viewAnagrafica;
    private GestioneAutoView viewGestioneAuto;

    public ConcessionarioController(ConcessionarioView view, AnagraficaClientiView viewAnagrafica,GestioneAutoView viewGestioneAuto ) {
        this.view = view;
        this.viewAnagrafica = viewAnagrafica;
        this.viewGestioneAuto = viewGestioneAuto;
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case ANAGRAFICA_CLIENTI:
                viewAnagrafica.mostraAnagraficaClienti();
            break;
            case GESTIONE_AUTO:
                viewGestioneAuto.mostraGestioneAuto();
            default:
            break;   
        }
    }
    
}
