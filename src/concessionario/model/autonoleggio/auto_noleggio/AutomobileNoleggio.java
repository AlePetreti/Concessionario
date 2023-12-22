package concessionario.model.autonoleggio.auto_noleggio;

public class AutomobileNoleggio {

    private String modello;
    private String marca;
    private int numeroPorte;
    private int cilindrata;
    private int cavalli;
    private DisponibilitàAuto DisponibilitàAuto;


    
    public AutomobileNoleggio(String modello, String marca,int numeroPorte, int cilindrata, int cavalli, DisponibilitàAuto DisponibilitàAuto) {
        this.modello = modello;
        this.marca = marca;
        this.numeroPorte = numeroPorte;
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

    @Override
    public String toString() {
        return "Automobile [modello=" + modello + ", marca=" + marca + ", numeroPorte=" + numeroPorte
                + ", cilindrata=" + cilindrata + ", cavalli=" + cavalli + ", DisponibilitàAuto=" + DisponibilitàAuto + "]";
    }

    
    
    
}

