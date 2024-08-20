package concessionario.model.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactoryClienteTest {

    private FactoryCliente factory;

    @BeforeEach
    public void init() {
        factory = new FactoryCliente();
    }

    @Test
    public void testCreaClienteRandomNonNull() {
        // Verifica che il metodo non restituisca un oggetto nullo
        Cliente cliente = factory.creaClienteRandom();
        assertNotNull(cliente, "Il cliente generato non dovrebbe essere null");
    }

    @Test
    public void testCreaClienteRandomNomeValido() {
        // Verifica che il nome del cliente sia uno dei nomi possibili
        Cliente cliente = factory.creaClienteRandom();
        String[] nomi = {"Alessandro", "Andrea", "Giovanni", "Fabio", "Luca", "Ciro"};
        assertTrue(isValueInArray(cliente.getNome(), nomi), "Il nome del cliente generato dovrebbe essere valido");
    }

    @Test
    public void testCreaClienteRandomCognomeValido() {
        // Verifica che il cognome del cliente sia uno dei cognomi possibili
        Cliente cliente = factory.creaClienteRandom();
        String[] cognomi = {"Petreti", "Rossi", "Bianchi", "Verdi", "Esposito", "Messi"};
        assertTrue(isValueInArray(cliente.getCognome(), cognomi), "Il cognome del cliente generato dovrebbe essere valido");
    }

    @Test
    public void testCreaClienteRandomEmailValida() {
        // Verifica che l'email del cliente sia formata correttamente
        Cliente cliente = factory.creaClienteRandom();
        String expectedEmail = cliente.getNome().toLowerCase() + "." + cliente.getCognome().toLowerCase() + "@gmail.com";
        assertEquals(expectedEmail, cliente.getEmail(), "L'email del cliente dovrebbe essere formata correttamente");
    }

    @Test
    public void testCreaClienteRandomTelefonoValido() {
        // Verifica che il numero di telefono del cliente sia formato correttamente
        Cliente cliente = factory.creaClienteRandom();
        assertTrue(cliente.getTelefono().startsWith("3"), "Il numero di telefono dovrebbe iniziare con '3'");
        assertEquals(10, cliente.getTelefono().length(), "Il numero di telefono dovrebbe essere lungo 10 cifre");
    }

    @Test
    public void testCreaClienteRandomCodiceFiscaleValido() {
        // Verifica che il codice fiscale sia formato da 16 caratteri alfanumerici
        Cliente cliente = factory.creaClienteRandom();
        String codiceFiscale = cliente.getCf();
        assertNotNull(codiceFiscale, "Il codice fiscale non dovrebbe essere null");
        assertEquals(16, codiceFiscale.length(), "Il codice fiscale dovrebbe essere lungo 16 caratteri");
        assertTrue(codiceFiscale.matches("[A-Z0-9]{16}"), "Il codice fiscale dovrebbe contenere solo lettere maiuscole e numeri");
    }

    private boolean isValueInArray(String value, String[] array) {
        for (String s : array) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
