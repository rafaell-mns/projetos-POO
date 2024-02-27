package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

@SuppressWarnings("serial")
public class QuantidadeInsuficiente extends Exception{

	public QuantidadeInsuficiente() {
		super("Quantidade insuficiente.");
	}
}
