package concessionario;
import java.util.LinkedList;
import java.util.List;

import concessionario.automobile.Automobile;

public class Listino {
    
    private LinkedList<ElementoListino> listino;

    public Listino() {
        this.listino = new LinkedList<ElementoListino>();
    }
    /**
     * 
     * @param auto
     * @return prezzo dell'auto passata per parametro
     */
    public double getPrezzoAuto(Automobile auto) {
        for(ElementoListino e: listino){
            if(auto.equals(e.getAutomobile())) {
                return e.getPrezzo();
            }
        }
        return -1;
    }
    
    /**
     * aggiungo un elemento listino al listino
     * @param elementoListino
     * @return true
     */
    public boolean aggiungiAuto(ElementoListino elementoListino) {
        listino.add(elementoListino);
        return true;
    }

    /**
     * 
     * @return Listino 
     */
    public List<ElementoListino> getListino() {
        return new LinkedList<>(listino);
    }

    public void stampaListino(List<Automobile> auto) {
        auto.forEach((c)->System.out.println( "\n" + c + "\n"));
    }
}
