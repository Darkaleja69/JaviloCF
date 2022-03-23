package packModelo;

public class FactoryBarcos {
	private static FactoryBarcos miFactoryBarcos;
	
	private FactoryBarcos() {
	}
	
	public static FactoryBarcos getMiFactoryBarcos() {
		if (miFactoryBarcos==null) {
			miFactoryBarcos=new FactoryBarcos();
		}
		return miFactoryBarcos;
	}
	
}
