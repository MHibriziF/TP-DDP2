package assignments.assignment4.page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.BillPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NameNotFoundException;

public class CustomerMenu extends MemberMenu{
    private Stage stage;
    private Scene scene;
    private Scene addOrderScene;
    private Scene printBillScene;
    private Scene payBillScene;
    private Scene cekSaldoScene;
    private BillPrinter billPrinter; // Instance of BillPrinter
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private ListView<String> menuItemsListView = new ListView<>();
    private static Label label = new Label();
    private MainApp mainApp;
    private List<Restaurant> restoList = new ArrayList<>();
    private User user;


    public CustomerMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addOrderScene = createTambahPesananForm();
        this.billPrinter = new BillPrinter(stage, mainApp, this.user); // Pass user to BillPrinter constructor
        this.printBillScene = createBillPrinter();
        this.payBillScene = createBayarBillForm();
        this.cekSaldoScene = createCekSaldoScene();

        // Refresh data restoran
        this.refresh(restoList);
        for (Restaurant restaurant : this.restoList) {
            restaurantComboBox.getItems().add(restaurant.getNama());
        }
        menuItemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public Scene getScene(){
        return this.scene;
    }

    @Override
    public Scene createBaseMenu() {
        // Membuat layout menu
        VBox layout = new VBox(10);
        GridPane grid = new GridPane();
        layout.setStyle("-fx-background-color: #FFF2D7;");
        layout.setAlignment(Pos.CENTER);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
    
        // Membuat button 
        Button buatPesananButton = new Button("Buat Pesanan");
        buatPesananButton.setMinWidth(150);
        Button cetakBillButton = new Button("Cetak Bill");
        cetakBillButton.setMinWidth(150);
        Button bayarBillButton = new Button("Bayar Bill");
        bayarBillButton.setMinWidth(150);
        Button cekSaldoButton = new Button("Cek Saldo");
        cekSaldoButton.setMinWidth(150);
        Button logoutButton = new Button("Logout");
        logoutButton.setMinWidth(150);

        // Implementasi fungsionalitas button
        buatPesananButton.setOnAction(event -> {
            addOrderScene = createTambahPesananForm();
            stage.setScene(addOrderScene);
        });
        cetakBillButton.setOnAction(event -> {
           printBillScene = createBillPrinter();
           mainApp.addScene("printer", printBillScene); // Menambahkan scene agar bisa kembali saat di BillPrinter
           stage.setScene(printBillScene);
        });
        bayarBillButton.setOnAction(event -> {
            payBillScene = createBayarBillForm();
            stage.setScene(payBillScene);
        });
        cekSaldoButton.setOnAction(event -> {
            cekSaldoScene = createCekSaldoScene();
            stage.setScene(cekSaldoScene);
        });
        logoutButton.setOnAction(event -> {
            mainApp.logout();
        });

        // Menambahkan button ke menu
        grid.add(buatPesananButton, 0, 0);
        grid.add(cetakBillButton, 1, 0);
        grid.add(bayarBillButton, 0, 1);
        grid.add(cekSaldoButton, 1, 1);
        layout.getChildren().addAll(grid, logoutButton);

        return new Scene(layout, 400, 600);
    }

    private Scene createTambahPesananForm() {
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-background-color: #FFF2D7;");
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(10, 10, 10, 10));
        restaurantComboBox.setMinWidth(100);

        // Label dan layout combo-box resto
        HBox restoInputLayout = new HBox(10);   
        Label restoNameLabel  = new Label("Restaurant Name:");
        restaurantComboBox.setPromptText("Pilih restoran");
        restoInputLayout.setAlignment(Pos.CENTER);
        restoInputLayout.setPadding(new Insets(10, 10, 10, 10));
        restoInputLayout.getChildren().addAll(restoNameLabel, restaurantComboBox);

        // Membuat label tanggal dan button yang diperlukan
        Label tanggalLabel  = new Label("Date (DD/MM/YYYY):");
        TextField inputTanggal = new TextField();
        inputTanggal.setMinWidth(400);
        Label menuLabel = new Label("Menu:");
        Button menuButton = new Button("Menu");
        Button makeOrderButton = new Button("Buat Pesanan");
        Button backButton = new Button("Kembali");
       
        // Implementasi fungsionalitas tombol
        menuButton.setOnAction(event -> {
            String restoName = restaurantComboBox.getValue();
            Restaurant resto = DepeFood.findRestaurant(restoName);
            if (resto != null) {
                menuItemsListView.getItems().clear();
                // Menambahkan deskripsi makanan ke menuItemsListView
                for (Menu menu: resto.getMenu()) {
                    String itemDesc = String.format("%s", menu.getNamaMakanan());
                    menuItemsListView.getItems().add(itemDesc);
                }
            } else {
                showAlert("Invalid value!", "Invalid value!", "Pilih restaurant terlebih dahulu!", AlertType.ERROR);
            }
        });
        makeOrderButton.setOnAction(event -> {
            String tanggal = inputTanggal.getText();
            String namaResto = restaurantComboBox.getValue();
            ObservableList<String> selectedItems =  menuItemsListView.getSelectionModel().getSelectedItems();
            handleBuatPesanan(namaResto, tanggal, selectedItems); // Membuat pesanan
        });
        backButton.setOnAction(event-> {
            stage.setScene(scene);
        });

