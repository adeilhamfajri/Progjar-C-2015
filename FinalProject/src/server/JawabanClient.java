/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Pemain_ThreadReceiveSoal;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ade Ilham Fajri
 */
class JawabanClient {
    Socket client_socket = null;
    BufferedReader recieve=null;  
     String msgin = "";
     String jawaban;
     static DataInputStream din;

    JawabanClient(Socket client_socket) {
        this.client_socket = client_socket;
    }
    
    public String run() {
       
        if(client_socket.isConnected()){
            try {
                din = new DataInputStream(client_socket.getInputStream());
                msgin = din.readUTF();
                jawaban = msgin;   
                System.out.println("Ngecek jawaban client");
                System.out.println(jawaban);
            } catch (IOException ex) {
                Logger.getLogger(Pemain_ThreadReceiveSoal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            System.out.println("Tidak terkoneksi");
        }
        return jawaban;
    }
     
     
}
