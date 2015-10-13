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
class Implementasi_by_Server {
   
    public void start() throws IOException{
        String dir_sem = new String("H:/");
        File dir = new File(dir_sem);
        
         int port = 7777;
         ServerSocket serversock = new ServerSocket(port);
         System.out.println("Server started listening on " + port);
         
         while (true) {
            Socket socket = serversock.accept();            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
        
            byte[] buf = new byte[1024];
            int len = is.read(buf);
            String request = new String(buf);
            String[] temp = request.split("\\s+");
            String cmd = temp[0].trim();
            
             boolean error = false;
                    String respond = "";
                    if (cmd.equals("ls")) {
                        if (temp.length > 2) {
                            error = true;
                            respond = "too many arguments";
                        }
                        else {
                            //return list of files
                            respond = "";
                            String[] files = null;
                            if (temp.length == 1)
                                files = dir.list();
                            else {
                                String args = temp[1].trim();
                                File custom_dir = new File(args);
                                if (!custom_dir.isDirectory()) {
                                    error = true;
                                    respond = "Folder doesn\'t exists!\n";
                                }
                                else {
                                    files = custom_dir.list();
                                }
                            }
                            
                            if (!error) {
                                for (String file: files) {
                                    respond += file + ('\n');
                                }
                            }
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