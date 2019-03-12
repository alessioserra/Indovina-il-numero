package application.gioco;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private final int Nmax=100;
	private final int Tmax=8;
	
	private int segreto;
	private int tentativiFatti;
	
	private boolean iniziata=false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button controlloPartita;

    @FXML
    private TextField tentativiRimasti;

    @FXML
    private TextField tentativo;

    @FXML
    private Button controlloTentativi;

    @FXML
    private TextArea messaggi;

    @FXML
    void iniziaButton(ActionEvent event) {

    	//Inizia una nuova partita:
    	this.segreto = (int)((Math.random())*Nmax + 1);  //Genero numero segreto
    	this.tentativiFatti=1;
    	this.iniziata=true;
    	
    	//Gestion dell'interfaccia
    	controlloPartita.setDisable(true);
    	controlloTentativi.setDisable(false);
    	
    	messaggi.clear();
    	tentativiRimasti.setText(String.valueOf(Tmax));
     	
    }

    @FXML
    void provaButton(ActionEvent event) {
    	
    	//Leggi valore tentativo
    	
    	int numero;
    	
    	try {
        numero = Integer.parseInt(tentativo.getText()) ;
    	}catch (NumberFormatException e) {
    	  //messaggio all'utente se la stringa non è un numero valido
    		messaggi.setText("Il numero non è valido!\n");
    		return;
    	}
    	
    	//Controllo alto/basso/indovinato
    	if (numero==segreto) {
    		messaggi.appendText("Complimenti hai indovinato il numero in "+tentativiFatti+" tentativi\n");
    		
    		controlloPartita.setDisable(false);
    		controlloTentativi.setDisable(true);
    		iniziata=false;
    		return;
    	}
    	
    	//Controllo tentativi
    	if (tentativiFatti==Tmax) {
    		messaggi.appendText("HAI PERSO! Il numer segreto era: "+segreto+"\n");
    		
    		controlloPartita.setDisable(false);
    		controlloTentativi.setDisable(true);
    		iniziata=false;
    		return;
    	}
    	
        //Controllo numero
    	if (numero<segreto) messaggi.appendText("Tentativo troppo BASSO!\n");
    	else messaggi.appendText("Tentativo troppo ALTO!\n");
    	
    	tentativiRimasti.setText(Integer.toString(Tmax-tentativiFatti));
    	tentativiFatti++;
    	
    	

    }

    @FXML
    void initialize() {
        assert controlloPartita != null : "fx:id=\"controlloPartita\" was not injected: check your FXML file 'Sample.fxml'.";
        assert tentativiRimasti != null : "fx:id=\"tentativiRimasti\" was not injected: check your FXML file 'Sample.fxml'.";
        assert tentativo != null : "fx:id=\"tentativo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert controlloTentativi != null : "fx:id=\"controlloTentativi\" was not injected: check your FXML file 'Sample.fxml'.";
        assert messaggi != null : "fx:id=\"messaggi\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}
