package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

@SuppressWarnings("serial")
public class ProdutoJaCadastrado extends Exception{
	
	public ProdutoJaCadastrado() {
		super("Produto já cadastrado.");
	}
	
	public ProdutoJaCadastrado(String mensagem) {
		super(mensagem);
	}
}
