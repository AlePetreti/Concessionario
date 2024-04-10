package concessionario.view;

import java.util.List;
import java.util.Optional;

import concessionario.model.ElementoListino;
import concessionario.model.automobile.Automobile;
import concessionario.model.cliente.Cliente;

public interface GestioneAutoView {
    
    void mostraGestioneAuto();

    void mostraListino(List<ElementoListino> listino);

    ElementoListino getElementoListino();

    String getModelloAuto();

    String getMarcaAuto();

    Optional<Integer> getKmAuto();

    Optional<Integer> getNumeroPorte();

    Optional<Integer> getCilindrata();

    Optional<Double> getPrezzoMax();

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);

    void mostraCreaPreventivo();

    void mostraSpecificheAutoPreventivo(Automobile auto);

    void mostraListaClienti(List<Cliente> cliente);

    Cliente getClienteSelezionato(List<Cliente> listaClienti);

}
