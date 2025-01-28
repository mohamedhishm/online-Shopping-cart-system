Online Shopping Cart System
Overview
This is a simple Online Shopping Cart System implemented in Java using Swing for the graphical user interface (GUI) and Object Serialization for saving and loading data. It allows users to add products, manage customer information, and view the shopping cart details of each customer. The program also ensures data persistence by saving and loading the data to/from a file.

Features
Add Products: Users can add products to the system by providing product ID, name, and price.
Add Customers: Users can add customers with their name, email, address, and phone number.
Manage Shopping Cart: Customers can add products to their shopping cart and view the total price of their purchases.
View Customer Details: Display customer information along with their shopping cart details and the total price.
Data Persistence: All products and customer details are saved to a file and can be loaded when the application is reopened.
Custom Styling: Buttons and UI components are styled with custom colors, fonts, and hover effects to enhance user experience.
Requirements
Java 8 or higher.
A Java IDE or command-line environment to compile and run the program.
How to Run
Clone the repository or download the project files.
Compile the Java files.
Run the Main class to launch the application.
bash
Copy
Edit
javac Main.java
java Main
Usage
Upon running the application, the main window will appear with the following options:

Add Product: Opens a dialog to input product details and add them to the system.
Add Customer: Opens a dialog to input customer details and allows selecting products to add to the customer's shopping cart.
View Customer Details: Opens a dialog showing all customers, their details, and the products in their shopping cart along with the total amount.
Example:
Add Product:

Product ID: P001
Product Name: Laptop
Price: 999.99
This will add a product to the system.

Add Customer:

Name: Mohamed Hisham
Email: mohamed@example.com
Address: 332-Sporting-Alex
Phone: 1275421502
This will add a new customer and allow selecting products for the shopping cart.

View Customer Details:

Displays customer information and a list of products in their cart with the total price.
Saving and Loading Data
The application saves product and customer data to a file (save.data) on exit and loads it on startup. This ensures that all data is persisted between sessions.

Saving Data:
The system saves the current state of the product list and customer list every time the application is closed.

Loading Data:
Upon starting the application, the system attempts to load the saved data. If no saved data is found, it will start with a fresh state.

Files
Main.java: Main class with the GUI and core logic of the shopping cart system.
Product.java: Class representing a product with ID, name, and price.
ShoppingCart.java: Class representing a customer’s shopping cart with customer details and purchased products.
save.data: File used to persist products and customers' data.
Contributing
Feel free to fork the project, make changes, and submit pull requests for any improvements, features, or bug fixes.

License
This project is open-source and available under the MIT License.

Contact
For any questions, feel free to reach out to [mohamedabnhisham@gmail.com].
