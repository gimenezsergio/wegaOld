package ingresos.persistencia;

import com.google.gson.Gson;
import entidades.DB;
import entidades.MensajeBrowser;
import ingresos.entidades.CargaParam;
import ingresos.entidades.CargaParamComentarios;
import ingresos.entidades.Packaging;
import ingresos.entidades.PlanGeneral;
import ingresos.entidades.Precargas;
import ingresos.entidades.PrecargasDetalle;
import ingresos.entidades.PrecargasMaestro;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CargaDao {

    private CargaDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static CargaDao INSTANCE = null;

    public static CargaDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new CargaDao();
        }
        return INSTANCE;
    }

    private final static String UPDATE_STATUS_MAESTRO = "UPDATE precarga_maestro SET precarga_maestro_status = 1, precarga_maestro_fecharecepcion = ?, precarga_imaestro_analista = ? WHERE precarga_maestro.precarga_imaestro_id = ?";
    private final static String UPDATE_PRECARGA_DETALLE = "UPDATE precarga_detalle SET precarga_detalle_obs = ? WHERE precarga_detalle_id = ?";
    private final static String CONSULT_STATUS_MAESTRO = "SELECT * FROM `precarga_maestro` WHERE (precarga_maestro_diseno = 0 OR precarga_maestro_pkging = 0) AND precarga_imaestro_id = ?";

    public static void updateStatus(String idMaestro, String fechaRecepcion, CargaParam cargaDatos) throws ClassNotFoundException, IOException, SQLException, Exception {
        Connection con = null;
        PreparedStatement psmt = null;

        Connection conStatusConsul = null;
        PreparedStatement pstmStatusConsul = null;
        ResultSet rsStatusConsul = null;

        Connection conObs = null;
        PreparedStatement pstmObs = null;
        ResultSet rsStatusObs = null;
        Gson convertir = new Gson();
        //Vacio, da paermiso para cerrar
        //Con resultados, no da permiso para cerrar

        try {
            conStatusConsul = DB.getInstance().getConnection();
            pstmStatusConsul = conStatusConsul.prepareStatement(CONSULT_STATUS_MAESTRO);
            pstmStatusConsul.setString(1, idMaestro);
            rsStatusConsul = pstmStatusConsul.executeQuery();
            if (rsStatusConsul.next()) {
                throw new Exception("Faltan items para cargar antes de cerrar");
            } else {
                try {
                    
                    for (int x = 0; x < cargaDatos.getComentarios().size(); x++) {
                        if (cargaDatos.getComentarios().get(x).getObs() != null && !cargaDatos.getComentarios().get(x).getObs().isEmpty()) {
                            try {
                                System.out.println("en DAO id detalle:" + cargaDatos.getComentarios().get(x).getIdDetale());
                                System.out.println("en DAO obs: " + cargaDatos.getComentarios().get(x).getObs());
                                System.out.println(cargaDatos.getIdMaestro());
                                System.out.println(cargaDatos.getFecha());
                                System.out.println("Con obs: " + cargaDatos.getComentarios().get(x).getObs() + " iDetalle: " + cargaDatos.getComentarios().get(x).getIdDetale());

                                conObs = DB.getInstance().getConnection();
                                pstmObs = conObs.prepareStatement(UPDATE_PRECARGA_DETALLE);
                                //rsStatusObs = pstmObs.executeQuery();
                                pstmObs.setString(1, cargaDatos.getComentarios().get(x).getObs());
                                pstmObs.setString(2, cargaDatos.getComentarios().get(x).getIdDetale());
                                pstmObs.executeUpdate();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                throw new Exception("No se pudo insertar comentario");

                            } finally {
                                if (pstmObs != null) {
                                    try {
                                        pstmObs.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (conObs != null) {
                                    try {
                                        conObs.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            System.out.println("Sin obs: " + cargaDatos.getComentarios().get(x).getObs() + " iDetalle: " + cargaDatos.getComentarios().get(x).getIdDetale());
                        }

                    }
                    con = DB.getInstance().getConnection();
                    psmt = con.prepareStatement(UPDATE_STATUS_MAESTRO);
                    psmt.setString(1, fechaRecepcion);
                    psmt.setString(2, cargaDatos.getAnalista());
                    psmt.setString(3, idMaestro);
                    psmt.executeUpdate();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Exeption update: " + ex.getMessage());

                } finally {
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

//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("Exeption consulta: " + ex.getMessage());
//            MensajeBrowser mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
//            System.out.println("Exeption CARGA: " + ex.getMessage());
//            out.println(convertir.toJson(mensajeError));
        } finally {
            if (rsStatusConsul != null) {
                try {
                    rsStatusConsul.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmStatusConsul != null) {
                try {
                    pstmStatusConsul.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conStatusConsul != null) {
                try {
                    conStatusConsul.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private final static String PREGARGA_ENCABEZADO_SELECT_ALL = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id WHERE precarga_maestro_status = 1 ORDER BY precarga_imaestro_id DESC";
    private final static String PREGARGA_DETALLE_SELECT_ALL = "SELECT *, productos.family_id, productos.subfamily_id, family_Product.fami_nombre, sub_family_Product.subfami_nombre FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto INNER JOIN family_Product ON productos.family_id = family_Product.fami_id INNER JOIN sub_family_Product ON productos.subfamily_id = sub_family_Product.subfami_id WHERE precarga_detalle_idmaestro = ?";

    public ArrayList<Precargas> obtenerUltimos() throws SQLException, Exception {
        Precargas miPrecarga = null;
        //ArrayList PlanControlConValores = new ArrayList();

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
                rsPrecrgaUltimos.getString("precarga_maestro_arribo"));
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

                        System.out.println("Producto: " + rsPrecargasDetalles.getString("precarga_detalle_idproducto"));

                        //PlanControlConValores = PlanControlDisenoDAO.getInstance().obtenerUltimos(miMaestro.getId());
                        //miDetalle.setListaPlanGeneral(PlanControlConValores);
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

//     private final static String PREGARGA_ENCABEZADO_SELECT_ALL2 = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id WHERE precarga_maestro_status = 1 ORDER BY precarga_imaestro_id DESC";
//    private final static String PREGARGA_DETALLE_SELECT_ALL2 = "SELECT *, productos.family_id, productos.subfamily_id, family_Product.fami_nombre, sub_family_Product.subfami_nombre FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto INNER JOIN family_Product ON productos.family_id = family_Product.fami_id INNER JOIN sub_family_Product ON productos.subfamily_id = sub_family_Product.subfami_id WHERE precarga_detalle_idmaestro = ?";
//    private final static String PACKAGING_SELECT2 = "SELECT *, valores1.valores_nombre AS pkging_col_cod_barra_Nombre, valores2.valores_nombre AS pkging_equiv_Nombre, valores3.valores_nombre AS pkging_ind_cod_barra_nombre, valores4.valores_nombre AS pkging_ind_ident_nombre, valores5.valores_nombre AS pkging_aplica_nombre, valores6.valores_nombre AS pkging_chas_nombre FROM ingresos_pkging INNER JOIN ingresos_valores AS valores1 ON pkging_col_cod_barra = valores1.valores_id INNER JOIN ingresos_valores AS valores2 ON pkging_equiv = valores2.valores_id INNER JOIN ingresos_valores AS valores3 ON pkging_ind_cod_barra = valores3.valores_id INNER JOIN ingresos_valores AS valores4 ON pkging_ind_ident = valores4.valores_id INNER JOIN ingresos_valores AS valores5 ON pkging_aplica = valores5.valores_id INNER JOIN ingresos_valores AS valores6 ON pkging_chas = valores6.valores_id WHERE pkging_precarga_detalle_id = ?";
//    private final static String PLAN_MAESTRO_SELECT2 = "SELECT * FROM ingresos_plan_maestro WHERE plan_maestro_detalle_id = ?";
//    private final static String PLAN_DETALLE_SELECT2 = "SELECT *, ingresos_plan_maestro.plan_maestro_detalle_id, IF (ingresos_valores.valores_id = 2, 'retenido', 'no_retenido') as retener FROM ingresos_plan_detalle INNER JOIN ingresos_plan_maestro ON ingresos_plan_maestro.plan_maestro_id = ingresos_plan_detalle.plan_detalle_maestroid INNER JOIN ingresos_valores ON ingresos_plan_detalle.plan_detalle_verificado = ingresos_valores.valores_id INNER JOIN ingresos_plan_item ON ingresos_plan_detalle.plan_detalle_tipo_plan = ingresos_plan_item.plan_item_id WHERE plan_maestro_id = ?";
    //private final static String PREGARGA_ENCABEZADO_SELECT_ALL2 = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id WHERE precarga_maestro_status = 1 ORDER BY precarga_imaestro_id DESC";
    private final static String PREGARGA_ENCABEZADO_SELECT_ALL2 = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id INNER JOIN precarga_arribo ON precarga_maestro.precarga_maestro_arribo = precarga_arribo.arribo_id WHERE precarga_maestro_status = 1 ORDER BY precarga_imaestro_id DESC";
    private final static String PREGARGA_ENCABEZADO_SELECT_ALL2_LIMIT = "SELECT * FROM precarga_maestro INNER JOIN proveedores ON precarga_maestro.precarga_maestro_idprovee = proveedores.prov_id INNER JOIN precarga_arribo ON precarga_maestro.precarga_maestro_arribo = precarga_arribo.arribo_id WHERE precarga_maestro_status = 1 ORDER BY precarga_imaestro_id DESC LIMIT 3";
    private final static String PREGARGA_DETALLE_SELECT_ALL2 = "SELECT *, productos.family_id, productos.subfamily_id, family_Product.fami_nombre, sub_family_Product.subfami_nombre FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto INNER JOIN family_Product ON productos.family_id = family_Product.fami_id INNER JOIN sub_family_Product ON productos.subfamily_id = sub_family_Product.subfami_id WHERE precarga_detalle_idmaestro = ?";
    //  private final static String PACKAGING_SELECT2 = "SELECT *, valores1.valores_nombre AS pkging_col_cod_barra_Nombre, valores2.valores_nombre AS pkging_equiv_Nombre, valores3.valores_nombre AS pkging_ind_cod_barra_nombre, valores4.valores_nombre AS pkging_ind_ident_nombre, valores5.valores_nombre AS pkging_aplica_nombre, valores6.valores_nombre AS pkging_chas_nombre FROM ingresos_pkging INNER JOIN ingresos_valores AS valores1 ON pkging_col_cod_barra = valores1.valores_id INNER JOIN ingresos_valores AS valores2 ON pkging_equiv = valores2.valores_id INNER JOIN ingresos_valores AS valores3 ON pkging_ind_cod_barra = valores3.valores_id INNER JOIN ingresos_valores AS valores4 ON pkging_ind_ident = valores4.valores_id INNER JOIN ingresos_valores AS valores5 ON pkging_aplica = valores5.valores_id INNER JOIN ingresos_valores AS valores6 ON pkging_chas = valores6.valores_id WHERE pkging_precarga_detalle_id = ?";
    private final static String PACKAGING_SELECT2 = "SELECT * , IF( `pkging_aplica` = 2 OR `pkging_col_cod_barra` = 2 OR `pkging_ind_cod_barra` = 2 OR `pkging_equiv` = 2 OR `pkging_chas` = 2 OR `pkging_ind_ident` = 2 , 'retenido', 'no_retenido' ) AS retener , valores1.valores_nombre AS pkging_col_cod_barra_Nombre, valores2.valores_nombre AS pkging_equiv_Nombre, valores3.valores_nombre AS pkging_ind_cod_barra_nombre, valores4.valores_nombre AS pkging_ind_ident_nombre, valores5.valores_nombre AS pkging_aplica_nombre, valores6.valores_nombre AS pkging_chas_nombre FROM ingresos_pkging INNER JOIN ingresos_valores AS valores1 ON pkging_col_cod_barra = valores1.valores_id INNER JOIN ingresos_valores AS valores2 ON pkging_equiv = valores2.valores_id INNER JOIN ingresos_valores AS valores3 ON pkging_ind_cod_barra = valores3.valores_id INNER JOIN ingresos_valores AS valores4 ON pkging_ind_ident = valores4.valores_id INNER JOIN ingresos_valores AS valores5 ON pkging_aplica = valores5.valores_id INNER JOIN ingresos_valores AS valores6 ON pkging_chas = valores6.valores_id WHERE pkging_precarga_detalle_id = ?";
    private final static String PLAN_MAESTRO_SELECT2 = "SELECT * FROM ingresos_plan_maestro WHERE plan_maestro_detalle_id = ?";
    private final static String PLAN_DETALLE_SELECT2 = "SELECT *, ingresos_plan_maestro.plan_maestro_detalle_id, IF (ingresos_valores.valores_id = 2, 'retenido', 'no_retenido') as retener FROM ingresos_plan_detalle INNER JOIN ingresos_plan_maestro ON ingresos_plan_maestro.plan_maestro_id = ingresos_plan_detalle.plan_detalle_maestroid INNER JOIN ingresos_valores ON ingresos_plan_detalle.plan_detalle_verificado = ingresos_valores.valores_id INNER JOIN ingresos_plan_item ON ingresos_plan_detalle.plan_detalle_tipo_plan = ingresos_plan_item.plan_item_id WHERE plan_maestro_id = ?";

    public ArrayList<Precargas> obtenerUltimosConPlanControl(String maestroId, String pdetalleId, String pproductoId, String cantPedida) throws ClassNotFoundException, IOException, SQLException, Exception {
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
        
        String PREGARGA_ENCABEZADO = "";
        if (cantPedida.equals("all")) {
            PREGARGA_ENCABEZADO = PREGARGA_ENCABEZADO_SELECT_ALL2;
        } else if (cantPedida.equals("news")) {
            PREGARGA_ENCABEZADO = PREGARGA_ENCABEZADO_SELECT_ALL2_LIMIT;
        }
        
        try {
            ArrayList<PrecargasDetalle> misDetalles = null;

            cPrecargaUltimos = DB.getInstance().getConnection();
            ptsmtPrecargaUltimo = cPrecargaUltimos.prepareStatement(PREGARGA_ENCABEZADO);
            //ptsmtPrecargaUltimo.setString(1, maestroId);
            rsPrecrgaUltimos = ptsmtPrecargaUltimo.executeQuery();
            while (rsPrecrgaUltimos.next()) {
                miPrecarga = new Precargas();
                PrecargasMaestro miMaestro = new PrecargasMaestro(
                        rsPrecrgaUltimos.getString("prov_nombre"),
                        rsPrecrgaUltimos.getString("precarga_maestro_fechamanifesto"),
                        rsPrecrgaUltimos.getString("precarga_maestro_codigomanifiesto"),
                        rsPrecrgaUltimos.getString("precarga_maestro_fecharecepcion"),
                        rsPrecrgaUltimos.getString("precarga_maestro_observaciones"), 
                        rsPrecrgaUltimos.getString("arribo_nombre"));
                miMaestro.setId(rsPrecrgaUltimos.getString("precarga_imaestro_id"));
                miMaestro.setAnalista(rsPrecrgaUltimos.getString("precarga_imaestro_analista"));
                listaEncabezado.add(miMaestro);
                try {
                    cPrecargasDetalles = DB.getInstance().getConnection();
                    ptsmPrecargasDetalles = cPrecargasDetalles.prepareStatement(PREGARGA_DETALLE_SELECT_ALL2);
                    ptsmPrecargasDetalles.setString(1, rsPrecrgaUltimos.getString("precarga_imaestro_id"));
                    //ptsmPrecargasDetalles.setString(2, pproductoId);
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

//                        System.out.println("Subfamilia: " + miDetalle.getSubFamiliaId());
//                        System.out.println("familia: " + miDetalle.getFamiliaId());
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
                            ptsmPackaging = cPacakcging.prepareStatement(PACKAGING_SELECT2);
//                            System.out.println("Id precarga detalle para packaging : " + rsPrecargasDetalles.getString("precarga_detalle_id"));
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
                                miPackaging.setRetener(rsPackaging.getString("retener"));
                            }
//                            System.out.println("Producto: " + rsPrecargasDetalles.getString("precarga_detalle_idproducto"));
                            miDetalle.setPackaging(miPackaging);
                        } catch (Exception ex) {
                            System.out.println("Exeption: " + ex.getMessage());
                        } finally {
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
                            //start
                            long lStartTimePLAN_MAESTRO_SELECT2 = System.nanoTime();
                            cPlanMaestro = DB.getInstance().getConnection();
                            ptsmPlanMaestro = cPlanMaestro.prepareStatement(PLAN_MAESTRO_SELECT2);
                            ptsmPlanMaestro.setString(1, miDetalle.getId());
                            rsPlanMaestro = ptsmPlanMaestro.executeQuery();

                            while (rsPlanMaestro.next()) {
//                                System.out.println("Id de plan maestro : " + rsPlanMaestro.getString("plan_maestro_id"));
                                try {
                                    //start
                                    long lStartTimePLAN_DETALLE_SELECT2 = System.nanoTime();
                                    cPlanDetalle = DB.getInstance().getConnection();
                                    ptsmPlanDetalle = cPlanDetalle.prepareStatement(PLAN_DETALLE_SELECT2);
                                    ptsmPlanDetalle.setString(1, rsPlanMaestro.getString("plan_maestro_id"));
                                    rsPlanDetalle = ptsmPlanDetalle.executeQuery();

                                    while (rsPlanDetalle.next()) {
                                        miPlanGeneral = new PlanGeneral();
//                                        System.out.println("Plan detalle: " + rsPlanDetalle.getString("plan_item_nombre") + "Tipo de plan: " + rsPlanDetalle.getString("valores_nombre"));
                                        //System.out.println("Tipo de plan: " + rsPlanDetalle.getString("valores_nombre"));
//                                        System.out.println("COMENTARIOS SQL" + rsPlanDetalle.getString("plan_detalle_coment"));
                                        miPlanGeneral.setDetalleId(rsPlanDetalle.getString("plan_detalle_id"));
                                        miPlanGeneral.setMaestroId(rsPlanDetalle.getString("plan_detalle_maestroid"));
                                        miPlanGeneral.setTipoPlan(rsPlanDetalle.getString("plan_item_nombre"));
                                        miPlanGeneral.setValor(rsPlanDetalle.getString("valores_nombre"));
                                        miPlanGeneral.setValorId(rsPlanDetalle.getString("valores_id"));
                                        miPlanGeneral.setRetener(rsPlanDetalle.getString("retener"));
                                        miPlanGeneral.setComentarios(rsPlanDetalle.getString("plan_detalle_coment"));
                                        //miPlanGeneral.setComentarios("TEST COMENTARIO");
                                        //miDetalle.setPlanGeneral(miPlanGeneral);
                                        //listaPlanGeneral.add(miPlanGeneral);  
                                        //miDetalle.setListaPlanGeneral(listaPlanGeneral);
                                        //miDetalle.setRetener("ProductoLibre");
                                        if (rsPlanDetalle.getString("plan_detalle_tipo_plan").equals("5")) {
                                            miDetalle.setComentario(rsPlanDetalle.getString("plan_detalle_coment"));
                                        } else {
                                            //   miDetalle.setComentario("SIN LOTE");
                                        }

                                        if (rsPlanDetalle.getString("retener").equals("retenido")) {
                                            miDetalle.setRetener("ProductoRetenido");
                                        } else {
                                            //miDetalle.setRetener("ProductoLibre");
                                        }
                                        miDetalle.setMiPlanGeneral(miPlanGeneral);
                                    }
                                    //end
                                    long lEndTimePLAN_DETALLE_SELECT2 = System.nanoTime();
                                    //time elapsed
                                    long output = lEndTimePLAN_DETALLE_SELECT2 - lStartTimePLAN_DETALLE_SELECT2;

                                    System.out.println("Elapsed time in milliseconds PLAN_DETALLE_SELECT2: " + output / 1000000);

                                } catch (Exception ex) {
                                    throw new Exception("Exception: " + ex.getMessage());
                                } finally {
                                    if (cPlanDetalle != null) {
                                        try {
                                            cPlanDetalle.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                //catch

                            }
                            
                            //end
                            long lEndTimePLAN_MAESTRO_SELECT2 = System.nanoTime();

                            //time elapsed
                            long outputPLAN_MAESTRO_SELECT2 = lEndTimePLAN_MAESTRO_SELECT2 - lStartTimePLAN_MAESTRO_SELECT2;

                            System.out.println("Elapsed time in milliseconds PLAN_MAESTRO_SELECT2: " + outputPLAN_MAESTRO_SELECT2 / 1000000);

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
                    ex.printStackTrace();
                    throw new Exception("exception: " + ex);
                } finally {
                    if (cPrecargasDetalles != null) {
                        try {
                            cPrecargasDetalles.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }
                //ACA VA EL CATCH
//                System.out.println("Mipresacra: " + miPrecarga);
                listaPrecarga.add(miPrecarga);
            }

//            System.out.println("Mis detalles: " + misDetalles);
//            System.out.println("Lista precargas: " + listaPrecarga);
        } finally {
            cPrecargaUltimos.close();

        }

        return listaPrecarga;
    }

}
