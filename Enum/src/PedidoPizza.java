import java.util.Scanner;

public class PedidoPizza {

       public static void main(String[] args) {
        // Criando o Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Pedindo ao usuário para informar o tamanho da pizza
        System.out.println("Digite o tamanho da pizza (PEQUENO, MEDIO, GRANDE):");
        String entrada = scanner.nextLine().toUpperCase();  // Lê a entrada e converte para maiúsculo

        // Tentando converter a entrada para um valor do enum
        try {
            TamanhoPizza tamanho = TamanhoPizza.valueOf(entrada);  // Converte a string para o enum
            System.out.println("Você escolheu uma pizza de tamanho: " + tamanho);
        } catch (IllegalArgumentException e) {
            System.out.println("Tamanho inválido. Por favor, escolha entre PEQUENO, MEDIO ou GRANDE.");
        }

        scanner.close();  // Fecha o scanner
    }
}
