import java.io.*
import java.net.*
import java.util.*
package tugas_1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tugas_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("H:/coba.txt"));
            bis.read(buffer);
            System.out.println(new String(buffer));
            bis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BacaFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BacaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
