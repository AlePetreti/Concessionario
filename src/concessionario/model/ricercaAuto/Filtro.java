package concessionario.model.ricercaAuto;

import java.util.Objects;

import concessionario.model.automobile.StatoMacchina;

public class Filtro {

    private final String modello;
    private final String marca;
    private final int km;
    private final int numeroPorte;
    private final int cilindrata;
    private final double prezzoMax;
    private final StatoMacchina statoMacchina;

    private Filtro(Builder builder) {
        this.modello = builder.modello;
        this.marca = builder.marca;
        this.km = builder.km;
        this.numeroPorte = builder.numeroPorte;
        this.cilindrata = builder.cilindrata;
        this.prezzoMax = builder.prezzoMax;
        this.statoMacchina = builder.statoMacchina;
    }

    public static class Builder {
        private String modello = "";
        private String marca = "";
        private int km = -1;
        private int numeroPorte = 0;
        private int cilindrata = 0;
        private double prezzoMax = 0.0;
        private StatoMacchina statoMacchina = StatoMacchina.NUOVO;

        public Builder setModello(String modello) {
            this.modello = Objects.requireNonNull(modello, "Il modello non può essere null");
            return this;
        }

        public Builder setMarca(String marca) {
            this.marca = Objects.requireNonNull(marca, "La marca non può essere null");
            return this;
        }

        public Builder setKm(int km) {
            this.km = km;
            return this;
        }

        public Builder setNumeroPorte(int numeroPorte) {
            this.numeroPorte = numeroPorte;
            return this;
        }

        public Builder setCilindrata(int cilindrata) {
            this.cilindrata = cilindrata;
            return this;
        }

        public Builder setPrezzoMax(double prezzoMax) {
            this.prezzoMax = prezzoMax;
            return this;
        }

        public Builder setStatoMacchina(StatoMacchina statoMacchina) {
            this.statoMacchina = Objects.requireNonNull(statoMacchina, "Lo stato della macchina non può essere null");
            return this;
        }

        public Filtro build() {
            return new Filtro(this);
        }
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
