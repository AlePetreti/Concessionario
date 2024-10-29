package concessionario.view.auto;

import java.util.List;
import java.util.Optional;

import concessionario.model.automobile.TipoAlimentazione;
import concessionario.model.cliente.Cliente;
import concessionario.model.listino.ElementoListino;

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

    void mostraListaClienti(List<Cliente> cliente);

    void addObserver(GestioneAutoViewObserver observer);

    void removeObserver(GestioneAutoViewObserver observer);

    Cliente getClienteSelezionato(List<Cliente> listaClienti);

    void mostraTipiAlimentazione();

    TipoAlimentazione getTipoAlimentazioneSelezionato();

    String getMarcaAutoSugg();
}
