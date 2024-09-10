package concessionario.view.concessionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import concessionario.controller.AutonoleggioController;
import concessionario.model.autonoleggio.Autonoleggio;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;
import concessionario.model.repartoVendita.Preventivo;
import concessionario.view.autonoleggio.AutonoleggioViewImpl;

public class ConcessionarioViewImpl implements ConcessionarioView {

    private final JFrame frame;
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

        // Panel per i bottoni
        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margine tra i bottoni

        // Bottone Anagrafica Cliente
        JButton bAnagrafica = new JButton("Anagrafica Cliente");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBottoni.add(bAnagrafica, gbc);
        bAnagrafica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.ANAGRAFICA_CLIENTI);
            }
        });

        // Bottone Gestione Auto
        JButton bGestioneAuto = new JButton("Gestione Auto");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBottoni.add(bGestioneAuto, gbc);
        bGestioneAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.GESTIONE_AUTO);
            }
        });

        // Bottone Noleggia Auto
        JButton bNoleggiaAuto = new JButton("Noleggia Auto");
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelBottoni.add(bNoleggiaAuto, gbc);
        bNoleggiaAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.NOLEGGIA_AUTO);
            }
        });

        // Bottone Leasing Auto
        JButton bLeasingAuto = new JButton("Leasing Auto");
        gbc.gridx = 3;
        gbc.gridy = 0;
        panelBottoni.add(bLeasingAuto, gbc);
        bLeasingAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.LEASING_AUTO);
            }
        });

        // Bottone Officina
        JButton bOfficina = new JButton("Officina");
        gbc.gridx = 4;
        gbc.gridy = 0;
        panelBottoni.add(bOfficina, gbc);
        bOfficina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.RIPARA_AUTO);
            }
        });

        // Panel per mostrare i preventivi completati
        JPanel panelPreventivi = new JPanel();
        panelPreventivi.setLayout(new BoxLayout(panelPreventivi, BoxLayout.Y_AXIS));
        panelPreventivi.add(new JLabel("Registro Vendite"));

        JButton aggiornaVendite = new JButton("Aggiorna Vendite");
        panelPreventivi.add(aggiornaVendite);
        aggiornaVendite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoConcessionario.AGGIORNA_VENDITE);
            }
        });

        // Spazio verticale tra il pulsante e la tabella
        panelPreventivi.add(Box.createVerticalStrut(10));

        // Creazione della tabella
        String[] nomiColonne = {"Marca", "Modello", "StatoMacchina", "Cognome", "Nome", "Prezzo"};
        modelloTabella = new DefaultTableModel(nomiColonne, 0);
        tabella = new JTable(modelloTabella);
        JScrollPane scrollPane = new JScrollPane(tabella);
        panelPreventivi.add(scrollPane);

        // Aggiungi i pannelli al frame
        this.frame.add(panelBottoni, BorderLayout.PAGE_START);
        this.frame.add(panelPreventivi, BorderLayout.CENTER);
    }

    /**
     * Metodo per notificare un evento
     * @param tipoEvento
     */
    private void notifyEvent(EventoConcessionario tipoEvento) {
        for (ConcessionarioViewObserver concessionarioViewObserver : osservatori) {
            concessionarioViewObserver.eventNotified(tipoEvento);
        }
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
        for (Preventivo preventivo : preventivi) {
            Object[] rowData = {
                preventivo.getAuto().getMarca(),
                preventivo.getAuto().getModello(),
                preventivo.getAuto().getStatoMacchina(),
                preventivo.getCliente().getCognome(),
                preventivo.getCliente().getNome(),
                preventivo.getPrezzoTotale()
            };
            modelloTabella.addRow(rowData);
        }
    }
}
