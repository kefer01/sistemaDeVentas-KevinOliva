package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Kevin Oliva
 */
public class VentasDAO extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    ModeloVentas modeVentas = new ModeloVentas();
    ModeloDetalleVentas modeloDetalle = new ModeloDetalleVentas();
    String sql;
    int respuesta;

    public int GuardarVentas(ModeloVentas modeVentas) {
        try {
            conectar();
            sql = "Insert into ventas(idVentas, NumeroVentas, FechaVenta, Monto, Estado, Cliente_idCliente, Vendedor_idVendedor) values (?, ?, ?, ?, ?, ?, ?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, modeVentas.getIdVentas());
            ps.setString(2, modeVentas.getNumeroVentas());
            ps.setString(3, modeVentas.getFechaVentas());
            ps.setDouble(4, modeVentas.getMonto());
            ps.setString(5, modeVentas.getEstado());
            ps.setInt(6, modeVentas.getIdCliente());
            ps.setInt(7, modeVentas.getIdVendedor());
            respuesta = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    public int GuardarDetalleVentas(ModeloDetalleVentas modeloDetalle) {
        try {
            conectar();
            sql = "Insert into detalle_ventas(idDetalleVentas, Ventas_idVentas, Producto_idProducto, Cantidad, PrecioVenta) values (?, ?, ?, ?, ?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, modeloDetalle.getIdDetalleVentas());
            ps.setInt(2, modeloDetalle.getVentas_idVentas());
            ps.setInt(3, modeloDetalle.getProducto_idProducto());
            ps.setInt(4, modeloDetalle.getCantidad());
            ps.setDouble(5, modeloDetalle.getPrecioVenta());
            respuesta = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }

    public int numFactura() {
        try {
            conectar();
            sql = "SELECT MAX(idVentas) as numFactura FROM ventas";
            ps = getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                respuesta = rs.getInt("numFactura");
            }
        } catch (Exception e) {
        } finally {
            cerrarConexion();
        }

        return respuesta;
    }
}
