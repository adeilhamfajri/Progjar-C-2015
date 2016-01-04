/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import static client.Pemain_ReceiveSoal.din;
import static client.Pemain_SendJawaban.dout;
import static client.Pemain_ThreadReceiveSoal.din;
import static client.Pemain_ThreadSendJawaban.dout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.utama;
import server.KirimKeClient;
/**
 *
 * @author Ade Ilham Fajri
 */
public class client {
    static DataOutputStream dout;
    String sinyal;
        
    public static void main(String[] args) throws IOException{

            try{
                Socket client_socket = new Socket("localhost",414);
                
                ReceiveSoal receive = new ReceiveSoal(client_socket);
                SendSoal send = new SendSoal(client_socket);
              //  Thread thread_99 = new Thread(receive);
                
             while(true){

                
                Thread thread_99 = new Thread(receive);
                thread_99.start();
            //    receive.run();
                
               
             //   Thread thread_1 = new Thread(send);
                send.run();

            }}catch(Exception e){
               //.... 
            }
        }

    private static class ReceiveSoal implements Runnable{
            
        Socket client_socket=null;
        BufferedReader recieve=null;  
        String msgin = "";
        static DataInputStream din;
        
        public ReceiveSoal(Socket client_socket) {
            this.client_socket = client_socket;
        }

        @Override
        public void run() {
            if(client_socket.isConnected()){
            try {
                din = new DataInputStream(client_socket.getInputStream());
                msgin = din.readUTF();
                System.out.println(msgin);
            } catch (IOException ex) {
                Logger.getLogger(Pemain_ThreadReceiveSoal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
            else{
                System.out.println("Tidak terkoneksi");
            }
        }              
    }

    private static class SendSoal implements Runnable{
        
        Socket client_socket=null;
        PrintWriter print=null;
        BufferedReader brinput=null;
        static DataOutputStream dout;
        
        public SendSoal(Socket client_socket) {
            this.client_socket = client_socket;
        }

        @Override
        public void run() {
            try {
                brinput = new BufferedReader(new InputStreamReader(System.in));
                String msgout=null;
                msgout = brinput.readLine();
                
                
                dout = new DataOutputStream(client_socket.getOutputStream()); 
                dout.writeUTF(msgout);
            } catch (IOException ex) {
                Logger.getLogger(clone_client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    
    
    
}  

    