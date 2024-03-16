package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;


public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;
    private static User userLoggedIn = null;

    public static void main(String[] args) {
        initUser();
        boolean programRunning = true;
        while(programRunning){
            printHeader();
            startMenu();
            int command = input.nextInt();
            input.nextLine();

            if(command == 1){
                System.out.println("\nSilakan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine();

                if (!userExist(nama)) {
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    continue;
                }

                userLoggedIn = getUser(nama, noTelp);
                boolean isLoggedIn = true;

                if(userLoggedIn.getRole() == "Customer"){
                    while (isLoggedIn){
                        menuCustomer();
                        int commandCust = input.nextInt();
                        input.nextLine();

                        switch(commandCust){
                            case 1 -> handleBuatPesanan();
                            case 2 -> handleCetakBill();
                            case 3 -> handleLihatMenu();
                            case 4 -> handleUpdateStatusPesanan();
                            case 5 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                }else{
                    while (isLoggedIn){
                        menuAdmin();
                        int commandAdmin = input.nextInt();
                        input.nextLine();

                        switch(commandAdmin){
                            case 1 -> handleTambahRestoran();
                            case 2 -> handleHapusRestoran();
                            case 3 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        } userLoggedIn = null;
                    }
                }
            }else if(command == 2){
                programRunning = false;
            }else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    public static User getUser(String nama, String nomorTelepon){
        for (User user : userList) {
            if (user.getNama().equals(nama) && user.getNoTelepon().equals(nomorTelepon)) {
                return user;
            }
        }
        return null;
    }

    public static void handleBuatPesanan(){
        boolean membuatPesanan = true, restaurantExist = false;
        Restaurant restaurant = null;
        String namaRestoran, tanggalPemesanan;
        int jumlahPesanan;
        System.out.println("--------------Buat Pesanan--------------");
        while (membuatPesanan) {
            System.out.print("Nama Restoran: ");
            namaRestoran = input.nextLine();
            for (Restaurant resto: restoList) {
                if (resto.getNama().equals(namaRestoran)) {
                    restaurantExist = true;
                    restaurant = resto;
                }
            }

            if (!restaurantExist) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            tanggalPemesanan = input.nextLine();
            if (OrderGenerator.checkDate(tanggalPemesanan) == false) {
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
                continue;
            }

            System.out.print("Jumlah Pesanan: ");
            jumlahPesanan = Integer.parseInt(input.nextLine());
            Menu[] pesanan = new Menu[jumlahPesanan];
            
            boolean orderTaken = false;
            System.out.println("Order:");
            for (int i = 0; i < jumlahPesanan; i++) {
                orderTaken = false;
                String orderan = input.nextLine();
                for (Menu menu : restaurant.getMenu()) {
                    if (menu.getNamaMakanan().equals(orderan)) {
                        orderTaken = true;
                    }
                }
                if (!orderTaken) {
                    System.out.println("Mohon memesan menu yang tersedia di Restoran!\n");
                    break;
                }
            }
            if (orderTaken) {
                int ongkir = hitungOngkir();
                String orderID = OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, userLoggedIn.getNoTelepon());
                Order userOrder = new Order(orderID, tanggalPemesanan, ongkir, restaurant, pesanan);
            } else {
                continue;
            }
        }
        

    }

    public static void handleCetakBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin cetak bill
    }

    public static void handleLihatMenu(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
    }

    public static void handleUpdateStatusPesanan(){
        // TODO: Implementasi method untuk handle ketika customer ingin update status pesanan
    }

    public static void handleTambahRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
    }

    public static void handleHapusRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
    }

    public static boolean userExist(String nama){
        for (User user : userList) {
            if (user.getNama().equals(nama)) {
                return true;
            }
        }
        return false;
    }

    public static int hitungOngkir() {
        return 0;
    }

    public static void initUser(){
       userList = new ArrayList<User>();
       userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
       userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
       userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
       userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
       userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

       userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
       userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    public static void printHeader(){
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu(){
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuCustomer(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }
}
