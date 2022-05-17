package packModelo;

import java.util.ArrayList;
import java.util.Observable;

public class ListaJugadores extends Observable{
	private static ListaJugadores miListaJug;
	private ArrayList<Jugador> listaJ;
	private int turno;
	
	public ListaJugadores() {
		listaJ=new ArrayList<Jugador>();
		turno=1;
	}
	
	public static ListaJugadores getMiListaJug() {
		if(miListaJug==null) {
			miListaJug=new ListaJugadores();
		}
		return miListaJug;
	}
	
	public void inicializarLista() {
		listaJ.add(new CPU());
		listaJ.add(new Jugador());
	}
	
	public void realizarTurno(int pOpcion,int pX,int pY) {
		boolean fin = false;
		int jugadorOCPU = 1;
		if(!(listaJ.get(1).armamentoVacio())) {
			if(pX <0 && pY <0) {
				
				
			}
			else {
				if(listaJ.get(1).turnoJugador(pOpcion, pX, pY)) {		
					if(comprobarFin(false)) 
					{	
						setChanged();
						notifyObservers(jugadorOCPU);
					}else {
						CPU maquina=(CPU) listaJ.get(0);
						maquina.turnoCPU();
						}
						
				}
			}
		}else {
			jugadorOCPU=3;
			setChanged();
			notifyObservers(jugadorOCPU);
		}
	}
	
	public Jugador aQuienLeToca() {
		if(turno % 2 == 0) {
			return listaJ.get(0);
		}else {
			return listaJ.get(1);
		}
	}
	
	public Jugador obtenerJugador() {
		return listaJ.get(1);
	}
	
	public Jugador obtenerCPU() {
		return listaJ.get(0);
	}
	
	public boolean comprobarFin(boolean pJug) {
		boolean fin=true;
		if(pJug) 
		{
			fin=listaJ.get(1).comprobarFin();
		}
		else 
		{
			fin=listaJ.get(0).comprobarFin();
		}
		return fin;
	}
	
	public void colocarBarcos(boolean pHorizontal, int pX, int pY, int pLongitud)
	{
		if(listaJ.get(1).colocarBarcos(pHorizontal, pX, pY, pLongitud))
		{
			CPU maquina=(CPU) listaJ.get(0);
			maquina.colocarBarco(pLongitud);
		}
	}
	
}
