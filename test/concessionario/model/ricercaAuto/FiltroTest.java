package concessionario.model.ricercaAuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import concessionario.model.automobile.StatoMacchina;

public class FiltroTest {

    private Filtro filtro;

    @BeforeEach
    public void init() {
        filtro = new Filtro();
    }

    @Test
    public void testSetModello() {
        String modello = "Classe A";
        filtro.setModello(modello);
        assertEquals(modello, filtro.getModello());
    }

    @Test
    public void testSetMarca() {
        String marca = "Mercedes";
        filtro.setMarca(marca);
        assertEquals(marca, filtro.getMarca());
    }

    @Test
    public void testSetKm() {
        int km = 10000;
        filtro.setKm(km);
        assertEquals(km, filtro.getKm());
    }

    @Test
    public void testSetNumeroPorte() {
        int numeroPorte = 5;
        filtro.setNumeroPorte(numeroPorte);
        assertEquals(numeroPorte, filtro.getNumeroPorte());
    }

    @Test
    public void testSetCilindrata() {
        int cilindrata = 1600;
        filtro.setCilindrata(cilindrata);
        assertEquals(cilindrata, filtro.getCilindrata());
    }

    @Test
    public void testSetPrezzoMax() {
        double prezzoMax = 30000.0;
        filtro.setPrezzoMax(prezzoMax);
        assertEquals(prezzoMax, filtro.getPrezzoMax());
    }

    @Test
    public void testSetStatoMacchina() {
        StatoMacchina statoMacchina = StatoMacchina.USATO;
        filtro.setStatoMacchina(statoMacchina);
        assertEquals(statoMacchina, filtro.getStatoMacchina());
    }

    @Test
    public void testSetModelloNull() {
        assertThrows(NullPointerException.class, () -> {
            filtro.setModello(null);
        }, "Dovrebbe essere lanciata una NullPointerException quando il modello è null");
    }

    @Test
    public void testSetMarcaNull() {
        assertThrows(NullPointerException.class, () -> {
            filtro.setMarca(null);
        }, "Dovrebbe essere lanciata una NullPointerException quando la marca è null");
    }
}
