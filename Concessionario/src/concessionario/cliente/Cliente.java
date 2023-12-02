package concessionario.cliente;
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
}
