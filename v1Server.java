/* Prototype v1
*/

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
            int timeout = 10000;    // set angka untuk timeout 10 000 ms atau 10 s
            servsock.setSoTimeout(timeout); // Membuat timeout
            System.out.println("Server mau berjalan ....");
            Socket socket = servsock.accept();
            System.out.println("Server berjalan ....");
            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
            String default_location = "/home/f/ff/";
            String ope = ListofFiles(default_location); //String ope untuk memanggil fungsi listoffile
            
            byte[] buf = new byte[50];
            is.read(buf);
//            System.out.println(new String(buf));
            //os.write("Selamat datang di serverku\r\n".getBytes());
            //current = in.nextLine();
            String input = new String(buf);
            if(input == "ls") {                                         // Jika inputan client ls
                System.out.println(input);                              //
  //            os.write(ope.getBytes());                   // masih ga bisa soalnya fungsi listoffile masih println
                os.flush();
            }
            
            
            os.close();
            is.close();
            socket.close();
            servsock.close();
        } catch (IOException ex) {
            Logger.getLogger(SimpleServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        private static String ListofFiles(String dir) {
        File f = new File(dir);
        File[] paths = f.listFiles();
        System.out.println("");
        for (File path : paths) {
            if (path.isFile())
                System.out.println("File " + path.getName());
            else if (path.isDirectory())
                System.out.println("Directory " + path.getName());
        }
        System.out.println("");
        return dir;
    }
}
