package concessionario.controller;

import java.util.stream.Collectors;
import concessionario.model.ElementoListino;
import concessionario.model.RegistroVendite;
import concessionario.model.officina.OfficinaModel;
import concessionario.view.anagrafica.AnagraficaClientiView;
import concessionario.view.auto.GestioneAutoView;
import concessionario.view.concessionario.ConcessionarioView;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.concessionario.EventoConcessionario;
import concessionario.view.officina.OfficinaView;
import concessionario.view.LeasingAutoView;

public class ConcessionarioController implements ConcessionarioViewObserver {

    private final ConcessionarioView view;
    private final AnagraficaClientiView viewAnagrafica;
    private final GestioneAutoView viewGestioneAuto;
    private final RegistroVendite registroVendite;
    private final LeasingAutoView viewLeasingAuto;
    private final OfficinaView viewOfficina;
    private final OfficinaModel officina;  // Aggiunto il modello OfficinaModel

    public ConcessionarioController(ConcessionarioView view, 
                                    AnagraficaClientiView viewAnagrafica, 
                                    GestioneAutoView viewGestioneAuto, 
                                    RegistroVendite registroVendite, 
                                    LeasingAutoView viewLeasingAuto, 
                                    OfficinaView viewOfficina, 
                                    OfficinaModel officina) {
        this.view = view;
        this.viewAnagrafica = viewAnagrafica;
        this.viewGestioneAuto = viewGestioneAuto;
        this.viewLeasingAuto = viewLeasingAuto;
        this.registroVendite = registroVendite;
        this.viewOfficina = viewOfficina;
        this.officina = officina;  // Inizializza il modello OfficinaModel
        this.view.addObserver(this); 
    }

    @Override
    public void eventNotified(EventoConcessionario e) {
        switch (e) {
            case ANAGRAFICA_CLIENTI:
                viewAnagrafica.mostraAnagraficaClienti();
                break;
            case GESTIONE_AUTO:
                viewGestioneAuto.mostraGestioneAuto();
                break;
            case LEASING_AUTO:
                viewLeasingAuto.mostraAuto(null);
                break;
            case AGGIORNA_VENDITE:
                view.mostraPreventiviCompletati(registroVendite.getListaPreventivi());
                break;
            case RIPARA_AUTO:
                // Recupera le auto usate e le mostra nella vista dell'officina
                viewOfficina.mostraAutoDisponibili(officina.getAutoUsate().getListino().stream()
                    .map(ElementoListino::getAutomobile)
                    .collect(Collectors.toList()));
                viewOfficina.setVisible(true); // Mostra la finestra dell'officina
                break;
            default:
                break;
        }
    }
}