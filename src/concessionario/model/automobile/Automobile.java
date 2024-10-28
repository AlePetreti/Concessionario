package concessionario.model.automobile;

public class Automobile {

    private final String modello;
    private final String marca;
    private final int km;
    private final int numeroPorte;
    private final int cilindrata;
    private final int cavalli;
    private final String targa;
    private final StatoMacchina statoMacchina;
    private TipoAlimentazione tipoAlimentazione;

    public Automobile(String modello, String marca, int km, int numeroPorte, int cilindrata, int cavalli, String targa, StatoMacchina statoMacchina, TipoAlimentazione tipoAlimentazione) {
        this.modello = modello;
        this.marca = marca;
        this.km = km;
        this.numeroPorte = numeroPorte;
        this.cilindrata = cilindrata;
        this.cavalli = cavalli;
        this.targa = targa;
        this.statoMacchina = statoMacchina;
        this.tipoAlimentazione = tipoAlimentazione;
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

    public String getTarga() {
		return targa;
	}

	public StatoMacchina getStatoMacchina() {
        return statoMacchina;
    }

    public TipoAlimentazione getTipoAlimentazione() {
        return tipoAlimentazione;
    }

    @Override
    public String toString() {
        return "Automobile [modello=" + modello + ", marca=" + marca + ", km=" + km + ", numeroPorte=" + numeroPorte
                + ", cilindrata=" + cilindrata + ", cavalli=" + cavalli +", targa="+ targa + ", statoMacchina=" + statoMacchina + "]";
    }
}
