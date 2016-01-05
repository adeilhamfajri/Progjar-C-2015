/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Ade Ilham Fajri
 */
class BacaSerialization {
    
    
    int port =414;
        Socket client_socket;
    BacaSerialization(Socket client_socket) {
        this.client_socket = client_socket;
    }
    
    public static void main(String args[]) throws IOException{
        
        InputStream IS = client_socket.getInputStream();
        
    }
}
