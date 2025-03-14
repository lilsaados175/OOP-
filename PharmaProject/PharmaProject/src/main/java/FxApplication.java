import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class FxApplication extends Application{
     private Stage signupStage;
    private Stage staffStage;
    private Stage inventoryStage;
    private Stage patientStage;
    private Stage cartStage;
    private Stage cartSelectStage;
    private Stage errorStage;
    private Stage checkoutStage;
    private Staff currentstaff;
    private Patient currentpatient;
    private Cart currentcart;
    private Payment currentpayment;

    @Override
    public void start(Stage primaryStage) {
        // Create the Login menu
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(20);
        grid.setHgap(20);

        // Add the username and password labels and fields
        Label usernameLabel = new Label("ID:");
        grid.add(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        grid.add(usernameField, 1, 0);
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 1);

        // Add the login button
        Button signUpButton = new Button("SignUp");
        grid.add(signUpButton, 1, 3);
        signUpButton.setOnAction(event -> {
            showSignUpMenu();
        });
        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 2);
        loginButton.setOnAction(event -> {
            boolean welcome=false;
            // Check the username and password (you'll need to add your own logic here)
            String username = usernameField.getText();
            String password = passwordField.getText();
            if((username.equals("admin"))&&(password.equals("1234"))) {
                showStaffMenu();
                welcome = true;
            }
            for (Staff staff : Staff.StaffList) {
                if((staff.getID().equals(username))&&(staff.getPassword().equals(password))){
                    currentstaff=staff;
                    showStaffMenu();
                    welcome=true;
                    break;
                }
            }
            if (!welcome) {
                Label errorLabel = new Label("Error, wrong ID or Password.");
                grid.add(errorLabel, 1, 5);
            }
        });

        // Create the scene and show the Login menu
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
    private void showSignUpMenu() {
        // Create menu
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(15);
        grid.setHgap(15);
        //add labels and buttons
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 1);
        TextField nameField = new TextField();
        grid.add(nameField, 1, 1);
        Label ageLabel = new Label("Age:");
        grid.add(ageLabel, 0, 2);
        TextField ageField = new TextField();
        grid.add(ageField, 1, 2);
        Label IDLabel = new Label("ID:");
        grid.add(IDLabel, 0, 3);
        TextField IDField = new TextField();
        grid.add(IDField, 1, 3);
        Label passLabel = new Label("Password:");
        grid.add(passLabel, 0, 4);
        TextField passField = new TextField();
        grid.add(passField, 1, 4);

        Button newStaff = new Button("Create New Staff");
        grid.add(newStaff, 1, 6);
        newStaff.setOnAction(event -> {
            try{
                String name= IDField.getText();
                int age = Integer.parseInt(IDField.getText());
                String id = IDField.getText();
                String pass = passField.getText();
                new Staff(name,age,id,pass); //automatically adds to arraylist
                Label createdLabel = new Label("New Staff Created");
                grid.add(createdLabel, 2, 7);
            }
            catch(Exception e) {
                showErrorMenu(e);
            }

        });
        // Create the scene and show the Welcome menu
        Scene scene = new Scene(grid, 600, 600);
        staffStage = new Stage();
        staffStage.setScene(scene);
        staffStage.setTitle("Staff SignUp");
        staffStage.show();
    }
    private void showStaffMenu() {
        // Create the menu
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(15);
        grid.setHgap(15);
        // Labels and Buttons
        Label welcomeLabel = new Label("Welcome to Staff Page!");
        grid.add(welcomeLabel, 0, 1);

        Button inventButton = new Button("Inventory");
        grid.add(inventButton, 0, 3);
        inventButton.setOnAction(event -> {
            showInventoryMenu();
        });
        Button cartButton = new Button("New Cart");
        grid.add(cartButton, 2, 3);
        cartButton.setOnAction(event -> {
            showPatientMenu();
        });

        // Create the scene and show the Welcome menu
        Scene scene = new Scene(grid, 400, 200);
        staffStage = new Stage();
        staffStage.setScene(scene);
        staffStage.setTitle("Staff Menu");
        staffStage.show();
    }

    private ObservableList<Medication> observableList;
    private FilteredList<Medication> filteredList;
    private void updateInventory(){
        observableList.setAll(Inventory.Inventory);
        filteredList.setPredicate(filteredList.getPredicate()); // Refresh the filteredList
    }
    private void showInventoryMenu(){
        // Create the ObservableList and FilteredList
        observableList = FXCollections.observableArrayList(Inventory.Inventory);
        filteredList = new FilteredList<>(observableList, p -> true);

        // Create the TableView
        TableView<Medication> tableView = new TableView<>(filteredList);

        // Create the columns
        TableColumn<Medication, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Medication, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Medication, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Medication, Double> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableView.getColumns().addAll(nameColumn, typeColumn, priceColumn, stockColumn);



        TextField nameField = new TextField();
        TextField typeField = new TextField();
        TextField priceField = new TextField();
        TextField stockField = new TextField();

        // Create the TextField and Search button
        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Search");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String searchText = searchTextField.getText().toLowerCase();
            filteredList.setPredicate(Medication -> Medication.getName().toLowerCase().contains(searchText)
                    || Medication.getType().toLowerCase().contains(searchText)
                    || Double.toString(Medication.getPrice()).contains(searchText)
                    || Integer.toString(Medication.getStock()).contains(searchText));

        });

        Button addMed = new Button("Add Med");
        addMed.setOnAction(event -> {
            try{
            String name= nameField.getText();
            String type= typeField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            Inventory.InventoryADD(name,type, price, stock);
            updateInventory();
            }
            catch(Exception e) {
                showErrorMenu(e);
            }
        });
        Button updateMed = new Button("Update Med");
        updateMed.setOnAction(event -> {
            Medication selectedMed = tableView.getSelectionModel().getSelectedItem();
            if (selectedMed != null) {
                try{
                    String name= nameField.getText();
                    String type= typeField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int stock = Integer.parseInt(stockField.getText());
                    Inventory.InventoryUPDATE(selectedMed,name,type, price, stock); //automatically adds to arraylist
                    updateInventory();
                }
                catch(Exception e) {
                    showErrorMenu(e);
                }
            }
            else {
                System.out.println("No ClassA object selected.");
            }
        });
        Button removeMed = new Button("Remove Med");
        removeMed.setOnAction(event -> {
            Medication selectedMed = tableView.getSelectionModel().getSelectedItem();
            if (selectedMed != null) {
                Inventory.InventoryREMOVE(selectedMed);
                updateInventory();
            }
            else {
                System.out.println("No ClassA object selected.");
            }
        });

        // Set the scene and show the stage
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.add(tableView, 0, 1,5,1);
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 5,1,1);
        grid.add(nameField, 1, 5,1,1);
        Label typeLabel = new Label("Type:");
        grid.add(typeLabel, 2, 5,1,1);
        grid.add(typeField, 3, 5,1,1);
        Label priceLabel = new Label("Price:");
        grid.add(priceLabel, 4, 5,1,1);
        grid.add(priceField, 5, 5,1,1);
        Label stockLabel = new Label("Stock:");
        grid.add(stockLabel, 6, 5,1,1);
        grid.add(stockField, 7, 5,1,1);

        grid.add(searchTextField, 0, 8,1,1);
        grid.add(searchButton, 1, 8,1,1);
        grid.add(addMed, 2, 8,1,1);
        grid.add(updateMed, 3, 8,1,1);
        grid.add(removeMed, 4, 8,1,1);

        //VBox root = new VBox(listView,nameField, typeField, priceField, stockField, searchTextField,
        //        searchButton, addMed, updateMed,removeMed);

        Scene scene = new Scene(grid, 1200, 600);
        inventoryStage = new Stage();
        inventoryStage.setScene(scene);
        inventoryStage.setTitle("Inventory Menu");
        inventoryStage.show();
    }

    private ObservableList<Patient> observablePatients;
    private  FilteredList<Patient> filteredPatients;
    private void updatePatients(){
        observablePatients.setAll(Patient.PatientList);
        filteredPatients.setPredicate(filteredPatients.getPredicate()); // Refresh the filteredList
    }
    private void showPatientMenu(){
        // Create the ObservableList and FilteredList
        observablePatients = FXCollections.observableArrayList(Patient.PatientList);
        filteredPatients = new FilteredList<>(observablePatients, p -> true);

        // Create the TableView
        TableView<Patient> tableView = new TableView<>(filteredPatients);

        // Create the columns
        TableColumn<Patient, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Patient, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Patient, String> addColumn = new TableColumn<>("Address");
        addColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Patient, Integer> telenumColumn = new TableColumn<>("Number");
        telenumColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Patient, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(nameColumn, ageColumn, addColumn, telenumColumn,emailColumn);

        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField addField = new TextField();
        TextField telenumField = new TextField();
        TextField emailField = new TextField();

        // Create the TextField and Search button
        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Search");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String searchText = searchTextField.getText().toLowerCase();
            filteredPatients.setPredicate(Patient -> Patient.getName().toLowerCase().contains(searchText)
                    || Integer.toString(Patient.getAge()).contains(searchText)
                    || Patient.getAddress().toLowerCase().contains(searchText)
                    || Integer.toString(Patient.getTelenum()).contains(searchText)
                    || Patient.getEmail().toLowerCase().contains(searchText)
            );

        });

        Button addPat = new Button("Add Patient");
        addPat.setOnAction(event -> {
            try{
                String name= nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String address= addField.getText();
                int telenum = Integer.parseInt(telenumField.getText());
                String email= emailField.getText();
                new Patient(name,age,address,telenum,email);
                updatePatients();
            }
            catch(Exception e) {
                showErrorMenu(e);
            }
        });
        Button newCart = new Button("New Cart");
        newCart.setOnAction(event -> {
            Patient selectedPat = tableView.getSelectionModel().getSelectedItem();
            if (selectedPat != null) {
                currentpatient=selectedPat;
                showCartMenu();
            }
            else {
                showErrorMenu(new Exception("please select a Patient"));
            }
        });

        // Set the scene and show the stage
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.add(tableView, 0, 1,5,1);
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 5,1,1);
        grid.add(nameField, 1, 5,1,1);
        Label ageLabel = new Label("Age:");
        grid.add(ageLabel, 2, 5,1,1);
        grid.add(ageField, 3, 5,1,1);
        Label addLabel = new Label("Address:");
        grid.add(addLabel, 4, 5,1,1);
        grid.add(addField, 5, 5,1,1);
        Label teleLabel = new Label("TeleNum:");
        grid.add(teleLabel, 6, 5,1,1);
        grid.add(telenumField, 7, 5,1,1);
        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 8, 5,1,1);
        grid.add(emailField, 9, 5,1,1);

        grid.add(searchTextField, 0, 8,1,1);
        grid.add(searchButton, 1, 8,1,1);
        grid.add(addPat, 2, 8,1,1);
        grid.add(newCart, 3, 8,1,1);


        Scene scene = new Scene(grid, 1250, 600);
        inventoryStage = new Stage();
        inventoryStage.setScene(scene);
        inventoryStage.setTitle("Patients Menu");
        inventoryStage.show();
    }

    private ObservableList<Medication> observableCart;
    private  FilteredList<Medication> filteredCart;
    Label totalCostLabel;
    private void updateCart(){
        observableCart.setAll(currentcart.Buying);
        filteredCart.setPredicate(filteredCart.getPredicate()); // Refresh the filteredList
        totalCostLabel = new Label(Double.toString(currentpayment.TotalCost()));
    }
    private void showCartMenu(){
        currentcart = new Cart(currentpatient);
        currentpayment = new Payment(currentcart);
        // Create the ObservableList and FilteredList
        observableCart = FXCollections.observableArrayList(currentcart.Buying);
        filteredCart = new FilteredList<>(observableCart, p -> true);

        // Create the TableView
        TableView<Medication> tableView = new TableView<>(filteredCart);

        // Create the columns
        TableColumn<Medication, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Medication, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Medication, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Medication, Double> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Medication, Integer> buyingColumn = new TableColumn<>("Buying");
        buyingColumn.setCellValueFactory(new PropertyValueFactory<>("buying"));

        tableView.getColumns().addAll(nameColumn, typeColumn, priceColumn, stockColumn,buyingColumn);


        TextField nameField = new TextField();
        TextField typeField = new TextField();
        TextField priceField = new TextField();
        TextField stockField = new TextField();



        Button addMed = new Button("Add to Cart");
        addMed.setOnAction(event -> {
            showCartSelectMenu();
        });
        Button checkoutBtn = new Button("Check out");
        checkoutBtn.setOnAction(event -> {
            showCheckoutMenu();
        });

        Button removeMed = new Button("Remove from Cart");
        removeMed.setOnAction(event -> {
            Medication selectedMed = tableView.getSelectionModel().getSelectedItem();
            if (selectedMed != null) {
                currentcart.Buying.remove(selectedMed);
                updateCart();
            }
            else {
                System.out.println("No object selected.");
            }
        });

        // Set the scene and show the stage
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.add(tableView, 0, 1,5,1);

        Label totalLabel = new Label("Total:");
        grid.add(totalLabel, 4, 4,1,1);
        totalCostLabel = new Label(Double.toString(currentpayment.TotalCost()));
        grid.add(totalCostLabel, 5, 4,1,1);

        grid.add(addMed, 2, 8,1,1);
        grid.add(removeMed, 3, 8,1,1);
        grid.add(checkoutBtn, 4, 8,1,1);

        Scene scene = new Scene(grid, 1200, 600);
        inventoryStage = new Stage();
        inventoryStage.setScene(scene);
        inventoryStage.setTitle("Cart Menu");
        inventoryStage.show();
        cartStage.show();
    }
    private void showCartSelectMenu(){
        // Create the ObservableList and FilteredList
        observableList = FXCollections.observableArrayList(Inventory.Inventory);
        filteredList = new FilteredList<>(observableList, p -> true);

        // Create the TableView
        TableView<Medication> tableView = new TableView<>(filteredList);

        // Create the columns
        TableColumn<Medication, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Medication, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Medication, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Medication, Double> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableView.getColumns().addAll(nameColumn, typeColumn, priceColumn, stockColumn);

        TextField nameField = new TextField();
        TextField typeField = new TextField();
        TextField priceField = new TextField();
        TextField stockField = new TextField();

        // Create the TextField and Search button
        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Search");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String searchText = searchTextField.getText().toLowerCase();
            filteredList.setPredicate(Medication -> Medication.getName().toLowerCase().contains(searchText)
                    || Medication.getType().toLowerCase().contains(searchText)
                    || Double.toString(Medication.getPrice()).contains(searchText)
                    || Integer.toString(Medication.getStock()).contains(searchText));

        });
        TextField quantity = new TextField();
        quantity.setPromptText("search");
        Button addToCart = new Button("Add to Cart");
        addToCart.setOnAction(event -> {
            Medication selectedMed = tableView.getSelectionModel().getSelectedItem();
            if ((selectedMed != null)&&(Integer.parseInt(quantity.getText())!=0)) {
                currentcart.addtoCart(selectedMed,Integer.parseInt(quantity.getText()));
                updateInventory();
                updateCart();
            }
            else {
                showErrorMenu(new Exception("Select a Medicine and Enter a Quantity"));
            }
        });

        // Set the scene and show the stage
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.add(tableView, 0, 1,5,1);

        grid.add(searchTextField, 0, 8,1,1);
        grid.add(searchButton, 1, 8,1,1);
        grid.add(quantity, 3, 8,1,1);
        grid.add(addToCart, 4, 8,1,1);


        Scene scene = new Scene(grid, 1200, 600);
        checkoutStage = new Stage();
        checkoutStage.setScene(scene);
        checkoutStage.setTitle("Inventory Menu");
        checkoutStage.show();
    }
    private void showCheckoutMenu(){
        // Create menu
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(15);
        grid.setHgap(15);
        //add buttons
        Label nameLabel = new Label("Total:");
        grid.add(nameLabel, 0, 1);
        totalCostLabel = new Label(Double.toString(currentpayment.TotalCost()));
        grid.add(totalCostLabel, 1, 1);
        Button paynow = new Button("Pay Now");
        grid.add(paynow, 1, 6);
        paynow.setOnAction(event -> {
            for (Medication med : currentcart.Buying) {
                currentcart.removeMedfromInv(med);
            }
            Label thankyou = new Label("Thank you for shopping with us");
            grid.add(thankyou, 1, 8);        });

        Scene scene = new Scene(grid, 500, 500);
        checkoutStage = new Stage();
        checkoutStage.setScene(scene);
        checkoutStage.setTitle("CheckOut Menu");
        checkoutStage.show();
    }
    private void showErrorMenu(Exception e){
        // Create menu
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(15);
        grid.setHgap(15);
        //add message
        Label errorLabel = new Label("Error: " + e.getMessage());
        grid.add(errorLabel, 0, 0);

        Scene scene = new Scene(grid, 400, 100);
        if (errorStage == null) {
            errorStage = new Stage();
            errorStage.setScene(scene);
            errorStage.setTitle("ERROR Menu");
        }
        errorStage.show();
    }
    public static void main(String[] args) {
        Application.launch(FxApplication.class);
    }

}