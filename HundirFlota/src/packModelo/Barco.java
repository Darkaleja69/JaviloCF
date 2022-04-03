package packModelo;

public abstract class Barco {
	private int longitud;
	private int vidas;
	
	
	public Barco(int pLongitud) {
		longitud = pLongitud;
		vidas = pLongitud;
		
	}
	
	public boolean estaHundido() {
		
		return (this.vidas==0);
	}
	
	
	public void recibirImpacto() {
		this.vidas --;
		System.out.println(vidas);
	}
	
	public int getLongitud()
	{
		return this.longitud;
	}
	
}
