package concessionario.controller;

import java.util.Collections;
import java.util.List;

import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;
import concessionario.model.cliente.StrategiaRicercaPerCf;
import concessionario.model.cliente.StrategiaRicercaPerNomeOCognome;
import concessionario.view.anagrafica.AnagraficaClientiView;
import concessionario.view.anagrafica.AnagraficaClientiViewObserver;
import concessionario.view.anagrafica.EventoAnagrafica;

public class AnagraficaClientiController implements AnagraficaClientiViewObserver {

    private final AnagraficaClientiView view;
    private final AnagraficaClienti anagraficaClienti;

    public AnagraficaClientiController(AnagraficaClientiView view, AnagraficaClienti anagraficaClienti) {
        this.view = view;
        this.anagraficaClienti = anagraficaClienti;
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(EventoAnagrafica e) {
        switch (e) {
            case ANAGRAFICA_CLIENTI_APERTA:
                view.mostraListaClienti(anagraficaClienti.getClienti());
                break;
            case REGISTRA_CLIENTI:
                double redditoAnnuale = Double.parseDouble(view.getRedditoAnnualeInserito());
                int numeroPorte = Integer.parseInt(view.getNumeroPorteInserito());
                Cliente nuovoCliente = new Cliente.Builder()
                    .nome(view.getNomeInserito())
                    .cognome(view.getCognomeInserito())
                    .email(view.getEmailInserita())
                    .telefono(view.getTelefonoInserito())
                    .codiceFiscale(view.getCfInserito())
                    .redditoAnnuale(redditoAnnuale)
                    .preferenzeAuto(view.getPreferenzeAutoInserita())
                    .preferenzeNumeroPorte(numeroPorte)
                    .preferenzeAlimentazione(view.getAlimentazioneInserita())
                    .build();

                anagraficaClienti.registraCliente(nuovoCliente);
                view.mostraListaClienti(anagraficaClienti.getClienti());
                break;
            case CERCA_CLIENTI:
                // Usa la strategia di ricerca per nome o cognome
                List<Cliente> clientiTrovati = anagraficaClienti.cercaClienti(view.getParolaChiave(), new StrategiaRicercaPerNomeOCognome());
                view.mostraListaClienti(clientiTrovati);
                break;
            case CERCA_CLIENTI_CF: {
                // Usa la strategia di ricerca per codice fiscale
                Cliente clienteTrovato = anagraficaClienti.cercaCliente(
                    view.getParolaChiave(), new StrategiaRicercaPerCf());
                if (clienteTrovato != null) {
                    view.mostraListaClienti(Collections.singletonList(clienteTrovato));
                } else {
                    view.mostraListaClienti(Collections.emptyList());
                }
                break;
            }
            default:
                break;
        }
    }
}
