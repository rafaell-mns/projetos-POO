package franciscoRafaelMenesesGoncalves.associacaoBD;

public class Taxa {
	private String nome;
	private int vigencia;
	private double valorAno;
	private int parcelas;
	private boolean administrativa;
	
	public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) {
		this.nome = nome;
		this.vigencia = vigencia;
		this.valorAno = valorAno;
		this.parcelas = parcelas;
		this.administrativa = administrativa;
	}

	public String getNome() {
		return nome;
	}

	public int getVigencia() {
		return vigencia;
	}

	public double getValorAno() {
		return valorAno;
	}

	public int getParcelas() {
		return parcelas;
	}

	public boolean isAdministrativa() {
		return administrativa;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public void setValorAno(double valorAno) {
		this.valorAno = valorAno;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public void setAdministrativa(boolean administrativa) {
		this.administrativa = administrativa;
	}
	
}
