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
}
