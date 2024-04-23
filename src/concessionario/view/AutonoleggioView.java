package concessionario.view;

import concessionario.view.concessionario.ConcessionarioViewObserver;

public interface AutonoleggioView{
    
    void autonoleggioView();

    void addObserver(ConcessionarioViewObserver observer);

    void removeObserver(ConcessionarioViewObserver observer);
}