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
// Thread che setta un auto a non disponibile per 5 minuti dopo che viene noleggiata
        new Thread(() -> {
            try {
                Thread.sleep(5 * 60 * 1000); 
                if (!auto.tempoNoleggio()) {
                    auto.setStato(DisponibilitàAuto.Disponibile);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
}



