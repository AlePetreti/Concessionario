package concessionario.controller;

import concessionario.model.Concessionario;
import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.view.preventivo.EventoPreventivo;
import concessionario.view.preventivo.PreventivoView;
import concessionario.view.preventivo.PreventivoViewObserver;

public class PreventivoController implements PreventivoViewObserver     {

    private final PreventivoView view;
    private final AnagraficaClienti anagraficaClienti;
    private final Concessionario concessionario;

    public PreventivoController(PreventivoView view, AnagraficaClienti anagraficaClienti, Concessionario concessionario) {
        this.view = view;
        this.anagraficaClienti = anagraficaClienti;
        this.concessionario = concessionario;
        this.view.addObserver(this);

    }

    @Override
    public void eventNotified(EventoPreventivo e) {
        switch(e) {
            case CONCLUDI_VENDITA:
            
            break;
            default:
            break;

        }
    }

    public void inizializzaPreventivo(Automobile automobile) {
        view.mostraCreaPreventivo();
        view.mostraListaClienti(anagraficaClienti.getClienti());
    }
    
}
