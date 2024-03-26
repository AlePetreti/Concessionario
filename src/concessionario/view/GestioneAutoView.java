package concessionario.view;

import java.util.List;
import java.util.Optional;

import concessionario.model.ElementoListino;

public interface GestioneAutoView {
    
    void mostraGestioneAuto();

    void mostraListino(List<ElementoListino> listino);

    String getModelloAuto();

    String getMarcaAuto();

    Optional<Integer> getKmAuto();

    Optional<Integer> getNumeroPorte();

    Optional<Integer> getCilindrata();

    Optional<Double> getPrezzoMax();

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);
}
