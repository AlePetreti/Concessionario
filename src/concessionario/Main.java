package concessionario;

import concessionario.controller.AutonoleggioController;
import concessionario.model.autonoleggio.Autonoleggio;
import concessionario.view.AutonoleggioViewImpl;

public class Main {
    public static void main(String[] args) throws Exception {

    // Autunoleggio
        Autonoleggio autonoleggio = new Autonoleggio();
        autonoleggio.scegliOperazione();
    } 
}
/*public class Main {

    public static void main(String[] args) {
        Autonoleggio autonoleggio = new Autonoleggio();
        AutonoleggioViewImpl view = new AutonoleggioViewImpl();
        AutonoleggioController controller = new AutonoleggioController(autonoleggio, view);

        controller.gestisciAutonoleggio();
    }
}*/
