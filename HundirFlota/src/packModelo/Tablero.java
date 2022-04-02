package packModelo;

import java.util.ArrayList;
import java.util.Observer;

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
		this.crearCasillas();
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
	private void crearCasillas()
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				Casilla c = new Casilla(i, j, true);
				this.tableroJugador[i][j] = c;
				c = new Casilla(i, j, false);
				this.tableroCPU[i][j] = c;
			}
		}
	}
	
	public void bombardearCasilla(Casilla pCasilla,boolean esJugador)
	{	
		if(esJugador) {
		
	}
		
	}
	
	
	private boolean valido(int pFila, int pCol, boolean esJugador, int pCont)
	{	
		boolean valido = true;
		
		if(esJugador)
		{
			for(int i = pFila -1; i <= pFila + 1; i++)
			{
				for(int j = pCol - 1; j <= pCol + 1; j++)
				{
					if(i > 9 || j > 9 || i < 0 || j < 0)
					{
						
					}
					else
					{
						if(this.tableroJugador[i][j].getBarco() != null) 
						{
							valido = false;
						}
					}
				}
			}	
		}
		
		else
		{
			for(int i = pFila -1; i <= pFila + 1; i++)
			{
				for(int j = pCol - 1; j <= pCol + 1; j++)
				{
					if(i > 9 || j > 9 || i < 0 || j < 0)
					{
						
					}
					else
					{
						if(this.tableroCPU[i][j].getBarco() != null) 
						{
							valido = false;
						}
					}
				}
			}	
		}
		
		return valido;
	}
	
	public boolean todoValido(int pFila, int pCol, int pTam, boolean esJugador, boolean pHorizontal)
	{
		boolean valido = true;
		int i;
		if(pHorizontal)
		{
			i = pCol;
			if(i + pTam -1 > 9)
			{
				valido = false;
			}
			
			//Si valido es false, no se alcanza al while
			while(i <= pCol + pTam - 1 && valido)
			{
				valido = this.valido(pFila, i, esJugador, 0);
				i++;
			}
		}
		else
		{
			i = pFila;
			if(i + pTam -1 > 9)
			{
				valido = false;
			}
			
			while(i <= pFila + pTam -1 && valido)
			{
				valido = this.valido(i, pCol, esJugador, 0);
				i++;
			}
		}
		return valido;
	}
	
	public ArrayList<Casilla> colocarBarco(Barco pBarco,int pFila,int pCol, int pLongitud, boolean pHorizontal,boolean pJug) 
	{
		ArrayList<Casilla> array = new ArrayList<Casilla>();
		if(pHorizontal)
		{
			for(int i = pCol; i < pLongitud + pCol; i++)
			{
				Casilla c = getCasilla(pFila, i,pJug);
				c.colocarBarco(pBarco);
				array.add(c);
			}
		}
		else
		{
			for(int i = pFila; i < pLongitud + pFila; i++)
			{
				Casilla c = getCasilla(i, pCol,pJug);
				c.colocarBarco(pBarco);
				array.add(c);
			}
		}
		return array;
	}
	
	public Casilla getCasilla(int pFila, int pCol,boolean esJug) {
		if(esJug) {
			return(this.tableroJugador[pFila][pCol]);
		}else {
			return this.tableroCPU[pFila][pCol];
		}
		
	}
	
	public ArrayList<Casilla> bombardear(int fila, int col,boolean pJug) {
		ArrayList<Casilla> array = new ArrayList<Casilla>();
		Casilla pCasilla = getCasilla(fila, col,pJug);
		pCasilla.bombardear();
		
		array.add(pCasilla);
		return array;
	}
	

}