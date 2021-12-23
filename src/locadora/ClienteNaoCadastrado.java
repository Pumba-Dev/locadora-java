package locadora;
@SuppressWarnings("serial")
public class ClienteNaoCadastrado extends Exception {

	public ClienteNaoCadastrado() {
		super("O cliente não encontra-se cadastrado");
	}
}