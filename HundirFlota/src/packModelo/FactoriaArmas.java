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
	
	public Arma crearArma(String pTipo) {
		Arma nuevaArma=null;
		if(pTipo.contentEquals("Misil")) {
			nuevaArma=new Misil();
		}
		else if(pTipo.equals("Escudo")) {
			nuevaArma=new Escudo();
		}
		else if(pTipo.contentEquals("Reparación")) {
			nuevaArma=new Reparacion();
		}
		else if(pTipo.equals("Bomba")) {
			nuevaArma=new Bomba();
		}
		return nuevaArma;
	}
	

}
