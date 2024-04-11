package gioco;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Giocatore {
	private final String nickname;
	private int totPunti;
	private final Color colore;
	private List<CartaRisorsa> carteRis = new ArrayList<>();
	
	public Giocatore(String nickname, Color colore) {
		
		this.nickname = nickname.trim();
		this.colore = colore;
		this.setTotPunti(0);
		
	}
	
	public void nuovaCartaRisorsa(CartaRisorsa ris) {
		this.carteRis.add(ris);
	}
	
	public List<CartaRisorsa> getCarteRisorsa(){
		return this.carteRis;
	}
	
	public int getTotPunti() {
		return totPunti;
	}

	public void setTotPunti(int totPunti) {
		this.totPunti = totPunti;
	}

	public Color getColore() {
		return colore;
	}

	public String getNickname() {
		return nickname;
	}


	
}
