package packModelo;

public class FactoryBarcos {
	
	//Atributos
	private static FactoryBarcos miFactoryBarcos;
	
	//Constructora
	private FactoryBarcos() {
	}
	
	public static FactoryBarcos getMiFactoryBarcos() {
		if (miFactoryBarcos==null) {
			miFactoryBarcos=new FactoryBarcos();
		}
		return miFactoryBarcos;
	}
	
	public Barco crearBarco(int pTipo) 
	{
		Barco b;
		if(pTipo == 1)
		{
			b = new Fragata(1);
		}
		else if(pTipo == 2)
		{
			b = new Destructor(2);
		}
		else if(pTipo == 3)
		{
			b = new Submarino(3);
		}
		else
		{
			b = new Portaviones(4);
		}
		
		return b;
	}
}
