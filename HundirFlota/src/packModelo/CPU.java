package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CPU extends Observable{
	private static CPU miCPU;
	private ListaBarcos listaB;
	private Casilla casillaAlerta;
	private Casilla casillaSospecha;
	private int cantEscudos;
	private int radares;
	
	private CPU() 
	{
		listaB = new ListaBarcos();
		casillaAlerta = null;
		casillaSospecha = null;
		cantEscudos = 3;
		radares = 5;
	}
	
	public static CPU getMiCPU() 
	{
		if (miCPU == null) 
		{
			miCPU = new CPU();
		}
		return miCPU;
	}
	
	public boolean escudosSuficientes()
	{
		return cantEscudos > 0;
	}

	public Barco colocarBarco(int pLongitud) 
	{	

		boolean horizontal=false;
		int x = 0;
		int y = 0;
		Random num = new Random();
		boolean posible = false;
		Barco bar = FactoryBarcos.getMiFactoryBarcos().crearBarco(pLongitud);
		listaB.anadirBarco(bar);
		while(!posible) 
		{
			x = num.nextInt(10);
			y = num.nextInt(10);
			horizontal = num.nextBoolean();
			posible = Tablero.getTablero().todoValido(x, y, pLongitud, false, horizontal);
		}
		Tablero.getTablero().colocarBarco(bar, x, y,pLongitud,horizontal,false);
	
		return bar;
		
	}
	
	public void disparar(){
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		boolean posible=false;
		int x = 0;
		int y = 0;
		Random num = new Random();
		while(!posible) 
		{
			x = num.nextInt(10);
			y = num.nextInt(10);
			posible =!(Tablero.getTablero().getCasilla(x, y, true).estaTocada());
		}
		casillas = Tablero.getTablero().bombardear(x, y,true);
		Jugador.getJugador().enviarCasillas(casillas);
	}
	
	public void colocarEscudo()
	{
		cantEscudos--;
		
		//Decidir a que barco colocarle el escudo
		ArrayList<Barco> barcos = this.listaB.barcosSinHundir();
		
		Barco barc=null;
		for (Barco b: barcos)
		{
			if (!b.tieneEscudo())
			{
				barc = b;
				break;
			}
		}
		
		Casilla c = Tablero.getTablero().buscarCasillaBarco(barc);
		int x = c.getFila();
		int y = c.getColumna();
		ArrayList<Casilla> casillas = Tablero.getTablero().colocarEscudo(x, y, false);
		//CPU.getMiCPU().enviarCasillas(casillas);
	}
	
	public void radar(int pX, int pY) {
		//para los turnos, que el m�todo devuelva un booleano para saber si ha funcionado y q haga la CPU su movimiento
		
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		if(this.quedanRadares()) {
			radares = radares -1;
			int fmax = pX +1;
			int cmax = pY +1;
					
			Casilla c = null;
				
			for(int i = pX -1;i<=fmax;i++) {
					
				for(int j = pY -1;j<=cmax;j++) {
						
					c = Tablero.getTablero().getCasilla(i, j, false);
					c.ponerRadar();
					casillas.add(c);
				}
					
			}
				
			CPU.getMiCPU().enviarCasillas(casillas);
				
		}
			
		//return(casillas.size() >0);
	}
	
	public void anadirBarco(Barco pBarco) {
		this.listaB.anadirBarco(pBarco);
	}
	
	public boolean comprobarFin() {
		return this.listaB.comprobarFin();
	}
	
	public boolean quedanRadares() {
		return(this.radares>=1);
	}
	
	public void enviarCasillas(ArrayList<Casilla> pCasillas)
	{
		setChanged();
		notifyObservers(pCasillas);
	}
	
	public void dispararInteligente()
	{
		//Inicializaciones
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		boolean posible = false;
		int x = 0;
		int y = 0;
		Random num = new Random();
		
		//Caso 1: se ha hundido un barco o se ha disparado al agua
		if(this.casillaAlerta == null || this.casillaAlerta.getBarco().estaHundido())
		{
			while(!posible) 
			{
				x = num.nextInt(10);
				y = num.nextInt(10);
				posible =!(Tablero.getTablero().getCasilla(x, y, true).estaTocada());
			}
			casillas = Tablero.getTablero().bombardear(x, y,true);
			
			//Se activa casillaAlerta
			if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null)
			{
				casillaAlerta = Tablero.getTablero().getCasilla(x, y, true);
			}
			else
			{
				this.casillaAlerta = null;
				this.casillaSospecha = null;
			}
		}
		
		//Caso 2: se ha hallado un barco por primera vez
		else if(this.casillaAlerta.tieneBarco() && this.casillaSospecha == null)
		{
			boolean fila;
			while(!posible)
			{
				fila = num.nextBoolean();
				if(fila)
				{
					x = casillaAlerta.getFila() - 1 + num.nextInt(2);
					y = casillaAlerta.getColumna();
				}
				else 
				{
					x = casillaAlerta.getFila();
					y = casillaAlerta.getColumna() - 1 + num.nextInt(2);
				}
				posible = (!(Tablero.getTablero().getCasilla(x, y, true).estaTocada())) && (y >= 0 && x >= 0);
			}
			
			casillas = Tablero.getTablero().bombardear(x, y,true);
			
			//Caso 2.1 se ha dado en un barco y se ha hundido
			if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null && Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido())
			{
				casillaAlerta = null;
				casillaSospecha = null;
			}
			//Caso 2.2 se ha dado en un barco y no se ha hundido
			else if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null && !(Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido()))
			{
				casillaSospecha = Tablero.getTablero().getCasilla(x, y, true);
			}
			//Caso 2.3 se ha dado al agua (no ocurre nada)
		}
		
		//Caso 3: se ha dado en el barco dos veces y no se ha hundido
		else if(this.casillaAlerta.tieneBarco() && this.casillaSospecha.tieneBarco())
		{
			x = this.casillaAlerta.getFila() - this.casillaSospecha.getFila();
			y = this.casillaAlerta.getColumna() - this.casillaSospecha.getColumna();
			if(!Tablero.getTablero().getCasilla(x, y, true).estaTocada())
			{
				
			}
		}
		
		Jugador.getJugador().enviarCasillas(casillas);
	}
}
