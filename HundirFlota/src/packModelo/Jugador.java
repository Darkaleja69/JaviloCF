package packModelo;

public class Jugador {
	
	//Atributos
	private ListaBarcos listaBarc;
	private static Jugador miJugador;
	
	//Constructora
	private Jugador()
	{
		
	}
	
	public static Jugador getJugador()
	{
		if(miJugador == null)
		{
			miJugador = new Jugador();
		}
		return(miJugador);
	}
	
	//M�todos
	public void colocarBarcos() {}
	
	
}
