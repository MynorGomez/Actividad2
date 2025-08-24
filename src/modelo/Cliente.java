/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author J
 */
public class Cliente extends Persona{
    private String nit;
    private int id ;
    Conexion cn;

    public Cliente(){}
    public Cliente(int id,String nit, String nombbres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombbres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id = id;
    }

   
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
        cn = new Conexion();
        cn.Abrir_conexion();
        String query;
        query = "SELECT id_Cliente as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento FROM db_empresa1.clientes; ";
        ResultSet consulta = cn.ConexionDB.createStatement().executeQuery(query);
        String encabezado[]={"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        String datos[] = new String [7];
         while (consulta.next()){
         datos[0]= consulta.getString("id");
         datos[1]= consulta.getString("nit");
         datos[2]= consulta.getString("nombres");
         datos[3]= consulta.getString("apellidos");
         datos[4]= consulta.getString("direccion");
         datos[5]= consulta.getString("telefono");
         datos[6]= consulta.getString("fecha_nacimiento");
         
         tabla.addRow(datos);
         }
        cn.cerrar_conexion();
        
        
        
        
    }catch(SQLException ex){
        cn.cerrar_conexion();
    System.out.println("Error: " + ex.getMessage());
    }
    return tabla;
    
    
    
    }
    @Override
    public void agregar (){
        try {
        PreparedStatement parametro;
        String query ="INSERT INTO clientes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
        cn= new Conexion();
        cn.Abrir_conexion();
        parametro = (PreparedStatement)cn.ConexionDB.prepareStatement(query);
        parametro.setString(1,this.getNit()); 
        parametro.setString(2,this.getNombbres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());  
        int executar = parametro.executeUpdate();
        
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex ){
        
        System.out.println("Error..." + ex.getMessage());
        }
        
    
    }
    
    @Override
    public void actualizar(){
         try {
        PreparedStatement parametro;
        String query ="update clientes set nit= ? ,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,fecha_nacimiento= ? where id_cliente = ? ";
        cn= new Conexion();
        cn.Abrir_conexion();
        parametro = (PreparedStatement)cn.ConexionDB.prepareStatement(query);
        parametro.setString(1,this.getNit()); 
        parametro.setString(2,this.getNombbres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());  
        parametro.setInt(7, this.getId());
        
        
        int executar = parametro.executeUpdate();
        
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Actualizado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex ){
        
        System.out.println("Error..." + ex.getMessage());
        }
    
    
    }
    
    @Override
    public void eliminar(){
    try {
        PreparedStatement parametro;
        String query ="delete from clientes where id_cliente = ? ";
        cn= new Conexion();
        cn.Abrir_conexion();
        parametro = (PreparedStatement)cn.ConexionDB.prepareStatement(query);
        parametro.setInt(1, this.getId());
        
        
        int executar = parametro.executeUpdate();
        
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Eliminado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex ){
        
        System.out.println("Error..." + ex.getMessage());
        }
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
