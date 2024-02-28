package model;

import utils.Utils;

public class Product {


    private static int count =1;

    private int id;

    private String nameProduct;
    private String description;
    private  double price;
    private int quantity;

    public Product(String nameProduct, String description, double price, int quantity) {
        this.id = count; // adiciona o Id
        this.nameProduct = nameProduct;
        this.description = description; // Inicializa a descrição corretamente
        this.price = price;
        this.quantity = quantity;
        Product.count+=1;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }


    public String getName() {
        return nameProduct;
    }

    public void setName(String name) {
        this.nameProduct = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product \n" +
                "id: " + this.getId() +
                ", Name: '" + this.getName() + '\'' +
                ", Description: '" + getDescription() + '\'' +
                ", Price: " + Utils.doubleToString(this.getPrice()) +
                ", Quantity: " + this.getQuantity();
    }

}
