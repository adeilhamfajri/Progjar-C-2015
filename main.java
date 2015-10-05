import java.io.*
import java.net.*
import java.util.*

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
