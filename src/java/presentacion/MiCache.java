package presentacion;

import logica.TipoObserv;
import entidades.DB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import static jdk.nashorn.internal.objects.NativeArray.map;

public class MiCache {

    private final static String CONSULT_LIST_OBS_PKG = "SELECT * FROM ingresos_observ_aux WHERE obs_tipo_campo LIKE '%pkg%'";
    private final static String CONSULT_LIST_OBS_BUJIA_PRECA = "SELECT * FROM `ingresos_observ_aux` WHERE obs_tipo_campo LIKE '%bujia_preca%'";
    private final static String CONSULT_LIST_OBS_BUJIA_ENCEN = "SELECT * FROM `ingresos_observ_aux` WHERE obs_tipo_campo LIKE '%bujia_encendido%'";
    private final static String CONSULT_LIST_OBS_LAMPARAS = "SELECT * FROM `ingresos_observ_aux` WHERE obs_tipo_campo LIKE '%lamparas%'";
    private final static String CONSULT_LIST_OBS_AIRE_HABITACULO = "SELECT * FROM `ingresos_observ_aux` WHERE obs_tipo_campo LIKE '%filtro_aire_habit%'";
    
    public static ArrayList<TipoObserv> TIPO_OBSERVACIONES_PKG = new ArrayList();
    public static ArrayList<TipoObserv> TIPO_OBSERVACIONES_BUJIA_PRECA = new ArrayList();
    public static ArrayList<TipoObserv> TIPO_OBSERVACIONES_BUJIA_ENCEN = new ArrayList();
    public static ArrayList<TipoObserv> TIPO_OBSERVACIONES_LAMPARAS = new ArrayList();
    public static ArrayList<TipoObserv> TIPO_OBSERVACIONES_AIRE_HABITACULO = new ArrayList();

