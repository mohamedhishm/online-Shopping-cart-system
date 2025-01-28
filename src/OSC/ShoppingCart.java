package OSC;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private String name;
    private String email;
    private String address;
    private long phone;
    private List<Product> shoppingCart;

    // Constructor
    public ShoppingCart(String name, String email, String address, long phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.shoppingCart = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone() {
        return phone;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    // Add a product to the cart
    public void addProduct(Product product) {
        shoppingCart.add(product);
    }

    // Calculate total cost of products in the cart
    public double calculateTotal() {
        double total = 0;
        for (Product product : shoppingCart) {
            total += product.getPrice();
        }
        return total;
    }

    // Display cart details
    public void showCartDetails() {
        System.out.println("Shopping Cart:");
        for (Product product : shoppingCart) {
            System.out.println(" - " + product.getName() + " ($" + product.getPrice() + ")");
        }
    }

    // Simulate payment process
    public void makePayment(double amount) {
        System.out.println("Payment of $" + amount + " has been processed successfully!");
    }
}
