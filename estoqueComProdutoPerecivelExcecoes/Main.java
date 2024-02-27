package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

import java.util.Date;

public class Main {

	public static void main(String[] args) throws DadosInvalidos, ProdutoJaCadastrado, ProdutoInexistente, ProdutoNaoPerecivel, ProdutoVencido {
		// TODO Auto-generated method stub
		
			Estoque estoque = new Estoque();
			Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2);
			//Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1);
			Date data1 = new Date();
			data1.setTime(data1.getTime() + 120000);
			Date data2 = new Date();
			data2.setTime(data2.getTime() + 220000);

			estoque.incluir(prod1);
			//estoque.incluir(prod2);
			estoque.comprar(14, 4, 8, data2);
			estoque.comprar(14, 10, 5, data1);
			estoque.quantidade(14);
			//estoque.comprar(15, 11, 4.23, data1);
			//estoque.comprar(15, 5, 2.5, data2);
			//estoque.quantidade(15);
			System.out.println("Total antes da venda: " + ((ProdutoPerecivel) prod1).getQuantidade()); //14
			
			
			for (Lote l: ((ProdutoPerecivel) prod1).getLotes() ) {
				System.out.println("qtd lote: " + l.getQuantidade()); //4 e 10
			}
			
			estoque.vender(14, 5); //5
			System.out.println("vendi 5");

			for (Lote l: ((ProdutoPerecivel) prod1).getLotes() ) {
				System.out.println("qtd lote: " + l.getQuantidade());	//
			}
			System.out.println("Total após venda: " + ((ProdutoPerecivel) prod1).getQuantidade()); //14 mas devia ser 9 	
	}

}