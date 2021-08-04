package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author Kevin Oliva
 */
public class ReporteDAO extends Conexion {

    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public void reporteFactura(int codigo) {
        try {
            conectar();
            Map idVentas = new HashMap();
            idVentas.put("idVentas", codigo);
            JasperReport reporte = null;
            String ubicacionReporte = "src\\reportes\\reporteFactura.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ubicacionReporte);
            JasperPrint impresion = JasperFillManager.fillReport(reporte, idVentas, getMiConexion());
            //JasperViewer vista = JasperViewer(impresion, false);
            JasperViewer vista = new JasperViewer(impresion, false);
            vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vista.setVisible(true);
        } catch (Exception e) {
        } finally {
            cerrarConexion();
        }
    }
}
