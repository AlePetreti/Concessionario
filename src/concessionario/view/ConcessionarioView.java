package concessionario.view;

public interface ConcessionarioView {
    
    void show();

    void mostraGestioneClienti();

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);
}
