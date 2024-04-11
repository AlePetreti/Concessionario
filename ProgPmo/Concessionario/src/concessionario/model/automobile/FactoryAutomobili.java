package concessionario.model.automobile;
import java.util.Random;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class FactoryAutomobili {
    
    private Random rnd;
    private StatoMacchina stato;
    private Automobile auto;

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
        StatoMacchina[] statoMacchina = stato.values();	 //prende valori ENUM e genera casualmente lo stato della macchina
        String marcaAuto = marche[rnd.nextInt(marche.length)];
        String modelloAuto = selezionaModello(marcaAuto);
        int numeroPorteAuto = numeroPorte[rnd.nextInt(numeroPorte.length)];
        int cilindrataAuto = cilindrata[rnd.nextInt(cilindrata.length)];
        int cavalliAuto = cavalli[rnd.nextInt(cavalli.length)];
        int statoUsura = rnd.nextInt(statoMacchina.length);
        Optional<String> targaOptional;
        StatoMacchina controllo = statoMacchina[statoUsura];
        // controllo se l'auto è usata così da poter inserire la targa
        if (controllo == StatoMacchina.USATO) {
            String targa = creaTarga();
            targaOptional = Optional.of(targa);
        } else {
            targaOptional = Optional.empty();
        }
        
        return new Automobile(modelloAuto, marcaAuto, 0, numeroPorteAuto, cilindrataAuto, cavalliAuto, controllo, targaOptional);
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
 
    
    
	private static String creaTarga(){
		
		Random rand = new Random();
        char lettera1 = (char) (rand.nextInt(26) + 'A');
        char lettera2 = (char) (rand.nextInt(26) + 'A');
        char lettera3 = (char) (rand.nextInt(26) + 'A');
        
        // Generiamo casualmente una sequenza di tre numeri per le ultime tre posizioni.
        int numero1 = rand.nextInt(10);
        int numero2 = rand.nextInt(10);
        int numero3 = rand.nextInt(10);
        
        // Concateniamo le parti per formare la targa completa.
        String targa = "" + lettera1 + lettera2 + lettera3 + numero1 + numero2 + numero3;
        
        return targa;
    
		}
  
    
    
}
