package franciscoRafaelMenesesGoncalves.associacao;

@SuppressWarnings("serial")
public class TaxaNaoExistente extends Exception {
	public TaxaNaoExistente() {
		super("Essa taxa não existe.");
	}
}
