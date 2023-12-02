package concessionario.autonoleggio;

public class Auto {
    private String marca;
    private String modello;
    private int cavalli;

    // Modifica qui: rimuovi la keyword 'void'
    public Auto(String marca, String modello, int cavalli) {
        this.marca = marca;
        this.modello = modello;
        this.cavalli = cavalli;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public int getCavalli() {
        return cavalli;
    }

    @Override
    public String toString() {
        return marca + " " + modello + " (Cavalli: " + cavalli + ")";
    }
}