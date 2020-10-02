package ingresos.persistencia;

import entidades.DB;
import ingresos.entidades.Packaging;
import ingresos.entidades.Precargas;
import ingresos.entidades.PrecargasDetalle;
import ingresos.entidades.PrecargasMaestro;
import static ingresos.persistencia.PlanControlDisenoDAO.updateStatusPrecargaDetalle;
import static ingresos.persistencia.PlanControlDisenoDAO.updateStatusPrecargaMaestro;
import ingresos.presentacion.Precarga;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static presentacion.MiCache.*;

public class PackagingDAO {

    private PackagingDAO() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static PackagingDAO INSTANCE = null;

    public static PackagingDAO getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new PackagingDAO();
        }
        return INSTANCE;
    }
    private final static String ESTADO_PRECARGA_DETALLE_SELECT = "SELECT precarga_detalle_pkging FROM precarga_detalle WHERE precarga_detalle.precarga_detalle_idmaestro = ? AND precarga_detalle_pkging = 0";
    private final static String ESTADO_PRECARGA_MAESTRO_UPDATE = "UPDATE precarga_maestro SET precarga_maestro.precarga_maestro_pkging = 1 WHERE precarga_maestro.precarga_imaestro_id = ?";
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
            if(rs.next()){
                System.out.println("Tiene registros, estado INCOMPLETO");
            }else{
                System.out.println("Tiene registros, estado COMPLETO. UPDATE estado de MaestroPrecarga.");
                try {
                    conMaestro = DB.getInstance().getConnection();
                    pstmMaestro = conMaestro.prepareStatement(ESTADO_PRECARGA_MAESTRO_UPDATE);
                    pstmMaestro.setString(1, id);
                    pstmMaestro.execute();
                }catch (Exception ex){
                    ex.printStackTrace();
                    System.out.println("Exeption " + ex.getMessage());
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

    private final static String INSERT_PACKAGING = "INSERT INTO ingresos_pkging (pkging_col_cod_barra, pkging_ind_cod_barra, pkging_ind_ident, pkging_equiv, pkging_aplica, pkging_precarga_detalle_id, pkging_chas, pkging_chas_coment, pkging_aplica_coment, pkging_equiv_coment, pkging_ind_ident_coment, pkging_ind_cod_barra_coment, pkging_col_cod_barra_coment) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String INSERT_OBS = "INSERT INTO ingresos_observ(ing_obs_text, ing_obs_tipo, ing_obs_packing_id) VALUES (?,?,?)";
    private final static String ESTADO_CLASS_PRECARGA_DETALLE = "UPDATE precarga_detalle SET precarga_detalle_pkging_class = 'pcking_completo' WHERE precarga_detalle_id = ?";
    private final static String ESTADO_PRECARGA_DETALLE = "UPDATE precarga_detalle SET precarga_detalle_pkging = 1 WHERE precarga_detalle_id = ?";

    public static void updateStatusPrecargaDetalle(String id) throws ClassNotFoundException, IOException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DB.getInstance().getConnection();
            pstm = con.prepareStatement(ESTADO_PRECARGA_DETALLE);
            pstm.setString(1, id);
            pstm.executeUpdate();
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

    public static void insert(Map parametro) throws Exception {
        Packaging miPackaging = new Packaging((String) parametro.get("colBarra"), (String) parametro.get("indBarra"), (String) parametro.get("indIdent"), (String) parametro.get("indEquiv"), (String) parametro.get("aplica"), (String) parametro.get("ind_chas"));
        miPackaging.setIdDetalle((String) parametro.get("idDetalle"));
        //miPackaging.setChasComent(parametro.get("pkg_ind_chas_obs"));
        System.out.println("MiPackaging: " + miPackaging);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Connection conObs = null;
        PreparedStatement psmtObs = null;

        Connection conUpdateEstado = null;
        PreparedStatement psmtEstado = null;

        try {
            con = DB.getInstance().getConnection();
            psmt = con.prepareStatement(INSERT_PACKAGING, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, miPackaging.getColBarra());
            psmt.setString(2, miPackaging.getIndBarra());
            psmt.setString(3, miPackaging.getIndIdent());
            psmt.setString(4, miPackaging.getIndEquiv());
            psmt.setString(5, miPackaging.getAplicaciones());
            psmt.setString(6, miPackaging.getIdDetalle());
            psmt.setString(7, miPackaging.getChas());
            psmt.setString(8, (String) parametro.get("pkg_ind_chas_obs"));
            psmt.setString(9, (String) parametro.get("pkg_ind_aplica_obs"));
            psmt.setString(10, (String) parametro.get("pkg_ind_equiv_obs"));
            psmt.setString(11, (String) parametro.get("pkg_ind_ident_obs"));
            psmt.setString(12, (String) parametro.get("pkg_ind_cod_barra_obs"));
            psmt.setString(13, (String) parametro.get("pkg_col_cod_barra_obs"));
            
            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();
//            if (rs.next()) {
//                System.out.println("ID insertado" + rs.getInt(1));
//                for (int i = 0; i < TIPO_OBSERVACIONES_PKG.size(); i++) {
//                    if (((String) parametro.get(TIPO_OBSERVACIONES_PKG.get(i).getTipo())).isEmpty()) {
//                        System.out.println("Observacion de " + (TIPO_OBSERVACIONES_PKG.get(i).getTipo()) + " vacio");
//                    } else {
//                        try {
//                            System.out.println("Observacion de " + (TIPO_OBSERVACIONES_PKG.get(i).getTipo()) + " es: " + ((String) parametro.get(TIPO_OBSERVACIONES_PKG.get(i).getTipo())) + " Su id es " + TIPO_OBSERVACIONES_PKG.get(i).getId());
//                            conObs = DB.getInstance().getConnection();
//                            psmtObs = conObs.prepareStatement(INSERT_OBS);
//                            psmtObs.setString(1, ((String) parametro.get(TIPO_OBSERVACIONES_PKG.get(i).getTipo())));
//                            psmtObs.setString(2, TIPO_OBSERVACIONES_PKG.get(i).getId());
//                            psmtObs.setInt(3, rs.getInt(1));
//                            psmtObs.execute();
//                            //ResultSet rsObs = null;
//
//                        } catch (Exception ex) {
//                            System.out.println("Exeption: " + ex.getMessage());
//                        } finally {
//                            if (psmtObs != null) {
//                                try {
//                                    psmtObs.close();
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            if (conObs != null) {
//                                try {
//                                    conObs.close();
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }
//            }

            try {
                System.out.println("ID detalle para UPDATE packaging: " + miPackaging.getIdDetalle());
                conUpdateEstado = DB.getInstance().getConnection();
                psmtEstado = conUpdateEstado.prepareStatement(ESTADO_CLASS_PRECARGA_DETALLE);
                psmtEstado.setString(1, miPackaging.getIdDetalle());
                psmtEstado.execute();
                //updateStatusPrecargaDetalle(miPackaging.getIdDetalle());
                updateStatusPrecargaDetalle(miPackaging.getIdDetalle(), (String) parametro.get("idMaestro"));
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

            System.out.println("DAO packaging: " + miPackaging);
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

        }

    }
}
