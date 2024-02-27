package franciscoRafaelMenesesGoncalves.associacaoBD;


public class Associacao {
	private int num;
	private String nome;
	
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
}
