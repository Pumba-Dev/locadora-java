package locadora;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TesteAluguel {

	@Test
	void testarAluguel() throws ClassNotFoundException, SQLException, ClienteJaCadastrado, VeiculoJaCadastrado, VeiculoNaoAlugado {
		DAOCliente daoC = new DAOCliente();
		daoC.removerTodos();
		Cliente c1 = new Cliente(1, "Pedro");
		daoC.inserir(c1);
			
		DAOVeiculo daoV = new DAOVeiculo();
		daoV.removerTodos();
		Carro v1 = new Carro("VW", "Fusca", 1970, 5000, 50, "ABC-1234", 1);
		daoV.inserir(v1);
		
		DAOAluguel daoA = new DAOAluguel();
		daoA.removerTodos();
		Aluguel a = new Aluguel(1, v1, c1, 10, 1000, 0);
		daoA.inserir(a);
		
		Aluguel recuperado = daoA.pesquisarPor(1);
		assertEquals("Pedro", recuperado.getCliente().getNome());
		assertEquals("VW", recuperado.getVeiculo().getMarca());
		assertEquals("Fusca", recuperado.getVeiculo().getModelo());
		assertEquals(10, recuperado.getDias());
		assertEquals(1000, recuperado.getValor());
		assertEquals(false, recuperado.isFechado());
		
	}
	
	@Test
	void testarVariosAlugueisDeVeiculo() throws ClassNotFoundException, SQLException, ClienteJaCadastrado, VeiculoJaCadastrado, VeiculoNaoAlugado {
		DAOCliente daoC = new DAOCliente();
		daoC.removerTodos();
		Cliente c1 = new Cliente(1, "Pedro");
		daoC.inserir(c1);
		Cliente c2 = new Cliente(2, "Raimundo");
		daoC.inserir(c2);
			
		DAOVeiculo daoV = new DAOVeiculo();
		daoV.removerTodos();
		Carro v1 = new Carro("VW", "Fusca", 1970, 5000, 50, "ABC-1234", 1);
		daoV.inserir(v1);
		Carro v2 = new Carro("VW", "Saveiro", 2019, 50000, 250, "ABC-1235", 1);
		daoV.inserir(v2);
		Carro v3 = new Carro("GM", "S10", 2018, 150000, 500, "ABC-1236", 1);
		daoV.inserir(v3);
		
		DAOAluguel daoA = new DAOAluguel();
		daoA.removerTodos();
		Aluguel a1 = new Aluguel(1, v1, c1, 10, 1010, 1);
		daoA.inserir(a1);
		
		Aluguel a2 = new Aluguel(2, v1, c1, 11, 1011, 1);
		daoA.inserir(a2);
		Aluguel a3 = new Aluguel(3, v1, c1, 12, 1012, 0);
		daoA.inserir(a3);
		
		Aluguel a4 = new Aluguel(4, v2, c2, 1, 250, 1);
		daoA.inserir(a4);

		Aluguel a5 = new Aluguel(5, v3, c2, 1, 250, 1);
		daoA.inserir(a5);

		Aluguel a6 = new Aluguel(6, v3, c1, 1, 250, 1);
		daoA.inserir(a6);
		
		assertEquals(3, v1.getAlugueis().size());
		assertEquals(1, v2.getAlugueis().size());
		assertEquals(2, v3.getAlugueis().size());
		
		assertEquals(4, c1.getAlugueis().size());
		assertEquals(2, c2.getAlugueis().size());
	}
	
}
