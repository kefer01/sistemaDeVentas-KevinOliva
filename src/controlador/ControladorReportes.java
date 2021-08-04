package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ReporteDAO;
import vistas.ReporteFactura;

/**
 * @author Kevin Oliva
 */
public class ControladorReportes implements ActionListener{

    ReporteDAO reportesDAO = new ReporteDAO();
    ReporteFactura reporteFactura = new ReporteFactura();

    public ControladorReportes(ReporteFactura reporteFactura) {
     this.reporteFactura = reporteFactura; 
     this.reporteFactura.btnMostrarFactura.addActionListener(this);
    }
    
    public void reporteFactura(){
        int codigo = Integer.parseInt(reporteFactura.txtNumFactura.getText());
        reportesDAO.reporteFactura(codigo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== reporteFactura.btnMostrarFactura) {
            reporteFactura();
        }
    }
    
    
}
