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
class Implementasi_cd_Server {
     
    public void start() throws IOException{
        String dir_sem = new String("H:/");
        File dir = new File(dir_sem);
        
         int port = 7777;
         ServerSocket serversock = new ServerSocket(port);
         System.out.println("Server started listening on " + port);
         
          
                while (true) {
                    Socket socket = serversock.accept();
                    //System.out.println("Client connected ...");
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    
                    byte[] buf = new byte[1024];
                    int len = is.read(buf);
                    String request = new String(buf);
                    String[] temp = request.split("\\s+");
                    String cmd = temp[0].trim();
                    
                    boolean error = false;
                    String respond = "";
                    
                    if (cmd.equals("cd")) {
                        if (temp.length > 2) {
                            error = true;
                            respond = "too many arguments\n";
                        }
                        else {
                            if (temp.length == 1) {
                                int idx = dir_sem.lastIndexOf('/');
                                dir_sem = dir_sem.substring(0,idx);
                                dir = new File(dir_sem);
                            }
                            else {
                                String args = temp[1].trim();
                                System.out.println("cd " + args);
                                File target_dir = new File(args);
                                if (!target_dir.isDirectory()) {
                                    error = true;
                                    respond = "Folder doesn\'t exists!\n";
                                }
                                else {
                                    dir_sem = args;
                                    dir = new File(dir_sem);
                                }
                            }
                        }
                    }
                    
                    else if (cmd.equals("pwd")) {
                        if (temp.length > 1) {
                            error = true;
                            respond = "pwd takes no argument\n";
                        }
                        else {
                            respond = dir_sem + '\n';
                        }
                    }
                    else {
                        error = true;
                        respond = "command not found\n";
                    }
                   
                    
                    os.write(respond.getBytes());
                    os.flush();
                    
                    os.close();
                    is.close();
                    socket.close();
                }
            }
        }
