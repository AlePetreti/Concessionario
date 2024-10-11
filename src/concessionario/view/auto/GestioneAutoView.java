package concessionario.view.auto;

import java.util.List;
import java.util.Optional;

import concessionario.model.listino.ElementoListino;

public interface GestioneAutoView {
    
    void mostraGestioneAuto();

    void mostraListino(List<ElementoListino> listino);

    ElementoListino getElementoListino();

    String getModelloAuto();

    String getMarcaAuto();

    void mostraSuggerimenti(List<ElementoListino> suggerimenti);

    Optional<Integer> getKmAuto();

    Optional<Integer> getNumeroPorte();

    Optional<Integer> getCilindrata();

    Optional<Double> getPrezzoMax();

    void addObserver(GestioneAutoViewObserver observer);

    void removeObserver(GestioneAutoViewObserver observer);

}
