package concessionario.controller;

import java.util.Optional;

import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;
import concessionario.model.repartoVendita.Preventivo;
import concessionario.model.repartoVendita.ServizioVendite;
import concessionario.view.preventivo.EventoPreventivo;
import concessionario.view.preventivo.PreventivoView;
import concessionario.view.preventivo.PreventivoViewObserver;

public class PreventivoController implements PreventivoViewObserver {

    private final PreventivoView view;
    private final AnagraficaClienti anagraficaClienti;
    private final ServizioVendite servizioVendite;
    private Optional<Automobile> autoSelezionata;

    public PreventivoController(PreventivoView view, AnagraficaClienti anagraficaClienti, ServizioVendite servizioVendite) {
        this.view = view;
        this.anagraficaClienti = anagraficaClienti;
        this.servizioVendite = servizioVendite;
        this.autoSelezionata = Optional.empty();
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(EventoPreventivo e) {
        switch (e) {
            case CONCLUDI_VENDITA:
                Optional<Preventivo> preventivo = servizioVendite.generaPreventivo(
                    autoSelezionata.orElse(null), 
                    view.getClienteSelezionato(anagraficaClienti.getClienti())
                );
                preventivo.ifPresent(servizioVendite::vendiAuto);
                break;
            default:
                break;
        }
    }

    public void inizializzaPreventivo(Automobile automobile) {
        view.mostraCreaPreventivo();
        view.mostraListaClienti(anagraficaClienti.getClienti());
        view.mostraSpecificheAutoPreventivo(automobile);
        double prezzoAuto = servizioVendite.calcolaPrezzoAuto(automobile);
        view.mostraPrezzoTotale(prezzoAuto);
        this.autoSelezionata = Optional.of(automobile);
    }

    public void inizializzaPreventivo(Automobile automobile, Cliente cliente) {
        view.mostraCreaPreventivo();
        view.mostraListaClienti(anagraficaClienti.getClienti());
        view.mostraSpecificheAutoPreventivo(automobile);
        view.selezionaCliente(cliente);
        double prezzoAuto = servizioVendite.calcolaPrezzoAuto(automobile);
        view.mostraPrezzoTotale(prezzoAuto);
        this.autoSelezionata = Optional.of(automobile);
    }
}
