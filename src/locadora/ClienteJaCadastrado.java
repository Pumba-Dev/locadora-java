package locadora;

@SuppressWarnings("serial")
public class ClienteJaCadastrado extends Exception {

	public ClienteJaCadastrado() {
		super("O cliente já encontra-se cadastrado");
	}
}