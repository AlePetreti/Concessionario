package concessionario.cliente;
import java.util.LinkedList;
import java.util.List;

public class AnagraficaClienti {

    private LinkedList<Cliente> listaClienti;

    public AnagraficaClienti() {
        listaClienti = new LinkedList<Cliente>();

    }
    // aggiungi cliente alla lista 
    public boolean registraCliente(Cliente cliente) {
        listaClienti.add(cliente);
        return true;
    }

    /**
     * ricerca un cliente in base al codice fiscale
     * @param cf
     * @return cliente altrimenti null
     */
    public Cliente cercaCliente(String cf) {
        for(Cliente e : listaClienti) {
            if(e.getCf().equals(cf)) {
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
            if(e.getNome().equals(nome) || e.getCognome().equals(cognome)){
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
            if(e.getNome().contains(parolaChiave) || e.getCognome().contains(parolaChiave)){
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
}
