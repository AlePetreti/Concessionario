package concessionario.model.assicurazione;

public enum LivelloDanni {
    NESSUN_DANNO("Nessun danno"), DANNO_LIEVE("Danni lievi"), DANNO_GRAVE("Danni gravi");

    private final String descrizione;

    LivelloDanni(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}