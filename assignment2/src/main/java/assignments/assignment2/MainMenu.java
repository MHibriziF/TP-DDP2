package assignments.assignment2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
<<<<<<< HEAD

=======
import assignments.assignment1.OrderGenerator;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;
<<<<<<< HEAD
    private static User userLoggedIn = null;

    public static void main(String[] args) {
        restoList = new ArrayList<Restaurant>();
        userList = new ArrayList<User>();
        initUser();
        printHeader();
=======
    private static User userLoggedIn;

    public static void main(String[] args) {
        restoList = new ArrayList<>();
        initUser();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        boolean programRunning = true;
        while(programRunning) {
            System.out.println();
            startMenu();
            int command = input.nextInt();
            input.nextLine();

<<<<<<< HEAD
            if(command == 1) {
                boolean isLoggedIn = false;
                String nama = null, noTelp = null;
                // Meminta data untuk login
                while (!isLoggedIn) {
                    System.out.println("\nSilakan Login:");
                    System.out.print("Nama: ");
                    nama = input.nextLine();
                    System.out.print("Nomor Telepon: ");
                    noTelp = input.nextLine();
                    userLoggedIn = getUser(nama, noTelp);
                    // Meminta input ulang jika user tidak ditemukan
                    if (userLoggedIn == null) {
                        System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                        continue;
                    }
                    isLoggedIn = true;
                }

                if(userLoggedIn.getRole() == "Customer") {
                    System.out.printf("Selamat datang %s!", userLoggedIn.getNama());
=======
            if(command == 1){
                System.out.println("\nSilakan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine();
                userLoggedIn = getUser(nama, noTelp);
                boolean isLoggedIn = true;
                if(userLoggedIn == null){
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    isLoggedIn = false;
                }
                else if(userLoggedIn.role == "Customer"){
                    System.out.println("Selamat Datang "+userLoggedIn.getNama()+"!");
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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
<<<<<<< HEAD
                } else {
                    System.out.printf("Selamat datang %s!", userLoggedIn.getNama());
                    while (isLoggedIn) {
=======
                }else{
                    System.out.println("Selamat Datang "+userLoggedIn.getNama()+"!");
                    while (isLoggedIn){
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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
<<<<<<< HEAD
            } else {
=======
            }
            else{
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

<<<<<<< HEAD
    /**
     * Method ini digunakan untuk mendapatkan user
     * @param nama String nama user
     * @param nomorTelepon String nomort telepon user
     * @return Objek User yang dicari (null jika tidak ada)
     */
    public static User getUser(String nama, String nomorTelepon) {
        for (User user : userList) {
            if (user.getNama().equals(nama) && user.getNoTelepon().equals(nomorTelepon)) {
=======
    public static User getUser(String nama, String nomorTelepon){

        for(User user: userList){
            if(user.getNama().equals(nama.trim()) && user.getNomorTelepon().equals(nomorTelepon.trim())){
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                return user;
            }
        }
        return null;
    }

<<<<<<< HEAD
    /**
     * Method ini digunakan untuk membuat pesanan user
     */
    public static void handleBuatPesanan() {
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
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            tanggalPemesanan = input.nextLine();
            // Meminta input kembali jika format tanggal tidak valid
            if (OrderGenerator.checkDate(tanggalPemesanan) == false) {
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
                continue;
            }

            System.out.print("Jumlah Pesanan: ");
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

    /**
     * Method ini digunakan untuk mencetak bill user
     */
    public static void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");
        Order pesanan = inputOrderID();
        double totalBiaya = 0;
        // Membuat bill
        String bill = "Bill:\n";
        bill += String.format("Order ID: %s\n", pesanan.getOrderID());
        bill += String.format("Tanggal Pemesanan: %s\n", pesanan.getTanggalPemesanan());
        bill += String.format("Restaurant: %s\n", pesanan.getRestaurant().getNama());
        bill += String.format("Lokasi Pengiriman: %s\n", userLoggedIn.getLokasi());
        bill += String.format("Status Pengiriman: %s\n", pesanan.getOrderFinished() ? "Selesai" : "Not Finished");
        bill += "Pesanan:\n"; 
        
        // Menghitung biaya
        for (Menu item : pesanan.getItems()) {
            bill += String.format("-%s %d\n", item.getNamaMakanan(),(int) item.getHarga());
            totalBiaya += item.getHarga();
        }

        totalBiaya += pesanan.getOngkosKirim();
        bill += String.format("Biaya Ongkos Kirim: Rp %d\n", pesanan.getOngkosKirim());
        bill += String.format("Total Biaya: Rp %d", (int) totalBiaya);
        System.out.println();
        System.out.print(bill);
    }

    /**
     * Method ini digunakan untuk melihat menu restoran yang dicari
     */
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

    /**
     * Method ini digunakan untuk mengupdate status pesanan
     */
    public static void handleUpdateStatusPesanan() {
        System.out.println("--------------Update Status Pesanan----------------");
        Order pesanan = inputOrderID();
        System.out.print("Status: ");
        String status = input.nextLine();
        // Mengupdate status pesanan jika status selesai dan beda dengan status sebelumnya
        if (status.equalsIgnoreCase("selesai") && !pesanan.getOrderFinished()){
            pesanan.finishOrder();
            System.out.printf("Status pesanan dengan ID %s berhasil diupdate!", pesanan.getOrderID());
        } else {
            System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate.", pesanan.getOrderID());
        }
    }

    /**
     * Method ini digunakan untuk menambah restoran
     */
    public static void handleTambahRestoran() {
        boolean menambahResto = true;
        System.out.println("--------------Tambah Restoran----------------");
        while (menambahResto) {
            System.out.print("Nama: ");
            String namaRestoran = input.nextLine();
            // Mengecek kevaliditas restoran
            if (!validRestaurant(namaRestoran)) {
                System.out.println("Nama Restoran tidak valid!\n");
                continue;
            }
            // Mengecek jika restoran sudah ada atau tidak
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
            // Pengecekan validitas input
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
            // Mensortir menu
            ArrayList<Menu> menuResto = resto.getMenu();
            sortMenu(menuResto, menuResto.size()); 
            System.out.printf("Restaurant %s Berhasil terdaftar.", namaRestoran);
            menambahResto = false;
        } 
    }

    /**
     * Method ini digunakan untuk menghapus restoran 
     */
    public static void handleHapusRestoran() {
        System.out.println("--------------Hapus Restoran----------------");
        Restaurant restoran = inputRestaurant();
        restoList.remove(restoran);
        System.out.print("Restoran berhasil dihapus.");
=======
    public static void handleBuatPesanan(){
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if(restaurant == null){
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine().trim();
            if(!OrderGenerator.validateDate(tanggalPemesanan)){
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }
            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");
            List<String> listMenuPesananRequest = new ArrayList<>();
            for(int i=0; i < jumlahPesanan; i++){
                listMenuPesananRequest.add(input.nextLine().trim());
            }
            if(! validateRequestPesanan(restaurant, listMenuPesananRequest)){
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            };
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

    public static boolean validateRequestPesanan(Restaurant restaurant, List<String> listMenuPesananRequest){
        return listMenuPesananRequest.stream().allMatch(pesanan -> 
            restaurant.getMenu().stream().anyMatch(menu -> menu.getNamaMakanan().equals(pesanan))
        );
    }

    public static Menu[] getMenuRequest(Restaurant restaurant, List<String> listMenuPesananRequest){
        Menu[] menu = new Menu[listMenuPesananRequest.size()];
        for(int i=0;i<menu.length;i++){
            for(Menu existMenu : restaurant.getMenu()){
                if(existMenu.getNamaMakanan().equals(listMenuPesananRequest.get(i))){
                    menu[i] = existMenu;
                }
            }
        }
        return menu;
    }

    public static String getMenuPesananOutput(Order order){
        StringBuilder pesananBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        for (Menu menu : order.getSortedMenu()) {
            pesananBuilder.append("- ").append(menu.getNamaMakanan()).append(" ").append(decimalFormat.format(menu.getHarga())).append("\n");
        }
        if (pesananBuilder.length() > 0) {
            pesananBuilder.deleteCharAt(pesananBuilder.length() - 1);
        }
        return pesananBuilder.toString();
    }

    public static String outputBillPesanan(Order order) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return String.format("Bill:%n" +
                         "Order ID: %s%n" +
                         "Tanggal Pemesanan: %s%n" +
                         "Lokasi Pengiriman: %s%n" +
                         "Status Pengiriman: %s%n"+
                         "Pesanan:%n%s%n"+
                         "Biaya Ongkos Kirim: Rp %s%n"+
                         "Total Biaya: Rp %s%n",
                         order.getOrderId(),
                         order.getTanggal(),
                         userLoggedIn.getLokasi(),
                         !order.getOrderFinished()? "Not Finished":"Finished",
                         getMenuPesananOutput(order),
                         decimalFormat.format(order.getOngkir()),
                         decimalFormat.format(order.getTotalHarga())
                         );
    }



    public static Restaurant getRestaurantByName(String name){
        Optional<Restaurant> restaurantMatched = restoList.stream().filter(restoran -> restoran.getNama().toLowerCase().equals(name.toLowerCase())).findFirst();
        if(restaurantMatched.isPresent()){
            return restaurantMatched.get();
        }
        return null;
    }

    public static void handleCetakBill(){
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if(order == null){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }
        
    }

    public static Order getOrderOrNull(String orderId) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
                }
            }
        }
        return null;
    }

    public static void handleLihatMenu(){
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if(restaurant == null){
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
    }

    public static void handleUpdateStatusPesanan(){
        System.out.println("--------------Update Status Pesanan---------------");
        while (true) {
            System.out.print("Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if(order == null){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.print("Status: ");
            String newStatus = input.nextLine().trim();
            if(newStatus.toLowerCase().equals("SELESAI".toLowerCase())){
                if(order.getOrderFinished() == true){
                    System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!", order.getOrderId());
                }
                else{
                    System.out.printf("Status pesanan dengan ID %s berhasil diupdate!", order.getOrderId());
                    order.setOrderFinished(true);
                }
            }
            return;
        }

    }

    public static void handleTambahRestoran(){
        System.out.println("--------------Tambah Restoran---------------");
        Restaurant restaurant = null;
        while (restaurant == null) {
            String namaRestaurant = getValidRestaurantName();
            restaurant = new Restaurant(namaRestaurant);
            restaurant = handleTambahMenuRestaurant(restaurant);
        }
        restoList.add(restaurant);
        System.out.print("Restaurant "+restaurant.getNama()+" Berhasil terdaftar." );
    }

    public static Restaurant handleTambahMenuRestaurant(Restaurant restoran){
        System.out.print("Jumlah Makanan: ");
        int  jumlahMenu = Integer.parseInt(input.nextLine().trim());
        boolean isMenuValid = true;
        for(int i = 0; i < jumlahMenu; i++){
            String inputValue = input.nextLine().trim();
            String[] splitter = inputValue.split(" ");
            String hargaStr = splitter[splitter.length-1];
            boolean isDigit = checkIsDigit(hargaStr);
            String namaMenu = String.join(" ", Arrays.copyOfRange(splitter, 0, splitter.length - 1));
            if(isDigit){
                int hargaMenu = Integer.parseInt(hargaStr);
                restoran.addMenu(new Menu(namaMenu, hargaMenu));
            }
            else{
                isMenuValid = false;
            }
        }
        if(!isMenuValid){
            System.out.println("Harga menu harus bilangan bulat!");
            System.out.println();
        }

        return isMenuValid? restoran : null; 
    }

    public static boolean checkIsDigit(String digits){
        return  digits.chars().allMatch(Character::isDigit);
    }
    
    public static String getValidRestaurantName() {
        String name = "";
        boolean isRestaurantNameValid = false;
    
        while (!isRestaurantNameValid) {
            System.out.print("Nama: ");
            String inputName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream()
                    .anyMatch(restoran -> restoran.getNama().toLowerCase().equals(inputName.toLowerCase()));
            boolean isRestaurantNameLengthValid = inputName.length() >= 4;
    
            if (isRestaurantExist) {
                System.out.printf("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!%n", inputName);
                System.out.println();
            } else if (!isRestaurantNameLengthValid) {
                System.out.println("Nama Restoran tidak valid! Minimal 4 karakter diperlukan.");
                System.out.println();
            } else {
                name = inputName;
                isRestaurantNameValid = true;
            }
        }
        return name;
    }
    

    public static void handleHapusRestoran(){
        System.out.println("--------------Hapus Restoran----------------");
        boolean isActionDeleteEnded = false;
        while (!isActionDeleteEnded) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream().anyMatch(restaurant -> restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase()));
            if(!isRestaurantExist){
                System.out.println("Restoran tidak terdaftar pada sistem.");
                System.out.println();
            }
            else{
                restoList = new ArrayList<>(restoList.stream().filter(restaurant-> !restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase())).toList());
                System.out.println("Restoran berhasil dihapus");
                isActionDeleteEnded = true;
            }
        }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    /**
     * Method ini digunakan untuk mensortir menu restoran berdasarkan harga
     * <p>Jika terdapat item dengan harga sama, akan disortir secara alfabet
     * @param items ArrayList<Menu> yang ingin disortir
     * @param n panjang ArrayList
     */
	public static void sortMenu(ArrayList<Menu> items, int n) {
        Menu temp;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                int hargaMakanan1 = (int) items.get(j).getHarga();
                int hargaMakanan2 = (int) items.get(j+1).getHarga();

                // Mensortir menu berdasarkan harga. Mensortir secara alfabet jika harga sama
                if (hargaMakanan1 == hargaMakanan2) {
                    String makanan1 = items.get(j).getNamaMakanan();
                    String makanan2 = items.get(j+1).getNamaMakanan();
                    if (makanan1.compareTo(makanan2) > 0) {
                        // Menukar Menu 
                        temp = items.get(j);
                        items.set(j, items.get(j+1)); 
                        items.set(j+1, temp);
                        swapped = true;
                    } 
                } else if (hargaMakanan1 > hargaMakanan2) {
                    // Menukar Menu 
                    temp = items.get(j);
                    items.set(j, items.get(j+1)); 
                    items.set(j+1, temp);
                    swapped = true;
                }
            }
            // Jika tidak ada pertukaran, Menu telah tersortir
            if (swapped == false) {
                break;
            } 
        }
    }

    /**
     * Method ini digunakan untuk mendapatkan Order dari Order ID yang diinput user
     * @return Order pesanan user
     */
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
     * Method ini digunakan untuk mengecek apakah string merupakan numerik
     * @param str String yang akan dicek
     * @return boolean value isNumeric
     */
    public static boolean isNumeric(String str) {
        for (int i = 0;i < str.length();i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method ini digunakan untuk mencari orderan user
     * @param orderID String Order ID user
     * @return objek Order yang dicari (null jika tidak ada) 
     */
    public static Order cariOrder(String orderID) {
        for (Order order : userLoggedIn.getOrderHistory()) {
            if (order.getOrderID().equals(orderID)) {
                return order;
            }
        }
        return null;
    }

    /**
     * Method ini digunakan untuk mengecek nama restoran valid atau tidak
     * @param nama String nama restoran
     * @return boolean valid atau tidaknya restoran
     */
    public static boolean validRestaurant(String nama) {
        nama = nama.replaceAll(" ", "");
        if (nama.length() >= 4) {
            return true;
        }
        return false;
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
