package concessionario.model.cliente;
public class Cliente {

    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String codiceFiscale;
    
    public Cliente(String name, String  cognome, String email, String telefono, String codiceFiscale) {
        this.nome = name;
        this.cognome =  cognome;
        this.email = email;
        this.telefono = telefono;
        this.codiceFiscale = codiceFiscale;
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
        return this.codiceFiscale;
    }

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", telefono=" + telefono
                + ", codiceFiscale=" + codiceFiscale + "]";  
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
