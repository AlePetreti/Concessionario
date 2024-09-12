package concessionario.model.ricercaAuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import concessionario.model.automobile.StatoMacchina;

public class FiltroTest {

    private Filtro.Builder filtroBuilder;

    @BeforeEach
    public void init() {
        filtroBuilder = new Filtro.Builder();
    }

    @Test
    public void testSetModello() {
        String modello = "Classe A";
        Filtro filtro = filtroBuilder.setModello(modello).build();
        assertEquals(modello, filtro.getModello());
    }

    @Test
    public void testSetMarca() {
        String marca = "Mercedes";
        Filtro filtro = filtroBuilder.setMarca(marca).build();
        assertEquals(marca, filtro.getMarca());
    }

    @Test
    public void testSetKm() {
        int km = 10000;
        Filtro filtro = filtroBuilder.setKm(km).build();
        assertEquals(km, filtro.getKm());
    }

    @Test
    public void testSetNumeroPorte() {
        int numeroPorte = 5;
        Filtro filtro = filtroBuilder.setNumeroPorte(numeroPorte).build();
        assertEquals(numeroPorte, filtro.getNumeroPorte());
    }

    @Test
    public void testSetCilindrata() {
        int cilindrata = 1600;
        Filtro filtro = filtroBuilder.setCilindrata(cilindrata).build();
        assertEquals(cilindrata, filtro.getCilindrata());
    }

    @Test
    public void testSetPrezzoMax() {
        double prezzoMax = 30000.0;
        Filtro filtro = filtroBuilder.setPrezzoMax(prezzoMax).build();
        assertEquals(prezzoMax, filtro.getPrezzoMax());
    }

    @Test
    public void testSetStatoMacchina() {
        StatoMacchina statoMacchina = StatoMacchina.USATO;
        Filtro filtro = filtroBuilder.setStatoMacchina(statoMacchina).build();
        assertEquals(statoMacchina, filtro.getStatoMacchina());
    }

    @Test
    public void testSetModelloNull() {
        assertThrows(NullPointerException.class, () -> {
            filtroBuilder.setModello(null).build();
        }, "Dovrebbe essere lanciata una NullPointerException quando il modello è null");
    }

    @Test
    public void testSetMarcaNull() {
        assertThrows(NullPointerException.class, () -> {
            filtroBuilder.setMarca(null).build();
        }, "Dovrebbe essere lanciata una NullPointerException quando la marca è null");
    }
}
