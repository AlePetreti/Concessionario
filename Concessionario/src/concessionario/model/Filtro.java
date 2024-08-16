package concessionario.model;

import java.util.Objects;

import concessionario.model.automobile.StatoMacchina;

public class Filtro {

    private String modello;
    private String marca;
    private int km;
    private int numeroPorte;
    private int cilindrata;
    private double prezzoMax;
    private StatoMacchina statoMacchina;

    public Filtro() {
        this.statoMacchina = StatoMacchina.NUOVO;
        this.km = -1;
        this.modello = "";
        this.marca = "";
    }

    public void setModello(String modello) {
        Objects.requireNonNull(modello);
        this.modello = modello;
    }

    public void setMarca(String marca) {
        Objects.requireNonNull(marca);
        this.marca = marca;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public void setCilindrata(int cilindrata) {
        this.cilindrata = cilindrata;
    }

    public void setPrezzoMax(double prezzoMax) {
        this.prezzoMax = prezzoMax;
    }

    public void setStatoMacchina(StatoMacchina statoMacchina) {
        this.statoMacchina = statoMacchina;
    }

    public String getModello() {
        return modello;
    }

    public String getMarca() {
        return marca;
    }

    public int getKm() {
        return km;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public int getCilindrata() {
        return cilindrata;
    }

    public double getPrezzoMax() {
        return prezzoMax;
    }

    public StatoMacchina getStatoMacchina() {
        return statoMacchina;
    }
}


