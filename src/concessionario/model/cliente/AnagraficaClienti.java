package concessionario.model.cliente;
import java.util.LinkedList;
import java.util.List;

public class AnagraficaClienti {

    private final List<Cliente> listaClienti;

    public AnagraficaClienti() {
        listaClienti = new LinkedList<Cliente>();

    }
    // aggiungi cliente alla lista 
    public boolean registraCliente(Cliente cliente) {
        if(!isPresent(cliente)) {
            listaClienti.add(cliente);
            return true;
        }
        return false; 
    }

    /**
     * ricerca un cliente in base al codice fiscale
     * @param cf
     * @return cliente altrimenti null
     */
    public Cliente cercaCliente(String cf) {
        for(Cliente e : listaClienti) {
            if(e.getCf().equalsIgnoreCase(cf)) {
                return e;
            }
        }
        return null;  
    }
    /**
     * cerca tutti i clienti che hanno il nome o il cognome uguali a quelli passati
     * @param nome
     * @param cognome
     * @return lista di clienti trovati altrimenti lista vuota
     */
    public List<Cliente> cercaClienti(String nome, String cognome) {
        List<Cliente> clientiTrovati = new LinkedList<Cliente>();
        for(Cliente e : listaClienti) {
            if(e.getNome().equalsIgnoreCase(nome) || e.getCognome().equalsIgnoreCase(cognome)){
                clientiTrovati.add(e);
            }
        }
        return clientiTrovati;
    }
    /**
     * cerca tutti i clienti che contengono la parola chiave
     * @param parolaChiave
     * @return lista di clienti trovati altrimenti lista vuota
     */
    public List<Cliente> cercaClienti(String parolaChiave) {
        List<Cliente> clientiTrovati = new LinkedList<Cliente>();
        for(Cliente e : listaClienti) {
            if(e.getNome().toLowerCase().contains(parolaChiave.toLowerCase()) || 
               e.getCognome().toLowerCase().contains(parolaChiave.toLowerCase())) {
                clientiTrovati.add(e);
            }
        }
        return clientiTrovati;
    }
    /**
     * 
     * @return lista dei clienti
     */
    public List<Cliente> getClienti() {
        return new LinkedList<>(listaClienti);
    }
    /**
     * 
     * @param cliente
     * @return TRUE se il cliente é presente nella lista dei clienti
     */
    public boolean isPresent(Cliente cliente) {
        if(listaClienti.contains(cliente)) {
            return true;
        }
        return false;
    }
}
