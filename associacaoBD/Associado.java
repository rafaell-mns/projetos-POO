package franciscoRafaelMenesesGoncalves.associacaoBD;


public class Associado {
	private int numero;
	private String nome;
	private String telefone;
	private long dataAssociacao;
	private long nascimento;


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
}
