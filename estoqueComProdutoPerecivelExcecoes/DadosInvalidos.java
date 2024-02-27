package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

@SuppressWarnings("serial")
public class DadosInvalidos extends Exception{

	public DadosInvalidos() {
		super("Algum dado é inválido.");
	}

	public DadosInvalidos(String mensagem) {
		super(mensagem);
	}

}
