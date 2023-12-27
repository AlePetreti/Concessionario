package concessionario.model.autonoleggio;

import java.util.ArrayList;
import java.util.List;

import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;
import concessionario.model.autonoleggio.auto_noleggio.DisponibilitàAuto;


public class Autonoleggio {

    private List<AutomobileNoleggio> automobili;

    public Autonoleggio() {
        this.automobili = new ArrayList<>();
    }

    public List<AutomobileNoleggio> getAutomobili() {
        return automobili;
    }

    public void noleggiaAuto(AutomobileNoleggio auto, int giorni) {
        auto.setStato(DisponibilitàAuto.NonDisponibile);

        double costoMinimo = 20.0;
        double costoMassimo = 50.0;
        double costoTotale = giorni * (costoMinimo + Math.random() * (costoMassimo - costoMinimo + 1));
        costoTotale = Math.round(costoTotale * 100.0) / 100.0;
        auto.setCostoNoleggio(costoTotale);
    }
    
}


