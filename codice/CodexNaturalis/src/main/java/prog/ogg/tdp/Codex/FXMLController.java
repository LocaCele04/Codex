package prog.ogg.tdp.Codex;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import gioco.Giocatore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class FXMLController implements Initializable {
	
	// il tag FXML serve per ricordarsi che si sta usando un oggetto/azione creata nel file fxml
	@FXML
    private ImageView immCopertina;
	
	@FXML
	private HBox infoPlayer1 = new HBox();
	
	@FXML
	private HBox infoPlayer2 = new HBox();
	
	@FXML
	private HBox infoPlayer3 = new HBox();
	
	@FXML
	private HBox infoPlayer4 = new HBox();
	
	@FXML
	private ComboBox<String> comboBoxNumGiocatori = new ComboBox<String>();
	
	@FXML
	private Label labelStart = new Label();
	

	private int numGiocatori = 2;
	private List<HBox> infoPlayers = new ArrayList<>();
	private List<Giocatore> giocatoriNonControllati = new ArrayList<>();
	private List<Giocatore> giocatori;

	// metodi
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	inizializzaComboBox();
    	
    	cambiaStatoComboBox();

    }
    
    public void inizializzaComboBox() {
    	// Inizializza il ComboBox
        ObservableList<String> items = FXCollections.observableArrayList(
            "2",
            "3",
            "4"
        );
        this.comboBoxNumGiocatori.setItems(items);
    }
    
    @FXML
    public void clickGioca(ActionEvent event) {
    	boolean eccezione = false;
    	
    	infoPlayers.clear();
    	giocatoriNonControllati.clear();
    	
    	if (this.numGiocatori >= 1) {
    	    infoPlayers.add(infoPlayer1);
    	}
    	if (this.numGiocatori >= 2) {
    	    infoPlayers.add(infoPlayer2);
    	}
    	if (this.numGiocatori >= 3) {
    	    infoPlayers.add(infoPlayer3);
    	}
    	if (this.numGiocatori == 4) {
    	    infoPlayers.add(infoPlayer4);
    	}
    	
    	
    	
    	ColorPicker colorPicker;
    	Color colore;
    	String nickname;
    	
    	for (HBox hbox : infoPlayers) {
    	    // Ottieni il nickname dall'HBox
    	    nickname = ((TextField) hbox.getChildren().get(1)).getText(); // Supponiamo che il nickname sia un Label

    	    // Ottieni il ColorPicker dall'HBox
    	    colorPicker = (ColorPicker) hbox.getChildren().get(2);

    	    // Ottieni il colore selezionato dal ColorPicker
    	    colore = colorPicker.getValue();

    	    // Aggiungi un nuovo giocatore alla lista giocatori
    	    giocatoriNonControllati.add(new Giocatore(nickname, colore));
    	}
    	eccezione = verificaDatiDuplicati();
    	
    	if (eccezione == false) {
    		List<Giocatore> giocatoriNonOrdinati = new ArrayList<>(giocatoriNonControllati);
    		
    		sceltaTurno(giocatoriNonOrdinati);
    		this.labelStart.setVisible(true);
    	}
    	
        
    }
     
    
    public List<Giocatore> getGiocatori(){
    	return this.giocatori;
    }
    

    public void sceltaTurno(List<Giocatore> giocatoriNonOrdinati) {
    	Random random = new Random();
    	int indiceGiocatoreEstratto = random.nextInt(giocatoriNonOrdinati.size());
    	Giocatore primo = giocatoriNonOrdinati.get(indiceGiocatoreEstratto);
    	
    	System.out.println("giocatore estratto: "+primo.getNickname());

        // Crea una nuova lista che parte dai giocatori prima del casuale
        this.giocatori = new ArrayList<>(giocatoriNonOrdinati.subList(indiceGiocatoreEstratto, giocatoriNonOrdinati.size()));

        // Aggiungi i giocatori precedenti a quello estratto
        this.giocatori.addAll(giocatoriNonOrdinati.subList(0, indiceGiocatoreEstratto));
    	
    	
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Scelta turni");
        alert.setHeaderText(null);
        
        String messaggio = "Il giocatore estratto è "+ primo.getNickname()+"\n l'ordine risulta quindi:\n " + primo.getNickname();
        
        for (Giocatore giocatore : giocatori.subList(1, this.giocatori.size())) {
        	messaggio += " -> " + giocatore.getNickname();
    	}
        
        
        alert.setContentText(messaggio);
        // Mostrare la finestra di avviso
        alert.showAndWait();
        
    }
    
    public boolean verificaDatiDuplicati() {
    	Giocatore currentPlayer;
    	Giocatore nextPlayer;
    	for (int i = 0; i < giocatoriNonControllati.size(); i++) {
    		
    	    currentPlayer = giocatoriNonControllati.get(i);
    	    if (currentPlayer.getNickname().equals("")) {
    	    	mostraErrore("Nickname nullo!", "Uno o più utenti non hanno un nickname");
	        	return true;
    		}
    	    for (int j = i+1; j < giocatoriNonControllati.size(); j++) {
    	        nextPlayer = giocatoriNonControllati.get(j);
    	        
    	        if (currentPlayer.getNickname().equals(nextPlayer.getNickname())) {
    	        	mostraErrore("Nickname duplicati!", "Due utenti hanno inserito lo stesso nickname");
    	        	return true;
    	        }
    	        if (currentPlayer.getColore().equals(nextPlayer.getColore())) {
    	        	mostraErrore("Colori non distinguibili!", "Due utenti hanno inserito lo stesso colore");
    	        	return true;
    	        }
    	    }
    	}
    	return false;
    }
    
    public void mostraErrore(String titolo, String messaggio) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
    
    
    
    public void cambiaStatoComboBox() {

    	// Imposta un listener per l'evento Action sulla ComboBox
        this.comboBoxNumGiocatori.setOnAction((ActionEvent event) -> {
	        
        	String selectedValue = comboBoxNumGiocatori.getValue();    
	        this.numGiocatori = Integer.parseInt(selectedValue);
            
            if (this.numGiocatori >= 3) {
            	this.infoPlayer3.setVisible(true);
            	this.infoPlayer4.setVisible(false);
            	if (this.numGiocatori == 4)
            		this.infoPlayer4.setVisible(true);
            }else {
            	this.infoPlayer3.setVisible(false);
            	this.infoPlayer4.setVisible(false);
            }
            
        });
    }
    
    public ImageView getImageView() {
        return this.immCopertina;
    }
    
    public Label getLabelStart() {
        return this.labelStart;
    } 
}
