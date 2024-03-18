package assignments.assignment2;

import java.util.ArrayList;

public class Order {
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
        this.items = new ArrayList<Menu>();
        for (Menu item :items) {
            this.items.add(item);
        } 
    }

    public String getOrderID() {
        return this.orderID;
    }

    public String getTanggalPemesanan() {
        return this.tanggalPemesanan;
    }

    public int getOngkosKirim() {
        return this.biayaOngkosKirim;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public ArrayList<Menu> getItems() {
        return this.items;
    }

    public boolean getOrderFinished() {
        return this.orderFinished;
    }
}
