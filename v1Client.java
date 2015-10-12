//Prototype Bacafile.java
package bacafile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            byte[] buf = new byte[10];
            Socket socket = new Socket("localhost", 6666);
            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            /* Sending Response
            "Hello Server IF */
            os.write("Hello Server IF\r\n\r\n".getBytes());
            os.flush();


            int len;
            while(true) {
                buf = new byte[10];
                len = is.read(buf); /* Retrieving Response "Selamat datang di serverku\r\n" */
                if(len == -1) 
                    break;
                
                System.out.print(new String(buf)); /* Printing Response "Selamat datang di serverku\r\n" */
            }

            /*-------------------------------------------------------*/
            Scanner in = new Scanner(System.in);
            String input = null;
            while(input!="q") {
                input = in.nextLine(); /* Input command to send to Server */
                os.write(input.getBytes());
                os.flush

                System.out.print(new String(buf));
            }
            /*-------------------------------------------------------*/

            //Closing Connection
            os.close();
            is.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(BacaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
