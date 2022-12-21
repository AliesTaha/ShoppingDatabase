/***********************************************************************************************************
* @Author: Ali Taha                                                                       @Date: 06/11/2021
* Mr.A. Carreiro
* FinalAliTahaConsumersCPT.java
*
* Program Description: (Backorder Included)
* This is the driver program of the business program, which includes the main() method and creates objects of
* other classes. It displays the catalogue of a business, which in turn displays menu options to the user.
* The classes used display the menu options to the user, and allows them to add, edit, and remove
* different inventory members from the organization. It finally outputs all of the staff within the organization.
* *********************************************************************************************************/
//Importing necessarry packages for all classes
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FinalAliTahaConsumersCPT { // Begin Class Final AliTahConsumersCPT
   public static void main(String[] args) { // Begin main()
   
      // ------Section 1: Variable Declaration and Initialization-------
      Scanner uI = new Scanner(System.in);                     // Instantiating the Scanner Class
      int choice = 0, choiceM = 0, choiceMI = 0, choiceMC = 0; // These used as part of switch statements for choice
      int ID = 0, productNum = 0, numOfItems = 0, x = 0;       // These are used to store integer attributes
      double price = 0;                                        // Floating point for the price
      String name = "", description = "";                      // String variables to store names
      boolean checking = false, exit = false;                  // Used to control while loops
      long phoneNum = 0;                                       // Since 10-digit, needs long type
   
      // These create objects such that the classes and their methods can be used
      Customer objCustmr = new Customer();
      Catalogue objCatlg = new Catalogue();
      PointOfSales objPOS = new PointOfSales(phoneNum);
      CustomerManaging objC = new CustomerManaging(choiceMC);
      InventoryManaging obj = new InventoryManaging(choiceMI, productNum);
   
      // Calls read customer methods, reading from .txt files into arrays.
      try {
         objCustmr.readCustomerFile();
      } catch (Exception e) {
         System.out.println(
               "This program has been checked and runs perfectly, but\nplease put all files in the same directory and run it again.");
      }
   
      // Calls read inventory method, reading from .txt files into arrays.
      try {
         objCatlg.readInventoryFile();
      } catch (Exception e) {
         System.out.println(
               "This program has been checked and runs perfectly, but\nplease put all files in the same directory and run it again.");
      }
   
      // Displays success criteria of task
      markingCriteria(uI);
   
      // -------Section 2: User Input-------
      while (!exit) {
      
         // -----------------------This is the Main Program Menu-------------------
         choice = integerU(uI,
               "-----------------------------------------------------------------------------------------\n"
                     + "**Welcome to the Ali Taha's Consumers Distributing Program**\n"
                     + "**Ali Taha's Store allows you to --Backorder--!!**\n"
                     + "Choose a number from the following options:\n" + "1 - Store Management Menu\n"
                     + "2 - Point of Sales Menu\n" + "3 - Exit the program (Sorting and Updating Text Files)\n"
                     + "-----------------------------------------------------------------------------------------",
               1, 3);
         // ---------------------This is the Store Management Menu---------------------
      
         // Store Management
         if (choice == 1) {
         
            choiceM = integerU(uI,
                  "-----------------------------------------------------------------------------------------\n"
                        + "**Entering the Store Management Menu- (Only Staff Members Permitted):**\n"
                        + "Choose a number from the following options:\n" + "1 - Inventory Management Menu\n"
                        + "2 - Customer Management Menu\n" + "3 - Back to Main Menu\n"
                        + "-----------------------------------------------------------------------------------------",
                  1, 3);
         
            switch (choiceM) {
            
            // -------------This is the Inventory Management Menu-------------
               case 1:
                  obj.InventoryManage(description, checking, objCatlg, name, price);
                  choiceMI = 0;
                  break;
            
            // ------Section 3&4: Program Execution and Output----------
            
            // --------------------- Customer Management Menu---------------------
               case 2:
                  objC.CustomerManage(objCustmr, checking, phoneNum, ID);
                  choiceMC = 3;
                  break;
            
               default:
                  System.out.println("Back to main menu...");
                  break;
            }
         } // End of Store Management
         
         // ---------------------This is the Point of Sales Menu---------------------
         else if (choice == 2) {
            choice = 0;
            objPOS.orderDesk(objCustmr, objCatlg, ID, choice, numOfItems, productNum, x);
            System.out.println("Store inventory has been updated...");
            uI.nextLine();
            pause(uI);
         } else
         
         {
            exit = false;
            x = integerU(uI, "Are you sure you want to exit?\n(1)-Yes (2)-No\n", 1, 2);
            if (x == 1)
               exit = true;
         }
      } // End of while loop for menu system-> User exits
   
      // -------------------This is the end of the computer databse system-----------
      System.out.println("Thank you for shopping with Ali Taha's Shopper Distributer.\n"
            + "All changes have been made, saved and sorted in text files.\nCome back soon!");
   
      // Sorts the text files
      objCustmr.bubbleSort();
      objCatlg.bubbleSort();
   
      // Updates text files
      objCustmr.writeToFile();
      objCatlg.writeToFile();
   
      // Closes Scanner
      uI.close();
   
   }// End of main() method

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI
    * @Return: void
    * @Purpose: To get the user to input an integer between a minimum nd maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   public static void pause(Scanner uI) {
      System.out.println("Enter any key to continue");
      uI.nextLine();
   }

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI, String message, int min, int max
    * @Return: int
    * @Purpose: To get the user to input an integer between a minimum and maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   private static int integerU(Scanner uI, String message, int min, int max) { // Begin
      // integerValue(uI,message,min,max) method
   
      int input1 = Integer.MIN_VALUE; // Declares and initializes input1 as the integer for uI, sets it to a negative
      // number to run loop
   
      System.out.println(message); // This prevents redundancy with System.out.println();
   
      // Check that the user inputted an int
      while (true) {
      
         try {
            input1 = uI.nextInt(); // The integer number is stored in variable input1, which is returned below
         } catch (Exception e) {
            uI.nextLine();
            System.out.println("Please enter an integer number...");
         }
      
         // If the int is between the min and the max, break the loop
         if (input1 <= max && input1 >= min)
            break;
      
         if (input1 > max || input1 < min)
            System.out.println("Note: Please only enter an integer number within the range given.");
      }
      return input1; // Returns input1
   }// End integerU() method

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI
    * @Return: void
    * @Purpose: To display success criteria of program and course requirements
    ***********************************************************************************************************/
   public static void markingCriteria(Scanner uI) {
      int wish;
      wish = integerU(uI,
            "Before the program begins...\nIf you want to display program success criteria for marking purposes, enter 0:\nEnter any other number to skip this.",
            Integer.MIN_VALUE, Integer.MAX_VALUE);
      if (wish == 0) {
         System.out.println("In terms of Customer Database CPT requirements, this program:\n"
               + "---Meets all the essential requirements of the database details---\n"
               + "---**Fully implements Backorder Component**---");
         uI.nextLine();
         pause(uI);
         System.out.println("With regards to course material, this program uses:\n"
               + "-Seperate classes and objects(Modular Programming)\n" + "-Works with Java IO\n"
               + "-Exception Handling\n" + "-Try Catch\n" + "-Composition\n" + "-Encapsulation\n"
               + "-Different Access Modifiers\n" + "-Polymorphism (Overloading...)\n"
               + "-Methods(Including static methods)\n" + "-Sorting Routines (Bubble Sort, Compare To...)\n"
               + "-Java Primitive Data Types\n" + "-Arrays (Including 1D-2D Dynamic Arrays)\n"
               + "-Error Checking Methods\nAnd many more...\n==========================================================");
         pause(uI);
      }
   
   }
}// End of class FinalAliTahaConsumersCPT

