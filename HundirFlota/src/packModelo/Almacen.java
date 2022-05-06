package packModelo;

public class Almacen {
	
	private int cantReparaciones;
	private int cantEscudos;
	private int cantMisiles;
	private int cantBombas;
	
	private double precioReparacion;
	private double precioEscudo;
	private double precioMisil;
	private double precioBomba;
	
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
}
