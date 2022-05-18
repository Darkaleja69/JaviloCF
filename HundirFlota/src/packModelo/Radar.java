package packModelo;

import java.util.ArrayList;
import java.util.Random;

public class Radar extends Arma{
    private Casilla r;
    private int radares; 

    public Radar () {
        r = null;
        radares = 1;
        super.coste = 100;
    }

    public boolean realizarFuncion(int pX,int pY, boolean pAQuien) {
        //para los turnos, que el metodo devuelva un booleano para saber si ha funcionado y q haga la CPU su movimiento
            boolean sig = false;
            boolean recolocar = false;
            ArrayList<Casilla> casillas = new ArrayList<Casilla>();
            if((pX > 10 && pY > 10 )) { //se quiere recolocar el radar
                if(radares>0) {
                    if(r != null) {
                    	Casilla c = r;
                    	c.quitarRadar(3);
                    	Tablero.getTablero().getCasilla(r.getFila(), r.getColumna(), pAQuien).quitarRadar(0);
                    	casillas.add(c);
                    }
                	r = recolocar(pAQuien);
                    recolocar = true;
                    Tablero.getTablero().colocarRadar(r.getFila(), r.getColumna(), pAQuien);
                    casillas.add(r); 
                }
            }
            else{//se quiere activar el radar
                if(!Tablero.getTablero().radarUsado(r, pAQuien)) {
                	sig = true;
                   	casillas = Tablero.getTablero().detectar(r.getFila(), r.getColumna(), pAQuien);
                   	}
            }
            
            if(sig || (recolocar && !sig)) {
            	if(pAQuien) {
                    ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).enviarCasillas(casillas);
                }else {
                    ListaJugadores.getMiListaJug().obtenerJugadorOCPU(0).enviarCasillas(casillas);
                }
            }
            
            return(sig);
    }

    public Casilla recolocar(boolean pAquien) {
        Casilla c = null;
    	Random num1 = new Random();
    	Random num2 = new Random();
    	int x = num1.nextInt(7)+2;
    	int y= num2.nextInt(7)+2;
    	c=Tablero.getTablero().getCasilla(x, y, pAquien);
		if(pAquien) {
		   System.out.println(c.getFila()); 		
		    	}
    	c.ponerRadar();
    	usarRadar();

        return(c);

    }
    public Casilla getRadar() {
        return r;
        
    }
    public void comprarRadar() {
    	this.radares ++;
    }
    public void usarRadar() {
    	this.radares --;
    }
    public int cantRadares() {
        return radares;
    }
}