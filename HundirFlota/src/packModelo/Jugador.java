package packModelo;

import java.util.ArrayList;
import java.util.Observable;

public class Jugador extends Observable {
	private static Jugador miJugador;
	private ListaBarcos lista;
	private int radares;
	private int cantEscudos;
	
	private Jugador() {
		lista=new ListaBarcos();
		radares = 5;
		cantEscudos = 3;
	}
	
	public static Jugador getJugador() {
		if(miJugador==null) {
			miJugador=new Jugador();
		}
		return miJugador;
	}
	
	public boolean disparar(int pX,int pY) {
		boolean disparado = false;
		Casilla b = null;
		Casilla x = Tablero.getTablero().getCasilla(pX, pY, false);
		if(!x.estaTocada()) 
		{
			b = x;
			Tablero.getTablero().bombardear(pX, pY, false);
			ArrayList<Casilla> casillas = new ArrayList<Casilla>();
			casillas.add(b);
			CPU.getMiCPU().enviarCasillas(casillas);
			disparado = true;
		}
		return disparado;
	}
	
	public boolean colocarBarcos(boolean pHorizontal, int pX, int pY, int pLongitud){
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		if(!hayDemasiados(pLongitud)) {
			
			if(Tablero.getTablero().todoValido(pX, pY, pLongitud, true, pHorizontal))
			{
				Barco b = FactoryBarcos.getMiFactoryBarcos().crearBarco(pLongitud);
				anadirBarco(b);
				casillas = Tablero.getTablero().colocarBarco(b, pX, pY, pLongitud, pHorizontal,true);
			}
		}
		setChanged();
		notifyObservers(casillas);
		return !(casillas.size() < 1);
	}
	
	public void radar(int pX, int pY) {
		//para los turnos, que el método devuelva un booleano para saber si ha funcionado y q haga la CPU su movimiento
		
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		if(this.quedanRadares()) {
			radares = radares -1;
			int fmax = pX +1;
			int cmax = pY +1;
			
			Casilla c = null;
		
			for(int i = pX -1;i<fmax+1;i++) {
			
				for(int j = pY -1;j<cmax+1;j++) {
				
					c = Tablero.getTablero().getCasilla(i, j, false);
					c.ponerRadar();
					casillas.add(c);
				}
			
			}
		CPU.getMiCPU().enviarCasillas(casillas);
		
		}
	
		//return(casillas.size() >0);
	}
	
	public boolean hayDemasiados(int pLong) {
		return lista.hayDemasiados(pLong);
	}
	
	public void anadirBarco(Barco pBarco) {
		this.lista.anadirBarco(pBarco);
	}
	
	public boolean comprobarFin() {
		return this.lista.comprobarFin();
	}
	
	public boolean barcosColocados() {
		return this.lista.lleno();
	}
	
	public void enviarCasillas(ArrayList<Casilla> pCasillas)
	{
		setChanged();
		notifyObservers(pCasillas);
	}
	public int barcosPorColocar(int pLong) {
		return 5-(this.lista.cantidadBarcos(pLong)+pLong);
	}
	
	public boolean escudosSuficientes()
	{
		return cantEscudos > 0;
	}
	
	public void colocarEscudo(int pX, int pY)
	{
		cantEscudos--;
		ArrayList<Casilla> casillas = Tablero.getTablero().colocarEscudo(pX, pY, true);
		Jugador.getJugador().enviarCasillas(casillas);
		
	}
	
	public int escudosPorColocar() {
		return this.cantEscudos;
	}
	
	public boolean quedanRadares() {
		return(this.radares>=1);
	}
	
	
}
