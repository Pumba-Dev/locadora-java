package locadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOVeiculo {

	public void inserir(Veiculo v) throws SQLException, ClassNotFoundException, ClienteJaCadastrado, VeiculoJaCadastrado {
        Connection con;
	    try {
			Veiculo vei = pesquisarPor(v.getPlaca());
			throw new VeiculoJaCadastrado();
		} catch (VeiculoNaoCadastrado e1) {
			con = Conexao.getConexao();
	        Statement st = con.createStatement();
	        	String cmd = "insert into veiculo "
	        			+ "(placa, marca, modelo, ano, "
	        			+ "valorAvaliado, valorDiaria, "
	        			+ "tipo, carga, cilindrada, "
	        			+ "passageiros, discriminador) "
	        			+ "values (\'" 
	        			+ v.getPlaca() + "\', \'" + v.getMarca() + "\', \'" + v.getModelo() + "\',"
	        			+ v.getAnoDeFabricacao() + "," + v.getValorAvaliado() + ","
	        			+ v.getValorDiaria();
	        	if (v instanceof Carro) {
	        		cmd = cmd + "," + ((Carro) v).getTipo() + ",0,0,0,1)";
	        	} else	if (v instanceof Caminhao) {
		        	cmd = cmd + ",0," + ((Caminhao) v).getCarga() + ",0,0,2)";
	        	} else	if (v instanceof Moto) {
		        	cmd = cmd + ",0,0," + ((Moto) v).getCilindrada() + ",0,3)";
	        	} else	if (v instanceof Onibus) {
		        	cmd = cmd + ",0,0,0," + ((Onibus) v).getPassageiros() + ",4)";
	        	}
	        	System.out.println(cmd);
	        	st.execute(cmd);
	    	    st.close();
		}
	}

	public void alterar(Veiculo v) {
		
	}

	public void remover(String placa) {
		
	}
	
	public void removerTodos() throws ClassNotFoundException, SQLException {
		Connection con = Conexao.getConexao();
        Statement st = con.createStatement();
    	    String cmd = "delete from veiculo"; 
    	    st.execute(cmd);
    	    st.close();
	}

	public Veiculo pesquisarPor(String placa) throws VeiculoNaoCadastrado {
        Connection con;
        try {
			con = Conexao.getConexao();
            Statement st = con.createStatement();
        	    String cmd = "select * from veiculo where placa = \'" + placa + "\'"; 
        	    System.out.println(cmd);
        	    ResultSet rs = st.executeQuery(cmd);
        	    if (rs.next()) {
        	    	   String marca = rs.getString("marca");
        	    	   String modelo = rs.getString("modelo");
        	    	   int ano = rs.getInt("ano");
        	    	   int tipo = rs.getInt("tipo");
        	    	   int carga = rs.getInt("carga");
        	    	   int cilindrada = rs.getInt("cilindrada");
        	    	   int passageiros = rs.getInt("passageiros");
        	    	   int discriminador = rs.getInt("discriminador");
        	    	   double valorAvaliado = rs.getDouble("valorAvaliado");
        	    	   double valorDiaria = rs.getDouble("valorDiaria");
        	    	   
        	    	   Veiculo v;
       	       if (discriminador == 1) {
       	    	      v = new Carro(marca, modelo, ano, valorAvaliado, valorDiaria, placa, tipo);
	    	       } else if (discriminador == 2) {
	    	    	      v = new Caminhao(marca, modelo, ano, valorAvaliado, valorDiaria, placa, carga);
	    	       } else if (discriminador == 3) {
	    	    	      v = new Moto(marca, modelo, ano, valorAvaliado, valorDiaria, placa, cilindrada);
	    	       } else {
	    	    	      v = new Onibus(marca, modelo, ano, valorAvaliado, valorDiaria, placa, passageiros);
	    	       }
       	       st.close();
        	    	   return v;
        	    } 	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        throw new VeiculoNaoCadastrado();
	}

//		public ArrayList<Cliente> pesquisarPor(String parteDoNome) throws ClienteNaoCadastrado {
//	        Connection con;
//	        try {
//				con = Conexao.getConexao();
//	            Statement st = con.createStatement();
//	        	    String cmd = "select * from cliente where nome like \'%" + parteDoNome + "%\'"; 
//	        	    ResultSet rs = st.executeQuery(cmd);
//	        	    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
//	        	    while (rs.next()) {
//	     	    	   String nome = rs.getString("nome");
//	     	    	   int cpf = rs.getInt("cpf");
//	        	    	   Cliente c = new Cliente(cpf, nome);
//	        	    	   clientes.add(c);
//	        	    } 
//	        	    st.close();
//	        	    return clientes;
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}  
//	        throw new ClienteNaoCadastrado();
//		}
//
//	}

}
