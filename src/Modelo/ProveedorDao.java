package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProveedorDao {
    
    Connection con;
    Conexion cn = new  Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor(ruc, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.execute();
            return true;            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    public List ListarProveedor(){
       List<Proveedor> Listapr = new ArrayList();
       String sql = "SELECT * FROM proveedor";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()){
               Proveedor cl =  new Proveedor();
               cl.setId(rs.getInt("id"));
               cl.setRuc(rs.getInt("ruc"));
               cl.setNombre(rs.getString("nombre"));
               cl.setTelefono(rs.getString("telefono"));
               cl.setDireccion(rs.getString("direccion"));
               cl.setRazon(rs.getString("razon"));  
               Listapr.add(cl);
           }
       }catch(SQLException e){  
           System.out.println(e.toString());
       }
       return Listapr;
   }
    public boolean EliminarProveedor(int id){
       String sql = "DELETE FROM proveedor WHERE id = ?";
       try{
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;          
       } catch (SQLException e) {
       System.out.println(e.toString());
       return false;       
   }finally{
           try{
               con.close();
           }catch (SQLException ex){
               System.out.println(ex.toString());
           }
       }
   }
    public boolean ModificarProveedor(Proveedor pr) {
      
    String sql = "UPDATE proveedor SET ruc=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
    try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, pr.getRuc());
        ps.setString(2, pr.getNombre());
        ps.setString(3, pr.getTelefono());
        ps.setString(4, pr.getDireccion());
        ps.setString(5, pr.getRazon());
        ps.setInt(6, pr.getId());
        
        // Ejecutar la consulta para realizar la actualización
        int rowsUpdated = ps.executeUpdate();
        
        // Verificar si se actualizaron filas
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    } finally {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
}
