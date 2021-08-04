
package modelo;

/**
 * @author Kevin Oliva
 */
public class ModeloVendedor {
    int idVendedor;
    String dpi;
    String nombres;
    String telefono;
    String estado;
    String usuario;

    public ModeloVendedor() {
    }

    public ModeloVendedor(int idVendedor, String dpi, String nombres, String telefono, String estado, String usuario) {
        this.idVendedor = idVendedor;
        this.dpi = dpi;
        this.nombres = nombres;
        this.telefono = telefono;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
