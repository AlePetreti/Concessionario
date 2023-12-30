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

        JButton confermaButton = new JButton("Conferma");
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confermaButtonClicked();
            }
        });

        // Imposta un font bold per le etichette a sinistra
        Font boldFont = new Font("Arial", Font.BOLD, 18);

        JLabel autoLabel = new JLabel("Auto Disponibili:");
        autoLabel.setFont(boldFont);

        JLabel durataContrattoLabel = new JLabel("Durata Contratto (anni):");
        durataContrattoLabel.setFont(boldFont);

        JLabel limiteChilometricoLabel = new JLabel("Limite Chilometrico:");
        limiteChilometricoLabel.setFont(boldFont);

        panel.add(autoLabel);
        panel.add(autoDisponibiliComboBox);
        panel.add(durataContrattoLabel);
        panel.add(durataContrattoComboBox);
        panel.add(limiteChilometricoLabel);
        panel.add(limiteChilometricoComboBox);
        panel.add(confermaButton);

        frameLeasingAuto.add(panel);
        centerFrame(frameLeasingAuto);
    }

    private void confermaButtonClicked() {
        // Ottieni i dettagli del contratto
        AutomobileNoleggio selectedAuto = (AutomobileNoleggio) autoDisponibiliComboBox.getSelectedItem();
        int durataContratto = (Integer) durataContrattoComboBox.getSelectedItem();
        int limiteChilometrico = (Integer) limiteChilometricoComboBox.getSelectedItem();

        // Calcola la tariffa mensile (esempio: prezzo mensile randomico tra 100 e 1000, multiplo di 50)
        int tariffaMensile = (new Random().nextInt(19) + 2) * 50;

        // Calcola il prezzo totale dell'auto
        int prezzoTotale = durataContratto * tariffaMensile * 12; // mese * 12 per ottenere il totale in base agli anni

        // Passa ai dettagli del contratto
        showContrattoDetails(selectedAuto, durataContratto, limiteChilometrico, tariffaMensile, prezzoTotale);
    }

    private void showContrattoDetails(AutomobileNoleggio selectedAuto, int durataContratto, int limiteChilometrico,
                                      int tariffaMensile, int prezzoTotale) {
        JFrame frameDettagliContratto = new JFrame("Dettagli Contratto");
        frameDettagliContratto.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel autoLabel = new JLabel("Auto selezionata: " + selectedAuto.getMarca() + " " + selectedAuto.getModello());
        JLabel anniContrattoLabel = new JLabel("Anni di contratto: " + durataContratto);
        JLabel limiteChilometricoLabel = new JLabel("Limite chilometrico: " + limiteChilometrico + " km");
        JLabel tariffaMensileLabel = new JLabel("Tariffa mensile: " + tariffaMensile + " euro");
        JLabel prezzoTotaleLabel = new JLabel("Prezzo Totale dell'auto: " + prezzoTotale + " euro");

        // Imposta un font più grande per le etichette
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
        // Genera un array di valori chilometrici tra 10000 e 50000
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











