package modelo;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import vistas.Vendedor;

/**
 * @author Kevin Oliva
 */
public class VendedorDAO extends Conexion {

    ModeloVendedor modeloVendedor = new ModeloVendedor();
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;
    String respuesta;

    //VALIDACION DE VENDEDOR EN EL LOGIN
    public ModeloVendedor validarVendedor(String usuario, String dpi) {
        try {
            conectar();
            sql = "Select * from vendedor where Usuario=? and Dpi=?";
            ps = (PreparedStatement) getMiConexion().prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, dpi);
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloVendedor.setIdVendedor(rs.getInt("idVendedor"));
                modeloVendedor.setDpi(rs.getString("Dpi"));
                modeloVendedor.setNombres(rs.getString("Nombres"));
                modeloVendedor.setTelefono(rs.getString("Telefono"));
                modeloVendedor.setEstado(rs.getString("Estado"));
                modeloVendedor.setUsuario(rs.getString("Usuario"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return modeloVendedor;
    }

    //CREATE
    public String registrarVendedor(ModeloVendedor vendedor) {
        try {
            conectar();
            sql = "Insert into vendedor (idVendedor, Dpi, Nombres, Telefono, Estado, Usuario) values (?,?,?,?,?,?)";
            ps = (PreparedStatement) getMiConexion().prepareStatement(sql);
            ps.setInt(1, vendedor.getIdVendedor());
            ps.setString(2, vendedor.getDpi());
            ps.setString(3, vendedor.getNombres());
            ps.setString(4, vendedor.getTelefono());
            ps.setString(5, vendedor.getEstado());
            ps.setString(6, vendedor.getUsuario());
            ps.executeUpdate();
            respuesta = "Registro almacenado correctamente !!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede almacenar el registro";
        } finally {
            cerrarConexion();
        }
        return respuesta;
    }

    //READ
    public ModeloVendedor mostarVendedor(int idVendedor) {
        try {
            conectar();
            sql = "Select * from vendedor where idVendedor=?";
            ps = (PreparedStatement) getMiConexion().prepareStatement(sql);
            ps.setInt(1, idVendedor);
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloVendedor.setIdVendedor(rs.getInt("idVendedor"));
                modeloVendedor.setDpi(rs.getString("Dpi"));
                modeloVendedor.setNombres(rs.getString("Nombres"));
                modeloVendedor.setTelefono(rs.getString("Telefono"));
                modeloVendedor.setEstado(rs.getString("Estado"));
                modeloVendedor.setUsuario(rs.getString("Usuario"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }

        return modeloVendedor;
    }

    //UPDATE
    public String modificarVendedor(ModeloVendedor vendedor) {
        try {
            conectar();
            sql = "Update vendedor set Dpi=?, Nombres=?, Telefono=?, Estado=?, Usuario=? where idVendedor=?";
            ps = (PreparedStatement) getMiConexion().prepareStatement(sql);
            ps.setString(1, vendedor.getDpi());
            ps.setString(2, vendedor.getNombres());
            ps.setString(3, vendedor.getTelefono());
            ps.setString(4, vendedor.getEstado());
            ps.setString(5, vendedor.getUsuario());
            ps.setInt(6, vendedor.getIdVendedor());
            ps.executeUpdate();
            respuesta = "Registro modificado correctamente";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede modificar el registro";
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    //DELETE
    public String eliminarVendedor(int idVendedor) {
        try {
            conectar();
            sql = "Delete from vendedor where idVendedor=?";
            ps = (PreparedStatement) getMiConexion().prepareStatement(sql);
            ps.setInt(1, idVendedor);
            ps.executeUpdate();
            respuesta = "Registro fue eliminado correctamente.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede eliminar este registro";
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    //TABLA DE VENDEDORES
    public ArrayList<ModeloVendedor> listarVend() {
        ArrayList<ModeloVendedor> lista = new ArrayList();
        try {
            conectar();
            sql = "Select * from vendedor";
            ps = (PreparedStatement) getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {    
                ModeloVendedor modVend = new ModeloVendedor();
                modVend.setIdVendedor(rs.getInt("idVendedor"));
                modVend.setDpi(rs.getString("Dpi"));
                modVend.setNombres(rs.getString("Nombres"));
                modVend.setTelefono(rs.getString("Telefono"));
                modVend.setEstado(rs.getString("Estado"));
                modVend.setUsuario(rs.getString("Usuario"));
                lista.add(modVend);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }

        return lista;
    }

}
