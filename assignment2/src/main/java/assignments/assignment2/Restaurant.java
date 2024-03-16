package assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private ArrayList<Menu> menu;
    public Restaurant(String nama){
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }
    
    public ArrayList<Menu> getMenu() {
        return this.menu;
    }
    // TODO: tambahkan methods yang diperlukan untuk class ini
}
