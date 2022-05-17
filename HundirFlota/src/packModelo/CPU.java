package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CPU extends Observable{
	private static CPU miCPU;
	private ListaBarcos listaB;
	private ArrayList<Casilla> sospecha;
	private CasillasProhibidas prohibidas;
	private Armamento miArmamento;
	private int radares;
	private int cantEscudos;
	
	private CPU() 
	{
		listaB = new ListaBarcos();
		sospecha = new ArrayList<Casilla>();
		prohibidas = new CasillasProhibidas();
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
	
	public void turnoCPU() {
		//Primero compramos bombas
		this.miArmamento.comprarArmamento(5);
		//Decidimos la acción que vamos a realizar
        boolean fin = false;
        int jugadorOCPU = 1;
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

            if(GestorJuego.getMiGestorJuego().comprobarFin(true)) 
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
	
	/*public void disparar(){
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
	}*/
	
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
						sospecha.add(0, c);
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
		//Se prohiben las casillas que estén al lado de un barco hundido
		this.prohibidas.prohibir();
		//Se eliminan las sospechas que estén prohibidas
		for(int i = 0; i < this.sospecha.size(); i++)
		{
			if(this.prohibidas.estaProhibida(this.sospecha.get(i).getFila(), this.sospecha.get(i).getColumna()))
			{
				this.sospecha.remove(i);
			}
		}
		
		//Caso 1: no se sospecha sobre ninguna casilla
		if(sospecha.isEmpty())
		{
			while(!posible) 
			{
				x = num.nextInt(10);
				y = num.nextInt(10);
				posible =(!(Tablero.getTablero().getCasilla(x, y, true).estaTocada()) && !this.prohibidas.estaProhibida(x, y)); //es posible si no está tocada y no está prohibida
			}
			casillas = Tablero.getTablero().bombardear(x, y, true);
			
			//Si se ha golpeado un barco y no se ha hundido
			if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null && !Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido())
			{
				generarSospechas(Tablero.getTablero().getCasilla(x, y, true));
			}
		}
		
		//Caso 2: se tienen sospechas sobre alguna casilla
		else
		{
			//se bombardea a la casilla más sospechosa
			x = this.sospecha.get(0).getFila();
			y = this.sospecha.get(0).getColumna();
			casillas = Tablero.getTablero().bombardear(x, y,true);
			sospecha.remove(0);
			
			//Caso 2.1 se ha dado en un barco y se ha hundido
			if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null && Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido())
			{
				//si esa casilla no estaba registrada en el radar, se han de borrar el resto de sospechas no registradas por el radar
				if(!Tablero.getTablero().getCasilla(x, y, true).tieneRadar())
				{
					//borrar de "sospecha" a todas las casillas que no hayan sido detectadas por radares
					for(int i = 0; i < sospecha.size(); i++)
					{
						if(!sospecha.get(i).tieneRadar())
						{
							sospecha.remove(i);
						}
					}
				}
			}
			//Caso 2.2 se ha dado en un barco que llevaba radar y no se ha hundido
			else if(Tablero.getTablero().getCasilla(x, y, true).tieneBarco() && Tablero.getTablero().getCasilla(x, y, true).tieneRadar() && !(Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido()))
			{
				this.generarSospechas(Tablero.getTablero().getCasilla(x, y, true));
			}
			//Caso 2.3 se ha dado en un barco y no se ha hundido
			else if(Tablero.getTablero().getCasilla(x, y, true).tieneBarco() && !(Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido()))
			{
				//borrar de "sospecha" a todas las casillas que no hayan sido detectadas por radares
				for(int i = 0; i < sospecha.size(); i++)
				{
					if(!sospecha.get(i).tieneRadar())
					{
						sospecha.remove(i);
					}
				}
				//crear nuevas sospechas sobre la última casilla golpeada
				this.generarSospechas(Tablero.getTablero().getCasilla(x, y, true));
			}
			//Caso 2.3 se ha dado en agua (no hace falta código)
		}
		Jugador.getJugador().enviarCasillas(casillas);
	}
	
	private void generarSospechas(Casilla c)
	{
		//primero se crea el ArrayList con las cuatro casillas que rodean a "c"
		ArrayList<Casilla> candidatas = new ArrayList<Casilla>();
		int fil = c.getFila();
		int col = c.getColumna();
		
		if(fil - 1 >= 0)
		{
			candidatas.add(Tablero.getTablero().getCasilla(c.getFila() - 1, c.getColumna(), true));	//norte
		}
		if(col + 1 < 10)
		{
			candidatas.add(Tablero.getTablero().getCasilla(c.getFila(), c.getColumna() + 1, true));	//este
		}
		if(fil + 1 < 10)
		{
			candidatas.add(Tablero.getTablero().getCasilla(c.getFila() + 1, c.getColumna(), true));	//sur
		}
		if(col - 1 >= 0)
		{
			candidatas.add(Tablero.getTablero().getCasilla(c.getFila(), c.getColumna() - 1, true));	//oeste
		}
		
		//Se eliminan las que se habían bombardeado en un turno anterior (PROVISIONAL)
		//también se eliminan las que tienen barcos hundidos
		for(int i = 0; i < candidatas.size(); i++)
		{
			if((candidatas.get(i).estaTocada()))
			{
				candidatas.remove(i); 
			}
		}
		
		//por último, se indexa el ArrayList de casillas a nuestra lista de sospechas
		this.sospecha.addAll(candidatas);
	}
}
