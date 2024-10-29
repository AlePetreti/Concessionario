package concessionario.view.preventivo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;

public class PreventivoViewImpl implements PreventivoView {

    private final List<PreventivoViewObserver> osservatori;
    private final JFrame framePreventivo;
    private final JTable specificheAutoTable;
    private final JLabel prezzoTotale;
    private final JComboBox<String> boxClienti;

    public PreventivoViewImpl() {
        this.osservatori = new LinkedList<>();

        framePreventivo = new JFrame("Preventivo");
        framePreventivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePreventivo.setSize(1280, 720);
        framePreventivo.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton b1 = new JButton("Concludi Vendita");
        bottomPanel.add(b1, BorderLayout.EAST);
        b1.addActionListener(e -> {
            notifyEvent(EventoPreventivo.CONCLUDI_VENDITA);
            framePreventivo.dispose();
        });
        this.prezzoTotale = new JLabel("Prezzo: ");
        bottomPanel.add(prezzoTotale, BorderLayout.WEST);

        // Pannello per le specifiche dell'auto
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(new JLabel("Specifiche auto"), BorderLayout.NORTH);

        // Tabella per le specifiche dell'auto
        specificheAutoTable = new JTable(new DefaultTableModel(new Object[]{"Proprietà", "Valore"}, 0));
        JScrollPane scrollPane = new JScrollPane(specificheAutoTable);
        panel1.add(scrollPane, BorderLayout.CENTER);

        // Pannello per la selezione dei clienti
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        boxClienti = new JComboBox<>();
        boxClienti.setPreferredSize(new Dimension(300, 30));
        panel2.add(boxClienti, BorderLayout.CENTER);

        this.framePreventivo.add(bottomPanel, BorderLayout.PAGE_END);
        this.framePreventivo.add(panel1, BorderLayout.CENTER);
        this.framePreventivo.add(panel2, BorderLayout.EAST);
    }

    @Override
    public void addObserver(PreventivoViewObserver observer) {
        this.osservatori.add(observer);
    }

    @Override
    public void removeObserver(PreventivoViewObserver observer) {
        this.osservatori.remove(observer);
    }

    private void notifyEvent(EventoPreventivo tipoEvento) {
        for (PreventivoViewObserver preventivoViewObserver : osservatori) {
            preventivoViewObserver.eventNotified(tipoEvento);
        }
    }

    @Override
    public void mostraCreaPreventivo() {
        framePreventivo.setVisible(true);
    }

    @Override
    public void mostraSpecificheAutoPreventivo(Automobile auto) {
        DefaultTableModel model = (DefaultTableModel) specificheAutoTable.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"Marca", auto.getMarca()});
        model.addRow(new Object[]{"Modello", auto.getModello()});
        model.addRow(new Object[]{"Numero Porte", auto.getNumeroPorte()});
        model.addRow(new Object[]{"Cilindrata", auto.getCilindrata()});
        model.addRow(new Object[]{"Cavalli", auto.getCavalli()});
        if (auto.getStatoMacchina().equals(StatoMacchina.USATO)) {
            model.addRow(new Object[]{"KM", auto.getKm()});
        }
        model.addRow(new Object[]{"Stato Auto", auto.getStatoMacchina()});
    }

    @Override
    public void mostraListaClienti(List<Cliente> cliente) {
        boxClienti.removeAllItems();
        for (Cliente c : cliente) {
            String nomeCognome = c.getNome() + " " + c.getCognome();
            boxClienti.addItem(nomeCognome);
        }
    }

    @Override
    public Cliente getClienteSelezionato(List<Cliente> listaClienti) {
        String nomeCognome = (String) boxClienti.getSelectedItem();
        for (Cliente cliente : listaClienti) {
            if ((cliente.getNome() + " " + cliente.getCognome()).equals(nomeCognome)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void mostraPrezzoTotale(double prezzo) {
        prezzoTotale.setText("Prezzo: € " + prezzo);
    }

    @Override
    public void selezionaCliente(Cliente cliente) {
        String nomeCognome = cliente.getNome() + " " + cliente.getCognome();
        boxClienti.setSelectedItem(nomeCognome);
    }
}
