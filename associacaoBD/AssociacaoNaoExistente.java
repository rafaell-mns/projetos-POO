package franciscoRafaelMenesesGoncalves.associacaoBD;

@SuppressWarnings("serial")
public class AssociacaoNaoExistente extends Exception{
	public AssociacaoNaoExistente() {
		super("Essa associação não existe.");
	}
}
