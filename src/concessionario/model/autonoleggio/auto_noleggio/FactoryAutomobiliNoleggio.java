package concessionario.model.autonoleggio.auto_noleggio;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class FactoryAutomobiliNoleggio {
    
    private Random rnd;

    public FactoryAutomobiliNoleggio() {
        this.rnd = new Random();
    }

    /*
     * @return una lista di auto generate random
     */
    public List<AutomobileNoleggio> creaAutoRandom() {
        
        List<AutomobileNoleggio> automobili = new ArrayList<AutomobileNoleggio>();
        String[] marche = {"KIA", "NISSAN", "MINI", "BMW", "FORD", "TESLA", "HONDA"};
        int[] numeroPorte = {3,5};
        int[] cilindrata = {1200, 1500, 1700, 1900, 2000, 2200, 2500};
        int[] cavalli = {75, 90, 116, 120, 150, 190, 210, 350, 400};
        for(int i = 0; i < 10; i++) {
            String marcaAuto = marche[rnd.nextInt(marche.length)];
            automobili.add(new AutomobileNoleggio(selezionaModello(marcaAuto), marcaAuto, numeroPorte[rnd.nextInt(numeroPorte.length)], cilindrata[rnd.nextInt(cilindrata.length)],cavalli[rnd.nextInt(cavalli.length)], DisponibilitàAuto.Disponibile));
        }
        return automobili;
    }
    
    private String selezionaModello(String marca) {
        String[] modelliKia = {"SPORTAGE", "SORENTO", "PICANTO", "EV6", "XCEED"};
        String[] modelliNissan = {"QASHQAI", "LEAF", "JUKE", "TOWNSTAR", "GTR"};
        String[] modelliMini = {"COOPERS", "COOPERD", "COUNTRYMAN", "COOPERSE"};
        String[] modelliBmw = {"SERIE1", "M2", "SERIE5", "I4"};
        String[] modelliFord = {"FOCUS", "PUMA", "KUGA", "FIESTA"};
        String[] modelliTesla = {"MODEL3", "MODELS", "MODELY", "MODELSPLAID"};
        String[] modelliHonda = {"CIVIC", "CR-V", "ZR-V", "HR-V"};
    
        switch (marca) {
            case "KIA":
                return modelliKia[rnd.nextInt(modelliKia.length)];
            case "NISSAN":
                return modelliNissan[rnd.nextInt(modelliNissan.length)];
            case "MINI":
                return modelliMini[rnd.nextInt(modelliMini.length)];
            case "BMW":
                return modelliBmw[rnd.nextInt(modelliBmw.length)];
            case "FORD":
                return modelliFord[rnd.nextInt(modelliFord.length)];
            case "TESLA":
                return modelliTesla[rnd.nextInt(modelliTesla.length)];
            case "HONDA":
                return modelliHonda[rnd.nextInt(modelliHonda.length)];
            default:
                return "modello non disponibile";
        }
    }   
}
