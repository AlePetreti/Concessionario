package concessionario.automobile;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class FactoryAutomobili {
    
    private Random rnd;

    public FactoryAutomobili() {
        this.rnd = new Random();
    }

    /**
     * 
     * @return un auto generato random
     */
    public Automobile creaAutoRandom1() {
        
        String[] marche = {"MERCEDES", "FIAT", "TOYOTA", "AUDI", "VOLKSWAGEN", "ALFAROMEO", "SUZUKI"};
        int[] numeroPorte = {3,5};
        int[] cilindrata = {1200, 1400, 1600, 1900, 2000, 2200, 3000, 4000};
        int[] cavalli = {75, 90, 116, 120, 150, 190, 210, 350, 400};
        String marcaAuto = marche[rnd.nextInt(marche.length)];
        String modelloAuto = selezionaModello(marcaAuto);
        int numeroPorteAuto = numeroPorte[rnd.nextInt(numeroPorte.length)];
        int cilindrataAuto = cilindrata[rnd.nextInt(cilindrata.length)];
        int cavalliAuto = cavalli[rnd.nextInt(cavalli.length)];
        return new Automobile(modelloAuto, marcaAuto, 0, numeroPorteAuto, cilindrataAuto, cavalliAuto, StatoMacchina.NUOVO); 
    }
    /**
     * 
     * @return una lista di auto generate random
     */
    public List<Automobile> creaAutoRandom() {
        
        List<Automobile> automobili = new ArrayList<Automobile>();
        String[] marche = {"MERCEDES", "FIAT", "TOYOTA", "AUDI", "VOLKSWAGEN", "ALFAROMEO", "SUZUKI"};
        int[] numeroPorte = {3,5};
        int[] cilindrata = {1200, 1400, 1600, 1900, 2000, 2200, 3000, 4000};
        int[] cavalli = {75, 90, 116, 120, 150, 190, 210, 350, 400};
        for(int i = 0; i < 10; i++) {
            String marcaAuto = marche[rnd.nextInt(marche.length)];
            automobili.add(new Automobile(selezionaModello(marcaAuto), marcaAuto, 0, numeroPorte[rnd.nextInt(numeroPorte.length)], cilindrata[rnd.nextInt(cilindrata.length)], cavalli[rnd.nextInt(cavalli.length)], StatoMacchina.NUOVO));
        }
        return automobili;
    }
    
    private String selezionaModello(String marca) {
        
        String[] modelliMercedes ={"ClasseA", "ClasseB", "Classe S", "ClasseG", "classeC"};
        String[] modelliFiat = {"PANDA", "PUNTO", "500", "DOBLO"};
        String[] modelliToyota = {"SUPRA", "YARIS", "RAV4", "AYGO"};
        String[] modelliAudi = {"A8", "RSQ8", "S6", "A3"};
        String[] modelliVolkswagen = {"GOLF", "POLO", "TIGUAN", "TUAREG"};
        String[] modelliAlfaRomeo = {"STELVIO", "GIULIA", "TONALE", "159"};
        String[] modelliSuzuki = {"SWIFT","VITARA","JIMNY","IGNIS"};
        switch (marca) {
            case ("MERCEDES"):
                return modelliMercedes[rnd.nextInt(modelliMercedes.length)];
            case ("FIAT"):
                return modelliFiat[rnd.nextInt(modelliFiat.length)];
            case ("TOYOTA"):
                return  modelliToyota[rnd.nextInt(modelliToyota.length)];
            case ("AUDI"):
                return  modelliAudi[rnd.nextInt(modelliAudi.length)];
            case ("VOLKSWAGEN"):
                return modelliVolkswagen[rnd.nextInt(modelliVolkswagen.length)];
            case ("ALFAROMEO"):
                return modelliAlfaRomeo[rnd.nextInt(modelliAlfaRomeo.length)];
            case ("SUZUKI"):
                return modelliSuzuki[rnd.nextInt(modelliSuzuki.length)];
            default:
            return "modello non disponibile";
        }
    }
    
}
