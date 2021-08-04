package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Kevin Oliva
 */
public class ProductoDAO extends Conexion {

    ModeloProducto modeloProducto = new ModeloProducto();
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    String respuesta;
    int respuesta2;

    public int actualizaStock(int cant, int idProducto) {
        try {
            conectar();
            sql = "Update producto set stock=? where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idProducto);
            respuesta2 = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            cerrarConexion();
        }
        return respuesta2;
    }

    public ModeloProducto listarProd(int codigo) {
        try {
            conectar();
            sql = "Select * from producto where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloProducto.setIdProducto(rs.getInt("idProducto"));
                modeloProducto.setNombres(rs.getString("Nombres"));
                modeloProducto.setPrecio(rs.getDouble("Precio"));
                modeloProducto.setStock(rs.getInt("Stock"));
                modeloProducto.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }

        return modeloProducto;
    }

    //CREATE
    public String registrarProducto(ModeloProducto producto) {
        try {
            conectar();
            sql = "Insert into producto (idProducto, Nombres, Precio, Stock, Estado) values (?, ?, ?, ?, ?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombres());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getEstado());
            ps.executeUpdate();
            respuesta = "Producto almacenado correctamente!!!";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede almacenar el producto";
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    //READ
    public ModeloProducto mostrarProd(int codigo) {
        try {
            conectar();
            sql = "Select * from producto where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloProducto.setIdProducto(rs.getInt("idProducto"));
                modeloProducto.setNombres(rs.getString("Nombres"));
                modeloProducto.setPrecio(rs.getDouble("Precio"));
                modeloProducto.setStock(rs.getInt("Stock"));
                modeloProducto.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }

        return modeloProducto;
    }

    //UPDATE
    public String modificarProducto(ModeloProducto modeloProd) {

        try {
            conectar();
            sql = "Update producto set Nombres=?, Precio=?, Stock=?, Estado=? where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, modeloProd.getNombres());
            ps.setDouble(2, modeloProd.getPrecio());
            ps.setInt(3, modeloProd.getStock());
            ps.setString(4, modeloProd.getEstado());
            ps.setInt(5, modeloProd.getIdProducto());
            ps.executeUpdate();
            respuesta = "Producto actualizado correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "Producto no pudo ser actualizado";
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    //DELETE
    public String eliminarProducto(int codigo) {
        try {
            conectar();
            sql = "Delete from producto where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            respuesta = "Producto eliminado correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se pudo eliminar el producto";
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    //TABLA DE PRODUCTOS
    public ArrayList<ModeloProducto> listarProd() {
        ArrayList<ModeloProducto> lista = new ArrayList();
        try {
            conectar();
            sql = "Select * from producto";
            ps = getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ModeloProducto modProd = new ModeloProducto();
                modProd.setIdProducto(rs.getInt("idProducto"));
                modProd.setNombres(rs.getString("Nombres"));
                modProd.setPrecio(rs.getDouble("Precio"));
                modProd.setStock(rs.getInt("Stock"));
                modProd.setEstado(rs.getString("Estado"));
                lista.add(modProd);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return lista;
    }
}
