package packModelo;

public class FactoriaArmas {
	private static FactoriaArmas miFactoria;
	
	private FactoriaArmas() {}
	
	public static FactoriaArmas getMiFactoria() {
		if(miFactoria==null) {
			miFactoria=new FactoriaArmas();
		}
		return miFactoria;
	}
	
	public Arma crearArma(int pTipo) {
		Arma nuevaArma=null;
		if(pTipo==1) {
			nuevaArma=new Misil();
		}
		else if(pTipo==2) {
			nuevaArma=new Escudo();
		}
		else if(pTipo==3) {
			nuevaArma=new Reparacion();
			
		}
		else if(pTipo==4) {
			nuevaArma=new Radar();
		}
		else if(pTipo==5) {
			nuevaArma=new Bomba();
		}
		return nuevaArma;
	}
	

}
