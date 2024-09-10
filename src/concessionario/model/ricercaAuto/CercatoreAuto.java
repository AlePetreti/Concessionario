package concessionario.model.ricercaAuto;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.listino.Listino;


public class CercatoreAuto {

    public Listino cercaAuto(Filtro filtro, Listino listinoAuto) {
        Listino autoFiltrate = new Listino();
        
        listinoAuto.getListino().stream()
        .filter(e -> filtro.getModello().isEmpty() || filtro.getModello().equalsIgnoreCase(e.getAutomobile().getModello()))
        .filter(e -> filtro.getMarca().isEmpty() || filtro.getMarca().equalsIgnoreCase(e.getAutomobile().getMarca()))
        .filter(e -> filtro.getKm() == -1 || filtro.getKm() >= e.getAutomobile().getKm())
        .filter(e -> filtro.getNumeroPorte() == 0 || filtro.getNumeroPorte() == e.getAutomobile().getNumeroPorte())
        .filter(e -> filtro.getCilindrata() == 0 || filtro.getCilindrata() == e.getAutomobile().getCilindrata())
        .filter(e -> filtro.getStatoMacchina().equals(StatoMacchina.NUOVO) || filtro.getStatoMacchina().equals(e.getAutomobile().getStatoMacchina()))
        .filter(e -> filtro.getPrezzoMax() == 0.0 || filtro.getPrezzoMax() >= e.getPrezzo())
        .forEach(e -> autoFiltrate.aggiungiAuto(e.getAutomobile(), e.getPrezzo()));
        
        return autoFiltrate;
    }
}
