package gioco;

public class Angolo {
	private int idCarta;	// 0 se non appartiene a nessuna carta
	private StatoAngolo stato;
	private final Simbolo simbolo;
	
	public Angolo(int idCarta, StatoAngolo stato, Simbolo simbolo) {
		this.setIdCarta(idCarta);
		this.setStato(stato);
		if (this.stato == StatoAngolo.ESISTE)
			this.simbolo = simbolo;
		else
			this.simbolo = Simbolo.NULLO;
	}

	public StatoAngolo getStato() {
		return stato;
	}

	public void setStato(StatoAngolo stato) {
		if (stato == StatoAngolo.LIBERO || stato == StatoAngolo.ESISTE || stato == StatoAngolo.INESISTENTE) {
			this.stato = stato;
        }else {
        	throw new IllegalArgumentException("Lo stato di un angolo puo avere solo 3 valori, LIBERO -> angolo senza carta, ESISTE -> angolo vuoto o con simbolo, INESISTENTE -> angolo di una carta che non c'è");
        }
	}

	public Simbolo getSimbolo() {
		return simbolo;
	}

	public int getIdCarta() {
		return idCarta;
	}

	// nella matrice di angoli una carta può coprire l'angolo di un'altra carta e quindi cambia l'id
	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}
	
	
}
