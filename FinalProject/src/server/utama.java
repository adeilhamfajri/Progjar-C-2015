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
public class utama {
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
    
    public static void main(String[] args) throws IOException {
        
        final int port = 414;
        ServerSocket server = new ServerSocket(port);
        
        while(true){
            Socket client_socket = server.accept();

          utama program = new utama();
          program.Run(client_socket);

        }        
    }

    private void Run(Socket client_socket) {
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
            KirimKeClient kirim = new KirimKeClient(kesempatan, kata_kunci, katapilihan, client_socket, sinyal);
            kirim.run();
            
            //Minta inputan dari Client
            JawabanClient jawaban = new JawabanClient(client_socket);
            pilih2 = jawaban.run();

            //memasukkan hal-hal penting ke method "proses"
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
         
        }while (kesempatan > 0 && pilih2!="");
        
        if(kesempatan==0 && !katapilihan.equals(gudangkata[acak])){
//                System.out.println("\n Jawaban anda Salah !");
//                System.out.println("Jawaban yang benar adalah : "+ gudangkata[acak]+"\n\n");
                pesan = "\nJawaban anda Salah !" + "\nJawaban yang benar adalah : " + gudangkata[acak]+"\n\n";
                KirimKeClient kirim = new KirimKeClient(client_socket);
                kirim.pesan(pesan);
    //            lanjut(client_socket); //proses pemberhentikan sementara
            }
        
        //nambah lagi ??
        do{
                back = false; //inisialisasi variable "back"
                
//                System.out.println("\n Apakah anda ngin mengulangi lagi ? [Y/N]");
                pesan = "\n Apakah anda ngin mengulangi lagi ? [ya/tidak]" + "\n==>";
                KirimKeClient ulang = new KirimKeClient(client_socket);
                ulang.pesan(pesan);
                
                
                JawabanClient jawaban = new JawabanClient(client_socket);
                lanjut = jawaban.run();
           
                
                if(lanjut.equals("ya")){
                    ulangi = true; //update nilai "ulang" jadi true
                }
                
                else if(lanjut.equals("tidak")){
                    ulangi = false; //update nilau "ulang" jadi false
                }
                
                else { //kalo  typo
                    back = true;
                }
              
            }while(back);
        }while(ulangi);
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

    private void lanjut(Socket client_socket) {
        
        JawabanClient jawaban = new JawabanClient(client_socket);
        String jawab = "";
        String pesan = " Ketik :\"lanjut\" kemudian \"enter\" untuk melanjutkan...";
        KirimKeClient kirim = new KirimKeClient(client_socket);
        kirim.pesan(pesan);
        
        //tempat melakukan perulangan jika menjawab salah
        do{
        //     System.out.print(" Ketik :\"lanjut\" kemudian \"enter\" untuk melanjutkan..."); //menampikan kata
             
             //menerima & memproses inputan
             jawab = jawaban.run();
        } while(!jawab.equals("lanjut")); //jika tidak
    }

}

