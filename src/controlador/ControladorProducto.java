package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloProducto;
import modelo.ProductoDAO;
import vistas.Producto;

/**
 * @author Kevin Oliva
 */
public class ControladorProducto implements ActionListener {

    ModeloProducto modelProducto = new ModeloProducto();
    ProductoDAO productoDAO = new ProductoDAO();
    Producto ventanaProducto = new Producto();

    public ControladorProducto(Producto ventanaProducto) {
        this.ventanaProducto = ventanaProducto;
        this.ventanaProducto.btnInsertar.addActionListener(this);
        this.ventanaProducto.btnConsultar.addActionListener(this);
        this.ventanaProducto.btnModificar.addActionListener(this);
        this.ventanaProducto.btnEliminar.addActionListener(this);
        this.ventanaProducto.btnSalir.addActionListener(this);
        this.ventanaProducto.btnLimpiar.addActionListener(this);
        mostrarTabla(ventanaProducto.tblTablaProducto);
    }

    public void insertarProducto() {
        modelProducto.setIdProducto(Integer.parseInt(ventanaProducto.txtIdProducto.getText()));
        modelProducto.setNombres(ventanaProducto.txtNomProd.getText());
        modelProducto.setPrecio(Double.parseDouble(ventanaProducto.txtPrecio.getText()));
        modelProducto.setStock(Integer.parseInt(ventanaProducto.txtStock.getText()));
        modelProducto.setEstado(ventanaProducto.txtEstado.getText());
        String respuesta = productoDAO.registrarProducto(modelProducto);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            mostrarTabla(ventanaProducto.tblTablaProducto);
        }
    }

    public void buscarProducto() {
        int codigo = Integer.parseInt(ventanaProducto.txtIdProducto.getText());
        modelProducto = productoDAO.mostrarProd(codigo);
        ventanaProducto.txtNomProd.setText(modelProducto.getNombres());
        ventanaProducto.txtPrecio.setText(String.valueOf(modelProducto.getPrecio()));
        ventanaProducto.txtStock.setText(String.valueOf(modelProducto.getStock()));
        ventanaProducto.txtEstado.setText(modelProducto.getEstado());
    }

    public void actualizaProducto() {
        modelProducto.setIdProducto(Integer.parseInt(ventanaProducto.txtIdProducto.getText()));
        modelProducto.setNombres(ventanaProducto.txtNomProd.getText());
        modelProducto.setPrecio(Double.parseDouble(ventanaProducto.txtPrecio.getText()));
        modelProducto.setStock(Integer.parseInt(ventanaProducto.txtStock.getText()));
        modelProducto.setEstado(ventanaProducto.txtEstado.getText());
        String respuesta = productoDAO.modificarProducto(modelProducto);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            mostrarTabla(ventanaProducto.tblTablaProducto);
        }
    }
    
    public void eliminarProducto(){
        int codigo = Integer.parseInt(ventanaProducto.txtIdProducto.getText());
        String respuesta = productoDAO.eliminarProducto(codigo);
        if (respuesta!= null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            mostrarTabla(ventanaProducto.tblTablaProducto);
        }
    }

    public void limpiar() {
        ventanaProducto.txtIdProducto.setText("");
        ventanaProducto.txtNomProd.setText("");
        ventanaProducto.txtPrecio.setText("");
        ventanaProducto.txtStock.setText("");
        ventanaProducto.txtEstado.setText("");
        ventanaProducto.txtIdProducto.requestFocus();
    }

    public void mostrarTabla(JTable tablaP){
        DefaultTableModel modelo = new DefaultTableModel();
        tablaP.setModel(modelo);
        modelo.addColumn("Id Producto");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Estado");
        Object [] columna = new Object [5];
        int numRegistros = productoDAO.listarProd().size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = productoDAO.listarProd().get(i).getIdProducto();
            columna[1] = productoDAO.listarProd().get(i).getNombres();
            columna[2]= productoDAO.listarProd().get(i).getPrecio();
            columna[3]= productoDAO.listarProd().get(i).getStock();
            columna[4] = productoDAO.listarProd().get(i).getEstado();
            modelo.addRow(columna);
                    
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaProducto.btnInsertar) {
            insertarProducto();
        }
        if (e.getSource() == ventanaProducto.btnConsultar) {
            buscarProducto();
        }
        if (e.getSource()== ventanaProducto.btnModificar) {
            actualizaProducto();
        }
        if (e.getSource()== ventanaProducto.btnEliminar) {
            eliminarProducto();
        }
        if (e.getSource() == ventanaProducto.btnSalir) {
            ventanaProducto.dispose();
        }
        if (e.getSource() == ventanaProducto.btnLimpiar) {
            limpiar();
        }
    }

}
