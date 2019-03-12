package application.gioco.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	
	private final int Nmax=100;
	private final int Tmax=8;
	
	private int segreto;
	private int tentativiFatti;
	private boolean iniziata;
	
	public NumeroModel() {
		iniziata=false;
	}

	/**
	 *Avvia nuova partita 
	 */
	
	public void newGame() {
		
		iniziata=true;
		this.segreto = (int)((Math.random())*Nmax + 1);  //Genero numero segreto
    	this.tentativiFatti=1;
	}
	
	public int tentativo(int t) {
		
		//Controllo se la partita è in corso
		if(!iniziata) {
			throw new IllegalStateException("La partita è terminata"); //Lancio eccezione
		}
		
		//Controllo se l'input e' nel range corretto                              
		if (!tentativoValido(t))                                         //Senza concatenazione
			throw new InvalidParameterException(String.format("Devi inserire un numero tra" + " %d e %d", 1,Nmax));
		
		//Gestione tentativo
		this.tentativiFatti++;
		
		if (this.tentativiFatti == this.Tmax) {
			//La partita e' finita perche' ho esaurito i tentativi
			this.iniziata=false;
		}
		
		//Indovinato
		if (this.segreto == t) {
			this.iniziata=false;
			return 0;
		}
		
		//Numero troppo alto
		if (t>this.segreto) return 1;
		
		//Numero troppo basso
		return -1;
		
	}
	
	public boolean tentativoValido(int t) {
		if (t<1 || t>Nmax) {
			return false;
		}
		else return true;
	}

}
