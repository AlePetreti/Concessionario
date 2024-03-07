package concessionario.controller;

import concessionario.view.AnagraficaClientiView;
import concessionario.view.ConcessionarioView;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;
import concessionario.view.GestioneAutoView;
import concessionario.view.LeasingAutoView; 

public class ConcessionarioController implements ConcessionarioViewObserver {

    private ConcessionarioView view;
    private AnagraficaClientiView viewAnagrafica;
    private GestioneAutoView viewGestioneAuto;
    private LeasingAutoView viewLeasingAuto;

    public ConcessionarioController(ConcessionarioView view, AnagraficaClientiView viewAnagrafica, GestioneAutoView viewGestioneAuto, LeasingAutoView viewLeasingAuto) {
        this.view = view;
        this.viewAnagrafica = viewAnagrafica;
        this.viewGestioneAuto = viewGestioneAuto;
        this.viewLeasingAuto = viewLeasingAuto;
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
            case LEASING_AUTO:
                viewLeasingAuto.mostraAuto(null);
                break;
            default:
                break;
        }
    }
}
