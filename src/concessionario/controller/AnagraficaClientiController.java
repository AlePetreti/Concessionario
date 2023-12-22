package concessionario.controller;

import java.util.Collections;
import java.util.List;

import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;
import concessionario.view.AnagraficaClientiView;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;

public class AnagraficaClientiController implements ConcessionarioViewObserver{

    private AnagraficaClientiView view;
    private AnagraficaClienti anagraficaClienti;

    public AnagraficaClientiController(AnagraficaClientiView view, AnagraficaClienti anagraficaClienti) {
        this.view = view;
        this.anagraficaClienti = anagraficaClienti;
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case ANAGRAFICA_CLIENTI_APERTA:
                view.mostraListaClienti(anagraficaClienti.getClienti());
            break; 
            case REGISTRA_CLIENTI:
                Cliente nuovoCliente = new Cliente(view.getNomeInserito(), view.getCognomeInserito(), view.getEmailInserita(),
                                                   view.getTelefonoInserito(), view.getCfInserito());
                anagraficaClienti.registraCliente(nuovoCliente);
                view.mostraListaClienti(anagraficaClienti.getClienti());
            break;
            case CERCA_CLIENTI:
                List<Cliente> clientiTrovati = anagraficaClienti.cercaClienti(view.getParolaChiave());
                view.mostraListaClienti(clientiTrovati);
            break;
            case CERCA_CLIENTI_CF: {
                Cliente clienteTrovato = anagraficaClienti.cercaCliente(view.getParolaChiave());
                if(clienteTrovato != null) {
                    view.mostraListaClienti(Collections.singletonList(clienteTrovato));
                }else {
                    view.mostraListaClienti(Collections.emptyList());
                }
                break;
            }
            default: 
            break;    
        }
    }   
}
