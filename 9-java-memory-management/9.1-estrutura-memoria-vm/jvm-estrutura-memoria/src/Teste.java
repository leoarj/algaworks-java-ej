import java.util.Scanner;
import java.util.Stack;

public class Teste {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu seu nome: ");
        String nome = scanner.nextLine();

        System.out.printf("Olá, %s", nome);
    }
}