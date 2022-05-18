package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Armamento {
	private ArrayList<Arma> armas;
		
		
		
	
	public Armamento() {
		armas=new ArrayList<Arma>();
		armas.add(new Radar());
	}
	
	
	public boolean consultaArmamento(int pOpcion, int pDinero) {
		boolean posible=false;
		if(pOpcion==1) { //misil
			if(pDinero>=100) {
				posible=true;
			}
		}else if(pOpcion==2) {
			if(pDinero>=50) {
				posible=true;
			}
		}else if(pOpcion==3) { 
			if(pDinero>=25) {
				posible=true;
			}
		}else if(pOpcion==4) { 
			if(pDinero >= 100) {
				posible = true;
		}
		}else if(pOpcion==5) { // Bomba: Coste de bomba es 0
			posible=true;
			
		}else {
			posible=true;
			}
		
		return posible;
	}
	
	public Arma comprarArmamento(int pTipo) {
		Reparacion x=(Reparacion) buscarArma(3);
		Radar y = (Radar) buscarArma(4);
		if(pTipo==3) {
			if(x==null) {
				return FactoriaArmas.getMiFactoria().crearArma(pTipo);
			}else {
				x.sumarCasilla();
			}
			
		}
		else if(pTipo == 4) {
			y.comprarRadar();
			return y;
		}
		else {
			return FactoriaArmas.getMiFactoria().crearArma(pTipo);
		}
		return x;
		
	}
	
	public void anadirArmamento(Arma pArma) {
		armas.add(pArma);
	}
	
	public void retirarArma(int pOpcion) {
		if(pOpcion == 4) {
			Radar r = (Radar) this.buscarArma(4);
			r.usarRadar();
		}
		else {
			
		}
		armas.remove(buscarArma(pOpcion));
	}
	
	public Arma buscarArma(int pOpcion) {
		Iterator<Arma> itr=this.getIterator();
		Arma x=null;
		boolean enc=false;
		if(pOpcion==1) { // Misil
			while(itr.hasNext() && !enc) {
				x=itr.next();
				if(x instanceof Misil) {
					enc=true;
					
				}
			}
		}else if(pOpcion == 2) { // Escudo
			while(itr.hasNext() && !enc) {
				x=itr.next();
				if(x instanceof Escudo) {
					enc=true;
				}
			}
		}else if(pOpcion==3) { //Reparacion
			while(itr.hasNext() && !enc) {
				x=itr.next();
				if(x instanceof Reparacion) {
					enc=true;
				}
			}
		}else if(pOpcion==4) {
			while(itr.hasNext() && !enc) {
				x=itr.next();
				if(x instanceof Radar) {
					enc=true;
				}
			}
		} else if(pOpcion==5){
			while(itr.hasNext() && !enc) {
				x=itr.next();
				if(x instanceof Bomba) {
					enc=true;
				}
			}
		}
		if(!enc) {
			x=null;
			
		}
		return x;
	}
	
	private Iterator<Arma> getIterator(){
		return armas.iterator();
	}
	
	public int armasPorUsar(int pOpcion) {
		boolean enc=false;
		int num=0;
		Iterator<Arma> itr=this.getIterator();
		Arma x=null;
		if(pOpcion==1) {
			while(itr.hasNext()) {
				x=itr.next();
				if(x instanceof Misil) {
					num++;
				}
			}
		}else if(pOpcion==2) {
			while(itr.hasNext()) {
				x=itr.next();
				if(x instanceof Escudo) {
					num++;
				}
			}
		}else if(pOpcion==3) {
			while(itr.hasNext() && !enc) {
				x=itr.next();
				if(x instanceof Reparacion) {
					enc=true;
					return ((Reparacion) x).getCasillas();
				}
			}
		}else if(pOpcion==4) {
			while(itr.hasNext()) {
				x=itr.next();
				if(x instanceof Radar) {
					num = ((Radar) x).cantRadares();
				}
			}
		}else if(pOpcion==5) {
			while(itr.hasNext()) {
				x=itr.next();
				if(x instanceof Bomba) {
					num++;
				}
			}
		}
		return num;
	}
	
	public boolean armamentoVacio() {
		return armas.size()==0;
	}
	
}
