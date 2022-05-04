package packModelo;

import java.util.Observable;

public abstract class Personaje extends Observable{
	private ListaBarcos listab;
	private int radares;
	private int cantEscudos;
	private Casilla radar;
	private ComportamientoDisparar cDisparar;
	private ComportamientoRadar cRadar;
	private ComportamientoEscudo cEscudo;
	private ComportamientoMisil cMisil;
	

	
	
	//Constructora
	private Personaje() {
		listab = new ListaBarcos();
		radares = 5;
		cantEscudos = 3;
		radar = new Casilla(0,0);
	}
	
	//Métodos
	public void disparar() {
		cDisparar.disparar();
	}
	
	public void ponerEscudo() {
		cEscudo.escudo();
	}
	
	
	public void radarAleatorio() {
		cRadar.Radar();
		
	}
	
	public void misil() {
		cMisil.Misil();
	}
	
	
	//Getters y setter
	public ListaBarcos getBarcos() {
		return(listab);
	}
	public int getRadares() {
		return(radares);
	}
	public int getEscudos() {
		return(cantEscudos);
	}

	
}
