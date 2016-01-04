/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.server.din;

/**
 *
 * @author Ade Ilham Fajri
 */
class MenerimaDariClient implements Runnable {
    
     Socket client_socket = null;
     String msgin = "";
     static DataInputStream din;

    MenerimaDariClient(Socket client_socket) {
        this.client_socket = client_socket;
    }
             
    @Override
    public void run() {
        
             DataInputStream dIn = null;
         try {
             //Kode untuk DataInputStream
  
             din = new DataInputStream(client_socket.getInputStream());
             msgin = din.readUTF();
             System.out.println(msgin);
         } catch (IOException ex) {
             Logger.getLogger(MenerimaDariClient.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
         }
         }
             
         
    }
    

