package concessionario.controller;


import concessionario.model.officina.OfficinaModel;
import concessionario.view.concessionario.ConcessionarioViewObserver;
import concessionario.view.concessionario.EventoConcessionario;
import concessionario.view.officina.OfficinaView;
import concessionario.model.automobile.Automobile;
import concessionario.model.listino.ElementoListino;
import java.util.stream.Collectors;



public class OfficinaController implements ConcessionarioViewObserver {

	  private final OfficinaModel officinaModel;
	    private final OfficinaView officinaView;

	    public OfficinaController(OfficinaModel officinaModel, OfficinaView officinaView) {
	        this.officinaModel = officinaModel;
	        this.officinaView = officinaView;
	        this.officinaView.registraOsservatore(this);

	        // Mostra inizialmente tutte le auto disponibili
	        aggiornaVistaAutoDisponibili();
	    }

	    @Override
	    public void eventNotified(EventoConcessionario e) {
	        switch (e) {
	            case RIPARA_AUTO:
	                int selectedIndex = officinaView.getSelectedAutoIndex();
	                if (selectedIndex != -1) {
	                    Automobile selectedAuto = officinaModel.getAutoUsate().getListino().get(selectedIndex).getAutomobile();
	                    officinaModel.getAutoUsate().rimuoviAuto(selectedAuto);
	                    officinaView.rimuoviAutoSelezionata(selectedIndex);
	                    officinaView.mostraMessaggioRiparazioneAvviata(selectedAuto);
	                }
	                break;
	            default:
	                break;
	        }
	    }

	    // Metodo per aggiornare la vista delle auto disponibili
	    private void aggiornaVistaAutoDisponibili() {
	        officinaView.mostraAutoDisponibili(officinaModel.getAutoUsate().getListino().stream()
	                .map(ElementoListino::getAutomobile)
	                .collect(Collectors.toList()));
	    }

}
	


