package packModelo;

public class Tablero {
	
	//Atributos
	private int[][] tableroCPU;
	private int[][] tableroJugador;
	private Barco[] barcosCPU;
	private Barco[] barcosJugador;
	
	private static Tablero miTablero;
	private Casilla[][] matriz;
	
	//Constructora MAE
	private Tablero() 
	{
		
	}
	public static Tablero getTablero()
	{
		if(miTablero == null)
		{
			miTablero = new Tablero();
		}
		return(miTablero);
	}
	
	
	
}
