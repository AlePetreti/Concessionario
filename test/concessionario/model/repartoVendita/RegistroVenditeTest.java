package concessionario.model.repartoVendita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.GeneratoreTarga;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;

public class RegistroVenditeTest {

    private RegistroVendite registroVendite;
    private Preventivo preventivo1;
    private Preventivo preventivo2;

    @BeforeEach
    public void init() {
        registroVendite = new RegistroVendite();

        // Inizializzazione degli oggetti per i test
        Automobile auto1 = new Automobile("Classe A", "Mercedes", 0, 5, 1600, 120, GeneratoreTarga.generateTarga(),StatoMacchina.NUOVO);
        Cliente cliente1 = new Cliente.Builder()
                .nome("Mario")
                .cognome("Rossi")
                .email("mario.rossi@example.com")
                .telefono("1234567890")
                .codiceFiscale("MRARSS80A01H501Z")
                .build();
        preventivo1 = new Preventivo(auto1, 25000.0, cliente1);

        Automobile auto2 = new Automobile("Punto", "Fiat", 0, 5, 1200, 75, GeneratoreTarga.generateTarga(),StatoMacchina.NUOVO);
        Cliente cliente2 = new Cliente.Builder()
                .nome("Luigi")
                .cognome("Verdi")
                .email("luigi.verdi@example.com")
                .telefono("0987654321")
                .codiceFiscale("VRDLGI85C01H501Z")
                .build();
        preventivo2 = new Preventivo(auto2, 15000.0, cliente2);
    }

    @Test
    public void testAddPreventivo() {
        registroVendite.addPreventivo(preventivo1);
        List<Preventivo> preventivi = registroVendite.getListaPreventivi();

        assertEquals(1, preventivi.size(), "La dimensione della lista dovrebbe essere 1");
        assertTrue(preventivi.contains(preventivo1), "La lista dovrebbe contenere il preventivo aggiunto");
    }

    @Test
    public void testAddPiuPreventivi() {
        registroVendite.addPreventivo(preventivo1);
        registroVendite.addPreventivo(preventivo2);
        List<Preventivo> preventivi = registroVendite.getListaPreventivi();

        assertEquals(2, preventivi.size(), "La dimensione della lista dovrebbe essere 2");
        assertTrue(preventivi.contains(preventivo1), "La lista dovrebbe contenere il primo preventivo");
        assertTrue(preventivi.contains(preventivo2), "La lista dovrebbe contenere il secondo preventivo");
    }

    @Test
    public void testGetListaPreventivi() {
        registroVendite.addPreventivo(preventivo1);
        List<Preventivo> preventivi = registroVendite.getListaPreventivi();

        preventivi.clear();  // Prova a svuotare la lista esterna

        List<Preventivo> preventiviInterni = registroVendite.getListaPreventivi();
        assertEquals(1, preventiviInterni.size(), "La lista interna non dovrebbe essere stata modificata");
    }
}
