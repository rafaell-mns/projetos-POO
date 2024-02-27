package franciscoRafaelMenesesGoncalves.associacao;

public class Pagamento {
	private long data;
    private double valor;
    private Taxa t;
    

    public Pagamento(Taxa t, long data, double valor) {
        this.data = data;
        this.valor = valor;
        this.t = t;
    }

    public long getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public Taxa getTaxa() {
        return t;
    }

    public void setTaxa(Taxa taxa) {
        this.t = taxa;
    }

}
