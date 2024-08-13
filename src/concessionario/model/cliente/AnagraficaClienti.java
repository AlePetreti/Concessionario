package concessionario.model.cliente;

import java.util.List;

public interface AnagraficaClienti {

    // aggiungi cliente alla lista 
    boolean registraCliente(Cliente cliente);

    /**
     * ricerca un cliente in base al codice fiscale
     * @param cf
     * @return cliente altrimenti null
     */
    Cliente cercaCliente(String cf);

    /**
     * cerca tutti i clienti che hanno il nome o il cognome uguali a quelli passati
     * @param nome
     * @param cognome
     * @return lista di clienti trovati altrimenti lista vuota
     */
    List<Cliente> cercaClienti(String nome, String cognome);

    /**
     * cerca tutti i clienti che contengono la parola chiave
     * @param parolaChiave
     * @return lista di clienti trovati altrimenti lista vuota
     */
    List<Cliente> cercaClienti(String parolaChiave);

    /**
     * 
     * @return lista dei clienti
     */
    List<Cliente> getClienti();

    /**
     * 
     * @param cliente
     * @return TRUE se il cliente é presente nella lista dei clienti
     */
    boolean isPresent(Cliente cliente);

}