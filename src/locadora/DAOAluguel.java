package locadora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAluguel {
	
	public void inserir(Aluguel a) {
		try {
	        Connection con;
			con = Conexao.getConexao();
		    Statement st = con.createStatement();
		    int fechado = 0;
		    if (a.isFechado()) { 
		    	   fechado = 1;
		    }
		    	String cmd = "insert into aluguel (idaluguel, idveiculo, idcliente, dias, valor, fechado) values (" +
		                   a.getCodigo() + ",\'" + a.getVeiculo().getPlaca() + "\'," + a.getCliente().getCpf() + 
		                   "," + a.getDias() + "," + a.getValor() + "," + fechado + ")"; 
	    	    System.out.println(cmd);
		    st.execute(cmd);
		    	st.close();
		 } catch (Exception e) {
			 e.printStackTrace(System.out);
		 }
	}

	public void alterar(Aluguel a) {
		
	}

	public void remover(int idAluguel) {
		
	}
	
	public void removerTodos() {
		try {
			Connection con = Conexao.getConexao();
	        Statement st = con.createStatement();
	    	    String cmd = "delete from aluguel"; 
	    	    st.execute(cmd);
	    	    st.close();
		 } catch (Exception e) {
			 e.printStackTrace(System.out);
		 }
	}

	public Aluguel pesquisarPor(int idAluguel) throws VeiculoNaoAlugado {
        Connection con;
        try {
			con = Conexao.getConexao();
            Statement st = con.createStatement();
        	    String cmd = "select * from aluguel where idaluguel = " + idAluguel; 
        	    System.out.println(cmd);
        	    ResultSet rs = st.executeQuery(cmd);
        	    if (rs.next()) {
        	    	   String placa = rs.getString("idVeiculo");
        	    	   int cpf = rs.getInt("idCliente");
        	    	   int codigo = rs.getInt("idaluguel");
        	    	   int dias = rs.getInt("dias");
        	    	   double valor = rs.getDouble("valor");
        	    	   int fechado = rs.getInt("fechado");
        	    	   
        	    	   DAOCliente daoC = new DAOCliente();
        	    	   Cliente c = daoC.pesquisarPor(cpf);
        	    	   DAOVeiculo daoV = new DAOVeiculo();
        	    	   Veiculo v = daoV.pesquisarPor(placa);
        	    	   
        	    	   Aluguel aluguel = new Aluguel(codigo, v, c, dias, valor, fechado);
        	    	   return aluguel;
        	    } 	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        throw new VeiculoNaoAlugado();
	}
	
	public ArrayList<Aluguel> pesquisarPor(Veiculo v) throws VeiculoNaoAlugado {
        Connection con;
        try {
			con = Conexao.getConexao();
            Statement st = con.createStatement();
        	    String cmd = "select * from aluguel where idveiculo = \'" + v.getPlaca() + "\'" ; 
        	    System.out.println(cmd);
        	    ResultSet rs = st.executeQuery(cmd);
        	    ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();
        	    while (rs.next()) {
        	    	   String placa = rs.getString("idVeiculo");
        	    	   int cpf = rs.getInt("idCliente");
        	    	   int codigo = rs.getInt("idaluguel");
        	    	   int dias = rs.getInt("dias");
        	    	   double valor = rs.getDouble("valor");
        	    	   int fechado = rs.getInt("fechado");
        	    	   
        	    	   DAOCliente daoC = new DAOCliente();
        	    	   Cliente c = daoC.pesquisarPor(cpf);
        	    	   
        	    	   Aluguel aluguel = new Aluguel(codigo, v, c, dias, valor, fechado);
        	    	   alugueis.add(aluguel);
        	    } 	    
 	    	    return alugueis;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        throw new VeiculoNaoAlugado();
	}

	public ArrayList<Aluguel> pesquisarPor(Cliente c) throws VeiculoNaoAlugado {
        Connection con;
        try {
			con = Conexao.getConexao();
            Statement st = con.createStatement();
        	    String cmd = "select * from aluguel where idcliente = " + c.getCpf(); 
        	    System.out.println(cmd);
        	    ResultSet rs = st.executeQuery(cmd);
        	    ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();
        	    while (rs.next()) {
        	    	   String placa = rs.getString("idVeiculo");
        	    	   int cpf = rs.getInt("idCliente");
        	    	   int codigo = rs.getInt("idaluguel");
        	    	   int dias = rs.getInt("dias");
        	    	   double valor = rs.getDouble("valor");
        	    	   int fechado = rs.getInt("fechado");
        	    	   
        	    	   DAOVeiculo daoV = new DAOVeiculo();
        	    	   Veiculo v = daoV.pesquisarPor(placa);
        	    	   
        	    	   Aluguel aluguel = new Aluguel(codigo, v, c, dias, valor, fechado);
        	    	   alugueis.add(aluguel);
        	    } 	    
 	    	    return alugueis;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        throw new VeiculoNaoAlugado();
	}


//	public ArrayList<Cliente> pesquisarPor(String parteDoNome) throws ClienteNaoCadastrado {
//        Connection con;
//        try {
//			con = Conexao.getConexao();
//            Statement st = con.createStatement();
//        	    String cmd = "select * from cliente where nome like \'%" + parteDoNome + "%\'"; 
//        	    ResultSet rs = st.executeQuery(cmd);
//        	    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
//        	    while (rs.next()) {
//     	    	   String nome = rs.getString("nome");
//     	    	   int cpf = rs.getInt("cpf");
//        	    	   Cliente c = new Cliente(cpf, nome);
//        	    	   clientes.add(c);
//        	    } 
//        	    st.close();
//        	    return clientes;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//        throw new ClienteNaoCadastrado();
//	}

}
