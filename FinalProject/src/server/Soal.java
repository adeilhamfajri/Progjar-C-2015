/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.KirimKeClient.dout;

/**
 *
 * @author Ade Ilham Fajri
 */
public class Soal {

    String katapilihan;
    String katakunci;
    int acak;
    int kesempatan;
    Socket client_socket;
    static DataOutputStream dout;

    public Soal( Socket client_socket) {
        this.client_socket = client_socket;
    }

    Soal(String katapilihan, String katakunci, int kesempatan) {
        this.katapilihan = katapilihan;
        this.katakunci = katakunci;
        this.kesempatan = kesempatan;
    }
    
    
    public void soaal(){
            
        try {
            dout = new DataOutputStream(client_socket.getOutputStream());
            
            System.out.println(client_socket);
            String msgout = "\n kesempatan anda menebak : ";
            dout.writeUTF(msgout);
            
//            msgout = " Kata Kunci : " + katakunci;
//            dout.writeUTF(msgout);
//            
//            msgout = " Tebak kata apa ini : " + katapilihan;
//            dout.writeUTF(msgout);
//            
//            msgout = " Anda tebak : ";
//            dout.writeUTF(msgout);
            
        } catch (IOException ex) {
            Logger.getLogger(Soal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
