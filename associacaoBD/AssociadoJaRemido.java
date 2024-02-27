package franciscoRafaelMenesesGoncalves.associacaoBD;

@SuppressWarnings("serial")
public class AssociadoJaRemido extends Exception {
	public AssociadoJaRemido() {
		super("Esse associado já foi remido.");
	}
}
