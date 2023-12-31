package concessionario.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import concessionario.model.autonoleggio.auto_noleggio.AutomobileNoleggio;
import concessionario.model.autonoleggio.auto_noleggio.FactoryAutomobiliNoleggio;

public class LeasingAutoViewImpl implements LeasingAutoView {

    private JFrame frameLeasingAuto;
    private JComboBox<AutomobileNoleggio> autoDisponibiliComboBox;
    private JComboBox<Integer> durataContrattoComboBox;
    private JComboBox<Integer> limiteChilometricoComboBox;
    private FactoryAutomobiliNoleggio factoryAutomobiliNoleggio;

    public LeasingAutoViewImpl(FactoryAutomobiliNoleggio factoryAutomobiliNoleggio) {
        this.factoryAutomobiliNoleggio = factoryAutomobiliNoleggio;
        frameLeasingAuto = new JFrame("Leasing Auto");
        frameLeasingAuto.setSize(800, 600);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        autoDisponibiliComboBox = new JComboBox<>();
        durataContrattoComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        limiteChilometricoComboBox = new JComboBox<>(generateChilometricoOptions());

        JButton bottoneConferma = new JButton("Conferma");
        bottoneConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confermaContratto();
            }
        });

        Font boldFont = new Font("Arial", Font.BOLD, 18); // Imposta un font diverso per le etichette di sinistra

        JLabel autoLabel = new JLabel("Auto Disponibili:");
        autoLabel.setFont(boldFont);

        JLabel durataContrattoLabel = new JLabel("Durata Contratto (anni):");
        durataContrattoLabel.setFont(boldFont);

        JLabel limiteChilometricoLabel = new JLabel("Limite Chilometrico (annuale):");
        limiteChilometricoLabel.setFont(boldFont);

        panel.add(autoLabel);
        panel.add(autoDisponibiliComboBox);
        panel.add(durataContrattoLabel);
        panel.add(durataContrattoComboBox);
        panel.add(limiteChilometricoLabel);
        panel.add(limiteChilometricoComboBox);
        panel.add(bottoneConferma);

        frameLeasingAuto.add(panel);
        centerFrame(frameLeasingAuto);
    }

    private void confermaContratto() {
        // Ottieni i dettagli del contratto
        AutomobileNoleggio autoSelezionata = (AutomobileNoleggio) autoDisponibiliComboBox.getSelectedItem();
        int durataContratto = (Integer) durataContrattoComboBox.getSelectedItem();
        int limiteChilometrico = (Integer) limiteChilometricoComboBox.getSelectedItem();

        int tariffaMensile = (new Random().nextInt(19) + 2) * 50;

        int prezzoTotale = durataContratto * tariffaMensile * 12;

        mostraDettagliContratto(autoSelezionata, durataContratto, limiteChilometrico, tariffaMensile, prezzoTotale);

    }

    private void mostraDettagliContratto(AutomobileNoleggio autoSelezionata, int durataContratto, int limiteChilometrico,
                                      int tariffaMensile, int prezzoTotale) {
        JFrame frameDettagliContratto = new JFrame("Dettagli Contratto");
        frameDettagliContratto.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel autoLabel = new JLabel("Auto selezionata: " + autoSelezionata.getMarca() + " " + autoSelezionata.getModello());
        JLabel anniContrattoLabel = new JLabel("Anni di contratto: " + durataContratto);
        JLabel limiteChilometricoLabel = new JLabel("Limite chilometrico: " + limiteChilometrico + " km");
        JLabel tariffaMensileLabel = new JLabel("Tariffa mensile: " + tariffaMensile + " euro");
        JLabel prezzoTotaleLabel = new JLabel("Prezzo Totale dell'auto: " + prezzoTotale + " euro");

        Font labelFont = new Font("Arial", Font.PLAIN, 18);

        autoLabel.setFont(labelFont);
        anniContrattoLabel.setFont(labelFont);
        limiteChilometricoLabel.setFont(labelFont);
        tariffaMensileLabel.setFont(labelFont);
        prezzoTotaleLabel.setFont(labelFont);

        panel.add(autoLabel);
        panel.add(anniContrattoLabel);
        panel.add(limiteChilometricoLabel);
        panel.add(tariffaMensileLabel);
        panel.add(prezzoTotaleLabel);

        frameDettagliContratto.add(panel);
        centerFrame(frameDettagliContratto);
        frameDettagliContratto.setVisible(true);
    }

    private Integer[] generateChilometricoOptions() {
        int minChilometrico = 10000;
        int maxChilometrico = 50000;
        int step = 1000;

        int numOptions = (maxChilometrico - minChilometrico) / step + 1;
        Integer[] options = new Integer[numOptions];

        for (int i = 0; i < numOptions; i++) {
            options[i] = minChilometrico + i * step;
        }

        return options;
    }

    private void centerFrame(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    @Override
    public void mostraAuto(List<AutomobileNoleggio> autoList) {
        SwingUtilities.invokeLater(() -> {
            DefaultComboBoxModel<AutomobileNoleggio> model = new DefaultComboBoxModel<>();
            List<AutomobileNoleggio> autoDisponibili = factoryAutomobiliNoleggio.creaAutoRandom();
            if (autoDisponibili != null) {
                for (AutomobileNoleggio auto : autoDisponibili) {
                    model.addElement(auto);
                }
            }
            autoDisponibiliComboBox.setModel(model);
            frameLeasingAuto.setVisible(true);
        });
    }
}











