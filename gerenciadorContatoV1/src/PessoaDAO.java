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
                    lista.add(new Pessoa(   resultado.getInt("id"),
                                            resultado.getString("nome"),
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
                    pessoa = new Pessoa(    resultado.getInt("id"),
                                            resultado.getString("nome"),
                                            resultado.getString("telefone"),
                                            resultado.getString("email"));
                }
            }

            return pessoa;
        }

        public static ArrayList<Pessoa> consultarPorNome(String nome) throws SQLException {
            ArrayList<Pessoa> lista = new ArrayList<>();

            String sql = "SELECT * FROM pessoa WHERE nome like ?";

            try(PreparedStatement comando = conexao.prepareStatement(sql)) {
                //aqui eu estabeleço o que vai no primeiro sinaal de interrogaçao
                comando.setString(1, "%" + nome + "%");

                try (ResultSet resultado = comando.executeQuery()) {

                    while (resultado.next()) {
                        lista.add( new Pessoa(resultado.getString("nome"),
                                                resultado.getString("telefone"),
                                                resultado.getString("email")));
                    }
                }
            }

            return lista;
        }

    public static ArrayList<Pessoa> consultarPorEmail(String email) throws SQLException {
        ArrayList<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa WHERE email = ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, email);
            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    lista.add(new Pessoa(
                            resultado.getString("nome"),
                            resultado.getString("telefone"),
                            resultado.getString("email")
                    ));
                }
            }
        }
        return lista;
    }


        public static int excluirPorId(int id) throws SQLException {
            String sql = "DELETE FROM pessoa WHERE id = ?";

            try(PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, id);
                int resultado = comando.executeUpdate();
                return resultado;
            }
        }

        public static int atualizarContato(Pessoa pessoa) throws SQLException {
            String sql = "UPDATE pessoa SET nome = ?,telefone = ?,email = ? WHERE id = ?";

            try(PreparedStatement comando = conexao.prepareStatement(sql)) {

                System.out.println("Atualizando contato com ID: " + pessoa.getId());
                System.out.println("Novo Nome: " + pessoa.getNome());
                System.out.println("Novo Telefone: " + pessoa.getTelefone());
                System.out.println("Novo Email: " + pessoa.getEmail());

                comando.setString(1, pessoa.getNome());
                comando.setString(2, pessoa.getTelefone());
                comando.setString(3, pessoa.getEmail());
                comando.setInt(4, pessoa.getId());

                int resultado = comando.executeUpdate();
                return resultado;
            }
        }
    }


