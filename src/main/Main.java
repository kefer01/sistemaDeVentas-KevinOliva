package main;

import controlador.ControladorVendedor;
import modelo.ModeloVendedor;
import modelo.VendedorDAO;
import vistas.LoginFormulario;
import vistas.PrincipalFormulario;


public class Main {
    public static void main(String[] args) {
        LoginFormulario login = new LoginFormulario();
        ControladorVendedor controlador = new ControladorVendedor(login);
        login.setVisible(true);
        
    }
}
