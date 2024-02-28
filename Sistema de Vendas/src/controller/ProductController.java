package controller;

import model.Client;
import model.Product;

import java.text.ParseException;
import java.util.*;

import static main.Main.mainMenu;

public class ProductController {

    private static Scanner sc = new Scanner(System.in);
    public static List<Product> products = new ArrayList<>();

    public static void menu() throws ParseException {
        int opcao;
        do {
            System.out.println("╔═════════════════════════════════════╗");
            System.out.println("║     Gestão de Produtos / Estoque    ║");
            System.out.println("╠═════════════════════════════════════╣");
            System.out.println("║        Escolha uma opção:           ║");
            System.out.println("╠═════════════════════════════════════╣");
            System.out.println("║     1. Registrar                    ║");
            System.out.println("║     2. Listar                       ║");
            System.out.println("║     3. Remover                      ║");
            System.out.println("║     4. Listar                       ║");
            System.out.println("║     5. Menu Principal               ║");
            System.out.println("║     0. Sair                         ║");
            System.out.println("║                                     ║");
            System.out.println("╚═════════════════════════════════════╝");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    registerProduct();
                    break;
                case 2:
                    listProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    addQuantityToProduct();
                    break;
                case 5:
                    mainMenu();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    public static void listProduct() {
        if (products.size() > 0) {
            for (Product p : products) {
                System.out.println(p + "\n");
            }
        } else {
            System.out.println("Não há produtos registrados");
        }
    }

    private static void registerProduct() {
        sc.nextLine();
        String nameProduct = getStringInput("Nome do Produto: ");
        String description = getStringInput("Descrição:");
        String priceStr = getStringInput("Preço:").replace(',', '.'); // Substitui vírgula por ponto
        Double price = getPrice(priceStr);
        if (price == null)
            return; // Retorna para que o usuário possa inserir o preço novamente
        int quantity = getIntegerInput("Quantidade: ");

        Product product = new Product(nameProduct, description, price, quantity);
        products.add(product);
        System.out.println("-------");
        System.out.println(product.getName() + " Registrado com sucesso!");
    }

    public static Product findProductById(int productId) {
        if (products != null) {
            for (Product product : products) {
                if (product.getId() == productId) {
                    return product;
                }
            }
        }
        return null;
    }

    public static Product removeProduct() {
        System.out.println("Digite o ID do produto: ");
        int productId = sc.nextInt();
        sc.nextLine();

        Product updateProduct = findProductById(productId);

        if (updateProduct.getId() == productId) {
            products.remove(updateProduct);
            System.out.println("Produto removido com sucesso.");

            return updateProduct;
        } else {
            System.out.println("Produto não encontrado.");
            return null;
        }
    }


    public static void addQuantityToProduct() {
        System.out.println("Digite o id do produto: ");
        int productId = sc.nextInt();
        sc.nextLine();
        Product productUpdate = findProductById(productId);
        if (productUpdate == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        int quantity = getIntegerInput("Digite a Quantidade  a ser incrementado: ");
        int currentQuantity = productUpdate.getQuantity();
        int newQuantity = currentQuantity + quantity;
        productUpdate.setQuantity(newQuantity);
        System.out.println("Quantidade adicionada com sucesso! Total: " + newQuantity);
    }

    private static Double getPrice(String priceStr) {
        Double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            System.out.println("Formato de preço inválido. Por favor, insira um número válido.");
            return null;
        }
        return price;
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
}