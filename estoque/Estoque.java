package franciscoRafaelMenesesGoncalves.estoque;

import java.util.ArrayList;
import java.util.Date;

public class Estoque {
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private Date data;
	
	public Estoque() {
		//Construtor padrão
	}
	
	public Date getData() {
		return data;
	}
	
	Produto pesquisar(int cod) {
		for (Produto p : produtos) {
	        if (p.getCodigo() == cod) {
	            return p;
	        }
	    }
		return null;
	}
	
	public String movimentacao(int cod, Date inicio, Date fim) {
	    String contabilidade = "";
	    Produto produto = pesquisar(cod);
	    
	    if(produto != null) {
	        ArrayList<Movimentacao> movimentacoes = produto.getMovimentacoes();
	        for (Movimentacao mov : movimentacoes) {
	        	Date dataMovimentacao = mov.getData();
	            if (dataMovimentacao.after(inicio) && dataMovimentacao.before(fim)) {
	            	contabilidade += mov.toString();
	            }
	        }
	    }
	    
	    return contabilidade;
	}
	
	public void comprar(int cod, int quant, double preco) {
		if(quant > 0 && preco > 0) {
			Produto produto = pesquisar(cod);
		    if (produto != null) {
		        produto.compra(quant, preco);
		    }
	    }	
	}
	
	public double precoDeVenda(int cod){
		Produto produto = pesquisar(cod);
		if (produto != null) {
	        return produto.getPrecoVenda();
	    } else {
	    	return -1;
	    }
	}
	
	public double precoDeCompra(int cod) {
		Produto produto = pesquisar(cod);
		if (produto != null) {
	        return produto.getPrecoCompra();
	    } else {
	    	return -1;
	    }	
	}
	
	public void incluir(Produto p) {
		if (pesquisar(p.getCodigo()) != null) {
	        System.out.println("Impossível adicionar. Produto repetido.");
	    } else {
	        produtos.add(p);
	    }
	}
	
	public double vender(int cod, int quant) {
		if(quant > 0) {
			Produto produto = pesquisar(cod);
			if(produto != null && produto.getQuantidade() >= quant) {
				double valorVenda = produto.venda(quant);
	            return valorVenda;
			}
		}
		return -1;
		}
	
	public void adicionarFornecedor(int cod, Fornecedor f) {
		Produto produto = pesquisar(cod);
		if(produto != null) {
			produto.fornecedores.add(f);
		}
	}
	
	public int quantidade(int cod) {
		Produto produto = pesquisar(cod);
		if(produto != null) {
			return produto.getQuantidade();
		}
		return -1;
	}
	
	public ArrayList<Fornecedor> fornecedores(int cod) {
		ArrayList<Fornecedor> listaFornecedores = new ArrayList<>();
		Produto produto = pesquisar(cod);
		
		if(produto != null) {
			listaFornecedores.addAll(produto.fornecedores);
		}
		
		return listaFornecedores;
	}
	
	public ArrayList<Produto> estoqueAbaixoDoMinimo(){
		ArrayList<Produto> listarProdutosAbaixoDoMinimo = new ArrayList<>();
		
		for (Produto p : produtos) {
	        if (p.getQuantidade() < p.getEstoqueMinimo()) {
	        	listarProdutosAbaixoDoMinimo.add(p);
	        }
	    }

	    return listarProdutosAbaixoDoMinimo;
	}
	
}
