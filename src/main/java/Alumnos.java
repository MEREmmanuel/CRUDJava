
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bolillo Espartano
 */
public class Alumnos {
    
   public void mostrarAlumnos (JTable paramtableAlumnos){
       Conexion objetoConexion = new Conexion();
       DefaultTableModel modelo = new DefaultTableModel();
       String sql = "select * from Alumno_Setup_Tab;";
       modelo.addColumn("Nombre");
       modelo.addColumn("Carrera");
       modelo.addColumn("#Control");
       modelo.addColumn("NSS");
       
       paramtableAlumnos.setModel(modelo);
       String [] datos = new String [4];
       try{
           Statement st = objetoConexion.obtenerConexion().createStatement();
           ResultSet rs = st.executeQuery(sql);
       
           while(rs.next()){
               datos[0]=rs.getString(1);
               datos[1]=rs.getString(2);
               datos[2]=rs.getString(3);
               datos[3]=rs.getString(4);
               
               modelo.addRow(datos);
           }
           paramtableAlumnos.setModel(modelo);
           
       } catch(SQLException e){
               JOptionPane.showMessageDialog(null,"Problema al mostrar tabla " + e.toString());
               }
   }
   
   public void seleccionarAlumnos(JTable paramtableAlumnos, JTextField paramNombre, JTextField paramCarrera, JTextField paramControl, JTextField paramNss){
       
       try{
           int fila = paramtableAlumnos.getSelectedRow();
           if(fila>=0){
               paramNombre.setText(paramtableAlumnos.getValueAt(fila, 0).toString());
               paramCarrera.setText(paramtableAlumnos.getValueAt(fila, 1).toString());
               paramControl.setText(paramtableAlumnos.getValueAt(fila, 2).toString());
               paramNss.setText(paramtableAlumnos.getValueAt(fila, 3).toString());
           }
           else{
                JOptionPane.showMessageDialog(null,"Para seleccionar debe elegir al alumno");

           }
       }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error de selección " + e.toString());

       }
   }
   
   public void insertarAlumnos(JTextField paramNombre, JTextField paramCarrera, JTextField paramControl, JTextField paramNss){
       Conexion objetoConexion = new Conexion();
       String consulta = "insert into Alumno_Setup_Tab (Nombre,Carrera,[Numero de control], NSS) values (?,?,?,?);";
       
       try{
           CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
           cs.setString(1, paramNombre.getText());
           cs.setString(2, paramCarrera.getText());
           cs.setInt(3, Integer.parseInt(paramControl.getText()));
           cs.setLong(4, Long.parseLong(paramNss.getText()));
           cs.execute();
           JOptionPane.showMessageDialog(null,"Datos corecctamente insertados");
       } catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error de inserción " + e.toString());
       }
   }
   
   public void actualizarAlumnos(JTextField paramNombre, JTextField paramCarrera, JTextField paramControl, JTextField paramNss){
       Conexion objetoConexion = new Conexion();
       String consulta = "UPDATE Alumno_Setup_Tab set Alumno_Setup_Tab.Carrera=?,Alumno_Setup_Tab.[Numero de control]=?,Alumno_Setup_Tab.NSS=? WHERE Alumno_Setup_Tab.Nombre=?";
       try{
           CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
           cs.setString(1, paramCarrera.getText());
           cs.setInt(2, Integer.parseInt(paramControl.getText()));
           cs.setLong(3, Long.parseLong(paramNss.getText()));
           cs.setString(4, paramNombre.getText());
           cs.execute();
           JOptionPane.showMessageDialog(null,"Datos corecctamente actualizados");
       } catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error de actualización " + e.toString());
       }
   }
   
   public void eliminarAlumnos(JTextField paramNombre){
       Conexion objetoConexion = new Conexion();
       String consulta = "Delete FROM Alumno_Setup_Tab WHERE Alumno_Setup_Tab.Nombre=?";
       try{
           CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
           cs.setString(1, paramNombre.getText());
           cs.execute();
           JOptionPane.showMessageDialog(null,"El alumno se borró del sistema");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error de eliminación "+ e.toString());
       }
   }
}
