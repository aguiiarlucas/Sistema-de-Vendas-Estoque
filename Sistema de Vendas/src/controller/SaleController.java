package controller;

import model.Client;
import model.Product;
import utils.Utils;

import java.text.ParseException;
import java.util.*;

import static controller.ProductController.products;
import static main.Main.mainMenu;
import static controller.ClientController.clients;

public class SaleController {
    private static Scanner sc = new Scanner(System.in);
    private static Map<Product, Integer> cart = new HashMap<>();


    public static void saleMenu() throws ParseException {
        int opcao;
        do {
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║    Escolha uma opção:         ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║  1. Comprar                   ║");
            System.out.println("║  2. Carrinho                  ║");
            System.out.println("║  3. Menu Principal            ║");
            System.out.println("║  0. Sair                      ║");
            System.out.println("╚═══════════════════════════════╝");


            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    buyProduct();
                    break;
                case 2:
                    cartProduct();
                    break;
                case 3:
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

    private static void cartProduct() throws ParseException {
        System.out.println("----Carrinho---");

        if (cart.size() > 0) {
            for (Product p : cart.keySet()) {
                System.out.println("Produto: " + p.getName() + "\nQuantidade: " + cart.get(p));
            }
        } else {
            System.out.println("Carrinho Vazio");
        }
        saleMenu();
    }

    private static void buyProduct() throws ParseException {
        if (products.isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        System.out.println("--------- Produtos disponíveis ------");
        for (Product p : products) {
            System.out.println(p + "\n");
        }

        int productId = getProductIdInput();
        Product product = findProductById(productId);

        if (product == null) {
            System.out.println("Produto não encontrado");
            return;
        }

        int quantityToBuy = getQuantityInput();

        if (product.getQuantity() < quantityToBuy) {
            System.out.println("Estoque insuficiente para este produto.");
            return;
        }

        int quantityInCart = cart.getOrDefault(product, 0);
        cart.put(product, quantityInCart + quantityToBuy);
        decrementProductQuantity(product, quantityToBuy);
        System.out.println(quantityToBuy + " " + product.getName() + "(s) adicionado(s) ao carrinho");

        System.out.println("Gostaria de adicionar outro produto? 1 SIM / 0 Finalizar");
        int opc = sc.nextInt();
        if (opc == 1) {
            buyProduct();
        } else {
            completePurchase();
        }
    }

    private static int getProductIdInput() {
        System.out.println("Código do Produto: ");
        return sc.nextInt();
    }

    private static Product findProductById(int productId) {
        return products.stream()
                .filter(product -> product.getId() == productId)
                .findFirst()
                .orElse(null);
    }

    private static int getQuantityInput() {
        System.out.println("Quantidade: ");
        return sc.nextInt();
    }



    private static void decrementProductQuantity(Product product, int quantity) {
        int currentQuantity = product.getQuantity();
        if (currentQuantity >= quantity) {
            product.setQuantity(currentQuantity - quantity);
        } else {
            System.out.println("Estoque insuficiente para este produto.");
        }
    }
    private static void completePurchase() throws ParseException {
        double amount = calculateTotalAmount();
        System.out.println("Produtos no carrinho: ");
        printCart();

        Client client = findValidClient();
        if (client == null) {
            System.out.println("Cliente não encontrado");
            return;
        }

        System.out.println("Cliente: " + client.getNome() + "\n");
        System.out.println("Valor total da compra: " + Utils.doubleToString(amount) + "\n");

        double remainingBalance = client.getSaldo() - amount;
        if (remainingBalance < 0) {
            System.out.println("Saldo insuficiente para completar a compra");
            return;
        }

        System.out.println("Saldo restante após a compra: " + Utils.doubleToString(remainingBalance));
        cart.clear();
        saleMenu();
    }

    private static double calculateTotalAmount() {
        double totalAmount = 0.0;
        for (Product p : cart.keySet()) {
            int quantity = cart.get(p);
            totalAmount += p.getPrice() * quantity;
        }
        return totalAmount;
    }

    private static void printCart() {
        for (Product p : cart.keySet()) { //keyset = valor e quantitidade
            int quantity = cart.get(p);
            System.out.println(p.getName() + " - Quantidade: " + quantity);
        }
    }

    private static Client findValidClient() {
        System.out.println("Código do Cliente: ");
        int clientId = sc.nextInt();
        return clients.stream()
                .filter(client -> client.getId() == clientId)
                .findFirst()
                .orElse(null);
    }



}
