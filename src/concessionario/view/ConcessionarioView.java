package concessionario.view;

public interface ConcessionarioView {
    
    void show();

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);
}
