package assignments.assignment3.copyassignment2;

import java.util.ArrayList;

import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class User {
    
    private String nama;
    private String nomorTelepon;
    private String email;
    private ArrayList<Order> orderHistory;
    private long saldo;
    private DepeFoodPaymentSystem payment;
    public String role;

    private String lokasi;
    public User(String nama, String nomorTelepon, String email, String lokasi, String role, DebitPayment payment, long saldo){
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.payment = payment;
        this.saldo = saldo;
        orderHistory = new ArrayList<>();
    }
    public User(String nama, String nomorTelepon, String email, String lokasi, String role, CreditCardPayment payment, long saldo){
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.payment = payment;
        this.saldo = saldo;
        orderHistory = new ArrayList<>();
    }
    public long getSaldo() {
        return saldo;
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
    public DepeFoodPaymentSystem getPayment() {
        return payment;
    }
    public void addOrderHistory(Order order){
        orderHistory.add(order);
    }
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
    public void setSaldo(long newSaldo) {
        saldo = newSaldo;
    }
    @Override
    public String toString() {
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
    }

}