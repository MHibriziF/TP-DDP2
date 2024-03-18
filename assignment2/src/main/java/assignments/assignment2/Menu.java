package assignments.assignment2;

public class Menu {
    // Atribut class Menu
    private String namaMakanan;
    private double harga;
    public Menu(String namaMakanan, double harga){
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    // Method getter
    public String getNamaMakanan() {
        return this.namaMakanan;
    }

    public double getHarga() {
        return this.harga;
    }
}
