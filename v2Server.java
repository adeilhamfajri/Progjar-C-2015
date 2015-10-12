package simpleserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class SimpleServer {
    public static void main(String[] args) {
        try {
            ServerSocket servsock = new ServerSocket(6666);
            System.out.println("Server mau berjalan ....");
            Socket socket = servsock.accept();
            System.out.println("Server berjalan ....");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
            byte[] buf = new byte[50];
            
            is.read(buf); String input = new String(buf); /* Retrieving Response "Hello Server IF" */
            System.out.println(new String(buf)); /* Printing Response "Hello Server IF" */

            os.write("Selamat datang di serverku\r\n".getBytes()); /* Sending Response "Selamat datang di serverku */
            os.flush();
            
            /*-------------------------------------------------------*/
            
            int timeout = 10000; servsock.setSoTimeout(timeout);        // Add 10s timeout
//          while (input != "q") {
                if(input == "ls") {
                    String ls = ListofFiles(input);
                    os.write(ls.getBytes());
                }
//          }
            
            /*-------------------------------------------------------*/

            // Closing Connection
            os.close();
            is.close();
            socket.close();
            servsock.close();
        } catch (IOException ex) {
            Logger.getLogger(SimpleServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Function for printing the list of the current directory
    private static String ListofFiles(String dir) {
        File f = new File(dir);
        File[] paths = f.listFiles();

        for (File path : paths) {
            if (path.isFile())
                dir = ("File " + path.getName());
//          System.out.println("File " + path.getName());
            else if (path.isDirectory())
                dir = ("Directory " + path.getName());
//          System.out.println("Directory " + path.getName());
        }
        return dir;
    }
}
