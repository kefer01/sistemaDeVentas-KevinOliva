
package modelo;

/**
 * @author Kevin Oliva
 */
public class ModeloVentas {
    int idVentas;
    String numeroVentas;
    String fechaVentas;
    double monto;
    String estado;
    int idCliente;
    int idVendedor;

    public ModeloVentas() {
    }

    public ModeloVentas(int idVentas, String numeroVentas, String fechaVentas, double monto, String estado, int idCliente, int idVendedor) {
        this.idVentas = idVentas;
        this.numeroVentas = numeroVentas;
        this.fechaVentas = fechaVentas;
        this.monto = monto;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public String getNumeroVentas() {
        return numeroVentas;
    }

    public void setNumeroVentas(String numeroVentas) {
        this.numeroVentas = numeroVentas;
    }

    public String getFechaVentas() {
        return fechaVentas;
    }

    public void setFechaVentas(String fechaVentas) {
        this.fechaVentas = fechaVentas;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    
    
    
}
