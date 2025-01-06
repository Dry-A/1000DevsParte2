import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class App {


    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/teste";
        String usuario = "root";
        String senha = "root";


        Connection conexao = null;
        Statement comando = null;
        ResultSet resultado = null;


        try {
            //estabelecer a conexao
            conexao = DriverManager.getConnection(url, usuario, senha);

            //Criar um Statemet para executar a consulta
              comando = conexao.createStatement();

            //Consulta SQL
                String sql = "SELECT * FROM pessoa";

            //Executar a consulta e obter resultados
               resultado = comando.executeQuery(sql);

            //Processar os resultados
              while(resultado.next()){
                   int id = resultado.getInt("id");
                   String nome = resultado.getString("nome");
                   String telefone = resultado.getString("telefone");
                   int idade = resultado.getInt("idade");
                   String profissao = resultado.getString("profissao");
                   Float renda = resultado.getFloat("renda");

                   System.out.println("ID: "+ id + ", Nome: " + nome + ", Telefone: " + telefone + ", Idade: " + idade + ", Profissão: "+profissao + ", Renda: R$ " + renda);
                }
        } catch (SQLException e) {
            e.printStackTrace();
            }finally{

            //Garantir que todos os recursos sejam fechados

               try{
                   if (resultado != null)
                       resultado.close();
                  if(comando != null)
                        comando.close();
                    if(conexao != null)
                       conexao.close();
               }catch(SQLException e){
                   e.printStackTrace();
               }
            }

            teclado.close();
            //System.out.println("teste");
        }
    }