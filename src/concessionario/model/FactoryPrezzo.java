package concessionario.model;

import java.util.Random;

public class FactoryPrezzo {
    private static final double PREZZO_MIN = 10000.0;
    private static final double PREZZO_MAX = 50000.0;
    private static Random rand;

    public FactoryPrezzo() {
        rand = new Random();
    }

    public static double generaPrezzo() {
        double prezzo = PREZZO_MIN + (rand.nextDouble() * (PREZZO_MAX - PREZZO_MIN));
        return Math.round(prezzo * 100.0) / 100.0;
    }
}
