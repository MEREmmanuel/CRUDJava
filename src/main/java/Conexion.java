/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author bolillo Espartano
 */
public class Conexion {
    
    Connection conexion = null;
    String usuario = "info";
    String contrasena = "info";
    String db = "CRUD";
    String ip = "localhost";
    String puerto = "1433";
    
    public Connection obtenerConexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String cadena = "jdbc:sqlserver://"+ip+":"+puerto+";"+"databaseName="+db+";"+
                            "encrypt=true;trustServerCertificate=true";
            conexion = DriverManager.getConnection(cadena, usuario, contrasena);
            //JOptionPane.showMessageDialog(null,"Conexion Exitosa");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Conexion Fallida"+e.toString());
            e.printStackTrace();
        }
        
        return conexion;
            
    }
    
}
