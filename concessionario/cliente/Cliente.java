package concessionario.cliente;

public class Cliente {

    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String codiceFiscale;
    private String AnnoFedelta;

    public Cliente(String name, String cognome, String email, String telefono, String codiceFiscale,
            String AnnoFedelta) {
        this.nome = name;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.codiceFiscale = codiceFiscale;
        this.AnnoFedelta = AnnoFedelta;
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

    public int getAnno() {
        return Integer.parseInt(this.AnnoFedelta);
    }

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", telefono=" + telefono
                + ", codiceFiscale=" + codiceFiscale + ", AnnoFedelta=" + AnnoFedelta + "]";
    }
}
