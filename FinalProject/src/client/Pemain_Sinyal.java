/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static client.Pemain_ThreadReceiveSoal.din;
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
public class Pemain_Sinyal {
    
    Socket client_socket = null;
    BufferedReader recieve=null;  
    String msgin = "";
    static DataInputStream din;

    Pemain_Sinyal(Socket client_socket) {
         this.client_socket = client_socket;
    }
    
    public void run() {
       
        if(client_socket.isConnected()){
            try {
                System.out.println("Bismillah");
                
                din = new DataInputStream(client_socket.getInputStream());
                msgin = din.readUTF();
                System.out.println(msgin);
                System.out.println("Ardanesia");
            } catch (IOException ex) {
                Logger.getLogger(Pemain_ThreadReceiveSoal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            System.out.println("Tidak terkoneksi");
        }
    }
    
}
