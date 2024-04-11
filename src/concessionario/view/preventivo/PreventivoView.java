package concessionario.view.preventivo;

import java.util.List;

import concessionario.model.cliente.Cliente;

public interface PreventivoView {
    
    void addObserver(PreventivoViewObserver observer);

    void removeObserver(PreventivoViewObserver observer);

    void mostraCreaPreventivo();

    //void mostraSpecificheAutoPreventivo(ElementoListino elemento);

    void mostraListaClienti(List<Cliente> cliente);

    Cliente getClienteSelezionato(List<Cliente> listaClienti);
}