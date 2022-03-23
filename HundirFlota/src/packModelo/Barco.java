package packModelo;

public abstract class Barco {
	private int longitud;
	private String[] coordenadas;
	private boolean[] tocado;
	
	public Barco(int pLongitud) {
		longitud = pLongitud;
		//pedir coordenadas
	}
}
