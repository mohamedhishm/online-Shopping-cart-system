package OSC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private List<Product> products = new ArrayList<>();
    private List<ShoppingCart> customers = new ArrayList<>();

    public Main() {
        load();

        JFrame frame = new JFrame("Online Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(Color.decode("#09122C"));
        frame.setLayout(new BorderLayout());
        // Add WindowListener to save data on exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                save(); // Save data when the application exits
            }
        });

        // Set a supermarket background image
        JLabel background = new JLabel(new ImageIcon("supermarket_background.jpg"));
        background.setLayout(new BorderLayout());
        frame.add(background);

        // Title Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#09122C"));
        JLabel title = new JLabel("Online Shopping Cart System");
        title.setForeground(Color.decode("#FFFBCA"));
        title.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(title);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(Color.decode("#09122C"));
        buttonPanel.setOpaque(false); // Transparent panel

        JButton addProductButton = createStyledButton("Add Product");
        JButton addCustomerButton = createStyledButton("Add Customer");
        JButton viewDetailsButton = createStyledButton("View Customer Details");

        buttonPanel.add(addProductButton);
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(viewDetailsButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Add event listeners
        addProductButton.addActionListener(e -> openAddProductDialog(frame));
        addCustomerButton.addActionListener(e -> openAddCustomerDialog(frame));
        viewDetailsButton.addActionListener(e -> openCustomerDetailsDialog(frame));

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.decode("#FFFBCA"));
        button.setBackground(new Color(135, 35, 65)); // #872341
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(190, 49, 68)); // #BE3144
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(135, 35, 65)); // #872341
            }
        });

        return button;
    }

    private void openAddProductDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Add Product", true);
        dialog.setSize(400, 300);
        dialog.getContentPane().setBackground(Color.decode("#09122C"));
        dialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setForeground(Color.decode("#FFFBCA"));
        JTextField idField = new JTextField();
        idField.setBackground(Color.decode("#E17564"));
        idField.setForeground(Color.decode("#FFFBCA"));

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setForeground(Color.decode("#FFFBCA"));
        JTextField nameField = new JTextField();
        nameField.setBackground(Color.decode("#E17564"));
        nameField.setForeground(Color.decode("#FFFBCA"));

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setForeground(Color.decode("#FFFBCA"));
        JTextField priceField = new JTextField();
        priceField.setBackground(Color.decode("#E17564"));
        priceField.setForeground(Color.decode("#FFFBCA"));

        JButton addButton = createStyledButton("Add Product");
        dialog.add(idLabel);
        dialog.add(idField);
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(priceLabel);
        dialog.add(priceField);
        dialog.add(new JLabel());
        dialog.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                products.add(new Product(id, name, price));
                JOptionPane.showMessageDialog(dialog, "Product added successfully!");
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input. Please try again.");
            }
        });

        dialog.setVisible(true);
    }

    private JLabel createCustomLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#FFFBCA"));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void openAddCustomerDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Add Customer", true);
        dialog.setSize(400, 400);
        dialog.getContentPane().setBackground(Color.decode("#09122C"));
        dialog.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel nameLabel = createCustomLabel("Customer Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = createCustomLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel addressLabel = createCustomLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel phoneLabel = createCustomLabel("Phone:");
        JTextField phoneField = new JTextField();

        JButton addButton = createStyledButton("Add Customer");
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(emailLabel);
        dialog.add(emailField);
        dialog.add(addressLabel);
        dialog.add(addressField);
        dialog.add(phoneLabel);
        dialog.add(phoneField);
        dialog.add(new JLabel());
        dialog.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                long phone = Long.parseLong(phoneField.getText());
                ShoppingCart customer = new ShoppingCart(name, email, address, phone);

                addProductsToCart(customer);
                customers.add(customer);
                JOptionPane.showMessageDialog(dialog, "Customer added successfully!");
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input. Please try again.");
            }
        });

        dialog.setVisible(true);
    }

    Date date = new Date();
    private void addProductsToCart(ShoppingCart customer) {
        JDialog dialog = new JDialog((JFrame) null, "Add Products to Cart", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(products.size() + 2, 1));

        JLabel instructionLabel = new JLabel("Select products to add to the cart:");
        dialog.add(instructionLabel);

        List<JCheckBox> checkBoxes = new ArrayList<>();
        for (Product product : products) {
            JCheckBox checkBox = new JCheckBox(product.getName() + " ($" + product.getPrice() + ")");
            checkBoxes.add(checkBox);
            dialog.add(checkBox);
        }

        JButton confirmButton = createStyledButton("Confirm");
        dialog.add(confirmButton);

        confirmButton.addActionListener(e -> {
            for (int i = 0; i < products.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    customer.addProduct(products.get(i));
                }
            }
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    private void openCustomerDetailsDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Customer Details", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new BorderLayout());

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        for (ShoppingCart customer : customers) {
            detailsArea.append(" \n                             ***Customer Details***\n\n");
            detailsArea.append("Name: " + customer.getName() + "      |       Email: " + customer.getEmail() + "\n");
            detailsArea.append("Address: " + customer.getAddress() + "      |       Phone: " + customer.getPhone() + "\n");
            detailsArea.append("Products:\n");

            for (Product product : customer.getShoppingCart()) {
                detailsArea.append(" - " + product.getName() + " (" + product.getPrice() + " $)\n");
            }

            detailsArea.append("Total: $" + customer.calculateTotal() + "\n");
            detailsArea.append("\n");
            detailsArea.append("_________________________________________________________________\n");
            detailsArea.append("                 _________________________________________\n");
        }

        for (ShoppingCart customer : customers) {
            System.out.println("\n       \t   \t           ***Data***                        ");
            SimpleDateFormat sdf = new SimpleDateFormat("\n' Time:' hh:mm:ss a");
            System.out.println(sdf.format(date));
            System.out.printf("%1$s %2$tA %2$te-%2$tm-%2$tY", " Today is:", date);
            System.out.printf("      |    time: %tH:%tM:%tS", date, date, date);
        }
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        dialog.add(scrollPane, BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.data"))) {
            oos.writeObject(products);
            oos.writeObject(customers);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.data"))) {
            products = (List<Product>) ois.readObject();
            customers = (List<ShoppingCart>) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}





/*   Data

("E-2020", "Laptop",1200.00);
("C-7030", "T-Shirt",20.00);
("E-1100", "Phone",800.00);
("E-8888", "Headphones",150.00);

("Mohamed Yasser", "mohamed@example.com","322-Sporting-Alex",1275421502 );
("Hisham Sherif", "hisham@example.com","45-Mahrosa-Alex",1226605496);
("Saad Basuiony", "saad@example.com","22-Sherton-Cairo",1140432235);

*/
