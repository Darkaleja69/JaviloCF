package packModelo;

public abstract class Barco {
	private int longitud;
	private int vidas;
	private Escudo escudo;
	
	public Barco(int pLongitud) {
		longitud = pLongitud;
		vidas = pLongitud;
		this.escudo=null;
		
	}
	
	public boolean tieneEscudo() {
		return !(this.escudo == null);
	}
	
	public void colocarEscudo(Escudo pEsc)
	{
		this.escudo = pEsc;
	}
	
	public boolean estaHundido() {
		
		return (this.vidas==0);
	}
	
	
	public void recibirImpacto() {
		System.out.println("recibe impacto");
		if (escudo != null)
		{
			this.vidas --;
		}
		else
		{
			escudo.recibirImpacto();
			System.out.println(escudo.funcional());
			if (!escudo.funcional())
			{
				this.escudo = null;
			}
		}
	}
	
	public int getLongitud()
	{
		return this.longitud;
	}
	
}
