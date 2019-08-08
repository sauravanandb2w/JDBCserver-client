/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author saurav anand
 */
public class client {
    public static void main(String[] args)
    {
        
        try 
        {
            System.out.println("Client Started...");
            Socket soc = new Socket("localhost",9806);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("enter the string");
            String str = userInput.readLine();
          //  System.out.println("enter the weight");
            //int weight = userInput.read();
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
            out.println(str);
            //out.print(weight);
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            System.out.println(in.readLine());
        } 
        
        catch (Exception e)
        {
            e.printStackTrace();;
        }
        
    }
    
}
