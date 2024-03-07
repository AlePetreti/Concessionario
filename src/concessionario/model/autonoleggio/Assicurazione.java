package concessionario.model.autonoleggio;

import java.util.Random;

public class Assicurazione {
    public static double calcolaCostoAssicurazione(int giorni, String copertura) {
        double costoAssicurazione = 0.0;

        switch (copertura) {
            case "Copertura Bassa":
                costoAssicurazione = calcolaCostoBasso(giorni);
                break;
            case "Copertura Media":
                costoAssicurazione = calcolaCostoMedia(giorni);
                break;
            case "Copertura Alta":
                costoAssicurazione = calcolaCostoAlta(giorni);
                break;
        }

        return costoAssicurazione;
    }

    private static double calcolaCostoBasso(int giorni) {
        return giorni * (5 * getRandomInt(40, 60));
    }

    private static double calcolaCostoMedia(int giorni) {
        return giorni * (5 * getRandomInt(80, 120));
    }

    private static double calcolaCostoAlta(int giorni) {
        return giorni * (5 * getRandomInt(123, 200));
    }

    private static int getRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}


