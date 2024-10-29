package concessionario.view.preventivo;

import java.util.List;

import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.Cliente;

public interface PreventivoView {
    
    void addObserver(PreventivoViewObserver observer);

    void removeObserver(PreventivoViewObserver observer);

    void mostraCreaPreventivo();

    void mostraSpecificheAutoPreventivo(Automobile auto);

    void mostraPrezzoTotale(double prezzo);

    void mostraListaClienti(List<Cliente> cliente);

    void selezionaCliente(Cliente cliente);

    Cliente getClienteSelezionato(List<Cliente> listaClienti);
}