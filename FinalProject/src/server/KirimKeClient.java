/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Serialization.Serialization;

/**
 *
 * @author Ade Ilham Fajri
 */
public class KirimKeClient {
    
    Socket client_socket;
    String katapilihan;
    String katakunci;
    String sinyal;
    int kesempatan;
    int score;

//    public KirimKeClient(Socket client_socket, String katapilihan, String katakunci) {
//        this.client_socket = client_socket;
//        this.katapilihan = katapilihan;
//        this.katakunci = katakunci;
//    }
    static DataOutputStream dout;
    
//    public KirimKeClient(Socket client_socket){
//        this.client_socket = client_socket;
//    }

    public KirimKeClient(int kesempatan, String katakunci, String katapilihan, Socket client_socket, int score) {
        this.katakunci = katakunci; 
        this.client_socket = client_socket; 
        this.katapilihan = katapilihan; 
        this.kesempatan = kesempatan; 
        this.score = score;
    }

    public KirimKeClient(Socket client_socket) {
       this.client_socket = client_socket;
    }


  //  @Override
    public void run() {
 
        try {
       //     String msgout = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n Game Tebak Kata\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n 1. anda akan diberi kesempatan menebak 8 kali\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
                String x = "Score anda : " + score;
                String a = "\nkesempatan anda menebak : " + kesempatan; 
                String b = "\nKata Kunci : " + katakunci; 
                String c = "\nTebak kata apa ini : " + katapilihan; 
                String d = " \nAnda tebak : ";
                String msgout = x + a + b + c + d;
            //Serialization GAGAL total
  //          OutputStream OS = client_socket.getOutputStream();
    //        ObjectOutputStream OOS = new ObjectOutputStream(OS);
      
//      int kesempatan, String katakunci, String katapilihan, Socket client_socket
     //       Serialization serial = new Serialization(kesempatan, katakunci, katapilihan);
    //        OOS.writeObject(serial);
            
            dout = new DataOutputStream(client_socket.getOutputStream());
            dout.writeUTF(msgout);
            dout.flush();
       
        } catch (IOException ex) {
            Logger.getLogger(KirimKeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pesan(String pesan) {
 
        try {
       
            String msgout = pesan;
                
            dout = new DataOutputStream(client_socket.getOutputStream());
            dout.writeUTF(msgout);
                  
       
        } catch (IOException ex) {
            Logger.getLogger(KirimKeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void soal(String katapilihan, String katakunci, int kesempatan){
        
    }
    
    public void sinyal(String sinyal){
        try {
            String sinyalku = sinyal;
            System.out.println("Sinyal di KirimKeClient : " + sinyalku);
            dout = new DataOutputStream(client_socket.getOutputStream());
            dout.writeUTF(sinyalku);
        } catch (IOException ex) {
            Logger.getLogger(KirimKeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