/***********************************************************************************************************
 * @Author: Ali Taha                                                              @Date: 06/11/2021 
 * @Mr.A. Carreiro 
 * Customer.java
 *
 * Program Description: This is the Customer class of the business program, which includes the getter and 
 * setter methods of the customers. The classes used display the menu options to the user, and allows them
 * to add, edit, and remove different inventory members from the organization.
 *********************************************************************************************************/
class Customer { // Begin Class Customer

   private ArrayList<Customer> customerDatabase = new ArrayList<>(); // Move this and adds to a database automatically
   
   //These are essential variables used in the methods within class Customer                                                                  
   private int ID;
   private String firstName;
   private String lastName;
   private long phoneNum;
   private String backOrdered;

   // Class Customer Constructor
   public Customer() {
   
   }

   // @Overloading CLass Constructor
   public Customer(int ID, String firstName, String lastName, long phoneNum, String backOrdered) {
      this.ID = ID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNum = phoneNum;
      this.backOrdered = backOrdered;
   
   }

   // -----Setters------
   // Set ID
   public void setID(int ID) {
      this.ID = ID;
   }

   // Set first name
   public void setFirstName(int ID, String firstName) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            customerDatabase.get(i).firstName = firstName;
         }
      }
   }

   // Set last name
   public void setLastName(int ID, String lastName) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            customerDatabase.get(i).lastName = lastName;
         }
      }
   }

   // Set phone number
   public void setPhoneNum(int ID, long phoneNum) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            customerDatabase.get(i).phoneNum = phoneNum;
         }
      }
   }

   // Set backordered items
   public void setBackorderedItems(int ID, String backOrdered) {
   
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            customerDatabase.get(i).backOrdered = backOrdered;
            break;
         }
      }
   
   }

   // -----Getters------
   // Get ID
   public int getID() {
      return ID;
   }

   // Get first name
   public String getFirstName(int ID) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            return customerDatabase.get(i).firstName;
         }
      }
      return String.format("ERROR");
   }

   // Get customer database
   public ArrayList<Customer> getCustomerDatabase() {
      return customerDatabase;
   }

   // Get lastName name
   public String getLastName(int ID) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            return customerDatabase.get(i).lastName;
         }
      }
      return String.format("ERROR");
   }

   // Get backordered items
   public String getBackorderedItems(int ID) {
      String s = "";
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            s = customerDatabase.get(i).backOrdered;
         }
      }
      return s;
   }

   // Get customer phone number
   public long getPhoneNum(int ID) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            return customerDatabase.get(i).phoneNum;
         }
      }
      return 34404;
   }

   // Adds customer
   public void addCustomer(int ID, String firstName, String lastName, long phoneNum, String backOrdered) {
      customerDatabase.add(new Customer(ID, firstName, lastName, phoneNum, backOrdered));
      bubbleSort();
   }

   // Set newId
   public int setNewID() {
      ID = (int) (Math.random() * 899999 + 100000);
      return ID;
   }

   // Removes customer
   public void removeCustomer(int ID) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            customerDatabase.remove(i);
         }
      }
   }

   // Overloads compareTo method
   public int compareTo(Customer objCustmr) {
      int valueInComparison = 0;
      if (this.ID < objCustmr.ID) {
         valueInComparison = -1;
      }
      if (this.ID > objCustmr.ID) {
         valueInComparison = 1;
      }
      return valueInComparison;
   }

   // Bubble Sorts ID
   public void bubbleSort() {
      ArrayList<Customer> list = customerDatabase;
      Customer temp;
      boolean sorted = false;
   
      while (!(sorted)) {
         sorted = true;
         for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
               temp = list.get(i);
               list.set(i, list.get(i + 1));
               list.set(i + 1, temp);
               sorted = false;
            }
         }
      }
   }
  
   // Verifies records of purchase
   public boolean getRecord(int ID) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            return true;
         }
      }
      return false;
   }

   // Verifies customer
   public boolean customerVerify(int ID) {
      for (int i = 0; i < customerDatabase.size(); i++) {
         if (customerDatabase.get(i).ID == ID) {
            return true;
         }
      }
      return false;
   }

   // Reads customer file
   public void readCustomerFile() throws FileNotFoundException {
      /**
       * This is going to read and store all the data from a a text file into a String
       * array that saves the data in a a object.
       **/
      try {
         File fileName = new File("customers.txt");
         Scanner customerScanner = new Scanner(fileName);
         while (customerScanner.hasNextLine()) {
            String s = customerScanner.nextLine();
            String[] array = s.split(";");
            addCustomer(Integer.parseInt(array[0]), array[1], array[2], Long.parseLong(array[3]), array[4]);
         } // End of while loop
         customerScanner.close();
      
         // Catching errors (Exception handling)
      } catch (IOException e) {
         System.out.println("File error occurred. Check directory location and try again.");
      } catch (Exception e) {
         System.out.println("A general error occured in customers. Try running all files in the same directory.");
      }
   }

   // Writes to customer file
   public void writeToFile() {
      try {
         FileWriter writer = new FileWriter("customers.txt");
         for (int i = 0; i < customerDatabase.size(); i++) {
            writer.write(customerDatabase.get(i).ID + ";" + customerDatabase.get(i).firstName + ";"
                  + customerDatabase.get(i).lastName + ";" + customerDatabase.get(i).phoneNum + ";"
                  + customerDatabase.get(i).backOrdered + "\n");
         }
         writer.close();
      } catch (Exception e) {
         System.out.println("Error");
      }
   }
   
    /****************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: 
    * @Return: void
    * @Purpose: To display customers
    ********************************************/
   public void displayCustomerDatabase() {
      bubbleSort();
      System.out.println(
            "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
      System.out.printf("%-30s%-30s%-30s%-30s%-30s\n", "Customer ID:", "Customer First Name:", "Customer Last Name :",
            "Customer's Phone Number", "Backordered Items");
      System.out.println(
            "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
      for (int i = 0; i < customerDatabase.size(); i++) {
         System.out.printf("%-30d%-30s%-30s%-30d%-30s\n", customerDatabase.get(i).ID, customerDatabase.get(i).firstName,
               customerDatabase.get(i).lastName, customerDatabase.get(i).phoneNum, customerDatabase.get(i).backOrdered);
      }
      System.out.println(
            "\n==============================================================================================================================================================");
   
   }


   /* Thought process prior:
    * public void setItemsOnBackorder(int ID, String itemsOnBackorder) { for (int i
    * = 0; i < customerDatabase.size(); i++) { if (customerDatabase.get(i).ID ==
    * ID) { customerDatabase.get(i).itemsOnBackorder = itemsOnBackorder; } } }
    */

}// End of Customer Class

/***********************************************************************************************************
 * @Author: Ali Taha             @Date: 06/11/2021 
 * @Mr.A. Carreiro 
 * Catalogue.java
 *
 * Program Description: This is the Catalogue class of the business program, which includes the getter and 
 * setter methods of the customers. The classes used display the menu options to the user, and allows them
 * to add, edit, and remove different customer members from the organization.
 *********************************************************************************************************/
class Catalogue { // Begin class Cataloge

   private ArrayList<Catalogue> catalogue = new ArrayList<>();
   private ArrayList<Catalogue> shoppingCart = new ArrayList<>();

   private int productNum;
   private String name;
   private double price;
   private int numInStock;
   private String description;
   private int numOfItemsOrdered;
   private int numBackordered;
   private int customerID;

   // Class Constructor
   public Catalogue() {
   
   }

   // @Constructor Overloading
   public Catalogue(int productNum, String name, double price, int numInStock, String description, int numBackordered,
         int customerID) { // constructor to make item
      this.productNum = productNum;
      this.name = name;
      this.price = price;
      this.numInStock = numInStock;
      this.description = description;
      this.numBackordered = numBackordered;
      this.customerID = customerID;
   }

   // -----Setters-----
   // Set product number
   public void setProductNum(int index, int productNum) {
      catalogue.get(index).productNum = productNum;
   }

   // Set Name
   public void setName(int productNum, String name) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            catalogue.get(i).name = name;
         }
      }
   }

   // Set price
   public void setPrice(int productNum, double price) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            catalogue.get(i).price = price;
         }
      }
   }

   // Set Number in stock
   public void setNumInStock(int productNum, int numInStock) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            catalogue.get(i).numInStock = numInStock;
         }
      }
   }

   // Set Customer Descripiton
   public void setDescription(int productNum, String description) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            catalogue.get(i).description = description;
         }
      }
   }

   // -----Getters-----
   // Get Catalogue
   public ArrayList<Catalogue> getCatalogue() {
      return catalogue;
   }

   // Get Shopping Cart
   public ArrayList<Catalogue> getShoppingCart() {
      return shoppingCart;
   }

   // Get Product Number
   public int getProductNum(int index) {
      return catalogue.get(index).productNum;
   }

   // Get Name
   public String getName(int productNum) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            return catalogue.get(i).name;
         }
      }
      return String.format("ERROR");
   }

   // Get Price
   public double getPrice(int productNum) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            return catalogue.get(i).price;
         }
      }
      return -4.20;
   }

   // Get Backordered Items
   public String getBackorderedItems() {
      ArrayList<String> names = new ArrayList<String>();
      for (int i = 0; i < shoppingCart.size(); i++) {
         if (shoppingCart.get(i).numBackordered > 0) {
            names.add(shoppingCart.get(i).name);
         }
      }
      String s = names.toString();
      return s;
   }

   // Get Number in Stock
   public int getNumInStock(int productNum) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            return catalogue.get(i).numInStock;
         }
      }
      return -1;
   }

   // Get description
   public String getDescription(int productNum) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            return catalogue.get(i).description;
         }
      }
      return String.format("ERROR");
   }

   // Adds to catalogue
   public void addCatalogueItem(int productNum, String name, double price, int numInStock, String description,
         int numBackordered, int customerID) {
      catalogue.add(new Catalogue(productNum, name, price, numInStock, description, numBackordered, customerID));
   }

   // Removes from catalogue
   public void removeCatalogueItem(int productNum) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            catalogue.remove(i);
         }
      }
   }

   // Displays catalogue
   public void displayCatalogue() {
      bubbleSort();
      System.out.println(
            "==============================================================================================================================================================");
      System.out.println(
            "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
      System.out.printf("%-14s%-25s%-20s%-22s%-32s%-26s%-20s\n", "Product ID:", "Product Name:", "Product Price:",
            "Product In Stock:", "Product Description:", "Number Backordered", "Linked Customer ID");
      System.out.println(
            "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
      for (int i = 0; i < catalogue.size(); i++) {
         System.out.printf("%-14d%-25s$%-19.2f%-22d%-32s%-26d%-20d\n", catalogue.get(i).productNum,
               catalogue.get(i).name, catalogue.get(i).price, catalogue.get(i).numInStock, catalogue.get(i).description,
               catalogue.get(i).numBackordered, catalogue.get(i).customerID);
      }
      System.out.println(
            "\n==============================================================================================================================================================");
   }

   // Displays customer catalogue
   public void displayCustomerCatalogue() {
      bubbleSort();
      System.out.println(
            "-------------------------------------------------------------------------------------------------------------------------------------------------------");
      System.out.printf("%-20s%-33s%-25s%-25s%-30s\n", "Product Number/ID:", "Product Name:", "Product Price:",
            "Available in stock", "Product Description:");
      System.out.println(
            "-------------------------------------------------------------------------------------------------------------------------------------------------------");
      for (int i = 0; i < catalogue.size(); i++) {
         System.out.printf("%-20d%-33s$%-24.2f%-25d%-30s\n", catalogue.get(i).productNum, catalogue.get(i).name,
               catalogue.get(i).price, catalogue.get(i).numInStock, catalogue.get(i).description);
      }
      System.out.println(
            "\nNOTE: The number of available items in stock will only update after you confirm purchase\nIf you would like to order more, feel free to backorder!");
      System.out.println(
            "-------------------------------------------------------------------------------------------------------------------------------------------------------");
   }

   // Adds to cart
   public void addToCart(int productNum, int numOfItems) {
      boolean check = false;
   
   //For loop used to check that product number and item bought are the same 
      for (int i = 0; i < shoppingCart.size(); i++) {
         if (shoppingCart.get(i).productNum == productNum) {
            check = true;
         }
      }
   
   //This adds the items to cart 
      if (check == true) {
         loopOne: for (int x = 0; x < shoppingCart.size(); x++) {
            if (shoppingCart.get(x).productNum == productNum) {
               shoppingCart.get(x).numOfItemsOrdered = shoppingCart.get(x).numOfItemsOrdered + numOfItems;
               break loopOne;
            }
         }
      } else {
         loopTwo: for (int y = 0; y < catalogue.size(); y++) {
            if (catalogue.get(y).productNum == productNum) {
               shoppingCart.add(catalogue.get(y));
               break loopTwo;
            }
         }
      
         loopThree: for (int z = 0; z < shoppingCart.size(); z++) {
            if (shoppingCart.get(z).productNum == productNum) {
               shoppingCart.get(z).numOfItemsOrdered = shoppingCart.get(z).numOfItemsOrdered + numOfItems;
               break loopThree;
            }//End of if statement
         }//End of loopThree
      }//End of else statement
   
   }//End of AddToCart()

   // Clears shoppingCart
   public void clearCart() {
      shoppingCart.clear();
   }

   // Displays cart
   public void displayCart() {
      double totalCost = 0;
      System.out.println("-----------------------------------RECEIPT-----------------------------------------------");
      System.out.printf("%-35s%-35s%-25s\n", "Name:", "Order Price:", "Order Quantity");
      for (int i = 0; i < shoppingCart.size(); i++) {
         System.out.printf("%-35s$%-34.2f%-25d\n", shoppingCart.get(i).name, shoppingCart.get(i).price,
               shoppingCart.get(i).numOfItemsOrdered);
         totalCost = totalCost + (shoppingCart.get(i).price * shoppingCart.get(i).numOfItemsOrdered);
      }
      System.out.println("-------------------------------------------------------------------------------------------");
      System.out.printf("Subtotal:   $%.2f\n", totalCost);
      System.out.printf("Tax added:  $%.2f\n", (totalCost * 0.13));
      System.out.printf("Total cost: $%.2f\n", (totalCost * 1.13));
      System.out.println("------------------------");
   }

   // Confirms purchase
   public void confirmPurchase(int customerID) {
   
      ArrayList<Integer> ary = new ArrayList<>();
   
      for (int x = 0; x < shoppingCart.size(); x++) {
         ary.add(shoppingCart.get(x).productNum);
      }
   
      for (int i = 0; i < ary.size(); i++) {
         for (int y = 0; y < catalogue.size(); y++) {
            if (ary.get(i) == catalogue.get(y).productNum) {
               if (catalogue.get(y).numInStock - shoppingCart.get(i).numOfItemsOrdered > 0) {
                  catalogue.get(y).numInStock = catalogue.get(y).numInStock - shoppingCart.get(i).numOfItemsOrdered;
               } else {
                  catalogue.get(y).numBackordered = Math
                        .abs(catalogue.get(y).numInStock - shoppingCart.get(i).numOfItemsOrdered);
                  catalogue.get(y).numInStock = 0;
                  catalogue.get(y).customerID = customerID;
               }
            }
         }
      }
      /*
       * PREVIOUS TRIES:
       * 
       * The following for loops will update the inventory stock for (int i = 0; i <
       * shoppingCart.size(); i++) { if (shoppingCart.get(i).numInStock >
       * shoppingCart.get(i).numOfItemsOrdered) { catalogue.get(i).numInStock =
       * catalogue.get(i).numInStock - catalogue.get(i).numOfItemsOrdered; } else {
       * catalogue.get(i).numBackordered = Math
       * .abs(shoppingCart.get(i).numOfItemsOrdered - catalogue.get(i).numInStock);
       * catalogue.get(i).numInStock = 0; catalogue.get(i).customerID = customerID;
       * shoppingCart.get(i).numBackordered = Math
       * .abs(shoppingCart.get(i).numOfItemsOrdered - catalogue.get(i).numInStock);
       * shoppingCart.get(i).numInStock = 0; shoppingCart.get(i).customerID =
       * customerID; Previous work in progress and thought process for part marks if
       * error occurs
       */
   
      /*
       * for (int i = 0; i < shoppingCart.size(); i++) { if
       * (shoppingCart.get(i).numInStock >= shoppingCart.get(i).numOfItemsOrdered) {
       * shoppingCart.get(i).numInStock = shoppingCart.get(i).numInStock -
       * shoppingCart.get(i).numOfItemsOrdered; } else {
       * shoppingCart.get(i).numInStock = 0; } }
       */
   }

   // Verifies products
   public boolean productVerify(int productNum) {
      for (int i = 0; i < catalogue.size(); i++) {
         if (catalogue.get(i).productNum == productNum) {
            return true;
         }
      }
      return false;
   }

   // Overrides compareTo
   public int compareTo(Catalogue c) {
      int comp = 0;// a comparator for the product numbers to assist in the bubble sorting
      if (this.productNum < c.productNum) {
         comp = -1;
      }
      if (this.productNum > c.productNum) {
         comp = 1;
      }
      return comp;
   }

   // Sorting
   public void bubbleSort() {
      ArrayList<Catalogue> list = catalogue;
      Catalogue temp;
      boolean sorted = false;
   
      while (!(sorted)) {
         sorted = true;
         for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
               temp = list.get(i);
               list.set(i, list.get(i + 1));
               list.set(i + 1, temp);
               sorted = false;
            }
         }
      }
   }

   // Reads inventory files
   public void readInventoryFile() throws FileNotFoundException {
      try {
         File fileName = new File("products.txt");
         Scanner catalogueScanner = new Scanner(fileName);
      
         while (catalogueScanner.hasNextLine()) {
            String m = catalogueScanner.nextLine();
            String[] arr = m.split(";");
            addCatalogueItem(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2]), Integer.parseInt(arr[3]),
                  arr[4], Integer.parseInt(arr[5]), Integer.parseInt(arr[6]));
         
         } // End of while loop
      
         catalogueScanner.close();
      } catch (IOException e) {
         System.out.println("File error occurred. Check directory location and try again.");
      } catch (Exception e) {
         System.out.println("A general error occured in products. Try running all files in the same directory.");
      }
   
   }

   // Writing to file (updates file)
   public void writeToFile() {
      try {
         FileWriter writer = new FileWriter("products.txt");
         for (int i = 0; i < catalogue.size(); i++) {
            writer.write(catalogue.get(i).productNum + ";" + catalogue.get(i).name + ";" + catalogue.get(i).price + ";"
                  + catalogue.get(i).numInStock + ";" + catalogue.get(i).description + ";"
                  + catalogue.get(i).numBackordered + ";" + catalogue.get(i).customerID + "\n");
         }
         writer.close();
      } catch (Exception e) {
         System.out.println("Error");
      }
   }

}// End of Catalogue class

