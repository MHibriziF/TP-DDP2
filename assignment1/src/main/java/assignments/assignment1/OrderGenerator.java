package assignments.assignment1;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
<<<<<<< HEAD
=======
    private static final int ORDER_ID_LENGTH = 16;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5

    /*
     * Anda boleh membuat method baru sesuai kebutuhan Anda
     * Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method
     * yang sudah ada.
     */
<<<<<<< HEAD
    public static void showMenu() {
=======

    /*
     * Method ini untuk menampilkan DepeFood
     */
    public static void initMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.err.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
    }

    /*
     * Method ini untuk menampilkan menu
     */
    public static void showMenu() {
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    /*
     * Method ini digunakan untuk menampilkan pilihan menu
     */
    public static void showMenu2() {
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    /*
     *  Method ini digunakan untuk mencetak pembatas antar pilihan menu
     */
    public static void printBarrier() {
        System.out.println("--------------------------------------------");
    }

    /*
     * Method ini digunakan untuk mengekstrak informasi
     * tanggal yang diberikan user sehingga menjad DDMMYYYY
     * 
     * @return String berupa tanggal pemesanan tanpa '/'
     */
    public static String getKodeTanggal(String tanggalOrder) {
        // Mengekstrak informasi tanggal, bulan, dan tahun
        String tanggal = tanggalOrder.substring(0, 2); 
        String bulan = tanggalOrder.substring(3, 5);
        String tahun = tanggalOrder.substring(6, 10);
        return tanggal + bulan + tahun;
    }

    /*
     * Method ini digunakan untuk mendapatkan 2 angka
     * kode berdasarkan no. telepon yang diberi user
     * 
     * @return String berupa 2 angka kode telepon
     */
    public static String getKodeTelepon(String noTelepon) {
        String kodeTelepon = "";
        int jumlahanDigit = 0; 
        for (int i = 0; i < noTelepon.length(); i++) {
            jumlahanDigit += (int) noTelepon.charAt(i) - 48;
        }
        jumlahanDigit = jumlahanDigit % 100;
        kodeTelepon += (char) (jumlahanDigit / 10 + 48); 
        kodeTelepon += (char) (jumlahanDigit % 10 + 48);
        return kodeTelepon;
    } 

    /* 
     * Method ini digunakan untuk
     * mengkalkulasikan dua karakter checksumnya
     * 
     * @return String berupa dua buah karakter checksum
     */
    public static String checksum(String currentOrderID) {
        int checksumGanjil = 0, checksumGenap = 0;
        String finalTwoCharacter = "";

        // Menghitung checksum genap dan checksum ganjil
        for (int i = 0; i < currentOrderID.length(); i++) {
            if (i % 2 == 0) {
                checksumGenap += charToCode39(currentOrderID.charAt(i));
            }
            else {
                checksumGanjil += charToCode39(currentOrderID.charAt(i));
            }
        }
        checksumGenap = checksumGenap % 36;
        checksumGanjil = checksumGanjil % 36;
        finalTwoCharacter += "" + code39ToChar(checksumGenap) + code39ToChar(checksumGanjil);
        return finalTwoCharacter;
    }

    /*
     * Method ini digunakan untuk mengkonversi karakter ke kode-39 nya
     * 
     * @return integer berupa kode-39 dari karakter
     */
    public static int charToCode39(char character) {
        String code39Set = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Konversi ke kode-39
        for (int i = 0; i < code39Set.length(); i++) {
            if (code39Set.charAt(i) == character) {
                return i;
            }
        }
        return 0;
    }

    /*
     * Method ini digunakan untuk mengkonversi angka 
     * kode-39 ke karakter yang sesuai
     * 
     * @return kode-39 yang telah dikonversi ke karakter
     */
    public static char code39ToChar(int code39) {
        if (code39 < 10) {
            code39 += 48; // Ditambah 48 karena ASCII '0' adalah 48 
        }
        else {
            code39 += 55; // Ditambah 55 karena nilai ASCII 'A' = 65 dan 'A' dalam code39 adalah 10 sehingga 65-10 = 55
        }
        return (char) code39;
    }

    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     *
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
<<<<<<< HEAD
        String orderID = "";
        orderID += namaRestoran.substring(0, 4).toUpperCase();
        orderID += getKodeTanggal(tanggalOrder);
        orderID += getKodeTelepon(noTelepon);
        orderID += checksum(orderID);
        return orderID;
    }
=======
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5

        String restaurantCode = getRestaurantCode(namaRestoran);
        String formattedDate = formatDate(tanggalOrder);
        String phoneNumberChecksum = getPhoneNumberChecksum(noTelepon);

        String id = restaurantCode + formattedDate + phoneNumberChecksum;
        id = id.toUpperCase();
        String checksum = calculateChecksum(id);

        return id + checksum;
    }

    /*
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     *
     * @return String Bill dengan format sesuai di bawah:
     * Bill:
     * Order ID: [Order ID]
     * Tanggal Pemesanan: [Tanggal Pemesanan]
     * Lokasi Pengiriman: [Kode Lokasi]
     * Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
<<<<<<< HEAD
    public static String generateBill(String orderID, String lokasi){
        String ongkosKirim = "";
        lokasi = lokasi.toUpperCase();

        // Memberikan ongkos kirim sesuai lokasi
        if (lokasi.equals("P")) {
            ongkosKirim = "Rp 10.000"; 
        } else if (lokasi.equals("U")) {
            ongkosKirim = "Rp 20.000";
        } else if (lokasi.equals("T")) {
            ongkosKirim = "Rp 35.000";
        } else if (lokasi.equals("S")) {
            ongkosKirim = "Rp 40.000";
        } else if (lokasi.equals("B")) {
            ongkosKirim = "Rp 60.000";
        }

        // Menyusun tanggal pemesanan dari Order ID
        String tanggalPemenesanan = orderID.substring(4, 6) + "/" + orderID.substring(6, 8) + "/" + orderID.substring(8, 12); 

        // Menyusun bill
        String bill = String.format("Bill:\n");
        bill += String.format("Order ID: %s\n", orderID);
        bill += String.format("Tanggal Pemesanan: %s\n", tanggalPemenesanan);
        bill += String.format("Lokasi Pengiriman: %s\n", lokasi);
        bill += String.format("Biaya Ongkos Kirim: %s\n", ongkosKirim);

        return bill;
=======
    public static String generateBill(String OrderID, String lokasi) {
        String formattedDate = OrderID.substring(4, 12);
        String tanggalPemesanan = formattedDate.substring(0, 2) + "/" + formattedDate.substring(2, 4) + "/"
                + formattedDate.substring(4, 8);

        return outputBill(OrderID, tanggalPemesanan, lokasi, calculateDeliveryCost(lokasi));
    }

    public static boolean validateRestaurantName(String restaurantName) {
        return restaurantName != null && !restaurantName.isEmpty() && getRestaurantCode(restaurantName).length() >= 4;
    }

    public static boolean validateDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return false;
        }

        for (String part : parts) {
            if (!part.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }

        return parts[0].length() == 2 && parts[1].length() == 2 && parts[2].length() == 4;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.chars().allMatch(Character::isDigit);
    }

    public static boolean validateLocation(String location) {
        char[] locationList = { 'P', 'U', 'T', 'S', 'B' };

        return location.length() == 1 && new String(locationList).contains(location);
    }

    public static boolean validateOrderID(String orderID) {
        if (orderID.length() != ORDER_ID_LENGTH) {
            System.out.println("Order ID minimal 16 karakter");
            return false;
        }

        if (!orderID.chars().allMatch(Character::isLetterOrDigit) || !checkIfChecksumValid(orderID)) {
            System.out.println("Silahkan masukkan Order ID yang valid!");
            return false;
        }

        return true;
    }

    public static boolean checkIfChecksumValid(String id) {
        String idWithoutChecksum = id.substring(0, id.length() - 2);
        String checksum = id.substring(id.length() - 2);

        return calculateChecksum(idWithoutChecksum).equals(checksum);
    }

    public static String getRestaurantCode(String restaurantName) {
        String[] words = restaurantName.split(" ");

        StringBuilder code = new StringBuilder();

        for (String word : words) {
            code.append(word);
        }

        return code.substring(0, Math.min(code.length(), 4));
    }

    public static String formatDate(String date) {
        String[] parts = date.split("/");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        return day + month + year;
    }

    public static String getPhoneNumberChecksum(String phoneNumber) {
        int sum = 0;
        for (char c : phoneNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        int checksum = sum % 100;
        return (checksum < 10) ? "0" + checksum : String.valueOf(checksum);
    }

    public static String calculateChecksum(String id) {
        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            int numericValue = getNumericValue(c);
            if (i % 2 == 0) {
                sumEven += numericValue;
            } else {
                sumOdd += numericValue;
            }
        }
        int remainderEven = sumEven % 36;
        int remainderOdd = sumOdd % 36;
        return reverseAssignment(remainderEven) + reverseAssignment(remainderOdd);
    }

    public static int getNumericValue(char c) {
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        } else {
            return c - 'A' + 10;
        }
    }

    public static String reverseAssignment(int remainder) {
        String code39CharacterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(code39CharacterSet.charAt(remainder));
    }

    public static int calculateDeliveryCost(String location) {
        switch (location) {
            case "P":
                return 10000;
            case "U":
                return 20000;
            case "T":
                return 35000;
            case "S":
                return 40000;
            case "B":
                return 60000;
            default:
                return 0;
        }
    }

    public static String outputBill(String orderID, String tanggalPemesanan, String lokasiPengiriman,
            int biayaOngkosKirim) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        decimalFormat.setDecimalFormatSymbols(symbols);

        return "Bill:\n" + "Order ID: " + orderID + "\n" + "Tanggal Pemesanan: " + tanggalPemesanan + "\n"
                + "Lokasi Pengiriman: " + lokasiPengiriman + "\n" + "Biaya Ongkos Kirim: Rp "
                + decimalFormat.format(biayaOngkosKirim) + "\n";
    }

    /*
     * Method ini untuk memproses ID Order
     */
    public static void processGenerateOrder() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine().toUpperCase();
            if (!validateRestaurantName(namaRestoran)) {
                System.out.println("Nama Restoran tidak valid!");
                continue;
            }

            System.out.print("Tanggal Pemesanan: ");
            String tanggalOrder = input.nextLine();

            if (!validateDate(tanggalOrder)) {
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
                continue;
            }

            System.out.print("No. Telpon: ");
            String noTelepon = input.nextLine();
            if (!validatePhoneNumber(noTelepon)) {
                System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
                continue;
            }

            System.out.println(
                    "Order ID " + generateOrderID(namaRestoran, tanggalOrder, noTelepon) + " diterima!");

            isInputValid = true;
        }
    }

    /*
     * Method ini untuk memproses generate bill
     */
    public static void processGenerateBill() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Order ID: ");
            String orderID = input.nextLine().toUpperCase();
            if (!validateOrderID(orderID)) {
                continue;
            }

            System.out.print("Lokasi Pengiriman: ");
            String lokasi = input.nextLine().toUpperCase();
            if (!validateLocation(lokasi)) {
                System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                continue;
            }

            System.out.println(generateBill(orderID, lokasi));
            isInputValid = true;
        }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    /*
     * Method ini digunakan untuk mengecek apakah
     * Order ID merupakan Order ID yang valid atau tidak
     * 
     * @return boolean berupa kevalidan Order ID
     */
    public static boolean checkOrderID(String orderID) {
        // Mengecek karakter angka pada Order ID (8 karakter tanggal dan 2 karakter telepon) 
        for (int i = 4; i < 14; i++) {
            if (!Character.isDigit(orderID.charAt(i))) {
                return false;
            }
        }
        // Mengecek kesesuaian checksum pada OrderID yang diberi dan Order Id yang benar
        String givenChecksum = orderID.substring(14, 16);
        String correctChecksum = checksum(orderID.substring(0, 14));

        return givenChecksum.equals(correctChecksum); // Order ID valid jika checksum yang diberi benar
    }

    /*
     * Method ini digunakan untuk mengecek apakah
     * tanggal yang diberikan user valid atau tidak
     * 
     * @return boolean berupa kevalidan tanggal
     */
    public static boolean checkDate(String date) {
        if (date.length() != 10) {
            return false;
        }
        if (date.charAt(2) != '/' || date.charAt(5) != '/') {
            return false;
        }
        return true;
    }

    /*
     * Method ini digunakan untuk mengecek kevalidan
     * nomor telepon yang diinput oleh user
     * 
     * @return boolean berupa kevalidan no telepon yang diberi
     */
    public static boolean checkTelephoneNo(String noTelepon) {
        for (int i = 0;i < noTelepon.length();i++) {
            if (!Character.isDigit(noTelepon.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
<<<<<<< HEAD
        // Inisialisasi variable
        String pilihanMenu = "";
        String namaRestoran, tanggalPemesanan, noTelepon;
        showMenu();

        // Meminta input selama belum memilih keluar (3)
        while (!pilihanMenu.equals("3")) {
            printBarrier();

            // Meminta pilihan user
            System.out.print("Pilihan menu: ");
            pilihanMenu = input.nextLine();

            // User memilih untuk mengenerate Order ID
            if (pilihanMenu.equals("1")) {

                // Meminta input selama melakukan pemesanan
                boolean melakukanPemesanan = true;
                while (melakukanPemesanan) {
                    System.out.println();

                    // Meminta nama restoran dan menguji kevalidannya
                    System.out.print("Nama Restoran: ");
                    namaRestoran = input.nextLine();
                    namaRestoran = namaRestoran.replaceAll(" ", "");
                    if (namaRestoran.length() < 4) {
                        System.out.println("Nama Restoran tidak valid!");
                        continue;
                    }

                    // Meminta tanggal pemesanan dan menguji kevalidannya
                    System.out.print("Tanggal Pemesanan: ");
                    tanggalPemesanan = input.nextLine();
                    if (checkDate(tanggalPemesanan) == false) {
                        System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
                        continue;
                    }

                    // Meminta no. telepon dan menguji kevalidannya
                    System.out.print("No. Telpon: ");
                    noTelepon = input.nextLine();
                    if (checkTelephoneNo(noTelepon) == false) {
                        System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
                        continue;
                    }

                    // Mengenerate Order ID setelah semua input valid
                    String orderID = generateOrderID(namaRestoran, tanggalPemesanan, noTelepon);
                    System.out.printf("Order ID %s diterima!%n", orderID);
                    
                    // Selesai memesan
                    melakukanPemesanan = false;
                }
            }

            // User memilih untuk mengenerate bill
            else if (pilihanMenu.equals("2")) {
                String orderID, lokasiPengiriman;

                // Meminta input selama melakukan billing
                boolean melakukanBilling = true;
                while (melakukanBilling) {
                    // Meminta Order ID dan menguji kevalidannya
                    System.out.print("Order ID: ");
                    orderID = input.nextLine();
                    if (orderID.length() < 16) {
                        System.out.println("Order ID minimal 16 karakter\n");
                        continue;
                    }
                    if (checkOrderID(orderID) == false) {
                        System.out.println("Silahkan masukkan Order ID yang valid!\n");
                        continue;
                    }

                    // Meminta lokasi pengiriman dan menguji kejangkauannya
                    System.out.print("Lokasi Pengiriman: ");
                    lokasiPengiriman = input.nextLine().toUpperCase();
                    if (!lokasiPengiriman.equals("P") && !lokasiPengiriman.equals("U") &&
                    !lokasiPengiriman.equals("T") && !lokasiPengiriman.equals("S") && !lokasiPengiriman.equals("B")) {
                        System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                        continue;
                    }

                    // Mengenerate bill setelah semua input valid
                    String bill = generateBill(orderID, lokasiPengiriman);
                    System.out.println();
                    System.out.print(bill);

                    // Selesai melakukan billing
                    melakukanBilling = false;
                }
            } 
            
            // User memilih untuk keluar
            else if (pilihanMenu.equals("3")) {
                System.out.println("Terima kasih telah menggunakan DepeFood!");
                break;
            }
            printBarrier();
            showMenu2();
        }
        input.close();
    }   
=======
        boolean isRunning = true;

        initMenu();
        while (isRunning) {
            showMenu();
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan Menu: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    processGenerateOrder();
                    break;
                case 2:
                    processGenerateBill();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println("--------------------------------------------");
        }
    }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
}
