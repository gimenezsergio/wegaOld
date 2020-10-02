package ingresos.persistencia;

import entidades.DB;
import ingresos.entidades.BusquedaProveedor;
import ingresos.entidades.Packaging;
import ingresos.entidades.PlanGeneral;
import ingresos.entidades.Precargas;
import ingresos.entidades.PrecargasDetalle;
import ingresos.entidades.PrecargasMaestro;
import ingresos.logica.BusquedaProveedorLogic;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BusquedaIngresosDAO {

    private BusquedaIngresosDAO() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static BusquedaIngresosDAO INSTANCE = null;

    public static BusquedaIngresosDAO getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new BusquedaIngresosDAO();
        }
        return INSTANCE;
    }

    //private final static String PROVEEDOR_PRECARGA = "SELECT *, SUM(precarga_detalle_cant) AS cant_suma FROM precarga_detalle INNER JOIN productos ON productos.id_producto = precarga_detalle.precarga_detalle_idproducto WHERE precarga_detalle_idmaestro IN( SELECT precarga_imaestro_id FROM precarga_maestro WHERE precarga_maestro_idprovee = ? AND precarga_maestro_fechamanifesto >= ? AND precarga_maestro_fechamanifesto <= ? ) GROUP BY precarga_detalle_idproducto";
    private final static String PROVEEDOR_PRECARGA = "SELECT * FROM precarga_detalle INNER JOIN productos ON productos.id_producto = precarga_detalle.precarga_detalle_idproducto WHERE precarga_detalle_idmaestro IN( SELECT precarga_imaestro_id FROM precarga_maestro WHERE precarga_maestro_idprovee = ? AND precarga_maestro_fechamanifesto >= ? AND precarga_maestro_fechamanifesto <= ? )";
    private final static String PROVEEDOR_DISENO = "SELECT plan_detalle_verificado FROM ingresos_plan_detalle WHERE plan_detalle_maestroid IN( SELECT plan_maestro_id FROM ingresos_plan_maestro WHERE plan_maestro_detalle_id = ? )";
    private final static String PROVEEDOR_PACKAGING = "SELECT IF( pkging_aplica = 2 OR pkging_col_cod_barra = 2 OR pkging_ind_cod_barra = 2 OR pkging_equiv = 2 OR pkging_chas = 2 OR pkging_ind_ident = 2, 'no_ok', 'ok' ) AS retener FROM ingresos_pkging WHERE pkging_precarga_detalle_id = ?";

    public ArrayList<BusquedaProveedor> proveedor(int proveedorId, String desde, String hasta) throws ClassNotFoundException, IOException, SQLException {
        ArrayList<BusquedaProveedor> listado = new ArrayList();
        int productoEstado;
        String verificado = "";

        //Precarga Conexion
        Connection conPrecarga = null;
        PreparedStatement pstmPrecarga = null;
        ResultSet rsPrecarga = null;

        //Plan Diseno Conexion
        Connection conDiseno = null;
        PreparedStatement pstmDiseno = null;
        ResultSet rsDiseno = null;

        //Plan Packaging Conexion
        Connection conPackaging = null;
        PreparedStatement pstmPackaging = null;
        ResultSet rsPackaging = null;

        //Plan Packaging Conexion
        conPrecarga = DB.getInstance().getConnection();
        pstmPrecarga = conPrecarga.prepareStatement(PROVEEDOR_PRECARGA);
        pstmPrecarga.setInt(1, proveedorId);
        pstmPrecarga.setString(2, desde);
        pstmPrecarga.setString(3, hasta);
        rsPrecarga = pstmPrecarga.executeQuery();
        while (rsPrecarga.next()) {
            BusquedaProveedor unProducto = new BusquedaProveedor();
            //Precarga
            unProducto.setProductoId(rsPrecarga.getString("precarga_detalle_idproducto"));
            unProducto.setProductoNombre(rsPrecarga.getString("codigo_producto"));
            //unProducto.setCantidad(rsPrecarga.getInt("cant_suma"));
            unProducto.setCantidad(rsPrecarga.getInt("precarga_detalle_cant"));
            //unProducto.setEstadoDiseno(rsPrecarga.getString("precarga_detalle_diseno"));
            unProducto.setEstadoPakaging(rsPrecarga.getString("precarga_detalle_pkging"));

            //Plan Diseno
            conDiseno = DB.getInstance().getConnection();
            pstmDiseno = conDiseno.prepareStatement(PROVEEDOR_DISENO);
            pstmDiseno.setString(1, rsPrecarga.getString("precarga_detalle_id"));
            rsDiseno = pstmDiseno.executeQuery();
            while (rsDiseno.next()) {
                System.out.println("Diseno verificado: " + rsDiseno.getString("plan_detalle_verificado") + " de producto: " + rsPrecarga.getString("codigo_producto"));
//                if (unProducto.getEstadoDisenoYPkg().equals("3") || unProducto.getEstadoDisenoYPkg().equals("1")) {
//                    unProducto.setEstadoDiseno(rsDiseno.getString("plan_detalle_verificado"));
//                    
//                }else{
//                    
//                }

//                if (unProducto.getEstadoDiseno().equals("2")) {
//                    // no sumar otro
//                } else {
//                    //sumar
//                    unProducto.setEstadoDiseno(rsDiseno.getString("plan_detalle_verificado"));
//
//                }
                
                if(rsDiseno.getString("plan_detalle_verificado").equals("2")){
                    unProducto.setEstadoDiseno(rsDiseno.getString("plan_detalle_verificado"));
                    verificado = rsDiseno.getString("plan_detalle_verificado");
                    System.out.println(unProducto.getProductoNombre() + " Estado Diseno (BREAK): " + unProducto.getEstadoDiseno());
                    break;
                }

            }

            //Plan Packaging
            conPackaging = DB.getInstance().getConnection();
            pstmPackaging = conPackaging.prepareStatement(PROVEEDOR_PACKAGING);
            pstmPackaging.setString(1, rsPrecarga.getString("precarga_detalle_id"));
            rsPackaging = pstmPackaging.executeQuery();
            while (rsPackaging.next()) {
                unProducto.setEstadoPakaging(rsPackaging.getString("retener"));
            }
            System.out.println(unProducto.getProductoNombre() + " Estado Diseno: " + unProducto.getEstadoDiseno());

            if (rsDiseno != null) {
                try {
                    rsDiseno.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmDiseno != null) {
                try {
                    pstmDiseno.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conDiseno != null) {
                try {
                    conDiseno.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (rsPackaging != null) {
                try {
                    rsPackaging.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmPackaging != null) {
                try {
                    pstmPackaging.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conPackaging != null) {
                try {
                    conPackaging.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

                //unProducto.calcularNoOk();
                
            productoEstado = BusquedaProveedorLogic.comprarYAgregar(listado, unProducto.getProductoNombre(), unProducto.getCantidad(), verificado);
            if (productoEstado == 1) {
                
            } else {
                listado.add(unProducto);
                
            }
            

           // listado.add(unProducto);
        }
        verificado = "1";

        if (rsPrecarga != null) {
            try {
                rsPrecarga.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmPrecarga != null) {
            try {
                pstmPrecarga.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conPrecarga != null) {
            try {
                conPrecarga.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }

    private final static String PRECARGA_ENCABEZADO_SQL = "SELECT * FROM precarga_maestro WHERE precarga_maestro_idprovee = ? AND precarga_maestro_fecharecepcion >= ? AND precarga_maestro_fecharecepcion <= ?";
    private final static String PRECARGA_DETALLE_SQL = "SELECT * FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto WHERE precarga_detalle_idmaestro = ? ORDER BY precarga_detalle_idmaestro DESC";
    private final static String PLAN_MAESTRO_SQL = "SELECT * FROM ingresos_plan_maestro WHERE plan_maestro_detalle_id = ?";

    private final static String PLAN_DETALLE_SLQ = "SELECT * FROM ingresos_plan_detalle WHERE plan_detalle_maestroid = ?";

    public ArrayList<BusquedaProveedor> no_ok(int proveedorId, String desde, String hasta) throws ClassNotFoundException, IOException, SQLException {
        Connection preEncabezadoCon = null;
        PreparedStatement preEncabezadoPstm = null;
        ResultSet preEncabezadoRs = null;

        Connection precargaDeatlleCon = null;
        PreparedStatement precargaDeatllePstm = null;
        ResultSet precargaDeatlleRs = null;

        Connection planMaestroCon = null;
        PreparedStatement planMaestroPstm = null;
        ResultSet planMaestroRs = null;

        Connection planDetalleCon = null;
        PreparedStatement planDetallePstm = null;
        ResultSet planDetalleRs = null;

        ArrayList<BusquedaProveedor> listado = new ArrayList();
        BusquedaProveedor unProducto;
        String product = "";

        int noOkTotal = 0;

        preEncabezadoCon = DB.getInstance().getConnection();
        preEncabezadoPstm = preEncabezadoCon.prepareStatement(PRECARGA_ENCABEZADO_SQL);
        preEncabezadoPstm.setInt(1, proveedorId);
        preEncabezadoPstm.setString(2, desde);
        preEncabezadoPstm.setString(3, hasta);
        preEncabezadoRs = preEncabezadoPstm.executeQuery();
        while (preEncabezadoRs.next()) {

            System.out.println("Precarga maestro id : " + preEncabezadoRs.getString("precarga_imaestro_id"));
            precargaDeatlleCon = DB.getInstance().getConnection();
            precargaDeatllePstm = precargaDeatlleCon.prepareStatement(PRECARGA_DETALLE_SQL);
            precargaDeatllePstm.setString(1, preEncabezadoRs.getString("precarga_imaestro_id"));
            precargaDeatlleRs = precargaDeatllePstm.executeQuery();
            while (precargaDeatlleRs.next()) {

                System.out.println("-------- PRECARGA DETALLE: " + precargaDeatlleRs.getString("precarga_detalle_id"));
                planMaestroCon = DB.getInstance().getConnection();
                planMaestroPstm = planMaestroCon.prepareStatement(PLAN_MAESTRO_SQL);
                planMaestroPstm.setString(1, precargaDeatlleRs.getString("precarga_detalle_id"));
                planMaestroRs = planMaestroPstm.executeQuery();
                while (planMaestroRs.next()) {
                    System.out.println("--------  -------- PLan Maestro: " + planMaestroRs.getString("plan_maestro_id"));
                    planDetalleCon = DB.getInstance().getConnection();
                    planDetallePstm = planDetalleCon.prepareStatement(PLAN_DETALLE_SLQ);
                    planDetallePstm.setString(1, planMaestroRs.getString("plan_maestro_id"));
                    planDetalleRs = planDetallePstm.executeQuery();
                    while (planDetalleRs.next()) {
                        unProducto = new BusquedaProveedor();
//                        unProducto.setIngresoNro(preEncabezadoRs.getString("precarga_imaestro_id"));
//                        unProducto.setComentario(precargaDeatlleRs.getString("precarga_detalle_obs"));
//                        unProducto.setCantidad(precargaDeatlleRs.getString("precarga_detalle_cant"));
//                        unProducto.setProductoNombre(precargaDeatlleRs.getString("codigo_producto"));
//                        System.out.println("--------  --------  -------- Verificado: " + planDetalleRs.getString("plan_detalle_verificado"));
//                        listado.add(unProducto);

                        if (listado.isEmpty()) {
                            unProducto.setNoOk(1);
                            unProducto.setIngresoNro(preEncabezadoRs.getString("precarga_imaestro_id"));
                            unProducto.setProductoNombre(precargaDeatlleRs.getString("codigo_producto"));
                            unProducto.setComentario(precargaDeatlleRs.getString("precarga_detalle_obs"));
                            unProducto.setCantidad(precargaDeatlleRs.getInt("precarga_detalle_cant"));
                            listado.add(unProducto);
                        } else {
                            int proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, precargaDeatlleRs.getString("codigo_producto"), precargaDeatlleRs.getInt("precarga_detalle_cant"),"verificado");

//                            if (proveedorLogic == 1) {
//                                //sumar la cantidad de No Ok al prducto encontrado
//                                System.out.println("sumar la cantidad de No Ok al prducto encontrado");
//                                System.out.println("Encontro: " + precargaDeatlleRs.getString("codigo_producto"));
//                                System.out.println("Sumarle 1 a: " + unProducto.getNoOk());
//                                unProducto.setNoOk(unProducto.getNoOk() + 1);
//                                System.out.println("Ahora no ok es: " + unProducto.getNoOk());
//                                //unProducto.setIngresoNro(preEncabezadoRs.getString("precarga_imaestro_id"));
//                                //unProducto.setProductoNombre(precargaDeatlleRs.getString("codigo_producto"));
//                                //unProducto.setComentario(precargaDeatlleRs.getString("precarga_detalle_obs"));
//                                //unProducto.setCantidad(precargaDeatlleRs.getString("precarga_detalle_cant"));
//                            } else {
//                                //Agregar producto completo
//                                System.out.println("Agregar producto completo");
//                                unProducto.setNoOk(1);
//                                unProducto.setIngresoNro(preEncabezadoRs.getString("precarga_imaestro_id"));
//                                unProducto.setProductoNombre(precargaDeatlleRs.getString("codigo_producto"));
//                                unProducto.setComentario(precargaDeatlleRs.getString("precarga_detalle_obs"));
//                                unProducto.setCantidad(precargaDeatlleRs.getString("precarga_detalle_cant"));
//                                listado.add(unProducto);
//                            }
                            if (proveedorLogic == 2) {

                                //Agregar producto completo
                                System.out.println("Agregar producto completo");
                                unProducto.setNoOk(1);
                                unProducto.setIngresoNro(preEncabezadoRs.getString("precarga_imaestro_id"));
                                unProducto.setProductoNombre(precargaDeatlleRs.getString("codigo_producto"));
                                unProducto.setComentario(precargaDeatlleRs.getString("precarga_detalle_obs"));
                                unProducto.setCantidad(precargaDeatlleRs.getInt("precarga_detalle_cant"));
                                listado.add(unProducto);
                            }

                        }
                    }
                    if (planDetalleRs != null) {
                        try {
                            planDetalleRs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (planDetallePstm != null) {
                        try {
                            planDetallePstm.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (planDetalleCon != null) {
                        try {
                            planDetalleCon.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (planMaestroRs != null) {
                    try {
                        planMaestroRs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (planMaestroPstm != null) {
                    try {
                        planMaestroPstm.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (planMaestroCon != null) {
                    try {
                        planMaestroCon.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (precargaDeatlleRs != null) {
                try {
                    precargaDeatlleRs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (precargaDeatllePstm != null) {
                try {
                    precargaDeatllePstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (precargaDeatlleCon != null) {
                try {
                    precargaDeatlleCon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        if (preEncabezadoRs != null) {
            try {
                preEncabezadoRs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preEncabezadoPstm != null) {
            try {
                preEncabezadoPstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preEncabezadoCon != null) {
            try {
                preEncabezadoCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cantidad de No OK son: " + noOkTotal);
        return listado;

    }

    private final static String PROVEEDOR_SQL = "SELECT * FROM precarga_maestro WHERE precarga_maestro_idprovee = ? AND precarga_maestro_fecharecepcion >= ? AND precarga_maestro_fecharecepcion <= ?";
    private final static String PRODUCTOS_SQL = "SELECT *, productos.codigo_producto FROM precarga_detalle INNER JOIN productos ON precarga_detalle.precarga_detalle_idproducto = productos.id_producto WHERE precarga_detalle_idmaestro = ? ORDER BY precarga_detalle.precarga_detalle_id DESC";
    private final static String NO_OK_MAESTRO_SQL = "SELECT * FROM ingresos_plan_maestro WHERE plan_maestro_id = ?";

    private final static String NO_OK_DETALLE_SQL = "SELECT *, COUNT(plan_detalle_verificado) AS no_ok, valores_nombre FROM ingresos_plan_detalle INNER JOIN ingresos_valores ON ingresos_plan_detalle.plan_detalle_verificado = ingresos_valores.valores_id WHERE plan_detalle_maestroid = ? AND plan_detalle_verificado = 2";
    private final static String NO_OK_DETALLE_SQL2 = "SELECT *, COUNT(plan_detalle_verificado) AS no_ok FROM ingresos_plan_detalle WHERE plan_detalle_maestroid = ? AND plan_detalle_verificado = 3";
    private final static String NO_OK_DETALLE_SQL3 = "SELECT * FROM ingresos_plan_detalle WHERE plan_detalle_maestroid = ? AND plan_detalle_verificado = 3";
// SELECT *, COUNT(plan_detalle_verificado) AS no_ok, valores_nombre FROM ingresos_plan_detalle INNER JOIN ingresos_valores ON ingresos_plan_detalle.plan_detalle_verificado = ingresos_valores.valores_id WHERE plan_detalle_maestroid = 456 AND plan_detalle_verificado = 3

    public ArrayList<BusquedaProveedor> no_ok2(int proveedorId, String desde, String hasta) throws ClassNotFoundException, IOException, SQLException {
        Connection proveedorCon = null;
        PreparedStatement proveedorPstm = null;
        ResultSet proveedorRs = null;

        Connection productosCon = null;
        PreparedStatement productosPstm = null;
        ResultSet productosRs = null;

        Connection noOkMaestroCon = null;
        PreparedStatement noOkMaestroPstm = null;
        ResultSet noOkMaestroRs = null;

        Connection noOkDetalleCon = null;
        PreparedStatement noOkDetallePstm = null;
        ResultSet noOkDetalleRs = null;

        ArrayList<BusquedaProveedor> listado = new ArrayList();
        BusquedaProveedor unProducto;
        String product = "";

        int noOkTotal = 0;

        proveedorCon = DB.getInstance().getConnection();
        proveedorPstm = proveedorCon.prepareStatement(PROVEEDOR_SQL);
        proveedorPstm.setInt(1, proveedorId);
        proveedorPstm.setString(2, desde);
        proveedorPstm.setString(3, hasta);
        proveedorRs = proveedorPstm.executeQuery();
        while (proveedorRs.next()) {
            System.out.println("manifiesto: " + proveedorRs.getString("precarga_imaestro_id"));

            productosCon = DB.getInstance().getConnection();
            productosPstm = productosCon.prepareStatement(PRODUCTOS_SQL);
            productosPstm.setString(1, proveedorRs.getString("precarga_imaestro_id"));
            productosRs = productosPstm.executeQuery();
            while (productosRs.next()) {
                System.out.println("------Precarga detalle: " + productosRs.getString("precarga_detalle_id"));
                System.out.println("------Producto: " + productosRs.getString("precarga_detalle_idproducto"));
                product = productosRs.getString("codigo_producto");

                noOkMaestroCon = DB.getInstance().getConnection();
                noOkMaestroPstm = noOkMaestroCon.prepareStatement(NO_OK_MAESTRO_SQL);
                noOkMaestroPstm.setString(1, productosRs.getString("precarga_detalle_id"));
                noOkMaestroRs = noOkMaestroPstm.executeQuery();
                while (noOkMaestroRs.next()) {
                    System.out.println("------ -------- NO_OK_MAESTRO_SQL: " + noOkMaestroRs.getString("plan_maestro_detalle_id"));
                    noOkDetalleCon = DB.getInstance().getConnection();
                    noOkDetallePstm = noOkDetalleCon.prepareStatement(NO_OK_DETALLE_SQL);
                    noOkDetallePstm.setString(1, noOkMaestroRs.getString("plan_maestro_id"));
                    noOkDetalleRs = noOkDetallePstm.executeQuery();
                    while (noOkDetalleRs.next()) {

                        unProducto = new BusquedaProveedor();
                        System.out.println("------ --------  --------- NO_OK_Detalle_SQL: " + noOkDetalleRs.getString("plan_detalle_verificado"));
                        //noOkTotal = noOkTotal + noOkDetalleRs.getInt("no_ok");
                        System.out.println("------ --------  --------- comment" + productosRs.getString("precarga_detalle_obs"));
                        if (noOkDetalleRs.getInt("plan_detalle_verificado") == 3) {
                            unProducto.setNoOk(noOkDetalleRs.getInt("no_ok"));
                            unProducto.setProductoNombre(productosRs.getString("codigo_producto"));
                            unProducto.setComentario(productosRs.getString("precarga_detalle_obs"));
                            unProducto.setCantidad(productosRs.getInt("precarga_detalle_cant"));
                            unProducto.setIngresoNro(proveedorRs.getString("precarga_imaestro_id"));

                            listado.add(unProducto);
                        }

//                        if (noOkDetalleRs.getInt("no_ok") > 0) {
//
//                            if (listado.isEmpty()) {
//                                unProducto.setProductoNombre(productosRs.getString("codigo_producto"));
//                                unProducto.setNoOk(noOkDetalleRs.getInt("no_ok"));
//                                listado.add(unProducto);
//                            } else {
//                                int proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, productosRs.getString("codigo_producto"));
//                                if (proveedorLogic == 1) {
//                                    //sumar la cantidad de No Ok al prducto encontrado
//                                    System.out.println("sumar la cantidad de No Ok al prducto encontrado");
//                                    //miBusqueda3.setNoOk(miBusqueda3.getNoOk() + 20);
//                                    unProducto.setNoOk(unProducto.getNoOk() + noOkDetalleRs.getInt("no_ok"));
//                                    unProducto.setProductoNombre(productosRs.getString("codigo_producto"));
//                                } else {
//                                    //Agregar producto completo
//                                    System.out.println("Agregar producto completo");
//                                    unProducto.setNoOk(noOkDetalleRs.getInt("no_ok"));
//                                    unProducto.setProductoNombre(productosRs.getString("codigo_producto"));
//                                    unProducto.setComentario(noOkDetalleRs.getString("plan_detalle_coment"));
//                                }
//                            }
//                        }
                        product = "";
                    }

                    if (noOkDetalleRs != null) {
                        try {
                            noOkDetalleRs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (noOkDetallePstm != null) {
                        try {
                            noOkDetallePstm.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (noOkDetalleCon != null) {
                        try {
                            noOkDetalleCon.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }

                if (noOkMaestroRs != null) {
                    try {
                        noOkMaestroRs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (noOkMaestroPstm != null) {
                    try {
                        noOkMaestroPstm.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (noOkMaestroCon != null) {
                    try {
                        noOkMaestroCon.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
            if (productosRs != null) {
                try {
                    productosRs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (productosPstm != null) {
                try {
                    productosPstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (productosCon != null) {
                try {
                    productosCon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        if (proveedorRs != null) {
            try {
                proveedorRs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (proveedorPstm != null) {
            try {
                proveedorPstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (proveedorCon != null) {
            try {
                proveedorCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cantidad de No OK son: " + noOkTotal);
        return listado;

    }

}
