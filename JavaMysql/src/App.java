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


    static public void consultar(int id) {

        String sql;

        if(id <=0){

            sql = "SELECT * FROM pessoa ";

        }else{
            sql = "SELECT * FROM pessoa WHERE id = ?";

        }


        try (PreparedStatement comando = conexao.prepareStatement(sql);) {
            if(id > 0){

                comando.setInt(1, id);
            }


            try (ResultSet resultado = comando.executeQuery();) {

                while (resultado.next()) {
                     id = resultado.getInt("id");
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

    public static void atualizar(int id, String novoNome, String novoTelefone, String novaProfissao) {
        //por id
        String sql = "UPDATE pessoa set nome = ?,telefone = ?,profissao = ? where id = ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(4, id);
            comando.setString(1, novoNome);
            comando.setString(2, novoTelefone);
            comando.setString(3, novaProfissao);

            comando.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }


    }

    public static void deletar(int id) {
        //por id
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);
            comando.executeUpdate();

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
        consultar(-1);
        //inserir("Pedro Manuel", "92930388", 65, "cozinheiro");
        //deletar(4);
        //atualizar(15, "Fernando","989994544","Designer gráfico");


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