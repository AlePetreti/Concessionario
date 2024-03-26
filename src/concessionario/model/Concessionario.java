package concessionario.model;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.AnagraficaClienti;

public class Concessionario {

    private Listino listino;
    
    
    public Concessionario(Listino listino) {
        this.listino = listino;
    }
    
    public boolean acquistaAutoDaPrivato(VenditorePrivato privato) {
        listino.aggiungiAuto(privato.getAutomobile(), privato.getprezzoVendita()); // questo prezzo é di rivendita in questo caso é uguale al prezzo di acquisto
        // pensare a dove usare il cliente
        return true;
    }

    public boolean vendiAuto(Automobile auto, AnagraficaClienti listaClienti) {
        if(auto.geStatoMacchina().equals(StatoMacchina.NUOVO)) {
            
        } else {
            
        }     
        return true;
    }
    
}
