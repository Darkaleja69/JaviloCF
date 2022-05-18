package packModelo;
import java.util.ArrayList;


public class ListaBarcos {
	private ArrayList<Barco> lista;
	
	public ListaBarcos() 
	{
		this.lista=new ArrayList<Barco>();
	}
	
	public void anadirBarco(Barco pBarco) 
	{
		this.lista.add(pBarco);
	}
	
	public boolean hayDemasiados(int pLong)
	{
		int cont = 0;
		for(Barco b : lista)
		{
			if(b.getLongitud() == pLong)
			{
				cont++;
			}
		}
		return (5 == pLong + cont);
	}
	
	public boolean comprobarFin() {
		boolean fin=true;
		int i=0;
		while(fin && i<10) 
		{
			fin=lista.get(i).estaHundido();
			i++;
		}
		return fin;
	}
	
	public boolean lleno() 
	{
		return(this.lista.size()==10);
	}
	
	public int cantidadBarcos(int pLong) {
		int cont=0;
		for(Barco b : lista)
		{
			if(b.getLongitud() == pLong)
			{
				cont++;
			}
		}
		return cont;
	}
	
	public ArrayList<Barco> barcosSinHundir()
	{
		ArrayList<Barco> barcos = new ArrayList<Barco>();
		for (Barco b: this.lista)
		{
			if (!b.estaHundido())
			{
				barcos.add(b);
			}
		}
		return barcos;
	}
	
	public boolean barcoReparable(int pReparaciones)
	{
		boolean posible = false;
		int i = 0;
		while(i < this.lista.size() && !posible)
		{
			Barco b = this.lista.get(i);
			posible = (pReparaciones >= b.getLongitud()) && b.estaRoto();
			i++;
		}
		System.out.println(posible);
		return posible;
	}
	
	public Barco getBarcoReparar(int pReparaciones)
	{
		boolean posible = false;
		Barco h = null;
		int i = 0;
		while(i < this.lista.size() && !posible)
		{
			Barco b = this.lista.get(i);
			posible = pReparaciones >= b.getLongitud() && b.estaRoto();
			h = b;
			i++;
		}
		return h;
	}
}
