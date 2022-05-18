package packModelo;

public class Casilla {
	private boolean tieneBomba;
	private int fila;
	private int columna;
	private Barco barco;
	private int radar;
	
	
	public Casilla(int pFila,int pColumna) {
		fila=pFila;
		columna=pColumna;
		barco = null;
		tieneBomba = false;
		radar = 0;
	}
	
	public int getRadar() {
		// 0 si no ha sido revelado
		// 1 si ha sido revelado por el radar
		// 2 si es el radar
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
			this.barco.eliminar();
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
	
	public boolean detectado() {
		return(radar==1);
		
	}
	public void detectar() {
		radar = 1;
	}
	public void ponerRadar() {
		radar = 2;
	}
	public void quitarRadar(int pRadar) {
		radar = pRadar;
	}
	
	public void reparar()
	{
		this.tieneBomba = false;
	}
}
