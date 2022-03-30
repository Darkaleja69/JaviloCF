package packModelo;

public class Juego {
	private static Juego miJuego;
	
	private Juego() {}
	public static Juego getJuego() {
		if(miJuego == null) {
			miJuego=new Juego();
		}
		return miJuego;
	}
	
	public void jugarPartida() {
		inicializarPartida();
		boolean fin=false;
		while(!fin) {
			
		}
	}
	
	private void inicializarPartida() {
		CPU.getMiCPU().colocarBarcos();
		Jugador.getJugador().colocarBarcos();
	}
	
	public void terminarPartida() {
		
	}
}
