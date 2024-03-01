package assignments.assignment1;

import java.util.Scanner;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);

    /* 
    Anda boleh membuat method baru sesuai kebutuhan Anda
    Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method yang sudah ada.
    */

    /*
     * Method  ini untuk menampilkan menu
     */
    public static void showMenu(){
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

    public static void showMenu2(){
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    public static void printBarrier() {
        System.out.println("--------------------------------------------");
    }

    public static String getKodeTanggal(String tanggalOrder){
        String tanggal = tanggalOrder.substring(0, 2);
        String bulan = tanggalOrder.substring(3, 5);
        String tahun = tanggalOrder.substring(6, 10);
        return tanggal + bulan + tahun;
    }

    public static String getKodeTelepon(String noTelepon){
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

    public static String checksum(String currentOrderID){
        int checksumGanjil = 0, checksumGenap = 0;
        String finalTwoCharacter = "";
        for (int i = 0; i < currentOrderID.length(); i++) {
            if (i % 2 == 0){
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

    public static int charToCode39(char character){
        String code39Set = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < code39Set.length(); i++) {
            if (code39Set.charAt(i) == character) {
                return i;
            }
        }
        return 0;
    }

    public static char code39ToChar(int code39){
        if (code39 < 10){
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
        // TODO:Lengkapi method ini sehingga dapat mengenerate Bill sesuai ketentuan
        return "Bill";
    }

    public static boolean checkDate(String date){
        if (date.length() != 10){
            return false;
        }
        if (date.charAt(2) != '/' || date.charAt(5) != '/'){
            return false;
        }
        return true;
    }
    public static boolean checkTelephoneNo(String noTelepon){
        for (int i = 0;i < noTelepon.length();i++){
            if (!Character.isDigit(noTelepon.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        String pilihanMenu = "";
        String namaRestoran, tanggalPemesanan, noTelepon;
        showMenu();
        while (!pilihanMenu.equals("3")){
            printBarrier();
            System.out.print("Pilihan menu: ");
            pilihanMenu = input.nextLine();
            if (pilihanMenu.equals("1")){
                boolean melakukanPemesanan = true;
                while (melakukanPemesanan){
                    System.out.println();
                    System.out.print("Nama Restoran: ");
                    namaRestoran = input.nextLine();
                    namaRestoran = namaRestoran.replaceAll(" ", "");
                    if (namaRestoran.length() < 4) {
                        System.out.println("Nama Restoran tidak valid!");
                        continue;
                    }

                    System.out.print("Tanggal Pemesanan: ");
                    tanggalPemesanan = input.nextLine();
                    if (checkDate(tanggalPemesanan) == false){
                        System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
                        continue;
                    }

                    System.out.print("No. Telpon: ");
                    noTelepon = input.nextLine();
                    if (checkTelephoneNo(noTelepon) == false){
                        System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
                        continue;
                    }
                    String orderID = generateOrderID(namaRestoran, tanggalPemesanan, noTelepon);
                    System.out.printf("Order ID %s diterima!%n", orderID);
                    melakukanPemesanan = false;
                }
            }
            else if (pilihanMenu.equals("2")){

            }

            else if (pilihanMenu.equals("3")){
                System.out.println("Terimakasih telah menggunakan DepeFood!");
                break;
            }
            printBarrier();
            showMenu2();
        }
    }   
}
