package concessionario.view;

import java.util.List;

import concessionario.model.ElementoListino;

public interface GestioneAutoView {
    
    void mostraGestioneAuto();

    void mostraListino(List<ElementoListino> listino);

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);
}
