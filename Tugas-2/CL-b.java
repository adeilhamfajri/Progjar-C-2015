package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public final static int SOCKET_PORT = 6666;
    public final static String SERVER = "127.0.0.1";
    public final static String FILE_TO_SEND = "/home/f/Documents/clientsend.txt";
    public final static String FILE_TO_RECEIVED = "/home/f/Documents/clientget.txt";
    
    public final static int FILE_SIZE = 102400;

    public static void main (String [] args ) throws IOException {
        int bytesRead;
        int current;

        InputStream is;
        OutputStream os = null;

        FileInputStream fis = null;
        FileOutputStream fos = null;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        Socket sock = null;
        
        File sendfile = new File(FILE_TO_SEND);

        try {
            sock = new Socket(SERVER, SOCKET_PORT);
            pls("Connecting...");

            byte[] rcv  = new byte [FILE_SIZE];
            is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVED);
            bos = new BufferedOutputStream(fos);
            
            bytesRead = is.read(rcv, 0, rcv.length);
            current = bytesRead;

            while(bytesRead > -1) {
                bytesRead = is.read(rcv, current, (rcv.length-current));
                if(bytesRead >= 0) current += bytesRead;
            }

            bos.write(rcv, 0 , current);
            bos.flush();
            pls("File " + FILE_TO_RECEIVED
                + " downloaded (" + current + " bytes read)");
        } finally {
            if (fos != null) fos.close();
            if (bos != null) bos.close();
            if (sock != null) sock.close();
        }
    }
    static void ps(String s) { System.out.print(s); }
    static void pls(String s) { System.out.println(s); }
}
