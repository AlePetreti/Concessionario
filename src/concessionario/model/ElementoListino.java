package concessionario.model;

import concessionario.model.automobile.Automobile;

public class ElementoListino {

    private Automobile automobile;
    private double prezzo;

    public ElementoListino(Automobile automobile, double prezzo) {
        this.automobile = automobile;
        this.prezzo = prezzo;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public String toString() {
        return "ElementoListino [automobile=" + automobile + ", prezzo=" + prezzo + "]";
    }

    
}
