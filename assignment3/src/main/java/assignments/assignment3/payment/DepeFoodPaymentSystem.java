package assignments.assignment3.payment;

import assignments.assignment3.copyassignment2.Order;
import assignments.assignment3.copyassignment2.User;

public interface DepeFoodPaymentSystem {
    public abstract void processPayment(long amount, User userLoggedIn, Order order);
}
