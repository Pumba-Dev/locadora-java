package locadora;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

public class TesteVeiculo {

	@Test
	public void testarInserirPesquisarVeiculos() throws ClassNotFoundException, SQLException, ClienteJaCadastrado, VeiculoJaCadastrado, VeiculoNaoCadastrado {
		DAOVeiculo dao = new DAOVeiculo();
		dao.removerTodos();
		
		Moto m1;
		
		Carro v1 = new Carro("VW", "Fusca", 1970, 5000, 50, "ABC-1234", 1);
		Moto  v2 = new Moto("Honda", "CG125", 2019, 6000, 90, "ABC-1235", 125);
		Caminhao v3 = new Caminhao("Volvo", "G6000", 1988, 50000, 250, "ABC-1236", 3000);
		Onibus v4 = new Onibus("Mercedez", "M150", 1990, 250000, 800, "ABC-1237", 42);
	
		dao.inserir(v1);
		dao.inserir(v2);
		dao.inserir(v3);
		dao.inserir(v4);

		try {
			dao.inserir(v3);
			fail("Era pra ter gerado exceção de veiculo já cadastrado.");
		} catch (VeiculoJaCadastrado e) {
			// Exceção esperada!
		}
		
		Veiculo r1 = dao.pesquisarPor("ABC-1234");
		assertTrue(r1 instanceof Carro);
        assertEquals(1, ((Carro) r1).getTipo());
		
		Veiculo r2 = dao.pesquisarPor("ABC-1235");
		assertTrue(r2 instanceof Moto);
        assertEquals(125, ((Moto) r2).getCilindrada());

		Veiculo r3 = dao.pesquisarPor("ABC-1236");
		assertTrue(r3 instanceof Caminhao);
        assertEquals(3000, ((Caminhao) r3).getCarga());

		Veiculo r4 = dao.pesquisarPor("ABC-1237");
		assertTrue(r4 instanceof Onibus);
        assertEquals(42, ((Onibus) r4).getPassageiros());
	}
}
