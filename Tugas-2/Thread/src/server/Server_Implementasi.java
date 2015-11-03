/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Ade Ilham Fajri
 */
class Server_Implementasi {
    
    private int port;
    private String default_path;
    
    Server_Implementasi(String default_path, int port) {
        this.default_path = default_path;
        this.port = port;
    }
    
     public void start() throws IOException {
          ServerSocket server = new ServerSocket(this.port);
          System.out.println("Server started listening on " + this.port);
          
          Socket socket = server.accept();
          Client_Thread client = new Client_Thread(socket, this.default_path);
          Thread client_thread = new Thread(client);
          client_thread.start();
            
 }
}
