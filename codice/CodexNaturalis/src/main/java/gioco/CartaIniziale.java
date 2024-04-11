package gioco;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class CartaIniziale extends Carta {

	private List<Simbolo> simboliFissi = new ArrayList<>();
	
	public CartaIniziale(boolean capovolta, String tipo, Image fronte, Image retro,
			Angolo altSx, Angolo altDx, Angolo basSx, Angolo basDx, List<Simbolo> simboliFissi) {
		super(capovolta, tipo, fronte, retro, altSx, altDx, basSx, basDx);
		
		
		for (Simbolo simbolo : simboliFissi) {
        	if (simbolo == Simbolo.PERGAMENA || simbolo == Simbolo.PENNA || simbolo == Simbolo.INCHIOSTRO)
        		throw new IllegalArgumentException("una carta iniziale non può avere simboli rari");
    	}
		this.simboliFissi = simboliFissi;
		// TODO Auto-generated constructor stub
	}

}
