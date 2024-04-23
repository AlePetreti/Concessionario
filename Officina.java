import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Officina extends JFrame {

	private JButton bottone;
	
	
	public Officina(){
		
		super("Officina");

	    setLayout(new BorderLayout()); // creo Layout
	    bottone = new JButton("Officina"); // Creo bottone officina 
	    add(bottone, BorderLayout.PAGE_START); // fisso il bottone in cima
		
	    bottone.addActionListener((ActionListener) new ActionListener() { // azione del bottone con classe anonima 

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	    
		setSize(800,500); // grandezza frame
		
	   	setLocationRelativeTo(null); // setta al centro il frame 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // chiude il frame 
		
		setVisible(true);
	}
}
