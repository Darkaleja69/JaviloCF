package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Jugador extends Observable {
	private ListaBarcos lista;
	private int radares;
	private int cantEscudos;
	private int cantReparaciones;
	private int dinero;
	private Armamento miArmamento;
	
	public Jugador() {
		lista=new ListaBarcos();
		radares = 1;
		cantEscudos = 3;
		dinero=500;
		miArmamento=new Armamento();
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
	public Arma obtenerArma(int pOpcion) {
		return (this.miArmamento.buscarArma(pOpcion));
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
	
	public int armasEnArmamento(int pOpcion) {
		return miArmamento.armasPorUsar(pOpcion);
	}
	public int dineroRestante() {
		return dinero;
	}
	public int radaresDisponibles() {
		return(radares);
	}
		
	public boolean turnoJugador(int pOpcion,int pX,int pY) {
		boolean finTurno=false;
			Arma miArma=miArmamento.buscarArma(pOpcion);
			if( miArma != null && miArmamento.armasPorUsar(pOpcion)>0) {
				if(miArma.realizarFuncion(pX,pY,false)) {
					finTurno=true;
					miArmamento.retirarArma(pOpcion);
				}
			}
		return finTurno;
	}
	
	public void comprarArmamento(int pOpcion) {
		Arma ar;
		if(miArmamento.consultaArmamento(pOpcion, dinero)) {
			if(pOpcion==4) {
				Radar r=(Radar) miArmamento.buscarArma(4);
				dinero=dinero - r.getCoste();
				r.comprarRadar();
				
				
			}
			else if(pOpcion == 5) {
				int cant = 10;
				for(int i = 0; i<cant+1; i++) {
					ar = miArmamento.comprarArmamento(5);
					miArmamento.anadirArmamento(ar);
				}
			}
			ar=miArmamento.comprarArmamento(pOpcion);
			dinero=dinero - ar.getCoste();
			miArmamento.anadirArmamento(ar);
		}
	}
	
	public boolean armamentoVacio() {
		return miArmamento.armamentoVacio();
	}
	
}
