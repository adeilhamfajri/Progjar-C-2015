/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ade Ilham Fajri
 */

public class Client_Thread implements Runnable{
    private String cur_dir;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    
    Client_Thread (Socket socket, String default_path){
        try {
            this.socket = socket;
            this.is = this.socket.getInputStream();
            this.os = this.socket.getOutputStream();
            this.cur_dir = default_path;
        } catch (IOException ex) {
            Logger.getLogger(Client_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getRequest() throws IOException {
        byte[] buf = new byte[1];
        this.is.read(buf);
        
        String request = new String();
        while(!(new String(buf).equals("\n"))) {
            request += new String(buf);
            this.is.read(buf);
        }
        
        int len = Integer.parseInt(request);
        request = new String();
        for(int i=0;i<len;i++){
            this.is.read(buf);
            request += new String(buf);
        }
        return request;
    }
    
   
    @Override
    public void run() {
        File dir = new File(this.cur_dir);
        
        while (true) {
            try {
                String request = getRequest();
                String[] temp = request.split("\\s+");
                String cmd = temp[0].trim();
                
                switch (cmd) {
                    
                    case "PWD":
                        PWD pwd = new PWD(request, this.cur_dir);
                        pwd.exec(this.os);
                        break;
            
                    case "LS":
                        LS ls = new LS(request, this.cur_dir);
                        ls.exec(this.os);
                        break;
                        
                    case "CD":
                        CD cd = new CD(request, this.cur_dir);
                        this.cur_dir = cd.exec(this.os);
                        break;
                        
                    case "MKDIR":
                        MKDIR mkdir = new MKDIR(request, this.cur_dir);
                        mkdir.exec(this.os);
                        break;
                    
                    default:
                        String respond = "Error: command not found\n";
                        String len = Integer.toString(respond.length());
                        len += "\n";
                        
                        this.os.write("0".getBytes());
                        this.os.flush();
                        this.os.write(len.getBytes());
                        this.os.flush();
                        this.os.write(respond.getBytes());
                        this.os.flush();
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client_Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
}