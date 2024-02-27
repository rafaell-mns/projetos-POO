package franciscoRafaelMenesesGoncalves.associacao;

import java.util.ArrayList;

public class Associado {
	private int numero;
	private String nome;
	private String telefone;
	private long dataAssociacao;
	private long nascimento;
	private ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();


	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
		this.dataAssociacao = dataAssociacao;
		this.nascimento = nascimento;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public long getDataAssociacao() {
		return dataAssociacao;
	}

	public long getNascimento() {
		return nascimento;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setDataAssociacao(long dataAssociacao) {
		this.dataAssociacao = dataAssociacao;
	}

	public void setNascimento(long nascimento) {
		this.nascimento = nascimento;
	}	
	
	public void registrarPagamento(Taxa t, long data, double valor) {
		Pagamento pagamento = new Pagamento(t, data, valor);
        pagamentos.add(pagamento);
	}
	
	public double somarPagamentos(Taxa t, long inicio, long fim) {
		double totalPagamentos = 0;

		for (Pagamento p : pagamentos) {
            if (p.getData() >= inicio && p.getData() <= fim && p.getTaxa().getNome().equals(t.getNome())) {
                totalPagamentos += p.getValor();
            }
        }
		
		return totalPagamentos;
	}
	
	public boolean ultimaParcela(Taxa t, double valorParcela) {
		double totalPago = 0;
		for (Pagamento p : pagamentos) {
			if(p.getTaxa().getNome().equals(t.getNome()))
				totalPago += p.getValor();
		}
		if(t.getValorAno() - totalPago <= valorParcela)
			return true;
		else 
			return false;
	}
}
