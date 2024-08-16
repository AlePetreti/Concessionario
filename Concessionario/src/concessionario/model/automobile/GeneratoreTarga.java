package concessionario.model.automobile;

import java.util.Random;

public class GeneratoreTarga{
    
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final Random RANDOM = new Random();

    public static String generateTarga() {
        StringBuilder targa = new StringBuilder();

        // Generate 2 letters
        for (int i = 0; i < 2; i++) {
            targa.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }

        // Generate 3 digits
        for (int i = 0; i < 3; i++) {
            targa.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
        }

        // Generate 2 letters
        for (int i = 0; i < 2; i++) {
            targa.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }

        return targa.toString();
    }

    
}
