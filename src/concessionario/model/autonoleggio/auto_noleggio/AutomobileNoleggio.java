package concessionario.model.autonoleggio.auto_noleggio;

import java.util.Date;

public class AutomobileNoleggio {

    private String modello;
    private String marca;
    private int numeroPorte;
    private int cilindrata;
    private int cavalli;
    private double costoNoleggio;
    private DisponibilitàAuto disponibilitàAuto;
    private Date ultimoNoleggio;

    public AutomobileNoleggio(String modello, String marca, int numeroPorte, int cilindrata, int cavalli, DisponibilitàAuto disponibilitàAuto) {
        this.modello = modello;
        this.marca = marca;
        this.numeroPorte = numeroPorte;
        this.cilindrata = cilindrata;
        this.cavalli = cavalli;
        this.disponibilitàAuto = disponibilitàAuto;
    }

    public String getModello() {
        return modello;
    }

    public String getMarca() {
        return marca;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public int getCilindrata() {
        return cilindrata;
    }

    public int getCavalli() {
        return cavalli;
    }

    public DisponibilitàAuto getDisponibilitàAuto() {
        return disponibilitàAuto;
    }

    public void setCostoNoleggio(double costoTotale) {
        this.costoNoleggio = costoTotale;
    }

    public double getCostoNoleggio() {
        return costoNoleggio;
    }

    public String getStato() {
        return disponibilitàAuto.toString();
    }

    public void setStato(DisponibilitàAuto stato) {
        this.disponibilitàAuto = stato;

        if (stato == DisponibilitàAuto.NonDisponibile) {
            this.ultimoNoleggio = new Date();
        }
    }


    public boolean tempoNoleggio() {
        if (ultimoNoleggio != null) {
            long currentTime = System.currentTimeMillis();
            long noleggioTime = ultimoNoleggio.getTime();
            long tempoTrascorso = currentTime - noleggioTime;

            // 5 minuti in millisecondi
            long cinqueMinuti = 5 * 60 * 1000;

            return tempoTrascorso >= cinqueMinuti;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Automobile [Marca: " + marca + ", Modello: " + modello + ",  numeroPorte:" + numeroPorte
                + ",  Cilindrata:" + cilindrata + ",  Cavalli:" + cavalli + ",  DisponibilitàAuto:" + disponibilitàAuto + "]";
    }
}


