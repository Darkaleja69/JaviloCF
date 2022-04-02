package packModelo;

public abstract class Barco {
	private int longitud;
	private String[] coordenadas;
	private boolean[] tocado;
	private int vidas;
	
	
	public Barco(int pLongitud) {
		longitud = pLongitud;
		vidas = pLongitud;
		tocado = new boolean[pLongitud];
		for(int i = 0; i < longitud; i++){
			tocado[i] = false;
		}
		//pedir coordenadas
	}
	
	public boolean estaHundido() {
		boolean hundido = false;
		int i = 0;
		while(!hundido && i>longitud -1) {
			hundido = tocado[i];
			i++;
		}
		return (hundido);
	}
	//public Casilla[] getCoordenadas
	
	
	public void recibirImpacto(Casilla pCasilla) {
		this.vidas --;
		int pFila = pCasilla.getFila();
		int pCol = pCasilla.getColumna();
		
		//metodo para conseguir las cordenadas del barco
		int Xinic = 0 ;
		int Xfin = 0 ;
		int Yinic = 0 ;
		int Yfin = 0 ;
		
		if(longitud > 1) {
			if(Xfin - Xinic == 0) {
				impactado(Yfin - Yinic -1);
			}
			else if(Yfin - Yinic == 0) {
				impactado(Xfin - Xinic - 1);
			}
		}
		else {
			tocado[0] = false;
		}
		
	}
	
	public void impactado(int pos) {
		
		this.tocado[pos] = true;
		
	}	
	
	public int getLongitud()
	{
		return this.longitud;
	}
	
}
