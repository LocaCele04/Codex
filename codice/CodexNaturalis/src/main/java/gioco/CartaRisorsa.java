package gioco;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CartaRisorsa extends Carta{
	
	private final int punti;
	private final Color colore;
	private final Simbolo simboloFisso; 	// simbolo che abbiamo se giriamo la carta

	public CartaRisorsa(boolean capovolta, String tipo, Image fronte, Image retro,
			Angolo altSx, Angolo altDx, Angolo basSx, Angolo basDx, String colore, int punti) {
		
		super(capovolta, tipo, fronte, retro, altSx, altDx, basSx, basDx);
				
		Color color;
		switch (colore) {
		    case "blue":
		        color = Color.BLUE;
		        break;
		    case "green":
		        color = Color.GREEN;
		        break;
		    case "red":
		        color = Color.RED;
		        break;
		    case "violet":
		        color = Color.VIOLET;
		        break;
		    default:
		        throw new IllegalArgumentException("Colore non valido");
		}
		this.colore = color;
		
		
		if (this.colore == Color.BLUE)
			this.simboloFisso = Simbolo.LUPO;
		else if (this.colore == Color.RED)
			this.simboloFisso = Simbolo.FUNGO;
		else if (this.colore == Color.GREEN)
			this.simboloFisso = Simbolo.FOGLIA;
		else
			this.simboloFisso = Simbolo.FARFALLA;
		
			
		
		if (punti>1 || punti<0) {
			throw new IllegalArgumentException("una carta risorsa può avere al massimo un punto");
		}
		this.punti = punti;
		
	}

	public int getPunti() {
		return punti;
	}

	public Simbolo getSimboloFisso() {
		return simboloFisso;
	}
	
}
