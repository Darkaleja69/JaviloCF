package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Jugador extends Observable {
	private static Jugador miJugador;
	private ListaBarcos lista;
	private int radares;
	private int cantEscudos;
	private int cantReparaciones;
	private int dinero;
	private Armamento miArmamento;
	
	private Jugador() {
		lista=new ListaBarcos();
		radares = 5;
		cantEscudos = 3;
		dinero=500;
		miArmamento=new Armamento();
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
		
		//lanzarBomba()
	}
	
	public boolean misil(int pX,int pY) {
		boolean disparado = false;
		Casilla b = null;
		Casilla x = Tablero.getTablero().getCasilla(pX, pY, false);
		if(miArmamento.consultaArmamento(1,dinero)) {
			if(!x.estaTocada()) 
			{
				b = x;
				Tablero.getTablero().lanzarMisil(pX, pY, false);
				ArrayList<Casilla> casillas = new ArrayList<Casilla>();
				casillas.add(b);
				CPU.getMiCPU().enviarCasillas(casillas);
				disparado = true;
				dinero=dinero-100;
			}
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
	
	public boolean radar() {
		//para los turnos, que el m�todo devuelva un booleano para saber si ha funcionado y q haga la CPU su movimiento
		
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		boolean radarizado = false;
		if(this.quedanRadares()) {
			radares = radares -1;
			radarizado = true;
			Random num1 = new Random();	
			Random num2 = new Random();	
			int pX = num1.nextInt(7)+2;
			int pY= num2.nextInt(7)+2;
			int fmax = pX +1;
			int cmax = pY +1;
			
			Casilla c = null;
		
			for(int i = pX -1;i<=fmax;i++) {
			
				for(int j = pY -1;j<=cmax;j++) {
				
					c = Tablero.getTablero().getCasilla(i, j, false);
					c.ponerRadar();
					casillas.add(c);
				}
			
			}
		
			CPU.getMiCPU().enviarCasillas(casillas);
		
		}
	
		return(radarizado);
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
	
	
	public void colocarEscudo(int pX, int pY)
	{	if(miArmamento.consultaArmamento(2, dinero)) {
		cantEscudos--;
		ArrayList<Casilla> casillas = Tablero.getTablero().colocarEscudo(pX, pY, true);
		Jugador.getJugador().enviarCasillas(casillas);
		dinero=dinero-50;
	}
}
	
	public int escudosPorColocar() {
		return this.cantEscudos;
	}
	
	public boolean quedanRadares() {
		return(this.radares>=1);
	}
	
	public int cantidadRadares() {
		return this.radares;
	}
	
	public int dineroRestante() {
		return dinero;
	}
	
	public void comprarReparacion()
	{
		int precio = Almacen.getMiAlmacen().getPrecio("Reparacion");
		if (dinero >= precio)
		{
			this.cantReparaciones++;
			dinero= dinero-precio;
		}
	}
	
	public void comprarEscudo()
	{
		int precio = Almacen.getMiAlmacen().getPrecio("Escudo");
	}
	
	public void comprarMisil()
		{
			
		}
	
	//public void comprarBomba()
	//{
	//int precio = Almacen.getMiAlmacen().getPrecio("Bomba");
	//if (dinero >= precio)
	//{
	//this.cantBombas++;
	//dinero= dinero-precio;
	//}
	//}
	
	public void usarReparacion()
	{
		cantReparaciones--;
	}
}
