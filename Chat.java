/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 *
 * @author saurav anand
 */
class ConnectionDB
{
    protected Connection ConnectDB() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Connection con = null;
        try
        {
           //Class.forName("com.mysql.jdbc.Driver");
           String path = "jdbc:mysql://localhost:3306/chatcontent";
           con  = DriverManager.getConnection(path,"root","mydatabase#4178");
           
           System.out.println("database connected with server");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return con;
    }
}


public class Chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            System.out.println("Waiting for client...");
            ServerSocket ss = new ServerSocket(9806);
            Socket soc = ss.accept();
            System.out.println("client connected...");

            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String str = in.readLine();
            PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
            out.println("Server says : "+str );
            ConnectionDB c = new ConnectionDB();
            Connection con = c.ConnectDB();
            String sql = "insert into talks (line,importance) values (?,?)";
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setNString(1,str);
            ptm.setInt(2, 123);
            ptm.executeUpdate();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
}


