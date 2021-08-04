package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloVendedor;
import modelo.VendedorDAO;
import vistas.LoginFormulario;
import vistas.PrincipalFormulario;
import vistas.Vendedor;

/**
 * @author Kevin Oliva
 */
public class ControladorVendedor implements ActionListener {

    LoginFormulario loginForm = new LoginFormulario();
    PrincipalFormulario principalForm = new PrincipalFormulario();
    ModeloVendedor modeloVend = new ModeloVendedor();
    VendedorDAO vendedorDAO = new VendedorDAO();
    Vendedor vendedorF = new Vendedor();

    public ControladorVendedor(Vendedor vendedorF) {
        this.vendedorF = vendedorF;
        this.vendedorF.btnInsertar.addActionListener(this);
        this.vendedorF.btnModificar.addActionListener(this);
        this.vendedorF.btnEliminar.addActionListener(this);
        this.vendedorF.btnConsultar.addActionListener(this);
        this.vendedorF.btnCerrar.addActionListener(this);
        this.vendedorF.btnLimpiar.addActionListener(this);
        tablaVendedores(vendedorF.tblTablaVendedores);
    }

    public ControladorVendedor(LoginFormulario loginForm) {
        this.loginForm = loginForm;
        this.loginForm.btnIngresar.addActionListener(this);
    }

    public void validar() {
        String usuario = loginForm.txtUsuario.getText();
        String dpi = loginForm.pswContrasena.getText();
        if (loginForm.txtUsuario.getText().equals("") || loginForm.pswContrasena.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El usuario o contraseña no puede estar vacio!!!");
        } else {
            modeloVend = vendedorDAO.validarVendedor(usuario, dpi);
            if (modeloVend.getUsuario() != null && modeloVend.getDpi() != null) {
                principalForm.setVisible(true);
                principalForm.txtIdVendedorLogin.setText(Integer.toString(modeloVend.getIdVendedor()));
                principalForm.txtVendedorLogin.setText(modeloVend.getNombres());
                loginForm.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrecto.");
                loginForm.txtUsuario.setText("");
                loginForm.pswContrasena.setText("");
                loginForm.txtUsuario.requestFocus();
            }
        }
    }

    public void insertarVendedor() {
        modeloVend.setIdVendedor(Integer.parseInt(vendedorF.txtCodigo.getText()));
        modeloVend.setDpi(vendedorF.txtDpi.getText());
        modeloVend.setNombres(vendedorF.txtNombres.getText());
        modeloVend.setTelefono(vendedorF.txtTelefono.getText());
        modeloVend.setEstado(vendedorF.txtEstado.getText());
        modeloVend.setUsuario(vendedorF.txtUsuario.getText());
        String respuesta = vendedorDAO.registrarVendedor(modeloVend);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            tablaVendedores(vendedorF.tblTablaVendedores);
        }

    }

    public void buscarVendedor() {
        int idVend = Integer.parseInt(vendedorF.txtCodigo.getText());
        modeloVend = vendedorDAO.mostarVendedor(idVend);
        vendedorF.txtDpi.setText(modeloVend.getDpi());
        vendedorF.txtNombres.setText(modeloVend.getNombres());
        vendedorF.txtTelefono.setText((modeloVend.getTelefono()));
        vendedorF.txtEstado.setText(modeloVend.getEstado());
        vendedorF.txtUsuario.setText(modeloVend.getUsuario());

    }

    public void modificarVendedor() {
        modeloVend.setIdVendedor(Integer.parseInt(vendedorF.txtCodigo.getText()));
        modeloVend.setDpi(vendedorF.txtDpi.getText());
        modeloVend.setNombres(vendedorF.txtNombres.getText());
        modeloVend.setTelefono(vendedorF.txtTelefono.getText());
        modeloVend.setEstado(vendedorF.txtEstado.getText());
        modeloVend.setUsuario(vendedorF.txtUsuario.getText());
        String respuesta = vendedorDAO.modificarVendedor(modeloVend);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            tablaVendedores(vendedorF.tblTablaVendedores);
        }
    }

    public void eliminarVendedor() {
        int codigo = Integer.parseInt(vendedorF.txtCodigo.getText());
        modeloVend.setIdVendedor(Integer.parseInt(vendedorF.txtCodigo.getText()));
        String respuesta = vendedorDAO.eliminarVendedor(codigo);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
            tablaVendedores(vendedorF.tblTablaVendedores);
        }
    }

    public void limpiar() {
        vendedorF.txtCodigo.setText("");
        vendedorF.txtDpi.setText("");
        vendedorF.txtNombres.setText("");
        vendedorF.txtTelefono.setText("");
        vendedorF.txtEstado.setText("");
        vendedorF.txtUsuario.setText("");
    }

    public void tablaVendedores(JTable tablaV) {
        DefaultTableModel modelo = new DefaultTableModel();
        tablaV.setModel(modelo);
        modelo.addColumn("Id Vendedor");
        modelo.addColumn("DPI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Estado");
        modelo.addColumn("Usuario");
        Object[] columna = new Object[6];
        int numRegistros = vendedorDAO.listarVend().size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = vendedorDAO.listarVend().get(i).getIdVendedor();
            columna[1] = vendedorDAO.listarVend().get(i).getDpi();
            columna[2] = vendedorDAO.listarVend().get(i).getNombres();
            columna[3] = vendedorDAO.listarVend().get(i).getTelefono();
            columna[4] = vendedorDAO.listarVend().get(i).getEstado();
            columna[5] = vendedorDAO.listarVend().get(i).getUsuario();
            modelo.addRow(columna);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginForm.btnIngresar) {
            validar();
        }
        if (e.getSource() == vendedorF.btnInsertar) {
            insertarVendedor();
        }
        if (e.getSource() == vendedorF.btnModificar) {
            modificarVendedor();
        }
        if (e.getSource() == vendedorF.btnEliminar) {
            eliminarVendedor();
        }
        if (e.getSource() == vendedorF.btnConsultar) {
            buscarVendedor();
        }
        if (e.getSource() == vendedorF.btnCerrar) {
            vendedorF.dispose();
        }
        if (e.getSource() == vendedorF.btnLimpiar) {
            limpiar();
        }
    }

}
