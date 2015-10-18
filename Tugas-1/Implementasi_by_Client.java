/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.net.*;
import java.io.*;
import java.util.*;


/**
 *
 * @author Ade Ilham Fajri
 */
class Implementasi_by_Client {
    public void start() throws IOException{
        
            Scanner scanner = new Scanner(System.in);
            String test = scanner.nextLine();
            
            while (!test.contentEquals("exit")) {
                Socket socket = new Socket("localhost", 4356);
                try (InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
                    os.write(test.getBytes());
                    os.flush();
                    
                    while (true) {
                        byte[] buf = new byte[10];
                        int len = is.read(buf);
                        if(len == -1){
                            break;
                        }
                        
                        System.out.print(new String(buf));
                    }
                    test = scanner.nextLine();
                }
        }  
    }
}
