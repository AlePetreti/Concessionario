package concessionario.controller;

import java.util.stream.Collectors;
import java.util.List;

import concessionario.model.automobile.StatoMacchina;
import concessionario.model.listino.ElementoListino;
import concessionario.model.listino.Listino;
import concessionario.model.ricercaAuto.CercatoreAuto;
import concessionario.model.ricercaAuto.Filtro;
import concessionario.view.auto.EventoGestioneAuto;
import concessionario.view.auto.GestioneAutoView;
import concessionario.view.auto.GestioneAutoViewObserver;


public class GestioneAutoController implements GestioneAutoViewObserver {

    private final GestioneAutoView view;
    private final Listino listinoAuto;
    private final Listino listinoUsato;
    private final CercatoreAuto cercatoreAuto;
    private final PreventivoController preventivoController;

    public GestioneAutoController(GestioneAutoView view, Listino listinoAuto, Listino listinoUsato, PreventivoController preventivoController) {
        this.view = view;
        this.listinoAuto = listinoAuto;
        this.listinoUsato = listinoUsato;
        this.cercatoreAuto = new CercatoreAuto();
        this.view.addObserver(this);
        this.preventivoController = preventivoController;
    }

    @Override
    public void eventNotified(EventoGestioneAuto e) {
        switch (e) {
            case GESTIONE_AUTO_APERTA:
                view.mostraListino(listinoAuto.getListino());
                view.mostraSuggerimenti(filtraSuggerimenti(listinoAuto));
                break;
            case CERCA_AUTO:
                Filtro filtroAuto = creaFiltro(false); // Filtro per auto nuove
                Listino autoTrovate = cercatoreAuto.cercaAuto(filtroAuto, listinoAuto);
                view.mostraListino(autoTrovate.getListino());
                break;
            case CERCA_AUTO_USATE:
                Filtro filtroAutoUsate = creaFiltro(true); // Filtro per auto usate
                Listino autoUsate = cercatoreAuto.cercaAuto(filtroAutoUsate, listinoUsato);
                view.mostraListino(autoUsate.getListino());
                break;
            case MOSTRA_PREVENTIVO:
                preventivoController.inizializzaPreventivo(view.getElementoListino().getAutomobile());
                break;
            default:
                break;
        }
    }

    private Filtro creaFiltro(boolean perAutoUsate) {
        Filtro.Builder filtroBuilder = new Filtro.Builder()
                .setModello(view.getModelloAuto())
                .setMarca(view.getMarcaAuto())
                .setKm(view.getKmAuto().orElse(-1))
                .setNumeroPorte(view.getNumeroPorte().orElse(0))
                .setCilindrata(view.getCilindrata().orElse(0))
                .setPrezzoMax(view.getPrezzoMax().orElse(0.0));

        if (perAutoUsate) {
            filtroBuilder.setStatoMacchina(StatoMacchina.USATO);
        }

        return filtroBuilder.build();
    }
    
    private List<ElementoListino> filtraSuggerimenti(Listino listino) {
        return listino.getListino().stream()
            .filter(elemento -> elemento.getPrezzo() < 20000)
            .collect(Collectors.toList());
    }
}
