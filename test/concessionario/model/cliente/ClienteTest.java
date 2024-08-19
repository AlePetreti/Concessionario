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
        cliente = new Cliente("Mario", "Rossi", "mario.rossi@example.com", "1234567890", "MRORSS80A01H501T");
        cliente2 = new Cliente("Mario", "Rossi", "mario.rossi@example.com", "1234567890", "MRORSS80A01H501T");
        cliente3 = new Cliente("Luigi", "Verdi", "luigi.verdi@example.com", "0987654321", "VRDLGU80A01H501Z");
    }
    
    @Test
    public void testGetter() {

        assertEquals("Mario", cliente.getNome());
        assertEquals("Rossi", cliente.getCognome());
        assertEquals("mario.rossi@example.com", cliente.getEmail());
        assertEquals("1234567890", cliente.getTelefono());
        assertEquals("MRORSS80A01H501T", cliente.getCf());

    }

    @Test
    public void testEquals() {

        assertEquals(cliente, cliente2);
        assertNotEquals(cliente, cliente3);
        assertNotEquals(cliente, null);
    }
}


