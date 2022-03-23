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
	public static Tablero getTablero(int pFil, int pCol)
	{
		if(miTablero == null)
		{
			miTablero = new Tablero(pFil, pCol);
		}
		return(miTablero);
	}
	
	//Métodos
	public void bombardearCasilla(int pFil, int pCol)
	{
		
	}
	
	public boolean valido()
	{
		return true;
	}
	
}
