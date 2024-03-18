package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;


public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;
    private static User userLoggedIn = null;

    public static void main(String[] args) {
        restoList = new ArrayList<Restaurant>();
        userList = new ArrayList<User>();
        initUser();
        printHeader();
        
        boolean programRunning = true;
        while(programRunning) {
            System.out.println();
            startMenu();
            int command = input.nextInt();
            input.nextLine();

            if(command == 1) {
                boolean isLoggedIn = false;
                String nama = null, noTelp = null;
                while (!isLoggedIn) {
                    System.out.println("\nSilakan Login:");
                    System.out.print("Nama: ");
                    nama = input.nextLine();
                    System.out.print("Nomor Telepon: ");
                    noTelp = input.nextLine();

                    if (!userExist(nama, noTelp)) {
                        System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                        continue;
                    }
                    userLoggedIn = getUser(nama, noTelp);
                    isLoggedIn = true;
                }

                if(userLoggedIn.getRole() == "Customer") {
                    System.out.printf("Selamat datang %s!", userLoggedIn.getNama());
                    while (isLoggedIn){
                        menuCustomer();
                        int commandCust = input.nextInt();
                        input.nextLine();

                        switch(commandCust) {
                            case 1 -> handleBuatPesanan();
                            case 2 -> handleCetakBill();
                            case 3 -> handleLihatMenu();
                            case 4 -> handleUpdateStatusPesanan();
                            case 5 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                } else {
                    System.out.printf("Selamat datang %s!", userLoggedIn.getNama());
                    while (isLoggedIn) {
                        menuAdmin();
                        int commandAdmin = input.nextInt();
                        input.nextLine();

                        switch(commandAdmin) {
                            case 1 -> handleTambahRestoran();
                            case 2 -> handleHapusRestoran();
                            case 3 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        } 
                    } 
                }  userLoggedIn = null;
            } else if (command == 2) {
                programRunning = false;
            } else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    public static User getUser(String nama, String nomorTelepon) {
        for (User user : userList) {
            if (user.getNama().equals(nama) && user.getNoTelepon().equals(nomorTelepon)) {
                return user;
            }
        }
        return null;
    }

    public static void handleBuatPesanan() {
        boolean membuatPesanan = true;
        Restaurant restoran = null;
        String namaRestoran, tanggalPemesanan;
        int jumlahPesanan;
        System.out.println("--------------Buat Pesanan--------------");
        while (membuatPesanan) {
            System.out.print("Nama Restoran: ");
            namaRestoran = input.nextLine();
            restoran = cariResto(namaRestoran);

            if (restoran == null) {
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
                for (Menu menu : restoran.getMenu()) {
                    if (menu.getNamaMakanan().equals(orderan)) {
                        pesanan[i] = menu;
                        orderTaken = true;
                        break;
                    }
                }
                if (!orderTaken) {
                    System.out.println("Mohon memesan menu yang tersedia di Restoran!\n");
                    break;
                }
            }

            if (orderTaken){
                int ongkir = hitungOngkir(userLoggedIn.getLokasi());
                String orderID = OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, userLoggedIn.getNoTelepon());
                Order userOrder = new Order(orderID, tanggalPemesanan, ongkir, restoran, pesanan);
                userLoggedIn.addOrderHistory(userOrder);
                System.out.printf("Pesanan dengan ID %s diterima!", orderID);
                membuatPesanan = false;  
            } else {
                continue;
            }
        }
    }

    public static void handleCetakBill() {
        Order pesanan = inputOrderID();
        double totalBiaya = 0;
        String bill = "Bill:\n";
        bill += String.format("Order ID: %s\n", pesanan.getOrderID());
        bill += String.format("Tanggal Pemesanan: %s\n", pesanan.getTanggalPemesanan());
        bill += String.format("Restaurant: %s\n", pesanan.getRestaurant().getNama());
        bill += String.format("Lokasi Pengiriman: %s\n", userLoggedIn.getLokasi());
        bill += String.format("Status Pengiriman: %s\n", pesanan.getOrderFinished() ? "Selesai" : "Not Finished");
        bill += "Pesanan:\n";

        for (Menu item : pesanan.getItems()) {
            bill += String.format("-%s %d\n", item.getNamaMakanan(),(int) item.getHarga());
            totalBiaya += item.getHarga();
        }
        totalBiaya += pesanan.getOngkosKirim();
        bill += String.format("Biaya Ongkos Kirim: Rp %d\n", pesanan.getOngkosKirim());
        bill += String.format("Total Biaya: Rp %d", (int) totalBiaya);
        System.out.print(bill);
    }

    public static void handleLihatMenu() {
        System.out.println("--------------Lihat Menu--------------");
        Restaurant restoran = inputRestaurant();
        ArrayList<Menu> menuRestoran = restoran.getMenu();
        for (int i = 1; i <= menuRestoran.size(); i++) {
            Menu item = menuRestoran.get(i-1);
            if (i == menuRestoran.size()) {
                System.out.printf("%d. %s %d", i, item.getNamaMakanan(), (int) item.getHarga());
                break;
            }
            System.out.printf("%d. %s %d\n", i, item.getNamaMakanan(), (int) item.getHarga());
        }
    }

    public static void handleUpdateStatusPesanan() {
        System.out.println("--------------Update Status Pesanan----------------");
        Order pesanan = inputOrderID();
        System.out.print("Status: ");
        String status = input.nextLine();
        if (status.equalsIgnoreCase("selesai") && !pesanan.getOrderFinished()){
            pesanan.finishOrder();
            System.out.printf("Status pesanan dengan ID %s berhasil diupdate!", pesanan.getOrderID());
        } else {
            System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate.", pesanan.getOrderID());
        }
    }

    public static void handleTambahRestoran() {
        boolean menambahResto = true;
        System.out.println("--------------Tambah Restoran----------------");
        while (menambahResto) {
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine();

            if (!validRestaurant(namaRestoran)) {
                System.out.println("Nama Restoran tidak valid!\n");
                continue;
            }

            if (cariResto(namaRestoran) != null) {
                System.out.printf("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!\n\n", namaRestoran);
                continue;
            }

            boolean menuInvalid = false; 
            System.out.print("Jumlah makanan: ");
            int jumlahMakanan = Integer.parseInt(input.nextLine());

            String[] arrayMakanan = new String[jumlahMakanan];
            double[] arrayHarga = new double[jumlahMakanan];
            for (int i = 0; i < jumlahMakanan; i++) {
                String userInput = input.nextLine();
                String[] parts = userInput.split(" ");
                // Mengecek apakah harga makanan bilangan bulat
                if (!isNumeric(parts[parts.length-1])) {
                    menuInvalid = true;
                } 
                // Mengekstrak nama makanan
                arrayMakanan[i] = "";
                for (int j = 0; j < parts.length-1;j++) {
                    arrayMakanan[i] += parts[j];
                    arrayMakanan[i] += j != parts.length - 2 ? " " : "";
                }
                // Mengekstrak harga makanan
                if (!menuInvalid) {
                    arrayHarga[i] = Double.parseDouble(parts[parts.length-1]);
                }
            }
        
            if (menuInvalid) {
                System.out.println("Harga menu harus bilangan bulat!\n");
                continue;
            }
            // Menambahkan restoran ke restoList
            Restaurant resto = new Restaurant(namaRestoran);
            restoList.add(resto);
            // Menambahkan menu-menu restoran
            for (int i = 0; i < jumlahMakanan; i++) {
                resto.addMenu(new Menu(arrayMakanan[i], arrayHarga[i]));
            }

            ArrayList<Menu> menuResto = resto.getMenu();
            sortMenu(menuResto, menuResto.size()); 
            System.out.printf("Restoran %s Berhasil terdaftar.", namaRestoran);
            menambahResto = false;
        } 
    }

    public static void handleHapusRestoran() {
        Restaurant restoran = inputRestaurant();
        restoList.remove(restoran);
        System.out.print("Restoran berhasil dihapus.");
    }

	public static void sortMenu(ArrayList<Menu> items, int n) {
        int i, j;
        Menu temp;
        boolean swapped;
        for (int r = 0; r < 2; r++) {
            for (i = 0; i < n - 1; i++) {
                swapped = false;
                for (j = 0; j < n - i - 1; j++) {
                    int hargaMakanan1 = (int) items.get(j).getHarga();
                    int hargaMakanan2 = (int) items.get(j+1).getHarga();
                    boolean biggerThan;
                    
                    if (r == 0) {
                        biggerThan = hargaMakanan1 > hargaMakanan2;
                    } else {
                        String makanan1 = items.get(j).getNamaMakanan();
                        String makanan2 = items.get(j+1).getNamaMakanan();
                        biggerThan = hargaMakanan1 == hargaMakanan2 && makanan1.compareTo(makanan2) > 0;
                    }

                    if (biggerThan) {
                        temp = items.get(j);
                        items.set(j, items.get(j+1)); 
                        items.set(j+1, temp);
                        swapped = true;
                    }
                }
                if (swapped == false)
                    break;
            }
        }
    }

    public static Order inputOrderID() {
        boolean menginputOrderID = true;
        Order pesanan = null;
        String orderID;
        while (menginputOrderID) {
            System.out.print("Masukkan Order ID: ");
            orderID = input.nextLine();
            pesanan = cariOrder(orderID);
            if (pesanan == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            menginputOrderID = false;
        }
        return pesanan;
    }

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

    public static boolean isNumeric(String str) {
        for (int i = 0;i < str.length();i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static Order cariOrder(String orderID) {
        for (Order order : userLoggedIn.getOrderHistory()) {
            if (order.getOrderID().equals(orderID)) {
                return order;
            }
        }
        return null;
    }

    public static boolean validRestaurant(String nama) {
        nama = nama.replaceAll(" ", "");
        if (nama.length() >= 4) {
            return true;
        }
        return false;
    }

    public static boolean userExist(String nama, String noTelp) {
        for (User user : userList) {
            if (user.getNama().equals(nama) && user.getNoTelepon().equals(noTelp)) {
                return true;
            }
        }
        return false;
    }

    public static Restaurant cariResto(String namaRestoran) {
        for (Restaurant resto: restoList) {
            if (resto.getNama().equalsIgnoreCase(namaRestoran)) {
                return resto;
            }
        }
        return null;
    }

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
    }

    public static void initUser() {
       userList = new ArrayList<User>();
       userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
       userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
       userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
       userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
       userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

       userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
       userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    public static void printHeader() {
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu() {
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuCustomer() {
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
