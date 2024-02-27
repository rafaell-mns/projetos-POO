package franciscoRafaelMenesesGoncalves.associacao;

@SuppressWarnings("serial")
public class TaxaJaExistente extends Exception {
	public TaxaJaExistente() {
		super("Essa taxa já existe.");
	}
}
