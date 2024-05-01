package assignments.assignment3.systemCLI;

import java.util.ArrayList;
import java.util.Arrays;
import assignments.assignment3.MainMenu;
import assignments.assignment3.copyassignment2.*;

public class AdminSystemCLI extends UserSystemCLI{
    private static ArrayList<Restaurant> restoList;

    @Override
    public void run() {
        restoList = MainMenu.getRestoList();
        super.run();
    }

    @Override
    public boolean handleMenu(int command){
        switch(command){
            case 1 -> handleTambahRestoran();
            case 2 -> handleHapusRestoran();
            case 3 -> {return false;}
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    protected void handleTambahRestoran(){
        System.out.println("--------------Tambah Restoran---------------");
        Restaurant restaurant = null;
        // Meminta input hingga valid
        while (restaurant == null) {
            String namaRestaurant = getValidRestaurantName();
            restaurant = new Restaurant(namaRestaurant);
            restaurant = handleTambahMenuRestaurant(restaurant);
        }
        // Menambahkan restoran
        restoList.add(restaurant);
        System.out.print("Restaurant " + restaurant.getNama() +" Berhasil terdaftar." );
    }

    protected void handleHapusRestoran(){
        System.out.println("--------------Hapus Restoran----------------");
        // Menerima input restoran
        Restaurant restoran = inputRestaurant();
        // Menghappus restoran
        restoList.remove(restoran);
        System.out.print("Restoran berhasil dihapus.");
    }

    public static Restaurant handleTambahMenuRestaurant(Restaurant restoran){
        System.out.print("Jumlah Makanan: ");
        int  jumlahMenu = Integer.parseInt(input.nextLine().trim());
        boolean isMenuValid = true;
        for(int i = 0; i < jumlahMenu; i++){
            // Menerima input makanan
            String inputValue = input.nextLine().trim();
            String[] splitter = inputValue.split(" ");
            String hargaStr = splitter[splitter.length-1];
            boolean isDigit = checkIsDigit(hargaStr);
            String namaMenu = String.join(" ", Arrays.copyOfRange(splitter, 0, splitter.length - 1));

            // Jika bagian terakhir bukan digit, tambahkan ke menu
            if(isDigit){
                int hargaMenu = Integer.parseInt(hargaStr);
                restoran.addMenu(new Menu(namaMenu, hargaMenu));
            }
            else{
                isMenuValid = false;
            }
        }
        // Cek apakah menu valid
        if(!isMenuValid){
            System.out.println("Harga menu harus bilangan bulat!");
            System.out.println();
        }

        return isMenuValid? restoran : null; 
    }

    public static String getValidRestaurantName() {
        String name = "";
        // Menerima input hingga nama restoran valid
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

    public static boolean checkIsDigit(String digits){
        return  digits.chars().allMatch(Character::isDigit);
    }

    /**
     * Method ini digunakan untuk mendapatkan Restauran dari nama restoran yang diinput user
     * @return Restaurant yang diinput
     */
    public static Restaurant inputRestaurant() {
        boolean menginputResto = true;
        Restaurant restoran = null;
        String namaRestoran;
        // Meminta input restoran hingga valid
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
}
