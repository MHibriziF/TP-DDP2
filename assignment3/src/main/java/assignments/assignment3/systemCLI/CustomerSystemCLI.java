package assignments.assignment3.systemCLI;

<<<<<<< HEAD
import java.util.ArrayList;
import assignments.assignment3.copyassignment2.*;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;
import assignments.assignment3.MainMenu;

public class CustomerSystemCLI extends UserSystemCLI {
    private static ArrayList<Restaurant> restoList;
    private static User userLoggedIn;

    @Override
    public void run() {
        userLoggedIn = MainMenu.getUserLoggedIn();
        restoList = MainMenu.getRestoList();
        super.run();
    }

    @Override
    public boolean handleMenu(int choice){
        switch(choice){
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import assignments.assignment1.OrderGenerator;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class CustomerSystemCLI extends UserSystemCLI {

    @Override
    boolean handleMenu(int choice) {
        switch (choice) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            case 1 -> handleBuatPesanan();
            case 2 -> handleCetakBill();
            case 3 -> handleLihatMenu();
            case 4 -> handleBayarBill();
            case 5 -> handleCekSaldo();
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    @Override
<<<<<<< HEAD
    public void displayMenu() {
=======
    void displayMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

<<<<<<< HEAD
    void handleBuatPesanan(){
        boolean membuatPesanan = true;
        Restaurant restoran = null;
        String namaRestoran, tanggalPemesanan;
        int jumlahPesanan;
        System.out.println("--------------Buat Pesanan--------------");
        while (membuatPesanan) {
            // Menghandle input
            System.out.print("Nama Restoran: ");
            namaRestoran = input.nextLine();
            restoran = cariResto(namaRestoran);
            // Meminta input kembali jika restoran tidak ditemukan
            if (restoran == null) {
=======
    private void handleBuatPesanan() {
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
<<<<<<< HEAD
            tanggalPemesanan = input.nextLine();
            // Meminta input kembali jika format tanggal tidak valid
            if (OrderGenerator.checkDate(tanggalPemesanan) == false) {
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
=======
            String tanggalPemesanan = input.nextLine().trim();
            if (!OrderGenerator.validateDate(tanggalPemesanan)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                continue;
            }

            System.out.print("Jumlah Pesanan: ");
<<<<<<< HEAD
            jumlahPesanan = Integer.parseInt(input.nextLine());
            Menu[] pesanan = new Menu[jumlahPesanan];
            
            // Meminta orderan user
            boolean orderTaken = false;
            System.out.println("Order:");
            for (int i = 0; i < jumlahPesanan; i++) {
                orderTaken = false;
                String orderan = input.nextLine();
                for (Menu menu : restoran.getMenu()) {
                    if (menu.getNamaMakanan().equals(orderan)) {
                        pesanan[i] = menu;
                        orderTaken = true;
                        break;
                    }
                }
                // Break dari loop jika menu tidak tersedia
                if (!orderTaken) {
                    System.out.println("Mohon memesan menu yang tersedia di Restoran!\n");
                    break;
                }
            }

            // Membuat Order dan menambahkannya ke order history user jika order berhasil diambil
            // Meminta input ulang jika tidak berhasil
            if (orderTaken){
                int ongkir = hitungOngkir(userLoggedIn.getLokasi());
                String orderID = OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, userLoggedIn.getNomorTelepon());
                Order userOrder = new Order(orderID, tanggalPemesanan, ongkir, restoran, pesanan);
                userLoggedIn.addOrderHistory(userOrder);
                System.out.printf("Pesanan dengan ID %s diterima!", orderID);
                membuatPesanan = false;  
            } else {
                continue;
            }
        }
    }

    void handleCetakBill(){
        System.out.println("--------------Cetak Bill----------------");
        Order pesanan = inputOrderID();
        String bill = buatBill(pesanan);
        System.out.print(bill);
    }

    void handleLihatMenu(){
        System.out.println("--------------Lihat Menu--------------");
        Restaurant restoran = inputRestaurant();
        ArrayList<Menu> menuRestoran = restoran.getMenu();
        System.out.println("Menu:");
        for (int i = 1; i <= menuRestoran.size(); i++) {
            Menu item = menuRestoran.get(i-1);
            if (i == menuRestoran.size()) {
                System.out.printf("%d. %s %d", i, item.getNamaMakanan(), (int) item.getHarga());
                break;
            }
            System.out.printf("%d. %s %d\n", i, item.getNamaMakanan(), (int) item.getHarga());
        }
    }

    void handleBayarBill(){
        Order order = inputOrderID();
        if (order.getOrderFinished() == false) {
            System.out.println(buatBill(order));
            handleUpdateStatusPesanan(order);
        } else {
            System.out.println("Pesanan dengan ID ini sudah lunas!");
        }
    }

    void handleUpdateStatusPesanan(Order order){
        System.out.println();
        System.out.println("Opsi Pembayaran:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit");
        System.out.print("Pilihan Metode Pembayaran: ");
        String pilihanMetode = input.nextLine();

        DepeFoodPaymentSystem payment = userLoggedIn.getPayment();
        if (pilihanMetode.trim().equals("1")) {
            if (payment instanceof CreditCardPayment) {
                ((CreditCardPayment) payment).processPayment((long) order.getTotalHarga(), userLoggedIn, order);
            } else {
                System.out.print("User belum memiliki metode pembayaran ini!");
            }
        } else if (pilihanMetode.trim().equals("2")) {
            if (payment instanceof DebitPayment) {
                ((DebitPayment) payment).processPayment((long) order.getTotalHarga(), userLoggedIn, order);
            } else {
                System.out.print("User belum memiliki metode pembayaran ini!");
            }
        } else {
            System.out.print("Input hanya berupa angka 1 atau 2");
        }
    }

    void handleCekSaldo(){
        System.out.println();
        System.out.printf("Sisa saldo sebesar Rp %d", userLoggedIn.getSaldo());
    }

    public String buatBill(Order order) {
        String bill = "\nBill:\n";
        bill += String.format("Order ID: %s\n", order.getOrderId());
        bill += String.format("Tanggal Pemesanan: %s\n", order.getTanggal());
        bill += String.format("Restaurant: %s\n", order.getRestaurant().getNama());
        bill += String.format("Lokasi Pengiriman: %s\n", userLoggedIn.getLokasi());
        bill += String.format("Status Pengiriman: %s\n", order.getOrderFinished() ? "Selesai" : "Not Finished");
        bill += "Pesanan:\n"; 
        
        for (Menu item : order.getItems()) {
            bill += String.format("-%s %d\n", item.getNamaMakanan(), (int) item.getHarga());
        }

        bill += String.format("Biaya Ongkos Kirim: Rp %d\n", order.getOngkir());
        bill += String.format("Total Biaya: Rp %d", (int) order.getTotalHarga());
        return bill;
    }
    
    /**
     * Method ini digunakan untuk mendapatkan Order dari Order ID yang diinput user
     * @return Order pesanan user
     */
    public static Order inputOrderID() {
        boolean menginputOrderID = true;
        Order pesanan = null;
        String orderId;
        while (menginputOrderID) {
            System.out.print("Masukkan Order ID: ");
            orderId = input.nextLine();
            pesanan = cariOrder(orderId);
            if (pesanan == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            menginputOrderID = false;
        }
        return pesanan;
    }

    /**
     * Method ini digunakan untuk mendapatkan Restauran dari nama restoran yang diinput user
     * @return Restaurant yang diinput
     */
    public static Restaurant inputRestaurant() {
        boolean menginputResto = true;
        Restaurant restoran = null;
        String namaRestoran;
        while (menginputResto) {
            System.out.print("Nama Restoran: "  );
            namaRestoran = input.nextLine();
            restoran = cariResto(namaRestoran);
            if (restoran == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            } menginputResto = false;
        }
        return restoran;
    }

    /**
     * Method ini digunakan untuk mencari orderan user
     * @param orderID String Order ID user
     * @return objek Order yang dicari (null jika tidak ada) 
     */
    public static Order cariOrder(String orderID) {
        for (Order order : userLoggedIn.getOrderHistory()) {
            if (order.getOrderId().equals(orderID)) {
                return order;
            }
        }
        return null;
    }

    /** 
     * Method untuk mencari restoran
     * @param namaRestoran nama restoran yang dicari
     * @return Objek restoran yang dicari (null jika tidak ada)
     */
    public static Restaurant cariResto(String namaRestoran) {
        for (Restaurant resto: restoList) {
            if (resto.getNama().equalsIgnoreCase(namaRestoran)) {
                return resto;
            }
        }
        return null;
    }
    /**
     * Method ini digunakan untuk menghitung ongkir
     * @param lokasi String berupa lokasi user
     * @return
     */
    public static int hitungOngkir(String lokasi) {
        int ongkosKirim = 0;
        if (lokasi.equals("P")) {
            ongkosKirim = 10000; 
        } else if (lokasi.equals("U")) {
            ongkosKirim = 20000;
        } else if (lokasi.equals("T")) {
            ongkosKirim = 35000;
        } else if (lokasi.equals("S")) {
            ongkosKirim = 40000;
        } else if (lokasi.equals("B")) {
            ongkosKirim = 60000;
        }
        return ongkosKirim;
=======
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");

            List<String> listMenuPesananRequest = new ArrayList<>();

            for (int i = 0; i < jumlahPesanan; i++) {
                listMenuPesananRequest.add(input.nextLine().trim());
            }

            if (!validateRequestPesanan(restaurant, listMenuPesananRequest)) {
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            }

            Order order = new Order(
                    OrderGenerator.generateOrderID(restaurantName, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                    tanggalPemesanan,
                    OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()),
                    restaurant,
                    getMenuRequest(restaurant, listMenuPesananRequest));

            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
            return;
        }
    }

    private void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }

    }

    void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
    }

    void handleUpdateStatusPesanan(Order order) {
        order.setOrderFinished(true);
    }

    void handleBayarBill() {
        System.out.println("--------------Bayar Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();

            Order order = getOrderOrNull(orderId);

            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }

            if (order.getOrderFinished()) {
                System.out.println("Pesanan dengan ID ini sudah lunas!\n");
                return;
            }

            System.out.println(outputBillPesanan(order));

            System.out.println("Opsi Pembayaran:");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit");

            System.out.print("Pilihan Metode Pembayaran: ");
            String paymentOption = input.nextLine().trim();

            if (!paymentOption.equals("1") && !paymentOption.equals("2")) {
                System.out.println("Pilihan tidak valid, silakan coba kembali\n");
                continue;
            }

            DepeFoodPaymentSystem paymentSystem = userLoggedIn.getPaymentSystem();

            boolean isCreditCard = paymentSystem instanceof CreditCardPayment;

            if ((isCreditCard && paymentOption.equals("2")) || (!isCreditCard && paymentOption.equals("1"))) {
                System.out.println("User belum memiliki metode pembayaran ini!\n");
                continue;
            }

            long amountToPay = 0;

            try {
                amountToPay = paymentSystem.processPayment(userLoggedIn.getSaldo(), (long) order.getTotalHarga());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }

            long saldoLeft = userLoggedIn.getSaldo() - amountToPay;

            userLoggedIn.setSaldo(saldoLeft);
            handleUpdateStatusPesanan(order);

            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            decimalFormat.setDecimalFormatSymbols(symbols);

            System.out.printf("Berhasil Membayar Bill sebesar Rp %s", decimalFormat.format(amountToPay));

            return;
        }
    }

    void handleCekSaldo() {
        System.out.println("--------------Cek Saldo----------------");

        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);

        System.out.printf("Sisa saldo sebesar Rp %s", decimalFormat.format(userLoggedIn.getSaldo()));
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

}