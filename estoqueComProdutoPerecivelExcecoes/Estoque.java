package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivelExcecoes;

import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoqueComExcecoes{
	ArrayList<Produto> produtos = new ArrayList<Produto>();
	int valorTotalVenda;
	
	public void incluir(Produto p) throws DadosInvalidos, ProdutoJaCadastrado{
		if(p == null || p.getCodigo() <= 0 || p.getDescricao() == null || p.getEstoqueMinimo() <= 0 || p.getLucro() <= 0 || p.getDescricao().trim().length() == 0)
			throw new DadosInvalidos();
		
		try {
			pesquisar(p.getCodigo());
			throw new ProdutoJaCadastrado(); //se pesquisar não der exceção, é pq existe. nesse caso, lança exceção
		} catch (ProdutoInexistente e) {
			produtos.add(p); //adiciona caso não exista
		}
	}
	
	public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel  {
		if (quant <= 0 || preco <= 0 || cod <= 0)
			throw new DadosInvalidos("Quantidade, preço ou código é inválido.");
		
		
			Produto produto = pesquisar(cod);
			
			if (val == null) { 
				if (!(produto instanceof ProdutoPerecivel)) //se nao for perecivel, tudo certo
	                produto.compra(quant, preco);
				else
					throw new DadosInvalidos("Produto perecível necessita de uma data de validade");
			} else {
				if (produto instanceof ProdutoPerecivel) {
					ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
					
					if (val.before(new Date()))
	                    throw new DadosInvalidos("Data de validade não pode ser anterior à data atual."); 
	                
					Lote lote = new Lote(quant, val);
		            produtoPerecivel.adicionarLote(lote);
					produto.compra(quant, preco);
				} else
					throw new ProdutoNaoPerecivel("Produto não perecível não requer data de validade."); 
			}
		
		
	}
	
	public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido, DadosInvalidos {
					
			Produto produto = pesquisar(cod);

			if (cod <= 0 || quant <=0 || quant > produto.getQuantidade())
				throw new DadosInvalidos("Código ou quantidade digitados são inválidos.");
			
			if (produto instanceof ProdutoPerecivel) {
				ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;

				if (todosLotesVencidos(cod)) {
	                throw new ProdutoVencido();
	            }

		        int disponivelTotal = 0;
		        Date dataAtual = new Date();
		        int n = quant;
		        
		        //pegar quantidade total dispnivel
		        for (Lote lote : produtoPerecivel.lotes) {
		        	if (lote.getValidade().after(dataAtual)) {
		                disponivelTotal += lote.getQuantidade();
		            }
				}
		        
		        if(disponivelTotal < quant)
		        	throw new ProdutoVencido();
		        	
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
		       
			} else if (produto.getQuantidade() >= quant) { //PRODUTO NAO PERECIVEL
				return produto.venda(quant);
			}
			
		
		return 0;
		}	
	
	public boolean todosLotesVencidos(int cod) throws ProdutoInexistente {
		int totalLotes = 0;
		int lotesVencidos = 0;
		
		try {
			Produto produto = pesquisar(cod);
		
			if (produto instanceof ProdutoPerecivel) {
                ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
                               
                for (Lote lote : produtoPerecivel.lotes) {
                	totalLotes += 1;
                	
                    if (!lote.getValidade().after(new Date())) 
                    	lotesVencidos += 1;
                    
                }
            }
		} catch(Exception e) {
			throw new ProdutoInexistente();
		}
		
		if (totalLotes == lotesVencidos)
			return true;
		else
			return false;
	}
	
	public Produto pesquisar(int cod) throws ProdutoInexistente {
		for (Produto p : produtos) {
	        if (p.getCodigo() == cod) {
	            return p;
	        }
	    }
		throw new ProdutoInexistente();
		}
	
	public String movimentacao(int cod, Date inicio, Date fim) throws ProdutoInexistente {
	    String contabilidade = "";
	    
	    try {
	    	Produto produto = pesquisar(cod);
	    	
	    	ArrayList<Movimentacao> movimentacoes = produto.getMovimentacoes();
	        for (Movimentacao mov : movimentacoes) {
	        	Date dataMovimentacao = mov.getData();
	            if (dataMovimentacao.after(inicio) && dataMovimentacao.before(fim)) {
	            	contabilidade += mov.toString();
	            }
	        }
	        
	        return contabilidade;
	    }
	    catch(ProdutoInexistente e) {
	    	throw new ProdutoInexistente();
	    }
	}
	
	public double precoDeVenda(int cod) throws ProdutoInexistente{
		try {
			Produto produto = pesquisar(cod);
			return produto.getPrecoVenda();
		} catch(ProdutoInexistente e) {
			throw new ProdutoInexistente();
		}
	}
	
	public double precoDeCompra(int cod) throws ProdutoInexistente {
		try {
			Produto produto = pesquisar(cod);
			return produto.getPrecoCompra();
		}catch(ProdutoInexistente e) {
			throw new ProdutoInexistente();
		}
	}
	
	public void adicionarFornecedor(int cod, Fornecedor f) throws ProdutoInexistente {
		try {
			Produto produto = pesquisar(cod);
			produto.fornecedores.add(f);
		} catch(ProdutoInexistente e) {
			throw new ProdutoInexistente();
		}
	
	}
	
	public int quantidade(int cod) throws ProdutoInexistente{
		try {
			Produto produto = pesquisar(cod);
			return produto.getQuantidade();
			/*
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
			*/
		} catch (ProdutoInexistente e) {
			throw new ProdutoInexistente();
		}
	}
	
	public ArrayList<Fornecedor> fornecedores(int cod) throws ProdutoInexistente {
		ArrayList<Fornecedor> listaFornecedores = new ArrayList<>();
				
		try {
			Produto produto = pesquisar(cod);
			listaFornecedores.addAll(produto.fornecedores);
			return listaFornecedores;
		}catch(ProdutoInexistente e) {
			throw new ProdutoInexistente();
		}
		
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

	public int quantidadeVencidos(int cod) throws ProdutoInexistente{
		int quantVencidos = 0;

		try {
			Produto produto = pesquisar(cod);
			
			if (produto instanceof ProdutoPerecivel) {
                ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
                               
                for (Lote lote : produtoPerecivel.lotes) {
                    if (!(lote.getValidade().after(new Date())) && produto.getCodigo() == cod) {
                        quantVencidos += produtoPerecivel.getQuantidade();
                       }
                }
                
                return quantVencidos;
            }
		} catch(Exception e) {
			throw new ProdutoInexistente();
		}
		
		return quantVencidos;
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