package concessionario.model.ricercaAuto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.GeneratoreTarga;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.listino.Listino;

public class CercatoreAutoTest {

    Listino listinoAuto;
    Filtro filtro;
    Filtro.Builder filtroBuilder;
    CercatoreAuto cercatore;
    Automobile auto;
    Automobile auto1;
    Automobile auto2;

    @BeforeEach
    public void init() {
        listinoAuto = new Listino();
        filtroBuilder = new Filtro.Builder();
        cercatore = new CercatoreAuto();
        auto = new Automobile("Punto", "Fiat", 50000, 3, 1200, 75, GeneratoreTarga.generateTarga(), StatoMacchina.USATO);
        auto1 = new Automobile("Golf", "Volkswagen", 30000, 5, 1600, 110, GeneratoreTarga.generateTarga(), StatoMacchina.NUOVO);
        auto2 = new Automobile("Classe A", "Mercedes", 45000, 3, 1600, 110, GeneratoreTarga.generateTarga(), StatoMacchina.USATO);
    }

    @Test
    public void testCercaAutoFiltroVuoto() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);

        filtro = filtroBuilder.build();
        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(2, risultato.getListino().size());
    }

    @Test
    public void testCercaAutoPerModello() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        
        filtro = filtroBuilder.setModello("Golf").build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertEquals("Golf", risultato.getListino().get(0).getAutomobile().getModello());
    }

    @Test
    public void testCercaAutoPerMarca() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        
        filtro = filtroBuilder.setMarca("Fiat").build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertEquals("Fiat", risultato.getListino().get(0).getAutomobile().getMarca());
    }

    @Test
    public void testCercaAutoPerStatoMacchina() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        
        filtro = filtroBuilder.setStatoMacchina(StatoMacchina.USATO).build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertEquals(StatoMacchina.USATO, risultato.getListino().get(0).getAutomobile().getStatoMacchina());
    }

    @Test
    public void testCercaAutoPerPrezzoMax() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        
        filtro = filtroBuilder.setPrezzoMax(10000).build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertTrue(risultato.getListino().get(0).getPrezzo() <= 10000);
    }

    @Test
    public void testCercaAutoPerCilindrata() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        
        filtro = filtroBuilder.setCilindrata(1600).build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertEquals(1600, risultato.getListino().get(0).getAutomobile().getCilindrata());
    }

    @Test
    public void testCercaAutoPerKm() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        
        filtro = filtroBuilder.setKm(40000).build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertTrue(risultato.getListino().get(0).getAutomobile().getKm() <= 40000);
    }

    @Test
    public void testCercaAutoPerNumeroPorte() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        
        filtro = filtroBuilder.setNumeroPorte(5).build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertEquals(5, risultato.getListino().get(0).getAutomobile().getNumeroPorte());
    }

    @Test
    public void testCercaAutoFiltriCombinati() {
        listinoAuto.aggiungiAuto(auto, 5000);
        listinoAuto.aggiungiAuto(auto1, 15000);
        listinoAuto.aggiungiAuto(auto2, 8000);
        
        filtro = filtroBuilder
            .setMarca("Mercedes")
            .setModello("Classe A")
            .setPrezzoMax(10000)
            .build();

        Listino risultato = cercatore.cercaAuto(filtro, listinoAuto);

        assertEquals(1, risultato.getListino().size());
        assertEquals("Mercedes", risultato.getListino().get(0).getAutomobile().getMarca());
        assertEquals("Classe A", risultato.getListino().get(0).getAutomobile().getModello());
        assertTrue(risultato.getListino().get(0).getPrezzo() <= 10000);
    }
}
