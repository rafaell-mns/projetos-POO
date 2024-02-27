package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

import java.util.Date;

public class Lote {
	private int quantidade;
    private Date validade;
    
    public Lote(int quant, Date validade) {
        quantidade = quant;
        this.validade = validade;
    }

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getValidade() {
		return validade;
	}
}
