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
