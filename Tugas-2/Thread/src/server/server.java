/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ade Ilham Fajri
 */
public class server {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        
        JSONParser parser = new JSONParser();
        
         Object obj = parser.parse(new FileReader("configjason/config.json"));
        JSONObject config = (JSONObject) obj;

        String default_path = (String)config.get("default_path");
        int port = Integer.parseInt((String)config.get("port"));

        Server_Implementasi server = new Server_Implementasi(default_path, port);
        server.start();
    }
}
