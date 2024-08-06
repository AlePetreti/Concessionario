package concessionario.controller;

import java.util.Optional;

import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.repartoVendita.Preventivo;
import concessionario.model.repartoVendita.ServizioVendite;
import concessionario.view.preventivo.EventoPreventivo;
import concessionario.view.preventivo.PreventivoView;
import concessionario.view.preventivo.PreventivoViewObserver;

public class PreventivoController implements PreventivoViewObserver     {

    private final PreventivoView view;
    private final AnagraficaClienti anagraficaClienti;
    private final ServizioVendite concessionario;
    private Optional<Automobile> autoSelezionata;

    public PreventivoController(PreventivoView view, AnagraficaClienti anagraficaClienti, ServizioVendite concessionario) {
        this.view = view;
        this.anagraficaClienti = anagraficaClienti;
        this.concessionario = concessionario;
        this.autoSelezionata = Optional.empty();
        this.view.addObserver(this);

    }

    @Override
    public void eventNotified(EventoPreventivo e) {
        switch(e) {
            case CONCLUDI_VENDITA:
                Preventivo preventivo = concessionario.generaPreventivo(autoSelezionata.get(), view.getClienteSelezionato(anagraficaClienti.getClienti()));
                concessionario.vendiAuto(preventivo);
            break;
            default:
            break;

        }
    }

    public void inizializzaPreventivo(Automobile automobile) {
        view.mostraCreaPreventivo();
        view.mostraListaClienti(anagraficaClienti.getClienti());
        view.mostraSpecificheAutoPreventivo(automobile);
        this.autoSelezionata = Optional.of(automobile);
    }
    
}