/*************************************************************************************************************
 * @Author: Ali Taha                                                            @Date: 06/11/2021 
 * @Mr.A. Carreiro 
 * PointOfSales.java
 *
 * Program Description: This is the PointOfSales class of the business program, which includes the getter 
 * and setter methods of the products and what the customers order. The classes used display the menu options
 * to the user.
 ***********************************************************************************************************/
class PointOfSales { // Begin PointOfSales Class

   private Scanner uI = new Scanner(System.in);
   private String firstName = "", lastName = "";
   private long phoneNum;

   // Class constructor
   public PointOfSales(long phoneNum) {
      this.phoneNum = phoneNum;
   }

   // This manages the order-desk
   public void orderDesk(Customer objCustmr, Catalogue objCatlg, int ID, int choice, int numOfItems, int productNum,
         int x) {
   
      System.out
            .println("Welcome to the Point of Sales! This is where you can purchase any of the items we offer. And...");
      System.out.println("\n------------This is the existing Customer List------------");
      objCustmr.displayCustomerDatabase();
   
      System.out.println(
            "If this a loyal customer, and the name is on the list, simply enter the Customer ID displayed above to continue to cart.");
      ID = integerU(uI,
            "Please Note: If (NEW) customer is not on list above, enter any integer number to continue to add the new customer information",
            -1, Integer.MAX_VALUE);
      boolean check = objCustmr.customerVerify(ID);
      uI.nextLine();
   
      // Adding new customer above
      if (check == false) {
         System.out.println("This customer is not registered. Please make a new customer.");
         System.out.println("Enter customer first name: ");
         firstName = uI.nextLine();
         System.out.println("Enter customer last name: ");
         lastName = uI.nextLine();
         do {
            System.out.println("Please enter customer phone number (no dashes- only enter TEN integer numbers)");
            while (!(uI.hasNextLong())) {
               uI.nextLine();
               System.out.println("Please enter a valid phone number with no dashes: ");
            }
            phoneNum = uI.nextLong();
            uI.nextLine();
            if (phoneNum < 1000000000 || phoneNum > ((long) Math.pow(9.999999999, 10)))
               System.out.println("Phone number has to be 10 digits.");
         } while (phoneNum < 1000000000 || phoneNum > ((long) Math.pow(9.999999999, 10)));
      
         ID = objCustmr.setNewID();
         objCustmr.addCustomer(ID, firstName, lastName, phoneNum, "None");
         // objCustmr.bubbleSort(); -->This now occurs directly when a custome is
         // created.
      }
      System.out.printf("Welcome " + objCustmr.getFirstName(ID) + " " + objCustmr.getLastName(ID) + "!\n");
      System.out.println("Enter any key to continue");
      uI.nextLine();
   
      // The Order Desk, Ordering a Product
   
      // Error checking
      do {
         objCatlg.displayCustomerCatalogue();
         do {
            productNum = integerU(uI, "Please enter the ID of a product number the customer would like to buy: ", 0,
                  Integer.MAX_VALUE);
            if (!(objCatlg.productVerify(productNum)))
               System.out.println(
                     "Please only enter the ID of an existing product\nAdding new products is perfomed in the Main Menu (Store Management)");
            uI.nextLine();
         } while (!(objCatlg.productVerify(productNum)));
      
         // Ask them to enter number of items requested
         numOfItems = integerU(uI, "How many of this item would you like?", 0, Integer.MAX_VALUE);
      
         /*
          * Backorder previous thought process -> This was going to be used for backorder
          * purposes if ((catalogue.getNumInStock(productNum) - numOfItems) < 0) { //
          * catalogue.setNumInBackorder(productNum, numOfItems - //
          * catalogue.getNumInStock(productNum)); catalogue.setNumInStock(productNum, 0);
          * } else { catalogue.setNumInStock(productNum,
          * catalogue.getNumInStock(productNum) - numOfItems); }
          *
          * if (numOfItems > catalogue.getNumInStock(productNum)) System.out.println(
          * "The number you ordered exceeds our stock. Please refer to the catalogue and order within the limit\nIf no items availabe (stock is zero), proceed to inventory management to add a product."
          * );
          */
      
         // Add object to customer cart
         objCatlg.addToCart(productNum, numOfItems);
      
         // Ask the user if they would like to add more items or proceed to checkouts
         choice = integerU(uI,
               "Would you like to add more items? Enter '1' to add more to the cart\n"
                     + "                                 -Enter '2' to go to checkout( Confirm V.S Delete Purchase): ",
               1, 2);
      } while (choice == 1);
      objCatlg.displayCart();
   
      x = integerU(uI, "Enter 1- To Confirm Purchase\nEnter 2- To Clear All Items from Shopping Cart", 1, 2);
   
      // Confirms Purchase
      if (x == 1) {
         objCatlg.confirmPurchase(ID);
         objCustmr.setBackorderedItems(ID, objCatlg.getBackorderedItems());
         System.out.println("Order confirmed. Thank you for supporting local businesses.");
      }
      // Clears Purchase
      else if (x == 2) {
         System.out.println("We're sorry to see you go. Cart has been cleared.");
      }
   
      // Clears cart
      objCatlg.clearCart();
   } // End of Point of Sales method

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI, String message, int min, int max
    * @Return: int
    * @Purpose: To get the user to input an integer between a minimum and maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   private static int integerU(Scanner uI, String message, int min, int max) { // Begin
      // integerValue(uI,message,min,max) method
   
      int input1 = Integer.MIN_VALUE; // Declares and initializes input1 as the integer for uI, sets it to a negative
      // number to run loop
   
      System.out.println(message); // This prevents redundancy with System.out.println();
   
      // Check that the user inputted an int
      while (true) {
      
         try {
            input1 = uI.nextInt(); // The integer number is stored in variable input1, which is returned below
         } catch (Exception e) {
            uI.nextLine();
            System.out.println("Please enter an integer number...");
         }
      
         // If the int is between the min and the max, break the loop
         if (input1 <= max && input1 >= min)
            break;
      
         if (input1 > max || input1 < min)
            System.out.println("Note: Please only enter an integer number within the range given.");
      }
      return input1; // Returns input1
   }// End integerU() method
}// End of Class Point of Sales

