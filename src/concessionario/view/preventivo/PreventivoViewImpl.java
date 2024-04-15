package concessionario.view.preventivo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import concessionario.model.ElementoListino;
import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.StatoMacchina;
import concessionario.model.cliente.Cliente;

public class PreventivoViewImpl implements PreventivoView{
    
    private final List<PreventivoViewObserver> osservatori;
    private final JFrame framePreventivo;
    private final JTextArea specificheAutoPreventivo;
    private final JLabel prezzoTotale; 
    private final JComboBox<String> boxClienti;

    public PreventivoViewImpl() {
        this.osservatori = new LinkedList<>();

        // finestra per i preventivi
        framePreventivo = new JFrame("Preventivo");
        framePreventivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePreventivo.setSize(1280,720);
        framePreventivo.setLayout(new BorderLayout());
        // panel per mostrare specifiche auto
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JButton b1 = new JButton("Concludi Vendita");
        panel.add(b1, BorderLayout.EAST);
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifyEvent(EventoPreventivo.CONCLUDI_VENDITA);
                framePreventivo.dispose();
            }
        });
        this.prezzoTotale = new JLabel();
        panel.add(new JLabel("Prezzo totale: " + prezzoTotale), BorderLayout.WEST);
        // finire di visuallizare il prezzo

        // panel per specifiche auto del preventivo
        JPanel panel1 = new JPanel();
        //panel1.setLayout(new GridLayout(0, 1));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(new JLabel("Specifiche auto"));
        specificheAutoPreventivo = new JTextArea();
        specificheAutoPreventivo.setEditable(false);
        specificheAutoPreventivo.setFocusable(false);
        panel1.add(specificheAutoPreventivo); 
        
        // panel per selezionare i clienti
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        boxClienti = new JComboBox<>();
        boxClienti.setPreferredSize(new Dimension(300, 30));
        panel2.add(boxClienti, BorderLayout.CENTER);


        this.framePreventivo.add(panel, BorderLayout.PAGE_END);
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
        for(PreventivoViewObserver preventivoViewObserver : osservatori) {
            preventivoViewObserver.eventNotified(tipoEvento);
        } 
    }

    
    @Override
    public void mostraCreaPreventivo() {
        framePreventivo.setVisible(true);
    }
    
    
    @Override
    public void mostraSpecificheAutoPreventivo(Automobile auto) {
        specificheAutoPreventivo.setText("");
        specificheAutoPreventivo.append("Marca: " + auto.getMarca() + "\n");
        specificheAutoPreventivo.append("Modello: " + auto.getModello() + "\n");
        specificheAutoPreventivo.append("Numero porte: " + auto.getNumeroPorte() + "\n");
        specificheAutoPreventivo.append("Cilindrata: " + auto.getCilindrata() + "\n");
        specificheAutoPreventivo.append("Cavalli: " + auto.getCavalli() + "\n");
        if (auto.geStatoMacchina().equals(StatoMacchina.USATO)) {
            specificheAutoPreventivo.append("KM: " + auto.getKm() + "\n");
        }
        specificheAutoPreventivo.append("Stato Auto: " + auto.geStatoMacchina() + "\n");
    }
    
    
    @Override
    public void mostraListaClienti(List<Cliente> cliente) {
        for(Cliente c : cliente) {
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
    public void mostraPrezzoTotale(ElementoListino ElementoListino) {
    }
}