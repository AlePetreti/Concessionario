package concessionario;

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

    
}
