package concessionario.model.repartoVendita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;

public class PreventivoTest {
    
    private Automobile auto;
    private Cliente cliente;

    @BeforeEach
    public void init() {
        auto = new Automobile("Classe A", "Mercedes", 0, 5, 2000, 500, StatoMacchina.NUOVO);
        cliente = new Cliente("Mario", "Rossi", "mario.rossi@example.com", "1234567890", "MRARSS80A01H501U");
    }

    @Test
    public void testGetters() {
        Preventivo preventivo = new Preventivo(auto, 60000.0, cliente);
        assertEquals(auto, preventivo.getAuto());
        assertEquals(cliente, preventivo.getCliente());
        assertEquals(60000.0, preventivo.getPrezzoTotale());
    }
}
