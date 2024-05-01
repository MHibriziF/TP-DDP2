package assignments.assignment3.copyassignment2;

import java.util.Scanner;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);

    /*
     * Method  ini untuk menampilkan menu
     */
    public static void showMenu() {
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
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
        String orderID = "";
        orderID += namaRestoran.substring(0, 4).toUpperCase();
        orderID += getKodeTanggal(tanggalOrder);
        orderID += getKodeTelepon(noTelepon);
        orderID += checksum(orderID);
        return orderID;
    }


    /*
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     * 
     * @return String Bill dengan format sesuai di bawah:
     *          Bill:
     *          Order ID: [Order ID]
     *          Tanggal Pemesanan: [Tanggal Pemesanan]
     *          Lokasi Pengiriman: [Kode Lokasi]
     *          Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
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
}
