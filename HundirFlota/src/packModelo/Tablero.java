package packModelo;

import java.util.ArrayList;

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
				Casilla c = new Casilla(i, j);
				this.tableroJugador[i][j] = c;
				c = new Casilla(i, j);
				this.tableroCPU[i][j] = c;
			}
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
						//no ocurre nada
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
	
	public ArrayList<Casilla> colocarEscudo (int pX, int pY, boolean pJug)
	{
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		if (pJug)
		{
			if (this.tableroJugador[pX][pY].tieneBarco() && !this.tableroJugador[pX][pY].getBarco().estaHundido())
			{
				Barco b = this.tableroJugador[pX][pY].getBarco();
				Escudo esc = new Escudo();
				b.colocarEscudo(esc);
				
				//a�adir todas las casillas que ocupe el barco al array
				casillas = this.obtenerCasillasBarco(this.tableroJugador[pX][pY], true);
			}
			
		}
		else
		{
			Barco b = this.tableroCPU[pX][pY].getBarco();
			Escudo esc = new Escudo();
			b.colocarEscudo(esc);
			//a�adir todas las casillas que ocupe el barco al array
			casillas = this.obtenerCasillasBarco(this.tableroCPU[pX][pY], false);
		}
		
		return casillas;
	}
	
	public Casilla getCasilla(int pFila, int pCol,boolean esJug) {
		if(esJug) 
		{
			return(this.tableroJugador[pFila][pCol]);
		}else 
		{
			return this.tableroCPU[pFila][pCol];
		}
		
	}
	public boolean radarUsado( boolean esJug) {
		Casilla c = this.buscarRadar(esJug);
		int cont = 0;
		if(c != null) {
			int fil = c.getFila();
			int col = c.getColumna();
			
			for(int i=fil-1; i<=fil+1; i++) {
				for(int j =col-1; j<=col+1 ; j++) {
					if(Tablero.getTablero().getCasilla(i, j, esJug).getRadar()>0) {
						cont = cont + 1;
						
					}
					
				}
			}
		}
		
		return(cont==9);
		
		
	}
	public Casilla buscarRadar(boolean esJug) {
		Casilla c;
		if(esJug) {
			Radar r = (Radar) ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).obtenerArma(3);
			c = r.getRadar();
		}
		else {
			Radar r = (Radar) ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).obtenerArma(3);
			c = r.getRadar();
		}
		
		return c;
	}
	public ArrayList<Casilla> colocarRadar(int pFila, int pCol, boolean pJug) {
		ArrayList<Casilla> array = new ArrayList<Casilla>();
		Casilla r = getCasilla(pFila, pCol, pJug);
		r.ponerRadar();
		array.add(r);
		return array;
	}
	public ArrayList<Casilla> detectar(int fila, int col,boolean pJug) {
		ArrayList<Casilla> array = new ArrayList<Casilla>();
		int fmax = fila +1;
		int cmax = col +1;
		
				
		Casilla c = null;
			
		for(int i = fila -1;i<=fmax;i++) {	
			
			for(int j = col -1;j<=cmax;j++) {	
				c = Tablero.getTablero().getCasilla(i, j, pJug);
				c.detectar();;
				array.add(c);
			}
			
		}
			
		
		return array;
	}
	
	public ArrayList<Casilla> bombardear(int fila, int col,boolean pJug) {
		ArrayList<Casilla> array = new ArrayList<Casilla>();
		Casilla pCasilla = getCasilla(fila, col,pJug);
		pCasilla.bombardear();
		
		array.add(pCasilla);
		return array;
	}
	
	public ArrayList<Casilla> lanzarMisil(int fila, int col,boolean pJug) {
		ArrayList<Casilla> array = new ArrayList<Casilla>();
		Casilla pCasilla = getCasilla(fila, col,pJug);
		pCasilla.lanzarMisil();
		
		array.add(pCasilla);
		return array;
	}
	
	public ArrayList<Casilla> obtenerCasillasBarco(Casilla pCasilla,boolean pJug){
		Barco bar=pCasilla.getBarco();
		int filaI=pCasilla.getFila();
		int colI=pCasilla.getColumna();
		ArrayList<Casilla> casillasBarc=new ArrayList<Casilla>();
		casillasBarc.add(pCasilla);
		int i=1;
		boolean hor=false;
		boolean sumar=false;
		boolean[][] noMirar=new boolean[10][10];
		noMirar[filaI][colI]=true;
		int fila=filaI;
		int col=colI;
		int x=0;
		boolean restar=false;
		
		while(i<bar.getLongitud() && !hor) {
			sumar=false;
			restar=false;
			x=i;
			if(fila<9) {
				if(this.getCasilla(fila+1, col, pJug).getBarco()==bar && !noMirar[fila+1][col]) {
					
					casillasBarc.add(this.getCasilla(fila+1, col, pJug));
					i++;
					noMirar[fila+1][col]=true;
					sumar=true;
				}
			}
			if(fila>0) {
					
					if(this.getCasilla(fila-1, col, pJug).getBarco()==bar && !noMirar[fila-1][col]) {
						casillasBarc.add(this.getCasilla(fila-1, col, pJug));
						i++;
						noMirar[fila-1][col]=true;
						restar=true;
					}
			}
			if(i==1) {
				hor=true;
			}else if(sumar) {
						fila++;
					}else if(restar) {
						fila--;
			}
			if(x==i && !hor) {
				if(fila>filaI) {
					fila=filaI-1;
				}else {
					fila=filaI+1;
				}
			}
		}
		
		while(i<bar.getLongitud()) {
			x=i;
			sumar=false;
			if(col<9) {
				if(this.getCasilla(fila, col+1, pJug).getBarco()==bar && !noMirar[fila][col+1]) {
					casillasBarc.add(this.getCasilla(fila, col+1, pJug));
					i++;
					noMirar[fila][col+1]=true;
					sumar=true;
					
				}
			}
			if(col>0) {
				if(this.getCasilla(fila, col-1, pJug).getBarco()==bar && !noMirar[fila][col-1]) {
					casillasBarc.add(this.getCasilla(fila, col-1, pJug));
					i++;
					noMirar[fila][col-1]=true;
					sumar=false;
					
				}
			}
			if(sumar) {
				col++;
			}else {
				col--;
			}

			if(x==i){
				if(col>colI) {
					col=colI-1;
				}else {
					col=colI+1;
				}
			}
		}
		
		return casillasBarc;
		
	}
	
	public Casilla buscarCasillaBarco(Barco pBarco)
	{
		int i = 0;
		int j = 0;
		boolean enc = false;
		Casilla c = null;
		while (i<10 && !enc)
		{
			while (j<10 && !enc)
			{
				if (this.tableroCPU[i][j].tieneBarco() && this.tableroCPU[i][j].getBarco().equals(pBarco))
				{
					c = this.tableroCPU[i][j];
					enc = true;
				}
				j++;
			}
			j = 0;
			i++;
		}
		return c;
	}
	public void quitarRadar(Casilla c , boolean esJug, int pRadar) {
		int x = c.getFila();
		int y = c.getColumna();
		if(esJug) {
			Tablero.getTablero().tableroCPU[x][y].quitarRadar(pRadar);
		}
		else {
			Tablero.getTablero().tableroJugador[x][y].quitarRadar(pRadar);
		}
		
		
	}
	

}