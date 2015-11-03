/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

/**
 *
 * @author Dewi.k
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Socket soc;
        BufferedImage img;
        img = null;
        soc = new Socket("localhost", 4000);
        System.out.println("Client is running....");
        try {
            System.out.println("Reading image....");
            img = ImageIO.read(new File("E:\\Semester 5\\Progjar\\digital_image_processing.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ImageIO.write((RenderedImage) img, "jpg", baos);
          baos.flush();
          
          byte[] bytes =baos.toByteArray();
          baos.close();
          
            System.out.println("Sending image to server....");
            OutputStream out = soc.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);
            System.out.println("Image sent to server....");
            dos.close();
            out.close();
            
        }
        catch(Exception e)
        {
            System.out.println("Exception " + e.getMessage());
            soc.close();
        }
        soc.close();
    }    
}
