import java.util.Random;

public class GeneratoreTarghe {
    public static void main(String[] args) {
        String lettere = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();

        String targa = "";

        // Genera le prime 2 lettere casualmente
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(lettere.length());
            targa += lettere.charAt(index);
        }

        // Genera i 4 numeri casualmente
        for (int i = 0; i < 4; i++) {
            int numero = random.nextInt(10);
            targa += numero;
        }

        // Aggiunge un separatore "-"
        targa += "-";

        // Genera le ultime 2 lettere casualmente
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(lettere.length());
            targa += lettere.charAt(index);
        }

        System.out.println("Targa generata: " + targa);
    }
}