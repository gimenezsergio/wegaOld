package ingresos.persistencia;

import ingresos.entidades.PlanNaftaTank;
import com.google.gson.Gson;
import ingresos.entidades.PlanBujiaEncen;
import entidades.DB;
import ingresos.entidades.Packaging;
import ingresos.entidades.PlanAceiteCartucho;
import ingresos.entidades.PlanAceiteUSellada;
import ingresos.entidades.PlanAireFAP;
import ingresos.entidades.PlanAireFreno;
import ingresos.entidades.PlanAireHabit;
import ingresos.entidades.PlanAirePesados;
import ingresos.entidades.PlanAireRedondo;
import ingresos.entidades.PlanBujiaPreca;
import ingresos.entidades.PlanDieselCartucho;
import ingresos.entidades.PlanDieselUSelladaMetalica;
import ingresos.entidades.PlanDieselUSelladaPlastico;
import ingresos.entidades.PlanGeneral;
import ingresos.entidades.PlanLamparas;
import ingresos.entidades.PlanNaftaCartucho;
import ingresos.entidades.PlanNaftaUSellada;
import ingresos.entidades.Precargas;
import ingresos.entidades.PrecargasDetalle;
import ingresos.entidades.PrecargasMaestro;
import ingresos.persistencia.PackagingDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import static presentacion.MiCache.*;

public class PlanControlDisenoDAO {

    

    private PlanControlDisenoDAO() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static PlanControlDisenoDAO INSTANCE = null;

