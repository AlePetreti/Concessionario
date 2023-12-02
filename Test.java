package src;
import java.util.LinkedList;


public class Test {

	public static void main(String[] args) {
		 
		
	
        LinkedList<Macchina> generale = new LinkedList<>();
        LinkedList<Macchina> nuova    = new LinkedList<>();
        LinkedList<Macchina> usata    = new LinkedList<>();
        generale.add(new Macchina("Kia"  ,true,7000,3,500));
        generale.add(new Macchina("Punto",false,80000,3,500));
        generale.add(new Macchina("Tata" ,false,1000,5,600));
        generale.add(new Macchina("Ford" ,true,200,3,50));
        generale.add(new Macchina("Dacia",true,400,5,300));
        generale.add(new Macchina("Lamborghini",true,20000,3,1000));
        
        Macchina rest = new Macchina("Rolls Roice",false,22222,3,600);
        
       
    
      
        rest.filtraAuto(generale,nuova,usata);
	   
	    rest.getAcquisto(nuova);
		
		
		
	}

}
