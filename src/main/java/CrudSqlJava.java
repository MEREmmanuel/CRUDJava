
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bolillo Espartano
 */
public class CrudSqlJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InterCRUD objetoInterCRUD = new InterCRUD();
        objetoInterCRUD.setVisible(true);
        Conexion con = new Conexion();
        Connection conexion = con.obtenerConexion();
    }
    
}
