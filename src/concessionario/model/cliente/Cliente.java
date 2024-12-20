package concessionario.model.cliente;

public class Cliente {
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String codiceFiscale;
    private double redditoAnnuale;
    private int numeroMembriNucleoFamiliare;
    
    private Cliente(Builder builder) {
        this.nome = builder.nome;
        this.cognome = builder.cognome;
        this.email = builder.email;
        this.telefono = builder.telefono;
        this.codiceFiscale = builder.codiceFiscale;
        this.redditoAnnuale = builder.redditoAnnuale;
        this.numeroMembriNucleoFamiliare = builder.numeroMembriNucleoFamiliare;
    }
    
    public static class Builder {
        private String nome;
        private String cognome;
        private String email;
        private String telefono;
        private String codiceFiscale;
        private double redditoAnnuale;
        private int numeroMembriNucleoFamiliare;
        
        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder cognome(String cognome) {
            this.cognome = cognome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder codiceFiscale(String codiceFiscale) {
            this.codiceFiscale = codiceFiscale;
            return this;
        }

        public Builder redditoAnnuale(double redditoAnnuale) {
            this.redditoAnnuale = redditoAnnuale;
            return this;
        }

        public Builder numeroMembriNucleoFamiliare(int numeroMembriNucleoFamiliare) {
            this.numeroMembriNucleoFamiliare = numeroMembriNucleoFamiliare;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        } 
    }    

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCf() {
        return codiceFiscale;
    }

    public double getRedditoAnnuale() {
        return redditoAnnuale;
    }

    public int getNumeroMembriNucleoFamiliare() {
        return numeroMembriNucleoFamiliare;
    }

   
    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", telefono=" + telefono
                + ", codiceFiscale=" + codiceFiscale + ", redditoAnnuale=" + redditoAnnuale
                + ", numeroMembriNucleoFamiliare=" + numeroMembriNucleoFamiliare + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cognome == null) {
            if (other.cognome != null)
                return false;
        } else if (!cognome.equals(other.cognome))
            return false;
        if (codiceFiscale == null) {
            if (other.codiceFiscale != null)
                return false;
        } else if (!codiceFiscale.equals(other.codiceFiscale))
            return false;
        return true;
    }
}