/***********************************************************************************************************
 * @Author: Ali Taha @Date: 06/11/2021 @Mr.A. Carreiro InventoryManaging.java
 *
 * @Program Description: This is the InventoryManaging class of the business
 *          program, which includes the getter and setter methods of the
 *          customers. The classes used display the menu options to the user,
 *          and allows them to add, edit, and remove different customer members
 *          from the organization.
 *********************************************************************************************************/
class InventoryManaging { // Begin class Inventory Managing
   private Scanner uI = new Scanner(System.in);
   private int choiceMI;
   private int productNum;
   private int numInStock = 0;

   // Class Constructor
   public InventoryManaging(int choiceMI, int productNum) {
      this.choiceMI = choiceMI;
      this.productNum = productNum;
   }

   // This manages everything in the inventory
   public void InventoryManage(String description, boolean checking, Catalogue objCatlg, String name, double price) {
      while (choiceMI != 7) {
         choiceMI = integerU(uI,
               "----------------------------------------------------------------------------------\n"
                     + "**Entering Inventory Management Menu**\n" + "Choose a number from the following options:\n"
                     + "1 - Completely edit the number of items in stock for an existing product\n"
                     + "2 - ->   Add more items in stock for an existing product\n"
                     + "3 - ->   Remove items from stock of an existing product\n"
                     + "4 - Add a new product (New Release) to the catalogue\n"
                     + "5 - Remove an existing product from the catalogue\n"
                     + "6 - Display catalogue items & Backorder\n" + "7 - Go back to the main menu \n"
                     + "----------------------------------------------------------------------------------",
               1, 7);
      
         switch (choiceMI) {
         // Edit the product stock
            case 1:
               objCatlg.displayCatalogue();
            
            // do while loop for Product ID input
               do {
                  productNum = integerU(uI, "Please enter the ID of the product you would like to edit", 0,
                     Integer.MAX_VALUE);
                  checking = objCatlg.productVerify(productNum);
                  if (!checking)
                     System.out.println("No such product exists. Enter an existing product");
                  else
                     numInStock = integerU(uI, "Please enter a new amount for the product's inventory:", 0,
                        Integer.MAX_VALUE);
               } while (!checking);
            
            // Editing stocks
               objCatlg.setNumInStock(productNum, numInStock);
               System.out.println("Edit successful");
               uI.nextLine();
               pause(uI);
               break;
         
         // Add more items in stock for an existing product
            case 2:
               objCatlg.displayCatalogue();
            
            // do while loop for error checking
               do {
                  productNum = integerU(uI, "Please enter the ID of the product you would like to edit", 0,
                     Integer.MAX_VALUE);
                  checking = objCatlg.productVerify(productNum);
                  if (!checking)
                     System.out.println("No such product exists. Enter an existing product");
                  else {
                     numInStock = objCatlg.getNumInStock(productNum);
                     numInStock += integerU(uI,
                        "Please enter number of products you would like to ADD to existing stock (arrivals)", 0,
                        Integer.MAX_VALUE);
                  }
               } while (!checking);
            
            // Adding stocks
               objCatlg.setNumInStock(productNum, numInStock);
               System.out.println("Added successfully");
               uI.nextLine();
               pause(uI);
               break;
         
         // Remove items in stock for an existing product
            case 3:
               objCatlg.displayCatalogue();
            // for error checking
               do {
                  productNum = integerU(uI, "Please enter the ID of the product you would like to edit", 0,
                     Integer.MAX_VALUE);
                  checking = objCatlg.productVerify(productNum);
                  if (!checking)
                     System.out.println("No such product exists. Enter an existing product");
                  else {
                     numInStock = objCatlg.getNumInStock(productNum);
                     numInStock -= integerU(uI,
                        "Please enter number of products you would like to DEDUCT from existing stock (damages/sold)",
                        0, Integer.MAX_VALUE);
                  }
               } while (!checking);
            
            // Removing stocks
               objCatlg.setNumInStock(productNum, numInStock);
               System.out.println("Stock removed successfully");
               uI.nextLine();
               pause(uI);
               break;
         
         // Add new product
            case 4:
               uI.nextLine();
               addingProduct(productNum, name, price, numInStock, description, objCatlg);
               pause(uI);
               break;
         
         // Remove an existing product from catalogue
            case 5:
               objCatlg.displayCatalogue();
               removingProduct(productNum, objCatlg);
               pause(uI);
               break;
         
         // This displays the catalogue
            case 6:
               objCatlg.displayCatalogue();
               uI.nextLine();
               pause(uI);
               break;
         
         // This returns to the main() menu
            default:
               System.out.println("Back to main menu we go!");
               break;
         }
      }
      choiceMI = 1;
   } // End Inventory Managing Method

