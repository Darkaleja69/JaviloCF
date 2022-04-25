package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GestorJuego extends Observable {
	private static GestorJuego miGestor;
	
	private GestorJuego() 
	{
	}
	public static GestorJuego getMiGestorJuego() {
		if (miGestor==null) 
		{
			miGestor=new GestorJuego();
		}
		return miGestor;
	}
	
	//Metodos
	public void colocarBarcos(boolean pHorizontal, int pX, int pY, int pLongitud)
	{
		if(Jugador.getJugador().colocarBarcos(pHorizontal, pX, pY, pLongitud))
		{
			CPU.getMiCPU().colocarBarco(pLongitud);
		}
	}
	
	public void colocarEscudo(int pX, int pY)
	{
		boolean fin = false;
		int jugadorOCPU = 1;

		if (Jugador.getJugador().escudosSuficientes())
		{
			Jugador.getJugador().colocarEscudo(pX, pY);
		}
		
		//Turno CPU
		Random x = new Random();
		boolean escudo = x.nextBoolean();
		if (escudo && CPU.getMiCPU().escudosSuficientes()) //poner escudo CPU
		{
			CPU.getMiCPU().colocarEscudo();
		}
		else//Disparo CPU
		{
			CPU.getMiCPU().dispararInteligente();
			if(comprobarFin(true)) 
			{
				fin = true;
				jugadorOCPU=2;
				
			}
		}
		if(fin) 
		{	
				
			setChanged();
			notifyObservers(jugadorOCPU);
		}
	}
	
	public void disparar(int pX, int pY) 
	{
		boolean fin = false;
		int jugadorOCPU=1;
		boolean disparado = Jugador.getJugador().disparar(pX, pY);
		
		if(disparado) {
			if(comprobarFin(false)) 
			{
				fin = true;
			
			}
			else //Turno CPU
			{
				Random x = new Random();
				boolean escudo = x.nextBoolean();
				if (escudo && CPU.getMiCPU().escudosSuficientes()) //poner escudo CPU
				{
					CPU.getMiCPU().colocarEscudo();
				}
				else //Disparo CPU
				{
				CPU.getMiCPU().dispararInteligente();
				if(comprobarFin(true)) 
				{
					fin = true;
					jugadorOCPU=2;
					
				}
			}
			
		}
		if(fin) 
		{	
				
			setChanged();
			notifyObservers(jugadorOCPU);
		}
		}
	}
	
	public void radar() {
		
		Jugador.getJugador().radar();
		
		
		
	}
	public void turnoCPU() {
		//Turno CPU
        boolean fin = false;
        int jugadorOCPU=1;
        Random z = new Random();
        int turno = z.nextInt(2)+1;
        
        if (turno == 2 && CPU.getMiCPU().escudosSuficientes()) //poner escudo CPU
        {
            CPU.getMiCPU().colocarEscudo();

        }
        else if(turno == 3 && CPU.getMiCPU().quedanRadares()) //radar CPU
        {
            CPU.getMiCPU().radarCPU();

        }
        else //disparar CPU
        {
            CPU.getMiCPU().dispararInteligente();

            if(comprobarFin(true)) 
            {
                fin = true;
                jugadorOCPU=2;

            }
        }
        if(fin) 
		{	
			setChanged();
			notifyObservers(jugadorOCPU);
		}
	}
	
	
	private boolean comprobarFin(boolean pJug) {
		boolean fin=true;
		if(pJug) 
		{
			fin=Jugador.getJugador().comprobarFin();
		}
		else 
		{
			fin=CPU.getMiCPU().comprobarFin();
		}
		return fin;
	}
	
	public ArrayList<Casilla> marcarBarcoHundido(Casilla pCasilla,boolean pJug) {
		return Tablero.getTablero().obtenerCasillasBarco(pCasilla, pJug);
	}
	
	public int barcosPorColocar(int pLong) {
		return Jugador.getJugador().barcosPorColocar(pLong);
	}
	
	public int escudosPorColocar() {
		return Jugador.getJugador().escudosPorColocar();
	}
	
	public int radaresPorColocar() {
		return Jugador.getJugador().cantidadRadares();
	}
}
