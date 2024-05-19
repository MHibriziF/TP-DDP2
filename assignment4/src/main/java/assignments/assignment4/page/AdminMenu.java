package assignments.assignment4.page;

import java.util.ArrayList;
import java.util.List;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu{
    private Stage stage;
    private Scene scene;
    private User user;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    private List<Restaurant> restoList = new ArrayList<>();
    private MainApp mainApp; // Reference to MainApp instance
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private ListView<String> menuItemsListView = new ListView<>();
    private TextField inputRestoName;
    public AdminMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addRestaurantScene = createAddRestaurantForm();
        this.addMenuScene = createAddMenuForm();
        this.viewRestaurantsScene = createViewRestaurantsForm();

        // Refresh data restoran
        this.refresh(restoList);
        for (Restaurant restaurant : this.restoList) {
            restaurantComboBox.getItems().add(restaurant.getNama());
        }
    }

    public List<Restaurant> getRestoList() {
        return this.restoList;
    }

    public Scene getScene(){
        return this.scene;
    }

    @Override
    public Scene createBaseMenu() {
        // Membuat layout menu
        GridPane menuLayout = new GridPane();
        menuLayout.setStyle("-fx-background-color: #FFF2D7;");
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: #FFF2D7;");
        menuLayout.setVgap(10);
        menuLayout.setHgap(10);
      
        // Membuat button 
        Button tambahRestoButton = new Button("Tambah Restoran");
        tambahRestoButton.setMinWidth(150);
        Button tambahMenuButton = new Button("Tambah Menu Restoran");
        tambahMenuButton.setMinWidth(150);
        Button lihatDaftarButton = new Button("Lihat Daftar Restoran");
        lihatDaftarButton.setMinWidth(150);
        Button logoutButton = new Button("Logout");
        logoutButton.setMinWidth(150);

        // Implementasi fungsionalitas button
        tambahRestoButton.setOnAction(event -> {
            addRestaurantScene = createAddRestaurantForm();
            stage.setScene(addRestaurantScene);
        });
        tambahMenuButton.setOnAction(event -> {
           addMenuScene = createAddMenuForm();
           stage.setScene(addMenuScene);
        });
        lihatDaftarButton.setOnAction(event -> {
            viewRestaurantsScene = createViewRestaurantsForm();
            stage.setScene(viewRestaurantsScene);
        });
        logoutButton.setOnAction(event -> {
            mainApp.logout();
        });

        // Menambahkan button ke menu
        menuLayout.add(tambahRestoButton, 0, 0);
        menuLayout.add(tambahMenuButton, 1, 0);
        menuLayout.add(lihatDaftarButton, 0, 1);
        menuLayout.add(logoutButton, 1, 1);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createAddRestaurantForm() {
        // Membuat layout form add restaurant
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #FFF2D7;");
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));

        // Label dan input resto
        Label restoNameLabel  = new Label("Restaurant Name:"); 
        inputRestoName = new TextField();
        inputRestoName.setMinWidth(400);

        // Membuat layout button
        HBox buttonLayout = new HBox(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonLayout.setAlignment(Pos.CENTER);
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Kembali");

        // Implementasi fungsionalitas button
        submitButton.setOnAction(event -> {
            String restoName = inputRestoName.getText();
            handleTambahRestoran(restoName);
        });
        backButton.setOnAction(event -> {
            stage.setScene(scene);
        });

        // Menambahkan node ke layout
        buttonLayout.getChildren().addAll(submitButton, backButton);
        layout.getChildren().addAll(restoNameLabel, inputRestoName, buttonLayout);

        return new Scene(layout, 400, 600);
    }

    private Scene createAddMenuForm() {
        // Membuat layout form add menu
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #FFF2D7;");
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));

        // Label dan input resto
        Label restoNameLabel  = new Label("Restaurant Name:"); 
        TextField inputRestoName = new TextField();
        inputRestoName.setMinWidth(400);

        // Label dan input menu item
        Label menuItemLabel  = new Label("Menu Item Name:"); 
        TextField inputMenuItem = new TextField();
        inputMenuItem.setMinWidth(400);

        // Label dan input harga
        Label priceLabel  = new Label("Price:"); 
        TextField inputPrice = new TextField();
        inputPrice.setMinWidth(400);

        // Membuat layout button
        HBox buttonLayout = new HBox(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonLayout.setAlignment(Pos.CENTER);
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Kembali");

        // Implementasi fungsionalitas button
        submitButton.setOnAction(event -> {
            String restoName = inputRestoName.getText();
            String menuName = inputMenuItem.getText();
            String priceStr = inputPrice.getText();
            Restaurant resto = DepeFood.findRestaurant(restoName);
            double price;
            try {
                price = Double.parseDouble(priceStr);
                // Periksa apakah resto ada atau tidak
                if (resto != null) {
                    handleTambahMenuRestoran(resto, menuName, price); // Menambah menu ke restoran
                    inputMenuItem.setText("");
                    inputPrice.setText("");
                } else {
                    showAlert("Invalid input!", "Invalid input!", "Nama restoran tidak ditemukan", AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid input!", "Invalid input!", "Price diisi dengan angka!", AlertType.ERROR);
            }
        });
        backButton.setOnAction(event -> {
            stage.setScene(scene);
        });

        // Menambahkan node ke layout
        buttonLayout.getChildren().addAll(submitButton, backButton);
        layout.getChildren().addAll(restoNameLabel, inputRestoName, menuItemLabel, 
                                inputMenuItem, priceLabel, inputPrice, buttonLayout);

        return new Scene(layout, 400, 600);
    }
    
    
    private Scene createViewRestaurantsForm() {
        // Membuat layout form add restaurant
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #FFF2D7;");
        layout.setPadding(new Insets(10, 10, 10, 10));
        restaurantComboBox.setMinWidth(100);

        // Label dan layout combo-box resto
        HBox restoInputLayout = new HBox(10);   
        Label restoNameLabel  = new Label("Restaurant Name:");
        restoInputLayout.setAlignment(Pos.CENTER);
        restoInputLayout.setPadding(new Insets(10, 10, 10, 10));
        restoInputLayout.getChildren().addAll(restoNameLabel, restaurantComboBox);

        // Membuat layout untuk menampilkan item dan button search 
        VBox viewMenuLayout = new VBox(10);
        viewMenuLayout.setAlignment(Pos.CENTER);
        Label menuLabel  = new Label("Menu:");
        Button searchButton = new Button("Search");
        Button backButton = new Button("Kembali");
        viewMenuLayout.getChildren().addAll(searchButton, menuLabel, menuItemsListView, backButton);
        restaurantComboBox.setPromptText("Pilih Restoran");
       
        // Implementasi fungsionalitas tombol
        searchButton.setOnAction(event -> {
            String restoName = restaurantComboBox.getValue();
            Restaurant resto = DepeFood.findRestaurant(restoName);
            if (resto != null) {
                menuItemsListView.getItems().clear();
                for (Menu menu: resto.getMenu()) {
                    String itemDesc = String.format("%s - Rp%.1f", menu.getNamaMakanan(), menu.getHarga());
                    menuItemsListView.getItems().add(itemDesc);
                }
            } else {
                showAlert("Invalid value!", "Invalid value!", "Pilih restaurant terlebih dahulu!", AlertType.ERROR);
            }
        });
        backButton.setOnAction(event-> {
            stage.setScene(scene);
        });

        layout.getChildren().addAll(restoInputLayout, viewMenuLayout);
        return new Scene(layout, 400, 600);
    }
    

    private void handleTambahRestoran(String nama) {
        // Periksa apakah resotan ada dan namanya valid
        String validName = DepeFood.getValidRestaurantName(nama);
        boolean isRestaurantNameInvalid = validName.equals("Nama Restoran tidak valid! Minimal 4 karakter diperlukan.");
        boolean isRestaurantExist = validName.equals(String.format("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!", nama));
        
        if (isRestaurantExist) { // Memberikan pesan jika restoran sudah terdaftar
            showAlert("Invalid input!", "Invalid input!", String.format("Restoran dengan nama %s sudah pernah terdaftar", nama), AlertType.ERROR);
        } else if (isRestaurantNameInvalid) { // Memberikan pesan jika nama tidak valid
            showAlert("Invalid input!", "Invalid input!", "Nama restoran minimal 4 karakter!", AlertType.ERROR);
        } else { // Menambah restoran
            DepeFood.handleTambahRestoran(nama);
            restaurantComboBox.getItems().add(nama);
            showAlert("Success", "Success!", String.format("Restoran dengan nama %s berhasil terdaftar", nama), AlertType.INFORMATION);
            inputRestoName.setText("");
        }
    }

    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, double price) {
        DepeFood.handleTambahMenuRestoran(restaurant, itemName, price);
        showAlert("Success", "Success!", "Item berhasil ditambahkan ke restoran!", AlertType.INFORMATION);
    }
}
