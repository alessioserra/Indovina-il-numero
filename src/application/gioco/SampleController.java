package application.gioco;

import java.net.URL;
import java.util.ResourceBundle;

import application.gioco.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private NumeroModel model;
	
	public void setModel(NumeroModel model) {
		this.model = model;
	}

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

    	//Comunico al modello di iniziare una nuova partita 
    	model.newGame();
    	
    	//Gestion dell'interfaccia
    	controlloPartita.setDisable(true);
    	controlloTentativi.setDisable(false);
    	
    	tentativo.clear();
    	messaggi.clear();
    	tentativiRimasti.setText(String.valueOf( model.getTMax() ));
     	
    }

    @FXML
    void provaButton(ActionEvent event) {
    	
    	//Leggi valore tentativo
    	
    	int numero;
    	
    	try {
        numero = Integer.parseInt(tentativo.getText()) ;
    	}catch (NumberFormatException e) {
    	  //messaggio all'utente se la stringa non è un numero valido
    		messaggi.appendText("Il numero non è valido!\n");
    		return;
    	}
    	
    	//DOPPIO CONTROLLO
    	if (!model.tentativoValido(numero)) {
    		messaggi.appendText("Range non valido\n");
    	}
    	
    	//Testo tentativo
    	int risultato = model.tentativo(numero);
    	
    	//Controllo alto/basso/indovinato
    	if (risultato==0) {
    		messaggi.appendText("Complimenti hai indovinato il numero in "+model.getTentativiFatti()+" tentativi\n");
    		
    		controlloPartita.setDisable(false);
    		controlloTentativi.setDisable(true);
    	}
    	
    	else if(risultato<0){
    		messaggi.appendText("Tentativo troppo BASSO!\n");
    	}
    	else messaggi.appendText("Tentativo troppo ALTO!\n");
    	
    	tentativiRimasti.setText(Integer.toString(model.getTMax()-model.getTentativiFatti()));
    	
    	if(!model.isIniziata()) {
    		//La partita e' finita!
    		if (risultato !=0) {
    	       messaggi.appendText("Hai PERSO, hai finito i tentativi!\n");
    	       messaggi.appendText("Il numero segreto era: "+model.getSegreto());
    	    }
    	}
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
