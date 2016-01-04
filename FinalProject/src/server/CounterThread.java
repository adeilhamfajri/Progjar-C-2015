/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.Socket;

/**
 *
 * @author Ade Ilham Fajri
 */
class CounterThread implements Runnable{
    
    int Cout = 0;
    Socket client_socket;

    CounterThread(Socket client_socket) {
        this.client_socket = client_socket;
    }
    @Override
    public void run() {
        
    }
    
    public int getCout(){
        Cout = 55;
        return Cout;
}
    
    
}
