package packModelo;

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
	
	
	public boolean valido(int pFila,int pCol,boolean esJugador)
	{	
		boolean valido=true;
		if(esJugador) {
			if(!(tableroJugador[pFila-1][pCol].getBarco()==null && tableroJugador[pFila][pCol-1].getBarco()==null && tableroJugador[pFila][pCol+1].getBarco()==null && tableroJugador[pFila+1][pCol].getBarco()==null)) {
					valido=false;
			}
		}else {
			if(!(tableroCPU[pFila-1][pCol].getBarco()==null && tableroCPU[pFila][pCol-1].getBarco()==null && tableroCPU[pFila][pCol+1].getBarco()==null && tableroCPU[pFila+1][pCol].getBarco()==null)) {
				valido=false;
			}
		}
		System.out.println(valido);
		return valido;
	}
	
	public boolean todoValido(int pFila, int pCol, int pTam, boolean esJugador, boolean pHorizontal)
	{
		boolean valido = true;
		int i;
		if(pHorizontal)
		{
			i = pCol;
			while(i <= pCol + pTam - 1 && valido)
			{
				valido = this.valido(pFila, i, esJugador);
				i++;
				if(i > 10)
				{
					valido = false;
				}
			}
		}
		else
		{
			i = pFila;
			while(i <= pFila + pTam -1 && valido)
			{
				valido = this.valido(i, pCol, esJugador);
				i++;
				if(i > 10)
				{
					valido = false;
				}
			}
		}
		return valido;
	}
	
	public void colocarBarco(Barco pBarco,int pFila,int pCol) {
		Casilla pCasilla = getCasilla(pFila, pCol);
		pCasilla.colocarBarco(pBarco);
		
	}
	
	public Casilla getCasilla(int pFila, int pCol) {
		
		return(this.tableroJugador[pFila][pCol]);
	}
	
	public void bombardear(int fila, int col) {
		Casilla pCasilla = getCasilla(fila, col);
		pCasilla.bombardear();
		
		if (pCasilla.tieneBarco()) {
			Barco pBarco = pCasilla.getBarco();
			
		}
		
		
		
		
	}
	
}
