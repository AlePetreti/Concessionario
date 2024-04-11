package concessionario.model.officina;
import java.util.Random;

import concessionario.model.automobile.Automobile;
import concessionario.model.automobile.FactoryAutomobili;
import concessionario.model.automobile.StatoMacchina;

public class Officina extends FactoryAutomobili {

	 public void riparazioneAuto(Automobile auto)
	 {
		 if(auto.geStatoMacchina() == StatoMacchina.USATO)
		 {
			 
			 if(auto.getKm() < 5000)
			 {
				 //costa un tot all'autosalone etc...
			 }else if(auto.getKm() < 50000)
			 {
				 
			 }else if(auto.getKm() < 100000)
			 {
				 
			 }
			 
		 }
	 }
	
}
