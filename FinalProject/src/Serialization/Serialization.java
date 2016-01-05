/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialization;

import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author Ade Ilham Fajri
 */
public class Serialization implements Serializable{
    
    public int kesempatan;
    public String katakunci;
    public String katapilihan;
    public Socket client_socket;
    public String msgout;
    
    public Serialization(String msgout){
        this.msgout = msgout;
    }

    public Serialization(int kesempatan, String katakunci, String katapilihan) {
    
        this.katakunci = katakunci;
        this.katapilihan = katapilihan;
        this.kesempatan = kesempatan;
    }
    
}
