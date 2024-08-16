package concessionario.model;

import java.util.List;

import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;

public class Leasing {

    
    private List<AutomobileNoleggio> automobili;

    public List<AutomobileNoleggio> getAutomobili() {
        return automobili;
    }
    private String autoSelezionata;
    private int durataContratto;
    private double prezzoMensile;
    private int limiteChilometrico;

    public Leasing(String autoSelezionata, int durataContratto, double prezzoMensile, int limiteChilometrico) {
        this.autoSelezionata = autoSelezionata;
        this.durataContratto = durataContratto;
        this.prezzoMensile = prezzoMensile;
        this.limiteChilometrico = limiteChilometrico;
    }


    public String getAutoSelezionata() {
        return autoSelezionata;
    }

    public int getDurataContratto() {
        return durataContratto;
    }

    public double getPrezzoMensile() {
        return prezzoMensile;
    }

    public int getLimiteChilometrico() {
        return limiteChilometrico;
    }
}

