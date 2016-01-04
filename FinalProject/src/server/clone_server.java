

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import server.KirimKeClient;
import static server.Soal.dout;
import client.client;
import java.util.Scanner;
/**
 *
 * @author Ade Ilham Fajri
 */
public class clone_server implements Runnable{
    static DataInputStream din;
    private static String[] gudangkata = {
        "michaelfaraday", "cristianoronaldo", 
        "lionelmessi", "markzuckerberg", "andyrubin","bayernmunich",
        "alberteinstein","kutu", "jetli"
    };
    
    //deklarasi variable array kata kunci
    private static String[] katakunci = {
        "Penemu Listrik", "pemain bola bernomor punggung 7 diReal Madrid",
        "Pemenang piala ballon d'or tahun 2013", "Penemu Facebook", "Penemu Android","Sebutan lain dari bayern munchen",
        "tokoh dunia","nama panggilan mahasiswa ithb jurusan SK", "nama panggilan mahasiswa ithb jurusan EL"  
    };
    
    Random rand = new Random();
        
    int acak = 0;
    static int kesempatan;
    String katapilihan = "";
    boolean ulangi = false;
    boolean loncat =  false;
    char pilih = 0, jawab = 0;
    String pilih2 = "";
    String lanjut = "";
    String pesan = "";
    String[] ubah;
    boolean back;
    String sinyal = "terus";
    int ulang=3;
    
    public static void main(String[] args) throws IOException {
        
        final int port = 414;
        ServerSocket server = new ServerSocket(port);
        
        while(true){
            Socket client_socket = server.accept();

          clone_server program = new clone_server(client_socket);
          Thread thread_1 = new Thread(program);
          thread_1.start();
//          program.Run(client_socket);

        }        
    }
    Socket client_socket = null;
    private clone_server(Socket client_socket) {
        this.client_socket = client_socket;
    }

     @Override
    public void run() {
        PrintWriter pwPrintWriter;
        BufferedReader brBufferedReader = null;
        
        
        //method untuk pemberhenti kompiler
        //lanjut();
        pesan = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "Game Tebak Kata" 
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\n                                       anda diberi kesempatan menebak 8 kali" + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
     //   KirimKeClient opening = new KirimKeClient(client_socket);
     //   opening.pesan(pesan);
        
        //pengacakan angka dengan angka maksimal panjang gudangkata
        do{
        acak = rand.nextInt(gudangkata.length);

        //mereset String katapilihan
        katapilihan = "";

        //memberi kesempatan 8x
        kesempatan = 8;

        //mengubah string ke array
        ubah = gudangkata[acak].split("");
        
        for(int c=0; c<= gudangkata[acak].length(); c++){
                //proses penggantian
                ubah[c] = "-";
                
                //proses, kata yang sudah diganti lalu digabungkan ke dalam katapilihan
                katapilihan = katapilihan.concat(ubah[c]);
            }
        String kata_kunci = katakunci[acak];
        String pesan;
       
        do{
            
            pilih2 = ""; //kita reset jadi kosongan lagi, setiap selesai looping
            kata_kunci = katakunci[acak];
             //Ngirim Soal ke Client
            System.out.println("Yang dikirim ke Client : " + katapilihan);
            KirimKeClient kirim = new KirimKeClient(kesempatan, kata_kunci, katapilihan, client_socket);
            kirim.run();
            System.out.println("\n");
            //Minta inputan dari Client
            JawabanClient jawaban = new JawabanClient(client_socket);
            pilih2 = jawaban.run();

            //memasukkan hal-hal penting ke method "proses"
            System.out.println("Jawaban dari Client : " + pilih2);
            katapilihan = proses(katapilihan, pilih2, acak);
            System.out.println("Kata Pilihan  : " + katapilihan);
            //apabila kata yang diinputkan mengandung unsur kata yang dimaksud, maka counter kesempatan dilewati
            
            if(katapilihan.equals(gudangkata[acak])){
                   // System.out.println("\n Selamat Jawaban Anda Benar !!");
                    pesan = "\nSelamat Jawaban Anda Benar !!";
                    kirim.pesan(pesan);
                    break; //selesai dari permainan, hanya utk 1 kali permainan
       
                }
            
            //apabila kata yang diinputkan mengandung unsur kata yang dimaksud, maka counter kesempatan dilewati
            if(gudangkata[acak].contains(pilih2)){
                continue; //melompat ke akhir permainan
            }
                
         kesempatan--;
         
        }while (kesempatan > 0);
        
        if(kesempatan==0 && !katapilihan.equals(gudangkata[acak])){

                pesan = "\nJawaban anda Salah !" + "\nJawaban yang benar adalah : " + gudangkata[acak]+"\n\n";
                KirimKeClient kirim = new KirimKeClient(client_socket);
                kirim.pesan(pesan);
            }

        ulang--;
        
        }while(ulang > 0);
        System.out.println("Games Over !");
    }

    private static String proses(String katapilihan, String pilih2, int acak) {
        
        //mengubah string biasa ke String Array untuk mengecek jawaban yang benar
        String[] ubah2 = gudangkata[acak].split("");
        
        //Mengubah string biasa ke String Array pada kata yang ditanyakan
        String[] ubah = katapilihan.split("");
        
        //reset katapilihan agar tidak jadi tumpukan
        katapilihan = "";
        
        for(int c=0; c<=gudangkata[acak].length(); c++){
            //mengecek apabila kata yang diinputkan apakah sama dengan kata yang sebenarnya
            if(ubah2[c].equalsIgnoreCase(pilih2)){
                ubah[c] = ubah2[c];
            }
        
            katapilihan = katapilihan.concat(ubah[c]);
    }
         return katapilihan; 
    }

    private void lanjut() {
        
        katapilihan="";
         for(int c=0; c<= gudangkata[acak].length(); c++){
                //proses penggantian
                ubah[c] = "-";
                
                //proses, kata yang sudah diganti lalu digabungkan ke dalam katapilihan
                katapilihan = katapilihan.concat(ubah[c]);
                System.out.println("Fungsi lanjut : " + katapilihan);
            }
        
    }
}

