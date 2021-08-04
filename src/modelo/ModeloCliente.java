
package modelo;

/**
 * @author Kevin Oliva
 */
public class ModeloCliente {
    private int idCliente;
    private String nit;
    private String nombres;
    private String direccion;
    private String estado;

    public ModeloCliente() {
    }

    public ModeloCliente(int idCliente, String nit, String nombres, String direccion, String estado) {
        this.idCliente = idCliente;
        this.nit = nit;
        this.nombres = nombres;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
