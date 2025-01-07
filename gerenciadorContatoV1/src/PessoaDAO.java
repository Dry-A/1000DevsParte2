import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//classe que vai interagir com o Banco de dados. ela vai se encarregar de salvar, consultar, deletar e atualizar dados
//classe que nao será objeto, portanto devera ser static

public class PessoaDAO {

    //o Main vai chamar a classe conexao para se conectar com o banco de dados
    //vai pegar a conexao devolvida, gravar no PessoaDado para poder fazer as açoes
    public static Connection conexao = null;

    public static void inserir(Pessoa pessoa) throws SQLException {

        String sql = "INSERT INTO pessoa(nome, telefone,email ) VALUES(?,?,?)";

        try(PreparedStatement comando = conexao.prepareStatement(sql);) {

            comando.setString(1, pessoa.getNome());
            comando.setString(2, pessoa.getTelefone());
            comando.setString(3, pessoa.getEmail());

            comando.executeUpdate();
        }

        //conexao.set;
    }

}