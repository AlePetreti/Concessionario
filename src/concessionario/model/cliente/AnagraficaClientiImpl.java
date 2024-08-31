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
        if (!ePresente(cliente)) {
            listaClienti.add(cliente);
            return true;
        }
        return false;
    }

    // Usa la strategia di ricerca per codice fiscale
    @Override
    public Cliente cercaCliente(String codiceFiscale, StrategiaDiRicerca strategia) {
        List<Cliente> risultato = strategia.cerca(listaClienti, codiceFiscale);
        return risultato.isEmpty() ? null : risultato.get(0);
    }

    // Usa la strategia di ricerca per nome o cognome
    @Override
    public List<Cliente> cercaClienti(String parolaChiave, StrategiaDiRicerca strategia) {
        return strategia.cerca(listaClienti, parolaChiave);
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
