package assignments.assignment2;

import java.util.ArrayList;

public class User {
    // Atribut class User
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
        this.orderHistory = new ArrayList<Order>();
    }

    // Method getter
    public String getNama() {
        return this.nama;
    }

    public String getNoTelepon() {
        return this.nomorTelepon;
    }

    public String getRole() {
        return this.role;
    }

    public String getLokasi() {
        return this.lokasi;
    }

    public ArrayList<Order> getOrderHistory() {
        return this.orderHistory;
    }

    // Method untuk menambahkan order ke orderHistory
    public void addOrderHistory(Order item) {
        this.orderHistory.add(item);
    }

}