        menuLayout.getChildren().addAll(restoInputLayout, tanggalLabel, inputTanggal, menuButton, menuLabel, menuItemsListView, makeOrderButton, backButton);
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createBillPrinter(){
        // Membuat layout
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-background-color: #FFF2D7;");
        menuLayout.setAlignment(Pos.CENTER);

        // Membuat nodes yang dibutuhkan
        TextField inputOrderId = new TextField();
        inputOrderId.setMinWidth(400);
        Button printBillButton = new Button("Print Bill");
        printBillButton.setMinWidth(150);
        Button backButton = new Button("Kembali");
        backButton.setMinWidth(150);


        //Implementasi fungsionalitas button
        printBillButton.setOnAction(event -> {
            String orderId = inputOrderId.getText();
            try {
                billPrinter.checkOrder(orderId);
            } catch (NameNotFoundException e) {
                showAlert("Invalid Order ID", "Invalid Order ID", String.format("Order ID %s tidak ditemukan", orderId), AlertType.ERROR);
            }
        });
        backButton.setOnAction(event -> {
            stage.setScene(scene);
        });

        menuLayout.getChildren().addAll(inputOrderId, printBillButton, backButton);
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createBayarBillForm() {
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-background-color: #FFF2D7;");
        menuLayout.setAlignment(Pos.CENTER);

        // Membuat nodes yang dibutuhkan
        TextField inputOrderId = new TextField();
        inputOrderId.setMinWidth(400);
        ComboBox<String> paymentComboBox = new ComboBox<>();
        paymentComboBox.setPromptText("Pilih Opsi Pembayaran");
        paymentComboBox.getItems().addAll("Credit Card", "Debit");
        Button payButton = new Button("Bayar");
        payButton.setMinWidth(150);
        Button backButton = new Button("Kembali");
        backButton.setMinWidth(150);
        
        // Implementasi fungsionalitas button
        payButton.setOnAction(event -> {
            String orderId = inputOrderId.getText();
            String pilihanPembayaran = paymentComboBox.getValue();
            handleBayarBill(orderId, pilihanPembayaran);
        });
        backButton.setOnAction(event -> {
            stage.setScene(scene);
        });

        menuLayout.getChildren().addAll(inputOrderId, paymentComboBox, payButton, backButton);
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createCekSaldoScene() {
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-background-color: #FFF2D7;");
        menuLayout.setAlignment(Pos.CENTER);

        // Membuat label berisi informasi nama dan saldo dari user
        Label namaUserLabel = new Label(String.format("%s", user.getNama()));
        Label saldoUserLabel = new Label(String.format("Saldo: Rp %d", user.getSaldo()));
        namaUserLabel.setStyle("-fx-font-size: 15px");
        saldoUserLabel.setStyle("-fx-font-size: 15px");

        // Implentasi fungsionalitas tombol
        Button backButton = new Button("Kembali");
        backButton.setMinWidth(150);
        backButton.setOnAction(event -> {
            stage.setScene(scene);
        });

        menuLayout.getChildren().addAll(namaUserLabel, saldoUserLabel, backButton);
        return new Scene(menuLayout, 400,600);
    }

    private void handleBuatPesanan(String namaRestoran, String tanggalPemesanan, List<String> menuItems) {
        try {
            // Buat pesanan
            String orderId = DepeFood.handleBuatPesanan(namaRestoran, tanggalPemesanan, menuItems.size(), menuItems, user);
            // periksa apakah input membuat order Id yang valid
            if (orderId != null) {
                showAlert("Order Succesful", "Order Succesful", String.format("Order dengan ID %s berhasil ditambahkan", orderId), AlertType.INFORMATION);   
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            // Memberikan pesan jika gagal
            showAlert("Invalid input", "Invalid input", "Masukkan input sesuai ketentuan!", AlertType.ERROR);
        }
    }

    private void handleBayarBill(String orderID, String pilihanPembayaran) {
        try {
            String result = DepeFood.handleBayarBill(orderID, pilihanPembayaran, user); // Menghandle pembayaran
            if (result.equals("success")) { // Memproses pembayaran yang berhasil 
                Order order = DepeFood.getOrderOrNull(orderID, user);
                double cost = (order.getTotalHarga());
                String message = String.format("Berhasil membayar bill sebesar %.0f", cost);

                // Pesan berhasil sesuai tipe pembayaran
                if (pilihanPembayaran.equals("Credit Card")) {
                    double transactionFee = order.getTotalHarga() * 0.02;
                    message += String.format(" dengan biaya transaksi sebesar Rp %.0f", transactionFee);
                    showAlert("Success!", message, "", AlertType.INFORMATION);            
                } else {
                    showAlert("Success!", message, "", AlertType.INFORMATION);            
                }
            } else if (result.equals("no order")) { // Pesan gagal
                showAlert("Payment Failed", "Payment Failed", "Order Id tidak ditemukan!", AlertType.ERROR);
            } else if (result.equals("lunas")) { // Pesan gagal
                showAlert("Payment Failed", "Payment Failed", "Order ini sudah lunas!", AlertType.ERROR);
            } else if (result.equals("invalid payment")) { // Pesan gagal
                showAlert("Payment Failed", "Payment Failed", "Pilih metode pembayaran terlebih dahulu!", AlertType.ERROR);
            } else if (result.equals("incompatible payment")) { // Pesan gagal
                showAlert("Payment Failed", "Payment Failed", "User tidak memiliki sistem payment ini!", AlertType.ERROR);
            } else if (result.equals("no payment")) {
                showAlert("Payment Failed", "Payment Failed", "Pilih metode pembayaran!", AlertType.ERROR);
            }
        } catch (Exception e) {
            // Pesan jika saldo user tidak mencukupi
            showAlert("Payment Failed!", "Payment Failed", "Saldo tidak mencukupi!", AlertType.ERROR);
        }
    }
}