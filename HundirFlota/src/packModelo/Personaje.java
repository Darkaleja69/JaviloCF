package packModelo;

public abstract class Personaje {
	private ListaBarcos listab;
	private int radares;
	private int cantEscudos;
	//private cDisparar;
	//private cRadar;
	//private cEscudo;
	
	
	//Constructora
	private Personaje() {
		
	}
	
	//M�todos
	
	private void radarAleatorio() {
		
	}
	
	
	//Getters y setter
	public ListaBarcos getBarcos() {
		return(listab);
	}
	public int getRadares() {
		return(radares);
	}
	public int getEscudos() {
		return(cantEscudos);
	}

	
}
