package concessionario.model.autonoleggio.auto_noleggio;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class FactoryAutomobiliNoleggio {
    
    private Random rnd;

    public FactoryAutomobiliNoleggio() {
        this.rnd = new Random();
    }

    /**
     * 
     * @return un auto generato random
     */
    public AutomobileNoleggio creaAutoRandom2() {
        
        String[] marche = {"KIA", "NISSAN", "MINI", "BMW", "FORD", "TESLA", "HONDA"};
        int[] numeroPorte = {3,5};
        int[] cilindrata = {1200, 1400, 1600, 1900, 2000, 2200, 3000, 4000};
        String marcaAuto = marche[rnd.nextInt(marche.length)];
        String modelloAuto = selezionaModello(marcaAuto);
        int numeroPorteAuto = numeroPorte[rnd.nextInt(numeroPorte.length)];
        int cilindrataAuto = cilindrata[rnd.nextInt(cilindrata.length)];
        return new AutomobileNoleggio(modelloAuto, marcaAuto, 0, numeroPorteAuto, cilindrataAuto, DisponibilitàAuto.Disponibile); 
    }
    /**
     * 
     * @return una lista di auto generate random
     */
    public List<AutomobileNoleggio> creaAutoRandom() {
        
        List<AutomobileNoleggio> automobili = new ArrayList<AutomobileNoleggio>();
        String[] marche = {"KIA", "NISSAN", "MINI", "BMW", "FORD", "TESLA", "HONDA"};
        int[] numeroPorte = {3,5};
        int[] cilindrata = {1200, 1400, 1600, 1900, 2000, 2200, 3000, 4000};
        for(int i = 0; i < 10; i++) {
            String marcaAuto = marche[rnd.nextInt(marche.length)];
            automobili.add(new AutomobileNoleggio(selezionaModello(marcaAuto), marcaAuto, 0, numeroPorte[rnd.nextInt(numeroPorte.length)], cilindrata[rnd.nextInt(cilindrata.length)],DisponibilitàAuto.Disponibile));
        }
        return automobili;
    }
    
    private String selezionaModello(String marca) {
        
        String[] modelliKia ={"SPORTAGE", "SORENTO", "PICANTO", "EV6", "XCEED"};
        String[] modelliNissan = {"QASHQAI", "LEAF", "JUKE", "TOWNSTAR", "GTR"};
        String[] modelliMini = {"COOPERS", "COOPERD", "COUNTRYMAN", "COOPERSE"};
        String[] modelliBmw = {"SERIE1", "M2", "SERIE5", "I4"};
        String[] modelliFord = {"FOCUS", "PUMA", "KUGA", "FIESTA"};
        String[] modelliTesla = {"MODEL3", "MODELS", "MODELY", "MODELSPLAID"};
        String[] modelliHonda = {"CIVIC","CR-V","ZR-V","HR-V"};
        switch (marca) {
            case ("MERCEDES"):
                return modelliKia[rnd.nextInt(modelliKia.length)];
            case ("FIAT"):
                return modelliNissan[rnd.nextInt(modelliNissan.length)];
            case ("TOYOTA"):
                return  modelliMini[rnd.nextInt(modelliMini.length)];
            case ("AUDI"):
                return  modelliBmw[rnd.nextInt(modelliBmw.length)];
            case ("VOLKSWAGEN"):
                return modelliFord[rnd.nextInt(modelliFord.length)];
            case ("ALFAROMEO"):
                return modelliTesla[rnd.nextInt(modelliTesla.length)];
            case ("SUZUKI"):
                return modelliHonda[rnd.nextInt(modelliHonda.length)];
            default:
            return "modello non disponibile";
        }
    }
    
}
