import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class App {

    //Criar conexao
    static Connection conexao = null;

    static public void criarConexao() {

        //Parametros de Conexão
        String url = "jdbc:mysql://localhost:3306/teste";
        String usuario = "root";
        String senha = "root";

        //estabelecer a conexao
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void consultar() {

        //Conectar ao banco de dados e executar a consulta
        try (Statement comando = conexao.createStatement()) {

            String sql = "SELECT * FROM pessoa";

            try (ResultSet resultado = comando.executeQuery(sql);) {

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nome = resultado.getString("nome");
                    String telefone = resultado.getString("telefone");
                    int idade = resultado.getInt("idade");
                    String profissao = resultado.getString("profissao");
                    Float renda = resultado.getFloat("renda");

                    System.out.println("ID: " + id + ", Nome: " + nome + ", Telefone: " + telefone + ", Idade: " + idade + ", Profissão: " + profissao + ", Renda: R$ " + renda);
                }

            }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void inserir(String nome, String telefone, int idade, String profissao) {

        String sql = "INSERT INTO pessoa(nome, telefone, idade, profissao) VALUES(?,?,?,?)";

        try (PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, nome);
            comando.setString(2, telefone);
            comando.setInt(3, idade);
            comando.setString(4, profissao);

            comando.executeUpdate();

            try (ResultSet resultado = comando.getGeneratedKeys()) {
                if (resultado.next()) {
                    System.out.println("Id do usuário recém inserido " + resultado.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        criarConexao();
        consultar();
        inserir("Pedro Manuel", "92930388", 65, "cozinheiro");

        if (conexao != null)
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        teclado.close();
        //System.out.println("teste");
    }
}