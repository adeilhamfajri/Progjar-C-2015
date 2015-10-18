/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;

/**
 *
 * @author Ade Ilham Fajri
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Implementasi_by_Server server = new Implementasi_by_Server();
        server.start();

    }
    
}
