package ingresos.persistencia;

import entidades.DB;
import ingresos.entidades.Precargas;
import ingresos.entidades.PrecargasDetalle;
import ingresos.entidades.PrecargasMaestro;
import ingresos.presentacion.Precarga;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrecargaDAO {

    private PrecargaDAO() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static PrecargaDAO INSTANCE = null;

    public static PrecargaDAO getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new PrecargaDAO();
        }
        return INSTANCE;
    }

    public static void insert(Map precargaDedetalle) throws Exception {
        Map encabezado = (Map) precargaDedetalle.get("encabezado");
        List productos = (List) precargaDedetalle.get("productos");
        //Precargas miPrecarga = new PrecargasMaestro();
        System.out.println("arribo: " + (String) encabezado.get("arribo"));
        Precargas miPrecarga = new Precargas();
        PrecargasMaestro miMaestro = new PrecargasMaestro(
                (String) encabezado.get("id_proveedor"),
                (String) encabezado.get("fecha_manifiesto"),
                (String) encabezado.get("codigo_manifiesto"),
                (String) encabezado.get("fecha_recepcion"),
                (String) encabezado.get("obs"),
                (String) encabezado.get("arribo")
        );

        System.out.println("Encabezado en DAO: " + miMaestro);

        for (int i = 0; i < productos.size(); i++) {
            PrecargasDetalle miDetalle = new PrecargasDetalle(
                    String.valueOf(((Map) productos.get(i)).get("producto_id")),
                    String.valueOf(((Map) productos.get(i)).get("cant1")),
                    String.valueOf(((Map) productos.get(i)).get("muestras1")),
                    String.valueOf(((Map) productos.get(i)).get("obs")));
            System.out.println("Productos: " + miDetalle);
        }
        Connection con = null;
        try {

            con = DB.getInstance().getConnection();
            PreparedStatement sentencia = con.prepareStatement("insert into precarga_maestro ("
                    + "precarga_maestro_fechamanifesto, "
                    + "precarga_maestro_fecharecepcion, "
                    + "precarga_maestro_observaciones,"
                    + "precarga_maestro_codigomanifiesto,"
                    + "precarga_maestro_idprovee, precarga_maestro_arribo)"
                    + "values(?,?,?,?,?,?);");
            sentencia.setString(1, miMaestro.getFechaManifesto());
            sentencia.setString(2, miMaestro.getFechaRecepcion());
            sentencia.setString(3, miMaestro.getObs());
            sentencia.setString(4, miMaestro.getCodigoManifiesto());
            sentencia.setString(5, miMaestro.getProveedor());
            sentencia.setString(6, miMaestro.getArribo());
            sentencia.execute();
            PreparedStatement sentencia2 = con.prepareStatement("SELECT precarga_imaestro_id FROM precarga_maestro ORDER BY precarga_imaestro_id DESC LIMIT 1;");
            ResultSet respuesta2 = sentencia2.executeQuery();
            String mae_id_actual = "";
            if (respuesta2.next()) {
                mae_id_actual = respuesta2.getString(1);
            }
            for (int i = 0; i < productos.size(); i++) {
                PreparedStatement sentencia3 = con.prepareStatement(""
                        + "INSERT INTO precarga_detalle ("
                        + "precarga_detalle_idmaestro, "
                        + "precarga_detalle_idproducto, "
                        + "precarga_detalle_cant,"
                        + "precarga_detalle_muestra, "
                        + "precarga_detalle_obs) "
                        + "VALUES(?,?,?,?,?);");
                sentencia3.setString(1, String.valueOf(mae_id_actual));
                sentencia3.setString(2, String.valueOf(((Map) productos.get(i)).get("producto_id")));
                sentencia3.setString(3, String.valueOf(((Map) productos.get(i)).get("cant1")));
                sentencia3.setString(4, String.valueOf(((Map) productos.get(i)).get("muestras1")));
                sentencia3.setString(5, String.valueOf(((Map) productos.get(i)).get("obs")));

                sentencia3.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("No se pudo insertar precarga");
        } finally {            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private final static String PREGARGA_ENCABEZADO_SELECT_ALL = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id WHERE precarga_maestro_status = 0 ORDER BY precarga_imaestro_id DESC";
    //private final static String PREGARGA_ENCABEZADO_SELECT_ALL = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id ORDER BY precarga_imaestro_id DESC LIMIT 15";
    //private final static String PREGARGA_DETALLE_SELECT_ALL = "SELECT * FROM precarga_detalle WHERE precarga_detalle_idmaestro = (SELECT precarga_imaestro_id FROM precarga_maestro ORDER BY precarga_imaestro_id DESC LIMIT 1)";
    //private final static String PREGARGA_DETALLE_SELECT_ALL = "SELECT *, productos.family_id, productos.subfamily_id FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto WHERE precarga_detalle_idmaestro = ?";
    private final static String PREGARGA_DETALLE_SELECT_ALL = "SELECT *, productos.family_id, productos.subfamily_id, family_Product.fami_nombre, sub_family_Product.subfami_nombre FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto INNER JOIN family_Product ON productos.family_id = family_Product.fami_id INNER JOIN sub_family_Product ON productos.subfamily_id = sub_family_Product.subfami_id WHERE precarga_detalle_idmaestro = ?";

    public ArrayList<Precargas> obtenerUltimos() throws SQLException, Exception {
        Precargas miPrecarga = null;

        ArrayList<Precargas> listaPrecarga = new ArrayList();
        ArrayList<PrecargasMaestro> listaEncabezado = new ArrayList();
        Connection cPrecargaUltimos = null;
        PreparedStatement ptsmtPrecargaUltimo = null;
        ResultSet rsPrecrgaUltimos = null;

        Connection cPrecargasDetalles = null;
        PreparedStatement ptsmPrecargasDetalles = null;
        ResultSet rsPrecargasDetalles = null;
        try {
            ArrayList<PrecargasDetalle> misDetalles = null;

            cPrecargaUltimos = DB.getInstance().getConnection();
            ptsmtPrecargaUltimo = cPrecargaUltimos.prepareStatement(PREGARGA_ENCABEZADO_SELECT_ALL);
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
                        miDetalle.setProductoId(rsPrecargasDetalles.getString("id_producto"));

                        System.out.println("Producto: " + rsPrecargasDetalles.getString("precarga_detalle_idproducto"));
                        misDetalles.add(miDetalle);
                        miPrecarga.setDetalles(misDetalles);
                        miPrecarga.setMaestro(miMaestro);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Exeption: " + ex.getMessage());
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
            //cPrecargaUltimos.close();
        }

        return listaPrecarga;
    }

}