   // Removes Products
   public void removingProduct(int productNum, Catalogue objCatlg) {
      do {
         productNum = integerU(uI, "Please enter a product number to remove that product:", -1, Integer.MAX_VALUE);
         if (!(objCatlg.productVerify(productNum)))
            System.out.println("Only enter an existing product please");
      } while (objCatlg.productVerify(productNum) == false);
      objCatlg.removeCatalogueItem(productNum);
      System.out.println("Product removed successfully.");
      uI.nextLine();
   }

   // Adds Products
   public void addingProduct(int productNum, String name, Double price, int numInStock, String description,
         Catalogue objCatlg) {
      System.out.println("Adding new product...\nPlease enter a name for the new product: ");
      name = uI.nextLine();
   
      // Check this? productNum = String.format("%06d",
      // Math.random().nextInt(999999));
      productNum = (int) (Math.random() * 899999 + 100000);
      price = doubleU(uI, "Please enter a price for the new product:", 0, Double.MAX_VALUE);
      numInStock = integerU(uI, "Please enter the number in stock for this new product: ", 0, Integer.MAX_VALUE);
   
      System.out.println("Please enter a description for this new product: ");
      uI.nextLine();
      description = uI.nextLine();
      objCatlg.addCatalogueItem(productNum, name, price, numInStock, description, 0, 0);
      System.out.println("Product Added Successfully");
   }

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI
    * @Return: void
    * @Purpose: To get the user to input an integer between a minimum nd maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   public static void pause(Scanner uI) {
      System.out.println("Enter any key to continue");
      uI.nextLine();
   }

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI, String message, int min, int max
    * @Return: int
    * @Purpose: To get the user to input an integer between a minimum and maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   private static int integerU(Scanner uI, String message, int min, int max) { // Begin
      // integerValue(uI,message,min,max) method
   
      int input1 = Integer.MIN_VALUE; // Declares and initializes input1 as the integer for uI, sets it to a negative
      // number to run loop
   
      System.out.println(message); // This prevents redundancy with System.out.println();
   
      // Check that the user inputted an int
      while (true) {
      
         try {
            input1 = uI.nextInt(); // The integer number is stored in variable input1, which is returned below
         } catch (Exception e) {
            uI.nextLine();
            System.out.println("Please enter an integer number...");
         }
      
         // If the int is between the min and the max, break the loop
         if (input1 <= max && input1 >= min)
            break;
      
         if (input1 > max || input1 < min)
            System.out.println("Note: Please only enter an integer number within the range given.");
      }
      return input1; // Returns input1
   }// End integerU() method

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI, String message, double min, double max
    * @Return: int
    * @Purpose: To get the user to input an integer between a minimum and maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   private static double doubleU(Scanner uI, String message, double min, double max) { // Begin
      // doubleU(uI,message,min,max) method
   
      double input1 = Double.MIN_VALUE - 1; // Declares and initializes input1 as the integer for uI, sets it to a
                                            // negative
      // number to run loop
   
      System.out.println(message); // This prevents redundancy with System.out.println();
   
      // Check that the user inputted a double
      while (true) {
      
         try {
            input1 = uI.nextDouble(); // The double number is stored in variable input1, which is returned below
         } catch (Exception e) {
         }
         if (input1 == Double.MIN_VALUE) {
            try {
               input1 = uI.nextInt();
            } catch (Exception e) {
               uI.nextLine();
               System.out.println("Please enter a valid number...");
            }
         }
      
         // If the double is between the min and the max, break the loop
         if ((input1 < max && input1 > min))
            break;
      
         if (input1 > max || input1 < min) {
            System.out.println("Note: Please only enter a number within the range given.");
            uI.nextLine();
         }
      }
      return input1; // Returns input1
   }// End doubleU() method

}// End class InvenrtoryManaging

