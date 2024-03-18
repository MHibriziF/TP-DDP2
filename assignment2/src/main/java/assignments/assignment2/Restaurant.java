package assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
    // Atribut class Restaurant
    private String nama;
    private ArrayList<Menu> menu;

    public Restaurant(String nama){
        this.nama = nama;
        this.menu = new ArrayList<Menu>();
    }

    // Method getter
    public String getNama() {
        return this.nama;
    }
    
    public ArrayList<Menu> getMenu() {
        return this.menu;
    }

    // Method untuk menambah menu
    public void addMenu(Menu item) {
        menu.add(item);
    }
}
