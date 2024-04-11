package concessionario.controller;

import concessionario.model.Listino;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;
import concessionario.view.GestioneAutoView;

public class GestioneAutoController implements ConcessionarioViewObserver{

    private GestioneAutoView view;
    private Listino listinoAuto;


    public GestioneAutoController(GestioneAutoView view, Listino listino) {
        this.view = view;
        this.listinoAuto = listino;
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case GESTIONE_AUTO_APERTA:
                view.mostraListino(listinoAuto.getListino());
            break;
            default:
            break;
        }
    }
    
}
