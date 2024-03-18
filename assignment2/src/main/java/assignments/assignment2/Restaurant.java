package assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private ArrayList<Menu> menu;
    public Restaurant(String nama){
        this.nama = nama;
        this.menu = new ArrayList<Menu>();
    }

    public String getNama() {
        return this.nama;
    }
    
    public ArrayList<Menu> getMenu() {
        return this.menu;
    }

    public void addMenu(Menu item) {
        menu.add(item);
    }
    // TODO: tambahkan methods yang diperlukan untuk class ini
}
