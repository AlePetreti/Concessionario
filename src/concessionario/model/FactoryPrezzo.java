package concessionario.model;

import java.util.Random;

public class FactoryPrezzo {
    private static final double PREZZO_MINIMO = 10000.0;
    private static final double PREZZO_MASSIMO = 50000.0;
    private static Random rand;

    public FactoryPrezzo() {
        rand = new Random();
    }

    public static double generaPrezzo() {
        double prezzo = PREZZO_MINIMO + (rand.nextDouble() * (PREZZO_MASSIMO - PREZZO_MINIMO));
        return Math.round(prezzo * 100.0) / 100.0;
    }
}
