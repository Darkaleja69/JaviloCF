package packModelo;

public class Tablero {
	
	//Atributos
	
	private static Tablero miTablero;
	private Casilla[][] tableroJugador;
	private Casilla[][] tableroCPU;
	
	//Constructora MAE
	private Tablero(int pFil, int pCol) 
	{
		this.tableroJugador = new Casilla[pFil][pCol];
		this.tableroCPU = new Casilla[pFil][pCol];
	}
	public static Tablero getTablero()
	{
		if(miTablero == null)
		{
			miTablero = new Tablero(10, 10);
		}
		return(miTablero);
	}
	
	//M�todos
	public void bombardearCasilla(Casilla pCasilla,boolean esJugador)
	{	if(esJugador) {
		
	}
		
	}
	
	public boolean valido(int pFila,int pCol,boolean esJugador)
	{	boolean valido=true;
		if(esJugador) {
			if(!(tableroJugador[pFila-1][pCol].getBarco()==null && tableroJugador[pFila][pCol-1].getBarco()==null && tableroJugador[pFila][pCol+1].getBarco()==null && tableroJugador[pFila+1][pCol].getBarco()==null)) {
					valido=false;
			}
		}else {
			if(!(tableroCPU[pFila-1][pCol].getBarco()==null && tableroCPU[pFila][pCol-1].getBarco()==null && tableroCPU[pFila][pCol+1].getBarco()==null && tableroCPU[pFila+1][pCol].getBarco()==null)) {
				valido=false;
			}
		}
	
		return valido;
	
	}
	
	public void colocarBarco(Barco pBarco,int pFila,int pCol) {
		
	}
	
}