    public static PlanControlDisenoDAO getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new PlanControlDisenoDAO();
        }
        return INSTANCE;
    }

    private final static String ESTADO_PRECARGA_DETALLE_SELECT = "SELECT precarga_detalle_pkging FROM precarga_detalle WHERE precarga_detalle.precarga_detalle_idmaestro = ? AND precarga_detalle_diseno = 0";
    private final static String ESTADO_PRECARGA_MAESTRO_UPDATE = "UPDATE precarga_maestro SET precarga_maestro.precarga_maestro_diseno = 1 WHERE precarga_maestro.precarga_imaestro_id = ?";

    public static void updateStatusPrecargaMaestro(String id) throws ClassNotFoundException, IOException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Connection conMaestro = null;
        PreparedStatement pstmMaestro = null;
        try {
            System.out.println("ID detalle maestro: " + id);
            con = DB.getInstance().getConnection();
            pstm = con.prepareStatement(ESTADO_PRECARGA_DETALLE_SELECT);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.println("Tiene registros, estado INCOMPLETO");
            } else {
                System.out.println("Tiene registros, estado COMPLETO. UPDATE estado de MaestroPrecarga.");
                try {
                    conMaestro = DB.getInstance().getConnection();
                    pstmMaestro = conMaestro.prepareStatement(ESTADO_PRECARGA_MAESTRO_UPDATE);
                    pstmMaestro.setString(1, id);
                    pstmMaestro.execute();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Exeption " + ex.getMessage());
                } finally {
                    if (conMaestro != null) {
                        try {
                            conMaestro.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private final static String ESTADO_PRECARGA_DETALLE = "UPDATE precarga_detalle SET precarga_detalle_diseno = 1 WHERE precarga_detalle_id = ?";

    public static void updateStatusPrecargaDetalle(String idDetalle, String idMaestro) throws ClassNotFoundException, IOException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DB.getInstance().getConnection();
            pstm = con.prepareStatement(ESTADO_PRECARGA_DETALLE);
            pstm.setString(1, idDetalle);
            pstm.executeUpdate();
            updateStatusPrecargaMaestro(idMaestro);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // private final static String INSERT_OBS_GENERAL_LAMPARAS = "INSERT INTO ingresos_observ(ing_obs_text, ing_obs_tipo, ing_obs_lamparas) VALUES (?,?,?)";
    private final static String INSERT_GENERICO_MAESTRO = "INSERT INTO ingresos_plan_maestro (plan_maestro_detalle_id) VALUES (?)";

    private final static String ESTADO_PRECARGA_DETALLE_GENERICO = "UPDATE precarga_detalle SET precarga_detalle_diseno_class = 'diseno_completo' WHERE precarga_detalle_id = ?";

    private final static String INSERT_GENERICO_DETALLE_BUJIA_ENCEN = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void bujiaEncenInsertGenerico(Map miPc) throws Exception {

        // if plan control = precalentamiento
        PlanBujiaEncen miPlanControl = new PlanBujiaEncen(
                (String) miPc.get("identificacion_bujia_encendido"),
                (String) miPc.get("lote_bujia_encendido"),
                (String) miPc.get("rosca_bujia_encendido"),
                (String) miPc.get("largo_rosca_bujia_encendido"),
                (String) miPc.get("hexagono_bujia_encendido"),
                (String) miPc.get("asiento_bujia_encendido"),
                (String) miPc.get("resistor_bujia_encendido"),
                (String) miPc.get("rango_termico_bujia_encendido"),
                (String) miPc.get("posicion_electrodo_bujia_encendido"),
                (String) miPc.get("cantidad_electrodos_bujia_encendido"),
                (String) miPc.get("identificacion_bujia_encendido_id"),
                (String) miPc.get("lote_bujia_encendido_id"),
                (String) miPc.get("rosca_bujia_encendido_id"),
                (String) miPc.get("largo_rosca_bujia_encendido_id"),
                (String) miPc.get("hexagono_bujia_encendido_id"),
                (String) miPc.get("asiento_bujia_encendido_id"),
                (String) miPc.get("resistor_bujia_encendido_id"),
                (String) miPc.get("rango_termico_bujia_encendido_id"),
                (String) miPc.get("posicion_electrodo_bujia_encendido_id"),
                (String) miPc.get("cantidad_electrodos_bujia_encendido_id"),
                (Map) miPc.get("resistor")
        );
        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de bujia preca DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidaResistor().get("resistor_bujia_encendido_Nom_id"), (String) miPlanControl.getMedidaResistor().get("resistor_bujia_encendido_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("resistor_bujia_encendido_id"));

                    insertMedidas((String) miPlanControl.getMedidaResistor().get("resistor_bujia_encendido_Min_id"), (String) miPlanControl.getMedidaResistor().get("resistor_bujia_encendido_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("resistor_bujia_encendido_id"));

                    insertMedidas((String) miPlanControl.getMedidaResistor().get("resistor_bujia_encendido_Max_id"), (String) miPlanControl.getMedidaResistor().get("resistor_bujia_encendido_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("resistor_bujia_encendido_id"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
//                System.out.println("ID's "
//                        + miPlanControl.getIdentificacionId()
//                        + " " + miPlanControl.getVisualId()
//                        + " " + miPlanControl.getLoteId()
//                        + " " + miPlanControl.getDiametroCarcasaId()
//                        + " " + miPlanControl.getLargoFiltroId()
//                        + " " + miPlanControl.getPicoEntradaRoscaId()
//                        + " " + miPlanControl.getPicoRetornoFiltroId()
//                        + " " + miPlanControl.getPicoRetornoTanqueId()
//                        + " " + miPlanControl.getPicoSalidaRoscaId()
//                        + " " + miPlanControl.getSoporteFijacionId()
//                        + " " + miPlanControl.getValvulaCanisterId()
//                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_BUJIA_ENCEN, Statement.RETURN_GENERATED_KEYS);
psmt.setInt(1, rsMaestro.getInt(1));

psmt.setString(2, miPlanControl.getIdentificacionId());

psmt.setString(3, miPlanControl.getIdentificacion());

psmt.setNull(4, java.sql.Types.INTEGER);

psmt.setString(5, (String) miPc.get("identificacion_bujia_encendido_obs"));



psmt.setInt(6, rsMaestro.getInt(1));

psmt.setString(7, miPlanControl.getLoteId());

psmt.setString(8, miPlanControl.getLote());

psmt.setNull(9, java.sql.Types.INTEGER);

psmt.setString(10, (String) miPc.get("lote_bujia_encendido_obs"));



psmt.setInt(11, rsMaestro.getInt(1));

psmt.setString(12, miPlanControl.getRoscaId());

psmt.setString(13, miPlanControl.getRosca());

psmt.setNull(14, java.sql.Types.INTEGER);

psmt.setString(15, (String) miPc.get("rosca_bujia_encendido_obs"));



psmt.setInt(16, rsMaestro.getInt(1));

psmt.setString(17, miPlanControl.getLargoRoscaId());

psmt.setString(18, miPlanControl.getLargoRosca());

psmt.setNull(19, java.sql.Types.INTEGER);

psmt.setString(20, (String) miPc.get("largo_rosca_bujia_encendido_obs"));



psmt.setInt(21, rsMaestro.getInt(1));

psmt.setString(22, miPlanControl.getHexagonoId());

psmt.setString(23, miPlanControl.getHexagono());

psmt.setNull(24, java.sql.Types.INTEGER);

psmt.setString(25, (String) miPc.get("hexagono_bujia_encendido_obs"));



psmt.setInt(26, rsMaestro.getInt(1));

psmt.setString(27, miPlanControl.getAsientoId());

psmt.setString(28, miPlanControl.getAsiento());

psmt.setNull(29, java.sql.Types.INTEGER);

psmt.setString(30, (String) miPc.get("asiento_bujia_encendido_obs"));



psmt.setInt(31, rsMaestro.getInt(1));

psmt.setString(32, miPlanControl.getResistorId());

psmt.setString(33, miPlanControl.getResistor());

psmt.setInt(34, medic_detale_maestro_id);

psmt.setString(35, (String) miPc.get("resistor_bujia_encendido_obs"));



psmt.setInt(36, rsMaestro.getInt(1));

psmt.setString(37, miPlanControl.getRangoTermicoId());

psmt.setString(38, miPlanControl.getRangoTermico());

psmt.setNull(39, java.sql.Types.INTEGER);

psmt.setString(40, (String) miPc.get("rango_termico_bujia_encendido_obs"));



psmt.setInt(41, rsMaestro.getInt(1));

psmt.setString(42, miPlanControl.getPosicionELectrodoId());

psmt.setString(43, miPlanControl.getPosicionELectrodo());

psmt.setNull(44, java.sql.Types.INTEGER);

psmt.setString(45, (String) miPc.get("posicion_electrodo_bujia_encendido_obs"));



psmt.setInt(46, rsMaestro.getInt(1));

psmt.setString(47, miPlanControl.getCantidadElectrodoId());

psmt.setString(48, miPlanControl.getCantidadElectrodo());

psmt.setNull(49, java.sql.Types.INTEGER);

psmt.setString(50, (String) miPc.get("cantidad_electrodos_bujia_encendido_obs"));


                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    ///
    ///
    ///  CODIGO ACTUAL
    ///
    ///
    private final static String INSERT_GENERICO_DETALLE_BUJIA_PRECA = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";
//    private final static String INSERT_GENERICO_DETALLE_BUJIA_PRECA = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";
//private final static String INSERT_GENERICO_DETALLE_DIESEL_U_SELLADA_PLASTICO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void bujiaPrecaInsertGENERICO(Map miPc) throws Exception {
        PlanBujiaPreca miPlanControl = new PlanBujiaPreca(
                (String) miPc.get("identificacion_bujia_preca"),
                (String) miPc.get("lote_bujia_preca"),
                (String) miPc.get("rosca_bujia_preca"),
                (String) miPc.get("hexagono_bujia_preca"),
                (String) miPc.get("longuitudL1_bujia_preca"),
                (String) miPc.get("longuitudL2_bujia_preca"),
                (String) miPc.get("longuitudL3_bujia_preca"),
                (String) miPc.get("perno_conexion_bujia_preca"),
                (String) miPc.get("identificacion_bujia_preca_id"),
                (String) miPc.get("lote_bujia_preca_id"),
                (String) miPc.get("rosca_bujia_preca_id"),
                (String) miPc.get("hexagono_bujia_preca_id"),
                (String) miPc.get("longuitudL1_bujia_preca_id"),
                (String) miPc.get("longuitudL2_bujia_preca_id"),
                (String) miPc.get("longuitudL3_bujia_preca_id"),
                (String) miPc.get("perno_conexion_bujia_preca_id"),
                (Map) miPc.get("longuitudL1"),
                (Map) miPc.get("longuitudL2"),
                (Map) miPc.get("longuitudL3")
        );

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidasL1Id().get("longuitudL1_bujia_preca_Nom_id"), (String) miPlanControl.getMedidasL1Id().get("longuitudL1_bujia_preca_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL1_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL1Id().get("longuitudL1_bujia_preca_Min_id"), (String) miPlanControl.getMedidasL1Id().get("longuitudL1_bujia_preca_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL1_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL1Id().get("longuitudL1_bujia_preca_Max_id"), (String) miPlanControl.getMedidasL1Id().get("longuitudL1_bujia_preca_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL1_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL2Id().get("longuitudL2_bujia_preca_Nom_id"), (String) miPlanControl.getMedidasL2Id().get("longuitudL2_bujia_preca_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL2_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL2Id().get("longuitudL2_bujia_preca_Min_id"), (String) miPlanControl.getMedidasL2Id().get("longuitudL2_bujia_preca_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL2_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL2Id().get("longuitudL2_bujia_preca_Max_id"), (String) miPlanControl.getMedidasL2Id().get("longuitudL2_bujia_preca_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL2_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL3Id().get("longuitudL3_bujia_preca_Nom_id"), (String) miPlanControl.getMedidasL3Id().get("longuitudL3_bujia_preca_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL3_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL3Id().get("longuitudL3_bujia_preca_Min_id"), (String) miPlanControl.getMedidasL3Id().get("longuitudL3_bujia_preca_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL3_bujia_preca_id"));

                    insertMedidas((String) miPlanControl.getMedidasL3Id().get("longuitudL3_bujia_preca_Max_id"), (String) miPlanControl.getMedidasL3Id().get("longuitudL3_bujia_preca_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("longuitudL3_bujia_preca_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
//                System.out.println("ID's "
//                        + miPlanControl.getIdentificacionId()
//                        + " " + miPlanControl.getVisualId()
//                        + " " + miPlanControl.getLoteId()
//                        + " " + miPlanControl.getDiametroCarcasaId()
//                        + " " + miPlanControl.getLargoFiltroId()
//                        + " " + miPlanControl.getPicoEntradaRoscaId()
//                        + " " + miPlanControl.getPicoRetornoFiltroId()
//                        + " " + miPlanControl.getPicoRetornoTanqueId()
//                        + " " + miPlanControl.getPicoSalidaRoscaId()
//                        + " " + miPlanControl.getSoporteFijacionId()
//                        + " " + miPlanControl.getValvulaCanisterId()
//                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_BUJIA_PRECA, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_bujia_preca_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getLoteId());

                psmt.setString(8, miPlanControl.getLote());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("lote_bujia_preca_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getRoscaId());

                psmt.setString(13, miPlanControl.getRosca());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("rosca_bujia_preca_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getHexagonoId());

                psmt.setString(18, miPlanControl.getHexagono());

                psmt.setNull(19, java.sql.Types.INTEGER);

                psmt.setString(20, (String) miPc.get("hexagono_bujia_preca_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getL1Id());

                psmt.setString(23, miPlanControl.getL1());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("longuitudL1_bujia_preca_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getL2Id());

                psmt.setString(28, miPlanControl.getL2());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("longuitudL2_bujia_preca_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getL3Id());

                psmt.setString(33, miPlanControl.getL3());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("longuitudL3_bujia_preca_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getPernoId());

                psmt.setString(38, miPlanControl.getPerno());

                psmt.setNull(39, java.sql.Types.INTEGER);

                psmt.setString(40, (String) miPc.get("perno_conexion_bujia_preca_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_LAMPARAS = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";
    private final static String INSERT_MEDICIONES_MAESTRO = "INSERT INTO ingresos_plan_detalle_medicion_maestro () VALUES ();";
    private final static String INSERT_MEDICIONES_DETALLE = "INSERT INTO ingresos_plan_detalle_medicion_detalle( medic_detalle_medicion_id, medic_detalle_medida, medic_detale_maestro_id, medic_detalle_tipo_plan ) VALUES (?,?,?,?)";

    public static void insertMedidas(String id, String medida, int maestro, String tipoPlan) throws Exception {
        Connection conInsertMedidasDetalle = null;
        PreparedStatement psmtInsertMedidasDetalle = null;
        ResultSet rsInsertMedidasDetalle = null;

        try {
            conInsertMedidasDetalle = DB.getInstance().getConnection();
            psmtInsertMedidasDetalle = conInsertMedidasDetalle.prepareStatement(INSERT_MEDICIONES_DETALLE);
            psmtInsertMedidasDetalle.setString(1, id);
            psmtInsertMedidasDetalle.setString(2, medida);
            psmtInsertMedidasDetalle.setInt(3, maestro);
            psmtInsertMedidasDetalle.setString(4, tipoPlan);
            psmtInsertMedidasDetalle.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("No se pudo insertar detalle de mediciones");
        } finally {
            if (conInsertMedidasDetalle != null) {
                try {
                    conInsertMedidasDetalle.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void lamparasInsertGENERICO(Map miPc) throws Exception {
        System.out.println("Lamparas DAO");

        PlanLamparas miPlanControl = new PlanLamparas(
                (String) miPc.get("identificacion_lamparas"),
                (String) miPc.get("lote_lamparas"),
                (String) miPc.get("diseno_lamparas"),
                (String) miPc.get("zocalo_lamparas"),
                (String) miPc.get("tension_lamparas"),
                (String) miPc.get("potencia_lamparas"),
                (String) miPc.get("chas_lamparas"),
                (String) miPc.get("identificacion_lamparas_id"),
                (String) miPc.get("lote_lamparas_id"),
                (String) miPc.get("diseno_lamparas_id"),
                (String) miPc.get("zocalo_lamparas_id"),
                (String) miPc.get("tension_lamparas_id"),
                (String) miPc.get("potencia_lamparas_id"),
                (String) miPc.get("chas_lamparas_id")
        );
        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        //System.out.println("Plan control de bujia preca DAO: " + miPlanControl);

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        try {

            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {

                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_LAMPARAS, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_lamparas_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getLoteId());

                psmt.setString(8, miPlanControl.getLote());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("lote_lamparas_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getDisenoId());

                psmt.setString(13, miPlanControl.getDiseno());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("diseno_lamparas_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getZocaloId());

                psmt.setString(18, miPlanControl.getZocalo());

                psmt.setNull(19, java.sql.Types.INTEGER);

                psmt.setString(20, (String) miPc.get("zocalo_lamparas_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getTensionId());

                psmt.setString(23, miPlanControl.getTension());

                psmt.setNull(24, java.sql.Types.INTEGER);

                psmt.setString(25, (String) miPc.get("tension_lamparas_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getPotenciaId());

                psmt.setString(28, miPlanControl.getPotencia());

                psmt.setNull(29, java.sql.Types.INTEGER);

                psmt.setString(30, (String) miPc.get("potencia_lamparas_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getChasId());

                psmt.setString(33, miPlanControl.getChas());

                psmt.setNull(34, java.sql.Types.INTEGER);

                psmt.setString(35, (String) miPc.get("chas_lamparas_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private final static String INSERT_GENERICO_DETALLE_AIRE_HABITACULO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";
    private final static String INSERT_OBS_GENERAL_AIRE_HABITACULO = "INSERT INTO ingresos_observ(ing_obs_text, ing_obs_tipo, ing_obs_plan_detalle_id) VALUES (?,?,?)";

    public static void aireHabitaculoInsertGENERICO(Map miPc) throws Exception {

        System.out.println("Medidas Largo: " + miPc.get("medidaLargo"));
        System.out.println("Medidas Alto: " + miPc.get("medidaAlto"));

        PlanAireHabit miPlanControl = new PlanAireHabit(
                (String) miPc.get("identificacion_filtro_aire_habit"),
                (String) miPc.get("aspecto_visual_filtro_aire_habit"),
                (String) miPc.get("lote_filtro_aire_habit"),
                (String) miPc.get("largo_filtro_aire_habit"),
                (String) miPc.get("alto_filtro_aire_habit"),
                (String) miPc.get("ancho_filtro_aire_habit"),
                (String) miPc.get("elem_filtrante_filtro_aire_habit"),
                (String) miPc.get("identificacion_filtro_aire_id"),
                (String) miPc.get("aspecto_visual_filtro_aire_habit_id"),
                (String) miPc.get("lote_filtro_aire_habit_id"),
                (String) miPc.get("largo_filtro_aire_habit_id"),
                (String) miPc.get("alto_filtro_aire_habit_id"),
                (String) miPc.get("ancho_filtro_aire_habit_id"),
                (String) miPc.get("elem_filtrante_filtro_aire_habit_id"),
                (Map) miPc.get("medidaLargo"),
                (Map) miPc.get("medidaAlto"),
                (Map) miPc.get("medidaAncho")
        );
        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("ID largo nominal1 : " + miPc.get("largo_filtro_aire_habit_Nom_id"));

        System.out.println("Medida nominal: " + miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Nom"));
        System.out.println("Medida min: " + miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Min"));
        System.out.println("Medida max: " + miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Max"));
        System.out.println("Medidas: " + miPlanControl.getMedidaLargo());
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {
                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);
                    insertMedidas((String) miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Nom_id"), (String) miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Nom"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getLargoId());
                    insertMedidas((String) miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Min_id"), (String) miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Min"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getLargoId());
                    insertMedidas((String) miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Max_id"), (String) miPlanControl.getMedidaLargo().get("largo_filtro_aire_habit_Max"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getLargoId());

                    insertMedidas((String) miPlanControl.getMedidaAlto().get("alto_filtro_aire_habit_Nom_id"), (String) miPlanControl.getMedidaAlto().get("alto_filtro_aire_habit_Nom"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAltoId());
                    insertMedidas((String) miPlanControl.getMedidaAlto().get("alto_filtro_aire_habit_Min_id"), (String) miPlanControl.getMedidaAlto().get("alto_filtro_aire_habit_Min"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAltoId());
                    insertMedidas((String) miPlanControl.getMedidaAlto().get("alto_filtro_aire_habit_Max_id"), (String) miPlanControl.getMedidaAlto().get("alto_filtro_aire_habit_Max"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAltoId());

                    insertMedidas((String) miPlanControl.getMedidaAncho().get("Ancho_filtro_aire_habit_Nom_id"), (String) miPlanControl.getMedidaAncho().get("Ancho_filtro_aire_habit_Nom"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAnchoId());
                    insertMedidas((String) miPlanControl.getMedidaAncho().get("Ancho_filtro_aire_habit_Min_id"), (String) miPlanControl.getMedidaAncho().get("Ancho_filtro_aire_habit_Min"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAnchoId());
                    insertMedidas((String) miPlanControl.getMedidaAncho().get("Ancho_filtro_aire_habit_Max_id"), (String) miPlanControl.getMedidaAncho().get("Ancho_filtro_aire_habit_Max"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAnchoId());

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {

                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_AIRE_HABITACULO, Statement.RETURN_GENERATED_KEYS);
                psmt.setInt(1, rsMaestro.getInt(1));
                psmt.setString(2, miPlanControl.getIdentificacionId());
                psmt.setString(3, miPlanControl.getIdentificacion());
                psmt.setNull(4, java.sql.Types.INTEGER);
                psmt.setString(5, (String) miPc.get("identificacion_filtro_aire_habit_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));
                psmt.setString(7, miPlanControl.getAspectoVisualId());
                psmt.setString(8, miPlanControl.getAspectoVisual());
                psmt.setNull(9, java.sql.Types.INTEGER);
                psmt.setString(10, (String) miPc.get("aspecto_visual_filtro_aire_habit_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));
                psmt.setString(12, miPlanControl.getLoteId());
                psmt.setString(13, miPlanControl.getLote());
                psmt.setNull(14, java.sql.Types.INTEGER);
                psmt.setString(15, (String) miPc.get("lote_filtro_aire_habit_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));
                psmt.setString(17, miPlanControl.getLargoId());
                psmt.setString(18, miPlanControl.getLargo());
                psmt.setInt(19, medic_detale_maestro_id);
                psmt.setString(20, (String) miPc.get("largo_filtro_aire_habit_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));
                psmt.setString(22, miPlanControl.getAltoId());
                psmt.setString(23, miPlanControl.getAlto());
                psmt.setInt(24, medic_detale_maestro_id);
                psmt.setString(25, (String) miPc.get("alto_filtro_aire_habit_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));
                psmt.setString(27, miPlanControl.getAnchoId());
                psmt.setString(28, miPlanControl.getAncho());
                psmt.setInt(29, medic_detale_maestro_id);
                psmt.setString(30, (String) miPc.get("Ancho_filtro_aire_habit_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));
                psmt.setString(32, miPlanControl.getElemFiltranteId());
                psmt.setString(33, miPlanControl.getElemFiltrante());
                psmt.setNull(34, java.sql.Types.INTEGER);
                psmt.setString(35, (String) miPc.get("elem_filtrante_filtro_aire_habit_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_ACEITE_U_SELLADA = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void aceiteUSelladaInsert(Map miPc) throws Exception {

        System.out.println("Medidas Largo: " + miPc.get("medidaAltoTotalJunta"));
        System.out.println("Medidas Alto: " + miPc.get("medidaDiametroCarcasa"));

        PlanAceiteUSellada miPlanControl = new PlanAceiteUSellada(
                (String) miPc.get("identificacion_filtro_aceite_u_sellada"),
                (String) miPc.get("aspecto_visual_filtro_aceite_u_sellada"),
                (String) miPc.get("lote_filtro_aceite_u_sellada"),
                (String) miPc.get("diametro_carcasa_filtro_aceite_u_sellada"),
                (String) miPc.get("alto_total_junta_filtro_aceite_u_sellada"),
                (String) miPc.get("diametro_junta_sello_filtro_aceite_u_sellada"),
                (String) miPc.get("dureza_junta_sello_filtro_aceite_u_sellada"),
                (String) miPc.get("altura_junta_sello_filtro_aceite_u_sellada"),
                (String) miPc.get("rosca_filtro_aceite_u_sellada"),
                (String) miPc.get("brida_filtro_aceite_u_sellada"),
                (String) miPc.get("flapper_filtro_aceite_u_sellada"),
                (String) miPc.get("valvula_descarga_filtro_aceite_u_sellada"),
                (String) miPc.get("valvula_seguridad_filtro_aceite_u_sellada"),
                (String) miPc.get("tuerca_fijacion_filtro_aceite_u_sellada"),
                (Map) miPc.get("medidaDiametroCarcasa"),
                (Map) miPc.get("medidaAltoTotalJunta"),
                (Map) miPc.get("medidaDiametroJuntaSello"),
                (Map) miPc.get("medidaDurezaJuntaSello"),
                (Map) miPc.get("medidaAlturaJuntaSello")
        );

        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_filtro_aceite_u_sellada_id"));
        miPlanControl.setAspectoVisualId((String) miPc.get("aspecto_visual_filtro_aceite_u_sellada_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_filtro_aceite_u_sellada_id"));
        miPlanControl.setDiametroCarcasaId((String) miPc.get("diametro_carcasa_filtro_aceite_u_sellada_id"));
        miPlanControl.setAltoTotalJuntaId((String) miPc.get("alto_total_junta_filtro_aceite_u_sellada_id"));
        miPlanControl.setDiametrojuntaSelloId((String) miPc.get("diametro_junta_sello_filtro_aceite_u_sellada_id"));
        miPlanControl.setDurezaJuntaSelloId((String) miPc.get("dureza_junta_sello_filtro_aceite_u_sellada_id"));
        miPlanControl.setAlturaJuntaSelloId((String) miPc.get("altura_junta_sello_filtro_aceite_u_sellada_id"));
        miPlanControl.setRoscaId((String) miPc.get("rosca_filtro_aceite_u_sellada_id"));
        miPlanControl.setBridaId((String) miPc.get("brida_filtro_aceite_u_sellada_id"));
        miPlanControl.setFalpperId((String) miPc.get("flapper_filtro_aceite_u_sellada_id"));
        miPlanControl.setValvulaDescargaId((String) miPc.get("valvula_descarga_filtro_aceite_u_sellada_id"));
        miPlanControl.setValvulaSeguridadId((String) miPc.get("valvula_seguridad_filtro_aceite_u_sellada_id"));
        miPlanControl.setTuercaFijacionId((String) miPc.get("tuerca_fijacion_filtro_aceite_u_sellada_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {
                    System.out.println("Nom id: " + miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Nom_id"));
                    System.out.println("Nom: " + miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Nom"));
                    System.out.println("Maestro id: " + rsInsertMedidasMaestro.getInt(1));
                    System.out.println("Alto total junta: " + miPlanControl.getAltoTotalJuntaId());
                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);
                    insertMedidas((String) miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Nom_id"), (String) miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("alto_total_junta_filtro_aceite_u_sellada_id"));
                    insertMedidas((String) miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Min_id"), (String) miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("alto_total_junta_filtro_aceite_u_sellada_id"));
                    insertMedidas((String) miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Max_id"), (String) miPlanControl.getMedidaAltoTotalJunta().get("alto_total_junta_filtro_aceite_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("alto_total_junta_filtro_aceite_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aceite_u_sellada_Nom_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aceite_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAlturaJuntaSelloId());
                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aceite_u_sellada_Min_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aceite_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAlturaJuntaSelloId());
                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aceite_u_sellada_Max_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aceite_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getAlturaJuntaSelloId());

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_filtro_aceite_u_sellada_Nom_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_filtro_aceite_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getDiametroCarcasaId());
                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_filtro_aceite_u_sellada_Min_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_filtro_aceite_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getDiametroCarcasaId());
                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_filtro_aceite_u_sellada_Max_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_filtro_aceite_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getDiametroCarcasaId());

                    insertMedidas((String) miPlanControl.getMedidaDiametroJuntaSello().get("diametro_junta_sello_filtro_aceite_u_sellada_Nom_id"), (String) miPlanControl.getMedidaDiametroJuntaSello().get("diametro_junta_sello_filtro_aceite_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_junta_sello_filtro_aceite_u_sellada_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiametroJuntaSello().get("diametro_junta_sello_filtro_aceite_u_sellada_Min_id"), (String) miPlanControl.getMedidaDiametroJuntaSello().get("diametro_junta_sello_filtro_aceite_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_junta_sello_filtro_aceite_u_sellada_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiametroJuntaSello().get("diametro_junta_sello_filtro_aceite_u_sellada_Max_id"), (String) miPlanControl.getMedidaDiametroJuntaSello().get("diametro_junta_sello_filtro_aceite_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_junta_sello_filtro_aceite_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaDurezaJuntaSello().get("dureza_junta_sello_filtro_aceite_u_sellada_Nom_id"), (String) miPlanControl.getMedidaDurezaJuntaSello().get("dureza_junta_sello_filtro_aceite_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getDurezaJuntaSelloId());
                    insertMedidas((String) miPlanControl.getMedidaDurezaJuntaSello().get("dureza_junta_sello_filtro_aceite_u_sellada_Min_id"), (String) miPlanControl.getMedidaDurezaJuntaSello().get("dureza_junta_sello_filtro_aceite_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getDurezaJuntaSelloId());
                    insertMedidas((String) miPlanControl.getMedidaDurezaJuntaSello().get("dureza_junta_sello_filtro_aceite_u_sellada_Max_id"), (String) miPlanControl.getMedidaDurezaJuntaSello().get("dureza_junta_sello_filtro_aceite_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), miPlanControl.getDurezaJuntaSelloId());

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
                System.out.println("ID's "
                        + miPlanControl.getIdentificacionId()
                        + " " + miPlanControl.getAspectoVisualId()
                        + " " + miPlanControl.getLoteId()
                        + " " + miPlanControl.getDiametroCarcasaId()
                        + " " + miPlanControl.getAltoTotalJuntaId()
                        + " " + miPlanControl.getAlturaJuntaSelloId()
                        + " " + miPlanControl.getDurezaJuntaSelloId()
                        + " " + miPlanControl.getBridaId()
                        + " " + miPlanControl.getFalpperId()
                        + " " + miPlanControl.getValvulaDescargaId()
                        + " " + miPlanControl.getValvulaSeguridadId()
                        + " " + miPlanControl.getTuercaFijacionId()
                        + " " + miPlanControl.getDiametrojuntaSelloId()
                        + " " + miPlanControl.getDiametrojuntaSelloId()
                        + " " + miPlanControl.getRoscaId()
                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_ACEITE_U_SELLADA, Statement.RETURN_GENERATED_KEYS);
                psmt.setInt(1, rsMaestro.getInt(1));
                psmt.setString(2, miPlanControl.getIdentificacionId());
                psmt.setString(3, miPlanControl.getIdentificacion());
                psmt.setNull(4, java.sql.Types.INTEGER);
                psmt.setString(5, (String) miPc.get("identificacion_filtro_aceite_u_sellada_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));
                psmt.setString(7, miPlanControl.getAspectoVisualId());
                psmt.setString(8, miPlanControl.getAspectoVisual());
                psmt.setNull(9, java.sql.Types.INTEGER);
                psmt.setString(10, (String) miPc.get("aspecto_visual_filtro_aceite_u_sellada_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));
                psmt.setString(12, miPlanControl.getLoteId());
                psmt.setString(13, miPlanControl.getLote());
                psmt.setNull(14, java.sql.Types.INTEGER);
                psmt.setString(15, (String) miPc.get("lote_filtro_aceite_u_sellada_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));
                psmt.setString(17, miPlanControl.getDiametroCarcasaId());
                psmt.setString(18, miPlanControl.getDiametroCarcasa());
                psmt.setInt(19, medic_detale_maestro_id);
                psmt.setString(20, (String) miPc.get("diametro_carcasa_filtro_aceite_u_sellada_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));
                psmt.setString(22, miPlanControl.getAltoTotalJuntaId());
                psmt.setString(23, miPlanControl.getAltoTotalJunta());
                psmt.setInt(24, medic_detale_maestro_id);
                psmt.setString(25, (String) miPc.get("alto_total_junta_filtro_aceite_u_sellada_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));
                psmt.setString(27, miPlanControl.getAlturaJuntaSelloId());
                psmt.setString(28, miPlanControl.getAlturaJuntaSello());
                psmt.setInt(29, medic_detale_maestro_id);
                psmt.setString(30, (String) miPc.get("altura_junta_sello_filtro_aceite_u_sellada_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));
                psmt.setString(32, miPlanControl.getDurezaJuntaSelloId());
                psmt.setString(33, miPlanControl.getDurezaJuntaSello());
                psmt.setInt(34, medic_detale_maestro_id);
                psmt.setString(35, (String) miPc.get("dureza_junta_sello_filtro_aceite_u_sellada_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));
                psmt.setString(37, miPlanControl.getBridaId());
                psmt.setString(38, miPlanControl.getBrida());
                psmt.setNull(39, java.sql.Types.INTEGER);
                psmt.setString(40, (String) miPc.get("brida_filtro_aceite_u_sellada_obs"));

                psmt.setInt(41, rsMaestro.getInt(1));
                psmt.setString(42, miPlanControl.getFalpperId());
                psmt.setString(43, miPlanControl.getFalpper());
                psmt.setNull(44, java.sql.Types.INTEGER);
                psmt.setString(45, (String) miPc.get("flapper_filtro_aceite_u_sellada_obs"));

                psmt.setInt(46, rsMaestro.getInt(1));
                psmt.setString(47, miPlanControl.getValvulaDescargaId());
                psmt.setString(48, miPlanControl.getValvulaDescarga());
                psmt.setNull(49, java.sql.Types.INTEGER);
                psmt.setString(50, (String) miPc.get("valvula_descarga_filtro_aceite_u_sellada_obs"));

                psmt.setInt(51, rsMaestro.getInt(1));
                psmt.setString(52, miPlanControl.getValvulaSeguridadId());
                psmt.setString(53, miPlanControl.getValvulaSeguridad());
                psmt.setNull(54, java.sql.Types.INTEGER);
                psmt.setString(55, (String) miPc.get("valvula_seguridad_filtro_aceite_u_sellada_obs"));

                psmt.setInt(56, rsMaestro.getInt(1));
                psmt.setString(57, miPlanControl.getTuercaFijacionId());
                psmt.setString(58, miPlanControl.getTuercaFijacion());
                psmt.setNull(59, java.sql.Types.INTEGER);
                psmt.setString(60, (String) miPc.get("tuerca_fijacion_filtro_aceite_u_sellada_obs"));

                psmt.setInt(61, rsMaestro.getInt(1));
                psmt.setString(62, miPlanControl.getDiametrojuntaSelloId());
                psmt.setString(63, miPlanControl.getDiametrojuntaSello());
                psmt.setInt(64, medic_detale_maestro_id);
                psmt.setString(65, (String) miPc.get("diametro_junta_sello_filtro_aceite_u_sellada_obs"));

                psmt.setInt(66, rsMaestro.getInt(1));
                psmt.setString(67, miPlanControl.getRoscaId());
                psmt.setString(68, miPlanControl.getRosca());
                psmt.setNull(69, java.sql.Types.INTEGER);
                psmt.setString(70, (String) miPc.get("rosca_filtro_aceite_u_sellada_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_ACEITE_CARTUCHO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void aceiteCartuchoInsert(Map miPc) throws Exception {

        System.out.println("Medidas Largo: " + miPc.get("medidaAltoTotalJunta"));
        System.out.println("Medidas Alto: " + miPc.get("medidaDiametroCarcasa"));
        System.out.println("medidas interno tapa superiro: " + miPc.get("medidaDiameInternoTapaSup"));
        PlanAceiteCartucho miPlanControl = new PlanAceiteCartucho(
                (String) miPc.get("identificacion_filtro_aceite_cartucho"),
                (String) miPc.get("aspecto_visual_filtro_aceite_cartucho"),
                (String) miPc.get("lote_filtro_aceite_cartucho"),
                (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho"),
                (String) miPc.get("dia_int_tapa_sup_filtro_aceite_cartucho"),
                (String) miPc.get("dia_tapa_inf_filtro_aceite_cartucho"),
                (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho"),
                (String) miPc.get("altura_tot_filtro_filtro_aceite_cartucho"),
                (String) miPc.get("sello1_filtro_aceite_cartucho"),
                (String) miPc.get("sello2_filtro_aceite_cartucho"),
                (String) miPc.get("sello3_filtro_aceite_cartucho"),
                (String) miPc.get("sello4_filtro_aceite_cartucho"),
                (String) miPc.get("arandelas_filtro_aceite_cartucho"),
                (Map) miPc.get("medidaAlturaTotal"),
                (Map) miPc.get("medidaDiamInteriorTapa"),
                (Map) miPc.get("medidaDiamTapaInferior"),
                (Map) miPc.get("medidaDiametroTapaSup"),
                (Map) miPc.get("medidaDiameInternoTapaSup"),
                (Map) miPc.get("medidaSello1"),
                (Map) miPc.get("medidaSello2"),
                (Map) miPc.get("medidaSello3"),
                (Map) miPc.get("medidaSello4")
        );

        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_filtro_aceite_cartucho_id"));
        miPlanControl.setVisualId((String) miPc.get("aspecto_visual_filtro_aceite_cartucho_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_filtro_aceite_cartucho_id"));
        miPlanControl.setDiamTapaSupId((String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
        miPlanControl.setDiamIntTapaSupId((String) miPc.get("dia_int_tapa_sup_filtro_aceite_cartucho_id"));
        miPlanControl.setDiamTapaInfId((String) miPc.get("dia_tapa_inf_filtro_aceite_cartucho_id"));
        miPlanControl.setDiamIntTapaInfId((String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_id"));
        miPlanControl.setAlturaTotalId((String) miPc.get("altura_tot_filtro_filtro_aceite_cartucho_id"));
        miPlanControl.setSello1Id((String) miPc.get("sello1_filtro_aceite_cartucho_id"));
        miPlanControl.setSello2Id((String) miPc.get("sello2_filtro_aceite_cartucho_id"));
        miPlanControl.setSello3Id((String) miPc.get("sello3_filtro_aceite_cartucho_id"));
        miPlanControl.setSello4Id((String) miPc.get("sello4_filtro_aceite_cartucho_id"));
        miPlanControl.setArandelasId((String) miPc.get("arandelas_filtro_aceite_cartucho_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_tot_filtro_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_tot_filtro_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_tot_filtro_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_tot_filtro_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_tot_filtro_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_tot_filtro_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_tot_filtro_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_tot_filtro_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_tot_filtro_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiamInteriorTapa().get("dia_int_tapa_inf_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiamInteriorTapa().get("dia_int_tapa_inf_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiamInteriorTapa().get("dia_int_tapa_inf_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaDiamInteriorTapa().get("dia_int_tapa_inf_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiamInteriorTapa().get("dia_int_tapa_inf_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaDiamInteriorTapa().get("dia_int_tapa_inf_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiamTapaInferior().get("dia_int_tapa_inf_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiamTapaInferior().get("dia_int_tapa_inf_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiamTapaInferior().get("dia_int_tapa_inf_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaDiamTapaInferior().get("dia_int_tapa_inf_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiamTapaInferior().get("dia_int_tapa_inf_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaDiamTapaInferior().get("dia_int_tapa_inf_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiameInternoTapaSup().get("dia_int_tapa_sup_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiameInternoTapaSup().get("dia_int_tapa_sup_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiameInternoTapaSup().get("dia_int_tapa_sup_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaDiameInternoTapaSup().get("dia_int_tapa_sup_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiameInternoTapaSup().get("dia_int_tapa_sup_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaDiameInternoTapaSup().get("dia_int_tapa_sup_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_int_tapa_sup_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroTapaSup().get("dia_tapa_sup_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroTapaSup().get("dia_tapa_sup_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiametroTapaSup().get("dia_tapa_sup_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroTapaSup().get("dia_tapa_sup_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaDiametroTapaSup().get("dia_tapa_sup_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroTapaSup().get("dia_tapa_sup_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaSello1().get("sello1_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello1().get("sello1_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello1().get("sello1_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaSello1().get("sello1_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello1().get("sello1_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaSello1().get("sello1_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaSello2().get("sello2_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello2().get("sello2_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello2().get("sello2_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaSello2().get("sello2_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello2().get("sello2_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaSello2().get("sello2_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaSello3().get("sello3_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello3().get("sello3_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello3().get("sello3_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaSello3().get("sello3_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello3().get("sello3_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaSello3().get("sello3_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaSello4().get("sello4_filtro_aceite_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello4().get("sello4_filtro_aceite_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello4().get("sello4_filtro_aceite_cartucho_Min_id"), (String) miPlanControl.getMedidaSello4().get("sello4_filtro_aceite_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));
                    insertMedidas((String) miPlanControl.getMedidaSello4().get("sello4_filtro_aceite_cartucho_Max_id"), (String) miPlanControl.getMedidaSello4().get("sello4_filtro_aceite_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
                System.out.println("ID's "
                        + miPlanControl.getIdentificacionId()
                        + " " + miPlanControl.getVisualId()
                        + " " + miPlanControl.getLoteId()
                        + " " + miPlanControl.getDiamTapaSupId()
                        + " " + miPlanControl.getDiamIntTapaSupId()
                        + " " + miPlanControl.getDiamTapaInfId()
                        + " " + miPlanControl.getDiamIntTapaInfId()
                        + " " + miPlanControl.getAlturaTotalId()
                        + " " + miPlanControl.getSello1Id()
                        + " " + miPlanControl.getSello2Id()
                        + " " + miPlanControl.getSello3Id()
                        + " " + miPlanControl.getSello4Id()
                        + " " + miPlanControl.getArandelasId()
                );
                try {
                    con = DB.getInstance().getConnection();
                    psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_ACEITE_CARTUCHO, Statement.RETURN_GENERATED_KEYS);
                    psmt.setInt(1, rsMaestro.getInt(1));
                    psmt.setString(2, miPlanControl.getIdentificacionId());
                    psmt.setString(3, miPlanControl.getIdentificacion());
                    psmt.setNull(4, java.sql.Types.INTEGER);
                    psmt.setString(5, (String) miPc.get("identificacion_filtro_aceite_cartucho_obs"));

                    psmt.setInt(6, rsMaestro.getInt(1));
                    psmt.setString(7, miPlanControl.getVisualId());
                    psmt.setString(8, miPlanControl.getVisual());
                    psmt.setNull(9, java.sql.Types.INTEGER);
                    psmt.setString(10, (String) miPc.get("aspecto_visual_filtro_aceite_cartucho_obs"));

                    psmt.setInt(11, rsMaestro.getInt(1));
                    psmt.setString(12, miPlanControl.getLoteId());
                    psmt.setString(13, miPlanControl.getLote());
                    psmt.setNull(14, java.sql.Types.INTEGER);
                    psmt.setString(15, (String) miPc.get("lote_filtro_aceite_cartucho_obs"));

                    psmt.setInt(16, rsMaestro.getInt(1));
                    psmt.setString(17, miPlanControl.getDiamTapaSupId());
                    psmt.setString(18, miPlanControl.getDiamTapaSup());
                    psmt.setInt(19, medic_detale_maestro_id);
                    psmt.setString(20, (String) miPc.get("dia_tapa_sup_filtro_aceite_cartucho_obs"));

                    psmt.setInt(21, rsMaestro.getInt(1));
                    psmt.setString(22, miPlanControl.getDiamIntTapaSupId());
                    psmt.setString(23, miPlanControl.getDiamIntTapaSup());
                    psmt.setInt(24, medic_detale_maestro_id);
                    psmt.setString(25, (String) miPc.get("dia_int_tapa_sup_filtro_aceite_cartucho_obs"));

                    psmt.setInt(26, rsMaestro.getInt(1));
                    psmt.setString(27, miPlanControl.getDiamTapaInfId());
                    psmt.setString(28, miPlanControl.getDiamTapaInf());
                    psmt.setInt(29, medic_detale_maestro_id);
                    psmt.setString(30, (String) miPc.get("dia_tapa_inf_filtro_aceite_cartucho_obs"));

                    psmt.setInt(31, rsMaestro.getInt(1));
                    psmt.setString(32, miPlanControl.getDiamIntTapaInfId());
                    psmt.setString(33, miPlanControl.getDiamIntTapaInf());
                    psmt.setInt(34, medic_detale_maestro_id);
                    psmt.setString(35, (String) miPc.get("dia_int_tapa_inf_filtro_aceite_cartucho_obs"));

                    psmt.setInt(36, rsMaestro.getInt(1));
                    psmt.setString(37, miPlanControl.getAlturaTotalId());
                    psmt.setString(38, miPlanControl.getAlturaTotal());
                    psmt.setInt(39, medic_detale_maestro_id);
                    psmt.setString(40, (String) miPc.get("altura_tot_filtro_filtro_aceite_cartucho_obs"));

                    psmt.setInt(41, rsMaestro.getInt(1));
                    psmt.setString(42, miPlanControl.getSello1Id());
                    psmt.setString(43, miPlanControl.getSello1());
                    psmt.setInt(44, medic_detale_maestro_id);
                    psmt.setString(45, (String) miPc.get("sello1_filtro_aceite_cartucho_obs"));

                    psmt.setInt(46, rsMaestro.getInt(1));
                    psmt.setString(47, miPlanControl.getSello2Id());
                    psmt.setString(48, miPlanControl.getSello2());
                    psmt.setInt(49, medic_detale_maestro_id);
                    psmt.setString(50, (String) miPc.get("sello2_filtro_aceite_cartucho_obs"));

                    psmt.setInt(51, rsMaestro.getInt(1));
                    psmt.setString(52, miPlanControl.getSello3Id());
                    psmt.setString(53, miPlanControl.getSello3());
                    psmt.setInt(54, medic_detale_maestro_id);
                    psmt.setString(55, (String) miPc.get("sello3_filtro_aceite_cartucho_obs"));

                    psmt.setInt(56, rsMaestro.getInt(1));
                    psmt.setString(57, miPlanControl.getSello4Id());
                    psmt.setString(58, miPlanControl.getSello4());
                    psmt.setInt(59, medic_detale_maestro_id);
                    psmt.setString(60, (String) miPc.get("sello4_filtro_aceite_cartucho_obs"));

                    psmt.setInt(61, rsMaestro.getInt(1));
                    psmt.setString(62, miPlanControl.getArandelasId());
                    psmt.setString(63, miPlanControl.getArandelas());
                    psmt.setNull(64, java.sql.Types.INTEGER);
                    psmt.setString(65, (String) miPc.get("arandelas_filtro_aceite_cartucho_obs"));

                    psmt.executeUpdate();
                    rs = psmt.getGeneratedKeys();

                } catch (Exception ex) {
                    throw new Exception("Exepcion: " + ex.getMessage());

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
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_ACEITE_FAP = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void aireFAPInsert(Map miPc) throws Exception {
        System.out.println("Medidas Largo: " + miPc.get("medidaAltoTotalJunta"));
        System.out.println("Medidas Alto: " + miPc.get("medidaDiametroCarcasa"));
        System.out.println("medidas interno tapa superiro: " + miPc.get("medidaDiameInternoTapaSup"));
        PlanAireFAP miPlanControl = new PlanAireFAP(
                (String) miPc.get("identificacion_FAP"),
                (String) miPc.get("Aspecto_visual_FAP"),
                (String) miPc.get("lote_FAP"),
                (String) miPc.get("largo1_FAP"),
                (String) miPc.get("largo2_FAP"),
                (String) miPc.get("ancho1_FAP"),
                (String) miPc.get("ancho2_FAP"),
                (String) miPc.get("alto_FAP"),
                (String) miPc.get("hotmel_FAP"),
                (String) miPc.get("manto_FAP"),
                (String) miPc.get("prefiltro_FAP"),
                (String) miPc.get("Manija_FAP"),
                (String) miPc.get("filtrante_FAP"),
                (Map) miPc.get("largo1"),
                (Map) miPc.get("largo2"),
                (Map) miPc.get("ancho1"),
                (Map) miPc.get("ancho2"),
                (Map) miPc.get("alto")
        );

        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_FAP_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_FAP_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_FAP_id"));
        miPlanControl.setLargo1Id((String) miPc.get("largo1_FAP_id"));
        miPlanControl.setLargo2Id((String) miPc.get("largo2_FAP_id"));
        miPlanControl.setAncho1Id((String) miPc.get("ancho1_FAP_id"));
        miPlanControl.setAncho2Id((String) miPc.get("ancho2_FAP_id"));
        miPlanControl.setAltoId((String) miPc.get("alto_FAP_id"));
        miPlanControl.setHotmelId((String) miPc.get("hotmel_FAP_id"));
        miPlanControl.setMantoId((String) miPc.get("manto_FAP_id"));
        miPlanControl.setPrefiltroId((String) miPc.get("prefiltro_FAP_id"));
        miPlanControl.setManijaId((String) miPc.get("Manija_FAP_id"));
        miPlanControl.setFiltranteId((String) miPc.get("filtrante_FAP_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidaLargo1().get("largo1_FAP_Nom_id"), (String) miPlanControl.getMedidaLargo1().get("largo1_FAP_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo1_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaLargo1().get("largo1_FAP_Min_id"), (String) miPlanControl.getMedidaLargo1().get("largo1_FAP_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo1_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaLargo1().get("largo1_FAP_Max_id"), (String) miPlanControl.getMedidaLargo1().get("largo1_FAP_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo1_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaLargo2().get("largo2_FAP_Nom_id"), (String) miPlanControl.getMedidaLargo2().get("largo2_FAP_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo2_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaLargo2().get("largo2_FAP_Min_id"), (String) miPlanControl.getMedidaLargo2().get("largo2_FAP_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo2_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaLargo2().get("largo2_FAP_Max_id"), (String) miPlanControl.getMedidaLargo2().get("largo2_FAP_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo2_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAncho1().get("ancho1_FAP_Nom_id"), (String) miPlanControl.getMedidaAncho1().get("ancho1_FAP_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("ancho1_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAncho1().get("ancho1_FAP_Min_id"), (String) miPlanControl.getMedidaAncho1().get("ancho1_FAP_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("ancho1_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAncho1().get("ancho1_FAP_Max_id"), (String) miPlanControl.getMedidaAncho1().get("ancho1_FAP_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("ancho1_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAncho2().get("ancho2_FAP_Nom_id"), (String) miPlanControl.getMedidaAncho2().get("ancho2_FAP_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("ancho2_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAncho2().get("ancho2_FAP_Min_id"), (String) miPlanControl.getMedidaAncho2().get("ancho2_FAP_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("ancho2_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAncho2().get("ancho2_FAP_Max_id"), (String) miPlanControl.getMedidaAncho2().get("ancho2_FAP_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("ancho2_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlto().get("alto_FAP_Nom_id"), (String) miPlanControl.getMedidaAlto().get("alto_FAP_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("alto_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlto().get("alto_FAP_Min_id"), (String) miPlanControl.getMedidaAlto().get("alto_FAP_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("alto_FAP_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlto().get("alto_FAP_Max_id"), (String) miPlanControl.getMedidaAlto().get("alto_FAP_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("alto_FAP_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
//                System.out.println("ID's "
//                        + miPlanControl.getIdentificacionId()
//                        + " " + miPlanControl.getVisualId()
//                        + " " + miPlanControl.getLoteId()
//                        + " " + miPlanControl.getDiamTapaSupId()
//                        + " " + miPlanControl.getDiamIntTapaSupId()
//                        + " " + miPlanControl.getDiamTapaInfId()
//                        + " " + miPlanControl.getDiamIntTapaInfId()
//                        + " " + miPlanControl.getAlturaTotalId()
//                        + " " + miPlanControl.getSello1Id()
//                        + " " + miPlanControl.getSello2Id()
//                        + " " + miPlanControl.getSello3Id()
//                        + " " + miPlanControl.getSello4Id()
//                        + " " + miPlanControl.getArandelasId()
//                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_ACEITE_FAP, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_FAP_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getVisualId());

                psmt.setString(8, miPlanControl.getVisual());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("Aspecto_visual_FAP_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getLoteId());

                psmt.setString(13, miPlanControl.getLote());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("lote_FAP_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getLargo1Id());

                psmt.setString(18, miPlanControl.getLargo1());

                psmt.setInt(19, medic_detale_maestro_id);

                psmt.setString(20, (String) miPc.get("largo1_FAP_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getLargo2Id());

                psmt.setString(23, miPlanControl.getLargo2());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("largo2_FAP_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getAncho1Id());

                psmt.setString(28, miPlanControl.getAncho1());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("ancho1_FAP_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getAncho2Id());

                psmt.setString(33, miPlanControl.getAncho2());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("ancho1_FAP_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getAltoId());

                psmt.setString(38, miPlanControl.getAlto());

                psmt.setInt(39, medic_detale_maestro_id);

                psmt.setString(40, (String) miPc.get("alto_FAP_obs"));

                psmt.setInt(41, rsMaestro.getInt(1));

                psmt.setString(42, miPlanControl.getHotmelId());

                psmt.setString(43, miPlanControl.getHotmel());

                psmt.setNull(44, java.sql.Types.INTEGER);

                psmt.setString(45, (String) miPc.get("hotmel_FAP_obs"));

                psmt.setInt(46, rsMaestro.getInt(1));

                psmt.setString(47, miPlanControl.getMantoId());

                psmt.setString(48, miPlanControl.getManto());

                psmt.setNull(49, java.sql.Types.INTEGER);

                psmt.setString(50, (String) miPc.get("manto_FAP_obs"));

                psmt.setInt(51, rsMaestro.getInt(1));

                psmt.setString(52, miPlanControl.getPrefiltroId());

                psmt.setString(53, miPlanControl.getPrefiltro());

                psmt.setNull(54, java.sql.Types.INTEGER);

                psmt.setString(55, (String) miPc.get("prefiltro_FAP_obs"));

                psmt.setInt(56, rsMaestro.getInt(1));

                psmt.setString(57, miPlanControl.getManijaId());

                psmt.setString(58, miPlanControl.getManija());

                psmt.setNull(59, java.sql.Types.INTEGER);

                psmt.setString(60, (String) miPc.get("Manija_FAP_obs"));

                psmt.setInt(61, rsMaestro.getInt(1));

                psmt.setString(62, miPlanControl.getFiltranteId());

                psmt.setString(63, miPlanControl.getFiltrante());

                psmt.setNull(64, java.sql.Types.INTEGER);

                psmt.setString(65, (String) miPc.get("filtrante_FAP_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_AIRE_REDONDO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void aireRedondo(Map miPc) throws Exception {
//        System.out.println("Medidas Largo: " + miPc.get("medidaAltoTotalJunta"));
//        System.out.println("Medidas Alto: " + miPc.get("medidaDiametroCarcasa"));
//        System.out.println("medidas interno tapa superiro: " + miPc.get("medidaDiameInternoTapaSup"));
        PlanAireRedondo miPlanControl = new PlanAireRedondo(
                (String) miPc.get("identificacion_filtro_aire_redondos"),
                (String) miPc.get("Aspecto_visual_filtro_aire_redondos"),
                (String) miPc.get("lote_filtro_aire_redondos"),
                (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_redondos"),
                (String) miPc.get("diametro_int_tapa_sup_filtro_aire_redondos"),
                (String) miPc.get("diametro_ext_base_inf_filtro_aire_redondos"),
                (String) miPc.get("diametro_int_base_inf_filtro_aire_redondos"),
                (String) miPc.get("altura_total_filtro_aire_redondos"),
                (String) miPc.get("diametro_ext_junta_sello_filtro_aire_redondos"),
                (String) miPc.get("diametro_int_junta_sello_filtro_aire_redondos"),
                (String) miPc.get("altura_junta_sello_filtro_aire_redondos"),
                (String) miPc.get("elem_filtrante_filtro_aire_redondos"),
                (Map) miPc.get("diametro_ext_tapa_sup"),
                (Map) miPc.get("diametro_int_tapa_sup"),
                (Map) miPc.get("diametro_ext_base_inf"),
                (Map) miPc.get("diametro_int_base_inf"),
                (Map) miPc.get("altura_total"),
                (Map) miPc.get("diametro_ext_junta_sello"),
                (Map) miPc.get("diametro_int_junta_sello"),
                (Map) miPc.get("altura_junta_sello")
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_filtro_aire_redondos_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_filtro_aire_redondos_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_filtro_aire_redondos_id"));
        miPlanControl.setDiametroExteriorTapaSuperiorId((String) miPc.get("diametro_ext_tapa_sup_filtro_aire_redondos_id"));
        miPlanControl.setDiametroInteriorTapaSuperiorId((String) miPc.get("diametro_int_tapa_sup_filtro_aire_redondos_id"));
        miPlanControl.setDiametroExteriorBaseInferiorId((String) miPc.get("diametro_ext_base_inf_filtro_aire_redondos_id"));
        miPlanControl.setDiametroInteriorBaseInferiorId((String) miPc.get("diametro_int_base_inf_filtro_aire_redondos_id"));
        miPlanControl.setAlturaTotalId((String) miPc.get("altura_total_filtro_aire_redondos_id"));
        miPlanControl.setDiametroExteriorJuntaSelloId((String) miPc.get("diametro_ext_junta_sello_filtro_aire_redondos_id"));
        miPlanControl.setDiametroInteriorJuntaSelloId((String) miPc.get("diametro_int_junta_sello_filtro_aire_redondos_id"));
        miPlanControl.setElementoFiltranteId((String) miPc.get("elem_filtrante_filtro_aire_redondos_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaDiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaDiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaDiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorTapaSuperior().get("diametro_int_tapa_sup_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaDiametroInteriorTapaSuperior().get("diametro_int_tapa_sup_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_sup_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorTapaSuperior().get("diametro_int_tapa_sup_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaDiametroInteriorTapaSuperior().get("diametro_int_tapa_sup_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_sup_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorTapaSuperior().get("diametro_int_tapa_sup_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaDiametroInteriorTapaSuperior().get("diametro_int_tapa_sup_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_sup_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_base_inf_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_base_inf_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_base_inf_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorBaseInferior().get("diametro_int_base_inf_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaDiametroInteriorBaseInferior().get("diametro_int_base_inf_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_base_inf_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorBaseInferior().get("diametro_int_base_inf_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaDiametroInteriorBaseInferior().get("diametro_int_base_inf_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_base_inf_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorBaseInferior().get("diametro_int_base_inf_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaDiametroInteriorBaseInferior().get("diametro_int_base_inf_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_base_inf_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_redondos_Nom_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_redondos_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_redondos_Min_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_redondos_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_junta_sello_filtro_aire_redondos_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_redondos_Max_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_redondos_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_junta_sello_filtro_aire_redondos_id"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
//                System.out.println("ID's "
//                        + miPlanControl.getIdentificacionId()
//                        + " " + miPlanControl.getVisualId()
//                        + " " + miPlanControl.getLoteId()
//                        + " " + miPlanControl.getDiamTapaSupId()
//                        + " " + miPlanControl.getDiamIntTapaSupId()
//                        + " " + miPlanControl.getDiamTapaInfId()
//                        + " " + miPlanControl.getDiamIntTapaInfId()
//                        + " " + miPlanControl.getAlturaTotalId()
//                        + " " + miPlanControl.getSello1Id()
//                        + " " + miPlanControl.getSello2Id()
//                        + " " + miPlanControl.getSello3Id()
//                        + " " + miPlanControl.getSello4Id()
//                        + " " + miPlanControl.getArandelasId()
//                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_AIRE_REDONDO, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_filtro_aire_redondos_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getVisualId());

                psmt.setString(8, miPlanControl.getVisual());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("Aspecto_visual_filtro_aire_redondos_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getLoteId());

                psmt.setString(13, miPlanControl.getLote());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("lote_filtro_aire_redondos_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getDiametroExteriorTapaSuperiorId());

                psmt.setString(18, miPlanControl.getDiametroExteriorTapaSuperior());

                psmt.setInt(19, medic_detale_maestro_id);

                psmt.setString(20, (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_redondos_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getDiametroInteriorTapaSuperiorId());

                psmt.setString(23, miPlanControl.getDiametroInteriorTapaSuperior());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("diametro_int_tapa_sup_filtro_aire_redondos_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getDiametroExteriorBaseInferiorId());

                psmt.setString(28, miPlanControl.getDiametroExteriorBaseInferior());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("diametro_ext_base_inf_filtro_aire_redondos_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getDiametroInteriorBaseInferiorId());

                psmt.setString(33, miPlanControl.getDiametroInteriorBaseInferior());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("diametro_int_base_inf_filtro_aire_redondos_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getAlturaTotalId());

                psmt.setString(38, miPlanControl.getAlturaTotal());

                psmt.setInt(39, medic_detale_maestro_id);

                psmt.setString(40, (String) miPc.get("altura_total_filtro_aire_redondos_obs"));

                psmt.setInt(41, rsMaestro.getInt(1));

                psmt.setString(42, miPlanControl.getDiametroExteriorJuntaSelloId());

                psmt.setString(43, miPlanControl.getDiametroExteriorJuntaSello());

                psmt.setInt(44, medic_detale_maestro_id);

                psmt.setString(45, (String) miPc.get("diametro_ext_junta_sello_filtro_aire_redondos_obs"));

                psmt.setInt(46, rsMaestro.getInt(1));

                psmt.setString(47, miPlanControl.getDiametroInteriorJuntaSelloId());

                psmt.setString(48, miPlanControl.getDiametroInteriorJuntaSello());

                psmt.setInt(49, medic_detale_maestro_id);

                psmt.setString(50, (String) miPc.get("diametro_int_junta_sello_filtro_aire_redondos_obs"));

                psmt.setInt(51, rsMaestro.getInt(1));

                psmt.setString(52, miPlanControl.getElementoFiltranteId());

                psmt.setString(53, miPlanControl.getElementoFiltrante());

                psmt.setNull(54, java.sql.Types.INTEGER);

                psmt.setString(55, (String) miPc.get("elem_filtrante_filtro_aire_redondos_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_AIRE_PESADO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void airePesado(Map miPc) throws Exception {

        PlanAirePesados miPlanControl = new PlanAirePesados(
                (String) miPc.get("identificacion_filtro_aire_pesados"),
                (String) miPc.get("Aspecto_visual_filtro_aire_pesados"),
                (String) miPc.get("lote_filtro_aire_pesados"),
                (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_pesados"),
                (String) miPc.get("diametro_agujero_tapa_superior_filtro_aire_pesados"),
                (String) miPc.get("diametro_ext_base_inf_filtro_aire_pesados"),
                (String) miPc.get("diametro_agujero_base_inf_filtro_aire_pesados"),
                (String) miPc.get("altura_total_filtro_aire_pesados"),
                (String) miPc.get("diametro_ext_junta_sello_filtro_aire_pesados"),
                (String) miPc.get("diametro_int_junta_sello_filtro_aire_pesados"),
                (String) miPc.get("altura_junta_sello_filtro_aire_pesados"),
                (Map) miPc.get("diametro_ext_tapa_sup"),
                (Map) miPc.get("diametro_agujero_tapa_superior"),
                (Map) miPc.get("diametro_ext_base_inf"),
                (Map) miPc.get("diametro_agujero_base_inf"),
                (Map) miPc.get("altura_total"),
                (Map) miPc.get("diametro_ext_junta_sello"),
                (Map) miPc.get("diametro_int_junta_sello"),
                (Map) miPc.get("altura_junta_sello")
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_filtro_aire_pesados_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_filtro_aire_pesados_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_filtro_aire_pesados_id"));
        miPlanControl.setDiametroExteriorTapaSuperiorId((String) miPc.get("diametro_ext_tapa_sup_filtro_aire_pesados_id"));
        miPlanControl.setDiametroAgujeroTapaSuperiorId((String) miPc.get("diametro_agujero_tapa_superior_filtro_aire_pesados_id"));
        miPlanControl.setDiametroExteriorBaseInferiorId((String) miPc.get("diametro_ext_base_inf_filtro_aire_pesados_id"));
        miPlanControl.setDiametroAgujeroBaseInferiorId((String) miPc.get("diametro_agujero_base_inf_filtro_aire_pesados_id"));
        miPlanControl.setAlturaTotalId((String) miPc.get("altura_total_filtro_aire_pesados_id"));
        miPlanControl.setDiametroExteriorJuntaSelloId((String) miPc.get("diametro_ext_junta_sello_filtro_aire_pesados_id"));
        miPlanControl.setDiametroInteriorJuntaSelloId((String) miPc.get("diametro_int_junta_sello_filtro_aire_pesados_id"));
        miPlanControl.setAlturaJuntaSelloId((String) miPc.get("altura_junta_sello_filtro_aire_pesados_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidadiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidadiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidadiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidadiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidadiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidadiametroExteriorTapaSuperior().get("diametro_ext_tapa_sup_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroAgujeroTapaSuperior().get("diametro_agujero_tapa_superior_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidaDiametroAgujeroTapaSuperior().get("diametro_agujero_tapa_superior_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_agujero_tapa_superior_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroAgujeroTapaSuperior().get("diametro_agujero_tapa_superior_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidaDiametroAgujeroTapaSuperior().get("diametro_agujero_tapa_superior_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_agujero_tapa_superior_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroAgujeroTapaSuperior().get("diametro_agujero_tapa_superior_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidaDiametroAgujeroTapaSuperior().get("diametro_agujero_tapa_superior_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_agujero_tapa_superior_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_base_inf_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_base_inf_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidaDiametroExteriorBaseInferior().get("diametro_ext_base_inf_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_base_inf_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroAgujeroBaseInferior().get("diametro_agujero_base_inf_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidaDiametroAgujeroBaseInferior().get("diametro_agujero_base_inf_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_agujero_base_inf_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroAgujeroBaseInferior().get("diametro_agujero_base_inf_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidaDiametroAgujeroBaseInferior().get("diametro_agujero_base_inf_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_agujero_base_inf_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroAgujeroBaseInferior().get("diametro_agujero_base_inf_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidaDiametroAgujeroBaseInferior().get("diametro_agujero_base_inf_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_agujero_base_inf_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidaDiametroExteriorJuntaSello().get("diametro_ext_junta_sello_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidaDiametroInteriorJuntaSello().get("diametro_int_junta_sello_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_pesados_Nom_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_pesados_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_pesados_Min_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_pesados_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_junta_sello_filtro_aire_pesados_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_pesados_Max_id"), (String) miPlanControl.getMedidaAlturaJuntaSello().get("altura_junta_sello_filtro_aire_pesados_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_junta_sello_filtro_aire_pesados_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
//                System.out.println("ID's "
//                        + miPlanControl.getIdentificacionId()
//                        + " " + miPlanControl.getVisualId()
//                        + " " + miPlanControl.getLoteId()
//                        + " " + miPlanControl.getDiamTapaSupId()
//                        + " " + miPlanControl.getDiamIntTapaSupId()
//                        + " " + miPlanControl.getDiamTapaInfId()
//                        + " " + miPlanControl.getDiamIntTapaInfId()
//                        + " " + miPlanControl.getAlturaTotalId()
//                        + " " + miPlanControl.getSello1Id()
//                        + " " + miPlanControl.getSello2Id()
//                        + " " + miPlanControl.getSello3Id()
//                        + " " + miPlanControl.getSello4Id()
//                        + " " + miPlanControl.getArandelasId()
//                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_AIRE_PESADO, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_filtro_aire_pesados_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getVisualId());

                psmt.setString(8, miPlanControl.getVisual());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("Aspecto_visual_filtro_aire_pesados_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getLoteId());

                psmt.setString(13, miPlanControl.getLote());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("lote_filtro_aire_pesados_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getDiametroExteriorTapaSuperiorId());

                psmt.setString(18, miPlanControl.getDiametroExteriorTapaSuperior());

                psmt.setInt(19, medic_detale_maestro_id);

                psmt.setString(20, (String) miPc.get("diametro_ext_tapa_sup_filtro_aire_pesados_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getDiametroAgujeroTapaSuperiorId());

                psmt.setString(23, miPlanControl.getDiametroAgujeroTapaSuperior());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("diametro_agujero_tapa_superior_filtro_aire_pesados_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getDiametroExteriorBaseInferiorId());

                psmt.setString(28, miPlanControl.getDiametroExteriorBaseInferior());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("diametro_ext_base_inf_filtro_aire_pesados_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getDiametroAgujeroBaseInferiorId());

                psmt.setString(33, miPlanControl.getDiametroAgujeroBaseInferior());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("diametro_agujero_base_inf_filtro_aire_pesados_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getAlturaTotalId());

                psmt.setString(38, miPlanControl.getAlturaTotal());

                psmt.setInt(39, medic_detale_maestro_id);

                psmt.setString(40, (String) miPc.get("altura_total_filtro_aire_pesados_obs"));

                psmt.setInt(41, rsMaestro.getInt(1));

                psmt.setString(42, miPlanControl.getDiametroExteriorJuntaSelloId());

                psmt.setString(43, miPlanControl.getDiametroExteriorJuntaSello());

                psmt.setInt(44, medic_detale_maestro_id);

                psmt.setString(45, (String) miPc.get("diametro_ext_junta_sello_filtro_aire_pesados_obs"));

                psmt.setInt(46, rsMaestro.getInt(1));

                psmt.setString(47, miPlanControl.getDiametroInteriorJuntaSelloId());

                psmt.setString(48, miPlanControl.getDiametroInteriorJuntaSello());

                psmt.setInt(49, medic_detale_maestro_id);

                psmt.setString(50, (String) miPc.get("diametro_int_junta_sello_filtro_aire_pesados_obs"));

                psmt.setInt(51, rsMaestro.getInt(1));

                psmt.setString(52, miPlanControl.getAlturaJuntaSelloId());

                psmt.setString(53, miPlanControl.getAlturaJuntaSello());

                psmt.setInt(54, medic_detale_maestro_id);

                psmt.setString(55, (String) miPc.get("altura_junta_sello_filtro_aire_pesados_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_NAFTA_CARTUCHO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void naftaCartucho(Map miPc) throws Exception {

        PlanNaftaCartucho miPlanControl = new PlanNaftaCartucho(
                (String) miPc.get("identificacion_nafta_cartucho"),
                (String) miPc.get("Aspecto_visual_nafta_cartucho"),
                (String) miPc.get("lote_nafta_cartucho"),
                (String) miPc.get("diametro_ext_tapa_sup_nafta_cartucho"),
                (String) miPc.get("diametro_int_tapa_superior_nafta_cartucho"),
                (String) miPc.get("diametro_ext_tapa_inf_nafta_cartucho"),
                (String) miPc.get("diametro_int_tapa_inf_nafta_cartucho"),
                (String) miPc.get("altura_total_nafta_cartucho"),
                (Map) miPc.get("Medida_diametro_ext_tapa_sup"),
                (Map) miPc.get("Medida_diametro_int_tapa_superior"),
                (Map) miPc.get("Medida_diametro_ext_tapa_inf"),
                (Map) miPc.get("Medida_diametro_int_tapa_inf"),
                (Map) miPc.get("Medida_altura_total")
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_nafta_cartucho_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_nafta_cartucho_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_nafta_cartucho_id"));
        miPlanControl.setDiametroExternoTapaSuperiorId((String) miPc.get("diametro_ext_tapa_sup_nafta_cartucho_id"));
        miPlanControl.setDiametroInternoTapaSuperiorId((String) miPc.get("diametro_int_tapa_superior_nafta_cartucho_id"));
        miPlanControl.setDiametroExternoTapaInferiorId((String) miPc.get("diametro_ext_tapa_inf_nafta_cartucho_id"));
        miPlanControl.setDiametroInternoTapaInferiorId((String) miPc.get("diametro_int_tapa_inf_nafta_cartucho_id"));
        miPlanControl.setAlturaTotalFiltroId((String) miPc.get("altura_total_nafta_cartucho_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);
                    insertMedidas((String) miPlanControl.getMedidadDametroExternoTapaSuperior().get("diametro_ext_tapa_sup_nafta_cartucho_Nom_id"), (String) miPlanControl.getMedidadDametroExternoTapaSuperior().get("diametro_ext_tapa_sup_nafta_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidadDametroExternoTapaSuperior().get("diametro_ext_tapa_sup_nafta_cartucho_Min_id"), (String) miPlanControl.getMedidadDametroExternoTapaSuperior().get("diametro_ext_tapa_sup_nafta_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidadDametroExternoTapaSuperior().get("diametro_ext_tapa_sup_nafta_cartucho_Max_id"), (String) miPlanControl.getMedidadDametroExternoTapaSuperior().get("diametro_ext_tapa_sup_nafta_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInternoTapaSuperior().get("diametro_int_tapa_superior_nafta_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroInternoTapaSuperior().get("diametro_int_tapa_superior_nafta_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_superior_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInternoTapaSuperior().get("diametro_int_tapa_superior_nafta_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroInternoTapaSuperior().get("diametro_int_tapa_superior_nafta_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_superior_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInternoTapaSuperior().get("diametro_int_tapa_superior_nafta_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroInternoTapaSuperior().get("diametro_int_tapa_superior_nafta_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_superior_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExternoTapaInferior().get("diametro_ext_tapa_inf_nafta_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroExternoTapaInferior().get("diametro_ext_tapa_inf_nafta_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_inf_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExternoTapaInferior().get("diametro_ext_tapa_inf_nafta_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroExternoTapaInferior().get("diametro_ext_tapa_inf_nafta_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_inf_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroExternoTapaInferior().get("diametro_ext_tapa_inf_nafta_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroExternoTapaInferior().get("diametro_ext_tapa_inf_nafta_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_inf_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInternoTapaInferior().get("diametro_int_tapa_inf_nafta_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroInternoTapaInferior().get("diametro_int_tapa_inf_nafta_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_inf_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInternoTapaInferior().get("diametro_int_tapa_inf_nafta_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroInternoTapaInferior().get("diametro_int_tapa_inf_nafta_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_inf_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroInternoTapaInferior().get("diametro_int_tapa_inf_nafta_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroInternoTapaInferior().get("diametro_int_tapa_inf_nafta_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_inf_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotalFiltro().get("altura_total_nafta_cartucho_Nom_id"), (String) miPlanControl.getMedidaAlturaTotalFiltro().get("altura_total_nafta_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotalFiltro().get("altura_total_nafta_cartucho_Min_id"), (String) miPlanControl.getMedidaAlturaTotalFiltro().get("altura_total_nafta_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_nafta_cartucho_id"));

                    insertMedidas((String) miPlanControl.getMedidaAlturaTotalFiltro().get("altura_total_nafta_cartucho_Max_id"), (String) miPlanControl.getMedidaAlturaTotalFiltro().get("altura_total_nafta_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_nafta_cartucho_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
                System.out.println("ID's "
                        + miPlanControl.getIdentificacionId()
                        + " " + miPlanControl.getVisualId()
                        + " " + miPlanControl.getLoteId()
                        + " " + miPlanControl.getDiametroExternoTapaInferiorId()
                        + " " + miPlanControl.getDiametroExternoTapaSuperiorId()
                        + " " + miPlanControl.getDiametroInternoTapaInferiorId()
                        + " " + miPlanControl.getDiametroInternoTapaSuperiorId()
                        + " " + miPlanControl.getAlturaTotalFiltroId()
                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_NAFTA_CARTUCHO, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_nafta_cartucho_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getVisualId());

                psmt.setString(8, miPlanControl.getVisual());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("Aspecto_visual_nafta_cartucho_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getLoteId());

                psmt.setString(13, miPlanControl.getLote());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("lote_nafta_cartucho_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getDiametroExternoTapaSuperiorId());

                psmt.setString(18, miPlanControl.getDiametroExternoTapaSuperior());

                psmt.setInt(19, medic_detale_maestro_id);

                psmt.setString(20, (String) miPc.get("diametro_ext_tapa_sup_nafta_cartucho_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getDiametroInternoTapaSuperiorId());

                psmt.setString(23, miPlanControl.getDiametroInternoTapaSuperior());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("diametro_int_tapa_superior_nafta_cartucho_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getDiametroExternoTapaInferiorId());

                psmt.setString(28, miPlanControl.getDiametroExternoTapaInferior());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("diametro_ext_tapa_inf_nafta_cartucho_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getDiametroInternoTapaInferiorId());

                psmt.setString(33, miPlanControl.getDiametroInternoTapaInferior());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("diametro_int_tapa_inf_nafta_cartucho_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getAlturaTotalFiltroId());

                psmt.setString(38, miPlanControl.getAlturaTotalFiltro());

                psmt.setInt(39, medic_detale_maestro_id);

                psmt.setString(40, (String) miPc.get("altura_total_nafta_cartucho_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    
    private final static String INSERT_GENERICO_DETALLE_NAFTA_TANK = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";
    public static void naftaTank(Map miPc) throws Exception {

        PlanNaftaTank miPlanControl = new PlanNaftaTank(
                (String) miPc.get("identificacion_nafta_tank"),
                (String) miPc.get("Aspecto_visual_nafta_tank"),
                (String) miPc.get("lote_nafta_tank"),
                (String) miPc.get("diametro1_nafta_tank"),
                (String) miPc.get("diametro2_nafta_tank"),
                (String) miPc.get("altura_total_nafta_tank"),
                (String) miPc.get("diametro_conector1_nafta_tank"),
                (String) miPc.get("diametro_conector2_nafta_tank"),
                (String) miPc.get("diametro_conector3_nafta_tank"),
                (String) miPc.get("diametro_alojamiento_nafta_tank"),
                (String) miPc.get("soporte_fijacion_nafta_tank"),
                (Map) miPc.get("Medida_diametro1"),
                (Map) miPc.get("Medida_diametro2"),
                (Map) miPc.get("Medida_altura_total"),
                (Map) miPc.get("Medida_diametro_conector1"),
                (Map) miPc.get("Medida_diametro_conector2"),
                (Map) miPc.get("Medida_diametro_conector3"),
                (Map) miPc.get("Medida_diametro_alojamiento")
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_nafta_tank_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_nafta_tank_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_nafta_tank_id"));
        miPlanControl.setDiametro1Id((String) miPc.get("diametro1_nafta_tank_id"));
        miPlanControl.setDiametro2Id((String) miPc.get("diametro2_nafta_tank_id"));
        miPlanControl.setAlturaTotalId((String) miPc.get("altura_total_nafta_tank_id"));
        miPlanControl.setDiametroConector1Id((String) miPc.get("diametro_conector1_nafta_tank_id"));
        miPlanControl.setDiametroConector2Id((String) miPc.get("diametro_conector2_nafta_tank_id"));
        miPlanControl.setDiametroConector3Id((String) miPc.get("diametro_conector3_nafta_tank_id"));
        miPlanControl.setDiametroAlojamientoId((String) miPc.get("diametro_alojamiento_nafta_tank_id"));
        miPlanControl.setSoporteFijacionId((String) miPc.get("soporte_fijacion_nafta_tank_id"));
        

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));
        System.out.println(miPlanControl);

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);
                    
                    insertMedidas((String) miPlanControl.getMedidaDiametro1().get("diametro1_nafta_tank_Nom_id"), (String) miPlanControl.getMedidaDiametro1().get("diametro1_nafta_tank_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro1_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametro1().get("diametro1_nafta_tank_Min_id"), (String) miPlanControl.getMedidaDiametro1().get("diametro1_nafta_tank_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro1_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametro1().get("diametro1_nafta_tank_Max_id"), (String) miPlanControl.getMedidaDiametro1().get("diametro1_nafta_tank_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro1_nafta_tank_id"));


insertMedidas((String) miPlanControl.getMedidaDiametro2().get("diametro2_nafta_tank_Nom_id"), (String) miPlanControl.getMedidaDiametro2().get("diametro2_nafta_tank_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro2_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametro2().get("diametro2_nafta_tank_Min_id"), (String) miPlanControl.getMedidaDiametro2().get("diametro2_nafta_tank_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro2_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametro2().get("diametro2_nafta_tank_Max_id"), (String) miPlanControl.getMedidaDiametro2().get("diametro2_nafta_tank_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro2_nafta_tank_id"));


insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_nafta_tank_Nom_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_nafta_tank_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_nafta_tank_Min_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_nafta_tank_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaAlturaTotal().get("altura_total_nafta_tank_Max_id"), (String) miPlanControl.getMedidaAlturaTotal().get("altura_total_nafta_tank_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altura_total_nafta_tank_id"));


insertMedidas((String) miPlanControl.getMedidaDiametroConector1().get("diametro_conector1_nafta_tank_Nom_id"), (String) miPlanControl.getMedidaDiametroConector1().get("diametro_conector1_nafta_tank_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector1_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroConector1().get("diametro_conector1_nafta_tank_Min_id"), (String) miPlanControl.getMedidaDiametroConector1().get("diametro_conector1_nafta_tank_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector1_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroConector1().get("diametro_conector1_nafta_tank_Max_id"), (String) miPlanControl.getMedidaDiametroConector1().get("diametro_conector1_nafta_tank_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector1_nafta_tank_id"));


insertMedidas((String) miPlanControl.getMedidaDiametroConector2().get("diametro_conector2_nafta_tank_Nom_id"), (String) miPlanControl.getMedidaDiametroConector2().get("diametro_conector2_nafta_tank_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector2_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroConector2().get("diametro_conector2_nafta_tank_Min_id"), (String) miPlanControl.getMedidaDiametroConector2().get("diametro_conector2_nafta_tank_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector2_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroConector2().get("diametro_conector2_nafta_tank_Max_id"), (String) miPlanControl.getMedidaDiametroConector2().get("diametro_conector2_nafta_tank_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector2_nafta_tank_id"));


insertMedidas((String) miPlanControl.getMedidaDiametroConector3().get("diametro_conector3_nafta_tank_Nom_id"), (String) miPlanControl.getMedidaDiametroConector3().get("diametro_conector3_nafta_tank_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector3_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroConector3().get("diametro_conector3_nafta_tank_Min_id"), (String) miPlanControl.getMedidaDiametroConector3().get("diametro_conector3_nafta_tank_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector3_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroConector3().get("diametro_conector3_nafta_tank_Max_id"), (String) miPlanControl.getMedidaDiametroConector3().get("diametro_conector3_nafta_tank_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_conector3_nafta_tank_id"));


insertMedidas((String) miPlanControl.getMedidaDiametroAlojamiento().get("diametro_alojamiento_nafta_tank_Nom_id"), (String) miPlanControl.getMedidaDiametroAlojamiento().get("diametro_alojamiento_nafta_tank_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_alojamiento_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroAlojamiento().get("diametro_alojamiento_nafta_tank_Min_id"), (String) miPlanControl.getMedidaDiametroAlojamiento().get("diametro_alojamiento_nafta_tank_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_alojamiento_nafta_tank_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroAlojamiento().get("diametro_alojamiento_nafta_tank_Max_id"), (String) miPlanControl.getMedidaDiametroAlojamiento().get("diametro_alojamiento_nafta_tank_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_alojamiento_nafta_tank_id"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
                System.out.println("ID's "
                        + miPlanControl.getIdentificacionId()
                        + " " + miPlanControl.getVisualId()
                        + " " + miPlanControl.getLoteId()
                       
                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_NAFTA_TANK, Statement.RETURN_GENERATED_KEYS);

               psmt.setInt(1, rsMaestro.getInt(1));

psmt.setString(2, miPlanControl.getIdentificacionId());

psmt.setString(3, miPlanControl.getIdentificacion());

psmt.setNull(4, java.sql.Types.INTEGER);

psmt.setString(5, (String) miPc.get("identificacion_nafta_tank_obs"));



psmt.setInt(6, rsMaestro.getInt(1));

psmt.setString(7, miPlanControl.getVisualId());

psmt.setString(8, miPlanControl.getVisual());

psmt.setNull(9, java.sql.Types.INTEGER);

psmt.setString(10, (String) miPc.get("Aspecto_visual_nafta_tank_obs"));



psmt.setInt(11, rsMaestro.getInt(1));

psmt.setString(12, miPlanControl.getLoteId());

psmt.setString(13, miPlanControl.getLote());

psmt.setNull(14, java.sql.Types.INTEGER);

psmt.setString(15, (String) miPc.get("lote_nafta_tank_obs"));



psmt.setInt(16, rsMaestro.getInt(1));

psmt.setString(17, miPlanControl.getDiametro1Id());

psmt.setString(18, miPlanControl.getDiametro1());

psmt.setInt(19, medic_detale_maestro_id);

psmt.setString(20, (String) miPc.get("diametro1_nafta_tank_obs"));



psmt.setInt(21, rsMaestro.getInt(1));

psmt.setString(22, miPlanControl.getDiametro2Id());

psmt.setString(23, miPlanControl.getDiametro2());

psmt.setInt(24, medic_detale_maestro_id);

psmt.setString(25, (String) miPc.get("diametro2_nafta_tank_obs"));



psmt.setInt(26, rsMaestro.getInt(1));

psmt.setString(27, miPlanControl.getAlturaTotalId());

psmt.setString(28, miPlanControl.getAlturaTotal());

psmt.setInt(29, medic_detale_maestro_id);

psmt.setString(30, (String) miPc.get("altura_total_nafta_tank_obs"));



psmt.setInt(31, rsMaestro.getInt(1));

psmt.setString(32, miPlanControl.getDiametroConector1Id());

psmt.setString(33, miPlanControl.getDiametroConector1());

psmt.setInt(34, medic_detale_maestro_id);

psmt.setString(35, (String) miPc.get("diametro_conector1_nafta_tank_obs"));



psmt.setInt(36, rsMaestro.getInt(1));

psmt.setString(37, miPlanControl.getDiametroConector2Id());

psmt.setString(38, miPlanControl.getDiametroConector2());

psmt.setInt(39, medic_detale_maestro_id);

psmt.setString(40, (String) miPc.get("diametro_conector2_nafta_tank_obs"));



psmt.setInt(41, rsMaestro.getInt(1));

psmt.setString(42, miPlanControl.getDiametroConector3Id());

psmt.setString(43, miPlanControl.getDiametroConector3());

psmt.setInt(44, medic_detale_maestro_id);

psmt.setString(45, (String) miPc.get("diametro_conector3_nafta_tank_obs"));



psmt.setInt(46, rsMaestro.getInt(1));

psmt.setString(47, miPlanControl.getDiametroAlojamientoId());

psmt.setString(48, miPlanControl.getDiametroAlojamiento());

psmt.setInt(49, medic_detale_maestro_id);

psmt.setString(50, (String) miPc.get("diametro_alojamiento_nafta_tank_obs"));



psmt.setInt(51, rsMaestro.getInt(1));

psmt.setString(52, miPlanControl.getSoporteFijacionId());

psmt.setString(53, miPlanControl.getSoporteFijacion());

psmt.setNull(54, java.sql.Types.INTEGER);

psmt.setString(55, (String) miPc.get("soporte_fijacion_nafta_tank_obs"));




                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_NAFTA_U_SELLADA = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void naftaUSellada(Map miPc) throws Exception {

        PlanNaftaUSellada miPlanControl = new PlanNaftaUSellada(
                (String) miPc.get("identificacion_nafta_u_sellada"),
                (String) miPc.get("Aspecto_visual_nafta_u_sellada"),
                (String) miPc.get("lote_nafta_u_sellada"),
                (String) miPc.get("diametro_carcasa_nafta_u_sellada"),
                (String) miPc.get("largo_tot_fieltro_nafta_u_sellada"),
                (String) miPc.get("pico_entrada_nafta_u_sellada"),
                (String) miPc.get("pico_salida_nafta_u_sellada"),
                (String) miPc.get("pico_ret_fieltro_nafta_u_sellada"),
                (String) miPc.get("pico_ret_tanque_nafta_u_sellada"),
                (String) miPc.get("valvula_canister_nafta_u_sellada"),
                (String) miPc.get("soporte_fijacion_nafta_u_sellada"),
                (Map) miPc.get("Medida_diametro_carcasa"),
                (Map) miPc.get("Medida_largo_tot_fieltro"),
                (Map) miPc.get("Medida_pico_entrada"),
                (Map) miPc.get("Medida_pico_salida"),
                (Map) miPc.get("Medida_pico_ret_fieltro"),
                (Map) miPc.get("Medida_pico_ret_tanque")
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_nafta_u_sellada_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_nafta_u_sellada_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_nafta_u_sellada_id"));
        miPlanControl.setDiametroCarcasaId((String) miPc.get("diametro_carcasa_nafta_u_sellada_id"));
        miPlanControl.setLargoFiltroId((String) miPc.get("largo_tot_fieltro_nafta_u_sellada_id"));
        miPlanControl.setPicoEntradaRoscaId((String) miPc.get("pico_entrada_nafta_u_sellada_id"));
        miPlanControl.setPicoSalidaRoscaId((String) miPc.get("pico_salida_nafta_u_sellada_id"));
        miPlanControl.setPicoRetornoFiltroId((String) miPc.get("pico_ret_fieltro_nafta_u_sellada_id"));
        miPlanControl.setPicoRetornoTanqueId((String) miPc.get("pico_ret_tanque_nafta_u_sellada_id"));
        miPlanControl.setValvulaCanisterId((String) miPc.get("valvula_canister_nafta_u_sellada_id"));
        miPlanControl.setSoporteFijacionId((String) miPc.get("soporte_fijacion_nafta_u_sellada_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);
                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_nafta_u_sellada_Nom_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_nafta_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_carcasa_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_nafta_u_sellada_Min_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_nafta_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_carcasa_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_nafta_u_sellada_Max_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametro_carcasa_nafta_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_carcasa_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidalargoFiltro().get("largo_tot_fieltro_nafta_u_sellada_Nom_id"), (String) miPlanControl.getMedidalargoFiltro().get("largo_tot_fieltro_nafta_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo_tot_fieltro_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidalargoFiltro().get("largo_tot_fieltro_nafta_u_sellada_Min_id"), (String) miPlanControl.getMedidalargoFiltro().get("largo_tot_fieltro_nafta_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo_tot_fieltro_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidalargoFiltro().get("largo_tot_fieltro_nafta_u_sellada_Max_id"), (String) miPlanControl.getMedidalargoFiltro().get("largo_tot_fieltro_nafta_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("largo_tot_fieltro_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("pico_entrada_nafta_u_sellada_Nom_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("pico_entrada_nafta_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_entrada_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("pico_entrada_nafta_u_sellada_Min_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("pico_entrada_nafta_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_entrada_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("pico_entrada_nafta_u_sellada_Max_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("pico_entrada_nafta_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_entrada_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("pico_salida_nafta_u_sellada_Nom_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("pico_salida_nafta_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_salida_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("pico_salida_nafta_u_sellada_Min_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("pico_salida_nafta_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_salida_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("pico_salida_nafta_u_sellada_Max_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("pico_salida_nafta_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_salida_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("pico_ret_fieltro_nafta_u_sellada_Nom_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("pico_ret_fieltro_nafta_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_ret_fieltro_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("pico_ret_fieltro_nafta_u_sellada_Min_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("pico_ret_fieltro_nafta_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_ret_fieltro_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("pico_ret_fieltro_nafta_u_sellada_Max_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("pico_ret_fieltro_nafta_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_ret_fieltro_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("pico_ret_tanque_nafta_u_sellada_Nom_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("pico_ret_tanque_nafta_u_sellada_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_ret_tanque_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("pico_ret_tanque_nafta_u_sellada_Min_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("pico_ret_tanque_nafta_u_sellada_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_ret_tanque_nafta_u_sellada_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("pico_ret_tanque_nafta_u_sellada_Max_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("pico_ret_tanque_nafta_u_sellada_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("pico_ret_tanque_nafta_u_sellada_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
                System.out.println("ID's "
                        + miPlanControl.getIdentificacionId()
                        + " " + miPlanControl.getVisualId()
                        + " " + miPlanControl.getLoteId()
                        + " " + miPlanControl.getDiametroCarcasaId()
                        + " " + miPlanControl.getLargoFiltroId()
                        + " " + miPlanControl.getPicoEntradaRoscaId()
                        + " " + miPlanControl.getPicoRetornoFiltroId()
                        + " " + miPlanControl.getPicoRetornoTanqueId()
                        + " " + miPlanControl.getPicoSalidaRoscaId()
                        + " " + miPlanControl.getSoporteFijacionId()
                        + " " + miPlanControl.getValvulaCanisterId()
                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_NAFTA_U_SELLADA, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_nafta_u_sellada_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getVisualId());

                psmt.setString(8, miPlanControl.getVisual());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("Aspecto_visual_nafta_u_sellada_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getLoteId());

                psmt.setString(13, miPlanControl.getLote());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("lote_nafta_u_sellada_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getDiametroCarcasaId());

                psmt.setString(18, miPlanControl.getDiametroCarcasa());

                psmt.setInt(19, medic_detale_maestro_id);

                psmt.setString(20, (String) miPc.get("diametro_carcasa_nafta_u_sellada_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getLargoFiltroId());

                psmt.setString(23, miPlanControl.getLargoFiltro());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("largo_tot_fieltro_nafta_u_sellada_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getPicoEntradaRoscaId());

                psmt.setString(28, miPlanControl.getPicoEntradaRosca());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("pico_entrada_nafta_u_sellada_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getPicoSalidaRoscaId());

                psmt.setString(33, miPlanControl.getPicoSalidaRosca());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("pico_salida_nafta_u_sellada_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getPicoRetornoFiltroId());

                psmt.setString(38, miPlanControl.getPicoRetornoFiltro());

                psmt.setInt(39, medic_detale_maestro_id);

                psmt.setString(40, (String) miPc.get("pico_ret_fieltro_nafta_u_sellada_obs"));

                psmt.setInt(41, rsMaestro.getInt(1));

                psmt.setString(42, miPlanControl.getPicoRetornoTanqueId());

                psmt.setString(43, miPlanControl.getPicoRetornoTanque());

                psmt.setInt(44, medic_detale_maestro_id);

                psmt.setString(45, (String) miPc.get("pico_ret_tanque_nafta_u_sellada_obs"));

                psmt.setInt(46, rsMaestro.getInt(1));

                psmt.setString(47, miPlanControl.getValvulaCanisterId());

                psmt.setString(48, miPlanControl.getValvulaCanister());

                psmt.setNull(49, java.sql.Types.INTEGER);

                psmt.setString(50, (String) miPc.get("valvula_canister_nafta_u_sellada_obs"));

                psmt.setInt(51, rsMaestro.getInt(1));

                psmt.setString(52, miPlanControl.getSoporteFijacionId());

                psmt.setString(53, miPlanControl.getSoporteFijacion());

                psmt.setNull(54, java.sql.Types.INTEGER);

                psmt.setString(55, (String) miPc.get("soporte_fijacion_nafta_u_sellada_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String INSERT_GENERICO_DETALLE_DIESEL_U_SELLADA_PLASTICO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void dieselUSelladaPlastico(Map miPc) throws Exception {

        PlanDieselUSelladaPlastico miPlanControl = new PlanDieselUSelladaPlastico(
                (String) miPc.get("identificacion_DieselSelladaPlastico"),
                (String) miPc.get("Aspecto_visual_DieselSelladaPlastico"),
                (String) miPc.get("lote_DieselSelladaPlastico"),
                (String) miPc.get("diametroCarcasa_DieselSelladaPlastico"),
                (String) miPc.get("altoTotal_DieselSelladaPlastico"),
                (String) miPc.get("picoEntrada_DieselSelladaPlastico"),
                (String) miPc.get("picoSalida_DieselSelladaPlastico"),
                (String) miPc.get("picoRetornoFiltro_DieselSelladaPlastico"),
                (String) miPc.get("picoRetornoTanque_DieselSelladaPlastico"),
                (String) miPc.get("sooprte_DieselSelladaPlastico"),
                (String) miPc.get("controlTemp_DieselSelladaPlastico"),
                (String) miPc.get("calefactor_DieselSelladaPlastico"),
                (String) miPc.get("elemFilrante_DieselSelladaPlastico"),
                (Map) miPc.get("Medida_diametroCarcasa"),
                (Map) miPc.get("Medida_altoTotal"),
                (Map) miPc.get("Medida_picoEntrada"),
                (Map) miPc.get("Medida_picoSalida"),
                (Map) miPc.get("Medida_picoRetornoFiltro"),
                (Map) miPc.get("Medida_picoRetornoTanque")
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_DieselSelladaPlastico_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_DieselSelladaPlastico_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_DieselSelladaPlastico_id"));
        miPlanControl.setDiametroCarcasaId((String) miPc.get("diametroCarcasa_DieselSelladaPlastico_id"));
        miPlanControl.setAltoTotalFiltroId((String) miPc.get("altoTotal_DieselSelladaPlastico_id"));
        miPlanControl.setPicoEntradaRoscaId((String) miPc.get("picoEntrada_DieselSelladaPlastico_id"));
        miPlanControl.setPicoSalidaRoscaId((String) miPc.get("picoSalida_DieselSelladaPlastico_id"));
        miPlanControl.setPicoRetornoFiltroId((String) miPc.get("picoRetornoFiltro_DieselSelladaPlastico_id"));
        miPlanControl.setPicoRetornoTanqueId((String) miPc.get("picoRetornoTanque_DieselSelladaPlastico_id"));
        miPlanControl.setSoporteId((String) miPc.get("sooprte_DieselSelladaPlastico_id"));
        miPlanControl.setControlTemperaturaId((String) miPc.get("controlTemp_DieselSelladaPlastico_id"));
        miPlanControl.setCalefactorId((String) miPc.get("calefactor_DieselSelladaPlastico_id"));
        miPlanControl.setElementoFiltranteId((String) miPc.get("elemFilrante_DieselSelladaPlastico_id"));

        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaPlastico_Nom_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaPlastico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroCarcasa_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaPlastico_Min_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaPlastico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroCarcasa_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaPlastico_Max_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaPlastico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroCarcasa_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoTotalFiltro().get("altoTotal_DieselSelladaPlastico_Nom_id"), (String) miPlanControl.getMedidaAltoTotalFiltro().get("altoTotal_DieselSelladaPlastico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoTotal_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoTotalFiltro().get("altoTotal_DieselSelladaPlastico_Min_id"), (String) miPlanControl.getMedidaAltoTotalFiltro().get("altoTotal_DieselSelladaPlastico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoTotal_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoTotalFiltro().get("altoTotal_DieselSelladaPlastico_Max_id"), (String) miPlanControl.getMedidaAltoTotalFiltro().get("altoTotal_DieselSelladaPlastico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoTotal_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaPlastico_Nom_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaPlastico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoEntrada_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaPlastico_Min_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaPlastico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoEntrada_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaPlastico_Max_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaPlastico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoEntrada_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaPlastico_Nom_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaPlastico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoSalida_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaPlastico_Min_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaPlastico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoSalida_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaPlastico_Max_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaPlastico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoSalida_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaPlastico_Nom_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaPlastico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoFiltro_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaPlastico_Min_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaPlastico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoFiltro_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaPlastico_Max_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaPlastico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoFiltro_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaPlastico_Nom_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaPlastico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoTanque_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaPlastico_Min_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaPlastico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoTanque_DieselSelladaPlastico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaPlastico_Max_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaPlastico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoTanque_DieselSelladaPlastico_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
//                System.out.println("ID's "
//                        + miPlanControl.getIdentificacionId()
//                        + " " + miPlanControl.getVisualId()
//                        + " " + miPlanControl.getLoteId()
//                        + " " + miPlanControl.getDiametroCarcasaId()
//                        + " " + miPlanControl.getLargoFiltroId()
//                        + " " + miPlanControl.getPicoEntradaRoscaId()
//                        + " " + miPlanControl.getPicoRetornoFiltroId()
//                        + " " + miPlanControl.getPicoRetornoTanqueId()
//                        + " " + miPlanControl.getPicoSalidaRoscaId()
//                        + " " + miPlanControl.getSoporteFijacionId()
//                        + " " + miPlanControl.getValvulaCanisterId()
//                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_DIESEL_U_SELLADA_PLASTICO, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_DieselSelladaPlastico_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getVisualId());

                psmt.setString(8, miPlanControl.getVisual());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("Aspecto_visual_DieselSelladaPlastico_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getLoteId());

                psmt.setString(13, miPlanControl.getLote());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("lote_DieselSelladaPlastico_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getDiametroCarcasaId());

                psmt.setString(18, miPlanControl.getDiametroCarcasa());

                psmt.setInt(19, medic_detale_maestro_id);

                psmt.setString(20, (String) miPc.get("diametroCarcasa_DieselSelladaPlastico_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getAltoTotalFiltroId());

                psmt.setString(23, miPlanControl.getAltoTotalFiltro());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("altoTotal_DieselSelladaPlastico_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getPicoEntradaRoscaId());

                psmt.setString(28, miPlanControl.getPicoEntradaRosca());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("picoEntrada_DieselSelladaPlastico_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getPicoSalidaRoscaId());

                psmt.setString(33, miPlanControl.getPicoSalidaRosca());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("picoSalida_DieselSelladaPlastico_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getPicoRetornoFiltroId());

                psmt.setString(38, miPlanControl.getPicoRetornoFiltro());

                psmt.setInt(39, medic_detale_maestro_id);

                psmt.setString(40, (String) miPc.get("picoRetornoFiltro_DieselSelladaPlastico_obs"));

                psmt.setInt(41, rsMaestro.getInt(1));

                psmt.setString(42, miPlanControl.getPicoRetornoTanqueId());

                psmt.setString(43, miPlanControl.getPicoRetornoTanque());

                psmt.setInt(44, medic_detale_maestro_id);

                psmt.setString(45, (String) miPc.get("picoRetornoTanque_DieselSelladaPlastico_obs"));

                psmt.setInt(46, rsMaestro.getInt(1));

                psmt.setString(47, miPlanControl.getSoporteId());

                psmt.setString(48, miPlanControl.getSoporte());

                psmt.setNull(49, java.sql.Types.INTEGER);

                psmt.setString(50, (String) miPc.get("sooprte_DieselSelladaPlastico_obs"));

                psmt.setInt(51, rsMaestro.getInt(1));

                psmt.setString(52, miPlanControl.getControlTemperaturaId());

                psmt.setString(53, miPlanControl.getControlTemperatura());

                psmt.setNull(54, java.sql.Types.INTEGER);

                psmt.setString(55, (String) miPc.get("controlTemp_DieselSelladaPlastico_obs"));

                psmt.setInt(56, rsMaestro.getInt(1));

                psmt.setString(57, miPlanControl.getCalefactorId());

                psmt.setString(58, miPlanControl.getCalefactor());

                psmt.setNull(59, java.sql.Types.INTEGER);

                psmt.setString(60, (String) miPc.get("calefactor_DieselSelladaPlastico_obs"));

                psmt.setInt(61, rsMaestro.getInt(1));

                psmt.setString(62, miPlanControl.getElementoFiltranteId());

                psmt.setString(63, miPlanControl.getElementoFiltrante());

                psmt.setNull(64, java.sql.Types.INTEGER);

                psmt.setString(65, (String) miPc.get("elemFilrante_DieselSelladaPlastico_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    
    
    private final static String INSERT_GENERICO_DETALLE_DIESEL_CARTUCHO = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";
    public static void dieselCartucho(Map miPc) throws Exception {

        // if plan control = precalentamiento
        PlanDieselCartucho miPlanControl = new PlanDieselCartucho(
                (String) miPc.get("identificacion_diesel_cartucho"),
                (String) miPc.get("Aspecto_visual_diesel_cartucho"),
                (String) miPc.get("lote_diesel_cartucho"),
                (String) miPc.get("diametro_ext_tapa_inf_diesel_cartucho"),
                (String) miPc.get("diametro_ext_tapa_sup_diesel_cartucho"),
                (String) miPc.get("diametro_int_tapa_inf_diesel_cartucho"),
                (String) miPc.get("diametro_int_tapa_sup_diesel_cartucho"),
                (String) miPc.get("sello1_diesel_cartucho"),
                (String) miPc.get("sello2_diesel_cartucho"),
                (String) miPc.get("sello3_diesel_cartucho"),
                (String) miPc.get("sello4_diesel_cartucho"),
                (String) miPc.get("elemento_filtrante_diesel_cartucho"),                
                (Map) miPc.get("Medida_diametro_ext_tapa_inf"),
                (Map) miPc.get("Medida_diametro_ext_tapa_sup"),
                (Map) miPc.get("Medida_diametro_int_tapa_inf"),
                (Map) miPc.get("Medida_diametro_int_tapa_sup"),
                (Map) miPc.get("Medida_sello1"),
                (Map) miPc.get("Medida_sello2"),
                (Map) miPc.get("Medida_sello3"),
                (Map) miPc.get("Medida_sello4")
        );
        miPlanControl.setIdentificacionId((String)miPc.get("identificacion_diesel_cartucho_id"));
        miPlanControl.setVisualId((String)miPc.get("Aspecto_visual_diesel_cartucho_id"));
        miPlanControl.setLoteId((String)miPc.get("lote_diesel_cartucho_id"));
        miPlanControl.setDiametroExtTapaInfId((String)miPc.get("diametro_ext_tapa_inf_diesel_cartucho_id"));
        miPlanControl.setDiametroExtTapaSupId((String)miPc.get("diametro_ext_tapa_sup_diesel_cartucho_id"));
        miPlanControl.setDiametroIntTapaInfId((String)miPc.get("diametro_int_tapa_inf_diesel_cartucho_id"));
        miPlanControl.setDiametroIntTapaSupId((String)miPc.get("diametro_int_tapa_sup_diesel_cartucho_id"));
        miPlanControl.setSello1Id((String)miPc.get("sello1_diesel_cartucho_id"));
        miPlanControl.setSello2Id((String)miPc.get("sello2_diesel_cartucho_id"));
        miPlanControl.setSello3Id((String)miPc.get("sello3_diesel_cartucho_id"));
        miPlanControl.setSello4Id((String)miPc.get("sello4_diesel_cartucho_id"));
        miPlanControl.setElementoFiltranteId((String)miPc.get("elemento_filtrante_diesel_cartucho_id"));
        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de bujia preca DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidaDiametroExtTapaInf().get("diametro_ext_tapa_inf_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroExtTapaInf().get("diametro_ext_tapa_inf_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_inf_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroExtTapaInf().get("diametro_ext_tapa_inf_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroExtTapaInf().get("diametro_ext_tapa_inf_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_inf_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroExtTapaInf().get("diametro_ext_tapa_inf_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroExtTapaInf().get("diametro_ext_tapa_inf_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_inf_diesel_cartucho_id"));


insertMedidas((String) miPlanControl.getMedidaDiametroExtTapaSup().get("diametro_ext_tapa_sup_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroExtTapaSup().get("diametro_ext_tapa_sup_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroExtTapaSup().get("diametro_ext_tapa_sup_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroExtTapaSup().get("diametro_ext_tapa_sup_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroExtTapaSup().get("diametro_ext_tapa_sup_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroExtTapaSup().get("diametro_ext_tapa_sup_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_ext_tapa_sup_diesel_cartucho_id"));


insertMedidas((String) miPlanControl.getMedidaDiametroIntTapaInf().get("diametro_int_tapa_inf_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroIntTapaInf().get("diametro_int_tapa_inf_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_inf_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroIntTapaInf().get("diametro_int_tapa_inf_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroIntTapaInf().get("diametro_int_tapa_inf_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_inf_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroIntTapaInf().get("diametro_int_tapa_inf_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroIntTapaInf().get("diametro_int_tapa_inf_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_inf_diesel_cartucho_id"));


insertMedidas((String) miPlanControl.getMedidaDiametroIntTapaSup().get("diametro_int_tapa_sup_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaDiametroIntTapaSup().get("diametro_int_tapa_sup_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_sup_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroIntTapaSup().get("diametro_int_tapa_sup_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaDiametroIntTapaSup().get("diametro_int_tapa_sup_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_sup_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaDiametroIntTapaSup().get("diametro_int_tapa_sup_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaDiametroIntTapaSup().get("diametro_int_tapa_sup_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametro_int_tapa_sup_diesel_cartucho_id"));


insertMedidas((String) miPlanControl.getMedidaSello1().get("sello1_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello1().get("sello1_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello1_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello1().get("sello1_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaSello1().get("sello1_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello1_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello1().get("sello1_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaSello1().get("sello1_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello1_diesel_cartucho_id"));


insertMedidas((String) miPlanControl.getMedidaSello2().get("sello2_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello2().get("sello2_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello2_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello2().get("sello2_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaSello2().get("sello2_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello2_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello2().get("sello2_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaSello2().get("sello2_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello2_diesel_cartucho_id"));


insertMedidas((String) miPlanControl.getMedidaSello3().get("sello3_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello3().get("sello3_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello3_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello3().get("sello3_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaSello3().get("sello3_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello3_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello3().get("sello3_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaSello3().get("sello3_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello3_diesel_cartucho_id"));


insertMedidas((String) miPlanControl.getMedidaSello4().get("sello4_diesel_cartucho_Nom_id"), (String) miPlanControl.getMedidaSello4().get("sello4_diesel_cartucho_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello4_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello4().get("sello4_diesel_cartucho_Min_id"), (String) miPlanControl.getMedidaSello4().get("sello4_diesel_cartucho_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello4_diesel_cartucho_id"));

insertMedidas((String) miPlanControl.getMedidaSello4().get("sello4_diesel_cartucho_Max_id"), (String) miPlanControl.getMedidaSello4().get("sello4_diesel_cartucho_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("sello4_diesel_cartucho_id"));


                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {

                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_DIESEL_CARTUCHO, Statement.RETURN_GENERATED_KEYS);

                
                
                psmt.setInt(1, rsMaestro.getInt(1));

psmt.setString(2, miPlanControl.getIdentificacionId());

psmt.setString(3, miPlanControl.getIdentificacion());

psmt.setNull(4, java.sql.Types.INTEGER);

psmt.setString(5, (String) miPc.get("identificacion_diesel_cartucho_obs"));



psmt.setInt(6, rsMaestro.getInt(1));

psmt.setString(7, miPlanControl.getLoteId());

psmt.setString(8, miPlanControl.getLote());

psmt.setNull(9, java.sql.Types.INTEGER);

psmt.setString(10, (String) miPc.get("lote_diesel_cartucho_obs"));



psmt.setInt(11, rsMaestro.getInt(1));

psmt.setString(12, miPlanControl.getVisualId());

psmt.setString(13, miPlanControl.getVisual());

psmt.setNull(14, java.sql.Types.INTEGER);

psmt.setString(15, (String) miPc.get("Aspecto_visual_diesel_cartucho_obs"));



psmt.setInt(16, rsMaestro.getInt(1));

psmt.setString(17, miPlanControl.getDiametroExtTapaInfId());

psmt.setString(18, miPlanControl.getDiametroExtTapaInf());

psmt.setInt(19, medic_detale_maestro_id);

psmt.setString(20, (String) miPc.get("diametro_ext_tapa_inf_diesel_cartucho_obs"));



psmt.setInt(21, rsMaestro.getInt(1));

psmt.setString(22, miPlanControl.getDiametroExtTapaSupId());

psmt.setString(23, miPlanControl.getDiametroExtTapaSup());

psmt.setInt(24, medic_detale_maestro_id);

psmt.setString(25, (String) miPc.get("diametro_ext_tapa_sup_diesel_cartucho_obs"));



psmt.setInt(26, rsMaestro.getInt(1));

psmt.setString(27, miPlanControl.getDiametroIntTapaInfId());

psmt.setString(28, miPlanControl.getDiametroIntTapaInf());

psmt.setInt(29, medic_detale_maestro_id);

psmt.setString(30, (String) miPc.get("diametro_int_tapa_inf_diesel_cartucho_obs"));



psmt.setInt(31, rsMaestro.getInt(1));

psmt.setString(32, miPlanControl.getDiametroIntTapaSupId());

psmt.setString(33, miPlanControl.getDiametroIntTapaSup());

psmt.setInt(34, medic_detale_maestro_id);

psmt.setString(35, (String) miPc.get("diametro_int_tapa_sup_diesel_cartucho_obs"));



psmt.setInt(36, rsMaestro.getInt(1));

psmt.setString(37, miPlanControl.getSello1Id());

psmt.setString(38, miPlanControl.getSello1());

psmt.setInt(39, medic_detale_maestro_id);

psmt.setString(40, (String) miPc.get("sello1_diesel_cartucho_obs"));



psmt.setInt(41, rsMaestro.getInt(1));

psmt.setString(42, miPlanControl.getSello2Id());

psmt.setString(43, miPlanControl.getSello2());

psmt.setInt(44, medic_detale_maestro_id);

psmt.setString(45, (String) miPc.get("sello2_diesel_cartucho_obs"));



psmt.setInt(46, rsMaestro.getInt(1));

psmt.setString(47, miPlanControl.getSello3Id());

psmt.setString(48, miPlanControl.getSello3());

psmt.setInt(49, medic_detale_maestro_id);

psmt.setString(50, (String) miPc.get("sello3_diesel_cartucho_obs"));



psmt.setInt(51, rsMaestro.getInt(1));

psmt.setString(52, miPlanControl.getSello4Id());

psmt.setString(53, miPlanControl.getSello4());

psmt.setInt(54, medic_detale_maestro_id);

psmt.setString(55, (String) miPc.get("sello4_diesel_cartucho_obs"));



psmt.setInt(56, rsMaestro.getInt(1));

psmt.setString(57, miPlanControl.getElementoFiltranteId());

psmt.setString(58, miPlanControl.getElementoFiltrante());

psmt.setNull(59, java.sql.Types.INTEGER);

psmt.setString(60, (String) miPc.get("elemento_filtrante_diesel_cartucho_obs"));



                

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    
    private final static String INSERT_GENERICO_DETALLE_AIRE_FRENOS = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";
    
    public static void aireFrenos(Map miPc) throws Exception{        
        // if plan control = precalentamiento
        PlanAireFreno miPlanControl = new PlanAireFreno(
                (String) miPc.get("identificacion_aire_frenos"),
                (String) miPc.get("Aspecto_visual_aire_frenos"),
                (String) miPc.get("lote_aire_frenos"),
                (String) miPc.get("rosca_aire_frenos")                
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_aire_frenos_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_aire_frenos_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_aire_frenos_id"));
        miPlanControl.setRoscaId((String) miPc.get("rosca_aire_frenos_id"));
        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de bujia preca DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

  
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {

                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_AIRE_FRENOS, Statement.RETURN_GENERATED_KEYS);

                
                psmt.setInt(1, rsMaestro.getInt(1));

psmt.setString(2, miPlanControl.getIdentificacionId());

psmt.setString(3, miPlanControl.getIdentificacion());

psmt.setNull(4, java.sql.Types.INTEGER);

psmt.setString(5, (String) miPc.get("identificacion_aire_frenos_obs"));



psmt.setInt(6, rsMaestro.getInt(1));

psmt.setString(7, miPlanControl.getLoteId());

psmt.setString(8, miPlanControl.getLote());

psmt.setNull(9, java.sql.Types.INTEGER);

psmt.setString(10, (String) miPc.get("lote_aire_frenos_obs"));



psmt.setInt(11, rsMaestro.getInt(1));

psmt.setString(12, miPlanControl.getVisualId());

psmt.setString(13, miPlanControl.getVisual());

psmt.setNull(14, java.sql.Types.INTEGER);

psmt.setString(15, (String) miPc.get("Aspecto_visual_aire_frenos_obs"));



psmt.setInt(16, rsMaestro.getInt(1));

psmt.setString(17, miPlanControl.getRoscaId());

psmt.setString(18, miPlanControl.getRosca());

psmt.setNull(19, java.sql.Types.INTEGER);

psmt.setString(20, (String) miPc.get("rosca_aire_frenos_obs"));


                
                

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //private final static String PREGARGA_ENCABEZADO_SELECT_ALL = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id ORDER BY precarga_imaestro_id DESC LIMIT 2";
    private final static String PREGARGA_ENCABEZADO_SELECT_ALL = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id WHERE precarga_maestro.precarga_imaestro_id = ?";
    private final static String PREGARGA_DETALLE_SELECT_ALL = "SELECT *, productos.family_id, productos.subfamily_id, family_Product.fami_nombre, sub_family_Product.subfami_nombre FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto INNER JOIN family_Product ON productos.family_id = family_Product.fami_id INNER JOIN sub_family_Product ON productos.subfamily_id = sub_family_Product.subfami_id WHERE precarga_detalle_idmaestro = ?";
    private final static String PACKAGING_SELECT = "SELECT *, valores1.valores_nombre AS pkging_col_cod_barra_Nombre, valores2.valores_nombre AS pkging_equiv_Nombre, valores3.valores_nombre AS pkging_ind_cod_barra_nombre, valores4.valores_nombre AS pkging_ind_ident_nombre, valores5.valores_nombre AS pkging_aplica_nombre, valores6.valores_nombre AS pkging_chas_nombre FROM ingresos_pkging INNER JOIN ingresos_valores AS valores1 ON pkging_col_cod_barra = valores1.valores_id INNER JOIN ingresos_valores AS valores2 ON pkging_equiv = valores2.valores_id INNER JOIN ingresos_valores AS valores3 ON pkging_ind_cod_barra = valores3.valores_id INNER JOIN ingresos_valores AS valores4 ON pkging_ind_ident = valores4.valores_id INNER JOIN ingresos_valores AS valores5 ON pkging_aplica = valores5.valores_id INNER JOIN ingresos_valores AS valores6 ON pkging_chas = valores6.valores_id WHERE pkging_precarga_detalle_id = ?";
    private final static String PLAN_MAESTRO_SELECT = "SELECT * FROM ingresos_plan_maestro WHERE plan_maestro_detalle_id = ?";
    private final static String PLAN_DETALLE_SELECT = "SELECT *, ingresos_plan_maestro.plan_maestro_detalle_id FROM ingresos_plan_detalle INNER JOIN ingresos_plan_maestro ON ingresos_plan_maestro.plan_maestro_id = ingresos_plan_detalle.plan_detalle_maestroid INNER JOIN ingresos_valores ON ingresos_plan_detalle.plan_detalle_verificado = ingresos_valores.valores_id INNER JOIN ingresos_plan_item ON ingresos_plan_detalle.plan_detalle_tipo_plan = ingresos_plan_item.plan_item_id WHERE plan_maestro_id = ?";

    public ArrayList<Precargas> obtenerUltimos(String maestroId) throws ClassNotFoundException, IOException, SQLException, Exception {
        Precargas miPrecarga = null;

        ArrayList<Precargas> listaPrecarga = new ArrayList();
        ArrayList<PrecargasMaestro> listaEncabezado = new ArrayList();
        ArrayList<PlanGeneral> listaPlanGeneral = new ArrayList();
        PlanGeneral miPlanGeneral = null;

        Connection cPrecargaUltimos = null;
        PreparedStatement ptsmtPrecargaUltimo = null;
        ResultSet rsPrecrgaUltimos = null;

        Connection cPrecargasDetalles = null;
        PreparedStatement ptsmPrecargasDetalles = null;
        ResultSet rsPrecargasDetalles = null;

        Connection cPacakcging = null;
        PreparedStatement ptsmPackaging = null;
        ResultSet rsPackaging = null;

        Connection cPlanMaestro = null;
        PreparedStatement ptsmPlanMaestro = null;
        ResultSet rsPlanMaestro = null;

        Connection cPlanDetalle = null;
        PreparedStatement ptsmPlanDetalle = null;
        ResultSet rsPlanDetalle = null;
        try {
            ArrayList<PrecargasDetalle> misDetalles = null;

            cPrecargaUltimos = DB.getInstance().getConnection();
            ptsmtPrecargaUltimo = cPrecargaUltimos.prepareStatement(PREGARGA_ENCABEZADO_SELECT_ALL);
            ptsmtPrecargaUltimo.setString(1, maestroId);
            rsPrecrgaUltimos = ptsmtPrecargaUltimo.executeQuery();
            while (rsPrecrgaUltimos.next()) {
                miPrecarga = new Precargas();
                PrecargasMaestro miMaestro = new PrecargasMaestro(
                        rsPrecrgaUltimos.getString("prov_nombre"),
                        rsPrecrgaUltimos.getString("precarga_maestro_fechamanifesto"),
                        rsPrecrgaUltimos.getString("precarga_maestro_codigomanifiesto"),
                        rsPrecrgaUltimos.getString("precarga_maestro_fecharecepcion"),
                        rsPrecrgaUltimos.getString("precarga_maestro_observaciones"),
                        rsPrecrgaUltimos.getString("precarga_maestro_arribo")
                );
                miMaestro.setId(rsPrecrgaUltimos.getString("precarga_imaestro_id"));
                listaEncabezado.add(miMaestro);
                try {

                    cPrecargasDetalles = DB.getInstance().getConnection();
                    ptsmPrecargasDetalles = cPrecargasDetalles.prepareStatement(PREGARGA_DETALLE_SELECT_ALL);
                    ptsmPrecargasDetalles.setString(1, rsPrecrgaUltimos.getString("precarga_imaestro_id"));
                    rsPrecargasDetalles = ptsmPrecargasDetalles.executeQuery();
                    misDetalles = new ArrayList();
                    while (rsPrecargasDetalles.next()) {

                        PrecargasDetalle miDetalle = new PrecargasDetalle(
                                rsPrecargasDetalles.getString("codigo_producto"),
                                rsPrecargasDetalles.getString("precarga_detalle_cant"),
                                rsPrecargasDetalles.getString("precarga_detalle_muestra"),
                                rsPrecargasDetalles.getString("precarga_detalle_obs"));
                        miDetalle.setEstadoPcking(rsPrecargasDetalles.getString("precarga_detalle_pkging_class"));
                        miDetalle.setId(rsPrecargasDetalles.getString("precarga_detalle_id"));
                        miDetalle.setFamiliaId(rsPrecargasDetalles.getString("family_id"));
                        miDetalle.setSubFamiliaId(rsPrecargasDetalles.getString("subfamily_id"));
                        miDetalle.setFamiliaNombre(rsPrecargasDetalles.getString("fami_nombre"));
                        miDetalle.setSubfamiliaNombre(rsPrecargasDetalles.getString("subfami_nombre"));
                        miDetalle.setEstadoDiseno(rsPrecargasDetalles.getString("precarga_detalle_diseno_class"));

                        System.out.println("Subfamilia: " + miDetalle.getSubFamiliaId());
                        System.out.println("familia: " + miDetalle.getFamiliaId());
                        if (miDetalle.getSubFamiliaId().equals("29")) {
                            //cada detalle tendra un plan de producto y de packaging
                            miDetalle.setPlanTemporario("Encendido");
                        } else if (miDetalle.getSubFamiliaId().equals("28")) {
                            miDetalle.setPlanTemporario("Precalentamiento");
                        } else if (miDetalle.getFamiliaId().equals("9")) {
                            miDetalle.setPlanTemporario("Lamparas");
                        } else {
                            miDetalle.setPlanTemporario("Plan no aplicado");
                        }
                        try {
                            cPacakcging = DB.getInstance().getConnection();
                            ptsmPackaging = cPacakcging.prepareStatement(PACKAGING_SELECT);
                            System.out.println("Id precarga detalle para packaging : " + rsPrecargasDetalles.getString("precarga_detalle_id"));
                            ptsmPackaging.setString(1, rsPrecargasDetalles.getString("precarga_detalle_id"));
                            rsPackaging = ptsmPackaging.executeQuery();
                            Packaging miPackaging = new Packaging();
                            while (rsPackaging.next()) {
                                miPackaging.setChasNombre(rsPackaging.getString("pkging_chas_nombre"));
                                miPackaging.setColBarraNombre(rsPackaging.getString("pkging_col_cod_barra_nombre"));
                                miPackaging.setIndAplicacionesNombre(rsPackaging.getString("pkging_aplica_nombre"));
                                miPackaging.setIndBarraNombre(rsPackaging.getString("pkging_ind_cod_barra_nombre"));
                                miPackaging.setIndEquivNombre(rsPackaging.getString("pkging_equiv_nombre"));
                                miPackaging.setIndIdentNombre(rsPackaging.getString("pkging_ind_ident_nombre"));
                            }
                            System.out.println("Producto: " + rsPrecargasDetalles.getString("precarga_detalle_idproducto"));
                            miDetalle.setPackaging(miPackaging);
                        } catch (Exception ex) {
                            System.out.println("Exeption: " + ex.getMessage());
                        } finally {

                            //////////
                            if (rsPackaging != null) {
                                try {
                                    rsPackaging.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (ptsmPackaging != null) {
                                try {
                                    ptsmPackaging.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (cPacakcging != null) {
                                try {
                                    cPacakcging.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        try {
                            cPlanMaestro = DB.getInstance().getConnection();
                            ptsmPlanMaestro = cPlanMaestro.prepareStatement(PLAN_MAESTRO_SELECT);
                            ptsmPlanMaestro.setString(1, miDetalle.getId());
                            rsPlanMaestro = ptsmPlanMaestro.executeQuery();

                            while (rsPlanMaestro.next()) {
                                try {

                                    System.out.println("Id de plan maestro : " + rsPlanMaestro.getString("plan_maestro_id"));
                                    cPlanDetalle = DB.getInstance().getConnection();
                                    ptsmPlanDetalle = cPlanDetalle.prepareStatement(PLAN_DETALLE_SELECT);
                                    ptsmPlanDetalle.setString(1, rsPlanMaestro.getString("plan_maestro_id"));
                                    rsPlanDetalle = ptsmPlanDetalle.executeQuery();

                                    while (rsPlanDetalle.next()) {
                                        miPlanGeneral = new PlanGeneral();
                                        System.out.println("Plan detalle: " + rsPlanDetalle.getString("plan_item_nombre") + "Tipo de plan: " + rsPlanDetalle.getString("valores_nombre"));
                                        //System.out.println("Tipo de plan: " + rsPlanDetalle.getString("valores_nombre"));

                                        miPlanGeneral.setDetalleId(rsPlanDetalle.getString("plan_detalle_id"));
                                        miPlanGeneral.setMaestroId(rsPlanDetalle.getString("plan_detalle_maestroid"));
                                        miPlanGeneral.setTipoPlan(rsPlanDetalle.getString("plan_item_nombre"));
                                        miPlanGeneral.setValor(rsPlanDetalle.getString("valores_nombre"));
                                        miPlanGeneral.setValorId(rsPlanDetalle.getString("valores_id"));
                                        //miDetalle.setPlanGeneral(miPlanGeneral);
                                        //listaPlanGeneral.add(miPlanGeneral);  
                                        //miDetalle.setListaPlanGeneral(listaPlanGeneral);
                                        miDetalle.setMiPlanGeneral(miPlanGeneral);
                                    }

                                } catch (Exception ex) {
                                    System.out.println("Exeption: " + ex.getMessage());
                                } finally {
                                    if (rsPlanDetalle != null) {
                                        try {
                                            rsPlanDetalle.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (ptsmPlanDetalle != null) {
                                        try {
                                            ptsmPlanDetalle.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (cPlanDetalle != null) {
                                        try {
                                            cPlanDetalle.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Exeption: " + ex.getMessage());
                        } finally {

                            if (rsPlanMaestro != null) {
                                try {
                                    rsPlanMaestro.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (ptsmPlanMaestro != null) {
                                try {
                                    ptsmPlanMaestro.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (cPlanMaestro != null) {
                                try {
                                    cPlanMaestro.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (miDetalle.getFamiliaId().equals("9")) {

                            miDetalle.setPlanDiseno("Lamparas");
                        } else if (miDetalle.getSubFamiliaId().equals("28")) {
                            miDetalle.setPlanDiseno("Bujia preca");
                        } else if (miDetalle.getSubFamiliaId().equals("29")) {
                            miDetalle.setPlanDiseno("Bujia encendido");
                        } else {
                            miDetalle.setPlanDiseno("SIN PLAN DEFINIDO");
                        }
                        misDetalles.add(miDetalle);
                        miPrecarga.setDetalles(misDetalles);
                        miPrecarga.setMaestro(miMaestro);

                    }

                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                } finally {
                    if (rsPrecargasDetalles != null) {
                        try {
                            rsPrecargasDetalles.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (ptsmPrecargasDetalles != null) {
                        try {
                            ptsmPrecargasDetalles.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cPrecargasDetalles != null) {
                        try {
                            cPrecargasDetalles.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("Mipresacra: " + miPrecarga);
                listaPrecarga.add(miPrecarga);
            }

            System.out.println("Mis detalles: " + misDetalles);
            System.out.println("Lista precargas: " + listaPrecarga);
        } finally {
            if (rsPrecrgaUltimos != null) {
                try {
                    rsPrecrgaUltimos.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ptsmtPrecargaUltimo != null) {
                try {
                    ptsmtPrecargaUltimo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cPrecargaUltimos != null) {
                try {
                    cPrecargaUltimos.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return listaPrecarga;
    }

    private final static String INSERT_GENERICO_DETALLE_DIESEL_U_SELLADA_METALICA = "INSERT INTO ingresos_plan_detalle (plan_detalle_maestroid, plan_detalle_tipo_plan, plan_detalle_verificado,plan_detalle_medicion, plan_detalle_coment) VALUES (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?), (?,?,?,?,?)";

    public static void dieselUSelladaMetalica(Map miPc) throws Exception {

        PlanDieselUSelladaMetalica miPlanControl = new PlanDieselUSelladaMetalica(
                (String) miPc.get("identificacion_DieselSelladaMetalico"),
                (String) miPc.get("Aspecto_visual_DieselSelladaMetalico"),
                (String) miPc.get("lote_DieselSelladaMetalico"),
                (String) miPc.get("diametroCarcasa_DieselSelladaMetalico"),
                (String) miPc.get("altoTotal_DieselSelladaMetalico"),
                (String) miPc.get("rosca_brida_DieselSelladaMetalico"),
                (String) miPc.get("diametroInterior_DieselSelladaMetalico"),
                (String) miPc.get("altoJunta_DieselSelladaMetalico"),
                (String) miPc.get("dureza_DieselSelladaMetalico"),
                (String) miPc.get("roscaPurgador_DieselSelladaMetalico"),
                (String) miPc.get("roscaVaso_DieselSelladaMetalico"),
                (String) miPc.get("vasoDecantador_DieselSelladaMetalico"),
                (String) miPc.get("picoEntrada_DieselSelladaMetalico"),
                (String) miPc.get("picoSalida_DieselSelladaMetalico"),
                (String) miPc.get("picoRetornoFiltro_DieselSelladaMetalico"),
                (String) miPc.get("picoRetornoTanque_DieselSelladaMetalico"),
                (String) miPc.get("temperatura_DieselSelladaMetalico"),
                (String) miPc.get("calefactor_DieselSelladaMetalico"),
                (String) miPc.get("filtrante_DieselSelladaMetalico"),
                (Map) miPc.get("Medida_diametroCarcasa"),
                (Map) miPc.get("Medida_altoTotal"),
                (Map) miPc.get("Medida_rosca_brida"),
                (Map) miPc.get("Medida_diametroInterior"),
                (Map) miPc.get("Medida_altoJunta"),
                (Map) miPc.get("Medida_dureza"),
                (Map) miPc.get("Medida_picoEntrada"),
                (Map) miPc.get("Medida_picoSalida"),
                (Map) miPc.get("Medida_picoRetornoFiltro"),
                (Map) miPc.get("Medida_picoRetornoTanque")
        );
        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_DieselSelladaMetalico_id"));
        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_DieselSelladaMetalico_id"));
        miPlanControl.setLoteId((String) miPc.get("lote_DieselSelladaMetalico_id"));
        miPlanControl.setDiametroCarcasaId((String) miPc.get("diametroCarcasa_DieselSelladaMetalico_id"));
        miPlanControl.setAltoTotalId((String) miPc.get("altoTotal_DieselSelladaMetalico_id"));
        miPlanControl.setRoscaBridaId((String) miPc.get("rosca_brida_DieselSelladaMetalico_id"));
        miPlanControl.setDiametroIntJuntaId((String) miPc.get("diametroInterior_DieselSelladaMetalico_id"));
        miPlanControl.setAltoJuntaId((String) miPc.get("altoJunta_DieselSelladaMetalico_id"));
        miPlanControl.setDurezaId((String) miPc.get("dureza_DieselSelladaMetalico_id"));
        miPlanControl.setRoscaPurgadorId((String) miPc.get("roscaPurgador_DieselSelladaMetalico_id"));
        miPlanControl.setRoscaVasoId((String) miPc.get("roscaVaso_DieselSelladaMetalico_id"));
        miPlanControl.setVasoDecantadorId((String) miPc.get("vasoDecantador_DieselSelladaMetalico_id"));
        miPlanControl.setPicoEntradaRoscaId((String) miPc.get("picoEntrada_DieselSelladaMetalico_id"));
        miPlanControl.setPicoSalidaRoscaId((String) miPc.get("picoSalida_DieselSelladaMetalico_id"));
        miPlanControl.setPicoRetornoFiltroId((String) miPc.get("picoRetornoFiltro_DieselSelladaMetalico_id"));
        miPlanControl.setPicoRetornoTanqueId((String) miPc.get("picoRetornoTanque_DieselSelladaMetalico_id"));
        miPlanControl.setTemperaturaId((String) miPc.get("temperatura_DieselSelladaMetalico_id"));
        miPlanControl.setCalefactorId((String) miPc.get("calefactor_DieselSelladaMetalico_id"));
        miPlanControl.setElementoFiltranteId((String) miPc.get("filtrante_DieselSelladaMetalico_id"));

//        miPlanControl.setIdentificacionId((String) miPc.get("identificacion_DieselSelladaPlastico_id"));
//        miPlanControl.setVisualId((String) miPc.get("Aspecto_visual_DieselSelladaPlastico_id"));
//        miPlanControl.setLoteId((String) miPc.get("lote_DieselSelladaPlastico_id"));
//        miPlanControl.setDiametroCarcasaId((String) miPc.get("diametroCarcasa_DieselSelladaPlastico_id"));
//        miPlanControl.setAltoTotalFiltroId((String) miPc.get("altoTotal_DieselSelladaPlastico_id"));
//        miPlanControl.setPicoEntradaRoscaId((String) miPc.get("picoEntrada_DieselSelladaPlastico_id"));
//        miPlanControl.setPicoSalidaRoscaId((String) miPc.get("picoSalida_DieselSelladaPlastico_id"));
//        miPlanControl.setPicoRetornoFiltroId((String) miPc.get("picoRetornoFiltro_DieselSelladaPlastico_id"));
//        miPlanControl.setPicoRetornoTanqueId((String) miPc.get("picoRetornoTanque_DieselSelladaPlastico_id"));
//        miPlanControl.setSoporteId((String) miPc.get("sooprte_DieselSelladaPlastico_id"));
//        miPlanControl.setControlTemperaturaId((String) miPc.get("controlTemp_DieselSelladaPlastico_id"));
//        miPlanControl.setCalefactorId((String) miPc.get("calefactor_DieselSelladaPlastico_id"));
//        miPlanControl.setElementoFiltranteId((String) miPc.get("elemFilrante_DieselSelladaPlastico_id"));
        miPlanControl.setIdDetalle((String) miPc.get("idDetalle"));
        System.out.println("Id detalle: " + miPlanControl.getIdDetalle());
        System.out.println("Plan control de Aire habitaculo DAO: " + miPlanControl);

        Gson convertir = new Gson();
        System.out.println("mi Plan: " + convertir.toJson(miPlanControl));

        Connection conMaestro = null;
        PreparedStatement psmtMaestro = null;
        ResultSet rsMaestro = null;

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        Connection conInsertMedidasMaestro = null;
        PreparedStatement psmtInsertMedidasMaestro = null;
        ResultSet rsInsertMedidasMaestro = null;

        int medic_detale_maestro_id = 0;
        try {

            try {
                conInsertMedidasMaestro = DB.getInstance().getConnection();
                psmtInsertMedidasMaestro = conInsertMedidasMaestro.prepareStatement(INSERT_MEDICIONES_MAESTRO, Statement.RETURN_GENERATED_KEYS);
                psmtInsertMedidasMaestro.executeUpdate();
                rsInsertMedidasMaestro = psmtInsertMedidasMaestro.getGeneratedKeys();
                if (rsInsertMedidasMaestro.next()) {

                    medic_detale_maestro_id = rsInsertMedidasMaestro.getInt(1);

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroCarcasa_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroCarcasa_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaDiametroCarcasa().get("diametroCarcasa_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroCarcasa_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoTotal().get("altoTotal_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaAltoTotal().get("altoTotal_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoTotal_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoTotal().get("altoTotal_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaAltoTotal().get("altoTotal_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoTotal_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoTotal().get("altoTotal_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaAltoTotal().get("altoTotal_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoTotal_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaRoscaBrida().get("rosca_brida_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaRoscaBrida().get("rosca_brida_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("rosca_brida_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaRoscaBrida().get("rosca_brida_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaRoscaBrida().get("rosca_brida_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("rosca_brida_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaRoscaBrida().get("rosca_brida_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaRoscaBrida().get("rosca_brida_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("rosca_brida_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getmedidaDiametroIntJunta().get("diametroInterior_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getmedidaDiametroIntJunta().get("diametroInterior_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroInterior_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getmedidaDiametroIntJunta().get("diametroInterior_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getmedidaDiametroIntJunta().get("diametroInterior_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroInterior_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getmedidaDiametroIntJunta().get("diametroInterior_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getmedidaDiametroIntJunta().get("diametroInterior_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("diametroInterior_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoJunta().get("altoJunta_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaAltoJunta().get("altoJunta_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoJunta_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoJunta().get("altoJunta_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaAltoJunta().get("altoJunta_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoJunta_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaAltoJunta().get("altoJunta_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaAltoJunta().get("altoJunta_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("altoJunta_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaDureza().get("dureza_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaDureza().get("dureza_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dureza_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaDureza().get("dureza_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaDureza().get("dureza_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dureza_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaDureza().get("dureza_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaDureza().get("dureza_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("dureza_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoEntrada_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoEntrada_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaPicoEntradaRosca().get("picoEntrada_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoEntrada_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoSalida_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoSalida_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaPicoSalidaRosca().get("picoSalida_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoSalida_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoFiltro_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoFiltro_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaPicoRetornoFiltro().get("picoRetornoFiltro_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoFiltro_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaMetalico_Nom_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaMetalico_Nom"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoTanque_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaMetalico_Min_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaMetalico_Min"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoTanque_DieselSelladaMetalico_id"));

                    insertMedidas((String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaMetalico_Max_id"), (String) miPlanControl.getMedidaPicoRetornoTanque().get("picoRetornoTanque_DieselSelladaMetalico_Max"), rsInsertMedidasMaestro.getInt(1), (String) miPc.get("picoRetornoTanque_DieselSelladaMetalico_id"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception("No se pudo insertar Maestro de mediciones");
            } finally {
                if (conInsertMedidasMaestro != null) {
                    try {
                        conInsertMedidasMaestro.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conMaestro = DB.getInstance().getConnection();
            psmtMaestro = conMaestro.prepareStatement(INSERT_GENERICO_MAESTRO, Statement.RETURN_GENERATED_KEYS);
            psmtMaestro.setString(1, (String) miPc.get("idDetalle"));
            psmtMaestro.executeUpdate();
            rsMaestro = psmtMaestro.getGeneratedKeys();
            if (rsMaestro.next()) {
//                System.out.println("ID's "
//                        + miPlanControl.getIdentificacionId()
//                        + " " + miPlanControl.getVisualId()
//                        + " " + miPlanControl.getLoteId()
//                        + " " + miPlanControl.getDiametroCarcasaId()
//                        + " " + miPlanControl.getLargoFiltroId()
//                        + " " + miPlanControl.getPicoEntradaRoscaId()
//                        + " " + miPlanControl.getPicoRetornoFiltroId()
//                        + " " + miPlanControl.getPicoRetornoTanqueId()
//                        + " " + miPlanControl.getPicoSalidaRoscaId()
//                        + " " + miPlanControl.getSoporteFijacionId()
//                        + " " + miPlanControl.getValvulaCanisterId()
//                );
                con = DB.getInstance().getConnection();
                psmt = con.prepareStatement(INSERT_GENERICO_DETALLE_DIESEL_U_SELLADA_METALICA, Statement.RETURN_GENERATED_KEYS);

                psmt.setInt(1, rsMaestro.getInt(1));

                psmt.setString(2, miPlanControl.getIdentificacionId());

                psmt.setString(3, miPlanControl.getIdentificacion());

                psmt.setNull(4, java.sql.Types.INTEGER);

                psmt.setString(5, (String) miPc.get("identificacion_DieselSelladaMetalico_obs"));

                psmt.setInt(6, rsMaestro.getInt(1));

                psmt.setString(7, miPlanControl.getVisualId());

                psmt.setString(8, miPlanControl.getVisual());

                psmt.setNull(9, java.sql.Types.INTEGER);

                psmt.setString(10, (String) miPc.get("Aspecto_visual_DieselSelladaMetalico_obs"));

                psmt.setInt(11, rsMaestro.getInt(1));

                psmt.setString(12, miPlanControl.getLoteId());

                psmt.setString(13, miPlanControl.getLote());

                psmt.setNull(14, java.sql.Types.INTEGER);

                psmt.setString(15, (String) miPc.get("lote_DieselSelladaMetalico_obs"));

                psmt.setInt(16, rsMaestro.getInt(1));

                psmt.setString(17, miPlanControl.getDiametroCarcasaId());

                psmt.setString(18, miPlanControl.getDiametroCarcasa());

                psmt.setInt(19, medic_detale_maestro_id);

                psmt.setString(20, (String) miPc.get("diametroCarcasa_DieselSelladaMetalico_obs"));

                psmt.setInt(21, rsMaestro.getInt(1));

                psmt.setString(22, miPlanControl.getAltoTotalId());

                psmt.setString(23, miPlanControl.getAltoTotal());

                psmt.setInt(24, medic_detale_maestro_id);

                psmt.setString(25, (String) miPc.get("altoTotal_DieselSelladaMetalico_obs"));

                psmt.setInt(26, rsMaestro.getInt(1));

                psmt.setString(27, miPlanControl.getRoscaBridaId());

                psmt.setString(28, miPlanControl.getRoscaBrida());

                psmt.setInt(29, medic_detale_maestro_id);

                psmt.setString(30, (String) miPc.get("rosca_brida_DieselSelladaMetalico_obs"));

                psmt.setInt(31, rsMaestro.getInt(1));

                psmt.setString(32, miPlanControl.getDiametroIntJuntaId());

                psmt.setString(33, miPlanControl.getDiametroIntJunta());

                psmt.setInt(34, medic_detale_maestro_id);

                psmt.setString(35, (String) miPc.get("diametroInterior_DieselSelladaMetalico_obs"));

                psmt.setInt(36, rsMaestro.getInt(1));

                psmt.setString(37, miPlanControl.getAltoJuntaId());

                psmt.setString(38, miPlanControl.getAltoJunta());

                psmt.setInt(39, medic_detale_maestro_id);

                psmt.setString(40, (String) miPc.get("altoJunta_DieselSelladaMetalico_obs"));

                psmt.setInt(41, rsMaestro.getInt(1));

                psmt.setString(42, miPlanControl.getDurezaId());

                psmt.setString(43, miPlanControl.getDureza());

                psmt.setInt(44, medic_detale_maestro_id);

                psmt.setString(45, (String) miPc.get("dureza_DieselSelladaMetalico_obs"));

                psmt.setInt(46, rsMaestro.getInt(1));

                psmt.setString(47, miPlanControl.getRoscaPurgadorId());

                psmt.setString(48, miPlanControl.getRoscaPurgador());

                psmt.setNull(49, java.sql.Types.INTEGER);

                psmt.setString(50, (String) miPc.get("roscaPurgador_DieselSelladaMetalico_obs"));

                psmt.setInt(51, rsMaestro.getInt(1));

                psmt.setString(52, miPlanControl.getRoscaVasoId());

                psmt.setString(53, miPlanControl.getRoscaVaso());

                psmt.setNull(54, java.sql.Types.INTEGER);

                psmt.setString(55, (String) miPc.get("roscaVaso_DieselSelladaMetalico_obs"));

                psmt.setInt(56, rsMaestro.getInt(1));

                psmt.setString(57, miPlanControl.getVasoDecantadorId());

                psmt.setString(58, miPlanControl.getVasoDecantador());

                psmt.setNull(59, java.sql.Types.INTEGER);

                psmt.setString(60, (String) miPc.get("vasoDecantador_DieselSelladaMetalico_obs"));

                psmt.setInt(61, rsMaestro.getInt(1));

                psmt.setString(62, miPlanControl.getPicoEntradaRoscaId());

                psmt.setString(63, miPlanControl.getPicoEntradaRosca());

                psmt.setInt(64, medic_detale_maestro_id);

                psmt.setString(65, (String) miPc.get("picoEntrada_DieselSelladaMetalico_obs"));

                psmt.setInt(66, rsMaestro.getInt(1));

                psmt.setString(67, miPlanControl.getPicoSalidaRoscaId());

                psmt.setString(68, miPlanControl.getPicoSalidaRosca());

                psmt.setInt(69, medic_detale_maestro_id);

                psmt.setString(70, (String) miPc.get("picoSalida_DieselSelladaMetalico_obs"));

                psmt.setInt(71, rsMaestro.getInt(1));

                psmt.setString(72, miPlanControl.getPicoRetornoFiltroId());

                psmt.setString(73, miPlanControl.getPicoRetornoFiltro());

                psmt.setInt(74, medic_detale_maestro_id);

                psmt.setString(75, (String) miPc.get("picoRetornoFiltro_DieselSelladaMetalico_obs"));

                psmt.setInt(76, rsMaestro.getInt(1));

                psmt.setString(77, miPlanControl.getPicoRetornoTanqueId());

                psmt.setString(78, miPlanControl.getPicoRetornoTanque());

                psmt.setInt(79, medic_detale_maestro_id);

                psmt.setString(80, (String) miPc.get("picoRetornoTanque_DieselSelladaMetalico_obs"));

                psmt.setInt(81, rsMaestro.getInt(1));

                psmt.setString(82, miPlanControl.getTemperaturaId());

                psmt.setString(83, miPlanControl.getTemperatura());

                psmt.setNull(84, java.sql.Types.INTEGER);

                psmt.setString(85, (String) miPc.get("temperatura_DieselSelladaMetalico_obs"));

                psmt.setInt(86, rsMaestro.getInt(1));

                psmt.setString(87, miPlanControl.getCalefactorId());

                psmt.setString(88, miPlanControl.getCalefactor());

                psmt.setNull(89, java.sql.Types.INTEGER);

                psmt.setString(90, (String) miPc.get("calefactor_DieselSelladaMetalico_obs"));

                psmt.setInt(91, rsMaestro.getInt(1));

                psmt.setString(92, miPlanControl.getElementoFiltranteId());

                psmt.setString(93, miPlanControl.getElementoFiltrante());

                psmt.setNull(94, java.sql.Types.INTEGER);

                psmt.setString(95, (String) miPc.get("filtrante_DieselSelladaMetalico_obs"));

                psmt.executeUpdate();
                rs = psmt.getGeneratedKeys();
            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPlanControl.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_PRECARGA_DETALLE_GENERICO);
                psmtEstado.setString(1, miPlanControl.getIdDetalle());
                psmtEstado.execute();
                updateStatusPrecargaDetalle(miPlanControl.getIdDetalle(), (String) miPc.get("idMaestro"));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex.getMessage());
            } finally {
                if (psmtEstado != null) {
                    try {
                        psmtEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conUpdateEstado != null) {
                    try {
                        conUpdateEstado.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            System.out.println("DAO packaging: " + miPlanControl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exeption: " + ex.getMessage());
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

            if (psmtMaestro != null) {
                try {
                    psmtMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conMaestro != null) {
                try {
                    conMaestro.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String PREGARGA_ENCABEZADO_SELECT_ALL_INDIVIDUAL = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id WHERE precarga_maestro.precarga_imaestro_id = ?";
    private final static String PREGARGA_DETALLE_SELECT_ALL_INDIVIDUAL = "SELECT *, productos.family_id, productos.subfamily_id, family_Product.fami_nombre, sub_family_Product.subfami_nombre FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto INNER JOIN family_Product ON productos.family_id = family_Product.fami_id INNER JOIN sub_family_Product ON productos.subfamily_id = sub_family_Product.subfami_id WHERE precarga_detalle_idmaestro = ? AND productos.id_producto = ?";
    private final static String PACKAGING_SELECT_INDIVIDUAL = "SELECT *, valores1.valores_nombre AS pkging_col_cod_barra_Nombre, valores2.valores_nombre AS pkging_equiv_Nombre, valores3.valores_nombre AS pkging_ind_cod_barra_nombre, valores4.valores_nombre AS pkging_ind_ident_nombre, valores5.valores_nombre AS pkging_aplica_nombre, valores6.valores_nombre AS pkging_chas_nombre FROM ingresos_pkging INNER JOIN ingresos_valores AS valores1 ON pkging_col_cod_barra = valores1.valores_id INNER JOIN ingresos_valores AS valores2 ON pkging_equiv = valores2.valores_id INNER JOIN ingresos_valores AS valores3 ON pkging_ind_cod_barra = valores3.valores_id INNER JOIN ingresos_valores AS valores4 ON pkging_ind_ident = valores4.valores_id INNER JOIN ingresos_valores AS valores5 ON pkging_aplica = valores5.valores_id INNER JOIN ingresos_valores AS valores6 ON pkging_chas = valores6.valores_id WHERE pkging_precarga_detalle_id = ?";
    private final static String PLAN_MAESTRO_SELECT_INDIVIDUAL = "SELECT * FROM ingresos_plan_maestro WHERE plan_maestro_detalle_id = ?";
    private final static String PLAN_DETALLE_SELECT_INDIVIDUAL = "SELECT *, ingresos_plan_maestro.plan_maestro_detalle_id FROM ingresos_plan_detalle INNER JOIN ingresos_plan_maestro ON ingresos_plan_maestro.plan_maestro_id = ingresos_plan_detalle.plan_detalle_maestroid INNER JOIN ingresos_valores ON ingresos_plan_detalle.plan_detalle_verificado = ingresos_valores.valores_id INNER JOIN ingresos_plan_item ON ingresos_plan_detalle.plan_detalle_tipo_plan = ingresos_plan_item.plan_item_id WHERE plan_maestro_id = ?";

    public ArrayList<Precargas> obtenerIndividual(String maestroId, String pdetalleId, String pproductoId) throws ClassNotFoundException, IOException, SQLException, Exception {
        Precargas miPrecarga = null;

        ArrayList<Precargas> listaPrecarga = new ArrayList();
        ArrayList<PrecargasMaestro> listaEncabezado = new ArrayList();
        ArrayList<PlanGeneral> listaPlanGeneral = new ArrayList();
        PlanGeneral miPlanGeneral = null;

        Connection cPrecargaUltimos = null;
        PreparedStatement ptsmtPrecargaUltimo = null;
        ResultSet rsPrecrgaUltimos = null;

        Connection cPrecargasDetalles = null;
        PreparedStatement ptsmPrecargasDetalles = null;
        ResultSet rsPrecargasDetalles = null;

        Connection cPacakcging = null;
        PreparedStatement ptsmPackaging = null;
        ResultSet rsPackaging = null;

        Connection cPlanMaestro = null;
        PreparedStatement ptsmPlanMaestro = null;
        ResultSet rsPlanMaestro = null;

        Connection cPlanDetalle = null;
        PreparedStatement ptsmPlanDetalle = null;
        ResultSet rsPlanDetalle = null;
        try {
            ArrayList<PrecargasDetalle> misDetalles = null;

            cPrecargaUltimos = DB.getInstance().getConnection();
            ptsmtPrecargaUltimo = cPrecargaUltimos.prepareStatement(PREGARGA_ENCABEZADO_SELECT_ALL_INDIVIDUAL);
            ptsmtPrecargaUltimo.setString(1, maestroId);
            rsPrecrgaUltimos = ptsmtPrecargaUltimo.executeQuery();
            while (rsPrecrgaUltimos.next()) {
                miPrecarga = new Precargas();
                PrecargasMaestro miMaestro = new PrecargasMaestro(
                        rsPrecrgaUltimos.getString("prov_nombre"),
                        rsPrecrgaUltimos.getString("precarga_maestro_fechamanifesto"),
                        rsPrecrgaUltimos.getString("precarga_maestro_codigomanifiesto"),
                        rsPrecrgaUltimos.getString("precarga_maestro_fecharecepcion"),
                        rsPrecrgaUltimos.getString("precarga_maestro_observaciones"),
                        rsPrecrgaUltimos.getString("precarga_maestro_arribo")
                );
                miMaestro.setId(rsPrecrgaUltimos.getString("precarga_imaestro_id"));
                listaEncabezado.add(miMaestro);
                try {

                    cPrecargasDetalles = DB.getInstance().getConnection();
                    ptsmPrecargasDetalles = cPrecargasDetalles.prepareStatement(PREGARGA_DETALLE_SELECT_ALL_INDIVIDUAL);
                    ptsmPrecargasDetalles.setString(1, rsPrecrgaUltimos.getString("precarga_imaestro_id"));
                    ptsmPrecargasDetalles.setString(2, pproductoId);
                    rsPrecargasDetalles = ptsmPrecargasDetalles.executeQuery();
                    misDetalles = new ArrayList();
                    while (rsPrecargasDetalles.next()) {

                        PrecargasDetalle miDetalle = new PrecargasDetalle(
                                rsPrecargasDetalles.getString("codigo_producto"),
                                rsPrecargasDetalles.getString("precarga_detalle_cant"),
                                rsPrecargasDetalles.getString("precarga_detalle_muestra"),
                                rsPrecargasDetalles.getString("precarga_detalle_obs"));
                        miDetalle.setEstadoPcking(rsPrecargasDetalles.getString("precarga_detalle_pkging_class"));
                        miDetalle.setId(rsPrecargasDetalles.getString("precarga_detalle_id"));
                        miDetalle.setFamiliaId(rsPrecargasDetalles.getString("family_id"));
                        miDetalle.setSubFamiliaId(rsPrecargasDetalles.getString("subfamily_id"));
                        miDetalle.setFamiliaNombre(rsPrecargasDetalles.getString("fami_nombre"));
                        miDetalle.setSubfamiliaNombre(rsPrecargasDetalles.getString("subfami_nombre"));
                        miDetalle.setEstadoDiseno(rsPrecargasDetalles.getString("precarga_detalle_diseno_class"));

                        System.out.println("Subfamilia: " + miDetalle.getSubFamiliaId());
                        System.out.println("familia: " + miDetalle.getFamiliaId());
                        if (miDetalle.getSubFamiliaId().equals("29")) {
                            //cada detalle tendra un plan de producto y de packaging
                            miDetalle.setPlanTemporario("Encendido");
                        } else if (miDetalle.getSubFamiliaId().equals("28")) {
                            miDetalle.setPlanTemporario("Precalentamiento");
                        } else if (miDetalle.getFamiliaId().equals("9")) {
                            miDetalle.setPlanTemporario("Lamparas");
                        } else {
                            miDetalle.setPlanTemporario("Plan no aplicado");
                        }
                        try {
                            cPacakcging = DB.getInstance().getConnection();
                            ptsmPackaging = cPacakcging.prepareStatement(PACKAGING_SELECT_INDIVIDUAL);
                            System.out.println("Id precarga detalle para packaging : " + rsPrecargasDetalles.getString("precarga_detalle_id"));
                            ptsmPackaging.setString(1, pdetalleId);
                            rsPackaging = ptsmPackaging.executeQuery();
                            Packaging miPackaging = new Packaging();
                            while (rsPackaging.next()) {
                                miPackaging.setChasNombre(rsPackaging.getString("pkging_chas_nombre"));
                                miPackaging.setChas(rsPackaging.getString("pkging_chas"));
                                miPackaging.setColBarraNombre(rsPackaging.getString("pkging_col_cod_barra_nombre"));
                                miPackaging.setColBarra(rsPackaging.getString("pkging_col_cod_barra"));
                                miPackaging.setIndAplicacionesNombre(rsPackaging.getString("pkging_aplica_nombre"));
                                miPackaging.setIndAplicaciones(rsPackaging.getString("pkging_aplica"));
                                miPackaging.setIndBarraNombre(rsPackaging.getString("pkging_ind_cod_barra_nombre"));
                                miPackaging.setIndBarra(rsPackaging.getString("pkging_ind_cod_barra"));
                                miPackaging.setIndEquivNombre(rsPackaging.getString("pkging_equiv_nombre"));
                                miPackaging.setIndEquiv(rsPackaging.getString("pkging_equiv"));
                                miPackaging.setIndIdentNombre(rsPackaging.getString("pkging_ind_ident_nombre"));
                                miPackaging.setIndIdent(rsPackaging.getString("pkging_ind_ident"));
                            }
                            System.out.println("Producto: " + rsPrecargasDetalles.getString("precarga_detalle_idproducto"));
                            miDetalle.setPackaging(miPackaging);
                        } catch (Exception ex) {
                            System.out.println("Exeption: " + ex.getMessage());
                        } finally {

                            //////////
                            if (rsPackaging != null) {
                                try {
                                    rsPackaging.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (ptsmPackaging != null) {
                                try {
                                    ptsmPackaging.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (cPacakcging != null) {
                                try {
                                    cPacakcging.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        try {
                            cPlanMaestro = DB.getInstance().getConnection();
                            ptsmPlanMaestro = cPlanMaestro.prepareStatement(PLAN_MAESTRO_SELECT_INDIVIDUAL);
                            ptsmPlanMaestro.setString(1, pdetalleId);
                            rsPlanMaestro = ptsmPlanMaestro.executeQuery();

                            while (rsPlanMaestro.next()) {
                                try {

                                    System.out.println("Id de plan maestro : " + rsPlanMaestro.getString("plan_maestro_id"));
                                    cPlanDetalle = DB.getInstance().getConnection();
                                    ptsmPlanDetalle = cPlanDetalle.prepareStatement(PLAN_DETALLE_SELECT_INDIVIDUAL);
                                    ptsmPlanDetalle.setString(1, rsPlanMaestro.getString("plan_maestro_id"));
                                    rsPlanDetalle = ptsmPlanDetalle.executeQuery();

                                    while (rsPlanDetalle.next()) {
                                        miPlanGeneral = new PlanGeneral();
                                        System.out.println("Plan detalle: " + rsPlanDetalle.getString("plan_item_nombre") + "Tipo de plan: " + rsPlanDetalle.getString("valores_nombre"));
                                        //System.out.println("Tipo de plan: " + rsPlanDetalle.getString("valores_nombre"));

                                        miPlanGeneral.setDetalleId(rsPlanDetalle.getString("plan_detalle_id"));
                                        miPlanGeneral.setMaestroId(rsPlanDetalle.getString("plan_detalle_maestroid"));
                                        miPlanGeneral.setTipoPlan(rsPlanDetalle.getString("plan_item_nombre"));
                                        miPlanGeneral.setValor(rsPlanDetalle.getString("valores_nombre"));
                                        miPlanGeneral.setValorId(rsPlanDetalle.getString("valores_id"));
                                        miPlanGeneral.setComentarios(rsPlanDetalle.getString("plan_detalle_coment"));
                                        //miDetalle.setPlanGeneral(miPlanGeneral);
                                        //listaPlanGeneral.add(miPlanGeneral);  
                                        //miDetalle.setListaPlanGeneral(listaPlanGeneral);
                                        miDetalle.setMiPlanGeneral(miPlanGeneral);
                                    }

                                } catch (Exception ex) {
                                    System.out.println("Exeption: " + ex.getMessage());
                                } finally {
                                    if (rsPlanDetalle != null) {
                                        try {
                                            rsPlanDetalle.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (ptsmPlanDetalle != null) {
                                        try {
                                            ptsmPlanDetalle.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (cPlanDetalle != null) {
                                        try {
                                            cPlanDetalle.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Exeption: " + ex.getMessage());
                        } finally {

                            if (rsPlanMaestro != null) {
                                try {
                                    rsPlanMaestro.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (ptsmPlanMaestro != null) {
                                try {
                                    ptsmPlanMaestro.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (cPlanMaestro != null) {
                                try {
                                    cPlanMaestro.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (miDetalle.getFamiliaId().equals("9")) {
                            miDetalle.setPlanDiseno("Lamparas");
                        } else if (miDetalle.getSubFamiliaId().equals("28")) {
                            miDetalle.setPlanDiseno("Bujia preca");
                        } else if (miDetalle.getSubFamiliaId().equals("29")) {
                            miDetalle.setPlanDiseno("Bujia encendido");
                        } else if (miDetalle.getSubFamiliaId().equals("4")) {
                            miDetalle.setPlanDiseno("Airehabitáculo");
                        } else {
                            miDetalle.setPlanDiseno("SIN PLAN DEFINIDO");
                        }
                        misDetalles.add(miDetalle);
                        miPrecarga.setDetalles(misDetalles);
                        miPrecarga.setMaestro(miMaestro);

                    }

                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                } finally {
                    if (rsPrecargasDetalles != null) {
                        try {
                            rsPrecargasDetalles.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (ptsmPrecargasDetalles != null) {
                        try {
                            ptsmPrecargasDetalles.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cPrecargasDetalles != null) {
                        try {
                            cPrecargasDetalles.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("Mipresacra: " + miPrecarga);
                listaPrecarga.add(miPrecarga);
            }

            System.out.println("Mis detalles: " + misDetalles);
            System.out.println("Lista precargas: " + listaPrecarga);
        } finally {
            if (rsPrecrgaUltimos != null) {
                try {
                    rsPrecrgaUltimos.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ptsmtPrecargaUltimo != null) {
                try {
                    ptsmtPrecargaUltimo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cPrecargaUltimos != null) {
                try {
                    cPrecargaUltimos.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return listaPrecarga;
    }

}
