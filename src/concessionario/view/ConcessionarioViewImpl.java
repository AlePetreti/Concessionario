package concessionario.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import concessionario.model.Preventivo;


public class ConcessionarioViewImpl implements ConcessionarioView {

    private final JFrame frame;
    private final JList<Preventivo> preventiviCompletati;
    private final DefaultListModel<Preventivo> modelloLista;

    private List<ConcessionarioViewObserver> osservatori;

    public ConcessionarioViewImpl() {
        this.osservatori = new LinkedList<>();
        // menu principale
        this.frame = new JFrame("CONCESSIONARIO");
        this.frame.setSize(1280, 720);
        this.frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 200, 10));
        // bottone anagrafica cliente
        JButton bAnagrafica = new JButton("Anagrafica Cliente");
        panel.add(bAnagrafica);
        bAnagrafica.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.ANAGRAFICA_CLIENTI);
            }
        });

        // bottone gestione auto
        JButton bGestioneAuto = new JButton("Gestione Auto");
        panel.add(bGestioneAuto);
        bGestioneAuto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.GESTIONE_AUTO);
            }
        });

        // panel per mostrare i preventivi completati
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Registro Vendite"));
        JButton aggiornaVendite = new JButton("Aggiorna Vendite");
        panel1.add(aggiornaVendite);
        aggiornaVendite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(TipoEvento.AGGIORNA_VENDITE);
            }
            
        });
        modelloLista = new DefaultListModel<>();
        preventiviCompletati = new JList<>(modelloLista);
        preventiviCompletati.setLayoutOrientation(JList.VERTICAL);
        panel1.add(preventiviCompletati);
       


        this.frame.add(panel, BorderLayout.PAGE_START);
        this.frame.add(panel1, BorderLayout.CENTER);
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

    @Override
    public void mostraPreventiviCompletati(List<Preventivo> preventivi) {
        modelloLista.clear();

        for(Preventivo preventivo : preventivi) {
            modelloLista.addElement(preventivo);
        }
    }
}