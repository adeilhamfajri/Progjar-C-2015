/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ade Ilham Fajri
 */
abstract class ProsesGameThread implements Runnable{
    
    Socket client_socket;
    ProsesGameThread(Socket client_socket) {
       this.client_socket = client_socket;
    }
    
    private static String[] gudangkata = {
        "michaelfaraday", "cristianoronaldo", 
        "lionelmessi", "markzuckerberg", "andyrubin","bayernmunich",
        "alberteinstein","kutu", "jetli"
    };
    
    //deklarasi variable array kata kunci
    private static String[] katakunci = {
        "Penemu Listrik", "pemain bola bernomor punggung 7 diReal Madrid",
        "Pemenang piala ballon d'or tahun 2013", "Penemu Facebook", "Penemu Android","Sebutan lain dari bayern munchen",
        "tokoh dunia","nama panggilan mahasiswa ithb jurusan SK", "nama panggilan mahasiswa ithb jurusan EL"  
    };
    
    
    public static void main(String[] args){
        
        Scanner scan  = new Scanner(System.in);
        Random rand = new Random();
        
        int acak = 0;
        int kesempatan;
        String katapilihan = "";
        boolean ulangi = false;
        boolean loncat =  false;
        char pilih = 0, jawab = 0;
        String pilih2 = "";
        String[] ubah;
        boolean back;
        
        
        
        
        
    }
    
    
}
