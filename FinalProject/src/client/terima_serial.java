/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static client.Pemain_ThreadReceiveSoal.din;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import Serialization.Serialization;

/**
 *
 * @author Ade Ilham Fajri
 */
public class terima_serial {
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        Socket client_socket = new Socket("localhost", 404);
        
        InputStream IS = client_socket.getInputStream();
        ObjectInputStream OIS = new ObjectInputStream(IS);
        
        Serialization serial = (Serialization)OIS.readObject();
        
        if(serial != null){
            System.out.println("kesempatan : " + serial.kesempatan);
        }else{
            System.out.println("Serialization gagal");
        }
    }
}
