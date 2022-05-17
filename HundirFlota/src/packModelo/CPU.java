package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CPU extends Jugador{
	private ListaBarcos listaB;
	private ArrayList<Casilla> sospecha;
	private CasillasProhibidas prohibidas;
	private Armamento miArmamento;
	private int dinero;
	private int radares;
	private int cantEscudos;
	
	public CPU()
	{
		listaB = new ListaBarcos();
		miArmamento = new Armamento();
		sospecha = new ArrayList<Casilla>();
		prohibidas = new CasillasProhibidas();
		cantEscudos = 3;
		radares = 5;
		dinero = 500;
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
		//Realizamos inicializaciones varias
		boolean fin = false;
		int jugadorOCPU = 1;
		Random z = new Random();
		this.miArmamento.anadirArmamento(miArmamento.comprarArmamento(5));
		
		//Si se tiene dinero, se lo funde en armas aleatoriamente
		while(this.dinero > 0)
		{
			int adquisicion = z.nextInt(3) + 1;
			if(adquisicion == 1 && this.dinero >= 100)
			{//misil
				this.miArmamento.anadirArmamento(this.miArmamento.comprarArmamento(1));
				this.dinero -= 100;
			}
			else if(adquisicion == 2 && this.dinero >= 50)
			{//escudo
				this.miArmamento.anadirArmamento(this.miArmamento.comprarArmamento(2));
				this.dinero -= 50;
			}
			else if(adquisicion == 3 && this.dinero >= 50)
			{//escudo
				this.miArmamento.anadirArmamento(this.miArmamento.comprarArmamento(3));
				this.dinero -= 50;
			}
			else if(adquisicion == 4 && this.dinero >= 50)
			{//escudo
				this.miArmamento.anadirArmamento(this.miArmamento.comprarArmamento(3));
				this.dinero -= 50;
			}
		}
		System.out.println(this.dinero);
		
		//Decidimos la accion que vamos a realizar
        int turno = z.nextInt(2)+1;
        
        if (turno == 2 && this.miArmamento.armasPorUsar(2) > 0) //poner escudo CPU
        {
            this.colocarEscudo();
        }
        else if(turno == 3 && this.miArmamento.armasPorUsar(4) > 0) //radar CPU
        {
            this.radarCPU();

        }
        else //disparar (de forma inteligente) CPU
        {
            this.dispararInteligente();

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
	
	public void colocarEscudo()
	{
		Arma a = this.miArmamento.buscarArma(2);
		this.miArmamento.retirarArma(2);
		
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
		
		a.realizarFuncion(x, y, true);
	}
	
	public void radarCPU() {
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		Arma a = this.miArmamento.buscarArma(4);
		this.miArmamento.retirarArma(4);
		a.realizarFuncion(-1, -1, true);
		a.realizarFuncion(0, 0, true);
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
		//Se prohiben las casillas que est�n al lado de un barco hundido
		this.prohibidas.prohibir();
		//Se eliminan las sospechas que est�n prohibidas
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
				posible =(!(Tablero.getTablero().getCasilla(x, y, true).estaTocada()) && !this.prohibidas.estaProhibida(x, y)); //es posible si no est� tocada y no est� prohibida
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
			//se bombardea a la casilla m�s sospechosa
			x = this.sospecha.get(0).getFila();
			y = this.sospecha.get(0).getColumna();
			casillas = Tablero.getTablero().bombardear(x, y,true);
			sospecha.remove(0);
			
			//Caso 2.1 se ha dado en un barco y se ha hundido
			if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null && Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido())
			{
				//si esa casilla no estaba registrada en el radar, se han de borrar el resto de sospechas no registradas por el radar
				if(!(Tablero.getTablero().getCasilla(x, y, true).getRadar() == 1))
				{
					//borrar de "sospecha" a todas las casillas que no hayan sido detectadas por radares
					for(int i = 0; i < sospecha.size(); i++)
					{
						if(!(sospecha.get(i).getRadar() == 1))
						{
							sospecha.remove(i);
						}
					}
				}
			}
			//Caso 2.2 se ha dado en un barco que llevaba radar y no se ha hundido
			else if(Tablero.getTablero().getCasilla(x, y, true).tieneBarco() && Tablero.getTablero().getCasilla(x, y, true).getRadar() == 1 && !(Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido()))
			{
				this.generarSospechas(Tablero.getTablero().getCasilla(x, y, true));
			}
			//Caso 2.3 se ha dado en un barco y no se ha hundido
			else if(Tablero.getTablero().getCasilla(x, y, true).tieneBarco() && !(Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido()))
			{
				//borrar de "sospecha" a todas las casillas que no hayan sido detectadas por radares
				for(int i = 0; i < sospecha.size(); i++)
				{
					if(!(sospecha.get(i).getRadar() == 1))
					{
						sospecha.remove(i);
					}
				}
				//crear nuevas sospechas sobre la �ltima casilla golpeada
				this.generarSospechas(Tablero.getTablero().getCasilla(x, y, true));
			}
			//Caso 2.3 se ha dado en agua (no hace falta c�digo)
		}
		ListaJugadores.getMiListaJug().obtenerJugadorOCPU(0).enviarCasillas(casillas);
	}
	public void anadirSospechas(ArrayList<Casilla> pSospechas)
	{
		this.sospecha.addAll(pSospechas);
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
		
		//Se eliminan las que se hab�an bombardeado en un turno anterior (PROVISIONAL)
		//tambi�n se eliminan las que tienen barcos hundidos
		for(int i = 0; i < candidatas.size(); i++)
		{
			if((candidatas.get(i).estaTocada()))
			{
				candidatas.remove(i); 
			}
		}
		
		//por ultimo, se indexa el ArrayList de casillas a nuestra lista de sospechas
		this.sospecha.addAll(candidatas);
	}
}
