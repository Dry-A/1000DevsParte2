import java.sql.*;
import java.util.ArrayList;

//classe que vai interagir com o Banco de dados. ela vai se encarregar de salvar, consultar, deletar e atualizar dados
//classe que nao será objeto, portanto devera ser static
public class PessoaDAO {

    //o Main vai chamar a classe conexao para se conectar com o banco de dados
    //vai pegar a conexao devolvida, gravar no PessoaDado para poder fazer as açoes
    public static Connection conexao = null;

    public static void inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa(nome, telefone,email ) VALUES(?,?,?)";

        try(
                PreparedStatement comando = conexao.prepareStatement(sql);
                ) {
            comando.setString(1, pessoa.getNome());
            comando.setString(2, pessoa.getTelefone());
            comando.setString(3, pessoa.getEmail());
            comando.executeUpdate();
        }
    }

        public static ArrayList<Pessoa> consultarTodosContatos() throws SQLException{
            ArrayList<Pessoa> lista = new ArrayList<Pessoa>();

            String sql = "SELECT * FROM pessoa";

            try (   Statement comando = conexao.createStatement();
                    ResultSet resultado = comando.executeQuery(sql);
                 ){

                while (resultado.next()) {
                    lista.add(new Pessoa(   resultado.getString("nome"),
                                            resultado.getString("telefone"),
                                            resultado.getString("email")));
                }
            }
            return lista;
        }

        public static Pessoa consultarPorId(int id) throws SQLException {
            Pessoa pessoa = null;

            String sql = "SELECT * FROM pessoa WHERE id = ?";

            try(PreparedStatement comando = conexao.prepareStatement(sql)){
                comando.setInt(1, id);
                ResultSet resultado = comando.executeQuery();

                if (resultado.next()) {
                    pessoa = new Pessoa(    resultado.getString("nome"),
                                            resultado.getString("telefone"),
                                            resultado.getString("email"));
                }
            }

            return pessoa;
        }

        public static Pessoa consultarPorNome(String nome) throws SQLException {
            Pessoa pessoa = null;
            String sql = "SELECT * FROM pessoa WHERE nome = ?";

            try(PreparedStatement comando = conexao.prepareStatement(sql)){
                //aqui eu estabeleço o que vai no primeiro sinaal de interrogaçao
                comando.setString(1, nome);
                ResultSet resultado = comando.executeQuery();

                if (resultado.next()) {
                    pessoa = new Pessoa( resultado.getString("nome"),
                                         resultado.getString("telefone"),
                                         resultado.getString("email"));
                }
            }
            return pessoa;

        }
    }


