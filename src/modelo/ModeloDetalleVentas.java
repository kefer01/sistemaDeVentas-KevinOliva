
package modelo;

/**
 * @author Kevin Oliva
 */
public class ModeloDetalleVentas {
    int idDetalleVentas;
    int cantidad;
    double precioVenta;
    int producto_idProducto;
    int ventas_idVentas;

    public ModeloDetalleVentas() {
    }

    public ModeloDetalleVentas(int idDetalleVentas, int cantidad, double precioVenta, int producto_idProducto, int ventas_idVentas) {
        this.idDetalleVentas = idDetalleVentas;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.producto_idProducto = producto_idProducto;
        this.ventas_idVentas = ventas_idVentas;
    }

    public int getIdDetalleVentas() {
        return idDetalleVentas;
    }

    public void setIdDetalleVentas(int idDetalleVentas) {
        this.idDetalleVentas = idDetalleVentas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getProducto_idProducto() {
        return producto_idProducto;
    }

    public void setProducto_idProducto(int producto_idProducto) {
        this.producto_idProducto = producto_idProducto;
    }

    public int getVentas_idVentas() {
        return ventas_idVentas;
    }

    public void setVentas_idVentas(int ventas_idVentas) {
        this.ventas_idVentas = ventas_idVentas;
    }
    
    
}
