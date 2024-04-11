package prog.ogg.tdp.Codex;

import javafx.application.Application;
import javafx.event.EventHandler;

import static javafx.application.Application.launch;

import java.io.IOException;
import java.util.List;

import gioco.Giocatore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class EntryPoint extends Application {

	private Stage stage;
	private Scene schermataIniziale;
	private Scene inserimentoGiocatori;
	private Scene partita;
	private FXMLLoader loader;
	private FXMLController controller;
	private FXMLControllerPartita controllerPartita;
	private List<Giocatore> giocatori;
	
    @Override
    public void start(Stage stage) throws Exception {
    	this.stage = stage;

        Image icon = new Image(getClass().getResourceAsStream("/fxml/immagini/icona.jpg"));
        this.stage.getIcons().add(icon);
        
    	this.stage.setResizable(false); // Impedire il ridimensionamento
    	
    	this.loader = new FXMLLoader(getClass().getResource("/fxml/Copertina.fxml"));
    	Parent root = loader.load();
    	this.controller = loader.getController();
    	
    	ImageView immCopertina = controller.getImageView();
        
        this.schermataIniziale = new Scene(root);
        
        this.stage.setTitle("Codex Naturalis");
        this.stage.setScene(this.schermataIniziale);
        this.stage.show();
        
        
        
        immCopertina.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                inserimentoGiocatori();
            }
        });
        

    }
    
    private void inserimentoGiocatori() {
    	
    	this.loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
    	
    	try {
    		Parent root = this.loader.load();
    		this.controller = this.loader.getController();
        	Label labelStart = controller.getLabelStart();
        	this.inserimentoGiocatori = new Scene(root);
        	
            this.stage.setScene(this.inserimentoGiocatori);
            this.stage.show();
    		
            labelStart.visibleProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    // La Label è diventata visibile
                    this.giocatori = this.controller.getGiocatori();
                    nuovaPartita();
                }
            });
            
    	} catch (IOException e) {
    	    e.printStackTrace(); 
    	}
    	
    }
    
    
    private void nuovaPartita() {
    	
    	this.loader = new FXMLLoader(getClass().getResource("/fxml/Partita.fxml"));
    	
    	try {
    		Parent root = this.loader.load();
    		this.controllerPartita = this.loader.getController();
        	this.controllerPartita.inizioPartita(this.giocatori);        	
        	this.partita = new Scene(root);
        	
            this.stage.setScene(this.partita);
            this.stage.show();
    		
            
    	} catch (IOException e) {
    	    e.printStackTrace(); 
    	}
    	
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
