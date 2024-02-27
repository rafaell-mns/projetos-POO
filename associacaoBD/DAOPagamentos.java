package franciscoRafaelMenesesGoncalves.associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOPagamentos {
	public void inserir(String taxa, int vigencia, int numAssociado, long data, double valor, int numAssociacao) throws ValorInvalido, SQLException, TaxaNaoExistente, AssociadoNaoExistente, AssociadoJaRemido {		
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		// Recuperar o ID da taxa para a qual o pagamento tá sendo realizado
		DAOTaxas DAOTaxa = new DAOTaxas();
		int idTaxa = DAOTaxa.recuperaID(taxa, vigencia, numAssociacao);	

		String comando = "INSERT INTO tb_pagamentos VALUES (" + numAssociado + ", " + valor + ", " + data + ", " + idTaxa + ")";
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
	}
	
	double somarPagamento(int id_associado, int id_taxa) throws SQLException {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		double soma = 0;
		
		String comando = "SELECT * FROM tb_pagamentos WHERE id_associado = " + id_associado +  " AND id_taxa = " + id_taxa;
		ResultSet rs = stmt.executeQuery(comando);
		
		while(rs.next()){
            double pagamento = rs.getDouble("valor_pago");
            soma += pagamento;
		}
		
		return soma;
	}

	public void removerTodos() throws SQLException {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		String comando = "DELETE FROM tb_pagamentos";
	
		System.out.println(""); // Saltar uma linha no terminal
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
	}

}
