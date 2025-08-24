/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo; 
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author J
 */
public class Conexion {
    
    public Connection ConexionDB ;
    public final String bd = "db_empresa1";
    public final String UrlConexion = String.format("jdbc:mysql://127.0.0.1:3306/%s", bd);
    public final String  usuario = "usr_Empresa";
    public final String contraseña = "M@ynor15";
    public final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    public void Abrir_conexion(){
    try{
        Class.forName(jdbc);
        
        ConexionDB = DriverManager.getConnection(UrlConexion,usuario,contraseña);
       // JOptionPane.showMessageDialog(null,"Conexion Exitosa....","EXITO",JOptionPane.INFORMATION_MESSAGE);
        

        
    }catch(HeadlessException | ClassNotFoundException | SQLException ex){
    
        System.out.println("Error..." + ex.getMessage());
    }
     
    }
public void cerrar_conexion(){

try{ 
    ConexionDB.close();
    

}catch(SQLException ex){
    System.out.println("Error..." + ex.getMessage());
}

} 



}

