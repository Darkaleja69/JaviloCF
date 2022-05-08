package packModelo;

public class Almacen {
	
	private int cantReparaciones;
	private int cantEscudos;
	private int cantMisiles;
	private int cantBombas;
	
	private int precioReparacion;
	private int precioEscudo;
	private int precioMisil;
	private int precioBomba;
	
	private static Almacen miAlmacen;
	
	
	private Almacen()
	{
		cantReparaciones = 10;
		cantEscudos = 10;
		cantMisiles = 10;
		cantBombas = 10;
		precioReparacion = 10;
		precioEscudo = 10;
		precioMisil = 10;
		precioBomba = 10;
	}
		
	
	public static Almacen getMiAlmacen()
	{
		if (miAlmacen == null)
		{
			miAlmacen = new Almacen();
		}
		return miAlmacen;
	}
	
	public void comprarArma(String pArma)
	{
		if (pArma.equals("Reparacion"))
		{
			if (cantReparaciones < 0)
			{
				cantReparaciones = cantReparaciones - 1;
			}
		}
	    else if(pArma.equals("Escudo"))
	    {
	    	if (cantEscudos < 0)
			{
				cantEscudos = cantEscudos - 1;
			}
	    }
	    else if(pArma.equals("Misil"))
	    {
	    		if (cantMisiles < 0)
	    		{
	    			cantMisiles = cantMisiles - 1;
	    		}
	    }
	    else
	    {
	    	if (cantBombas < 0)
			{
				cantBombas = cantBombas - 1;
			}
	    }
	}
	
	public int getPrecio(String pArma)
	{
		int precio;
		if (pArma.equals("Reparacion"))
		{
			precio = precioReparacion;
		}
		else if (pArma.equals("Escudo"))
		{
			precio = precioEscudo;
		}
		else if (pArma.equals("Misil"))
		{
			precio = precioMisil;
		}
		else
		{
			precio = precioBomba;
		}
		return precio;
	}
}
