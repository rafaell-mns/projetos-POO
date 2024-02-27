package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

@SuppressWarnings("serial")
public class ProdutoNaoPerecivel extends Exception {
	
	public ProdutoNaoPerecivel() {
		super("nao perecivel");
	}
	
	public ProdutoNaoPerecivel(String mensagem) {
		super(mensagem);
	}
}
