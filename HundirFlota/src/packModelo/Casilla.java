package packModelo;

public class Casilla {
	private boolean tieneBomba;
	private int fila;
	private int columna;
	private Barco barco;
	private boolean radar;
	
	public Casilla(int pFila,int pColumna) {
		fila=pFila;
		columna=pColumna;
		barco = null;
		tieneBomba = false;
		radar = false;
	}
	
	public boolean getRadar() {
		return radar;
	}
	
	public int getFila() {
		return fila;
	}
	public int getColumna() 
	{
		return columna;
	}
	
	public boolean estaTocada() {
		return tieneBomba;
	}
	
	public void bombardear() 
	{
		if (!tieneBarco())
		{
			tieneBomba = true;
		}
		else 
		{
			if (!this.barco.tieneEscudo())
			{
				tieneBomba = true;
			}
			this.barco.recibirImpacto();
		}
	}
	
	public void lanzarMisil() 
	{
		if (!tieneBarco())
		{
			tieneBomba = true;
		}
		else 
		{
			if (!this.barco.tieneEscudo())
			{
				tieneBomba = true;
			}
			this.barco.recibirImpacto();
		}
	}
	
	public void colocarBarco(Barco pBarco) {
		
		barco = pBarco;
	}
	
	public Barco getBarco() {
		return barco;
	}
	
	public boolean tieneBarco()
	{
		return (!(this.barco==null));
	}
	
	public boolean tieneEscudo() {
		return this.barco.tieneEscudo();
	}
	
	public boolean tieneRadar() {
		return(this.radar==true);
		
	}
	public void ponerRadar() {
		this.radar = true;
	}
	public void quitarRadar() {
		this.radar = false;
	}
}
