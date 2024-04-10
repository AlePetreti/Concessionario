package concessionario.controller;

import concessionario.model.RegistroVendite;
import concessionario.view.AnagraficaClientiView;
import concessionario.view.ConcessionarioView;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;
import concessionario.view.GestioneAutoView;

public class ConcessionarioController implements ConcessionarioViewObserver {

    private final ConcessionarioView view;
    private final AnagraficaClientiView viewAnagrafica;
    private final GestioneAutoView viewGestioneAuto;
    private final RegistroVendite registroVendite;

    public ConcessionarioController(ConcessionarioView view, AnagraficaClientiView viewAnagrafica,GestioneAutoView viewGestioneAuto, RegistroVendite registroVendite) {
        this.view = view;
        this.viewAnagrafica = viewAnagrafica;
        this.viewGestioneAuto = viewGestioneAuto;
        this.registroVendite = registroVendite;
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
            break;
            case AGGIORNA_VENDITE:
                view.mostraPreventiviCompletati(registroVendite.getListaPreventivi());
            default:
            break;  
        }
    }
    
}
