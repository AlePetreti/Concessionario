public class Cliente {

    private String name;
    private String surname;
    private String email;
    private int telephone;
    
    public Cliente(String name, String surname, String email, int telephone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getTelephone() {
        return telephone;
    }
}