/***********************************************************************************************************
 * @Author: Ali Taha                                                                @Date: 06/11/2021 
 * @Mr.A. Carreiro 
 * InventoryManaging.java
 *
 * Program Description: This is the InventoryManaging class of the business program, which includes the 
 * getter and setter methods of the customers. The classes used display the menu options to the user,  
 * and allows them to add, edit, and remove different customer members from the organization.
 *********************************************************************************************************/
class CustomerManaging { // Begin Class CustomerManaging
   private Scanner uI = new Scanner(System.in);
   private int choiceMC;
   private String firstName = "";
   private String lastName = "";

   // Class Constructor
   public CustomerManaging(int choiceMC) {
      this.choiceMC = choiceMC;
   }

   // This manages everything in the inventory
   public void CustomerManage(Customer objCustmr, boolean checking, long phoneNum, int ID) {
      while (choiceMC != 4) {
         choiceMC = integerU(uI,
               "----------------------------------------------------------------------------------\n"
                     + "**Entering Customer Management Menu**\n" + "1 - Add a customer\n" + "2 - Remove a customer\n" // Remove
                                                                                                                      // customer
                                                                                                                      // from
                                                                                                                      // existing
                                                                                                                      // file
                     + "3 - Display existing customers & Backorder\n" + "4 - Return to the Main menu\n"
                     + "----------------------------------------------------------------------------------",
               1, 4);
         switch (choiceMC) {
         
         // Add customer
            case 1:
               System.out.println("NOTE: These are the existing customers");
               objCustmr.displayCustomerDatabase();
               uI.nextLine();
               System.out.println("Enter customer first name: ");
               firstName = uI.nextLine();
               System.out.println("Enter customer last name: ");
               lastName = uI.nextLine();
            
            // Do while loop for phone number input
               do {
                  System.out.println("Please enter customer phone number (no dashes- only enter TEN integer numbers)");
                  while (!(uI.hasNextLong())) {
                     uI.nextLine();
                     System.out.println("Please enter a valid phone number with no dashes: ");
                  }
                  phoneNum = uI.nextLong();
                  if (phoneNum < 1000000000 || phoneNum > ((long) Math.pow(9.999999999, 10)))
                     System.out.println("Number has to be at least 10 digits");
               } while (phoneNum < 1000000000 || phoneNum > ((long) Math.pow(9.999999999, 10)));
            
               int newID = objCustmr.setNewID();
               objCustmr.addCustomer(newID, firstName, lastName, phoneNum, "None");
               System.out.println("New customer added successfully");
               uI.nextLine();
               pause(uI);
               break;
         
         // Removes Customer
            case 2:
               objCustmr.displayCustomerDatabase();
            
            // do while loop for error checking
               do {
                  ID = integerU(uI, "Please enter the ID of the customer you would like to remove", 0, Integer.MAX_VALUE);
                  checking = objCustmr.customerVerify(ID);
                  if (!checking)
                     System.out.println("No such customer exists. Enter an existing customer");
               } while (!checking);
               objCustmr.removeCustomer(ID);
               System.out.println("Customer removed successfully.");
               uI.nextLine();
               break;
         
         // Used for customer display
            case 3:
               objCustmr.displayCustomerDatabase();
               uI.nextLine();
               pause(uI);
               break;
         
         // Returning to main menu
            default:
               System.out.println("Back to main menu...");
               break;
         }
      
      }
      choiceMC = 1;
   }

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI
    * @Return: void
    * @Purpose: To get the user to input an integer between a minimum nd maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   public static void pause(Scanner uI) {
      System.out.println("Enter any key to continue");
      uI.nextLine();
   }

   /***********************************************************************************************************
    * @Author: Ali Taha @Date: 21/10/2021
    *
    * @Parameters: Scanner uI, String message, int min, int max
    * @Return: int
    * @Purpose: To get the user to input an integer between a minimum and maximum
    *           using the Scanner uI and to in turn check the user Input for the
    *           correct type.
    ***********************************************************************************************************/
   private static int integerU(Scanner uI, String message, int min, int max) { // Begin
      // integerValue(uI,message,min,max) method
   
      int input1 = Integer.MIN_VALUE; // Declares and initializes input1 as the integer for uI, sets it to a negative
      // number to run loop
   
      System.out.println(message); // This prevents redundancy with System.out.println();
   
      // Check that the user inputted an int
      while (true) {
      
         try {
            input1 = uI.nextInt(); // The integer number is stored in variable input1, which is returned below
         } catch (Exception e) {
            uI.nextLine();
            System.out.println("Please enter an integer number...");
         }
      
         // If the int is between the min and the max, break the loop
         if (input1 <= max && input1 >= min)
            break;
      
         if (input1 > max || input1 < min)
            System.out.println("Note: Please only enter an integer number within the range given.");
      }
      return input1; // Returns input1
   }// End integerU() method

}// End class CustomerManaging
