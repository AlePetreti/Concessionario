package concessionario.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import concessionario.model.ElementoListino;
import concessionario.model.automobile.Automobile;

public class GestioneAutoImpl implements GestioneAutoView{

    JFrame frameAuto;
    JTextArea listinoAuto;

    private List<ConcessionarioViewObserver> osservatori;
    

    public GestioneAutoImpl() {
        this.osservatori = new LinkedList<>();
        frameAuto = new JFrame("GestioneAuto");
        this.frameAuto.setSize(1280, 720);
        this.frameAuto.setLayout(new BorderLayout());
        // bottone acquisto da privato 
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton bAcquistoPrivato = new JButton("Acquista auto da privato");
        panel.add(bAcquistoPrivato);

        // lista delle auto 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(new JLabel("Listino Auto"));
        panel1.add(Box.createRigidArea(new Dimension(0, 8)));
        listinoAuto = new JTextArea();
        listinoAuto.setLineWrap(true);
        listinoAuto.setEditable(false);
        listinoAuto.setOpaque(false);
        panel1.add(listinoAuto);


        this.frameAuto.add(panel, BorderLayout.PAGE_START);
        this.frameAuto.add(panel1, BorderLayout.CENTER);
        
    }

    private void notifyEvent(TipoEvento tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(new Event(tipoEvento));
        }
    }

    @Override
    public void mostraGestioneAuto() {
        frameAuto.setVisible(true);
        notifyEvent(TipoEvento.GESTIONE_AUTO_APERTA);

    }

    @Override
    public void addObserver(ConcessionarioViewObserver observer) {
        osservatori.add(observer);
    }

    @Override
    public void removeObserver(ConcessionarioViewObserver observer) {
        osservatori.remove(observer);
    }

    @Override
    public void mostraListino(List<ElementoListino> listino) {
        String listaAuto = new String();

        for (ElementoListino elementoListino : listino) {
            listaAuto = listaAuto + formattaElementoListino(elementoListino) + "\n";
        }
        listinoAuto.setText(listaAuto);
    }

    private String formattaElementoListino(ElementoListino elemento) {
        String formatoStringa = new String();
        formatoStringa  = elemento.getAutomobile().getMarca() + " " + elemento.getAutomobile().getModello() + " " +  elemento.getPrezzo();
        return formatoStringa;
    }
    
}
