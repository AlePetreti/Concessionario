package concessionario.model.cliente;

import java.util.LinkedList;
import java.util.List;

public class AnagraficaClientiImpl implements AnagraficaClienti {

    private final List<Cliente> listaClienti;

    public AnagraficaClientiImpl() {
        listaClienti = new LinkedList<>();
    }

    @Override
    public boolean registraCliente(Cliente cliente) {
        if(!ePresente(cliente)) {
            listaClienti.add(cliente);
            return true;
        }
        return false; 
    }

    @Override
    public Cliente cercaCliente(String codiceFiscale, StrategiaDiRicerca CercaTramiteCf) {
        return CercaTramiteCf.cerca(listaClienti, codiceFiscale);
    }

    @Override
    public List<Cliente> cercaClienti(String parolaChiave) {
        List<Cliente> clientiTrovati = new LinkedList<>();
        for(Cliente e : listaClienti) {
            if(e.getNome().toLowerCase().contains(parolaChiave.toLowerCase()) || 
               e.getCognome().toLowerCase().contains(parolaChiave.toLowerCase())) { 
                clientiTrovati.add(e);
            }
        }
        return clientiTrovati;
    }

    @Override
    public List<Cliente> getClienti() {
        return new LinkedList<>(listaClienti);
    }

    @Override
    public boolean ePresente(Cliente cliente) {
        return listaClienti.contains(cliente);
    }
}
