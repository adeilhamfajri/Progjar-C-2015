/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ade Ilham Fajri
 */
public class Pemain_ThreadReceiveSoal implements Runnable{
    
    Socket client_socket = null;
    BufferedReader recieve=null;  
     String msgin = "";
     static DataInputStream din;
    
     public Pemain_ThreadReceiveSoal(Socket client_socket){
        this.client_socket = client_socket;

    }

    @Override
    public void run() {
       
        if(client_socket.isConnected()){
            try {
                System.out.println("terkoneksi");
                
                din = new DataInputStream(client_socket.getInputStream());
                msgin = din.readUTF();
                System.out.println(msgin);
            } catch (IOException ex) {
                Logger.getLogger(Pemain_ThreadReceiveSoal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            System.out.println("Tidak terkoneksi");
        }
    }
}
