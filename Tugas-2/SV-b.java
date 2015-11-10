package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final static int SOCKET_PORT = 6666;
    public final static String FILE_TO_SEND = "/var/www/html/serversend.txt";
    public final static String FILE_TO_RECEIVED = "/var/www/html/serverget.txt";

    public static void main (String [] args ) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        
        FileInputStream fis;
        FileOutputStream fos = null;
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        ServerSocket servsock = null;
        Socket sock = null;
        
        File sendfile = new File (FILE_TO_SEND);
        
        try {
            servsock = new ServerSocket(SOCKET_PORT);
            while (true) {
                pls("Waiting...");
                try {
                    sock = servsock.accept();
                    pls("Accepted connection : " + sock);
                    
                    byte[] snd = new byte [(int)sendfile.length()];
                    fis = new FileInputStream(sendfile);
                    bis = new BufferedInputStream(fis);

                    bis.read(snd, 0, snd.length);
                    os = sock.getOutputStream();
                    pls("Sending " + FILE_TO_SEND + "(" + snd.length + " bytes)");
                    os.write(snd, 0, snd.length);
                    os.flush();
                    pls("Done.");
                } finally {
                    if (bis != null) bis.close();
                    if (os != null) os.close();
                    if (sock!=null) sock.close();
                }
            }
        } finally {
            if (servsock != null) servsock.close();
        }
    }
    static void ps(String s) { System.out.print(s); }
    static void pls(String s) { System.out.println(s); }
}
