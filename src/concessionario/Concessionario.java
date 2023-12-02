package concessionario;

public class Concessionario {

    private Listino listino;

    
    public Concessionario(Listino listino) {
        this.listino = listino;
    }

    public boolean acquistaAutoDaPrivato(VenditorePrivato privato) {
        listino.aggiungiAuto(privato.getAutomobile(), 0); // questo prezzo é di rivendita
        // pensare a dove usare il cliente
        return true;
    }
}
