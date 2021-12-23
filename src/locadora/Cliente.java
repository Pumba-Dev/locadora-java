package locadora;

import java.util.ArrayList;

public class Cliente {

	private int cpf;
	private String nome;
	private ArrayList<Aluguel> alugueis = null;
	
	public ArrayList<Aluguel> getAlugueis() throws VeiculoNaoAlugado {		
		DAOAluguel daoA = new DAOAluguel();
		alugueis = daoA.pesquisarPor(this);
		return alugueis;
	}

	public void setAlugueis(ArrayList<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

	public int getCpf() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cliente(int cpf1, String nome1) {
		cpf = cpf1;
		nome = nome1;
	}	
}
