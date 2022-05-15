package packModelo;

import java.util.ArrayList;

public class Escudo extends Arma{

	    private int impactosRestantes;

	    public Escudo()
	    {
	        impactosRestantes = 2;
	        coste=50;
	    }


	    public boolean funcional()
	    {
	        return (impactosRestantes > 0);
	    }

	    public void recibirImpacto()
	    {
	        impactosRestantes--;
	    }
	    
	    @Override
	    public boolean realizarFuncion(int pX,int pY,boolean pAQuien) {
			ArrayList<Casilla> casillas = Tablero.getTablero().colocarEscudo(pX, pY, true);
			Jugador.getJugador().enviarCasillas(casillas);
			return true;
	    }

}
