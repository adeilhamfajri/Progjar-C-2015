/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
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
public class cadangan {
    static DataOutputStream dout;
    String sinyal;
        
    public static void main(String[] args) throws IOException{
        
    
        

            try{
                Socket client_socket = new Socket("localhost",414);
             while(true){

                Pemain_ReceiveSoal receive = new Pemain_ReceiveSoal(client_socket);
                receive.Run();

                Pemain_SendJawaban send = new Pemain_SendJawaban(client_socket);
                send.Run();


            }}catch(Exception e){
               //.... 
            }
        }
    }  

    class Pemain_ReceiveSoal {
        
    Socket client_socket = null;
    BufferedReader recieve=null;  
    String msgin = "";
    static DataInputStream din;
    
    public Pemain_ReceiveSoal(Socket client_socket) {
        this.client_socket = client_socket;
    }
    
    public void Run(){
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

     class Pemain_SendJawaban {
        
        Socket client_socket = null;
        PrintWriter print=null;
        BufferedReader brinput=null;
        static DataOutputStream dout;
        
        public Pemain_SendJawaban(Socket client_socket) {
            this.client_socket = client_socket;
        }
        
        public void Run(){
             try {
           //     System.out.println("Client connected to "+client_socket.getInetAddress() + " on port "+client_socket.getPort());
               
                brinput = new BufferedReader(new InputStreamReader(System.in));
                String msgout=null;
                msgout = brinput.readLine();
                
                
                dout = new DataOutputStream(client_socket.getOutputStream());
                dout.writeUTF(msgout); 
                
                
            } catch (IOException ex) {
                System.out.println("Gagal masuk !!");
            }
        }
    }


