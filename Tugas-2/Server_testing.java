/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverku;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Dewi.k
 */
public class Serverku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerSocket serverku = null;
            Socket socket;
            serverku = new ServerSocket(4000);
            System.out.println("Server waiting....");
            
            socket = serverku.accept();
            System.out.println("Client connected....");
            
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            
            int len = dis.readInt();
            System.out.println("Image Size " + len/1024 + "KB");
            
            byte[] data = new byte[len];
            dis.readFully(data);
            dis.close();
            in.close();
            
            InputStream ian = new ByteArrayInputStream(data);
            BufferedImage bImage = ImageIO.read(ian);
            
            JFrame f = new JFrame("server");
            ImageIcon icon = new ImageIcon(bImage);
            JLabel l = new JLabel();
            l.setIcon(icon);
            f.add(l);
            f.pack();
            f.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Serverku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
