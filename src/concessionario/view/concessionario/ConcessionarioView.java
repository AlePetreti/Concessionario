package concessionario.view.concessionario;

import java.util.List;

import concessionario.model.Preventivo;

public interface ConcessionarioView {

    void show();

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);

    void mostraPreventiviCompletati(List<Preventivo> preventivi);
}
