package packModelo;

public abstract class Arma {
	protected int coste=0;
	
	public Arma() {}
	
	public boolean realizarFuncion(int pX,int pY, boolean pAQuien) {
		return true;
	}
	public int getCoste() {
		return coste;
	}
}
