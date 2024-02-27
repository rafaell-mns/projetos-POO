package franciscoRafaelMenesesGoncalves.associacao;

public interface InterfaceAssociacao {
	public double calcularFrequencia(int numAssociado, int numAssociacao, long inicio,
	long fim) throws AssociadoNaoExistente, ReuniaoNaoExistente,
	AssociacaoNaoExistente, ValorInvalido;

	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long
	dataReuniao)throws AssociadoNaoExistente, ReuniaoNaoExistente,
	AssociacaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel,
	ValorInvalido;

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

	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente,
	ReuniaoJaExistente, ValorInvalido;
	
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente,
	TaxaJaExistente, ValorInvalido;
}
