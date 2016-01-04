/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import client.Pemain_ThreadSendJawaban;
import java.io.DataInputStream;

/**
 *
 * @author Ade Ilham Fajri
 */
public class SoalDariServer implements Runnable{
    
    Socket client_socket = null;
    PrintWriter pwPrintWriter;
    BufferedReader brBufferedReader = null;
  
    
    public SoalDariServer(Socket client_socket){
        this.client_socket = client_socket;
        
    }
    
  
    public static String[] gudangkata = {
        "michaelfaraday", "cristianoronaldo", 
        "lionelmessi", "markzuckerberg", "andyrubin","bayernmunich",
        "alberteinstein","kutu", "jetli"
    };
    
    public static String[] katakunci = {
        "Penemu Listrik", "pemain bola bernomor punggung 7 diReal Madrid",
        "Pemenang piala ballon d'or tahun 2013", "Penemu Facebook", "Penemu Android","Sebutan lain dari bayern munchen",
        "tokoh dunia","nama panggilan mahasiswa ithb jurusan SK", "nama panggilan mahasiswa ithb jurusan EL"  
    };

    SoalDariServer(SoalDariServer soal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    @Override
    public void run() {
      
        try{
            
            Scanner scan  = new Scanner(System.in);
            Random rand = new Random();

            int acak = 0;
            int kesempatan;
            String katapilihan = "";
         
            boolean ulangi = false;
            boolean loncat =  false;
            char pilih = 0, jawab = 0;
            String pilih2 = "";
            String[] ubah;
            boolean back;
            
            pwPrintWriter =new PrintWriter(new OutputStreamWriter(this.client_socket.getOutputStream()));
            
   //         do{
//                String msgToClientString = ("\n kesempatan anda menebak : ");    
                pwPrintWriter.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + "Game Tebak Kata\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "1. anda akan diberi kesempatan menebak 8 kali\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                pwPrintWriter.flush();
                
        //        lanjut();
                
                //pengacakan angka dengan angka maksimal panjang gudangkata
                acak = rand.nextInt(gudangkata.length);
                
                //mereset String katapilihan
                katapilihan = "";

                //memberi kesempatan 8x
                kesempatan = 8;

                //mengubah string ke array
                ubah = gudangkata[acak].split("");

                //mengganti semua kata dengan simbol '-'
                for(int c=0; c<= gudangkata[acak].length(); c++){
                    //proses penggantian
                    ubah[c] = "-";

                    //proses, kata yang sudah diganti lalu digabungkan ke dalam katapilihan
                    katapilihan = katapilihan.concat(ubah[c]);
                }
                
                //tempat perulangan proses berlangsungan permainan
                
                do{
                
                pwPrintWriter.println("\n kesempatan anda menebak : " + kesempatan);// untuk menampilkan jumlah kesempatan menebak
                pwPrintWriter.flush();

                pwPrintWriter.println(" Kata Kunci " + katakunci[acak]); // untuk menamplikan kata kunci sebagai pemandu penebakan
                pwPrintWriter.flush();


                pwPrintWriter.println(" Tebak kata apa ini : " + katapilihan); // unutk menampilkan kata yang harus ditebak
                pwPrintWriter.flush();

                pwPrintWriter.println(" Anda tebak : "); // inputan pilihan
                pwPrintWriter.flush();
                
               
                //proses menginputkan
               // pilih = scan.next().toLowerCase().charAt(0);
              //  System.out.println("pilih : " + pilih);
                
                //proses mengubah dari char ke string
                pilih2 = String.valueOf(pilih);
              //  System.out.println("pilih dua : " + pilih2);
                
                //memasukkan hal-hal penting ke method "proses"
                katapilihan = proses(katapilihan, pilih2, acak);
                
                //cek jawaban
                if(katapilihan.equals(gudangkata[acak])){
                    System.out.println("\n Selamat Jawaban Anda Benar !!");
                    break; //selesai dari permainan, hanya utk 1 kali permainan
                }
                
                //apabila kata yang diinputkan mengandung unsur kata yang dimaksud, maka counter kesempatan dilewati
                if(gudangkata[acak].contains(pilih2)){
                    continue; //melompat ke akhir permainan
                }
                
                //kesempatan akan minus 1 setiap jawabannya salah
                kesempatan--;
            }while(kesempatan > 0); //berhenti jika kesempatan == 0 
            
            //Jika kesempatan sudah habis, dan kata belum ketebak juga
           
            if(kesempatan==0 && !katapilihan.equals(gudangkata[acak])){
                System.out.println("\n Jawaban anda Salah !");
                System.out.println("Jawaban yang benar adalah : "+ gudangkata[acak]+"\n\n");
                lanjut(); //proses pemberhentikan sementara
            }

            
        }catch(Exception e){
            
        }
        
    }

    private void lanjut() {
        try {
            //  Scanner scan = new Scanner(System.in);
            char jawab=0; //deklarasi variable
               String messageString = "";
            brBufferedReader = new BufferedReader(new InputStreamReader(this.client_socket.getInputStream()));
            //tempat melakukan perulangan jika menjawab salah
            do{
                pwPrintWriter.println("Tekan \"c\" kemudian \"enter\" untuk melanjutkan...");
                pwPrintWriter.flush();
                messageString = brBufferedReader.readLine();
                //menerima & memproses inputan
                //     jawab = scan.next().toUpperCase().charAt(0);
            } while(!messageString.equals("EXIT")); //jika tidak   
        } catch (IOException ex) {
            Logger.getLogger(SoalDariServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private String proses(String katapilihan, String pilih2, int acak) {
        
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
            
            //menggabungkan semua array menjadi sebuah string
            katapilihan = katapilihan.concat(ubah[c]);
        }
        //mengembalikan nilai yang telah diproses
        return katapilihan; 
    }
}
