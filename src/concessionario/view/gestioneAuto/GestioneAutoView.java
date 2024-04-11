package concessionario.view.gestioneAuto;

import java.util.List;
import java.util.Optional;

import concessionario.model.ElementoListino;

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

    void addObserver(GestioneAutoViewObserver observer);

    void removeObserver(GestioneAutoViewObserver observer);

}
