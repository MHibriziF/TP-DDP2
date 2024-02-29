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

    public static void printBarrier(){
        System.out.println("--------------------------------------------");
    }
    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     * 
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        // TODO:Lengkapi method ini sehingga dapat mengenerate Order ID sesuai ketentuan
        return "TP";
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
        for (int i = 0;i < noTelepon.length();i++) {
            if (!Character.isDigit(noTelepon.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.setErr(System.out);
        String pilihanMenu = "";
        String namaRestoran, tanggalPemesanan, noTelepon;
        showMenu();
        while (!pilihanMenu.equals("3")) {
            printBarrier();
            System.out.print("Pilihan menu: ");
            pilihanMenu = input.nextLine();
            if (pilihanMenu.equals("1")) {
                
            }
            else if (pilihanMenu.equals("2")) {
                boolean melakukanPemesanan = true;
                while (melakukanPemesanan){
                    System.out.println();
                    System.out.print("Nama Restoran: ");
                    namaRestoran = input.nextLine();
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
                }


            }
            else if (pilihanMenu.equals("3")){
                System.out.println("Terimakasih telah menggunakan DepeFood!");
                break;
            }
            printBarrier();
        }
    }   
}
