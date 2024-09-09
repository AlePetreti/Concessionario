package concessionario.model.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private Cliente cliente;
    private Cliente cliente2;
    private Cliente cliente3;

    @BeforeEach
    public void init() {
        cliente = new Cliente.Builder()
                .nome("Mario")
                .cognome("Rossi")
                .email("mario.rossi@example.com")
                .telefono("1234567890")
                .codiceFiscale("MRORSS80A01H501T")
                .build();

        cliente2 = new Cliente.Builder()
                .nome("Mario")
                .cognome("Rossi")
                .email("mario.rossi@example.com")
                .telefono("1234567890")
                .codiceFiscale("MRORSS80A01H501T")
                .build();

        cliente3 = new Cliente.Builder()
                .nome("Luigi")
                .cognome("Verdi")
                .email("luigi.verdi@example.com")
                .telefono("0987654321")
                .codiceFiscale("VRDLGU80A01H501Z")
                .build();
    }
    
    @Test
    public void testGetter() {
        // Test per verificare che i getter restituiscano i valori corretti
        assertEquals("Mario", cliente.getNome());
        assertEquals("Rossi", cliente.getCognome());
        assertEquals("mario.rossi@example.com", cliente.getEmail());
        assertEquals("1234567890", cliente.getTelefono());
        assertEquals("MRORSS80A01H501T", cliente.getCf());
    }

    @Test
    public void testEquals() {
        // Test per verificare l'implementazione del metodo equals
        assertEquals(cliente, cliente2);  // clienti con gli stessi dati dovrebbero essere uguali
        assertNotEquals(cliente, cliente3);  // clienti con dati diversi non dovrebbero essere uguali
        assertNotEquals(cliente, null);  // un cliente non dovrebbe mai essere uguale a null
    }
}
