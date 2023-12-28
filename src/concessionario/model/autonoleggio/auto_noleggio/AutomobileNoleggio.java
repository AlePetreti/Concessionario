package concessionario.model.autonoleggio.auto_noleggio;

public class AutomobileNoleggio {

    private String modello;
    private String marca;
    private int numeroPorte;
    private int cilindrata;
    private int cavalli;
    private double costoNoleggio;
    private DisponibilitàAuto DisponibilitàAuto;


    
    public AutomobileNoleggio(String modello, String marca,int i, int cilindrata, int cavalli, DisponibilitàAuto DisponibilitàAuto) {
        this.modello = modello;
        this.marca = marca;
        this.numeroPorte = i;
        this.cilindrata = cilindrata;
        this.cavalli = cavalli;
        this.DisponibilitàAuto = DisponibilitàAuto;
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

    public DisponibilitàAuto getdDisponibilitàAuto() {
        return DisponibilitàAuto;
    }
    
    public void setCostoNoleggio(double costoTotale) {
        this.costoNoleggio = costoTotale;
    }

    public double getCostoNoleggio() {
        return costoNoleggio;
    }
    
    public String getStato() {
        return DisponibilitàAuto.toString();
    }
    
    public void setStato(concessionario.model.autonoleggio.auto_noleggio.DisponibilitàAuto nondisponibile) {
    }

    @Override
    public String toString() {
        return "Automobile [Marca: " + marca + ", Modello: " + modello + ",  numeroPorte:" + numeroPorte
                + ",  Cilindrata:" + cilindrata + ",  Cavalli:" + cavalli + ",  DisponibilitàAuto:" + DisponibilitàAuto + "]";
    }
}



