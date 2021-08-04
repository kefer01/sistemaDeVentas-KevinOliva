package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteDAO;
import modelo.ModeloCliente;
import modelo.ModeloDetalleVentas;
import modelo.ModeloProducto;
import modelo.ModeloVentas;
import modelo.ProductoDAO;
import modelo.VentasDAO;
import vistas.LoginFormulario;
import vistas.PrincipalFormulario;
import vistas.Venta;

/**
 * @author Kevin Oliva
 */
public class ControladorVenta implements ActionListener {

    ModeloCliente modeloCliente = new ModeloCliente();
    ModeloProducto modeloProducto = new ModeloProducto();
    ClienteDAO clienteDAO = new ClienteDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    Venta ventanaVenta = new Venta();
    DefaultTableModel modeloTabla;
    int item = 1;
    int idProducto;
    String nombreProducto;
    int cantidad;
    int Stock;
    Double precioProducto;
    Double total;
    Double totalPagar;
    ModeloDetalleVentas modeloDetalleVentas = new ModeloDetalleVentas();
    ModeloVentas modeloVentas = new ModeloVentas();
    VentasDAO ventasDAO = new VentasDAO();

    public ControladorVenta(Venta ventanaVenta) {
        this.ventanaVenta = ventanaVenta;
        this.ventanaVenta.txtVendedor.setText(PrincipalFormulario.txtIdVendedorLogin.getText());
        this.ventanaVenta.btnBuscarCliente.addActionListener(this);
        this.ventanaVenta.btnBuscarProducto.addActionListener(this);
        this.ventanaVenta.btnAgregarProducto.addActionListener(this);
        this.ventanaVenta.btnGenerarVenta.addActionListener(this);
        this.ventanaVenta.btnCancelarVenta.addActionListener(this);
        numeroFactura();
        fecha();
        //this.ventanaVenta.txtFactura.setText(String.valueOf(ventasDAO.numeroFactura() + 1));
    }

    public void buscarCliente() {
        String codigo = ventanaVenta.txtCodCliente.getText();
        if (ventanaVenta.txtCodCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe de ingresar el codigo del cliente");
        } else {
            modeloCliente = clienteDAO.listaID(codigo);
            if (modeloCliente.getNit() != null) {
                ventanaVenta.txtNomCliente.setText(modeloCliente.getNombres());
                ventanaVenta.txtDireccion.setText(modeloCliente.getDireccion());
                ventanaVenta.txtNit.setText(modeloCliente.getNit());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no registrado");
                ventanaVenta.txtCodCliente.setText("");
                ventanaVenta.txtNomCliente.setText("");
                ventanaVenta.txtDireccion.setText("");
                ventanaVenta.txtNit.setText("");
                ventanaVenta.txtCodCliente.requestFocus();
            }
            modeloCliente.setIdCliente(0);
            modeloCliente.setNit("");
            modeloCliente.setNombres("");
            modeloCliente.setDireccion("");
            modeloCliente.setEstado("");
        }
    }

    public void buscarProducto() {
        if (ventanaVenta.txtCodProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe de ingresar el codigo del producto");
        } else {
            int codigo = Integer.parseInt(ventanaVenta.txtCodProducto.getText());
            modeloProducto = productoDAO.listarProd(codigo);
            if (modeloProducto.getIdProducto() != 0) {
                ventanaVenta.txtNomProducto.setText(modeloProducto.getNombres());
                ventanaVenta.txtPrecio.setText(String.valueOf(modeloProducto.getPrecio()));
                ventanaVenta.txtStock.setText(String.valueOf(modeloProducto.getStock()));
            } else {
                JOptionPane.showMessageDialog(null, "Producto no registrado");
                limpiarProducto();

            }
            modeloProducto.setIdProducto(0);
            modeloProducto.setNombres("");
            modeloProducto.setPrecio(0);
            modeloProducto.setStock(0);
            modeloProducto.setEstado("");
        }
    }

    public void agregarProducto() {
        modeloTabla = (DefaultTableModel) ventanaVenta.tblTablaDetalle.getModel();
        idProducto = Integer.parseInt(ventanaVenta.txtCodProducto.getText());
        nombreProducto = ventanaVenta.txtNomProducto.getText();
        cantidad = (Integer) ventanaVenta.spnCantidad.getValue();
        Stock = Integer.parseInt(ventanaVenta.txtStock.getText());
        precioProducto = Double.parseDouble(ventanaVenta.txtPrecio.getText());
        total = cantidad * precioProducto;
        if (Stock > 0) {
            Object[] objeto = new Object[6];
            objeto[0] = item;
            objeto[1] = idProducto;
            objeto[2] = nombreProducto;
            objeto[3] = cantidad;
            objeto[4] = precioProducto;
            objeto[5] = total;
            modeloTabla.addRow(objeto);
            ventanaVenta.tblTablaDetalle.setModel(modeloTabla);
            calcularTotal();
            item = item + 1;
            limpiarProducto();
        } else {
            JOptionPane.showMessageDialog(null, "Stock del producto no disponible");
        }
    }

    public void calcularTotal() {
        totalPagar = 0.0;
        for (int i = 0; i < ventanaVenta.tblTablaDetalle.getRowCount(); i++) {
            cantidad = Integer.parseInt(ventanaVenta.tblTablaDetalle.getValueAt(i, 3).toString());
            precioProducto = Double.parseDouble(ventanaVenta.tblTablaDetalle.getValueAt(i, 4).toString());
            totalPagar += cantidad * precioProducto;
        }
        ventanaVenta.txtTotalFactura.setText(String.valueOf(totalPagar));
    }

