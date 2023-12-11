package concessionario.automobile;

public class Automobile {

    private String modello;
    private String marca;
    private int km;
    private int numeroPorte;
    private int cilindrata;
    private int cavalli;
    private StatoMacchina statoMacchina;
    private String targa;
    private int prezzo;
    



    
    public Automobile(String modello, String marca, int km, int numeroPorte, int cilindrata, int cavalli, StatoMacchina statoMacchina, String targa, int prezzo) {
        this.modello = modello;
        this.marca = marca;
        this.km = km;
        this.numeroPorte = numeroPorte;
        this.cilindrata = cilindrata;
        this.cavalli = cavalli;
        this.statoMacchina = statoMacchina;
        this.targa = targa;
        this.prezzo = prezzo;
       
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
    
    
    public String getTarga() {
		return targa;
	}
    
    
    public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo += prezzo;
	}

	@Override
    public String toString() {
        return "Automobile [modello=" + modello + ", marca=" + marca + ", km=" + km + ", numeroPorte=" + numeroPorte
                + ", cilindrata=" + cilindrata + ", cavalli=" + cavalli + ", statoMacchina=" + statoMacchina + ",targa=" + targa + ",prezzo=" + prezzo + "]";
    }

    
    
    
}
