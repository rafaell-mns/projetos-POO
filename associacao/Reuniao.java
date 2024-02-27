package franciscoRafaelMenesesGoncalves.associacao;

import java.util.ArrayList;

public class Reuniao {
	private long data;
	private String ata;
	ArrayList<Associado> presentes = new ArrayList<Associado>();
	
	public Reuniao(long data, String ata) {
		this.data = data;
		this.ata = ata;
	}

	public long getData() {
		return data;
	}

	public String getAta() {
		return ata;
	}

	public void setData(long data) {
		this.data = data;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}
	
	public Associado pesquisarPresenca(int cod) throws FrequenciaNaoRegistrada {
		for (Associado frequencia : presentes) {
			if(frequencia.getNumero() == cod)
				return frequencia;
		}
		throw new FrequenciaNaoRegistrada();
	}
	
	public void registrarPresenca(Associado a) throws FrequenciaJaRegistrada {
		try {
			pesquisarPresenca(a.getNumero());
			throw new FrequenciaJaRegistrada();
		} catch (FrequenciaNaoRegistrada e) {
			presentes.add(a);
		}	
	}
	
	public boolean verificaPresenca(int cod) {
		for (Associado associado : presentes) {
			if(associado.getNumero() == cod)
				return true;
		}
		return false;
	}
	
}
