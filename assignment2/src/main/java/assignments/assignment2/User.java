package assignments.assignment2;

import java.util.ArrayList;

public class User {
<<<<<<< HEAD
    // Atribut class User
    private String nama;
    private String nomorTelepon;
    private String email;
    private String lokasi; 
    private String role;
    private ArrayList<Order> orderHistory;

=======
    
    private String nama;
    private String nomorTelepon;
    private String email;
    private ArrayList<Order> orderHistory;
    public String role;

    private String lokasi;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
<<<<<<< HEAD
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
=======
        orderHistory = new ArrayList<>();
    }
    public String getEmail() {
        return email;
    }
    public String getNama() {
        return nama;
    }
    public String getLokasi() {
        return lokasi;
    }
    public String getNomorTelepon() {
        return nomorTelepon;
    }
    public void addOrderHistory(Order order){
        orderHistory.add(order);
    }
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
    public boolean isOrderBelongsToUser(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

}
