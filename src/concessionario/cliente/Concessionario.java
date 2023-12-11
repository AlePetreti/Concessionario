package concessionario.cliente;

import java.util.List;

import concessionario.automobile.Automobile;
import concessionario.automobile.Optional;


public class Concessionario {

	private Optional optional;
	
	
	public void visualizzaAutoAcquistata(Auto auto, String scelta)
	    {
	    	/*stampa l'auto che si vuole acquistare*/
		    //inserire collection della quale effettuare  il filtro
		    
		   
	    	auto.stream().
	    	filter(n -> n.getModello().equals(scelta)).
	    	forEach(System.out::println);
	    	
	    	optional.putOptional(auto);
	    	
	    	/*eliminare auto da listino*/
	        auto.removeIf(n -> n.getModello().equals(scelta));
	    	
	    }
	    
	    
}
