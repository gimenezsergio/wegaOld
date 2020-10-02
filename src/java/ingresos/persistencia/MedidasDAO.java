package ingresos.persistencia;

import entidades.DB;
import ingresos.entidades.Medidas;
import ingresos.presentacion.Medida;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedidasDAO {

    private MedidasDAO() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static MedidasDAO INSTANCE = null;

    public static MedidasDAO getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new MedidasDAO();
        }
        return INSTANCE;
    }

    private final static String CONSULT_MEDIDAS = "SELECT * FROM ingresos_plan_detalle_medicion_detalle INNER JOIN ingresos_mediciones ON `medic_detalle_medicion_id` = medic_id INNER JOIN ingresos_plan_item ON medic_detalle_tipo_plan = plan_item_id WHERE medic_detale_maestro_id =( SELECT MAX(plan_detalle_medicion) FROM `ingresos_plan_detalle` WHERE `plan_detalle_maestroid` =( SELECT plan_maestro_id FROM ingresos_plan_maestro WHERE plan_maestro_detalle_id = ? ) AND plan_detalle_medicion IS NOT NULL ) ";

    public ArrayList<Medidas> obtener(String id) throws Exception {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ArrayList<Medidas> listaMedidas = new ArrayList();
        try {
            con = DB.getInstance().getConnection();
            psmt = con.prepareStatement(CONSULT_MEDIDAS);
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Medidas misMedidas = new Medidas(
                        rs.getString("medic_detalle_id"),
                        rs.getString("medic_nombre"),
                        rs.getString("plan_item_nombre"),
                        rs.getString("medic_detalle_medida")
                );                
                listaMedidas.add(misMedidas);
            }
        } catch (Exception ex) {
            throw new Exception("Error de base de datos medidas " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaMedidas;
    }

}
