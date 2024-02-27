package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

@SuppressWarnings("serial")
public class ProdutoVencido extends Exception{
	public ProdutoVencido() {
		super("Todos os lotes do produto estão vencidos. Não é possível realizar a venda.");
	}
	
	public ProdutoVencido(String mensagem){
		System.out.println(mensagem);
	}
}
