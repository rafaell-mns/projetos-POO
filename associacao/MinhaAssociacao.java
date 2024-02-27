package franciscoRafaelMenesesGoncalves.associacao;

import java.util.ArrayList;

public class MinhaAssociacao implements InterfaceAssociacao{
	private ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
	
	//Adicionar associação
	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		if(a.getNum() <= 0 || a.getNome() == null || a.getNome().trim().length() == 0)
			throw new ValorInvalido();
		
		try {
			pesquisarAssociacao(a.getNum());
			throw new AssociacaoJaExistente();
		} catch (AssociacaoNaoExistente e) {
			associacoes.add(a);		}
	}
	
	//Adicionar associado a uma associação
	public void adicionar(int associacao, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		if(a.getNumero() <= 0 || a.getNome() == null || a.getNome().trim().length() == 0 || a.getTelefone() == null || a.getTelefone().trim().length() == 0 || a.getDataAssociacao() <= 0 || a.getNascimento() <= 0)
			throw new ValorInvalido();
		
		Associacao assoc = pesquisarAssociacao(associacao);
		assoc.adicionarAssociados(a);
	}
		
	public Associacao pesquisarAssociacao(int num) throws AssociacaoNaoExistente {
		for (Associacao a : associacoes) {
			if(a.getNum() == num)
				return a;
		}
		throw new AssociacaoNaoExistente();
	}
	
	//Adicionar reunião a uma associação
	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido{
		if(r.getData() <= 0 || r.getAta() == null || r.getAta().trim().length() == 0)
			throw new ValorInvalido();
			
		Associacao assoc = pesquisarAssociacao(associacao);
		assoc.adicionarReuniao(r);
	}

	//Adicionar taxas à associação
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		if(t.getNome() == null || t.getNome().trim().length() == 0 || t.getVigencia() <= 0 || t.getValorAno() <= 0 || t.getParcelas() <= 0)
			throw new ValorInvalido();
		
		Associacao assoc = pesquisarAssociacao(associacao);
		assoc.adicionarTaxa(t);
	}

	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente, TaxaNaoExistente, ValorInvalido {
		if(vigencia <= 0)
			throw new ValorInvalido();
		
		Associacao assoc = pesquisarAssociacao(numAssociacao);
		if(!assoc.temTaxa(vigencia)) throw new TaxaNaoExistente();
		return assoc.totalTaxas(vigencia);	
	}

	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long data) throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel, ValorInvalido {
		if(codigoAssociado < 0 || numAssociacao < 0 || data < 0)
			throw new ValorInvalido();
		
		Associacao assoc = pesquisarAssociacao(numAssociacao);
		Associado a = assoc.pesquisarAssociado(codigoAssociado);
		Reuniao r = assoc.pesquisarReuniao(data);
		
		if(r.getData() < a.getDataAssociacao()) throw new FrequenciaIncompativel();
				
		r.registrarPresenca(a);
	}
	
	public double calcularFrequencia(int numAssociado, int numAssociacao, long inicio, long fim) throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, ValorInvalido {
		if(numAssociado < 0 || numAssociacao < 0 || inicio < 0 || fim < 0)
			throw new ValorInvalido();
		
		Associacao assoc = pesquisarAssociacao(numAssociacao);
		Associado a = assoc.pesquisarAssociado(numAssociado);
		if(!assoc.existeReuniao(inicio, fim)) throw new ReuniaoNaoExistente();
		
		return assoc.calculaFrequenciaReunioes(a, inicio, fim);
	}
	
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data, double valor) throws ValorInvalido, AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente, AssociadoJaRemido {
		if(numAssociacao < 0 || taxa == null || taxa.trim().length() == 0 || vigencia <= 0 || numAssociado < 0 || data < 0 || valor <= 0)
			throw new ValorInvalido();
	
		Associacao assoc = pesquisarAssociacao(numAssociacao);
		Taxa t = assoc.pesquisarTaxa(taxa);
		Associado a = assoc.pesquisarAssociado(numAssociado);
		
		if(valor > t.getValorAno()) throw new ValorInvalido();
		
		if (a instanceof AssociadoRemido) {
	        AssociadoRemido remido = (AssociadoRemido) a;
	        if (remido.getDataRemissao() <= data && t.isAdministrativa())  //se a data da taxa for depois que ele foi remido
	            throw new AssociadoJaRemido();
	    }
		
	    double valorParcela = t.getValorAno() / t.getParcelas();
	    	    
	    if(!a.ultimaParcela(t, valorParcela)) {
	    	if (valor < valorParcela) throw new ValorInvalido();
	    }
	    
	    a.registrarPagamento(t, data, valor);
	    
	}
	
	public double somarPagamentoDeAssociado (int numAssociacao, int numAssociado, String nomeTaxa, int vigencia, long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente{
		Associacao assoc = pesquisarAssociacao(numAssociacao);
        Associado a = assoc.pesquisarAssociado(numAssociado);
        Taxa t = assoc.pesquisarTaxa(nomeTaxa);

        if (t.getVigencia() != vigencia) throw new TaxaNaoExistente();
        
        return a.somarPagamentos(t, inicio, fim);
    }
	
}