    public void limpiarProducto() {
        ventanaVenta.txtCodProducto.setText("");
        ventanaVenta.txtNomProducto.setText("");
        ventanaVenta.txtPrecio.setText("");
        ventanaVenta.txtStock.setText("");
        ventanaVenta.spnCantidad.setValue(0);
        ventanaVenta.txtCodProducto.requestFocus();
    }

    public void guardarVenta() {
        /*  modeloVentas.setIdVentas(Integer.parseInt(ventanaVenta.txtFactura.getText()));
        modeloVentas.setNumeroVentas("7");
        modeloVentas.setFechaVentas(ventanaVenta.txtFecha.getText());
        modeloVentas.setMonto(totalPagar);
        modeloVentas.setEstado("1");
        modeloVentas.setIdCliente(Integer.parseInt(ventanaVenta.txtCodCliente.getText()));
        modeloVentas.setIdVendedor(Integer.parseInt(ventanaVenta.txtVendedor.getText()));
        ventasDAO.GuardarVentas(modeloVentas);*/
        int idVendedor = Integer.parseInt(ventanaVenta.txtVendedor.getText());        
        int idCliente = Integer.parseInt(ventanaVenta.txtCodCliente.getText());
        int idVenta = Integer.parseInt(ventanaVenta.txtFactura.getText());
        String fecha = ventanaVenta.txtFecha.getText();
        double monto = totalPagar;
        String estado = "1";
        modeloVentas.setIdVentas(idVenta);
        modeloVentas.setNumeroVentas("6");
        modeloVentas.setFechaVentas(fecha);
        modeloVentas.setMonto(monto);
        modeloVentas.setEstado(estado);
        modeloVentas.setIdCliente(idCliente);
        modeloVentas.setIdVendedor(idVendedor);
        ventasDAO.GuardarVentas(modeloVentas);
    }

    public void guardarDetalleVenta() {
        int idVentas = Integer.parseInt(ventanaVenta.txtFactura.getText());
        for (int i = 0; i < ventanaVenta.tblTablaDetalle.getRowCount(); i++) {
            item = Integer.parseInt(ventanaVenta.tblTablaDetalle.getValueAt(i, 0).toString());
            idProducto = Integer.parseInt(ventanaVenta.tblTablaDetalle.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(ventanaVenta.tblTablaDetalle.getValueAt(i, 3).toString());
            precioProducto = Double.parseDouble(ventanaVenta.tblTablaDetalle.getValueAt(i, 4).toString());
            modeloDetalleVentas.setVentas_idVentas(idVentas);
            modeloDetalleVentas.setIdDetalleVentas(item);
            modeloDetalleVentas.setProducto_idProducto(idProducto);
            modeloDetalleVentas.setCantidad(cantidad);
            modeloDetalleVentas.setPrecioVenta(precioProducto);
            ventasDAO.GuardarDetalleVentas(modeloDetalleVentas);
        }
    }

    public void actualizarStock() {
        for (int i = 0; i < ventanaVenta.tblTablaDetalle.getRowCount(); i++) {
            idProducto = Integer.parseInt(ventanaVenta.tblTablaDetalle.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(ventanaVenta.tblTablaDetalle.getValueAt(i, 3).toString());
            modeloProducto = productoDAO.listarProd(idProducto);
            int saldo = modeloProducto.getStock() - cantidad;
            productoDAO.actualizaStock(saldo, idProducto);
        }
    }

    public void numeroFactura() {
        int numero = ventasDAO.numFactura();
        if (numero == 0) {
            ventanaVenta.txtFactura.setText("1");
        } else {
            numero +=1;
            ventanaVenta.txtFactura.setText(String.valueOf(numero));
        }
    }

    public void fecha() {
        Calendar calendario = new GregorianCalendar();
        ventanaVenta.txtFecha.setText("" + calendario.get(Calendar.YEAR) + "-" + (calendario.get(Calendar.MONTH) + 1) + "-" + calendario.get(Calendar.DAY_OF_MONTH));
    }

    public void limpiarFactura() {
        ventanaVenta.txtCodCliente.setText("");
        ventanaVenta.txtNomCliente.setText("");
        ventanaVenta.txtDireccion.setText("");
        ventanaVenta.txtNit.setText("");
        ventanaVenta.txtCodProducto.setText("");
        ventanaVenta.txtNomProducto.setText("");
        ventanaVenta.txtPrecio.setText("");
        ventanaVenta.txtStock.setText("");
        ventanaVenta.txtTotalFactura.setText("");
        ventanaVenta.spnCantidad.setValue(0);
    }

    public void limpiarTabla() {
        for (int i = modeloTabla.getRowCount()-1; i >= 0; i--) {
            modeloTabla.removeRow(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaVenta.btnBuscarCliente) {
            buscarCliente();
        }
        if (e.getSource() == ventanaVenta.btnBuscarProducto) {
            buscarProducto();
        }
        if (e.getSource() == ventanaVenta.btnAgregarProducto) {
            agregarProducto();
        }
        if (e.getSource() == ventanaVenta.btnGenerarVenta) {
            if (ventanaVenta.txtTotalFactura.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan datos para generar la factura");
            } else {
                guardarVenta();
                guardarDetalleVenta();
                actualizarStock();
                JOptionPane.showMessageDialog(null, "Factura Generada Correctamente");
                numeroFactura();
                limpiarFactura();
                limpiarTabla();
            }
        }
        if (e.getSource() == ventanaVenta.btnCancelarVenta) {
            ventanaVenta.dispose();
        }
    }

}
