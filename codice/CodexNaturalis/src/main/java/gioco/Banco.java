package gioco;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Banco {
	private List<CartaRisorsa> mazzoRis = new ArrayList<>();
	private List<CartaOro> mazzoOro = new ArrayList<>();
	
	private CartaRisorsa ris1;
	private CartaRisorsa ris2;
	private CartaOro oro1;
	private CartaOro oro2;
	
	public Banco() {
		setMazzoRis();
	}

	public List<CartaRisorsa> getMazzoRis() {
		return mazzoRis;
	}

	public void setMazzoRis() {
		try {
            // Leggi il file JSON
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("risorse.json"));

            int j = 1;
            int i = 0;
            // Itera su ogni oggetto nel JSONArray
            for (Object obj : jsonArray) {
                JSONObject cartaJson = (JSONObject) obj;

                i++;

                // Leggi i valori degli angoli e dei punti dal JSONObject
                Angolo altSx = parseAngolo((JSONObject) cartaJson.get("altSx"));
                Angolo altDx = parseAngolo((JSONObject) cartaJson.get("altDx"));
                Angolo basSx = parseAngolo((JSONObject) cartaJson.get("basSx"));
                Angolo basDx = parseAngolo((JSONObject) cartaJson.get("basDx"));
                int punti = ((Long) cartaJson.get("punti")).intValue();
                String colore = (String) cartaJson.get("colore");

                CartaRisorsa carta = new CartaRisorsa(true, "risorsa", new Image("/fxml/immagini/risorse/fronte/image"+j+"x"+i+".png"), 
                		new Image("/fxml/immagini/risorse/retro/image1x"+i+".png"), 
                				altSx, altDx, basSx, basDx, colore, punti);

                // Aggiungi la carta alla lista
                mazzoRis.add(carta);
                System.out.println("carta aggiunta!");
                
                if (i%3==0) {
                	j++;
                	i = 0;
                }
            }
            long seed = System.nanoTime(); // Utilizza il tempo corrente come seme
            Collections.shuffle(mazzoRis, new Random(seed));
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	private static Angolo parseAngolo(JSONObject angoloJson) {
        StatoAngolo stato = StatoAngolo.valueOf((String)angoloJson.get("stato"));
        Simbolo simbolo = Simbolo.valueOf((String)angoloJson.get("simbolo"));
        return new Angolo(Carta.getCounterId(), stato, simbolo);
    }
	
	public Image getRetroProssimaRisorsa() {
		return mazzoRis.get(mazzoRis.size() - 1).getRetro();
	}
	
	public CartaRisorsa pescaRisorsa() {
		// Accedi all'ultimo elemento della lista
        CartaRisorsa ultimoElemento = mazzoRis.get(mazzoRis.size() - 1);

        // Rimuovi l'ultimo elemento dalla lista
        mazzoRis.remove(mazzoRis.size() - 1);
        System.out.println("carta rimossa!");
        System.out.println("carte rimanenti: "+mazzoRis.size());
        
        return ultimoElemento;
	}

	public Image showRis1() {
		return this.ris1.getFronte();
	}
	
	public Image showRis2() {
		return this.ris2.getFronte();
	}
	
	
	public CartaRisorsa presaRis1() {
		CartaRisorsa cartaOut =  ris1;
		riempiRis1();
		return cartaOut;
	}
	
	public CartaRisorsa presaRis2() {
		CartaRisorsa cartaOut =  ris2;
		riempiRis2();
		return cartaOut;
	}
	
	
	public void riempiRis1() {
		setRis1(pescaRisorsa());
	}
	
	public void riempiRis2() {
		setRis2(pescaRisorsa());
	}
	
	public void setRis1(CartaRisorsa ris1) {
		this.ris1 = ris1;
	}
	
	public void setRis2(CartaRisorsa ris2) {
		this.ris2 = ris2;
	}
	
}
