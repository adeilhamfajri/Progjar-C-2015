/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.io.*;
import java.util.*;
import java.net.*;
/**
 *
 * @author Ade Ilham Fajri
 */
class Client_Implementasi {
    public void start() throws IOException {
        
        Socket socket = new Socket("localhost", 7777);
        try (InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            RecieverThread reciever = new RecieverThread(is);
            Thread reciever_thread = new Thread(reciever);
            reciever_thread.start();
            
            Scanner scanner = new Scanner(System.in);
            String test = scanner.nextLine();
            while (!test.contentEquals("EXIT")) {
                String len = Integer.toString(test.length());
                len += "\n";
                os.write(len.getBytes());
                os.flush();
                os.write(test.getBytes());
                os.flush();
                test = scanner.nextLine();
            }     
        }
    }
}