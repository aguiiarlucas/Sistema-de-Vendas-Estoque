package main;

import controller.ClientController;
import controller.ProductController;
import model.Client;

import java.text.ParseException;
import java.util.Scanner;

import static controller.ClientController.cadClient;
import static controller.ClientController.menuClient;
import static controller.ProductController.menu;
import static controller.SaleController.saleMenu;

public class Main {

    public  static  Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        mainMenu();

    }
    public static  void mainMenu() throws ParseException {
        int opcao;
        do {
            System.out.println("╔═════════════════════════════╗");
            System.out.println("║      Escolha uma opção:     ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║  1. Cadastrar Cliente       ║");
            System.out.println("║  2. Cadastrar Produto       ║");
            System.out.println("║  3. Realizar Venda          ║");
            System.out.println("║  0. Sair                    ║");
            System.out.println("╚═════════════════════════════╝");


            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    menuClient();
                    break;
                case 2:
                    menu();
                    break;
                case 3:
                    saleMenu();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
    }



