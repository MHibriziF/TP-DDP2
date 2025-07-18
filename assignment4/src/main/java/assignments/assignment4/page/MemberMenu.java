package assignments.assignment4.page;

import java.util.List;

import assignments.assignment3.Restaurant;
import assignments.assignment3.DepeFood;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public abstract class MemberMenu {
    private Scene scene;

    abstract protected Scene createBaseMenu();

    protected void showAlert(String title, String header, String content, Alert.AlertType c){
        Alert alert = new Alert(c);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Scene getScene(){
        return this.scene;
    }

    protected void refresh(List<Restaurant> restoList){
        // Melakukan refresh pada data restoran
        for (Restaurant restaurant : DepeFood.getRestoList()) {
            if (!restoList.contains(restaurant)) {
                restoList.add(restaurant);
            }
        }
    }

}
