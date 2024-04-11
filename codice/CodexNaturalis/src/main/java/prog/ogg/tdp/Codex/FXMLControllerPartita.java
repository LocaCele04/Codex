package prog.ogg.tdp.Codex;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gioco.Banco;
import gioco.Carta;
import gioco.CartaOro;
import gioco.CartaRisorsa;
import gioco.Giocatore;
import gioco.Simbolo;
import gioco.StatoAngolo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FXMLControllerPartita implements Initializable {

	private boolean ultimoTurno = false;
	private List<Giocatore> giocatori = new ArrayList<>();
	
	@FXML
	private Label labelTurno = new Label();
	
	@FXML
    private HBox HBoxMazzoFila1; 
	
	@FXML
    private HBox HBoxMazzoFila2; 
	
	@FXML
	private ImageView mazzoRis = new ImageView();
	
	@FXML
	private ImageView ris1 = new ImageView();
	
	@FXML
	private ImageView ris2 = new ImageView();
	
	
	private List<ImageView> imageViews = new ArrayList<>(); // Lista per memorizzare le ImageView
	
	private Banco banco = new Banco();
	
	private int numGiocatore;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Aggiungi le ImageView al container
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(67);
            HBox.setMargin(imageView, new Insets(0, 5, 0, 0));
            HBoxMazzoFila1.getChildren().add(imageView);
            imageViews.add(imageView);
        }
        
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(67);
            HBox.setMargin(imageView, new Insets(0, 5, 0, 0));
            HBoxMazzoFila2.getChildren().add(imageView);
            imageViews.add(imageView);
        }
	}
	
	
	
	public void inizioPartita(List<Giocatore> players) {
    	this.giocatori = players;
    	
    	this.banco.riempiRis1();
    	ris1.setImage(this.banco.showRis1());
    	
    	this.banco.riempiRis2();
    	ris2.setImage(this.banco.showRis2());
    	
    	mazzoRis.setImage(this.banco.getRetroProssimaRisorsa());
    	
    	numGiocatore = 0;
    	inizioTurno(giocatori.get(numGiocatore));
        	
    	
    }
    
    public void inizioTurno(Giocatore giocatoreCorrente) {
    	numGiocatore++;
    	if(numGiocatore%this.giocatori.size()==0)
    		numGiocatore = 0;
    	
    	this.labelTurno.setText("E' IL TUO TURNO: "+giocatoreCorrente.getNickname());
    	System.out.println("E' IL TUO TURNO: "+giocatoreCorrente.getNickname());
    	
    	for (ImageView imageView : imageViews) {
    	    imageView.setImage(null); // Imposta l'immagine su null per svuotare l'ImageView
    	}
    	
    	for (int i=0; i<giocatoreCorrente.getCarteRisorsa().size();i++) {
    		imageViews.get(i).setImage(giocatoreCorrente.getCarteRisorsa().get(i).getFronte());
    	}
    	
    	mazzoRis.setOnMouseClicked(event -> {
    		fineTurnoRisorsa(giocatoreCorrente, this.banco.pescaRisorsa());
    		
        });
    	
    	ris1.setOnMouseClicked(event -> {
    		fineTurnoRisorsa(giocatoreCorrente, this.banco.presaRis1());
    		
        });
    	
    	ris2.setOnMouseClicked(event -> {
    		fineTurnoRisorsa(giocatoreCorrente, this.banco.presaRis2());
    		
        });
    	
    	
    }
    
    public void fineTurnoRisorsa(Giocatore giocatoreCorrente, CartaRisorsa nuovaCarta) {

    	giocatoreCorrente.nuovaCartaRisorsa(nuovaCarta);
		
    	
		for (ImageView imageView : this.imageViews) {
    	    imageView.setImage(null); // Imposta l'immagine su null per svuotare l'ImageView
    	}
    	
    	for (int i=0; i<giocatoreCorrente.getCarteRisorsa().size();i++) {
    		this.imageViews.get(i).setImage(giocatoreCorrente.getCarteRisorsa().get(i).getFronte());
    	}
    	
    	ris1.setImage(this.banco.showRis1());
    	ris2.setImage(this.banco.showRis2());
    	mazzoRis.setImage(this.banco.getRetroProssimaRisorsa());

    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cambio giocatore");
        alert.setHeaderText(null);
        alert.setContentText("Il turno di " + giocatoreCorrente.getNickname()+ " è terminato\n"
        		+ "Ora tocca a " + this.giocatori.get(numGiocatore).getNickname());
        // Mostrare la finestra di avviso
        alert.showAndWait();
    	
    	try {
    	    Thread.sleep(500); // Sospende l'esecuzione per 3 secondi (3000 millisecondi)
    	} catch (InterruptedException e) {
    	    // Gestione dell'eccezione se l'attesa viene interrotta
    	}
		inizioTurno(this.giocatori.get(numGiocatore));
    }
    
    
    public void stampaGiocatori() {
    	int cont = 0;
    	for (Giocatore giocatore : this.giocatori) {
    		cont++;
    	    System.out.println("giocatore n°"+cont+"\nnome: "+giocatore.getNickname()+" colore: "+giocatore.getColore()+"\n\n");
    	}
    }

}
