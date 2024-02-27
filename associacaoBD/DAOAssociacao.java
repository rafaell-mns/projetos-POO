package franciscoRafaelMenesesGoncalves.associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOAssociacao {
	public void inserir(Associacao a) throws SQLException, AssociacaoJaExistente {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		String nome = a.getNome();
		int num = a.getNum();
				
		String comando = "INSERT INTO tb_associacoes VALUES (" + num + ", " + "'" + nome + "')";
		
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
	}

	
	public Associacao recuperar(int num) throws SQLException, AssociacaoNaoExistente {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		Associacao associacao = null;
		
		String comando = "SELECT * FROM tb_associacoes WHERE num = " + num;
		System.out.println(comando);
		
		ResultSet rs = stmt.executeQuery(comando);
		
		if(rs.next()) { // Verifica se há um registro resultante
			int numero = rs.getInt("num");
			String nome = rs.getNString("nome");
			associacao = new Associacao(numero, nome);
		}else {
			stmt.close();
			throw new AssociacaoNaoExistente();
		}
		
		stmt.close();
		return associacao;
	}
	
	public boolean associacaoExiste(int num, String nomeAssociacao) throws SQLException{		
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		boolean existe = false;
		
		String comando = "SELECT * FROM tb_associacoes WHERE num = " + num + " AND nome = '" + nomeAssociacao + "'";
		
		System.out.println(comando);
		ResultSet rs = stmt.executeQuery(comando);

		existe = rs.next();
		stmt.close();
		return existe;
	}

	public boolean numAssociacaoExiste(int num) throws SQLException {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		boolean existe = false;
		
		String comando = "SELECT * FROM tb_associacoes WHERE num = " + num;
		
		System.out.println(comando);
		ResultSet rs = stmt.executeQuery(comando);

		existe = rs.next();
		stmt.close();
		return existe;
	}
	
	public void removerTodos() throws SQLException {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		String comando = "DELETE FROM tb_associacoes";
		
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
		
		System.out.println(""); // Saltar uma linha no terminal
	}
}
