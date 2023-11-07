package concessionario;
import java.util.LinkedList;

public class Listino {
    
    private LinkedList<ElementoListino> listino;

    public Listino() {
        this.listino = new LinkedList<ElementoListino>();

    }

    public double getPrezzoAuto(Automobile auto) {
        for(ElementoListino e: listino){
            if(auto.equals(e.getAutomobile())) {
                return e.getPrezzo();
            }
        }
        return -1;
    }
}
