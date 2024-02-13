package Modelo;

import java.sql.*;

public class Conexion {
    Connection c;
    Statement s;
    public Connection getConnection(){
        try{
            c = DriverManager.getConnection("jdbc:mysql:///venta?serverTimezone=UTC", "root", "cesardiego12345@");
            s = c.createStatement();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return c;
    }
    
}
