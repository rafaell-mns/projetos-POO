package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public class Produto {
	private int codigo;
	private String descricao;
	private double precoCompra;
	private double precoVenda;
	private double lucro;
	private int quantidade;
	private int estoqueMinimo;
	ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	ArrayList<Movimentacao> fluxo = new ArrayList<Movimentacao>();
	
	public ArrayList<Movimentacao> getMovimentacoes() {
	    return fluxo;
	}
	
	public Produto(int cod, String desc, int min, double lucro) {
		codigo = cod;
		descricao = desc;
		estoqueMinimo = min;
		this.lucro = lucro;
	}
	
	void adicionarMovimentacao(String tipo, double valor, int quantid) {
		Movimentacao mov = new Movimentacao(tipo, new Date(), valor, quantid);
		fluxo.add(mov);
	}
	
	void compra(int quant, double val) { //val = preço q tá comprando
		double novoPrecoCompra = ((quantidade * precoCompra) + (quant * val)) / (quantidade + quant);
		precoCompra = novoPrecoCompra;
		precoVenda = precoCompra * (1 + lucro);
		quantidade += quant;
		
		adicionarMovimentacao("Compra", precoCompra, quant);
	}
	
	double venda(int quant) {
		double valorVenda = quant * precoVenda;
		quantidade -= quant;
		
		adicionarMovimentacao("Venda", precoVenda, quant);
		
		return valorVenda;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public double getLucro() {
		return lucro;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
}
