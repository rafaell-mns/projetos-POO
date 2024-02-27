package franciscoRafaelMenesesGoncalves.associacaoBD;

@SuppressWarnings("serial")
public class AssociadoJaExistente extends Exception{
	public AssociadoJaExistente() {
		super("Esse associado já existe no sistema");
	}
}
