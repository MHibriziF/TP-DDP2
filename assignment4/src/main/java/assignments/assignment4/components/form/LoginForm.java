package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;

public class LoginForm {
    private Stage stage;
    private MainApp mainApp; // MainApp instance
    private TextField nameInput;
    private TextField phoneInput;

    public LoginForm(Stage stage, MainApp mainApp) { // Pass MainApp instance to constructor
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
        nameInput = new TextField();
        phoneInput = new TextField();
        DepeFood.initUser(); 
    }

    private Scene createLoginForm() {
        VBox vBox = new VBox(); 
        vBox.setStyle("-fx-background-color: #FFF2D7;");
        vBox.setAlignment(Pos.CENTER);

        // Pesan selamat datang
        Label welcomeMessage = new Label("Welcome to DepeFood");
        welcomeMessage.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");

        // Label dan input user
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #FFE0B5;");
        grid.setMinHeight(200);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);        
        Label nameLabel = new Label("Name:");
        Label phoneLabel = new Label("No Telepon:");

        // Mengatur ukuran field input
        nameInput.setMinWidth(300);
        phoneInput.setMinWidth(300);

        // Tombol login
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            handleLogin();
        });

        // Menambahkan label, input field, dan button ke grid
        grid.add(nameLabel, 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(phoneLabel, 0, 1);
        grid.add(phoneInput, 1, 1);
        grid.add(loginButton, 1, 2);

        vBox.getChildren().addAll(welcomeMessage, grid);

        return new Scene(vBox, 400, 600);
    }


    private void handleLogin() {
        String name = nameInput.getText();
        String phone = phoneInput.getText();
        User user = DepeFood.getUser(name, phone);

        // Periksa apakah user ada atau tidak
        if (user != null) {
            nameInput.setText("");
            phoneInput.setText("");
            // Mensetting scene sesuai role user
            String role = user.getRole();
            if (role.equals("Admin")) {
                Scene adminMenu = new AdminMenu(stage, mainApp, user).getScene();
                mainApp.addScene("Admin", adminMenu);
                stage.setScene(adminMenu);
            } else if (role.equals("Customer")){
                Scene customerMenu = new CustomerMenu(stage, mainApp, user).getScene();
                mainApp.addScene("Customer", customerMenu);
                stage.setScene(customerMenu);
            }
        } else {
            // Memberikan alert jika user tidak ditemukan
            Alert loginFailedAlert = new Alert(AlertType.ERROR, "");
            loginFailedAlert.setTitle("Login Failed");
            loginFailedAlert.getDialogPane().setHeaderText("User not found!");
            loginFailedAlert.initOwner(stage);
            loginFailedAlert.initModality(Modality.APPLICATION_MODAL);
            loginFailedAlert.showAndWait();
        }
    }

    public Scene getScene(){
        return this.createLoginForm();
    }

}
