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
	public ArrayList<Casilla> colocarBarco(int pLongitud) 
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
				x = num.nextInt(9);
				y = num.nextInt(9);
				horizontal=num.nextBoolean();
				posible = Tablero.getTablero().todoValido(x, y, pLongitud, false, horizontal);
			}
			casillas=Tablero.getTablero().colocarBarco(bar, x, y,pLongitud,horizontal,false);
			return casillas;
		
	}
}
