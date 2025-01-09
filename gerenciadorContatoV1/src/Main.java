import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    //Aqui definimos a lista contatos e teclado como variaveis globais
    //pois já estamos utilizando a orientação a objetos para ter uma melhor
    //separação não sendo necessário termos tanta proteção às variaveis do
    //programa principal

    private static ArrayList<Pessoa> listaContatos = new ArrayList<Pessoa>();

    private static Scanner teclado = new Scanner(System.in);


    public static void main(String[] args) {

        try{
            PessoaDAO.conexao = Conexao.getConexao();
        }catch (Exception e) {

            e.printStackTrace();
            //System.out.println("Não foi possível conectar ao banco de dados");

            return;
            //e.printStackTrace();
        }

        try {
            System.out.println(PessoaDAO.consultarPorId(1));

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Eita bebê, a consulta por id miou 🐈‍⬛");
        }

        try{
            System.out.println(PessoaDAO.consultarPorNome("Vini"));

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Xiii, a consulta por nome não deu certo 🦭");
        }

        //guarda a opcao selecionada pelo usuario no menu
        int opcao;

        do {
            limparTela();
            //obtem a opcao desejada pelo usuario
            opcao = obterEscolhaMenu();

            //executa a funcionalidade conforme escolhido pelo usuario
            processarEscolhaMenu(opcao);
        } while (opcao != 8);
    }

    private static void processarEscolhaMenu(int opcao) {
        switch (opcao) {
            case 1:
                incluirContato();
                pausa();
                break;
            case 2:
                alterarContato();
                break;
            case 3:
                consultarContatos();
                break;
            case 4:
                consultarContatoPorId();
                pausa();
                break;
            case 5:
                consultarContatoPorNome();
                break;
            case 6:
                consultarContatoPorEmail();
                break;
            case 7:
                excluirContato();
                pausa();
                break;
            case 8:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void consultarContatoPorEmail() {
        System.out.println("Informe o email do Contato a ser exibido:");
        String email = teclado.nextLine();

        try {
            ArrayList<Pessoa> contatos = PessoaDAO.consultarPorEmail(email);
            if (contatos.isEmpty()) {
                System.out.println("Não existe contato com este e-mail.");
            } else {
                for (Pessoa pessoa : contatos) {
                    System.out.println(pessoa);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar contato por e-mail.");
            System.out.println("Mensagem do Sistema: " + e.getMessage());
        }
    }

    private static void consultarContatoPorId() {
        int id;
        Pessoa pessoa = null;

        System.out.println("Informe o Id do Contato a ser exibido");
        id = teclado.nextInt();
        teclado.nextLine();

        try {
            pessoa = PessoaDAO.consultarPorId(id);
            if (pessoa != null)
                System.out.println(pessoa);
            else
                System.out.println("Não localizamos nenhum contato com esse id 👎🏼... Tente outro");
        } catch (SQLException e) {
            System.out.println("👎🏼 Erro ao consultar o id do Contato");
            System.out.println("⚠️ Mensagem do Sistema: " + e.getMessage());
        }

    }

    private static void consultarContatoPorNome() {
        String nome;
        ArrayList<Pessoa> contatos = new ArrayList<>();

        System.out.println("Informe o nome ou parte do nome do Contato a ser exibido");
        nome = teclado.nextLine();

        try {
            contatos = PessoaDAO.consultarPorNome(nome);
            if (contatos.isEmpty())
                System.out.println("Não existe contato com esse nome");

            else
                for (Pessoa pessoa : contatos){
                    System.out.println(pessoa);
                }

        } catch (SQLException e) {
            System.out.println("👎🏼 Erro ao consultar o nome do Contato");
            System.out.println("⚠️ Mensagem do Sistema: " + e.getMessage());
        }
    }

    private static int obterEscolhaMenu() {
        int opcao;

        System.out.println("\n--- Menu de Gerenciamento de Contatos ---\n");

        System.out.println("1. Incluir Contato");
        System.out.println("2. Alterar Contato");
        System.out.println("3. Consultar Todos os Contatos");
        System.out.println("4. Consultar Contato por Id");
        System.out.println("5. Consultar Contato por nome");
        System.out.println("6. Consultar Contato por email");
        System.out.println("7. Excluir Contato");
        System.out.println("8. Sair");

        System.out.print("\nEscolha uma opção: ");
        opcao = teclado.nextInt();
        teclado.nextLine(); // Limpeza buffer

        return opcao;
    }

    private static void incluirContato() {
        System.out.print("Digite o nome: ");
        String nome = teclado.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = teclado.nextLine();

        System.out.print("Digite o email: ");
        String email = teclado.nextLine();

        Pessoa novaPessoa = new Pessoa(nome, telefone, email);
        //substituir por um metodo criado em pessoaDao
        //listaContatos.add(novaPessoa);
        try {
            PessoaDAO.inserir(novaPessoa);
            System.out.println("Pessoa incluída com sucesso ⭐🙂!");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " Puxa vida, erro ao tentar inserir os dados no Banco 😵‍💫. Vamos tentar novamente? 🤓");

        }
        System.out.println("Mais um log ➡️ Contato incluído com sucesso!");
    }

    private static void alterarContato() {
        System.out.print("Digite o ID do contato a ser alterado: ");
        int id = teclado.nextInt();
        teclado.nextLine(); // limpeza buffer

        limparTela();

        // Busca a pessoa especificada pelo ID no banco
        Pessoa pessoa = null;

        try {
            pessoa = PessoaDAO.consultarPorId(id);
            if (pessoa != null)
                System.out.println(pessoa);
            else
                System.out.println("Não localizamos nenhum contato com esse id 👎🏼... Tente outro");
        } catch (SQLException e) {
            System.out.println("👎🏼 Erro ao consultar o id do Contato");
            System.out.println("⚠️ Mensagem do Sistema: " + e.getMessage());
        }


        // Solicitar atualizações ao usuário
        System.out.println("Nome atual --> " + pessoa.getNome());
        System.out.print("Digite o novo nome (ou deixe em branco para manter): ");
        String nome = teclado.nextLine();
        if (!nome.isBlank()) pessoa.setNome(nome);

        System.out.println("Telefone atual --> " + pessoa.getTelefone());
        System.out.print("Digite o novo telefone (ou deixe em branco para manter): ");
        String telefone = teclado.nextLine();
        if (!telefone.isBlank()) pessoa.setTelefone(telefone);

        System.out.println("Email atual --> " + pessoa.getEmail());
        System.out.print("Digite o novo email (ou deixe em branco para manter): ");
        String email = teclado.nextLine();
        if (!email.isBlank()) pessoa.setEmail(email);

        // Método para alterar no PessoaDAO
        try {
            int resultado = PessoaDAO.atualizarContato(pessoa);

            if (resultado > 0) {
                System.out.println("🎉 Contato atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Nenhuma alteração foi realizada. O contato pode já estar atualizado.");
            }
        } catch (SQLException e) {
            System.out.println("👎🏼 Erro ao tentar atualizar o Contato");
            System.out.println("⚠️ Mensagem do Sistema: " + e.getMessage());
        }

        pausa();
    }


    private static void consultarContatos() {

        ArrayList<Pessoa> contatos = new ArrayList<Pessoa>();

        try {
            contatos = PessoaDAO.consultarTodosContatos();
            //metodo isEmpty verifica se a lista esta vazia
            if (contatos.isEmpty()) {
                System.out.println("Nenhum contato cadastrado.");
            } else {
                System.out.println("\n--- Lista de Contatos ---");
                for (Pessoa pessoa : contatos) {
                    System.out.println(pessoa);
                }
            }
        } catch (SQLException e) {
            System.out.println("Xii, houve um erro ao consultar os dados no Banco de Dados. Tente novamente!");
            System.out.println("Erro do sistema 😭:" + e.getMessage());
        }
        pausa();
    }


    private static void excluirContato() {
        //obtem o id do contato;
        System.out.print("Digite o ID do contato a ser excluído: ");
        int id = teclado.nextInt();
        teclado.nextLine(); // Consumir quebra de linha

        Pessoa pessoa;

        //mostrar o id para o ususario para confirmar
        try {
            pessoa = PessoaDAO.consultarPorId(id);

            if (pessoa != null)
                System.out.println("Contato encontrado: \n" + pessoa);
            else {
                System.out.println("Xii... 😔 Não localizamos nenhum contato com esse id 👎🏼... Tente outro");
                return;
            }
        } catch (SQLException e) {
            System.out.println("👎🏼 Erro ao consultar o id do Contato");
            System.out.println("⚠️ Mensagem do Sistema: " + e.getMessage());
            return;
        }

        System.out.println("\nDeseja realmente excluir este contato? " + pessoa.getNome() + " [s/n]: ");
        char resposta = teclado.nextLine().toLowerCase().charAt(0);

        if (resposta == 'n') {
            System.out.println("Exclusão Cancelada 🫡!! ");
            return;
        }

        try {
            int resultado = PessoaDAO.excluirPorId(id);

            if (resultado == 0)
                System.out.println("🤨 Nenhum registro foi excluído pois este id não existe no banco. Tente outro 🛀🏼!");
            else
                System.out.println("Uhull 😁 Registro excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("O-ou 😔. Erro ao excluir este id do Banco de Dados. Tente novamente 🛀🏼!");
            System.out.println("Dá uma olhada na mensagem do sistema: " + e.getMessage());
        }

//        //excluir o contato
//        if (pessoa != null) {
//            listaContatos.remove(pessoa);
//            System.out.println("Contato excluído com sucesso!");
//        } else {
//            System.out.println("Contato não encontrado.");
//        }
    }

//    private static Pessoa encontrarContatoPorId(int id) {
//        //varre o array list para encontrar o id pesquisado
//        for (Pessoa pessoa : listaContatos) {
//            if (pessoa.getId() == id) {
//                //encontrou retorna o objeto pessoa
//                return pessoa;
//            }
//        }
//        //se chegou até aqui não existe este id
//        return null;
//    }


    private static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();

        } catch (IOException | InterruptedException ex) {
        }
    }

    private static void pausa() {
        System.out.println("\nTecle ENTER para continuar.");
        teclado.nextLine();
    }
}