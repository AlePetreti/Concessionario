package concessionario.cliente;

import concessionario.automobile.Automobile;
import concessionario.automobile.StatoMacchina;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Sconto {

    private Cliente c;
    private Automobile m;

    public Sconto(Cliente c, Automobile m) {
        this.c = c;
        this.m = m;
    }

    public boolean enableDiscount() {

        boolean enableDiscount = false;
        if (this.c.getAnno() >= 3 && this.m.geStatoMacchina().equals(StatoMacchina.NUOVO)) {
            enableDiscount = true;
        } else {
            enableDiscount = false;
        }
        return enableDiscount;
    }

    public int valueDiscount() {
        int valueDiscount = 0;

        if (this.c.getAnno() >= 10) {
            valueDiscount = 15;

        } else if (this.c.getAnno() >= 5) {
            valueDiscount = 10;

        } else if (this.c.getAnno() >= 3) {
            valueDiscount = 5;

        }

        return valueDiscount;
    }

    public BigDecimal calcolaPrezzoScontato() {
        BigDecimal prezzoOriginale = BigDecimal.valueOf(m.getPrezzo());
        BigDecimal percentualeSconto = BigDecimal.valueOf(valueDiscount());

        if (enableDiscount()) {
            BigDecimal sconto = prezzoOriginale.multiply(percentualeSconto).divide(BigDecimal.valueOf(100), 2,
                    RoundingMode.HALF_UP);
            return prezzoOriginale.subtract(sconto).setScale(2, RoundingMode.HALF_UP);
        } else {
            return prezzoOriginale.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public String print(boolean b) {

        String s;
        if (b == true) {
            s = "e' Disponibile";
        } else {
            s = "Non e' Disponibile";
        }

        return s;
    }

    @Override
    public String toString() {
        BigDecimal prezzoScontato = calcolaPrezzoScontato();

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String prezzoOriginaleFormatted = decimalFormat.format(m.getPrezzo());
        String prezzoScontatoFormatted = decimalFormat.format(prezzoScontato);

        return "Lo sconto per " + c.getNome() + " " + c.getCognome() + " sulla macchina selezionata "
                + this.print(this.enableDiscount()) + " [" + this.valueDiscount() + "%]. Prezzo originale: "
                + prezzoOriginaleFormatted + ", Prezzo scontato: " + prezzoScontatoFormatted;

    }
}
