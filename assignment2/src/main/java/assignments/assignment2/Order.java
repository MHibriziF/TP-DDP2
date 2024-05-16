package assignments.assignment2;

import java.util.ArrayList;

public class Order {
<<<<<<< HEAD
    // Atribut class Order
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

    // Method Getter
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
=======

    private String orderId;
    private String tanggal;
    private int ongkir;
    private Restaurant restaurant;
    private boolean orderFinished;
    private Menu[] items;

    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items) {
        this.orderId = orderId;
        this.tanggal = tanggal;
        this.ongkir = ongkir;
        this.restaurant = resto;
        this.orderFinished = false;
        this.items = items;
    }

    public Restaurant getRestaurant() {
        return restaurant;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    public boolean getOrderFinished() {
        return this.orderFinished;
    }

<<<<<<< HEAD
    // Method untuk menyelesaikan order
    public void finishOrder() {
        this.orderFinished = true;
=======
    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getOngkir() {
        return ongkir;
    }

    public Menu[] getItems() {
        return items;
    }

    public Menu[] getSortedMenu() {
        Menu[] menuArr = new Menu[getItems().length];
        for (int i = 0; i < getItems().length; i++) {
            menuArr[i] = getItems()[i];
        }
        int n = menuArr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (menuArr[j].getHarga() > menuArr[j + 1].getHarga()) {

                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j + 1];
                    menuArr[j + 1] = temp;
                }
            }
        }
        return menuArr;
    }

    public double getTotalHarga() {
        double sum = 0;
        for (Menu menu : getItems()) {
            sum += menu.getHarga();
        }
        return sum += getOngkir();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }
}
