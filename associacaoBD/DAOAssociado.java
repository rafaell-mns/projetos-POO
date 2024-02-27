package franciscoRafaelMenesesGoncalves.associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOAssociado {
	public void inserir(int id_associacao, Associado a) throws SQLException, AssociadoJaExistente {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		int num = a.getNumero();
		String nome = a.getNome();
		String telefone = a.getTelefone();
		long data_associacao = a.getDataAssociacao();
		long data_nascimento = a.getNascimento();

		long data_remissao = 0;
		if(a instanceof AssociadoRemido)
			data_remissao = ((AssociadoRemido) a).getDataRemissao();
				
		String comando = "INSERT INTO tb_associados VALUES (" + num + ", '" + nome + "', '" + telefone + "', " + data_associacao + ", " + data_nascimento + ", " + data_remissao + ", " + id_associacao + ")";
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
	}
	
	public Associado recuperar(int num) throws SQLException, AssociadoNaoExistente, ValorInvalido {
		if(num <= 0) throw new ValorInvalido();
		
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		Associado associado = null;
		
		String comando = "SELECT * FROM tb_associados WHERE num = " + num;
		System.out.println(comando);
		
		ResultSet rs = stmt.executeQuery(comando);
		
		if(rs.next()) { // Verifica se há um registro resultante
			int numero = rs.getInt("num");
			String nome = rs.getNString("nome");
			String telefone = rs.getNString("nome");
			long data_associacao = rs.getLong("data_associacao");
			long data_nascimento = rs.getLong("data_nascimento");
			long data_remissao = rs.getLong("data_remissao");
			
			if (data_remissao == 0) {
	            associado = new Associado(numero, nome, telefone, data_associacao, data_nascimento);
	        } else {
	            associado = new AssociadoRemido(numero, nome, telefone, data_associacao, data_nascimento, data_remissao);
	        }
	    }else {
	    	stmt.close();
	    	throw new AssociadoNaoExistente();
	    }
	    
	    stmt.close();
	    return associado;
	}

	public boolean associadoExiste(int num, int idAssociacao) throws SQLException { //olhar num e id_associacao
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		boolean existe = false;
		
		String comando = "SELECT * FROM tb_associados WHERE num = " + num + " AND id_associacao = " + idAssociacao;
		System.out.println(comando);
		ResultSet rs = stmt.executeQuery(comando);

		existe = rs.next();
		stmt.close();
		
		return existe;
	}
	
	public void removerTodos() throws SQLException {
		Connection conexao = Conectar.getConectar();
		Statement stmt = conexao.createStatement();
		
		String comando = "DELETE FROM tb_associados";
		
		System.out.println(comando);
		stmt.execute(comando);
		stmt.close();
	}

}
