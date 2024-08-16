package concessionario.model.officina;

import concessionario.model.ElementoListino;
import concessionario.model.Listino;
import concessionario.model.automobile.Automobile;

import java.util.Optional;

public class OfficinaModel {

    private Listino autoUsate;

    // Costruttore che accetta un Listino e assicura che autoUsate non sia null
    public OfficinaModel(Listino auto) {
        this.autoUsate = (auto != null) ? auto : new Listino();
    }

    // Restituisce il listino di auto usate
    public Listino getAutoUsate() {
        return this.autoUsate;
    }

    // Cerca un'auto nel listino dato il nome della marca, restituendo un Optional
    public Optional<Automobile> cercaAuto(Listino listino, String nome) {
        if (listino == null || listino.getListino() == null) {
            return Optional.empty(); // Restituisce un Optional vuoto se il listino è null
        }

        // Utilizza gli stream per trovare l'auto con il nome specificato
        return listino.getListino().stream()
                .map(ElementoListino::getAutomobile) // Mappa gli elementi del listino in automobili
                .filter(auto -> auto.getMarca().equalsIgnoreCase(nome)) // Filtra per marca
                .findFirst(); // Restituisce il primo risultato trovato come Optional
    }
}

