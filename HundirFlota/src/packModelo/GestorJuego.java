package packModelo;

import java.util.ArrayList;
import java.util.Observable;

public class GestorJuego extends Observable {
	private static GestorJuego miGestor;
	private ListaBarcos lBarcJugador;
	private ListaBarcos lBarcCPU;
	
	private GestorJuego() 
	{
		lBarcJugador = new ListaBarcos();
		lBarcCPU= new ListaBarcos();
	}
	public static GestorJuego getMiGestorJuego() {
		if (miGestor==null) {
			miGestor=new GestorJuego();
		}return miGestor;
	}
	
	//Metodos
	public void colocarBarcosJug(boolean pHorizontal, int pX, int pY, int pLongitud)
	{
		if(!lBarcJugador.hayDemasiados(pLongitud))
		{
			ArrayList<Casilla> casillas = new ArrayList<Casilla>();
			
			if(Tablero.getTablero().todoValido(pX, pY, pLongitud, true, pHorizontal))
			{
				Barco b = FactoryBarcos.getMiFactoryBarcos().crearBarco(pLongitud);
				lBarcJugador.anadirBarco(b);
				casillas = Tablero.getTablero().colocarBarco(b, pX, pY, pLongitud, pHorizontal,true);
				lBarcCPU.anadirBarco(CPU.getMiCPU().colocarBarco(pLongitud));
			}
			//actualizar vista
			setChanged();
			notifyObservers(casillas);
		}
	}
	
	public void disparar(int pX,int pY) 
	{
		boolean fin=false;
		ArrayList<Casilla> casillas=new ArrayList<Casilla>();
		Casilla x=Tablero.getTablero().getCasilla(pX, pY, false);
		if(!x.estaTocada()) 
		{
			casillas.add(x);
			Tablero.getTablero().bombardear(pX, pY, false);
			if(comprobarFin(false)) 
			{
				fin=true;
			}
			else 
			{
				//Disparo CPU
				casillas.addAll(CPU.getMiCPU().disparar());
				if(comprobarFin(true)) 
				{
					fin=true;
				}
			}
			System.out.println("hola");
			setChanged();
			notifyObservers(casillas);	
		}
		if(fin) 
		{
			System.out.println("hola+fin");
			setChanged();
			notifyObservers(fin);
		}
	}
	
	private boolean comprobarFin(boolean pJug) {
		boolean fin=true;
		if(pJug) {
			fin=this.lBarcJugador.comprobarFin();
			
		}else {
			fin=this.lBarcCPU.comprobarFin();
		}
		return fin;
	}
	
	public boolean barcosColocados() {
		return lBarcJugador.lleno();
	}
	
	
	
}
