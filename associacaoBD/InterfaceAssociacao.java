package franciscoRafaelMenesesGoncalves.associacaoBD;

public interface InterfaceAssociacao {
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int
	numAssociado, long data, double valor) throws AssociacaoNaoExistente,
	AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido;

	public double somarPagamentoDeAssociado (int numAssociacao, int numAssociado,
	String nomeTaxa, int vigencia, long inicio, long fim) throws
	AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente;

	public double calcularTotalDeTaxas (int numAssociacao, int vigencia) throws
	AssociacaoNaoExistente, TaxaNaoExistente, ValorInvalido;

	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido;

	public void adicionar(int associacao, Associado a) throws AssociacaoNaoExistente,
	AssociadoJaExistente, ValorInvalido;
	
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente,
	TaxaJaExistente, ValorInvalido;
}
