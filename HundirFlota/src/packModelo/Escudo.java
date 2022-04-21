package packModelo;

public class Escudo {

	    private int impactosRestantes;

	    public Escudo()
	    {
	        impactosRestantes = 2;
	    }


	    public boolean funcional()
	    {
	        return (impactosRestantes > 0);
	    }

	    public void recibirImpacto()
	    {
	        impactosRestantes--;
	    }

}
