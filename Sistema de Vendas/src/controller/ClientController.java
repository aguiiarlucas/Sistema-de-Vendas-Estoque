package controller;

import model.Client;
import model.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.Main.mainMenu;

public class ClientController {
    private static final Scanner sc = new Scanner(System.in);
    public static List<Client> clients = new ArrayList<>();


    public static void menuClient() throws ParseException {
        int opcao;
        do {
            System.out.println("╔═════════════════════════════╗");
            System.out.println("║     Escolha a opção:        ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║  1. Cadastro                ║");
            System.out.println("║  2. Lista                   ║");
            System.out.println("║  3. Adicionar Saldo         ║");
            System.out.println("║  4. Menu Principal          ║");
            System.out.println("║  0. Sair                    ║");
            System.out.println("╚═════════════════════════════╝");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadClient();
                    break;
                case 2:
                    listClient();
                    break;
                case 3:
                    addBalanceToClient();
                    break;

                case 4:
                    mainMenu();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    public static void cadClient() throws ParseException {
        System.out.println("Cadastro de Cliente");
        sc.nextLine();
        String nome = getStringInput("Nome: ");
        String endereco = getStringInput("Endereço: ");
        String telefone = getValidPhoneNumber("Telefone (formato: DDD + número): ");
        String email = getStringInput("E-mail: ");
        String password = getStringInput("Senha: ");
        Date dataDeNascimento = getDateInput("Data de Nascimento (formato: dd/MM/yyyy): ");
        double saldoInicial = 100.0;

        Client novoCliente = new Client(nome, endereco, telefone, email, password, dataDeNascimento, saldoInicial);
        clients.add(novoCliente);
        System.out.println("Cliente adicionado com sucesso!\n" + novoCliente);

    }

    public static void listClient() {
        if (clients.size() > 0) {
            for (Client c : clients) {
                System.out.println(c + "\n");
            }
        } else {
            System.out.println("Não há clientes registrados");
        }
    }

    public static void addBalanceToClient() {
        System.out.println("Digite o id do cliente: ");
        int clientId = sc.nextInt();
        Client clientUpdate = findClientById(clientId);
        if (clientUpdate == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        double amount = getDoubleInput("Digite o Saldo a ser incrementado: ");
        double currentBalance = clientUpdate.getSaldo();
        double newBalance = currentBalance + amount;
        clientUpdate.setSaldo(newBalance);
    }



    private static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(String.valueOf(sc.nextDouble()));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
    }


    private static String getValidPhoneNumber(String msg) {
        String phone;
        Pattern pattern = Pattern.compile("\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}"); // (ddd)+(telefone)
        Matcher matcher;

        do {
            phone = getStringInput(msg);
            matcher = pattern.matcher(phone);
            if (!matcher.matches()) {
                System.out.println("Formato inválido. Tente novamente");
            }
        } while (!matcher.matches());
        return phone;
    }

    public static Client findClientById(int clientId) {
        for (Client client : clients) {
            if (client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }

    private static String getStringInput(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    private static int getIntegerInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro válido.");
            }
        }
    }

    private static Date getDateInput(String msg) throws ParseException {
        System.out.print(msg);
        String dataDeNascimentoStr = sc.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(dataDeNascimentoStr);
    }
}
