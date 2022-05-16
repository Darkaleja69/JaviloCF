package packModelo;

import java.util.ArrayList;

public class CasillasProhibidas {
	
	//A
	private ArrayList<Casilla> lista;
	
	//C
	public CasillasProhibidas()
	{
		this.lista = new ArrayList<Casilla>();
	}
	
	//M
	public void prohibir()
	{
		//Primero reseteamos la lista de casillas prohibidas
		this.lista.clear();
		//Recorremos el tablero en busca de casillas con barcos completamente hundidos
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				//si encontramos una casilla con un barco hundido, prohibimos todas las casillas de alrededor
				if(Tablero.getTablero().getCasilla(i, j, true).tieneBarco() && Tablero.getTablero().getCasilla(i, j, true).getBarco().estaHundido())
				{
					for(int k = i - 1; k <= i + 1; k++)
					{
						for(int l = j - 1; l <= j + 1; l++)
						{
							if(k >= 0 && k < 10 && l >= 0 && l < 10)
							{
								this.lista.add(Tablero.getTablero().getCasilla(i, l, true));
							}
						}
					}
				}
			}
		}
	}
	
	//Comprueba si se ha prohibido esa casilla
	public boolean estaProhibida(int x, int y)
	{
		boolean prohibida = false;
		for(Casilla c : lista)
		{
			if(c.getFila() == x && c.getColumna() == y)
			{
				prohibida = true;
				break;
			}
		}
		return prohibida;
	}

}
