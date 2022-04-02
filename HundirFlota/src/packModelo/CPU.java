package packModelo;

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
	public void colocarBarcos() 
	{
		int x = 0;
		int y = 0;
		Random num = new Random();
		boolean posible = false;
		for(int i = 0; i <= 3; i++) 
		{
			Barco bar = FactoryBarcos.getMiFactoryBarcos().crearBarco(1);
			listaB.anadirBarco(bar);
			while(!posible) 
			{
				x = num.nextInt(9);
				y = num.nextInt(9);
				posible = Tablero.getTablero().todoValido(x, y, 1, false, true);
			}
			
			posible=false;
			//Tablero.getTablero().colocarBarco(bar, x, y);
		}
		
		for(int i=0;i<=2;i++) 
		{
			Barco bar=FactoryBarcos.getMiFactoryBarcos().crearBarco(2);
			listaB.anadirBarco(bar);
			while(!posible) {
				x = num.nextInt(9);
				y = num.nextInt(9);
				//posible=Tablero.getTablero().valido(x, y, false);
			}
			posible=false;
			//Tablero.getTablero().colocarBarco(bar, x, y);
		}
		
		for(int i=0; i<=1; i++) {
			Barco bar=FactoryBarcos.getMiFactoryBarcos().crearBarco(3);
			listaB.anadirBarco(bar);
			while(!posible) 
			{
				x = num.nextInt(9);
				y = num.nextInt(9);
				//posible=Tablero.getTablero().valido(x, y, false);
			}
			posible = false;
			//Tablero.getTablero().colocarBarco(bar, x, y);
		}
		
		Barco bar = FactoryBarcos.getMiFactoryBarcos().crearBarco(4);
		listaB.anadirBarco(bar);
		while(!posible) 
		{
			x=num.nextInt(9);
			y=num.nextInt(9);
			//posible=Tablero.getTablero().valido(x, y, false);
		}
		posible=false;
		//STablero.getTablero().colocarBarco(bar, x, y);
	}

}
