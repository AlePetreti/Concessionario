package concessionario.view;


import concessionario.controller.AutonoleggioController;
import concessionario.model.autonoleggio.Assicurazione;
import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AutonoleggioView extends JFrame {
    private AutonoleggioController controller;

    private JList<AutomobileNoleggio> listaAutoNoleggio;
    private JTextArea dettagliAuto;
    private JButton bottoneNoleggia;
    private JComboBox<String> coperturaAssicurativa;
    private JTextField durataNoleggioField;

    public AutonoleggioView(AutonoleggioController controller) {
        this.controller = controller;

        initializeUI();
        aggiornaListaAuto();
    }

    private void initializeUI() {
        setTitle("Autonoleggio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Dimension preferredSize = new Dimension(1440, 720);
        setPreferredSize(preferredSize);

        coperturaAssicurativa = new JComboBox<>(new String[]{"Copertura Bassa", "Copertura Media", "Copertura Alta"});
        coperturaAssicurativa.setSelectedIndex(0); // Imposta il default su Copertura Bassa

        JLabel labelCopertura = new JLabel("Copertura Assicurativa:");
        labelCopertura.setFont(new Font("Arial", Font.BOLD, 14));

        listaAutoNoleggio = new JList<>();
        dettagliAuto = new JTextArea();
        bottoneNoleggia = new JButton("Noleggia");

        JLabel labelAutoDisponibili = new JLabel("Auto Disponibili:");
        JLabel labelAutoSelezionata = new JLabel("Auto Selezionata:");

        listaAutoNoleggio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaAutoNoleggio);

        JPanel assicurazionePanel = new JPanel(new FlowLayout());
        assicurazionePanel.add(labelCopertura);
        assicurazionePanel.add(coperturaAssicurativa);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(labelAutoDisponibili, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(assicurazionePanel, BorderLayout.SOUTH);

        JPanel durataNoleggioPanel = new JPanel(new FlowLayout());
        JLabel labelDurataNoleggio = new JLabel("Inserire durata del noleggio (Giorni):");
        durataNoleggioField = new JTextField(5);
        durataNoleggioPanel.add(labelDurataNoleggio);
        durataNoleggioPanel.add(durataNoleggioField);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(labelAutoSelezionata, BorderLayout.NORTH);
        rightPanel.add(dettagliAuto, BorderLayout.CENTER);
        rightPanel.add(durataNoleggioPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(bottoneNoleggia, BorderLayout.SOUTH);

        bottoneNoleggia.addActionListener(e -> noleggiaAuto());

        listaAutoNoleggio.addListSelectionListener(e -> aggiornaDettagliAuto());
        pack();
        setLocationRelativeTo(null);
    }

    private void aggiornaListaAuto() {
        List<AutomobileNoleggio> automobili = controller.getAutomobili();
        listaAutoNoleggio.setListData(automobili.toArray(new AutomobileNoleggio[0]));
    }

    private void aggiornaDettagliAuto() {
        AutomobileNoleggio autoSelezionata = listaAutoNoleggio.getSelectedValue();
        if (autoSelezionata != null) {
            dettagliAuto.setText(autoSelezionata.toString() + "\nStato: " + autoSelezionata.getStato());
        }
    }

    private void noleggiaAuto() {
        AutomobileNoleggio autoSelezionata = listaAutoNoleggio.getSelectedValue();
        if (autoSelezionata != null) {
            try {
                String coperturaSelezionata = (String) coperturaAssicurativa.getSelectedItem();
                int durataNoleggio = Integer.parseInt(durataNoleggioField.getText());

                double costoAssicurazione = Assicurazione.calcolaCostoAssicurazione(durataNoleggio, coperturaSelezionata);

                controller.noleggiaAuto(autoSelezionata, durataNoleggio);
                aggiornaListaAuto();
                aggiornaDettagliAuto();

                double costoNoleggio = autoSelezionata.getCostoNoleggio() + costoAssicurazione;

                JOptionPane.showMessageDialog(this,
                        "Auto noleggiata con successo!\nCopertura assicurativa: " + coperturaSelezionata +
                                "\nDurata del noleggio: " + durataNoleggio + " giorni" + "\nCosto Totale: " + costoNoleggio + " euro",
                        "Noleggio Confermato",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Inserire una durata di noleggio valida (numero intero).", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona un'auto prima di noleggiare.");
        }
    }
}