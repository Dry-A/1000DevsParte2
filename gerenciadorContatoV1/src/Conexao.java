
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

//essa classe cria a conexao com o banco

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/teste";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConexao() throws SQLException {

        return DriverManager.getConnection(URL, USUARIO, SENHA);

    }
}
