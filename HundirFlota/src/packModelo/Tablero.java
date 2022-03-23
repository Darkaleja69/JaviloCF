package packModelo;

public class Tablero {
	
	//Atributos
	
	private static Tablero miTablero;
	private Casilla[][] tableroJugador;
	private Casilla[][] tableroCPU;
	
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
