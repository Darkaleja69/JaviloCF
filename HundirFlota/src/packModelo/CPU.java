package packModelo;

import java.util.ArrayList;
import java.util.Random;

public class CPU {
	private static CPU miCPU;
	private ListaBarcos listaB;
	
	private CPU() 
	{
		listaB=new ListaBarcos();
	}
	
	public static CPU getMiCPU() 
	{
		if (miCPU == null) 
		{
			miCPU = new CPU();
		}
		return miCPU;
	}
	
	/*
	 * ESTO SE DEBE REVISAR
	 */
	public Barco colocarBarco(int pLongitud) 
	{	ArrayList<Casilla> casillas=new ArrayList<Casilla>();
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
			horizontal=num.nextBoolean();
			posible = Tablero.getTablero().todoValido(x, y, pLongitud, false, horizontal);
		}
		Tablero.getTablero().colocarBarco(bar, x, y,pLongitud,horizontal,false);
		return bar;
		
	}
	
	public ArrayList<Casilla> disparar(){
		ArrayList<Casilla> casillas=new ArrayList<Casilla>();
		boolean posible=false;
		int x = 0;
		int y = 0;
		Random num = new Random();
		while(!posible) 
		{
			x=num.nextInt(10);
			y=num.nextInt(10);
			posible=!(Tablero.getTablero().getCasilla(x, y, true).estaTocada());
		}
		return (Tablero.getTablero().bombardear(x, y,true));
	}
	
	public void anadirBarco(Barco pBarco) {
		this.listaB.anadirBarco(pBarco);
	}
	
	public boolean comprobarFin() {
		return this.listaB.comprobarFin();
	}
	
}
