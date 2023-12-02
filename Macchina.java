package src;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Macchina {

	 private  String  modello;
	 private  Boolean usato;
	 private  int optional;
	 private  int prezzo;
	 private  int posti;
	 private  int cavalli;
	 private Optional prova; 
   
	
	 
   public Macchina(String modello, Boolean usato, int prezzo, int posti, int cavalli) {
		super();
		this.modello  = modello;
		this.usato    = usato;
		this.optional = optional;
		this.prezzo   = prezzo;
		this.posti    = posti;
		this.cavalli  = cavalli;
	}
	
   public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public Boolean getUsato() {
		return usato;
	}
	public void setUsato(Boolean usato) {
		this.usato = usato;
	}

    public int getPrezzo() {
		return prezzo;
	}
  
  
    public void setPrezzo(int prezzo) {
		this.prezzo += prezzo;
	}

    public int getOptional() {
		return optional;
	}

    
    public int getPosti() {
		return posti;
	}

	public void setPosti(int posti) {
		this.posti = posti;
	}

	public int getCavalli() {
		return cavalli;
	}

	public void setCavalli(int cavalli) {
		this.cavalli = cavalli;
	}

	

	public String toString() {
		return "Macchina [modello=" + modello + ", usato=" + usato + ", optional=" + optional + ", prezzo=" + prezzo
				+ ", posti=" + posti + ", cavalli=" + cavalli + "]";
	}



    public void filtraAuto(LinkedList<Macchina> auto, LinkedList<Macchina> nuova, LinkedList<Macchina> usato)
	{        
		   int scelta;
		
		    Scanner primo = new Scanner(System.in);
			System.out.println("Seleziona:> 1 per [auto usate]"); 
		    System.out.println("Seleziona:> 2 per [auto nuove]");  
		    
		    do
		    {
		    	scelta = primo.nextInt(); 
		    	while(scelta != 1 && scelta != 2)
		    	{
		    		System.out.println("[Input Errato]");
		    		break;
		    	}
		    	
		       
		    	
		    }while(scelta != 1 && scelta != 2);
		    
		    
		    switch(scelta)
		    {
		      case(1):
		    	     
		    	    auto.stream()
			        .filter(n -> n.getUsato())
			        .forEach(System.out::println);
			        
		            for(Macchina x : auto)
	        	    {
		            	usato.add(x);
	        	    }
	        	
	        	
		      break;
			  
		      case(2):
		    	    
		    	    auto.stream()
			        .filter(n -> !n.getUsato())
			        .forEach(System.out::println);
			  
		            for(Macchina x : auto)
      	            {
	                	nuova.add(x);
      	            }
		      
		      
	           break;	
		    }
		    
	}
	

	

    public void setOptional(int optional)
    {
    	this.optional = optional;
    	
    	
    }
  
	
  	Scanner secondo = new Scanner(System.in);
    Scanner terzo =   new Scanner(System.in);
    
    
    
    Optional test = new Optional();
    
    public void getAcquisto(LinkedList<Macchina> auto)
    {
    	
    	
    	System.out.println("Come desidere acquistare la tua auto?"
    		 	          +"Hai un budeget limitato?"
    			          +"Premi [si] o [no]");
    
    	String conferma;


    	
    	do
	    {
	    	
    		conferma = secondo.nextLine(); 
	    	
	    	while(!conferma.equals("si") && !conferma.equals("no") && !conferma.equals("Si") && !conferma.equals("No"))
	    	{
	    		System.out.println("[Input Errato]");
	    		break;
	    	}
	    	
	      
	    	
	    }while(!conferma.equals("si") && !conferma.equals("no") && !conferma.equals("Si") && !conferma.equals("No"));
	    
    	
    	int quota;
    	
    	System.out.println("inserisci la quota in denaro che vorresti spendere:>");
    	
    	
    	do
    	{
    	
    		quota = secondo.nextInt();
    	
    	}while(quota <= 0); 
    	
    
    	
    	int denaro = quota;
    	
    	Stream<Macchina> filter = null;
    	
    	if(conferma.equals("si"))
    	{ 
    		filter = auto.stream().
    		filter(n -> (n.getPrezzo() > (denaro - 200) && n.getPrezzo() < (denaro + 200)));
    		filter.forEach(System.out::println); 
 
    	    Scanner terzo = new Scanner(System.in);
    		
    	    System.out.println("Quale tra queste desideri acquistare?(indicare il nome dell'auto)");
    		
    		String scelta;
    		scelta = terzo.nextLine();
    		
    		
    		 auto.stream()
    		.filter(n -> n.getModello().equals(scelta))
    		.forEach(n -> test.putOptional(n));
    		
    		
    	    visualizzaAutoAcquistata(auto,scelta);
    		
    	
    	}
    	
    	if(conferma.equals("no"))
    	{
    		
    		 System.out.println("Inserisci[1] se vuoi acquistare un auto sportiva");
    		 System.out.println("Inserisci[2] vuoi acquistare un auto per esigenze particolari(ad esempio i posti auto)");
    		
            
    		 Scanner quarto = new Scanner(System.in);
    		 int appoggio;
    		 
    		 do
 		     {
    			 appoggio = quarto.nextInt();
 		    	
    			 while(appoggio != 1 && appoggio != 2)
 		    	 {	
    				 System.out.println("[Input Errato]");
 		    		 break;
 		    	 }
 		    	
 		       
 		    	
 		     }while(appoggio != 1 && appoggio != 2);
    		 
    		 Scanner quinto = new Scanner(System.in);

			 
			 
    		 if(appoggio == 1)
    		 {
    			
    			 System.out.println("Di quanti cavalli necessiti?");
    			
    			 
    			 int potenza;
    			 
    			 do
    			 {
    			
    				 potenza = quinto.nextInt(); 
    			 
    			
    				 
    				 
    			 }while(potenza <= 0);
    			 
    			 int cavalleria = potenza;
    			 
    		     auto.stream()
    		     .filter((n -> (n.getCavalli() == cavalleria)))
    		     .forEachOrdered(System.out::println);
    		      
    		    // visualizzaAutoAcquistata(auto,cavalleria);
    		 }
    		 
      		
    	}
    
        
    
    }
    
    public void visualizzaAutoAcquistata(LinkedList<Macchina> auto, String scelta)
    {
    	/*stampa l'auto che si vuole acquistare*/
    	auto.stream().
    	filter(n -> n.getModello().equals(scelta)).
    	forEach(System.out::println);
    	
    	/*eliminare auto da listino*/
        auto.removeIf(n -> n.getModello().equals(scelta));
    	
    }
    
    /*polimorfismo parametrico*/
    public void visualizzaAutoAcquistata(LinkedList<Macchina> auto, int scelta)
    {
    	/*stampa l'auto che si vuole acquistare*/
    	auto.stream().
    	filter(n -> (n.getCavalli() == (scelta))).forEachOrdered(System.out::println);
    	
    	/*eliminare auto da listino*/
        auto.removeIf(n -> n.getCavalli() == scelta);
    	
    }
    
    
    
    
    
    
    
}
