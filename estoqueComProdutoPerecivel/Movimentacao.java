package franciscoRafaelMenesesGoncalves.estoqueComProdutoPerecivel;

import java.util.Date;

public class Movimentacao {
	private Date data;
    private String tipoMovimento;
    private double valor;
    private int quantidade;

    public Movimentacao(String tipoMov, Date data, double valor, int quant){
        this.data = data;
        tipoMovimento = tipoMov;
        this.valor = valor;
        quantidade = quant;
    }

    @SuppressWarnings("deprecation")
	public String toString() { //23/10/2023. Compra. Valor: 5.0. Quant: 20.\n
		String dtStr = data.getDate() + "/" + (data.getMonth()+1) + "/" + (data.getYear() + 1900);
		String opStr = ". " + tipoMovimento + ". Valor: " + valor + ". Quant: " + quantidade + ".\n";
		return dtStr + opStr;
	}
    
    public String getTipoMovimento() {
        return tipoMovimento;
    }
    
    public Date getData() {
		return data;
	}

}
