package concessionario.model.repartoVendita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;

public class RegistroVenditeTest {

    private RegistroVendite registroVendite;
    private Preventivo preventivo1;
    private Preventivo preventivo2;

    @BeforeEach
    public void init() {
        registroVendite = new RegistroVendite();

        Automobile auto1 = new Automobile("Classe A", "Mercedes", 0, 5, 1600, 120, StatoMacchina.NUOVO);
        Cliente cliente1 = new Cliente("Mario", "Rossi", "mario.rossi@example.com", "1234567890", "MRARSS80A01H501Z");
        preventivo1 = new Preventivo(auto1, 25000.0, cliente1);

        Automobile auto2 = new Automobile("Punto", "Fiat", 0, 5, 1200, 75, StatoMacchina.NUOVO);
        Cliente cliente2 = new Cliente("Luigi", "Verdi", "luigi.verdi@example.com", "0987654321", "VRDLGI85C01H501Z");
        preventivo2 = new Preventivo(auto2, 15000.0, cliente2);
    }

    @Test
    public void testAddPreventivo() {
        registroVendite.addPreventivo(preventivo1);
        List<Preventivo> preventivi = registroVendite.getListaPreventivi();
        
        assertEquals(1, preventivi.size());
        assertTrue(preventivi.contains(preventivo1));
    }

    @Test
    public void testAddPiuPreventivi() {
        registroVendite.addPreventivo(preventivo1);
        registroVendite.addPreventivo(preventivo2);
        List<Preventivo> preventivi = registroVendite.getListaPreventivi();

        assertEquals(2, preventivi.size());
        assertTrue(preventivi.contains(preventivo1));
        assertTrue(preventivi.contains(preventivo2));
    }



    @Test
    public void testGetListaPreventivi() {
        registroVendite.addPreventivo(preventivo1);
        List<Preventivo> preventivi = registroVendite.getListaPreventivi();

        preventivi.clear();
        
        List<Preventivo> preventiviInterni = registroVendite.getListaPreventivi();
        assertEquals(1, preventiviInterni.size(), "La lista interna non dovrebbe essere stata modificata");
    }

}
