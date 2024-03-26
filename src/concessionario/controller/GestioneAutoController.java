package concessionario.controller;

import java.util.LinkedList;
import java.util.List;

import concessionario.model.CercatoreAuto;
import concessionario.model.ElementoListino;
import concessionario.model.Filtro;
import concessionario.model.Listino;
import concessionario.model.automobile.Automobile;
import concessionario.view.ConcessionarioViewObserver;
import concessionario.view.Event;
import concessionario.view.GestioneAutoView;

public class GestioneAutoController implements ConcessionarioViewObserver{

    private GestioneAutoView view;
    private Listino listinoAuto;
    private CercatoreAuto cercatoreAuto;
    private Filtro filtroAuto;


    public GestioneAutoController(GestioneAutoView view, Listino listino) {
        this.view = view;
        this.listinoAuto = listino;
        this.cercatoreAuto = new CercatoreAuto();
        this.view.addObserver(this);
        this.filtroAuto = new Filtro();
    }

    @Override
    public void eventNotified(Event e) {
        switch (e.getTipoEvento()) {
            case GESTIONE_AUTO_APERTA:
                view.mostraListino(listinoAuto.getListino());
            break;
            case CERCA_AUTO:
                impostaFiltro();
                Listino autoTrovate = cercatoreAuto.cercaAuto(filtroAuto, listinoAuto);
                view.mostraListino(autoTrovate.getListino());
            break;
            default:
            break;
        }
    }

    private void impostaFiltro() {
        filtroAuto.setModello(view.getModelloAuto());
        filtroAuto.setMarca(view.getMarcaAuto());
        filtroAuto.setKm(view.getKmAuto().orElse(-1));
        filtroAuto.setNumeroPorte(view.getNumeroPorte().orElse(0));
        filtroAuto.setCilindrata(view.getCilindrata().orElse(0));
        // filtroAuto.setPrezzoMax(view.getPrezzoMax().orElse(0.0));
    }
}
