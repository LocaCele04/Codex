package gioco;


import javafx.scene.image.Image;

public abstract class Carta {
	private static int counterId = 1;
	protected final int id;
	protected boolean capovolta;
	protected final Angolo altDx;
	protected final Angolo altSx;
	protected final Angolo basDx;
	protected final Angolo basSx;
	protected final String tipo;
	private final Image fronte;
	private final Image retro;
	
	public Carta(boolean capovolta, String tipo, Image fronte, Image retro,
			Angolo altSx, Angolo altDx, Angolo basSx, Angolo basDx) {	
		
		this.id = counterId;
		counterId++;
		
		
		if (altDx.getStato() == StatoAngolo.LIBERO || altSx.getStato() == StatoAngolo.LIBERO || basDx.getStato() == StatoAngolo.LIBERO || basSx.getStato() == StatoAngolo.LIBERO)
			throw new IllegalArgumentException("una carta non può avere angoli liberi, ma solo esserci o non esserci (ESISTENTE o INESISTENTE)");
		
		this.altDx = new Angolo(this.id, altDx.getStato(), altDx.getSimbolo());
		this.altSx = new Angolo(this.id, altSx.getStato(), altSx.getSimbolo());
		this.basDx = new Angolo(this.id, basDx.getStato(), basDx.getSimbolo());
		this.basSx = new Angolo(this.id, basSx.getStato(), basSx.getSimbolo());
		
		this.capovolta = capovolta;
		this.tipo = tipo;
		this.fronte = fronte;
		this.retro = retro;
		
	}

	public Image getFronte() {
		return fronte;
	}

	public Image getRetro() {
		return retro;
	}
	
	public static int getCounterId(){
		return Carta.counterId;
	}
	
}
