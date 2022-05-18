package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Jugador extends Observable {
	private ListaBarcos lista;
	private int radares;
	private int dinero;
	private Armamento miArmamento;
	
	public Jugador() {
		lista=new ListaBarcos();
		radares = 1;
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
	
	public boolean colocarBarcosAleatorio() {
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		ArrayList<Casilla> casillasBarcoFin=new ArrayList<Casilla>();		
		for(int i=1;i<5;i++) {
			while(!(lista.hayDemasiados(i))) {
				boolean horizontal=false;
				int x = 0;
				int y = 0;
				Random num = new Random();
				boolean posible = false;
				Barco bar = FactoryBarcos.getMiFactoryBarcos().crearBarco(i);
				lista.anadirBarco(bar);
				while(!posible) 
				{
					x = num.nextInt(10);
					y = num.nextInt(10);
					horizontal = num.nextBoolean();
					posible = Tablero.getTablero().todoValido(x, y, i, true, horizontal);
				}
				CPU maquina =(CPU)ListaJugadores.getMiListaJug().obtenerJugadorOCPU(0);
				maquina.colocarBarco(i);
				casillas.addAll(Tablero.getTablero().colocarBarco(bar, x, y,i,horizontal,true));
			}
			casillasBarcoFin.addAll(casillas);
		}
		
		setChanged();
		notifyObservers(casillasBarcoFin);
		return !(casillasBarcoFin.size() < 1);
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
				r.comprarRadar();	
			}
			else if(pOpcion == 5) {
				int cant = 10;
				for(int i = 1; i<=cant; i++) {
					ar = miArmamento.comprarArmamento(5);
					miArmamento.anadirArmamento(ar);
				}
			}else {
				ar=miArmamento.comprarArmamento(pOpcion);
				dinero=dinero - ar.getCoste();
				miArmamento.anadirArmamento(ar);
			}
		}
	}
	
	public boolean armamentoVacio() {
		return miArmamento.armamentoVacio();
	}
	
}
