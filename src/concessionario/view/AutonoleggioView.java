package concessionario.view;


public interface AutonoleggioView{
    
    void autonoleggioView();

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);
}