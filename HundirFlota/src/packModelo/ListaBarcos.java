package packModelo;
import java.util.ArrayList;


public class ListaBarcos {
	private ArrayList<Barco> lista;
	
	public ListaBarcos() {
		this.lista=new ArrayList<Barco>();
	}
	
	public void anadirBarco(Barco pBarco) {
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
}
