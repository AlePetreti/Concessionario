package concessionario.controller;

import concessionario.model.listino.Listino;
import concessionario.model.ricercaAuto.CercatoreAuto;
import concessionario.model.ricercaAuto.Filtro;
import concessionario.view.auto.EventoGestioneAuto;
import concessionario.view.auto.GestioneAutoView;
import concessionario.view.auto.GestioneAutoViewObserver;


public class GestioneAutoController implements GestioneAutoViewObserver{

    private final GestioneAutoView view;
    private final Listino listinoAuto;
    private final CercatoreAuto cercatoreAuto;
    private final Filtro filtroAuto;
    private final PreventivoController preventivoController;


    public GestioneAutoController(GestioneAutoView view, Listino listino, PreventivoController preventivoController) {
        this.view = view;
        this.listinoAuto = listino;
        this.cercatoreAuto = new CercatoreAuto();
        this.view.addObserver(this);
        this.filtroAuto = new Filtro();
        this.preventivoController = preventivoController;
    }

    @Override
    public void eventNotified(EventoGestioneAuto e) {
        switch (e) {
            case GESTIONE_AUTO_APERTA:
                view.mostraListino(listinoAuto.getListino());
            break;
            case CERCA_AUTO:
                impostaFiltro();
                Listino autoTrovate = cercatoreAuto.cercaAuto(filtroAuto, listinoAuto);
                view.mostraListino(autoTrovate.getListino());
            break;
            case CERCA_AUTO_USATE:
                /* TODO: da finire quando ci sono le auto usate */
            break;
            case MOSTRA_PREVENTIVO:
                preventivoController.inizializzaPreventivo(view.getElementoListino().getAutomobile());
            break; 
         /* case CONCLUDI_VENDITA:
                Preventivo preventivo = concessionario.generaPreventivo(view.getElementoListino().getAutomobile(), view.getClienteSelezionato(anagraficaClienti.getClienti()));
                concessionario.vendiAuto(preventivo);
            break; */
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
        filtroAuto.setPrezzoMax(view.getPrezzoMax().orElse(0.0));
    }
}
