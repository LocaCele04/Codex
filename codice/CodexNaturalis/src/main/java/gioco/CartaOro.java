package gioco;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class CartaOro extends CartaRisorsa{

	private List<Simbolo> condizione = new ArrayList<>();	//lista di simboli anche ripetuti, costituiscono la condizione per inserire la carta	
	
	public CartaOro(boolean capovolta, String tipo, Image fronte, Image retro,
			Angolo altSx, Angolo altDx, Angolo basSx, Angolo basDx, String colore, int punti, List<Simbolo> simboliNecessari) {
		super(capovolta, tipo, fronte, retro, altSx, altDx, basSx, basDx, colore, punti);
		
		for (Simbolo simbolo : simboliNecessari) {
        	if (simbolo == Simbolo.PERGAMENA || simbolo == Simbolo.PENNA || simbolo == Simbolo.INCHIOSTRO)
        		throw new IllegalArgumentException("una carta oro non può richiedere simboli rari");
    	}
		this.condizione = simboliNecessari;
	}

}
