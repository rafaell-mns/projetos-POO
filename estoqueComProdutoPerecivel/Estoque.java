package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoque{
	ArrayList<Produto> produtos = new ArrayList<Produto>();
	private Date data;
	int valorTotalVenda;
	
	public boolean incluir(Produto p) {
		if(p != null && p.getCodigo() > 0 && p.getDescricao() != null && p.getDescricao() != "" && p.getEstoqueMinimo() > 0 && p.getLucro() > 0) {
			if(pesquisar(p.getCodigo()) == null) {
				produtos.add(p);
				return true;
			}
		}
		return false;
	}
	
	public boolean comprar(int cod, int quant, double preco, Date val) {
		if(quant > 0 && preco > 0 && cod > 0) {
			Produto produto = pesquisar(cod);
			
			if (produto != null) {
				if((!(produto instanceof ProdutoPerecivel) && val != null) || (produto instanceof ProdutoPerecivel && val == null) || (produto instanceof ProdutoPerecivel && val.before(new Date()))){
					return false;
				}
				
				if(produto instanceof ProdutoPerecivel) {//produto perecivel
					ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
	                Lote lote = new Lote(quant, val);
		            produtoPerecivel.adicionarLote(lote);
			    }
				
				produto.compra(quant, preco);
			    return true;
				}
			}
		return false;
	}
	
	public double vender(int cod, int quant) {
		if (cod > 0 && quant > 0) {
			Produto produto = pesquisar(cod);
			
			if (produto != null) {
				if (produto instanceof ProdutoPerecivel) {
					ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;

			        int disponivelTotal = 0;
			        Date dataAtual = new Date();
			        int n = quant;
			        
			        //pegar quantidade total dispnivel
			        for (Lote lote : produtoPerecivel.lotes) {
			        	if (lote.getValidade().after(dataAtual)) {
			                disponivelTotal += lote.getQuantidade();
			            }
					}
			        
			        if (disponivelTotal >= quant) {
			        	for (Lote l : ((ProdutoPerecivel) produto).getLotes()) {
							if (!(l.getValidade().after(dataAtual)))
								continue;
							n = n - l.getQuantidade();
							if (n > 0) {
								l.setQuantidade(0);
							} else {
								l.setQuantidade(Math.abs(n));
								n = 0;
								return ((ProdutoPerecivel) produto).venda(quant);
							}
						}

			        }
				}  else if (produto.getQuantidade() >= quant) {
					return produto.venda(quant);
				}
			}
		}
		return -1; //erro na venda
	}
	
	public Date getData() {
		return data;
	}
	
	public Produto pesquisar(int cod) {
		for (Produto produto : produtos) {
	        if (produto.getCodigo() == cod) {
	            return produto;
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
	
	public void adicionarFornecedor(int cod, Fornecedor f) {
		Produto produto = pesquisar(cod);
		if(produto != null) {
			produto.fornecedores.add(f);
		}
	}
	
	public int quantidade(int cod) {
		Produto produto = pesquisar(cod);
		if(produto != null) {
			if(!(produto instanceof ProdutoPerecivel))			
				return produto.getQuantidade();
			else {
				ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
				
				int retornarQntd = 0;
				
		        for (Lote lote : produtoPerecivel.lotes) {
		        	if (!(lote.getValidade().before(new Date()))) {
		        		retornarQntd += lote.getQuantidade();
		        	}
				}
		        
		        return retornarQntd;
			}
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
	
	public ArrayList<Produto> estoqueAbaixoDoMinimo() {
	    ArrayList<Produto> listarProdutosAbaixoDoMinimo = new ArrayList<>();

	    for (Produto produto : produtos) {
	        if (produto instanceof ProdutoPerecivel) {
	            ProdutoPerecivel pp = (ProdutoPerecivel) produto;

	            for (Lote lote : pp.lotes) {
	                if (lote.getQuantidade() < pp.getEstoqueMinimo()) {
	                	listarProdutosAbaixoDoMinimo.add(produto);
	                    break;
	                }
	            }
	        } else {
	            if (produto.getQuantidade() < produto.getEstoqueMinimo()) {
	                    listarProdutosAbaixoDoMinimo.add(produto);
	            }
	        }
	    }

	    return listarProdutosAbaixoDoMinimo;
	}

	public int quantidadeVencidos(int cod){
		Produto produto = pesquisar(cod);
        if (produto != null) {
            if (produto instanceof ProdutoPerecivel) {
                ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
                int quantVencidos = 0;
               
                for (Lote lote : produtoPerecivel.lotes) {
                    if (!(lote.getValidade().after(new Date()))) {
                        ++quantVencidos;
                    }
                }
                
                return quantVencidos;
            }
        }
        return 0;
	}

	public ArrayList<Produto> estoqueVencido() {
	    ArrayList<Produto> produtosVencidos = new ArrayList<Produto>();
	    Date dataAtual = new Date();
	    
	    for (Produto produto : produtos) {
	        if (produto instanceof ProdutoPerecivel) {
	            ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
	            
	            for (Lote lote : produtoPerecivel.lotes) {
                    if (!(lote.getValidade().after(dataAtual))) {
                        produtosVencidos.add(produtoPerecivel);
                        break;
                    }
                }
	        }
	    }
	    return produtosVencidos;
	}

	public Estoque() {}
}