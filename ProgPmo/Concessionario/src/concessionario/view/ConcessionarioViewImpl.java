package concessionario.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


public class ConcessionarioViewImpl implements ConcessionarioView {

    private JFrame frame;

    private List<ConcessionarioViewObserver> osservatori;

    public ConcessionarioViewImpl() {
        this.osservatori = new LinkedList<>();
        // menu principale
        this.frame = new JFrame("CONCESSIONARIO");
        this.frame.setSize(400, 300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton bAnagrafica = addButton("Anagrafica Cliente", frame);
        bAnagrafica.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.ANAGRAFICA_CLIENTI);
            }
        });
        JButton bGestioneAuto = addButton("Gestione Auto", frame);
        bGestioneAuto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.GESTIONE_AUTO);
            }
        });
        
        addButton("Acquista da privato", frame); 
    }
    
    private static JButton addButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        return button;
    }
    
    /**
     * metodo per notificare un evento
     * @param tipoEvento
     */
    private void notifyEvent(TipoEvento tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(new Event(tipoEvento));
        }
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }
    
    @Override
    public void addObserver(ConcessionarioViewObserver observer) {
        osservatori.add(observer);
    }

    @Override
    public void removeObserver(ConcessionarioViewObserver observer) {
        osservatori.remove(observer);
    }
}