package assignments.assignment4.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NameNotFoundException;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Order;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;

public class BillPrinter {
    private Stage stage;
    private MainApp mainApp;
    private User user;
    private Order order;

    public BillPrinter(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user;
    }

    private Scene createBillPrinterForm(){
        // Membuat layout
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #FFF2D7;");
        layout.setAlignment(Pos.CENTER);

        List<Label> billLabels = outputBillPesanan(order);
        Button backButton = new Button("Kembali");

        // Implementasi fungsionalitas button
        backButton.setOnAction(event -> {
            stage.setScene(mainApp.getScene("printer"));
        });

        // Membuat layout bill
        VBox billLayout = new VBox(10);
        billLayout.setStyle("-fx-background-color: #F8C794;");
        billLayout.setAlignment(Pos.CENTER);
        billLayout.setMinHeight(10 * billLabels.size() + 20);
        for (Label label : billLabels) {
            billLayout.getChildren().add(label);
        }

        layout.getChildren().addAll(billLayout, backButton);
        return new Scene(layout, 400, 230 + 10 * billLabels.size());
    }

    private void printBill(String orderId) throws NameNotFoundException {
        // Periksa apakah order Id ada di user atau tidak
        order = DepeFood.getOrderOrNull(orderId, user); 
        if (order != null) {
            stage.setScene(getScene());
        } else {
            throw new NameNotFoundException();
        }
    }

    public void checkOrder(String orderId) throws NameNotFoundException {
        try {
            printBill(orderId);
        } catch (NameNotFoundException e) {
            throw e;
        }
    }

    protected List<Label> outputBillPesanan(Order order) {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(String.format("Bill:")));
        labels.add(new Label(String.format("Order ID: %s", order.getOrderId())));
        labels.add(new Label(String.format("Tanggal Pemesanan: %s", order.getTanggal())));
        labels.add(new Label(String.format("Lokasi Pengiriman: %s", user.getLokasi())));
        labels.add(new Label(String.format("Status Pengiriman: %s", !order.getOrderFinished() ? "Not Finished" : "Finished")));
        labels.add(new Label(String.format("Pesanan:")));
        for (Menu menu : order.getItems()) {
            labels.add(new Label(String.format("-%s Rp %.0f", menu.getNamaMakanan(), menu.getHarga())));
        }
        labels.add(new Label(String.format("Biaya Ongkos Kirim: Rp %d", order.getOngkir())));
        labels.add(new Label(String.format("Total Biaya: Rp %.0f", order.getTotalHarga())));

        return labels;
    }


    public Scene getScene() {
        return this.createBillPrinterForm();
    }

    // Class ini opsional
    public class MenuItem {
        private final StringProperty itemName;
        private final StringProperty price;

        public MenuItem(String itemName, String price) {
            this.itemName = new SimpleStringProperty(itemName);
            this.price = new SimpleStringProperty(price);
        }

        public StringProperty itemNameProperty() {
            return itemName;
        }

        public StringProperty priceProperty() {
            return price;
        }

        public String getItemName() {
            return itemName.get();
        }

        public String getPrice() {
            return price.get();
        }
    }
}
