/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static client.client.dout;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.SoalDariServer;

/**
 *
 * @author Ade Ilham Fajri
 */
public class Pemain_ThreadSendJawaban extends Thread implements Serializable {
    
    Socket client_socket = null;
    PrintWriter print=null;
    BufferedReader brinput=null;
    static DataOutputStream dout;
    
    public Pemain_ThreadSendJawaban(Socket client_socket){
        this.client_socket = client_socket;
          
    }


    @Override
    public void run() {
        
        if(client_socket.isConnected()){
              
            try {
                System.out.println("Client connected to "+client_socket.getInetAddress() + " on port "+client_socket.getPort());
               
                brinput = new BufferedReader(new InputStreamReader(System.in));
                String msgout=null;
                msgout = brinput.readLine();
                
                
                dout = new DataOutputStream(client_socket.getOutputStream());
                dout.writeUTF(msgout); 
                
                
            } catch (IOException ex) {
                System.out.println("Gagal masuk !!");
            }
        
        }
    }
        
}

