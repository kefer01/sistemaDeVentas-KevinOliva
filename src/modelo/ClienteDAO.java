package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Kevin Oliva
 */
public class ClienteDAO extends Conexion {

    ModeloCliente modeloCliente = new ModeloCliente();
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    String respuesta;

    public ModeloCliente listaID(String dpi) {
        try {
            conectar();
            sql = "Select * from cliente where Nit=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, dpi);
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloCliente.setIdCliente(rs.getInt("idCliente"));
                modeloCliente.setNit(rs.getString("Nit"));
                modeloCliente.setNombres(rs.getString("Nombres"));
                modeloCliente.setDireccion(rs.getString("Direccion"));
                modeloCliente.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return modeloCliente;
    }

    //CREATE
    public String registrarCliente(ModeloCliente cliente) {
        try {
            conectar();
            sql = "Insert Into cliente (idCliente, Nit, Nombres, Direccion, Estado) values (?, ?, ?, ?, ?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNit());
            ps.setString(3, cliente.getNombres());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getEstado());
            ps.executeUpdate();
            respuesta = "Cliente almacenado correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se pudo almacenar el cliente";
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    //READ
    public ModeloCliente buscarCliente(int codigo) {
        try {
            conectar();
            sql = "Select * from cliente where idCliente=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloCliente.setIdCliente(rs.getInt("idCliente"));
                modeloCliente.setNit(rs.getString("Nit"));
                modeloCliente.setNombres(rs.getString("Nombres"));
                modeloCliente.setDireccion(rs.getString("Direccion"));
                modeloCliente.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }

        return modeloCliente;
    }

    //UPDATE
    public String actualizarCliente(ModeloCliente cliente) {
        try {
            conectar();
            sql = "Update cliente set Nit=?, Nombres=?, Direccion=?, Estado=? where idCliente=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, cliente.getNit());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getEstado());
            ps.setInt(5, cliente.getIdCliente());
            ps.executeUpdate();
            respuesta = "Cliente actualizado correctamente";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se pudo actualizar el cliente";
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    //DELETE
    public String eliminarCliente(int codigo) {
        try {
            conectar();
            sql = "Delete from cliente where idCliente=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            respuesta = "Cliente eliminado correctamente";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se pudo eliminar el cliente";
        } finally {
            cerrarConexion();
        }
        return respuesta;
    }

    public ArrayList<ModeloCliente> tablaCientes() {
        ArrayList<ModeloCliente> lista = new ArrayList();
        try {
            conectar();
            sql = "Select * from cliente";
            ps = getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ModeloCliente modCliente = new ModeloCliente();
                modCliente.setIdCliente(rs.getInt("idCliente"));
                modCliente.setNit(rs.getString("Nit"));
                modCliente.setNombres(rs.getString("Nombres"));
                modCliente.setDireccion(rs.getString("Direccion"));
                modCliente.setEstado(rs.getString("Estado"));
                lista.add(modCliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return lista;
    }

}
