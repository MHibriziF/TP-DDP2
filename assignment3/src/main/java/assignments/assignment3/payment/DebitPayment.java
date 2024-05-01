package assignments.assignment3.payment;

import assignments.assignment3.copyassignment2.User;
import assignments.assignment3.copyassignment2.Order;
import assignments.assignment3.copyassignment2.Restaurant;

public class DebitPayment implements DepeFoodPaymentSystem{
    public static final double MINIMUM_TOTAL_PRICE = 500000;
    public void processPayment(long amount, User userLoggedIn, Order order) {
        Restaurant restaurant = order.getRestaurant(); // Mengambil restoran

        // Cek apakah biaya makanan sesuai harga minimum
        if (amount < MINIMUM_TOTAL_PRICE) {
            System.out.println("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
            return;
        }
        // Cek apakah jumlah saldo memenuhi
        if (userLoggedIn.getSaldo() < amount) {
            System.out.println("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
        } else {
            // Mengurangi saldo user dan menambah saldo restoran
            userLoggedIn.setSaldo(userLoggedIn.getSaldo() - amount);
            restaurant.setSaldo(restaurant.getSaldo() + amount);
            System.out.printf("Berhasil Membayar Bill sebesar Rp %d", amount);
            order.setOrderFinished(true);
        }
    }
}
