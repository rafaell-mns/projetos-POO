package franciscoRafaelMenesesGoncalves.associacao;

import java.util.ArrayList;

public class Associacao {
	private int num;
	private String nome;
	private ArrayList<Associado> associados = new ArrayList<Associado>();
	private ArrayList<Reuniao> reunioes = new ArrayList<Reuniao>();
	private ArrayList<Taxa> taxas = new ArrayList<Taxa>();
	
	public Associacao(int num, String nome) {
		this.num = num;
		this.nome = nome;
	}

	public int getNum() {
		return num;
	}

	public String getNome() {
		return nome;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void adicionarAssociados(Associado a) throws AssociadoJaExistente {
		try {
			pesquisarAssociado(a.getNumero());
			throw new AssociadoJaExistente();
		} catch (AssociadoNaoExistente e) {
			associados.add(a);
		}
	}
	
	public Associado pesquisarAssociado(int num) throws AssociadoNaoExistente {
		for (Associado a : associados) {
			if(a.getNumero() == num) 
				return a;
		}
		throw new AssociadoNaoExistente();
	}
	
	public void adicionarReuniao(Reuniao r) throws ReuniaoJaExistente {
		try {
			pesquisarReuniao(r.getData());
			throw new ReuniaoJaExistente();
		} catch (ReuniaoNaoExistente e) {
			reunioes.add(r);
		}
	}
	
	public Reuniao pesquisarReuniao(long data) throws ReuniaoNaoExistente {
		for (Reuniao r : reunioes) {
			if(r.getData() == data)
				return r;
		}
		throw new ReuniaoNaoExistente();
	}
	
	public void adicionarTaxa(Taxa t) throws TaxaJaExistente {
		for (Taxa taxaExistente : taxas) {
			if(taxaExistente.getNome().equals(t.getNome()) && taxaExistente.getVigencia() == t.getVigencia())
				throw new TaxaJaExistente();
		}
		taxas.add(t);
	}
	
	public Taxa pesquisarTaxa(String nome) throws TaxaNaoExistente {
		for (Taxa t : taxas) {
			if(t.getNome().equals(nome))
				return t;
		}
		throw new TaxaNaoExistente();
	}

	public boolean temTaxa(int vigencia) {
		for (Taxa t : taxas) {
			if(t.getVigencia() == vigencia)
				return true;
		}
		return false;
	}
	
	public double totalTaxas(int vigencia) {
		double soma = 0;
		for (Taxa t : taxas) {
			if(t.getVigencia() == vigencia)
				soma += t.getValorAno();
		}
		return soma;
	}

	public boolean existeReuniao(long inicio, long fim) {
		for (Reuniao r : reunioes) {
			if(inicio <= r.getData() && fim >= r.getData())
				return true;
		}
		return false;
	}
	
	public double calculaFrequenciaReunioes(Associado a, long inicio, long fim) {
		int totalReunioes = 0;
		int presenca = 0;
		
		for (Reuniao r : reunioes) {
			if(inicio <= r.getData() && fim >= r.getData()) {
				++totalReunioes;
				if(r.verificaPresenca(a.getNumero()))
					++presenca;
			}	
		}
		
		return (double) presenca/totalReunioes;
	}
	
	
}
