package franciscoRafaelMenesesGoncalves.associacaoBD;

import java.sql.SQLException;

public class MinhaAssociacao implements InterfaceAssociacao{
    private DAOAssociacao daoAssociacao = new DAOAssociacao();
    private DAOAssociado daoAssociado = new DAOAssociado();
    private DAOTaxas daoTaxas = new DAOTaxas();
    private DAOPagamentos daoPagamentos = new DAOPagamentos();
	
    public MinhaAssociacao() { // Método construtor
    	try {
			daoPagamentos.removerTodos();
			daoAssociado.removerTodos();
			daoTaxas.removerTodos();
			daoAssociacao.removerTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	@Override
	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		if(a.getNum() <= 0 || a.getNome() == null || a.getNome().trim().length() == 0)
			throw new ValorInvalido();
		
		try {
			if(daoAssociacao.associacaoExiste(a.getNum(), a.getNome())) throw new AssociacaoJaExistente();
			
			daoAssociacao.inserir(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void adicionar(int associacao, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		if(a.getNumero() <= 0 || a.getNome() == null || a.getNome().trim().length() == 0 || a.getTelefone() == null || a.getTelefone().trim().length() == 0 || a.getDataAssociacao() <= 0 || a.getNascimento() <= 0)
			throw new ValorInvalido();
		if(a instanceof AssociadoRemido) {
			if(((AssociadoRemido) a).getDataRemissao() < 0 || ((AssociadoRemido) a).getDataRemissao() < a.getDataAssociacao())
				throw new ValorInvalido();
		}
		
		
		try {
			if(!daoAssociacao.numAssociacaoExiste(associacao)) throw new AssociacaoNaoExistente();
			if(daoAssociado.associadoExiste(a.getNumero(), associacao)) throw new AssociadoJaExistente();
			
			daoAssociado.inserir(associacao, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		if(associacao <= 0 || t.getNome() == null || t.getNome().trim().length() == 0 || t.getVigencia() <= 0 || t.getValorAno() <= 0 || t.getParcelas() <= 0)
			throw new ValorInvalido();
				
		try {
			if(!daoAssociacao.numAssociacaoExiste(associacao)) throw new AssociacaoNaoExistente();
			if(daoTaxas.taxaExiste(t.getNome(), t.getVigencia(), associacao)) throw new TaxaJaExistente();
						
			daoTaxas.inserir(associacao, t); // Insere a taxa
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data, double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
		if(numAssociacao < 0 || taxa == null || taxa.trim().length() == 0 || vigencia <= 0 || numAssociado < 0 || data < 0 || data < vigencia || valor <= 0)
			throw new ValorInvalido();
		
		try {
			if(!daoAssociacao.numAssociacaoExiste(numAssociacao)) throw new AssociacaoNaoExistente();
			if(!daoAssociado.associadoExiste(numAssociado, numAssociacao)) throw new AssociadoNaoExistente();
			if(!daoTaxas.taxaExiste(taxa, vigencia, numAssociacao)) throw new TaxaNaoExistente();
			
			Taxa t = daoTaxas.recuperar(taxa, vigencia, numAssociacao);
			int id_taxa = daoTaxas.recuperaID(taxa, vigencia, numAssociacao);
			double totalAno = t.getValorAno();
			double mensalidade = totalAno/t.getParcelas();
						
			// O associado já foi remido para taxas administrativas?
			Associado associado = daoAssociado.recuperar(numAssociado);
			if(associado instanceof AssociadoRemido) {
			    AssociadoRemido associadoRemido = (AssociadoRemido) associado;
			    long dataRemissao = associadoRemido.getDataRemissao();
			    if(dataRemissao > data && t.isAdministrativa()) throw new AssociadoJaRemido();
			}
			
			// Verifica se o valor a ser pago excede o que falta para pagar
			double divida = totalAno - daoPagamentos.somarPagamento(numAssociado, id_taxa);
			if(valor > divida) throw new ValorInvalido();
			
			// Se a dívida ainda é maior do que a mensalidade, é porque não é a última parcela
			if(divida > mensalidade) {
				// Não deve ser pago um valor menor do que a parcela mensal
				if(valor < mensalidade) throw new ValorInvalido();
			}
			
			// Registra o pagamento
			daoPagamentos.inserir(taxa, vigencia, numAssociado, data, valor, numAssociacao);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		try {
			if(!daoAssociacao.numAssociacaoExiste(numAssociacao)) throw new AssociacaoNaoExistente();
			if(!daoAssociado.associadoExiste(numAssociado, numAssociacao)) throw new AssociadoNaoExistente();
			if(!daoTaxas.taxaExiste(nomeTaxa, vigencia, numAssociacao)) throw new TaxaNaoExistente();
			
			int idTaxa = daoTaxas.recuperaID(nomeTaxa, vigencia, numAssociacao);
			
			return daoPagamentos.somarPagamento(numAssociado, idTaxa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia)
			throws AssociacaoNaoExistente, TaxaNaoExistente, ValorInvalido {
		if(numAssociacao < 0 || vigencia <= 0) throw new ValorInvalido();
		
		double totalTaxas = 0;
		
		try {
			if(!daoAssociacao.numAssociacaoExiste(numAssociacao)) throw new AssociacaoNaoExistente();
			
			totalTaxas = daoTaxas.somaTaxasAno(numAssociacao, vigencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(totalTaxas != 0) return totalTaxas;
		else throw new TaxaNaoExistente();
	}
}
