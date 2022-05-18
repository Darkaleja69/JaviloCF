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
	
	public CPU()
	{
		listaB = new ListaBarcos();
		miArmamento = new Armamento();
		sospecha = new ArrayList<Casilla>();
		prohibidas = new CasillasProhibidas();
		radares = 5;
		dinero = 500;
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
		
		//Decidimos la accion que vamos a realizar
        int turno = z.nextInt(4)+1;
        
	    if (turno == 2 && this.miArmamento.armasPorUsar(2) > 0) //poner escudo CPU
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
        else if(turno == 3 && this.miArmamento.armasPorUsar(4) > 0) //radar CPU
        {
        	Arma miArma=miArmamento.buscarArma(4);
			if( miArma != null) {
				if(miArma.realizarFuncion(200,200,true)) {
					miArmamento.retirarArma(4);
				}
			}
			Arma x=miArmamento.buscarArma(4);
			if( x != null) {
				if(x.realizarFuncion(5,5,true)) {
					miArmamento.retirarArma(4);
				}
			}
			
			
        }
        else if(turno == 4 && this.listaB.barcoReparable(this.miArmamento.armasPorUsar(3))) //reparar
        {
        	Barco b = this.listaB.getBarcoReparar(this.miArmamento.armasPorUsar(3));
            //Casilla c = Tablero.getTablero().buscarCasillaBarco(this.listaB.barcoReparable(this.miArmamento.armasPorUsar(3)));
            //ArrayList<Casilla> array = Tablero.getTablero().obtenerCasillasBarco(c, false);
            /*for(Casilla cas : array)
            {
            	if(cas.estaTocada())
            	{
            		Arma ar = this.miArmamento.buscarArma(3);
            		ar.realizarFuncion(cas.getFila(), cas.getColumna(), false);
            		break;
            	}
            }
            for(int i = 0; i < array.size(); i++)
            {
            	this.miArmamento.retirarArma(3);
            }*/
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
		
	}
	
	public void radarCPU() {

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
		//Se prohiben las casillas que estan al lado de un barco hundido
		this.prohibidas.prohibir();
		//Se eliminan las sospechas que estan prohibidas
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
			Arma b = this.miArmamento.buscarArma(5);
			b.realizarFuncion(x, y, true);
			this.miArmamento.retirarArma(5);
			
			//Si se ha golpeado un barco y no se ha hundido
			if(Tablero.getTablero().getCasilla(x, y, true).getBarco() != null && !Tablero.getTablero().getCasilla(x, y, true).getBarco().estaHundido())
			{
				generarSospechas(Tablero.getTablero().getCasilla(x, y, true));
			}
		}
		//Caso 2: se ha adivinado la posici�n de un barco gracias al radar y adem�s se tienen misiles --> se aprovecha el misil
		else if(sospecha.get(0).detectado() && this.miArmamento.armasPorUsar(1) > 0)
		{
			Casilla cS = sospecha.get(0);
			this.miArmamento.buscarArma(1).realizarFuncion(cS.getFila(), cS.getColumna(), true);
			this.miArmamento.retirarArma(1);
		}
		//Caso 3: se tienen sospechas sobre alguna casilla
		else
		{
			//se bombardea a la casilla mas sospechosa
			x = this.sospecha.get(0).getFila();
			y = this.sospecha.get(0).getColumna();
			Arma b = this.miArmamento.buscarArma(5);
			b.realizarFuncion(x, y, true);
			this.miArmamento.retirarArma(5);
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
		ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).enviarCasillas(casillas);
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
