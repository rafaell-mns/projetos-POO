package franciscoRafaelMenesesGoncalves.associacaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
	private static final String url = "jdbc:mysql://localhost:3306/associacao_bd";
	private static final String usuario = "root";
	private static final String senha = "1234";
		
	public static Connection getConectar() {
		Connection conexao = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar driver

			conexao = DriverManager.getConnection(url, usuario, senha);
			//System.out.println("Conexão criada");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conexao;
	}
}
