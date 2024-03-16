package assignments.assignment2;

import java.util.ArrayList;

public class Order {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String orderID;
    private String tanggalPemesanan;
    private int biayaOngkosKirim;
    private Restaurant restaurant;
    private ArrayList<Menu> items;
    private boolean orderFinished;
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items){
        this.orderID = orderId;
        this.tanggalPemesanan = tanggal;
        this.biayaOngkosKirim = ongkir;
        this.restaurant = resto;
        for (Menu item :items) {
            this.items.add(item);
        }
    }
    
    // TODO: tambahkan methods yang diperlukan untuk class ini
}
