package franciscoRafaelMenesesGoncalves.associacaoBD;

@SuppressWarnings("serial")
public class AssociadoNaoExistente extends Exception{
	public AssociadoNaoExistente() {
		super("Não existe um associado com esse número.");
	}
}
