package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteDAO;
import modelo.ModeloCliente;
import vistas.Cliente;

/**
 * @author Kevin Oliva
 */
public class ControladorCliente implements ActionListener {

    ModeloCliente modelCliente = new ModeloCliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    Cliente ventanaCliente = new Cliente();

    public ControladorCliente(Cliente ventanaCliente) {
        this.ventanaCliente = ventanaCliente;
        this.ventanaCliente.btnInsertar.addActionListener(this);
        this.ventanaCliente.btnConsultar.addActionListener(this);
        this.ventanaCliente.btnModificar.addActionListener(this);
        this.ventanaCliente.btnEliminar.addActionListener(this);
        this.ventanaCliente.btnSalir.addActionListener(this);
        this.ventanaCliente.btnLimpiar.addActionListener(this);
        tablaClientes(ventanaCliente.tblTablaClientes);
    }

    public void insertarCliente() {
        modelCliente.setIdCliente(Integer.parseInt(ventanaCliente.txtIdCliente.getText()));
        modelCliente.setNit(ventanaCliente.txtNit.getText());
        modelCliente.setNombres(ventanaCliente.txtNombre.getText());
        modelCliente.setDireccion(ventanaCliente.txtDireccion.getText());
        modelCliente.setEstado(ventanaCliente.txtEstado.getText());
        String respuesta = clienteDAO.registrarCliente(modelCliente);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            tablaClientes(ventanaCliente.tblTablaClientes);
        }
    }

    public void mostarCliente() {
        int codigo = Integer.parseInt(ventanaCliente.txtIdCliente.getText());
        modelCliente = clienteDAO.buscarCliente(codigo);
        ventanaCliente.txtNit.setText(modelCliente.getNit());
        ventanaCliente.txtNombre.setText(modelCliente.getNombres());
        ventanaCliente.txtDireccion.setText(modelCliente.getDireccion());
        ventanaCliente.txtEstado.setText(modelCliente.getEstado());
    }

    public void modificarCliente() {
        modelCliente.setIdCliente(Integer.parseInt(ventanaCliente.txtIdCliente.getText()));
        modelCliente.setNit(ventanaCliente.txtNit.getText());
        modelCliente.setNombres(ventanaCliente.txtNombre.getText());
        modelCliente.setDireccion(ventanaCliente.txtDireccion.getText());
        modelCliente.setEstado(ventanaCliente.txtEstado.getText());
        String respuesta = clienteDAO.actualizarCliente(modelCliente);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            tablaClientes(ventanaCliente.tblTablaClientes);
        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }
    }

    public void eliminarCliente() {
        if (ventanaCliente.txtIdCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Id Cliente esta vacio");
        } else {
            int codigo = Integer.parseInt(ventanaCliente.txtIdCliente.getText());
            String respuesta = clienteDAO.eliminarCliente(codigo);
            if (respuesta != null) {
                JOptionPane.showMessageDialog(null, respuesta);
                limpiar();
                tablaClientes(ventanaCliente.tblTablaClientes);
            } else {
                JOptionPane.showMessageDialog(null, respuesta);
            }
        }
    }

    public void tablaClientes(JTable tablaC) {
        DefaultTableModel modelo = new DefaultTableModel();
        tablaC.setModel(modelo);
        modelo.addColumn("Id Cliente");
        modelo.addColumn("NIT");
        modelo.addColumn("Nombres");
        modelo.addColumn("Direccion");
        modelo.addColumn("Estado");
        Object[] columna = new Object[5];
        int numRegistros = clienteDAO.tablaCientes().size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = clienteDAO.tablaCientes().get(i).getIdCliente();
            columna[1] = clienteDAO.tablaCientes().get(i).getNit();
            columna[2] = clienteDAO.tablaCientes().get(i).getNombres();
            columna[3] = clienteDAO.tablaCientes().get(i).getDireccion();
            columna[4] = clienteDAO.tablaCientes().get(i).getEstado();
            modelo.addRow(columna);
        }

    }

    public void limpiar() {
        ventanaCliente.txtIdCliente.setText("");
        ventanaCliente.txtNit.setText("");
        ventanaCliente.txtNombre.setText("");
        ventanaCliente.txtDireccion.setText("");
        ventanaCliente.txtEstado.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaCliente.btnInsertar) {
            insertarCliente();
        }
        if (e.getSource() == ventanaCliente.btnConsultar) {
            mostarCliente();
        }
        if (e.getSource() == ventanaCliente.btnModificar) {
            modificarCliente();
        }
        if (e.getSource() == ventanaCliente.btnEliminar) {
            eliminarCliente();
        }
        if (e.getSource() == ventanaCliente.btnSalir) {
            ventanaCliente.dispose();
        }
        if (e.getSource() == ventanaCliente.btnLimpiar) {
            limpiar();
        }
    }

}
