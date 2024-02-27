package franciscoRafaelMenesesGoncalves.associacao;

@SuppressWarnings("serial")
public class FrequenciaJaRegistrada extends Exception {
	public FrequenciaJaRegistrada() {
		super("Esse cara já foi registrado na frequência.");
	}
}
