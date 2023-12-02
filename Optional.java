package src;

import java.util.Scanner;


public class Optional {

	private final int premium = 3;
	private final int medium  = 2;
	private final int basic   = 1;
	private static final int MAX = 10000;
	private static final int MID = 5000;
	private static final int LOW = 1000;

int select;
	
	
	Scanner scan = new Scanner(System.in);
	
	public void putOptional(Macchina auto)
	{
		System.out.print("In questa sezione è possibile scegliere gli optional");
		System.out.println("da aggungere alle vostre automobili.");
		System.out.println("Selezionare:>[1] per gli optional basic");
		System.out.println("Selezionare:>[2] per gli optional medium");
		System.out.println("Selezionare:>[3] per gli optional premium");
		System.out.println("Selezionare:>[0] se non si desidera avere optional");
	
		do
	    {
	        select = scan.nextInt(); 
	    	
	    	while(select != 1 && select != 2 && select != 3)
	    	{
	    		System.out.println("[Input Errato]");
	    		break;
	    	}
	    	
	       
	    	
	    }while(select != 1 && select!= 2 && select != 3);
	    
	    switch(select)
	    {
	    	case(1):
	    		auto.setOptional(basic);
	    	    auto.setPrezzo(LOW);
	    	break;
	    	
	    	case(2): 
	    		auto.setOptional(medium);
	    	    auto.setPrezzo(MID);
	    	break;
	    	
	    	case(3):
	    		auto.setOptional(premium);
	    	    auto.setPrezzo(MAX);
	    	break;
	    	
	    	case(0):
	    		System.out.println("Non hai seleionato optional");
	   
	  
	    
	    }
	}

	
	
	
	public int getPremium() {
		return premium;
	}

	public int getMedium() {
		return medium;
	}

	public int getBasic() {
		return basic;
	}








}
