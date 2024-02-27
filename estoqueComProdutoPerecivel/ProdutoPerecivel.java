package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivel;

import java.util.ArrayList;

public class ProdutoPerecivel extends Produto{
	protected ArrayList<Lote> lotes = new ArrayList<Lote>();
		
	public ProdutoPerecivel(int cod, String desc, int min, double lucro) {
		super(cod, desc, min, lucro);
		
	}
	
		
	public ArrayList<Lote> getLotes() {
		return lotes;
	}

	public void adicionarLote(Lote l) {
		lotes.add(l);
		//System.out.println("Adicionado!");
	}
	
	
}
