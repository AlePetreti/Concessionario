package concessionario.model.repartoVendita;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.GeneratoreTarga;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;

public class PreventivoTest {

    private Automobile auto;
    private Cliente cliente;

    @BeforeEach
    public void init() {
        
        auto = new Automobile("Classe A", "Mercedes", 0, 5, 2000, 500,GeneratoreTarga.generateTarga(), StatoMacchina.NUOVO);
        cliente = new Cliente.Builder()
                .nome("Mario")
                .cognome("Rossi")
                .email("mario.rossi@example.com")
                .telefono("1234567890")
                .codiceFiscale("MRARSS80A01H501U")
                .build();
    }

    @Test
    public void testGetters() {
        // Creazione di un Preventivo e verifica dei getter
        Preventivo preventivo = new Preventivo(auto, 60000.0, cliente);
        
        assertEquals(auto, preventivo.getAuto(), "L'automobile nel preventivo dovrebbe essere corretta");
        assertEquals(cliente, preventivo.getCliente(), "Il cliente nel preventivo dovrebbe essere corretto");
        assertEquals(60000.0, preventivo.getPrezzoTotale(), "Il prezzo totale nel preventivo dovrebbe essere corretto");
    }
}
