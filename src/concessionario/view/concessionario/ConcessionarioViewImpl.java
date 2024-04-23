package concessionario.view.concessionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concessionario.controller.AutonoleggioController;
import concessionario.model.Preventivo;
import concessionario.model.autonoleggio.Autonoleggio;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;
import concessionario.view.AutonoleggioViewImpl;


public class ConcessionarioViewImpl implements ConcessionarioView {

    private final JFrame frame;
    //private final JList<Preventivo> preventiviCompletati;
    //private final DefaultListModel<Preventivo> modelloLista;
    private final JTable tabella;
    private final DefaultTableModel modelloTabella;

    private List<ConcessionarioViewObserver> osservatori;

    public ConcessionarioViewImpl() {
        this.osservatori = new LinkedList<>();
        // menu principale
        this.frame = new JFrame("CONCESSIONARIO");
        this.frame.setSize(1280, 720);
        this.frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        // bottone anagrafica cliente
        JButton bAnagrafica = new JButton("Anagrafica Cliente");
        panel.add(bAnagrafica);
        bAnagrafica.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.ANAGRAFICA_CLIENTI);
            }
        });

        // bottone gestione auto
        JButton bGestioneAuto = new JButton("Gestione Auto");
        panel.add(bGestioneAuto);
        bGestioneAuto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.GESTIONE_AUTO);
            }
        });

        JButton bNoleggiaAuto = new JButton("Noleggia Auto");
        panel.add(bNoleggiaAuto);
        bNoleggiaAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.NOLEGGIA_AUTO);
            }
        });

        JButton bLeasingAuto = new JButton("Leasing Auto");
        panel.add(bLeasingAuto);
        bLeasingAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.LEASING_AUTO);
            }
        });

        // panel per mostrare i preventivi completati
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(new JLabel("Registro Vendite"));
        JButton aggiornaVendite = new JButton("Aggiorna Vendite");
        panel1.add(aggiornaVendite);
        aggiornaVendite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.AGGIORNA_VENDITE);
            }
            
        });

        panel1.add(Box.createVerticalStrut(10)); // Aggiunge uno spazio verticale tra il pulsante e la tabella
        String nomiColonne[] = {"Marca" , "Modello", "StatoMacchina", "Cognome", "Nome", "Prezzo"};
        modelloTabella = new DefaultTableModel(nomiColonne,0);
        tabella = new JTable(modelloTabella);
        panel1.add(tabella);


        this.frame.add(panel, BorderLayout.PAGE_START);
        this.frame.add(panel1, BorderLayout.CENTER);
    }

    /**
     * metodo per notificare un evento
     * @param tipoEvento
     */
    private void notifyEvent(EventoConcessionario tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(tipoEvento);
        }
        // sarebbe meglio spostarli nel main 
        if (tipoEvento == EventoConcessionario.NOLEGGIA_AUTO) {
            Autonoleggio autonoleggio = new Autonoleggio();
            autonoleggio.getAutomobili().addAll(new FactoryAutomobiliNoleggio().creaAutoRandom());
            AutonoleggioController autonoleggioController = new AutonoleggioController(autonoleggio);
            AutonoleggioViewImpl autonoleggioView = new AutonoleggioViewImpl(autonoleggioController);
            autonoleggioView.setVisible(true);
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
       modelloTabella.setRowCount(0);
       for(Preventivo preventivo : preventivi) {
            Object [] rowData = { preventivo.getAuto().getMarca(),
                                  preventivo.getAuto().getModello(),
                                  preventivo.getAuto().geStatoMacchina(),
                                  preventivo.getCliente().getCognome(),
                                  preventivo.getCliente().getNome(),
                                  preventivo.getPrezzoTotale() };
            modelloTabella.addRow(rowData);
        }
    }
}