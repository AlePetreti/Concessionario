package concessionario.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.View;

import concessionario.model.cliente.AnagraficaClienti;
import concessionario.model.cliente.Cliente;
import concessionario.view.ConcessionarioView;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;

public class ConcessionarioController implements ConcessionarioViewObserver {

    private ConcessionarioView view;
    private AnagraficaClienti anagraficaClienti;

    public ConcessionarioController(ConcessionarioView view, AnagraficaClienti anagraficaClienti) {
        this.view = view;
        this.anagraficaClienti = anagraficaClienti;
        this.view.addObserver(this);
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case ANAGRAFICA_CLIENTI:
                view.mostraAnagraficaClienti();
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
        }
    }
    
}
