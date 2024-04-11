package concessionario.model.automobile;

import java.util.Objects;
import java.util.Optional;

public class Automobile {

    private String modello;
    private String marca;
    private int km;
    private int numeroPorte;
    private int cilindrata;
    private int cavalli;
    private StatoMacchina statoMacchina;
    private Optional<String> targa;


    
    public Automobile(String modello, String marca, int km, int numeroPorte, int cilindrata, int cavalli, StatoMacchina statoMacchina, Optional<String> targa) {
        this.modello = modello;
        this.marca = marca;
        this.km = km;
        this.numeroPorte = numeroPorte;
        this.cilindrata = cilindrata;
        this.cavalli = cavalli;
        this.statoMacchina = statoMacchina;
        this.targa = Optional.empty();
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

    public int getCavalli() {
        return cavalli;
    }

    public StatoMacchina geStatoMacchina() {
        return statoMacchina;
    }

   
    
     public void setTarga(Optional<String> targa) {
		this.targa = targa;
	}

    //stampa targa solo se presente (vedere Optional)
     
	@Override
    public String toString() {
        return "Automobile [modello=" + modello + ", marca=" + marca + ", km=" + km + ", numeroPorte=" + numeroPorte
                + ", cilindrata=" + cilindrata + ", cavalli=" + cavalli + ", statoMacchina=" + statoMacchina + "targa "+ targa.orElse("Da immatricolare") + "]";
    }

    
    
    
}