    public static void consultaTipoObs() throws ClassNotFoundException, IOException, SQLException {
        Connection conObs = null;
        PreparedStatement psmtObs = null;
        ResultSet rsObs = null;

        try {
            conObs = DB.getInstance().getConnection();
            psmtObs = conObs.prepareStatement(CONSULT_LIST_OBS_PKG);
            rsObs = psmtObs.executeQuery();
            while (rsObs.next()) {
                System.out.println("observaciones tipo: " + rsObs.getString("obs_tipo_campo"));
                TipoObserv observ = new TipoObserv(rsObs.getString("obs_tipo_campo"),rsObs.getString("obs_tipo_id"));
                TIPO_OBSERVACIONES_PKG.add(observ);
            }
            System.out.println("Observaciones listado Packaging: " + TIPO_OBSERVACIONES_PKG);

        } catch (Exception ex) {
            System.out.println("Exption: " + ex.getMessage());
        } finally {
            if (rsObs != null) {
                try {
                    rsObs.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (psmtObs != null) {
                try {
                    psmtObs.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (conObs != null) {
                try {
                    conObs.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
        }
        
        Connection conObsBujiaEncen = null;
        PreparedStatement psmtObsBujiaEncen = null;
        ResultSet rsObsBujiaEncen = null;

        try {
            conObsBujiaEncen = DB.getInstance().getConnection();
            psmtObsBujiaEncen = conObsBujiaEncen.prepareStatement(CONSULT_LIST_OBS_BUJIA_ENCEN);
            rsObsBujiaEncen = psmtObsBujiaEncen.executeQuery();
            while (rsObsBujiaEncen.next()) {
                System.out.println("observaciones tipo: " + rsObsBujiaEncen.getString("obs_tipo_campo"));
                TipoObserv observ = new TipoObserv(rsObsBujiaEncen.getString("obs_tipo_campo"),rsObsBujiaEncen.getString("obs_tipo_id"));
                TIPO_OBSERVACIONES_BUJIA_ENCEN.add(observ);
            }
            System.out.println("Observaciones listado Bujia Encendido: " + TIPO_OBSERVACIONES_BUJIA_ENCEN);

        } catch (Exception ex) {
            System.out.println("Exption: " + ex.getMessage());
        } finally {
            if (rsObsBujiaEncen != null) {
                try {
                    rsObsBujiaEncen.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (psmtObsBujiaEncen != null) {
                try {
                    psmtObsBujiaEncen.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (conObsBujiaEncen != null) {
                try {
                    conObsBujiaEncen.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
        }
        
        
        Connection conObsBujiaPreca = null;
        PreparedStatement psmtObsBujiaPreca = null;
        ResultSet rsObsBujiaPreca = null;

        try {
            conObsBujiaPreca = DB.getInstance().getConnection();
            psmtObsBujiaPreca = conObsBujiaPreca.prepareStatement(CONSULT_LIST_OBS_BUJIA_PRECA);
            rsObsBujiaPreca = psmtObsBujiaPreca.executeQuery();
            while (rsObsBujiaPreca.next()) {
                System.out.println("observaciones tipo: " + rsObsBujiaPreca.getString("obs_tipo_campo"));
                TipoObserv observ = new TipoObserv(rsObsBujiaPreca.getString("obs_tipo_campo"),rsObsBujiaPreca.getString("obs_tipo_id"));
                TIPO_OBSERVACIONES_BUJIA_PRECA.add(observ);
            }
            System.out.println("Observaciones listado Bujia PRECA: " + TIPO_OBSERVACIONES_BUJIA_PRECA);

        } catch (Exception ex) {
            System.out.println("Exption: " + ex.getMessage());
        } finally {
            if (rsObsBujiaPreca != null) {
                try {
                    rsObsBujiaPreca.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (psmtObsBujiaPreca != null) {
                try {
                    psmtObsBujiaPreca.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (conObsBujiaPreca != null) {
                try {
                    conObsBujiaPreca.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
        }
        
        ///
        
        Connection conObsLamparas = null;
        PreparedStatement psmtObsLamparas = null;
        ResultSet rsObsLamparas = null;

        try {
            conObsLamparas = DB.getInstance().getConnection();
            psmtObsLamparas = conObsLamparas.prepareStatement(CONSULT_LIST_OBS_LAMPARAS);
            rsObsLamparas = psmtObsLamparas.executeQuery();
            while (rsObsLamparas.next()) {
                System.out.println("observaciones tipo: " + rsObsLamparas.getString("obs_tipo_campo"));
                TipoObserv observ = new TipoObserv(rsObsLamparas.getString("obs_tipo_campo"),rsObsLamparas.getString("obs_tipo_id"));
                TIPO_OBSERVACIONES_LAMPARAS.add(observ);
            }
            System.out.println("Observaciones listado LAMPARAS: " + TIPO_OBSERVACIONES_LAMPARAS);

        } catch (Exception ex) {
            System.out.println("Exption: " + ex.getMessage());
        } finally {
            if (rsObsLamparas != null) {
                try {
                    rsObsLamparas.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (psmtObsLamparas != null) {
                try {
                    psmtObsLamparas.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (conObsLamparas != null) {
                try {
                    conObsLamparas.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
        }
        
        ///
        
        Connection conObsAireHabitaculo = null;
        PreparedStatement psmtObsAireHabitaculo = null;
        ResultSet rsObsAireHabitaculo = null;

        try {
            conObsAireHabitaculo = DB.getInstance().getConnection();
            psmtObsAireHabitaculo = conObsAireHabitaculo.prepareStatement(CONSULT_LIST_OBS_AIRE_HABITACULO);
            rsObsAireHabitaculo = psmtObsAireHabitaculo.executeQuery();
            while (rsObsAireHabitaculo.next()) {
                System.out.println("observaciones tipo: " + rsObsAireHabitaculo.getString("obs_tipo_campo"));
                TipoObserv observ = new TipoObserv(rsObsAireHabitaculo.getString("obs_tipo_campo"),rsObsAireHabitaculo.getString("obs_tipo_id"));
                TIPO_OBSERVACIONES_AIRE_HABITACULO.add(observ);
            }
            System.out.println("Observaciones listado AIRE HABITACULO: " + TIPO_OBSERVACIONES_AIRE_HABITACULO);

        } catch (Exception ex) {
            System.out.println("Exption: " + ex.getMessage());
        } finally {
            if (rsObsAireHabitaculo != null) {
                try {
                    rsObsAireHabitaculo.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (psmtObsAireHabitaculo != null) {
                try {
                    psmtObsAireHabitaculo.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
            if (conObsAireHabitaculo != null) {
                try {
                    conObsAireHabitaculo.close();
                } catch (SQLException e) {
                    System.out.println("Exeption: " + e.getMessage());
                }
            }
        }

    }

}
