package persistencia;

import entidades.DB;
import entidades.InfoBusqueda;
import entidades.ReclamosOld;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InformeGerencialDao {

    private InformeGerencialDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static InformeGerencialDao INSTANCE = null;

    public static InformeGerencialDao getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new InformeGerencialDao();
        }
        return INSTANCE;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //private final static String SQL_INFORME_GERENCIAL_SELECT = "SELECT id_producto , id_resultado , lote, id_origen , cant_infundados , cant_tema_comercial , SUM(cant_fundados)  FROM reclamos_detalle GROUP BY id_producto;";
    private final static String SQL_INFORME_GERENCIAL_SELECT = ""
            + "SELECT productos.codigo_producto producto, "
            + "SUM(reclamos_detalle.cant_fundados) fundados, "
            + "SUM(reclamos_detalle.cant_infundados) infundados, "
            + "origenes.origen, "
            + "reclamos_detalle.motivo,"
            + "lote, "
            + "SUM(cant_tema_comercial) tema_comercial "
            + "FROM reclamos_detalle "
            + "JOIN productos ON productos.id_producto = reclamos_detalle.id_producto "
            //+ "JOIN resultados ON resultados.id_resultado = reclamos_detalle.id_resultado "
            + "JOIN origenes ON origenes.id_origen = reclamos_detalle.id_origen  "
            + "GROUP BY reclamos_detalle.id_producto "
            + "ORDER BY fundados DESC;";

    //private final static String SQL_INFORME_GERENCIAL_SELECT = "SELECT * FROM reclamos_detalle;";
    public ArrayList<ReclamosOld> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<ReclamosOld> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_INFORME_GERENCIAL_SELECT);
            rs = ptsmt.executeQuery();
            ReclamosOld a = null;
            while (rs.next()) {
                try {
                    a = new ReclamosOld();
                    a.setCodigo_producto(rs.getString("producto"), 0);
                    a.setCant_fundados(rs.getString("fundados"), 0);
                    a.setCant_infundados(rs.getString("infundados"), 0);
                    a.setMotivo(rs.getString("motivo"));
                    a.setOrigen(rs.getString("origen"), 0);
                    a.setLote(rs.getString("lote"));
                    a.setCant_tema_comercial(rs.getString("tema_comercial"), 0);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                lista.add(a);
            }
        } finally {
            try {

                rs.close();

            } finally {
                try {

                    ptsmt.close();

                } finally {
                    c.close();

                }
            }
        }
        return lista;
    }

    private final static String SQL_INF_GERENCIAL_DESDE_HASTA2 = "SELECT  \n"
            + "productos.codigo_producto producto, \n"
            + "SUM(reclamos_detalle.cant_fundados) fundados, \n"
            + "SUM(reclamos_detalle.cant_infundados) infundados, \n"
            + "origenes.origen, \n"
            + "reclamos_detalle.motivo, \n"
            + "reclamos_detalle.lote, \n"
            + "SUM(reclamos_detalle.cant_tema_comercial) tema_comercial,\n"
            + "reclamos_encabezado.fecha_recepcion\n"
            + "FROM reclamos_detalle\n"
            + "JOIN productos ON productos.id_producto = reclamos_detalle.id_producto \n"
            + "JOIN origenes ON origenes.id_origen = reclamos_detalle.id_origen \n"
            + "JOIN reclamos_encabezado ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item\n"
            // + "WHERE reclamos_encabezado.fecha_recepcion > \"2018-06-01\"\n"
            // + "AND reclamos_encabezado.fecha_recepcion < \"2018-06-10\"\n"
            + "WHERE reclamos_encabezado.fecha_recepcion >= ?\n"
            + "AND reclamos_encabezado.fecha_recepcion <= ?\n"
            + "GROUP BY reclamos_detalle.id_producto \n"
            + "ORDER BY fundados DESC;";

    private final static String SQL_INF_GERENCIAL_DESDE_HASTA = "SELECT productos.codigo_producto producto, SUM(reclamos_detalle.cant_fundados) fundados, SUM( reclamos_detalle.cant_infundados ) infundados, origenes.origen, reclamos_detalle.id_diseno, diseno.dis_nombre, reclamos_detalle.id_proceso, proceso.proc_nombre, reclamos_detalle.id_falla_mat, falla_materiales.falla_mat_nombre, reclamos_detalle.lote, SUM( reclamos_detalle.cant_tema_comercial ) tema_comercial, reclamos_encabezado.fecha_recepcion FROM reclamos_detalle JOIN productos ON productos.id_producto = reclamos_detalle.id_producto JOIN origenes ON origenes.id_origen = reclamos_detalle.id_origen JOIN reclamos_encabezado ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id WHERE reclamos_encabezado.fecha_recepcion >= ? AND reclamos_encabezado.fecha_recepcion <= ? GROUP BY reclamos_detalle.id_producto ORDER BY fundados DESC";

    private final static String SQL_INF_GERENCIAL_DESDE_HASTA3 = "SELECT productos.codigo_producto producto, SUM(reclamos_detalle.cant_fundados) fundados, SUM( reclamos_detalle.cant_infundados ) infundados, origenes.origen, reclamos_detalle.id_diseno, diseno.dis_nombre, reclamos_detalle.id_proceso, proceso.proc_nombre, reclamos_detalle.id_falla_mat, falla_materiales.falla_mat_nombre, reclamos_detalle.lote, SUM( reclamos_detalle.cant_tema_comercial ) tema_comercial, reclamos_encabezado.fecha_recepcion, reclamos_detalle.id_reclamos FROM reclamos_detalle JOIN productos ON productos.id_producto = reclamos_detalle.id_producto JOIN origenes ON origenes.id_origen = reclamos_detalle.id_origen JOIN reclamos_encabezado ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id WHERE reclamos_encabezado.fecha_recepcion >= ? AND reclamos_encabezado.fecha_recepcion <= ? GROUP by reclamos_detalle.id_reclamos ORDER BY fundados DESC";
    public ArrayList<ReclamosOld> busqueda(String desde, String hasta) throws ClassNotFoundException, IOException, SQLException, Exception {
        ArrayList<ReclamosOld> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_INF_GERENCIAL_DESDE_HASTA3);
            ptsmt.setString(1, desde);
            ptsmt.setString(2, hasta);
            rs = ptsmt.executeQuery();
            ReclamosOld a = null;
            while (rs.next()) {
                try {
                    a = new ReclamosOld();
                    a.setCodigo_producto(rs.getString("producto"), 0);
                    a.setCant_fundados(rs.getString("fundados"), 0);
                    a.setCant_infundados(rs.getString("infundados"), 0);
                    // a.setMotivo(rs.getString("motivo"));
                    a.setOrigen(rs.getString("origen"), 0);
                    a.setLote(rs.getString("lote"));
                    a.setCant_tema_comercial(rs.getString("tema_comercial"), 0);
                    a.setFecha_recepcion(rs.getString("fecha_recepcion"));
                    a.setId_proceso(rs.getString("id_proceso"), 0);
                    a.setId_diseno(rs.getString("id_diseno"), 0);
                    a.setId_falla_mat(rs.getString("id_falla_mat"), 0);
                    a.setDiseno(rs.getString("dis_nombre"));
                    a.setFalla_mat(rs.getString("falla_mat_nombre"));
                    a.setProceso(rs.getString("proc_nombre"));
                    if (!rs.getString("id_diseno").equals("7")) {
                        a.setDiagnostico(rs.getString("dis_nombre"));
                    } else if (!rs.getString("id_proceso").equals("14")) {
                        a.setDiagnostico(rs.getString("proc_nombre"));
                    } else if (!rs.getString("id_falla_mat").equals("9")) {
                        a.setDiagnostico(rs.getString("falla_mat_nombre"));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                lista.add(a);
            }
        } finally {
            try {

                rs.close();

            } finally {
                try {

                    ptsmt.close();

                } finally {
                    c.close();

                }
            }
        }
        return lista;

    }

}
