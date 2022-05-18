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
                    r = recolocar(pAQuien);
                    recolocar = true;
                }
                casillas = Tablero.getTablero().colocarRadar(r.getFila(), r.getColumna(), pAQuien);
            }
            else{//se quiere activar el radar
                
               	sig = true;
               	casillas = Tablero.getTablero().detectar(pX, pY, pAQuien);
                
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
    	if(radares > 0){
    		if(r != null) {
                Tablero.getTablero().getCasilla(r.getFila(), r.getColumna(), pAquien).quitarRadar();
            }
            Random num1 = new Random();
            Random num2 = new Random();
            int x = num1.nextInt(7)+2;
            int y= num2.nextInt(7)+2;
            c = new Casilla(x,y);
            c.ponerRadar();
            usarRadar();
        }

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