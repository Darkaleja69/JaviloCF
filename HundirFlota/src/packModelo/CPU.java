package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CPU extends Observable{
	private static CPU miCPU;
	private ListaBarcos listaB;
	private ArrayList<Casilla> sospecha;
	private Casilla casillaSospecha;
	private Casilla casillaAlerta;
	private int cantEscudos;
	private int radares;
	
	private CPU() 
	{
		listaB = new ListaBarcos();
		sospecha = new ArrayList<Casilla>();
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
	
	public void radarCPU() {
		//para los turnos, que el método devuelva un booleano para saber si ha funcionado y q haga la CPU su movimiento
		
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		if(this.quedanRadares()) {
			radares = radares -1;
			Random num1 = new Random();	
			Random num2 = new Random();	
			int pX = num1.nextInt(7)+2;
			int pY = num2.nextInt(7)+2;
			int fmax = pX +1;
			int cmax = pY +1;
					
			Casilla c = null;
				
			for(int i = pX -1;i<=fmax;i++) {
					
				for(int j = pY -1;j<=cmax;j++) {
						
					c = Tablero.getTablero().getCasilla(i, j, true);
					c.ponerRadar();
					casillas.add(c);
					if(c.tieneBarco()) {
						sospecha.add(c);
					}
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
		
		//Caso 1: no se sospecha sobre ninguna casilla
		if(sospecha.size() == 0)
		{
			while(!posible) 
			{
				x = num.nextInt(10);
				y = num.nextInt(10);
				posible =!(Tablero.getTablero().getCasilla(x, y, true).estaTocada());
			}
			casillas = Tablero.getTablero().bombardear(x, y,true);
			
			//Si se ha golpeado un barco...
			if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null)
			{
				generarSospechas(Tablero.getTablero().getCasilla(x, y, true));
			}
		}
		
		//Caso 2: se tienen sospechas sobre alguna casilla
		else
		{
			//primero se bombardea a la casilla más sospechosa
			x = this.sospecha.get(0).getFila();
			y = this.sospecha.get(0).getColumna();
			casillas = Tablero.getTablero().bombardear(x, y,true);
			sospecha.remove(0);
			
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
		}
	}
	
	private void generarSospechas(Casilla c)
	{
		
	}
}
