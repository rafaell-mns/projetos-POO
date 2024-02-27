package franciscoRafaelMenesesGoncalves.associacao;

@SuppressWarnings("serial")
public class ReuniaoJaExistente extends Exception{
	public ReuniaoJaExistente() {
		super("Esse reunião existe!");
	}
}
