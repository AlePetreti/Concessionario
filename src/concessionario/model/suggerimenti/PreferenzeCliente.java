package concessionario.model.suggerimenti;

import concessionario.model.automobile.TipoAlimentazione;

public class PreferenzeCliente {
    
    private double redditoAnnuale;
    private String preferenzeAuto;
    private int preferenzeNumeroPorte;
    private TipoAlimentazione preferenzeAlimentazione;

    private PreferenzeCliente(double redditoAnnuale, String preferenzeAuto, int preferenzeNumeroPorte, TipoAlimentazione preferenzeAlimentazione) {
        this.redditoAnnuale = redditoAnnuale;
        this.preferenzeAuto = preferenzeAuto;
        this.preferenzeNumeroPorte = preferenzeNumeroPorte;
        this.preferenzeAlimentazione = preferenzeAlimentazione;
    }

    public double getRedditoAnnuale() {
        return redditoAnnuale;
    }

    public String getPreferenzeAuto() {
        return preferenzeAuto;
    }

    public int getPreferenzeNumeroPorte() {
        return preferenzeNumeroPorte;
    }

    public TipoAlimentazione getPreferenzeAlimentazione() {
        return preferenzeAlimentazione;
    }

    @Override
    public String toString() {
        return "PreferenzeCliente [redditoAnnuale=" + redditoAnnuale + ", preferenzeAuto=" + preferenzeAuto
                + ", preferenzeNumeroPorte=" + preferenzeNumeroPorte + ", preferenzeAlimentazione="
                + preferenzeAlimentazione + "]";
    }

    public static class Builder {
        private double redditoAnnuale;
        private String preferenzeAuto;
        private int preferenzeNumeroPorte;
        private TipoAlimentazione preferenzeAlimentazione;    
        
        public Builder() {
            this.redditoAnnuale = 0;
            this.preferenzeAuto = null;
            this.preferenzeNumeroPorte = 0;
        }

        public Builder redditoAnnuale(double redditoAnnuale) {
            this.redditoAnnuale = redditoAnnuale;
            return this;
        }

        public Builder preferenzeAuto(String preferenzeAuto) {
            this.preferenzeAuto = preferenzeAuto;
            return this;
        }

        public Builder numeroMembriFamiglia(int numeroMembri) {
            this.preferenzeNumeroPorte = (numeroMembri > 2) ? 5 : 3;
            return this;
        }

        public Builder preferenzeAlimentazione(TipoAlimentazione preferenzeAlimentazione) {
            this.preferenzeAlimentazione = preferenzeAlimentazione;
            return this;
        }

        public PreferenzeCliente build() {
            return new PreferenzeCliente(redditoAnnuale, preferenzeAuto, preferenzeNumeroPorte, preferenzeAlimentazione);
        } 
    }    
}
