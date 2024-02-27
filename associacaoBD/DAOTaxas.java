package franciscoRafaelMenesesGoncalves.associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOTaxas {
	public void inserir(int id_associacao, Taxa t) throws SQLException {		
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		String nome = t.getNome();
		int vigencia = t.getVigencia();
		double valor = t.getValorAno();
		int parcelas = t.getParcelas();
		boolean administrativa = t.isAdministrativa();
		int id = proximoID();
				
		String comando = "INSERT INTO tb_taxas VALUES (" + id + ", '" + nome + "', " + vigencia + ", " + valor + ", " + parcelas + ", " + administrativa + ", " + id_associacao + ")";
		
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
	}

	public boolean taxaExiste(String nome, int vigencia, int idAssociacao) throws SQLException {		
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		boolean existe = false;
		
		String comando = "SELECT * FROM tb_taxas WHERE nome = '" + nome + "' AND vigencia = " + vigencia + " AND id_associacao = " + idAssociacao;
		ResultSet rs = stmt.executeQuery(comando);

		existe = rs.next();
		stmt.close();
		return existe;
	}
	
	public int recuperaID(String nome_taxa, int vigencia, int numAssociacao) throws SQLException, TaxaNaoExistente {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		String comando = "SELECT * FROM tb_taxas WHERE nome = '" + nome_taxa + "' AND vigencia = " + vigencia + " AND id_associacao = " + numAssociacao;		
		System.out.println(comando);
		ResultSet rs = stmt.executeQuery(comando);
		
		if(rs.next()) {
			int idTaxa = rs.getInt("id_taxa");
			stmt.close();
			return idTaxa;
		}else {
			stmt.close();
			throw new TaxaNaoExistente();
		}
	}
	
	private int proximoID() throws SQLException { // Gera um ID único para a taxa daquela associação
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();

		String comando = "SELECT MAX(id_taxa) AS id_max FROM tb_taxas";
		ResultSet rs = stmt.executeQuery(comando);
		
		int idMaximo = 0;
		
		if(rs.next()) {
			idMaximo = rs.getInt(1);
		}
		
		stmt.close();
		return idMaximo + 1;
	}
	
	public Taxa recuperar(String nome, int vigencia, int numAssociacao) throws SQLException, TaxaNaoExistente, ValorInvalido {
		if(nome == null || nome.trim().length() == 0 || vigencia <= 0)
			throw new ValorInvalido();
		
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		Taxa taxa = null;
		
		String comando = "SELECT * FROM tb_taxas WHERE nome = '" + nome + "' AND vigencia = " + vigencia + " AND id_associacao = " + numAssociacao;
		System.out.println(comando);
		
		ResultSet rs = stmt.executeQuery(comando);
		
		if(rs.next()) {
			String nome_taxa = rs.getNString("nome");
			int vigencia_taxa = rs.getInt("vigencia");
			double valorAno = rs.getDouble("valor_ano");
			int parcelas = rs.getInt("parcelas");
			boolean administrativa = rs.getBoolean("administrativa");
			taxa = new Taxa(nome_taxa, vigencia_taxa, valorAno, parcelas, administrativa);
		}else {
			stmt.close();
			throw new TaxaNaoExistente();
		}
		
		stmt.close();
		return taxa;
	}
	
	public double somaTaxasAno(int numAssociacao, int vigencia) throws SQLException {
		double soma = 0;
		
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();

		String comando = "SELECT valor_ano FROM tb_taxas WHERE id_associacao = " + numAssociacao + " AND vigencia = " + vigencia;
		System.out.println(comando);
		ResultSet rs = stmt.executeQuery(comando);
				
		while(rs.next()) {
			double valorAno = rs.getDouble("valor_ano");
			soma += valorAno;
		}
		
		stmt.close();
		return soma;
	}	
	
	public void removerTodos() throws SQLException {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		String comando = "DELETE FROM tb_taxas";
		
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
	}


}
