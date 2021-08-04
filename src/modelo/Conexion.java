package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Kevin Oliva
 */
public class Conexion {
    private Connection miConexion;
    private static final String url = "JDBC:mysql://localhost/ventas";
    private static final String user = "root";
    private static final String password = "";

    public Connection getMiConexion() {
        return miConexion;
    }

    public void setMiConexion(Connection miConexion) {
        this.miConexion = miConexion;
    }
    
    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            miConexion = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            
        }
    }
    
    public void cerrarConexion(){
        try {
            if(miConexion!=null){
                if(miConexion.isClosed()==false){
                    miConexion.close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            
        }
    }
}
