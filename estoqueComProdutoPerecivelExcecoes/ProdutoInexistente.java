package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

@SuppressWarnings("serial")
public class ProdutoInexistente extends Exception{
	
	public ProdutoInexistente() {
		super("Esse produto não existe no estoque.");
	}
	
	public ProdutoInexistente(String mensagem) {
		super(mensagem);
	}
}
