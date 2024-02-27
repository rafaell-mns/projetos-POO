package franciscoRafaelMenesesGoncalves.associacao;

@SuppressWarnings("serial")
public class ReuniaoNaoExistente extends Exception{
	public ReuniaoNaoExistente() {
		super("Não existem reuniões nesse dia");
	}
}
