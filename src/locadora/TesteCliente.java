package locadora;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

public class TesteCliente {

	@Test
	public void testInserirCliente() throws ClassNotFoundException, SQLException, ClienteJaCadastrado {
		DAOCliente dao = new DAOCliente();
		dao.removerTodos();
		
		Cliente c1 = new Cliente(1, "Pedro");
		Cliente c2 = new Cliente(2, "João");
		Cliente c3 = new Cliente(3, "Raimundo");
		Cliente c4 = new Cliente(4, "Kelson");
		Cliente c5 = new Cliente(5, "Rodrigo");
		
		dao.inserir(c1);
		dao.inserir(c2);
		dao.inserir(c3);
		dao.inserir(c4);
		dao.inserir(c5);

		try {
			dao.inserir(c3);
			fail("Era pra ter gerado exceção de cliente já cadastrado.");
		} catch (ClienteJaCadastrado e) {
			// Exceção esperada!
		}
	}
	
	@Test
	public void testPesquisarPorNome() throws ClassNotFoundException, SQLException, ClienteJaCadastrado, ClienteNaoCadastrado {
		DAOCliente dao = new DAOCliente();
		dao.removerTodos();
		
		Cliente c1 = new Cliente(1, "Pedro");
		Cliente c2 = new Cliente(2, "João");
		Cliente c3 = new Cliente(3, "Raimundo");
		Cliente c4 = new Cliente(4, "Kelson");
		Cliente c5 = new Cliente(5, "Rodrigo");
		
		dao.inserir(c1);
		dao.inserir(c2);
		dao.inserir(c3);
		dao.inserir(c4);
		dao.inserir(c5);
		
		ArrayList<Cliente> cli = dao.pesquisarPor("dr");
		assertEquals(2, cli.size());

	}
}
