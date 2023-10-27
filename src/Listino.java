import java.util.LinkedList;

public class Listino {
    
    private LinkedList<ElementoListino> listino;

    public Listino() {
        this.listino = new LinkedList<ElementoListino>();

    }

    public double getPrezzoAuto(ElementoListino autoInListino) {
        return autoInListino.getPrezzo();
    }

    // da rigurdare il listino e valutare se fare 2 liste diverse una per quelle nuove(listino) e una per quelle usate
}
