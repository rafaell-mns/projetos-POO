package franciscoRafaelMenesesGoncalves.associacaoBD;

@SuppressWarnings("serial")
public class AssociacaoJaExistente extends Exception{
	public AssociacaoJaExistente() {
		super("Essa associação já existe.");
	}
}
