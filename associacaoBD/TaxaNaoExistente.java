package franciscoRafaelMenesesGoncalves.associacaoBD;

@SuppressWarnings("serial")
public class TaxaNaoExistente extends Exception {
	public TaxaNaoExistente() {
		super("Essa taxa não existe.");
	}
}
