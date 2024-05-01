package assignments.assignment3.payment;

import assignments.assignment3.copyassignment2.User;
import assignments.assignment3.copyassignment2.Order;
import assignments.assignment3.copyassignment2.Restaurant;

public class CreditCardPayment implements DepeFoodPaymentSystem{
    public static final double TRANSACTION_FEE_PERCENTAGE = 0.02;

    public void processPayment(long amount, User userLoggedIn, Order order) {
        Restaurant restaurant = order.getRestaurant(); // Mengambil restoran
        long transactionFee = countTransactionFee(amount); // Hitung biaya transaksi

        // Mengurangi saldo user dan menambah saldo restoran
        userLoggedIn.setSaldo(userLoggedIn.getSaldo() - (amount + transactionFee));
        restaurant.setSaldo(restaurant.getSaldo() + amount + transactionFee);
        System.out.printf("Berhasil Membayar Bill sebesar Rp %d dengan biaya transaksi sebesar Rp %d", amount, transactionFee);
        order.setOrderFinished(true);
    }
    
    public long countTransactionFee(long amount) {
        return (long) (amount * TRANSACTION_FEE_PERCENTAGE);
    }
}
