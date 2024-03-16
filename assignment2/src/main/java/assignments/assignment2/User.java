package assignments.assignment2;

import java.util.ArrayList;

public class User {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String nomorTelepon;
    private String email;
    private String lokasi; 
    private String role;
    private ArrayList<Order> orderHistory;
    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
    }
    
    public String getNama() {
        return this.nama;
    }

    public String getNoTelepon() {
        return this.nomorTelepon;
    }

    public String getRole() {
        return this.role;
    }
    // TODO: tambahkan methods yang diperlukan untuk class ini
}
