package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestesBard {

	@Test
	public void testIncluirValido() {
        Estoque estoque = new Estoque();
        Produto prod1 = new Produto(1, "Macarrão", 5, 1);
        
        try {
			estoque.incluir(prod1);
            assertTrue(estoque.produtos.contains(prod1));
		} catch (DadosInvalidos | ProdutoJaCadastrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	@Test
	public void incluirInvalido() {
		Estoque estoque = new Estoque();
        Produto prod1 = new Produto(2, "Primeiro item", 3, 6);
        Produto prod2 = new Produto(2, "Segundo item", 3, 6);
        
        try {
            estoque.incluir(prod1); 
        } catch (DadosInvalidos | ProdutoJaCadastrado e) {
        	
        }
        
        try {
			estoque.incluir(prod2);
			fail("Nao devia ter adicionado");
		} catch (DadosInvalidos | ProdutoJaCadastrado e) {
			//já cadastrado
		}
        
        
		
	}
}
