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
    public Cliente cercaCliente(String criterio, StrategiaDiRicerca strategia);

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
    boolean ePresente(Cliente cliente);

}