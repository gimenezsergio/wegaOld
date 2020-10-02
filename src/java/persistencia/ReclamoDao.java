package persistencia;

import com.google.gson.Gson;
import entidades.Reclamos;
import entidades.ReclamoDetalle;
import entidades.ReclamosEncabezado;
import entidades.DB;
import entidades.MiUsuario;
import entidades.ReclamosOld;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Reclamo;

public class ReclamoDao {

    private final static String SQL_APROBAR = "UPDATE reclamos_encabezado SET aprobado=1 WHERE id_reclamos_encabezado = ?";

    public static void actualizar(String idReclamo) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_APROBAR);
        psmt.setString(1, idReclamo);

        psmt.execute();
    }

    Gson convertir = new Gson();

    private ReclamoDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ReclamoDao INSTANCE = null;

    public static ReclamoDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ReclamoDao();
        }
        return INSTANCE;
    }

    public static void insertar(Map reclamoDedetalla) throws Exception {
        Map encabezado = (Map) reclamoDedetalla.get("encabezado");
        List productos = (List) reclamoDedetalla.get("productos");

        System.out.println("Encabezado DAO: " + encabezado);
        System.out.println("products DAO: " + productos);

        ReclamosOld reclamoPut = new ReclamosOld();
        //try {
        //Encabezado
        reclamoPut.setCliente((String) encabezado.get("cliente"));
        reclamoPut.setFecha_emision(String.valueOf(encabezado.get("fecha_emision")));
        reclamoPut.setFecha_recepcion((String) encabezado.get("fecha_recepcion"));
        reclamoPut.setUserId((String) encabezado.get("user"));

        //Detalle
        for (int i = 0; i < productos.size(); i++) {
            reclamoPut.setCodigo_producto(String.valueOf(((Map) productos.get(i)).get("producto_id")), i);
            reclamoPut.setVerificacion(String.valueOf(((Map) productos.get(i)).get("verif_id")), i);
            reclamoPut.setCantidad_total(String.valueOf(((Map) productos.get(i)).get("cantidad_total")), i);
            reclamoPut.setOrigen(String.valueOf(((Map) productos.get(i)).get("orgigen_id")), i);
            reclamoPut.setResultado(String.valueOf(((Map) productos.get(i)).get("resultado")), i);
            reclamoPut.setCant_infundados(String.valueOf(((Map) productos.get(i)).get("infundados")), i);
            reclamoPut.setCant_fundados(String.valueOf(((Map) productos.get(i)).get("fundados")), i);
            reclamoPut.setCant_tema_comercial(String.valueOf(((Map) productos.get(i)).get("cant_tema_comercial")), i);
            reclamoPut.setId_provee(String.valueOf(((Map) productos.get(i)).get("id_proveedor")), i);

            if (String.valueOf(((Map) productos.get(i)).get("id_diseno")) == null || String.valueOf(((Map) productos.get(i)).get("id_diseno")).isEmpty()) {
                reclamoPut.setId_diseno(null, i);
            } else {
                reclamoPut.setId_diseno(String.valueOf(((Map) productos.get(i)).get("id_diseno")), i);
                //     String diseno = String.valueOf(((Map) productos.get(i)).get("id_diseno"));
                //   System.out.println("DISENO: " + diseno);

            }

            if (String.valueOf(((Map) productos.get(i)).get("id_proceso")) == null || String.valueOf(((Map) productos.get(i)).get("id_proceso")).isEmpty()) {
                reclamoPut.setId_proceso(null, i);
                System.out.println("Proceso vacio: " + String.valueOf(((Map) productos.get(i)).get("id_proceso")));
            } else {
                reclamoPut.setId_proceso(String.valueOf(((Map) productos.get(i)).get("id_proceso")), i);
            }

            if (String.valueOf(((Map) productos.get(i)).get("id_falla_mate")) == null || String.valueOf(((Map) productos.get(i)).get("id_falla_mate")).isEmpty()) {
                reclamoPut.setId_falla_mat(null, i);
            } else {
                reclamoPut.setId_proceso(String.valueOf(((Map) productos.get(i)).get("id_falla_mate")), i);
            }
            reclamoPut.ValidateCant(reclamoPut.getCantidad_total(), reclamoPut.getCant_fundados(), reclamoPut.getCant_infundados(), reclamoPut.getCant_tema_comercial(), i);

        }

        Connection con = null;
        con = DB.getInstance().getConnection();
        PreparedStatement sentencia = con.prepareStatement("insert into reclamos_encabezado ("
                + "id_cliente, fecha_emision, fecha_recepcion, id_user)"
                + "values(?,?,?,?);");
        sentencia.setString(1, reclamoPut.getCliente());
        sentencia.setString(2, reclamoPut.getFecha_emision());
        sentencia.setString(3, reclamoPut.getFecha_recepcion());
        sentencia.setString(4, reclamoPut.getUserId());
        sentencia.execute();
        PreparedStatement sentencia2 = con.prepareStatement("SELECT id_reclamos_encabezado FROM reclamos_encabezado ORDER BY id_reclamos_encabezado DESC LIMIT 1;");
        ResultSet respuesta2 = sentencia2.executeQuery();
        String mae_id_actual = "";
        if (respuesta2.next()) {
            mae_id_actual = respuesta2.getString(1);
        }
        for (int i = 0; i < productos.size(); i++) {
            PreparedStatement sentencia3 = con.prepareStatement(""
                    + "INSERT INTO reclamos_detalle ("
                    + "id_reclamos_detalle_item, "
                    + "id_producto, "
                    + "id_verificacion,"
                    + "id_origen, "
                    + "id_resultado, "
                    + "id_diseno,"
                    + "id_proceso,"
                    + "cant_infundados,"
                    + "cant_fundados,"
                    + "cant_tema_comercial,"
                    + "id_provee,"
                    + "id_falla_mat, "
                    + "cantidad_total, "
                    + "motivo, "
                    + "lote, "
                    + "observacion) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            sentencia3.setString(1, String.valueOf(mae_id_actual));
            sentencia3.setString(2, String.valueOf(((Map) productos.get(i)).get("producto_id")));
            sentencia3.setString(3, String.valueOf(((Map) productos.get(i)).get("verif_id")));
            sentencia3.setString(4, String.valueOf(((Map) productos.get(i)).get("orgigen_id")));
            sentencia3.setString(5, String.valueOf(((Map) productos.get(i)).get("resultado")));
            // if (String.valueOf(((Map) productos.get(i)).get("id_diseno")) == null || String.valueOf(((Map) productos.get(i)).get("id_diseno")).isEmpty()) {
            //     sentencia3.setString(6, null);
            //} else {
            sentencia3.setString(6, String.valueOf(((Map) productos.get(i)).get("id_diseno")));
            // }

            //  if (String.valueOf(((Map) productos.get(i)).get("id_proceso")) == null || String.valueOf(((Map) productos.get(i)).get("id_proceso")).isEmpty()) {
            //    sentencia3.setString(7, null);
            //    } else {
            //reclamoPut.setId_proceso(String.valueOf(((Map) productos.get(i)).get("id_proceso")), i);
            sentencia3.setString(7, String.valueOf(((Map) productos.get(i)).get("id_proceso")));
            //   }

            sentencia3.setString(8, String.valueOf(((Map) productos.get(i)).get("infundados")));
            sentencia3.setString(9, String.valueOf(((Map) productos.get(i)).get("fundados")));
            sentencia3.setString(10, String.valueOf(((Map) productos.get(i)).get("cant_tema_comercial")));
            sentencia3.setString(11, String.valueOf(((Map) productos.get(i)).get("id_proveedor")));
            //  if (String.valueOf(((Map) productos.get(i)).get("id_proceso")) == null || String.valueOf(((Map) productos.get(i)).get("id_proceso")).isEmpty()) {
            //     sentencia3.setString(12, null);
            // } else {
            sentencia3.setString(12, String.valueOf(((Map) productos.get(i)).get("id_falla_mate")));
            //  }

            sentencia3.setString(13, String.valueOf(((Map) productos.get(i)).get("cantidad_total")));
            sentencia3.setString(14, String.valueOf(((Map) productos.get(i)).get("Motivo")));
            sentencia3.setString(15, String.valueOf(((Map) productos.get(i)).get("lote")));
            sentencia3.setString(16, String.valueOf(((Map) productos.get(i)).get("observacion")));
            sentencia3.execute();
        }

    }

    private final static String SQL_RECLAMO_ENCABEZADO_SELECT_CLI = "SELECT * FROM reclamos_encabezado WHERE id_cliente = ? AND fecha_recepcion >= ? AND fecha_recepcion <= ?;";
    private final static String SQL_RECLAMO_ENCABEZADO_SELECT = "SELECT id_reclamos_encabezado, fecha_emision, fecha_recepcion, reclamos_encabezado.id_cliente, clientes.nombre, reclamos_encabezado.aprobado FROM reclamos_encabezado JOIN clientes ON clientes.id_cliente = reclamos_encabezado.id_cliente WHERE id_reclamos_encabezado = ?;";
    //private final static String SQL_RECLAMO_DETALLE_SELECT = "SELECT * from reclamos_encabezado INNER JOIN reclamos_detalle ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item WHERE reclamos_encabezado.id_reclamos_encabezado = ?;";
    private final static String SQL_RECLAMO_DETALLE_SELECT = "SELECT *, productos.codigo_producto , verificaciones.tipo, resultados.resultado, proveedores.prov_nombre, diseno.dis_nombre, proceso.proc_nombre, falla_materiales.falla_mat_nombre, origenes.origen from reclamos_encabezado INNER JOIN reclamos_detalle ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id JOIN origenes ON reclamos_detalle.id_origen = origenes.id_origen WHERE reclamos_encabezado.id_reclamos_encabezado = ?";

    public ArrayList<Reclamos> obtenerCustom(String cliente, String desde, String hasta, String IdSession) throws ClassNotFoundException,
            IOException, SQLException {
        Reclamos reclamoCero = new Reclamos();
        Reclamos reclamoUno = new Reclamos();
        Reclamos reclamoDos = new Reclamos();
        Reclamos reclamoTres = new Reclamos();
        Reclamos reclamoCuatro = new Reclamos();
        Reclamos reclamoCinco = new Reclamos();
        Reclamos reclamoSeis = new Reclamos();
        Reclamos reclamo7 = new Reclamos();
        Reclamos reclamo8 = new Reclamos();
        Reclamos reclamo9 = new Reclamos();
        Reclamos reclamo10 = new Reclamos();
        Reclamos reclamo11 = new Reclamos();
        Reclamos reclamo12 = new Reclamos();
        Reclamos reclamo13 = new Reclamos();
        Reclamos reclamo14 = new Reclamos();
        Reclamos reclamo15 = new Reclamos();
        Reclamos reclamo16 = new Reclamos();
        Reclamos reclamo17 = new Reclamos();
        Reclamos reclamo18 = new Reclamos();
        Reclamos reclamo19 = new Reclamos();
        Reclamos reclamo20 = new Reclamos();

        ArrayList<Reclamos> listaReclamos = new ArrayList();
        Boolean flagReclamoCero = false;
        Boolean flagReclamoUno = false;
        Boolean flagReclamoDos = false;
        Boolean flagReclamoTres = false;
        Boolean flagReclamoCuatro = false;
        Boolean flagReclamoCinco = false;
        Boolean flagReclamoSeis = false;
        Boolean flagReclamo7 = false;
        Boolean flagReclamo8 = false;
        Boolean flagReclamo9 = false;
        Boolean flagReclamo10 = false;
        Boolean flagReclamo11 = false;
        Boolean flagReclamo12 = false;
        Boolean flagReclamo13 = false;
        Boolean flagReclamo14 = false;
        Boolean flagReclamo15 = false;
        Boolean flagReclamo16 = false;
        Boolean flagReclamo17 = false;
        Boolean flagReclamo18 = false;
        Boolean flagReclamo19 = false;
        Boolean flagReclamo20 = false;

        ReclamosEncabezado miEncabezado = null;
        ReclamoDetalle miDetalleProductos = null;

        Connection cReclamoCliente = null;
        PreparedStatement ptsmtReclamoCLiente = null;
        ResultSet rsReclamoCliente = null;

        Connection cEncabazado = null;
        PreparedStatement ptsmtEncabezado = null;
        ResultSet rsEncabezado = null;

        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            //Obtener Id cliente
            cReclamoCliente = DB.getInstance().getConnection();
            ptsmtReclamoCLiente = cReclamoCliente.prepareStatement(SQL_RECLAMO_ENCABEZADO_SELECT_CLI);
            ptsmtReclamoCLiente.setString(1, cliente);
            ptsmtReclamoCLiente.setString(2, desde);
            ptsmtReclamoCLiente.setString(3, hasta);
            rsReclamoCliente = ptsmtReclamoCLiente.executeQuery();
            ReclamosOld reclamoIdClient = null;
            ArrayList idReclaDeClienAux = new ArrayList();
            while (rsReclamoCliente.next()) {
                idReclaDeClienAux.add(rsReclamoCliente.getString("id_reclamos_encabezado"));
            }
            System.out.println("Listado de reclamos del clientes: " + idReclaDeClienAux);

            //Ciclo por cada reclamo
            for (int i = 0; i < idReclaDeClienAux.size(); i++) {
                System.out.println("Reclamo num: " + idReclaDeClienAux.get(i));

                //Obtener encabezado
                //
                cEncabazado = DB.getInstance().getConnection();
                ptsmtEncabezado = cEncabazado.prepareStatement(SQL_RECLAMO_ENCABEZADO_SELECT);
                System.out.println("Cliente: " + cliente);

                ptsmtEncabezado.setString(1, (String) idReclaDeClienAux.get(i));
                rsEncabezado = ptsmtEncabezado.executeQuery();

                while (rsEncabezado.next()) {
                    miEncabezado = new ReclamosEncabezado();
                    miEncabezado.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                    miEncabezado.setFecha_recepcion(rsEncabezado.getString("fecha_recepcion"));
                    miEncabezado.setCliente(rsEncabezado.getString("id_cliente"));
                    miEncabezado.setCli_nombre(rsEncabezado.getString("nombre"));
                    miEncabezado.setFecha_emision(rsEncabezado.getString("fecha_emision"));
                    miEncabezado.setAprobado(rsEncabezado.getInt("aprobado"));
//                    miEncabezado.setLogedUser(MiUsuario.getUsuario(IdSession));

                    //miEncabezado.add(miEncabezado);
                    //listaProductos.add(miEncabezado);
                    System.out.println("Encabezado: " + rsEncabezado.getString("id_reclamos_encabezado"));
                    System.out.println("Encabezado: " + rsEncabezado.getString("fecha_recepcion"));
                    System.out.println("Encabezado: " + rsEncabezado.getString("id_cliente"));

                }

                //Obtener reclamo detalle
                //
                c = DB.getInstance().getConnection();
                ptsmt = c.prepareStatement(SQL_RECLAMO_DETALLE_SELECT);
                ptsmt.setString(1, (String) idReclaDeClienAux.get(i));
                rs = ptsmt.executeQuery();
                try {

                    while (rs.next()) {

                        miDetalleProductos = new ReclamoDetalle();
                        miDetalleProductos.setId_reclamo(rs.getString("id_reclamos_encabezado"));
                        //miDetalleProductos.setFecha(rs.getString("fecha_emision"));
                        // miDetalleProductos.setCliente(rs.getString("id_cliente"));
                        miDetalleProductos.setCodigo_producto(rs.getString("id_producto"), 0);
                        miDetalleProductos.setNombre_producto(rs.getString("codigo_producto"));
                        miDetalleProductos.setVerificacion(rs.getString("id_verificacion"), 0);
                        miDetalleProductos.setCantidad_total(rs.getString("cantidad_total"), 0);
                        miDetalleProductos.setObservacion(rs.getString("observacion"));
                        miDetalleProductos.setMotivo(rs.getString("motivo"));
                        miDetalleProductos.setOrigen(rs.getString("id_origen"), 0);
                        miDetalleProductos.setLote(rs.getString("lote"));
                        miDetalleProductos.setResultado(rs.getString("id_resultado"), 0);
                        miDetalleProductos.setCant_infundados(rs.getString("cant_infundados"), 0);
                        miDetalleProductos.setCant_fundados(rs.getString("cant_fundados"), 0);
                        miDetalleProductos.setCant_tema_comercial(rs.getString("cant_tema_comercial"), 0);
                        miDetalleProductos.setId_diseno(rs.getString("id_diseno"), 0);
                        miDetalleProductos.setId_proceso(rs.getString("id_proceso"), 0);
                        miDetalleProductos.setId_falla_mat(rs.getString("id_falla_mat"), 0);
                        miDetalleProductos.setId_provee(rs.getString("id_provee"), 0);
                        miDetalleProductos.setNombre_diseno(rs.getString("dis_nombre"));
                        miDetalleProductos.setNombre_falla_mat(rs.getString("falla_mat_nombre"));
                        miDetalleProductos.setNombre_origen(rs.getString("origen"));
                        miDetalleProductos.setNombre_proceso(rs.getString("proc_nombre"));
                        miDetalleProductos.setNombre_verificacion(rs.getString("tipo"));
                        miDetalleProductos.setNombre_resultado(rs.getString("resultado"));
                        miDetalleProductos.setNombre_provee(rs.getString("prov_nombre"));

                        System.out.println("Detalle producto" + miDetalleProductos);
                        if (i == 0) {
                            reclamoCero.setEncabezado(miEncabezado);
                            reclamoCero.setDetalle(miDetalleProductos);
                            flagReclamoCero = true;
                        } else if (i == 1) {
                            flagReclamoUno = true;
                            reclamoUno.setEncabezado(miEncabezado);
                            reclamoUno.setDetalle(miDetalleProductos);
                        } else if (i == 2) {
                            flagReclamoDos = true;
                            reclamoDos.setEncabezado(miEncabezado);
                            reclamoDos.setDetalle(miDetalleProductos);
                        } else if (i == 3) {
                            flagReclamoTres = true;
                            reclamoTres.setEncabezado(miEncabezado);
                            reclamoTres.setDetalle(miDetalleProductos);
                        } else if (i == 4) {
                            flagReclamoCuatro = true;
                            reclamoCuatro.setEncabezado(miEncabezado);
                            reclamoCuatro.setDetalle(miDetalleProductos);
                        } else if (i == 5) {
                            flagReclamoCinco = true;
                            reclamoCinco.setEncabezado(miEncabezado);
                            reclamoCinco.setDetalle(miDetalleProductos);
                        } else if (i == 6) {
                            flagReclamoSeis = true;
                            reclamoSeis.setEncabezado(miEncabezado);
                            reclamoSeis.setDetalle(miDetalleProductos);
                        } else if (i == 7) {
                            flagReclamo7 = true;
                            reclamo7.setEncabezado(miEncabezado);
                            reclamo7.setDetalle(miDetalleProductos);
                        } else if (i == 8) {
                            flagReclamo8 = true;
                            reclamo8.setEncabezado(miEncabezado);
                            reclamo8.setDetalle(miDetalleProductos);
                        } else if (i == 9) {
                            flagReclamo9 = true;
                            reclamo9.setEncabezado(miEncabezado);
                            reclamo9.setDetalle(miDetalleProductos);
                        } else if (i == 10) {
                            flagReclamo10 = true;
                            reclamo10.setEncabezado(miEncabezado);
                            reclamo10.setDetalle(miDetalleProductos);
                        } else if (i == 11) {
                            flagReclamo11 = true;
                            reclamo11.setEncabezado(miEncabezado);
                            reclamo11.setDetalle(miDetalleProductos);
                        } else if (i == 12) {
                            flagReclamo12 = true;
                            reclamo12.setEncabezado(miEncabezado);
                            reclamo12.setDetalle(miDetalleProductos);
                        } else if (i == 13) {
                            flagReclamo13 = true;
                            reclamo13.setEncabezado(miEncabezado);
                            reclamo13.setDetalle(miDetalleProductos);
                        } else if (i == 14) {
                            flagReclamo14 = true;
                            reclamo14.setEncabezado(miEncabezado);
                            reclamo14.setDetalle(miDetalleProductos);
                        } else if (i == 15) {
                            flagReclamo15 = true;
                            reclamo15.setEncabezado(miEncabezado);
                            reclamo15.setDetalle(miDetalleProductos);
                        } else if (i == 16) {
                            flagReclamo16 = true;
                            reclamo16.setEncabezado(miEncabezado);
                            reclamo16.setDetalle(miDetalleProductos);
                        } else if (i == 17) {
                            flagReclamo17 = true;
                            reclamo17.setEncabezado(miEncabezado);
                            reclamo17.setDetalle(miDetalleProductos);
                        } else if (i == 18) {
                            flagReclamo18 = true;
                            reclamo18.setEncabezado(miEncabezado);
                            reclamo18.setDetalle(miDetalleProductos);
                        } else if (i == 19) {
                            flagReclamo19 = true;
                            reclamo19.setEncabezado(miEncabezado);
                            reclamo19.setDetalle(miDetalleProductos);
                        } else if (i == 20) {
                            flagReclamo20 = true;
                            reclamo20.setEncabezado(miEncabezado);
                            reclamo20.setDetalle(miDetalleProductos);
                        } else {
                            throw new Exception("Se supero el limite de producos por reclamo.");

                        }

//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (c != null) {
                        c.close();
                    }

                }

            }

            System.out.println("reclamoCero" + reclamoCero.getListadoDetalle());
            System.out.println("reclamoUNo" + reclamoUno.getListadoDetalle());

            if (flagReclamoCero) {
                listaReclamos.add(reclamoCero);
            }
            if (flagReclamoUno) {
                listaReclamos.add(reclamoUno);
            }
            if (flagReclamoDos) {
                listaReclamos.add(reclamoDos);
            }
            if (flagReclamoTres) {
                listaReclamos.add(reclamoTres);
            }
            if (flagReclamoCuatro) {
                listaReclamos.add(reclamoCuatro);
            }
            if (flagReclamoCinco) {
                listaReclamos.add(reclamoCinco);
            }
            if (flagReclamoSeis) {
                listaReclamos.add(reclamoSeis);
            }

            if (flagReclamo7) {
                listaReclamos.add(reclamo7);
            }
            if (flagReclamo8) {
                listaReclamos.add(reclamo8);
            }
            if (flagReclamo9) {
                listaReclamos.add(reclamo9);
            }
            if (flagReclamo10) {
                listaReclamos.add(reclamo10);
            }
            if (flagReclamo11) {
                listaReclamos.add(reclamo11);
            }
            if (flagReclamo12) {
                listaReclamos.add(reclamo12);
            }
            if (flagReclamo13) {
                listaReclamos.add(reclamo13);
            }
            if (flagReclamo14) {
                listaReclamos.add(reclamo14);
            }
            if (flagReclamo15) {
                listaReclamos.add(reclamo15);
            }
            if (flagReclamo16) {
                listaReclamos.add(reclamo16);
            }
            if (flagReclamo17) {
                listaReclamos.add(reclamo17);
            }
            if (flagReclamo18) {
                listaReclamos.add(reclamo18);
            }
            if (flagReclamo19) {
                listaReclamos.add(reclamo19);
            }
            if (flagReclamo20) {
                listaReclamos.add(reclamo20);
            }
        } catch (Exception ex) {
            System.out.println("Exeption: " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            if (cReclamoCliente != null) {
                cReclamoCliente.close();
            }

        }
        return listaReclamos;
    }

    public ArrayList<Reclamos> obtenerBusqueda(String idProveedor, String desde, String hasta, String IdSession, String tipo) throws ClassNotFoundException,
            IOException, SQLException {
        String SQL_NUMEROS_RECLAMOS = null;
        String SQL_RECLAMO_ENCABEZADO = "SELECT id_reclamos_encabezado, fecha_emision, fecha_recepcion, reclamos_encabezado.id_cliente, reclamos_encabezado.aprobado, clientes.nombre FROM reclamos_encabezado JOIN clientes ON clientes.id_cliente = reclamos_encabezado.id_cliente WHERE id_reclamos_encabezado = ?";
        String SQL_RECLAMO_DETALLE = "SELECT reclamos_encabezado.id_cliente, reclamos_encabezado.id_reclamos_encabezado, reclamos_encabezado.fecha_emision, reclamos_encabezado.fecha_recepcion, reclamos_detalle.id_reclamos_detalle_item, diseno.dis_nombre, proceso.proc_nombre, falla_materiales.falla_mat_nombre, origenes.origen, productos.codigo_producto, verificaciones.tipo, resultados.resultado, proveedores.prov_nombre, reclamos_detalle.id_producto, reclamos_detalle.id_verificacion, reclamos_detalle.cantidad_total, clientes.nombre, reclamos_detalle.observacion, reclamos_detalle.motivo, reclamos_detalle.lote, reclamos_detalle.cant_infundados, reclamos_detalle.cant_fundados, reclamos_detalle.cant_tema_comercial, reclamos_detalle.id_reclamos_detalle_item FROM reclamos_detalle INNER JOIN reclamos_encabezado ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id JOIN origenes ON reclamos_detalle.id_origen = origenes.id_origen JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id JOIN clientes ON reclamos_encabezado.id_cliente = clientes.id_cliente WHERE reclamos_encabezado.id_reclamos_encabezado = ?";
        String SQL_NUMEROS_RECLAMOS_PROVEEDOR = "SELECT DISTINCT reclamos_detalle.id_reclamos_detalle_item, reclamos_encabezado.fecha_recepcion, reclamos_encabezado.id_reclamos_encabezado FROM reclamos_detalle, reclamos_encabezado WHERE reclamos_detalle.id_provee = ? AND reclamos_detalle.id_reclamos_detalle_item = reclamos_encabezado.id_reclamos_encabezado AND reclamos_encabezado.fecha_recepcion >= ? AND reclamos_encabezado.fecha_recepcion <= ?";
        String SQL_NUMEROS_RECLAMOS_PRODUCTO = "SELECT DISTINCT reclamos_detalle.id_reclamos_detalle_item, reclamos_encabezado.fecha_recepcion, reclamos_encabezado.id_reclamos_encabezado FROM reclamos_detalle, reclamos_encabezado WHERE reclamos_detalle.id_producto = ? AND reclamos_detalle.id_reclamos_detalle_item = reclamos_encabezado.id_reclamos_encabezado AND reclamos_encabezado.fecha_recepcion >= ? AND reclamos_encabezado.fecha_recepcion <= ?";
        String SQL_NUMEROS_RECLAMOS_CLIENTE = "SELECT DISTINCT reclamos_detalle.id_reclamos_detalle_item, reclamos_encabezado.fecha_recepcion, reclamos_encabezado.id_reclamos_encabezado FROM reclamos_detalle, reclamos_encabezado WHERE reclamos_encabezado.id_cliente = ? AND reclamos_detalle.id_reclamos_detalle_item = reclamos_encabezado.id_reclamos_encabezado AND reclamos_encabezado.fecha_recepcion >= ? AND reclamos_encabezado.fecha_recepcion <= ?";
        System.out.println("Tipo de busqueda: " + tipo);
        if (tipo.equals("producto")) {
            SQL_NUMEROS_RECLAMOS = SQL_NUMEROS_RECLAMOS_PRODUCTO;
        } else if (tipo.equals("proveedor")) {
            SQL_NUMEROS_RECLAMOS = SQL_NUMEROS_RECLAMOS_PROVEEDOR;

        } else if (tipo.equals("clientes")) {
            SQL_NUMEROS_RECLAMOS = SQL_NUMEROS_RECLAMOS_CLIENTE;
        }

        ArrayList<String> listadoNumReclamos = new ArrayList();
        ArrayList<Reclamos> listaReclamos = new ArrayList();
        ReclamosEncabezado miEncabezado = null;
        ReclamoDetalle miDetalleProductos = null;

        Connection conReclamosNum = null;
        PreparedStatement ptsmtReclmosNum = null;
        ResultSet rsReclamosNum = null;

        Connection cEncabazado = null;
        PreparedStatement ptsmtEncabezado = null;
        ResultSet rsEncabezado = null;

        Connection cDetalle = null;
        PreparedStatement ptsmtDetalle = null;
        ResultSet rsDetalle = null;

        try {

            try {
                conReclamosNum = DB.getInstance().getConnection();
                ptsmtReclmosNum = conReclamosNum.prepareStatement(SQL_NUMEROS_RECLAMOS);
                ptsmtReclmosNum.setString(1, idProveedor);
                ptsmtReclmosNum.setString(2, desde);
                ptsmtReclmosNum.setString(3, hasta);
                rsReclamosNum = ptsmtReclmosNum.executeQuery();

                while (rsReclamosNum.next()) {
                    listadoNumReclamos.add(rsReclamosNum.getString("id_reclamos_detalle_item"));
                }
                System.out.println("Listado reclamos: " + listadoNumReclamos);
            } finally {
                if (rsReclamosNum != null) {
                    try {
                        rsReclamosNum.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
                if (ptsmtReclmosNum != null) {
                    try {
                        ptsmtReclmosNum.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
                if (conReclamosNum != null) {
                    try {
                        conReclamosNum.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
            }

            for (int i = 0; i < listadoNumReclamos.size(); i++) {
                System.out.println("NUMERO reclamo: " + listadoNumReclamos.get(i));
                try {

                    cEncabazado = DB.getInstance().getConnection();
                    ptsmtEncabezado = cEncabazado.prepareStatement(SQL_RECLAMO_ENCABEZADO);
                    ptsmtEncabezado.setString(1, listadoNumReclamos.get(i));
                    rsEncabezado = ptsmtEncabezado.executeQuery();
                    while (rsEncabezado.next()) {
                        System.out.println("ID encabezado: " + rsEncabezado.getString("id_reclamos_encabezado"));
                        miEncabezado = new ReclamosEncabezado();
                        miEncabezado.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                        miEncabezado.setFecha_recepcion(rsEncabezado.getString("fecha_recepcion"));
                        miEncabezado.setCliente(rsEncabezado.getString("id_cliente"));
                        miEncabezado.setCli_nombre(rsEncabezado.getString("nombre"));
                        miEncabezado.setFecha_emision(rsEncabezado.getString("fecha_emision"));
                        miEncabezado.setAprobado(rsEncabezado.getInt("aprobado"));
                        //              miEncabezado.setLogedUser(MiUsuario.getUsuario(IdSession));
                        Reclamos reclamoCero = new Reclamos();
                        //Ciclo por cada reclamo
                        try {
                            cDetalle = DB.getInstance().getConnection();
                            ptsmtDetalle = cDetalle.prepareStatement(SQL_RECLAMO_DETALLE);
                            ptsmtDetalle.setString(1, rsEncabezado.getString("id_reclamos_encabezado"));
                            rsDetalle = ptsmtDetalle.executeQuery();
                            while (rsDetalle.next()) {
                                System.out.println("ENTRO AL WHILE");
                                miDetalleProductos = new ReclamoDetalle();
                                System.out.println("ID encabezado: " + rsEncabezado.getString("id_reclamos_encabezado") + " Producto: " + rsDetalle.getString("codigo_producto"));
                                miDetalleProductos.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                                miDetalleProductos.setCodigo_producto(rsDetalle.getString("id_producto"), 0);
                                miDetalleProductos.setNombre_producto(rsDetalle.getString("codigo_producto"));
                                miDetalleProductos.setVerificacion(rsDetalle.getString("id_verificacion"), 0);
                                miDetalleProductos.setCantidad_total(rsDetalle.getString("cantidad_total"), 0);
                                miDetalleProductos.setObservacion(rsDetalle.getString("observacion"));
                                miDetalleProductos.setMotivo(rsDetalle.getString("motivo"));
                                miDetalleProductos.setOrigen(rsDetalle.getString("origen"), 0);
                                miDetalleProductos.setLote(rsDetalle.getString("lote"));
                                miDetalleProductos.setCant_infundados(rsDetalle.getString("cant_infundados"), 0);
                                miDetalleProductos.setCant_fundados(rsDetalle.getString("cant_fundados"), 0);
                                miDetalleProductos.setCant_tema_comercial(rsDetalle.getString("cant_tema_comercial"), 0);
                                miDetalleProductos.setNombre_diseno(rsDetalle.getString("dis_nombre"));
                                miDetalleProductos.setNombre_falla_mat(rsDetalle.getString("falla_mat_nombre"));
                                miDetalleProductos.setNombre_origen(rsDetalle.getString("origen"));
                                miDetalleProductos.setNombre_proceso(rsDetalle.getString("proc_nombre"));
                                miDetalleProductos.setNombre_verificacion(rsDetalle.getString("tipo"));
                                miDetalleProductos.setNombre_resultado(rsDetalle.getString("resultado"));
                                miDetalleProductos.setNombre_provee(rsDetalle.getString("prov_nombre"));
                                System.out.println("--Detalle Producto: " + miDetalleProductos);
                                System.out.println("FIN DEL WHILE");
                                reclamoCero.setDetalle(miDetalleProductos);
                                System.out.println("Pasa para DetalleProducto: ");
                            }
                            reclamoCero.setEncabezado(miEncabezado);
                            listaReclamos.add(reclamoCero);
                            System.out.println("RECLAMO: " + reclamoCero);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Exeption: " + ex.getMessage());
                        } finally {
                            if (cDetalle != null) {
                                try {
                                    cDetalle.close();
                                } catch (SQLException e) {
                                    /* ignored */
                                }
                            }

                            if (ptsmtDetalle != null) {
                                try {
                                    ptsmtDetalle.close();
                                } catch (SQLException e) {
                                    /* ignored */
                                }
                            }
                            if (rsDetalle != null) {
                                try {
                                    rsDetalle.close();
                                } catch (SQLException e) {
                                    /* ignored */
                                }
                            }

                        }
                    }
                } finally {
                    if (cEncabazado != null) {
                        try {
                            cEncabazado.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }

                    if (ptsmtEncabezado != null) {
                        try {
                            ptsmtEncabezado.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (rsEncabezado != null) {
                        try {
                            rsEncabezado.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                }
            }// for
        } catch (ClassNotFoundException ex) {
            System.out.println("Alerta: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Alerta: " + ex.getMessage());
        } finally {

           
        }

        return listaReclamos;
    }

    private final static String SQL_RECLAMO_ENCABEZADO_SELECT_FECHAS_PRODUCTO = "SELECT * FROM reclamos_encabezado WHERE fecha_recepcion >= ? AND fecha_recepcion <= ?;";
    private final static String SQL_RECLAMO_ENCABEZADO_SELECT_PRODUCTO = "SELECT id_reclamos_encabezado, fecha_emision, fecha_recepcion, reclamos_encabezado.id_cliente, clientes.nombre, reclamos_encabezado.aprobado FROM reclamos_encabezado JOIN clientes ON clientes.id_cliente = reclamos_encabezado.id_cliente WHERE id_reclamos_encabezado = ?;";
    private final static String SQL_RECLAMO_DETALLE_SELECT_PRODUCTO = "SELECT *, productos.codigo_producto, verificaciones.tipo, resultados.resultado, proveedores.prov_nombre, diseno.dis_nombre, proceso.proc_nombre, falla_materiales.falla_mat_nombre, origenes.origen FROM reclamos_encabezado INNER JOIN reclamos_detalle ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id JOIN origenes ON reclamos_detalle.id_origen = origenes.id_origen WHERE reclamos_encabezado.id_reclamos_encabezado = ? AND reclamos_detalle.id_producto = ?";

    public ArrayList<Reclamos> obtenerProducto(String idProveedor, String desde, String hasta, String IdSession) throws ClassNotFoundException,
            IOException, SQLException {
        Reclamos reclamoCero = new Reclamos();
        Reclamos reclamoUno = new Reclamos();
        Reclamos reclamoDos = new Reclamos();
        Reclamos reclamoTres = new Reclamos();
        Reclamos reclamoCuatro = new Reclamos();
        Reclamos reclamoCinco = new Reclamos();
        Reclamos reclamoSeis = new Reclamos();
        Reclamos reclamoSiete = new Reclamos();
        Reclamos reclamoOcho = new Reclamos();
        ArrayList<Reclamos> listaReclamos = new ArrayList();
        Boolean flagReclamoCero = false;
        Boolean flagReclamoUno = false;
        Boolean flagReclamoDos = false;
        Boolean flagReclamoTres = false;
        Boolean flagReclamoCuatro = false;
        Boolean flagReclamoCinco = false;
        Boolean flagReclamoSeis = false;
        Boolean flagReclamoSiete = false;
        Boolean flagReclamoOcho = false;

        ReclamosEncabezado miEncabezado = null;
        ReclamoDetalle miDetalleProductos = null;

        Connection cReclamoCliente = null;
        PreparedStatement ptsmtReclamoCLiente = null;
        ResultSet rsReclamoCliente = null;

        Connection cEncabazado = null;
        PreparedStatement ptsmtEncabezado = null;
        ResultSet rsEncabezado = null;

        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            //Obtener Id idProveedor
            cReclamoCliente = DB.getInstance().getConnection();
            ptsmtReclamoCLiente = cReclamoCliente.prepareStatement(SQL_RECLAMO_ENCABEZADO_SELECT_FECHAS_PRODUCTO);
            ptsmtReclamoCLiente.setString(1, desde);
            ptsmtReclamoCLiente.setString(2, hasta);
            rsReclamoCliente = ptsmtReclamoCLiente.executeQuery();
            ReclamosOld reclamoIdClient = null;
            ArrayList idReclaDeClienAux = new ArrayList();
            while (rsReclamoCliente.next()) {
                idReclaDeClienAux.add(rsReclamoCliente.getString("id_reclamos_encabezado"));
            }
            System.out.println("Listado de reclamos del clientes: " + idReclaDeClienAux);

            //Ciclo por cada reclamo
            for (int i = 0; i < idReclaDeClienAux.size(); i++) {
                System.out.println("Reclamo num: " + idReclaDeClienAux.get(i));

                //Obtener encabezado
                //
                cEncabazado = DB.getInstance().getConnection();
                ptsmtEncabezado = cEncabazado.prepareStatement(SQL_RECLAMO_ENCABEZADO_SELECT_PRODUCTO);
                System.out.println("Proveedor: " + idProveedor);

                ptsmtEncabezado.setString(1, (String) idReclaDeClienAux.get(i));
                rsEncabezado = ptsmtEncabezado.executeQuery();

                while (rsEncabezado.next()) {
                    miEncabezado = new ReclamosEncabezado();
                    miEncabezado.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                    miEncabezado.setFecha_recepcion(rsEncabezado.getString("fecha_recepcion"));
                    miEncabezado.setCliente(rsEncabezado.getString("id_cliente"));
                    miEncabezado.setCli_nombre(rsEncabezado.getString("nombre"));
                    miEncabezado.setFecha_emision(rsEncabezado.getString("fecha_emision"));
                    miEncabezado.setAprobado(rsEncabezado.getInt("aprobado"));
                    //miEncabezado.setLogedUser(MiUsuario.getUsuario(IdSession));

                    //miEncabezado.add(miEncabezado);
                    //listaProductos.add(miEncabezado);
                    System.out.println("Encabezado: " + rsEncabezado.getString("id_reclamos_encabezado"));
                    System.out.println("Encabezado: " + rsEncabezado.getString("fecha_recepcion"));
                    System.out.println("Encabezado: " + rsEncabezado.getString("id_cliente"));

                }

                //Obtener reclamo detalle
                //
                c = DB.getInstance().getConnection();
                ptsmt = c.prepareStatement(SQL_RECLAMO_DETALLE_SELECT_PRODUCTO);
                ptsmt.setString(1, (String) idReclaDeClienAux.get(i));
                ptsmt.setString(2, idProveedor);
                rs = ptsmt.executeQuery();
                try {

                    while (rs.next()) {

                        miDetalleProductos = new ReclamoDetalle();
                        miDetalleProductos.setId_reclamo(rs.getString("id_reclamos_encabezado"));
                        //miDetalleProductos.setFecha(rs.getString("fecha_emision"));
                        // miDetalleProductos.setCliente(rs.getString("id_cliente"));
                        miDetalleProductos.setCodigo_producto(rs.getString("id_producto"), 0);
                        miDetalleProductos.setNombre_producto(rs.getString("codigo_producto"));
                        miDetalleProductos.setVerificacion(rs.getString("id_verificacion"), 0);
                        miDetalleProductos.setCantidad_total(rs.getString("cantidad_total"), 0);
                        miDetalleProductos.setObservacion(rs.getString("observacion"));
                        miDetalleProductos.setMotivo(rs.getString("motivo"));
                        miDetalleProductos.setOrigen(rs.getString("id_origen"), 0);
                        miDetalleProductos.setLote(rs.getString("lote"));
                        miDetalleProductos.setResultado(rs.getString("id_resultado"), 0);
                        miDetalleProductos.setCant_infundados(rs.getString("cant_infundados"), 0);
                        miDetalleProductos.setCant_fundados(rs.getString("cant_fundados"), 0);
                        miDetalleProductos.setCant_tema_comercial(rs.getString("cant_tema_comercial"), 0);
                        miDetalleProductos.setId_diseno(rs.getString("id_diseno"), 0);
                        miDetalleProductos.setId_proceso(rs.getString("id_proceso"), 0);
                        miDetalleProductos.setId_falla_mat(rs.getString("id_falla_mat"), 0);
                        miDetalleProductos.setId_provee(rs.getString("id_provee"), 0);
                        miDetalleProductos.setNombre_diseno(rs.getString("dis_nombre"));
                        miDetalleProductos.setNombre_falla_mat(rs.getString("falla_mat_nombre"));
                        miDetalleProductos.setNombre_origen(rs.getString("origen"));
                        miDetalleProductos.setNombre_proceso(rs.getString("proc_nombre"));
                        miDetalleProductos.setNombre_verificacion(rs.getString("tipo"));
                        miDetalleProductos.setNombre_resultado(rs.getString("resultado"));
                        miDetalleProductos.setNombre_provee(rs.getString("prov_nombre"));

                        System.out.println("Detalle producto" + miDetalleProductos);
                        if (i == 0) {
                            reclamoCero.setEncabezado(miEncabezado);
                            reclamoCero.setDetalle(miDetalleProductos);
                            flagReclamoCero = true;
                        } else if (i == 1) {
                            flagReclamoUno = true;
                            reclamoUno.setEncabezado(miEncabezado);
                            reclamoUno.setDetalle(miDetalleProductos);
                        } else if (i == 2) {
                            flagReclamoDos = true;
                            reclamoDos.setEncabezado(miEncabezado);
                            reclamoDos.setDetalle(miDetalleProductos);
                        } else if (i == 3) {
                            flagReclamoTres = true;
                            reclamoTres.setEncabezado(miEncabezado);
                            reclamoTres.setDetalle(miDetalleProductos);
                        } else if (i == 4) {
                            flagReclamoCuatro = true;
                            reclamoCuatro.setEncabezado(miEncabezado);
                            reclamoCuatro.setDetalle(miDetalleProductos);
                        } else if (i == 5) {
                            flagReclamoCinco = true;
                            reclamoCinco.setEncabezado(miEncabezado);
                            reclamoCinco.setDetalle(miDetalleProductos);
                        } else if (i == 6) {
                            flagReclamoSeis = true;
                            reclamoSeis.setEncabezado(miEncabezado);
                            reclamoSeis.setDetalle(miDetalleProductos);
                        } else if (i == 7) {
                            flagReclamoSiete = true;
                            reclamoSiete.setEncabezado(miEncabezado);
                            reclamoSiete.setDetalle(miDetalleProductos);
                        } else if (i == 8) {
                            flagReclamoSeis = true;
                            reclamoOcho.setEncabezado(miEncabezado);
                            reclamoOcho.setDetalle(miDetalleProductos);
                        } else {
                            throw new Exception("Se supero el limite de producos por reclamo.");

                        }

//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (c != null) {
                        c.close();
                    }

                }

            }

            System.out.println("reclamoCero" + reclamoCero.getListadoDetalle());
            System.out.println("reclamoUNo" + reclamoUno.getListadoDetalle());

            if (flagReclamoCero) {
                listaReclamos.add(reclamoCero);
            }
            if (flagReclamoUno) {
                listaReclamos.add(reclamoUno);
            }
            if (flagReclamoDos) {
                listaReclamos.add(reclamoDos);
            }
            if (flagReclamoTres) {
                listaReclamos.add(reclamoTres);
            }
            if (flagReclamoCuatro) {
                listaReclamos.add(reclamoCuatro);
            }
            if (flagReclamoCinco) {
                listaReclamos.add(reclamoCinco);
            }
            if (flagReclamoSeis) {
                listaReclamos.add(reclamoSeis);
            }
            if (flagReclamoSiete) {
                listaReclamos.add(reclamoSiete);
            }
            if (flagReclamoOcho) {
                listaReclamos.add(reclamoOcho);
            }
        } catch (Exception ex) {
            System.out.println("Exeption: " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            if (cReclamoCliente != null) {
                cReclamoCliente.close();
            }

        }
        return listaReclamos;
    }

    private final static String SQL_RECLAMO_ENCABEZADO_SELECT_FECHAS = "SELECT * FROM reclamos_encabezado WHERE fecha_recepcion >= ? AND fecha_recepcion <= ?;";
    private final static String SQL_RECLAMO_ENCABEZADO_SELECT_PROVEEDOR = "SELECT id_reclamos_encabezado, fecha_emision, fecha_recepcion, reclamos_encabezado.id_cliente, clientes.nombre, reclamos_encabezado.aprobado FROM reclamos_encabezado JOIN clientes ON clientes.id_cliente = reclamos_encabezado.id_cliente WHERE id_reclamos_encabezado = ?;";
    private final static String SQL_RECLAMO_DETALLE_SELECT_PROVEEDOR = "SELECT *, productos.codigo_producto, verificaciones.tipo, resultados.resultado, proveedores.prov_nombre, diseno.dis_nombre, proceso.proc_nombre, falla_materiales.falla_mat_nombre, origenes.origen FROM reclamos_encabezado INNER JOIN reclamos_detalle ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id JOIN origenes ON reclamos_detalle.id_origen = origenes.id_origen WHERE reclamos_encabezado.id_reclamos_encabezado = ? AND reclamos_detalle.id_provee = ?";
    private final static String SQL_RECLAMO_DETALLE_SELECT_PROVEEDOR_CANT = "SELECT *, productos.codigo_producto, verificaciones.tipo, resultados.resultado, proveedores.prov_nombre, diseno.dis_nombre, proceso.proc_nombre, falla_materiales.falla_mat_nombre, origenes.origen FROM reclamos_encabezado INNER JOIN reclamos_detalle ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id JOIN origenes ON reclamos_detalle.id_origen = origenes.id_origen WHERE reclamos_encabezado.id_reclamos_encabezado = ? AND reclamos_detalle.id_provee = ?";

    public ArrayList<Reclamos> obtenerProveedor(String idProveedor, String desde, String hasta, String IdSession) throws ClassNotFoundException,
            IOException, SQLException {
        Reclamos reclamoCero = new Reclamos();
        Reclamos reclamoUno = new Reclamos();
        Reclamos reclamoDos = new Reclamos();
        Reclamos reclamoTres = new Reclamos();
        Reclamos reclamoCuatro = new Reclamos();
        Reclamos reclamoCinco = new Reclamos();
        Reclamos reclamoSeis = new Reclamos();
        Reclamos reclamoSiete = new Reclamos();
        Reclamos reclamoOcho = new Reclamos();

        ArrayList<Reclamos> listaReclamos = new ArrayList();
        Boolean flagReclamoCero = false;
        Boolean flagReclamoUno = false;
        Boolean flagReclamoDos = false;
        Boolean flagReclamoTres = false;
        Boolean flagReclamoCuatro = false;
        Boolean flagReclamoCinco = false;
        Boolean flagReclamoSeis = false;
        Boolean flagReclamoSiete = false;
        Boolean flagReclamoOcho = false;

        ReclamosEncabezado miEncabezado = null;
        ReclamoDetalle miDetalleProductos = null;

        Connection cReclamoCliente = null;
        PreparedStatement ptsmtReclamoCLiente = null;
        ResultSet rsReclamoCliente = null;

        Connection cEncabazado = null;
        PreparedStatement ptsmtEncabezado = null;
        ResultSet rsEncabezado = null;

        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            //Obtener Id idProveedor
            cReclamoCliente = DB.getInstance().getConnection();
            ptsmtReclamoCLiente = cReclamoCliente.prepareStatement(SQL_RECLAMO_ENCABEZADO_SELECT_FECHAS);
            ptsmtReclamoCLiente.setString(1, desde);
            ptsmtReclamoCLiente.setString(2, hasta);
            rsReclamoCliente = ptsmtReclamoCLiente.executeQuery();
            ReclamosOld reclamoIdClient = null;
            ArrayList idReclaDeClienAux = new ArrayList();
            while (rsReclamoCliente.next()) {
                idReclaDeClienAux.add(rsReclamoCliente.getString("id_reclamos_encabezado"));
            }
            System.out.println("Listado de reclamos del clientes: " + idReclaDeClienAux);

            //Ciclo por cada reclamo
            for (int i = 0; i < idReclaDeClienAux.size(); i++) {
                System.out.println("Reclamo num: " + idReclaDeClienAux.get(i));

                //Obtener encabezado
                //
                cEncabazado = DB.getInstance().getConnection();
                ptsmtEncabezado = cEncabazado.prepareStatement(SQL_RECLAMO_ENCABEZADO_SELECT_PROVEEDOR);
                System.out.println("Proveedor: " + idProveedor);

                ptsmtEncabezado.setString(1, (String) idReclaDeClienAux.get(i));
                rsEncabezado = ptsmtEncabezado.executeQuery();

                while (rsEncabezado.next()) {
                    miEncabezado = new ReclamosEncabezado();
                    miEncabezado.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                    miEncabezado.setFecha_recepcion(rsEncabezado.getString("fecha_recepcion"));
                    miEncabezado.setCliente(rsEncabezado.getString("id_cliente"));
                    miEncabezado.setCli_nombre(rsEncabezado.getString("nombre"));
                    miEncabezado.setFecha_emision(rsEncabezado.getString("fecha_emision"));
                    miEncabezado.setAprobado(rsEncabezado.getInt("aprobado"));
                    //  miEncabezado.setLogedUser(MiUsuario.getUsuario(IdSession));

                    //miEncabezado.add(miEncabezado);
                    //listaProductos.add(miEncabezado);
                    System.out.println("Encabezado: " + rsEncabezado.getString("id_reclamos_encabezado"));
                    System.out.println("Encabezado: " + rsEncabezado.getString("fecha_recepcion"));
                    System.out.println("Encabezado: " + rsEncabezado.getString("id_cliente"));

                }

                //Obtener reclamo detalle
                //
                c = DB.getInstance().getConnection();
                ptsmt = c.prepareStatement(SQL_RECLAMO_DETALLE_SELECT_PROVEEDOR);
                ptsmt.setString(1, (String) idReclaDeClienAux.get(i));
                ptsmt.setString(2, idProveedor);
                rs = ptsmt.executeQuery();
                try {

                    while (rs.next()) {

                        miDetalleProductos = new ReclamoDetalle();
                        miDetalleProductos.setId_reclamo(rs.getString("id_reclamos_encabezado"));
                        //miDetalleProductos.setFecha(rs.getString("fecha_emision"));
                        // miDetalleProductos.setCliente(rs.getString("id_cliente"));
                        miDetalleProductos.setCodigo_producto(rs.getString("id_producto"), 0);
                        miDetalleProductos.setNombre_producto(rs.getString("codigo_producto"));
                        miDetalleProductos.setVerificacion(rs.getString("id_verificacion"), 0);
                        miDetalleProductos.setCantidad_total(rs.getString("cantidad_total"), 0);
                        miDetalleProductos.setObservacion(rs.getString("observacion"));
                        miDetalleProductos.setMotivo(rs.getString("motivo"));
                        miDetalleProductos.setOrigen(rs.getString("id_origen"), 0);
                        miDetalleProductos.setLote(rs.getString("lote"));
                        miDetalleProductos.setResultado(rs.getString("id_resultado"), 0);
                        miDetalleProductos.setCant_infundados(rs.getString("cant_infundados"), 0);
                        miDetalleProductos.setCant_fundados(rs.getString("cant_fundados"), 0);
                        miDetalleProductos.setCant_tema_comercial(rs.getString("cant_tema_comercial"), 0);
                        miDetalleProductos.setId_diseno(rs.getString("id_diseno"), 0);
                        miDetalleProductos.setId_proceso(rs.getString("id_proceso"), 0);
                        miDetalleProductos.setId_falla_mat(rs.getString("id_falla_mat"), 0);
                        miDetalleProductos.setId_provee(rs.getString("id_provee"), 0);
                        miDetalleProductos.setNombre_diseno(rs.getString("dis_nombre"));
                        miDetalleProductos.setNombre_falla_mat(rs.getString("falla_mat_nombre"));
                        miDetalleProductos.setNombre_origen(rs.getString("origen"));
                        miDetalleProductos.setNombre_proceso(rs.getString("proc_nombre"));
                        miDetalleProductos.setNombre_verificacion(rs.getString("tipo"));
                        miDetalleProductos.setNombre_resultado(rs.getString("resultado"));
                        miDetalleProductos.setNombre_provee(rs.getString("prov_nombre"));

                        System.out.println("Detalle producto" + miDetalleProductos);
                        if (i == 0) {
                            reclamoCero.setEncabezado(miEncabezado);
                            reclamoCero.setDetalle(miDetalleProductos);
                            flagReclamoCero = true;
                        } else if (i == 1) {
                            flagReclamoUno = true;
                            reclamoUno.setEncabezado(miEncabezado);
                            reclamoUno.setDetalle(miDetalleProductos);
                        } else if (i == 2) {
                            flagReclamoDos = true;
                            reclamoDos.setEncabezado(miEncabezado);
                            reclamoDos.setDetalle(miDetalleProductos);
                        } else if (i == 3) {
                            flagReclamoTres = true;
                            reclamoTres.setEncabezado(miEncabezado);
                            reclamoTres.setDetalle(miDetalleProductos);
                        } else if (i == 4) {
                            flagReclamoCuatro = true;
                            reclamoCuatro.setEncabezado(miEncabezado);
                            reclamoCuatro.setDetalle(miDetalleProductos);
                        } else if (i == 5) {
                            flagReclamoCinco = true;
                            reclamoCinco.setEncabezado(miEncabezado);
                            reclamoCinco.setDetalle(miDetalleProductos);
                        } else if (i == 6) {
                            flagReclamoSeis = true;
                            reclamoSeis.setEncabezado(miEncabezado);
                            reclamoSeis.setDetalle(miDetalleProductos);
                        } else if (i == 7) {
                            flagReclamoSiete = true;
                            reclamoSiete.setEncabezado(miEncabezado);
                            reclamoSiete.setDetalle(miDetalleProductos);
                        } else if (i == 8) {
                            flagReclamoOcho = true;
                            reclamoOcho.setEncabezado(miEncabezado);
                            reclamoOcho.setDetalle(miDetalleProductos);
                        } else {
                            throw new Exception("Se supero el limite de producos por reclamo.");

                        }

//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (c != null) {
                        c.close();
                    }

                }

            }

            System.out.println("reclamoCero" + reclamoCero.getListadoDetalle());
            System.out.println("reclamoUNo" + reclamoUno.getListadoDetalle());

            if (flagReclamoCero) {
                listaReclamos.add(reclamoCero);
            }
            if (flagReclamoUno) {
                listaReclamos.add(reclamoUno);
            }
            if (flagReclamoDos) {
                listaReclamos.add(reclamoDos);
            }
            if (flagReclamoTres) {
                listaReclamos.add(reclamoTres);
            }
            if (flagReclamoCuatro) {
                listaReclamos.add(reclamoCuatro);
            }
            if (flagReclamoCinco) {
                listaReclamos.add(reclamoCinco);
            }
            if (flagReclamoSeis) {
                listaReclamos.add(reclamoSeis);
            }
            if (flagReclamoSiete) {
                listaReclamos.add(reclamoSiete);
            }
            if (flagReclamoOcho) {
                listaReclamos.add(reclamoOcho);
            }
        } catch (Exception ex) {
            System.out.println("Exeption: " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            if (cReclamoCliente != null) {
                cReclamoCliente.close();
            }

        }
        return listaReclamos;
    }

    private final static String SQL_RECLAMO_ENCABEZADO_SELECT_ALL = "SELECT * FROM reclamos_encabezado ORDER BY id_reclamos_encabezado DESC LIMIT 25";
    private final static String SQL_RECLAMO_ENCABEZADO_ULTIMOS = "SELECT id_reclamos_encabezado, fecha_emision, fecha_recepcion, reclamos_encabezado.id_cliente, reclamos_encabezado.aprobado, clientes.nombre FROM reclamos_encabezado JOIN clientes ON clientes.id_cliente = reclamos_encabezado.id_cliente WHERE id_reclamos_encabezado = ?";
    private final static String SQL_RECLAMO_DETALLE_ULTIMOS = "SELECT *, productos.codigo_producto , verificaciones.tipo, resultados.resultado, proveedores.prov_nombre, diseno.dis_nombre, proceso.proc_nombre, falla_materiales.falla_mat_nombre, origenes.origen from reclamos_encabezado INNER JOIN reclamos_detalle ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id JOIN origenes ON reclamos_detalle.id_origen = origenes.id_origen WHERE reclamos_encabezado.id_reclamos_encabezado = ?";
//private final static String SQL_RECLAMO_DETALLE_ULTIMOS = "SELECT *, productos.codigo_producto, verificaciones.tipo, resultados.resultado, proveedores.prov_nombre FROM reclamos_encabezado INNER JOIN reclamos_detalle ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id WHERE reclamos_detalle.id_reclamos_detalle_item = ?";

    public ArrayList<Reclamos> obtenerUltimos(String IdSession) throws SQLException {
        Reclamos reclamoCero = new Reclamos();
        Reclamos reclamoUno = new Reclamos();
        Reclamos reclamoDos = new Reclamos();
        Reclamos reclamoTres = new Reclamos();
        Reclamos reclamoCuatro = new Reclamos();
        Reclamos reclamoCinco = new Reclamos();
        Reclamos reclamoSeis = new Reclamos();
        Reclamos reclamo7 = new Reclamos();
        Reclamos reclamo8 = new Reclamos();
        Reclamos reclamo9 = new Reclamos();
        Reclamos reclamo10 = new Reclamos();
        Reclamos reclamo11 = new Reclamos();
        Reclamos reclamo12 = new Reclamos();
        Reclamos reclamo13 = new Reclamos();
        Reclamos reclamo14 = new Reclamos();
        Reclamos reclamo15 = new Reclamos();
        Reclamos reclamo16 = new Reclamos();
        Reclamos reclamo17 = new Reclamos();
        Reclamos reclamo18 = new Reclamos();
        Reclamos reclamo19 = new Reclamos();
        Reclamos reclamo20 = new Reclamos();
        Reclamos reclamo21 = new Reclamos();
        Reclamos reclamo22 = new Reclamos();
        Reclamos reclamo23 = new Reclamos();
        Reclamos reclamo24 = new Reclamos();
        Reclamos reclamo25 = new Reclamos();
        Reclamos reclamo26 = new Reclamos();
        Reclamos reclamo27 = new Reclamos();
        Reclamos reclamo28 = new Reclamos();
        Reclamos reclamo29 = new Reclamos();
        Reclamos reclamo30 = new Reclamos();
        Reclamos reclamo31 = new Reclamos();
        Reclamos reclamo32 = new Reclamos();
        Reclamos reclamo33 = new Reclamos();
        Reclamos reclamo34 = new Reclamos();
        Reclamos reclamo35 = new Reclamos();
        Reclamos reclamo36 = new Reclamos();
        Reclamos reclamo37 = new Reclamos();
        Reclamos reclamo38 = new Reclamos();
        Reclamos reclamo39 = new Reclamos();
        Reclamos reclamo40 = new Reclamos();
        
        
        Reclamos reclamo41 = new Reclamos();
        Reclamos reclamo42 = new Reclamos();
        Reclamos reclamo43 = new Reclamos();
        Reclamos reclamo44 = new Reclamos();
        Reclamos reclamo45 = new Reclamos();
        Reclamos reclamo46 = new Reclamos();
        Reclamos reclamo47 = new Reclamos();
        Reclamos reclamo48 = new Reclamos();
        Reclamos reclamo49 = new Reclamos();
        Reclamos reclamo50 = new Reclamos();
        Reclamos reclamo51 = new Reclamos();
        Reclamos reclamo52 = new Reclamos();
        Reclamos reclamo53 = new Reclamos();
        Reclamos reclamo54 = new Reclamos();
        Reclamos reclamo55 = new Reclamos();
        Reclamos reclamo56 = new Reclamos();
        Reclamos reclamo57 = new Reclamos();
        Reclamos reclamo58 = new Reclamos();
        Reclamos reclamo59 = new Reclamos();
        Reclamos reclamo60 = new Reclamos();
        Reclamos reclamo61 = new Reclamos();
        Reclamos reclamo62 = new Reclamos();
        Reclamos reclamo63 = new Reclamos();
        Reclamos reclamo64 = new Reclamos();
        Reclamos reclamo65 = new Reclamos();
        Reclamos reclamo66 = new Reclamos();
        Reclamos reclamo67 = new Reclamos();
        Reclamos reclamo68 = new Reclamos();
        Reclamos reclamo69 = new Reclamos();
        Reclamos reclamo70 = new Reclamos();
        Reclamos reclamo71 = new Reclamos();
        Reclamos reclamo72 = new Reclamos();
        Reclamos reclamo73 = new Reclamos();
        Reclamos reclamo74 = new Reclamos();
        Reclamos reclamo75 = new Reclamos();
        Reclamos reclamo76 = new Reclamos();
        Reclamos reclamo77 = new Reclamos();
        Reclamos reclamo78 = new Reclamos();
        Reclamos reclamo79 = new Reclamos();
        Reclamos reclamo80 = new Reclamos();
        

     

        ArrayList<Reclamos> listaReclamos = new ArrayList();
        Boolean flagReclamoCero = false;
        Boolean flagReclamoUno = false;
        Boolean flagReclamoDos = false;
        Boolean flagReclamoTres = false;
        Boolean flagReclamoCuatro = false;
        Boolean flagReclamoCinco = false;
        Boolean flagReclamoSeis = false;
        Boolean flagReclamo7 = false;
        Boolean flagReclamo8 = false;
        Boolean flagReclamo9 = false;
        Boolean flagReclamo10 = false;
        Boolean flagReclamo11 = false;
        Boolean flagReclamo12 = false;
        Boolean flagReclamo13 = false;
        Boolean flagReclamo14 = false;
        Boolean flagReclamo15 = false;
        Boolean flagReclamo16 = false;
        Boolean flagReclamo17 = false;
        Boolean flagReclamo18 = false;
        Boolean flagReclamo19 = false;
        Boolean flagReclamo20 = false;
        Boolean flagReclamo21 = false;
        Boolean flagReclamo22 = false;
        Boolean flagReclamo23 = false;
        Boolean flagReclamo24 = false;
        Boolean flagReclamo25 = false;
        Boolean flagReclamo26 = false;
        Boolean flagReclamo27 = false;
        Boolean flagReclamo28 = false;
        Boolean flagReclamo29 = false;
        Boolean flagReclamo30 = false;
        Boolean flagReclamo31 = false;
        Boolean flagReclamo32 = false;
        Boolean flagReclamo33 = false;
        Boolean flagReclamo34 = false;
        Boolean flagReclamo35 = false;
        Boolean flagReclamo36 = false;
        Boolean flagReclamo37 = false;
        Boolean flagReclamo38 = false;
        Boolean flagReclamo39 = false;
        Boolean flagReclamo40 = false;
        
        Boolean flagReclamo41 = false;
        Boolean flagReclamo42 = false;
        Boolean flagReclamo43 = false;
        Boolean flagReclamo44 = false;
        Boolean flagReclamo45 = false;
        Boolean flagReclamo46 = false;
        Boolean flagReclamo47 = false;
        Boolean flagReclamo48 = false;
        Boolean flagReclamo49 = false;
        Boolean flagReclamo50 = false;
        Boolean flagReclamo51 = false;
        Boolean flagReclamo52 = false;
        Boolean flagReclamo53 = false;
        Boolean flagReclamo54 = false;
        Boolean flagReclamo55 = false;
        Boolean flagReclamo56 = false;
        Boolean flagReclamo57 = false;
        Boolean flagReclamo58 = false;
        Boolean flagReclamo59 = false;
        Boolean flagReclamo60 = false;
        Boolean flagReclamo61 = false;
        Boolean flagReclamo62 = false;
        Boolean flagReclamo63 = false;
        Boolean flagReclamo64 = false;
        Boolean flagReclamo65 = false;
        Boolean flagReclamo66 = false;
        Boolean flagReclamo67 = false;
        Boolean flagReclamo68 = false;
        Boolean flagReclamo69 = false;
        Boolean flagReclamo70 = false;
        Boolean flagReclamo71 = false;
        Boolean flagReclamo72 = false;
        Boolean flagReclamo73 = false;
        Boolean flagReclamo74 = false;
        Boolean flagReclamo75 = false;
        Boolean flagReclamo76 = false;
        Boolean flagReclamo77 = false;
        Boolean flagReclamo78 = false;
        Boolean flagReclamo79 = false;
        Boolean flagReclamo80 = false;

      

        ReclamosEncabezado miEncabezado = null;
        ReclamoDetalle miDetalleProductos = null;

        Connection cReclamoUltimos = null;
        PreparedStatement ptsmtReclamoUltimo = null;
        ResultSet rsReclamoUltimos = null;

        Connection cEncabazado = null;
        PreparedStatement ptsmtEncabezado = null;
        ResultSet rsEncabezado = null;

        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            //Obtener ultimos Id de reclamos a consultar
            cReclamoUltimos = DB.getInstance().getConnection();
            ptsmtReclamoUltimo = cReclamoUltimos.prepareStatement(SQL_RECLAMO_ENCABEZADO_SELECT_ALL);

            rsReclamoUltimos = ptsmtReclamoUltimo.executeQuery();
            ArrayList idReclamosUltimosAux = new ArrayList();
            while (rsReclamoUltimos.next()) {
                idReclamosUltimosAux.add(rsReclamoUltimos.getString("id_reclamos_encabezado"));
            }
            System.out.println("Listado de los ultimos reclamos: " + idReclamosUltimosAux);

            //Ciclo por cada reclamo
            for (int i = 0; i < idReclamosUltimosAux.size(); i++) {
                System.out.println("Reclamo num: " + idReclamosUltimosAux.get(i));

                try {

                    //Obtener encabezado
                    //
                    cEncabazado = DB.getInstance().getConnection();
                    ptsmtEncabezado = cEncabazado.prepareStatement(SQL_RECLAMO_ENCABEZADO_ULTIMOS);
                    System.out.println("ID reclamos encabezado: " + (String) idReclamosUltimosAux.get(i));

                    ptsmtEncabezado.setString(1, (String) idReclamosUltimosAux.get(i));
                    rsEncabezado = ptsmtEncabezado.executeQuery();
                    System.out.println("rsEncabezado: " + rsEncabezado);

                    while (rsEncabezado.next()) {
                        miEncabezado = new ReclamosEncabezado();
                        miEncabezado.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                        miEncabezado.setFecha_recepcion(rsEncabezado.getString("fecha_recepcion"));
                        miEncabezado.setCliente(rsEncabezado.getString("id_cliente"));
                        miEncabezado.setCli_nombre(rsEncabezado.getString("nombre"));
                        miEncabezado.setFecha_emision(rsEncabezado.getString("fecha_emision"));
                        miEncabezado.setAprobado(rsEncabezado.getInt("aprobado"));
                        miEncabezado.setLogedUser(MiUsuario.getUsuario(IdSession));
                        //miEncabezado.add(miEncabezado);
                        //listaProductos.add(miEncabezado);

                        System.out.println("Encabezado: " + rsEncabezado.getString("id_reclamos_encabezado"));
                        System.out.println("Encabezado: " + rsEncabezado.getString("fecha_recepcion"));
                        System.out.println("APROBADO: " + rsEncabezado.getString("aprobado"));

                    }

                } catch (Exception ex) {
                    System.out.println("Exeption: " + ex.getMessage());
                } finally {
                    if (cEncabazado != null) {
                        cEncabazado.close();
                        System.err.println("CONEXION cEncabazado CERRADA");
                    } else {
                        System.err.println("CONEXION cEncabazado NO CERRADA");
                    }
                }
                //Obtener reclamo detalle
                //
                c = DB.getInstance().getConnection();
                ptsmt = c.prepareStatement(SQL_RECLAMO_DETALLE_ULTIMOS);
                ptsmt.setString(1, (String) idReclamosUltimosAux.get(i));
                rs = ptsmt.executeQuery();

                try {

                    while (rs.next()) {

                        miDetalleProductos = new ReclamoDetalle();
                        miDetalleProductos.setId_reclamo(rs.getString("id_reclamos_encabezado"));
                        //miDetalleProductos.setFecha(rs.getString("fecha_emision"));
                        // miDetalleProductos.setCliente(rs.getString("id_cliente"));
                        miDetalleProductos.setCodigo_producto(rs.getString("id_producto"), 0);
                        miDetalleProductos.setNombre_producto(rs.getString("codigo_producto"));
                        miDetalleProductos.setVerificacion(rs.getString("id_verificacion"), 0);
                        miDetalleProductos.setCantidad_total(rs.getString("cantidad_total"), 0);
                        miDetalleProductos.setObservacion(rs.getString("observacion"));
                        miDetalleProductos.setMotivo(rs.getString("motivo"));
                        miDetalleProductos.setOrigen(rs.getString("id_origen"), 0);
                        miDetalleProductos.setLote(rs.getString("lote"));
                        miDetalleProductos.setResultado(rs.getString("id_resultado"), 0);
                        miDetalleProductos.setCant_infundados(rs.getString("cant_infundados"), 0);
                        miDetalleProductos.setCant_fundados(rs.getString("cant_fundados"), 0);
                        miDetalleProductos.setCant_tema_comercial(rs.getString("cant_tema_comercial"), 0);
                        miDetalleProductos.setId_diseno(rs.getString("id_diseno"), 0);
                        miDetalleProductos.setId_proceso(rs.getString("id_proceso"), 0);
                        miDetalleProductos.setId_falla_mat(rs.getString("id_falla_mat"), 0);
                        miDetalleProductos.setId_provee(rs.getString("id_provee"), 0);
                        miDetalleProductos.setNombre_diseno(rs.getString("dis_nombre"));
                        miDetalleProductos.setNombre_falla_mat(rs.getString("falla_mat_nombre"));
                        miDetalleProductos.setNombre_origen(rs.getString("origen"));
                        miDetalleProductos.setNombre_proceso(rs.getString("proc_nombre"));
                        miDetalleProductos.setNombre_verificacion(rs.getString("tipo"));
                        miDetalleProductos.setNombre_resultado(rs.getString("resultado"));
                        miDetalleProductos.setNombre_provee(rs.getString("prov_nombre"));

                        System.out.println("Detalle producto" + miDetalleProductos);
                        if (i == 0) {
                            reclamoCero.setEncabezado(miEncabezado);
                            reclamoCero.setDetalle(miDetalleProductos);
                            flagReclamoCero = true;
                        } else if (i == 1) {
                            flagReclamoUno = true;
                            reclamoUno.setEncabezado(miEncabezado);
                            reclamoUno.setDetalle(miDetalleProductos);
                        } else if (i == 2) {
                            flagReclamoDos = true;
                            reclamoDos.setEncabezado(miEncabezado);
                            reclamoDos.setDetalle(miDetalleProductos);
                        } else if (i == 3) {
                            flagReclamoTres = true;
                            reclamoTres.setEncabezado(miEncabezado);
                            reclamoTres.setDetalle(miDetalleProductos);
                        } else if (i == 4) {
                            flagReclamoCuatro = true;
                            reclamoCuatro.setEncabezado(miEncabezado);
                            reclamoCuatro.setDetalle(miDetalleProductos);
                        } else if (i == 5) {
                            flagReclamoCinco = true;
                            reclamoCinco.setEncabezado(miEncabezado);
                            reclamoCinco.setDetalle(miDetalleProductos);
                        } else if (i == 6) {
                            flagReclamoSeis = true;
                            reclamoSeis.setEncabezado(miEncabezado);
                            reclamoSeis.setDetalle(miDetalleProductos);
                        } else if (i == 7) {
                            flagReclamo7 = true;
                            reclamo7.setEncabezado(miEncabezado);
                            reclamo7.setDetalle(miDetalleProductos);
                        } else if (i == 8) {
                            flagReclamo8 = true;
                            reclamo8.setEncabezado(miEncabezado);
                            reclamo8.setDetalle(miDetalleProductos);
                        } else if (i == 9) {
                            flagReclamo9 = true;
                            reclamo9.setEncabezado(miEncabezado);
                            reclamo9.setDetalle(miDetalleProductos);
                        } else if (i == 10) {
                            flagReclamo10 = true;
                            reclamo10.setEncabezado(miEncabezado);
                            reclamo10.setDetalle(miDetalleProductos);
                        } else if (i == 11) {
                            flagReclamo11 = true;
                            reclamo11.setEncabezado(miEncabezado);
                            reclamo11.setDetalle(miDetalleProductos);
                        } else if (i == 12) {
                            flagReclamo12 = true;
                            reclamo12.setEncabezado(miEncabezado);
                            reclamo12.setDetalle(miDetalleProductos);
                        } else if (i == 13) {
                            flagReclamo13 = true;
                            reclamo13.setEncabezado(miEncabezado);
                            reclamo13.setDetalle(miDetalleProductos);
                        } else if (i == 14) {
                            flagReclamo14 = true;
                            reclamo14.setEncabezado(miEncabezado);
                            reclamo14.setDetalle(miDetalleProductos);
                        } else if (i == 15) {
                            flagReclamo15 = true;
                            reclamo15.setEncabezado(miEncabezado);
                            reclamo15.setDetalle(miDetalleProductos);
                        } else if (i == 16) {
                            flagReclamo16 = true;
                            reclamo16.setEncabezado(miEncabezado);
                            reclamo16.setDetalle(miDetalleProductos);
                        } else if (i == 17) {
                            flagReclamo17 = true;
                            reclamo17.setEncabezado(miEncabezado);
                            reclamo17.setDetalle(miDetalleProductos);
                        } else if (i == 18) {
                            flagReclamo18 = true;
                            reclamo18.setEncabezado(miEncabezado);
                            reclamo18.setDetalle(miDetalleProductos);
                        } else if (i == 19) {
                            flagReclamo19 = true;
                            reclamo19.setEncabezado(miEncabezado);
                            reclamo19.setDetalle(miDetalleProductos);
                        } else if (i == 20) {
                            flagReclamo20 = true;
                            reclamo20.setEncabezado(miEncabezado);
                            reclamo20.setDetalle(miDetalleProductos);
                        } else if (i == 21) {
                            flagReclamo21 = true;
                            reclamo21.setEncabezado(miEncabezado);
                            reclamo21.setDetalle(miDetalleProductos);
                        } else if (i == 22) {
                            flagReclamo22 = true;
                            reclamo22.setEncabezado(miEncabezado);
                            reclamo22.setDetalle(miDetalleProductos);
                        } else if (i == 23) {
                            flagReclamo23 = true;
                            reclamo23.setEncabezado(miEncabezado);
                            reclamo23.setDetalle(miDetalleProductos);
                        } else if (i == 24) {
                            flagReclamo24 = true;
                            reclamo24.setEncabezado(miEncabezado);
                            reclamo24.setDetalle(miDetalleProductos);
                        } else if (i == 25) {
                            flagReclamo25 = true;
                            reclamo25.setEncabezado(miEncabezado);
                            reclamo25.setDetalle(miDetalleProductos);
                        } else if (i == 26) {
                            flagReclamo26 = true;
                            reclamo26.setEncabezado(miEncabezado);
                            reclamo26.setDetalle(miDetalleProductos);
                        } else if (i == 27) {
                            flagReclamo27 = true;
                            reclamo27.setEncabezado(miEncabezado);
                            reclamo27.setDetalle(miDetalleProductos);
                        } else if (i == 28) {
                            flagReclamo28 = true;
                            reclamo28.setEncabezado(miEncabezado);
                            reclamo28.setDetalle(miDetalleProductos);
                        } else if (i == 29) {
                            flagReclamo29 = true;
                            reclamo29.setEncabezado(miEncabezado);
                            reclamo29.setDetalle(miDetalleProductos);
                        } else if (i == 30) {
                            flagReclamo30 = true;
                            reclamo30.setEncabezado(miEncabezado);
                            reclamo30.setDetalle(miDetalleProductos);
                        } else if (i == 31) {
                            flagReclamo31 = true;
                            reclamo31.setEncabezado(miEncabezado);
                            reclamo31.setDetalle(miDetalleProductos);
                        } else if (i == 32) {
                            flagReclamo32 = true;
                            reclamo32.setEncabezado(miEncabezado);
                            reclamo32.setDetalle(miDetalleProductos);
                        } else if (i == 33) {
                            flagReclamo33 = true;
                            reclamo33.setEncabezado(miEncabezado);
                            reclamo33.setDetalle(miDetalleProductos);
                        } else if (i == 34) {
                            flagReclamo34 = true;
                            reclamo34.setEncabezado(miEncabezado);
                            reclamo34.setDetalle(miDetalleProductos);
                        } else if (i == 35) {
                            flagReclamo35 = true;
                            reclamo35.setEncabezado(miEncabezado);
                            reclamo35.setDetalle(miDetalleProductos);
                        } else if (i == 36) {
                            flagReclamo36 = true;
                            reclamo36.setEncabezado(miEncabezado);
                            reclamo36.setDetalle(miDetalleProductos);
                        } else if (i == 37) {
                            flagReclamo37 = true;
                            reclamo37.setEncabezado(miEncabezado);
                            reclamo37.setDetalle(miDetalleProductos);
                        } else if (i == 38) {
                            flagReclamo38 = true;
                            reclamo38.setEncabezado(miEncabezado);
                            reclamo38.setDetalle(miDetalleProductos);
                        } else if (i == 39) {
                            flagReclamo39 = true;
                            reclamo39.setEncabezado(miEncabezado);
                            reclamo39.setDetalle(miDetalleProductos);
                        } else if (i == 40) {
                            flagReclamo40 = true;
                            reclamo40.setEncabezado(miEncabezado);
                            reclamo40.setDetalle(miDetalleProductos);
                        }else if (i == 41) {
                            flagReclamo41 = true;
                            reclamo41.setEncabezado(miEncabezado);
                            reclamo41.setDetalle(miDetalleProductos);
                        } else if (i == 42) {
                            flagReclamo42 = true;
                            reclamo42.setEncabezado(miEncabezado);
                            reclamo42.setDetalle(miDetalleProductos);
                        } else if (i == 43) {
                            flagReclamo43 = true;
                            reclamo43.setEncabezado(miEncabezado);
                            reclamo43.setDetalle(miDetalleProductos);
                        } else if (i == 44) {
                            flagReclamo44 = true;
                            reclamo44.setEncabezado(miEncabezado);
                            reclamo44.setDetalle(miDetalleProductos);
                        } else if (i == 45) {
                            flagReclamo45 = true;
                            reclamo45.setEncabezado(miEncabezado);
                            reclamo45.setDetalle(miDetalleProductos);
                        } else if (i == 46) {
                            flagReclamo46 = true;
                            reclamo46.setEncabezado(miEncabezado);
                            reclamo46.setDetalle(miDetalleProductos);
                        } else if (i == 47) {
                            flagReclamo47 = true;
                            reclamo47.setEncabezado(miEncabezado);
                            reclamo47.setDetalle(miDetalleProductos);
                        } else if (i == 48) {
                            flagReclamo48 = true;
                            reclamo48.setEncabezado(miEncabezado);
                            reclamo48.setDetalle(miDetalleProductos);
                        } else if (i == 49) {
                            flagReclamo49 = true;
                            reclamo49.setEncabezado(miEncabezado);
                            reclamo49.setDetalle(miDetalleProductos);
                        } else if (i == 50) {
                            flagReclamo50 = true;
                            reclamo50.setEncabezado(miEncabezado);
                            reclamo50.setDetalle(miDetalleProductos);
                        } else if (i == 51) {
                            flagReclamo51 = true;
                            reclamo51.setEncabezado(miEncabezado);
                            reclamo51.setDetalle(miDetalleProductos);
                        } else if (i == 52) {
                            flagReclamo52 = true;
                            reclamo52.setEncabezado(miEncabezado);
                            reclamo52.setDetalle(miDetalleProductos);
                        } else if (i == 53) {
                            flagReclamo53 = true;
                            reclamo53.setEncabezado(miEncabezado);
                            reclamo53.setDetalle(miDetalleProductos);
                        } else if (i == 54) {
                            flagReclamo54 = true;
                            reclamo54.setEncabezado(miEncabezado);
                            reclamo54.setDetalle(miDetalleProductos);
                        } else if (i == 55) {
                            flagReclamo55 = true;
                            reclamo55.setEncabezado(miEncabezado);
                            reclamo55.setDetalle(miDetalleProductos);
                        } else if (i == 56) {
                            flagReclamo56 = true;
                            reclamo56.setEncabezado(miEncabezado);
                            reclamo56.setDetalle(miDetalleProductos);
                        } else if (i == 57) {
                            flagReclamo57 = true;
                            reclamo57.setEncabezado(miEncabezado);
                            reclamo57.setDetalle(miDetalleProductos);
                        } else if (i == 58) {
                            flagReclamo58 = true;
                            reclamo58.setEncabezado(miEncabezado);
                            reclamo58.setDetalle(miDetalleProductos);
                        } else if (i == 59) {
                            flagReclamo59 = true;
                            reclamo59.setEncabezado(miEncabezado);
                            reclamo59.setDetalle(miDetalleProductos);
                        } else if (i == 60) {
                            flagReclamo60 = true;
                            reclamo60.setEncabezado(miEncabezado);
                            reclamo60.setDetalle(miDetalleProductos);
                        } else if (i == 61) {
                            flagReclamo61 = true;
                            reclamo61.setEncabezado(miEncabezado);
                            reclamo61.setDetalle(miDetalleProductos);
                        } else if (i == 62) {
                            flagReclamo62 = true;
                            reclamo62.setEncabezado(miEncabezado);
                            reclamo62.setDetalle(miDetalleProductos);
                        }  else if (i == 63) {
                            flagReclamo63 = true;
                            reclamo63.setEncabezado(miEncabezado);
                            reclamo63.setDetalle(miDetalleProductos);
                        }  else if (i == 64) {
                            flagReclamo64 = true;
                            reclamo64.setEncabezado(miEncabezado);
                            reclamo64.setDetalle(miDetalleProductos);
                        } else if (i == 65) {
                            flagReclamo65 = true;
                            reclamo65.setEncabezado(miEncabezado);
                            reclamo65.setDetalle(miDetalleProductos);
                        } else if (i == 66) {
                            flagReclamo66 = true;
                            reclamo66.setEncabezado(miEncabezado);
                            reclamo66.setDetalle(miDetalleProductos);
                        } else if (i == 67) {
                            flagReclamo67 = true;
                            reclamo67.setEncabezado(miEncabezado);
                            reclamo67.setDetalle(miDetalleProductos);
                        } else if (i == 68) {
                            flagReclamo68 = true;
                            reclamo68.setEncabezado(miEncabezado);
                            reclamo68.setDetalle(miDetalleProductos);
                        } else if (i == 69) {
                            flagReclamo69 = true;
                            reclamo69.setEncabezado(miEncabezado);
                            reclamo69.setDetalle(miDetalleProductos);
                        } else if (i == 70) {
                            flagReclamo70 = true;
                            reclamo70.setEncabezado(miEncabezado);
                            reclamo70.setDetalle(miDetalleProductos);
                        } else if (i == 71) {
                            flagReclamo71 = true;
                            reclamo71.setEncabezado(miEncabezado);
                            reclamo71.setDetalle(miDetalleProductos);
                        } else if (i == 72) {
                            flagReclamo72 = true;
                            reclamo72.setEncabezado(miEncabezado);
                            reclamo72.setDetalle(miDetalleProductos);
                        } else if (i == 73) {
                            flagReclamo73 = true;
                            reclamo73.setEncabezado(miEncabezado);
                            reclamo73.setDetalle(miDetalleProductos);
                        } else if (i == 74) {
                            flagReclamo74 = true;
                            reclamo74.setEncabezado(miEncabezado);
                            reclamo74.setDetalle(miDetalleProductos);
                        } else if (i == 75) {
                            flagReclamo75 = true;
                            reclamo75.setEncabezado(miEncabezado);
                            reclamo75.setDetalle(miDetalleProductos);
                        } else if (i == 76) {
                            flagReclamo76 = true;
                            reclamo76.setEncabezado(miEncabezado);
                            reclamo76.setDetalle(miDetalleProductos);
                        } else if (i == 77) {
                            flagReclamo77 = true;
                            reclamo77.setEncabezado(miEncabezado);
                            reclamo77.setDetalle(miDetalleProductos);
                        } else if (i == 78) {
                            flagReclamo78 = true;
                            reclamo78.setEncabezado(miEncabezado);
                            reclamo78.setDetalle(miDetalleProductos);
                        } else if (i == 79) {
                            flagReclamo79 = true;
                            reclamo79.setEncabezado(miEncabezado);
                            reclamo79.setDetalle(miDetalleProductos);
                        } else if (i == 80) {
                            flagReclamo80 = true;
                            reclamo80.setEncabezado(miEncabezado);
                            reclamo80.setDetalle(miDetalleProductos);
                        }  else {
                            throw new Exception("Se supero el limite de producos por reclamo.");

                        }
                        System.out.println("Encabezado: " + miEncabezado);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (c != null) {
                        c.close();
                        System.err.println("CONEXION c CERRADA");
                    } else {
                        System.err.println("CONEXION c NO CERRADA");
                    }

                }

            }

            System.out.println("reclamoCero" + reclamoCero.getListadoDetalle());
            System.out.println("reclamoUNo" + reclamoUno.getListadoDetalle());

            if (flagReclamoCero) {
                listaReclamos.add(reclamoCero);
            }
            if (flagReclamoUno) {
                listaReclamos.add(reclamoUno);
            }
            if (flagReclamoDos) {
                listaReclamos.add(reclamoDos);
            }
            if (flagReclamoTres) {
                listaReclamos.add(reclamoTres);
            }
            if (flagReclamoCuatro) {
                listaReclamos.add(reclamoCuatro);
            }
            if (flagReclamoCinco) {
                listaReclamos.add(reclamoCinco);
            }
            if (flagReclamoSeis) {
                listaReclamos.add(reclamoSeis);
            }
            if (flagReclamo7) {
                listaReclamos.add(reclamo7);
            }
            if (flagReclamo8) {
                listaReclamos.add(reclamo8);
            }
            if (flagReclamo9) {
                listaReclamos.add(reclamo9);
            }
            if (flagReclamo10) {
                listaReclamos.add(reclamo10);
            }
            if (flagReclamo11) {
                listaReclamos.add(reclamo11);
            }
            if (flagReclamo12) {
                listaReclamos.add(reclamo12);
            }
            if (flagReclamo13) {
                listaReclamos.add(reclamo13);
            }
            if (flagReclamo14) {
                listaReclamos.add(reclamo14);
            }
            if (flagReclamo15) {
                listaReclamos.add(reclamo15);
            }
            if (flagReclamo16) {
                listaReclamos.add(reclamo16);
            }
            if (flagReclamo17) {
                listaReclamos.add(reclamo17);
            }
            if (flagReclamo18) {
                listaReclamos.add(reclamo18);
            }
            if (flagReclamo19) {
                listaReclamos.add(reclamo19);
            }
            if (flagReclamo20) {
                listaReclamos.add(reclamo20);
            }
            if (flagReclamo21) {
                listaReclamos.add(reclamo21);
            }
            if (flagReclamo22) {
                listaReclamos.add(reclamo22);
            }
            if (flagReclamo23) {
                listaReclamos.add(reclamo23);
            }
            if (flagReclamo24) {
                listaReclamos.add(reclamo24);
            }
            if (flagReclamo25) {
                listaReclamos.add(reclamo25);
            }
            if (flagReclamo26) {
                listaReclamos.add(reclamo26);
            }
            if (flagReclamo27) {
                listaReclamos.add(reclamo27);
            }
            if (flagReclamo28) {
                listaReclamos.add(reclamo28);
            }
            if (flagReclamo29) {
                listaReclamos.add(reclamo29);
            }
            if (flagReclamo30) {
                listaReclamos.add(reclamo30);
            }
            if (flagReclamo31) {
                listaReclamos.add(reclamo31);
            }
            if (flagReclamo32) {
                listaReclamos.add(reclamo32);
            }
            if (flagReclamo33) {
                listaReclamos.add(reclamo33);
            }
            if (flagReclamo34) {
                listaReclamos.add(reclamo34);
            }
            if (flagReclamo35) {
                listaReclamos.add(reclamo35);
            }
            if (flagReclamo36) {
                listaReclamos.add(reclamo36);
            }
            if (flagReclamo37) {
                listaReclamos.add(reclamo37);
            }
            if (flagReclamo38) {
                listaReclamos.add(reclamo38);
            }
            if (flagReclamo39) {
                listaReclamos.add(reclamo39);
            }
            if (flagReclamo40) {
                listaReclamos.add(reclamo40);
            }
            if (flagReclamo41) {
                listaReclamos.add(reclamo41);
            }
            if (flagReclamo42) {
                listaReclamos.add(reclamo42);
            }
            if (flagReclamo43) {
                listaReclamos.add(reclamo43);
            }
            if (flagReclamo44) {
                listaReclamos.add(reclamo44);
            }
            if (flagReclamo45) {
                listaReclamos.add(reclamo45);
            }
            if (flagReclamo46) {
                listaReclamos.add(reclamo46);
            }
            if (flagReclamo47) {
                listaReclamos.add(reclamo47);
            }
            if (flagReclamo48) {
                listaReclamos.add(reclamo48);
            }
            if (flagReclamo49) {
                listaReclamos.add(reclamo49);
            }
            if (flagReclamo50) {
                listaReclamos.add(reclamo50);
            }
            if (flagReclamo51) {
                listaReclamos.add(reclamo51);
            }
            if (flagReclamo52) {
                listaReclamos.add(reclamo52);
            }
            if (flagReclamo53) {
                listaReclamos.add(reclamo53);
            }
            if (flagReclamo54) {
                listaReclamos.add(reclamo54);
            }
            if (flagReclamo55) {
                listaReclamos.add(reclamo55);
            }
            if (flagReclamo56) {
                listaReclamos.add(reclamo56);
            }
            if (flagReclamo57) {
                listaReclamos.add(reclamo57);
            }
            if (flagReclamo58) {
                listaReclamos.add(reclamo58);
            }
            if (flagReclamo59) {
                listaReclamos.add(reclamo59);
            }
            if (flagReclamo60) {
                listaReclamos.add(reclamo60);
            }
            if (flagReclamo61) {
                listaReclamos.add(reclamo61);
            }
            if (flagReclamo62) {
                listaReclamos.add(reclamo62);
            }
            if (flagReclamo63) {
                listaReclamos.add(reclamo63);
            }
            if (flagReclamo64) {
                listaReclamos.add(reclamo64);
            }
            if (flagReclamo65) {
                listaReclamos.add(reclamo65);
            }
            if (flagReclamo66) {
                listaReclamos.add(reclamo66);
            }
            if (flagReclamo67) {
                listaReclamos.add(reclamo67);
            }
            if (flagReclamo68) {
                listaReclamos.add(reclamo68);
            }
            if (flagReclamo69) {
                listaReclamos.add(reclamo69);
            }
            if (flagReclamo70) {
                listaReclamos.add(reclamo70);
            }
            if (flagReclamo71) {
                listaReclamos.add(reclamo71);
            }
            if (flagReclamo72) {
                listaReclamos.add(reclamo72);
            }
            if (flagReclamo73) {
                listaReclamos.add(reclamo73);
            }
            if (flagReclamo74) {
                listaReclamos.add(reclamo74);
            }
            if (flagReclamo75) {
                listaReclamos.add(reclamo75);
            }
            if (flagReclamo76) {
                listaReclamos.add(reclamo76);
            }
            if (flagReclamo77) {
                listaReclamos.add(reclamo77);
            }
            if (flagReclamo78) {
                listaReclamos.add(reclamo78);
            }
            if (flagReclamo79) {
                listaReclamos.add(reclamo79);
            }
            if (flagReclamo80) {
                listaReclamos.add(reclamo80);
            }

        } catch (Exception ex) {
            System.out.println("Exeption: " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            if (cReclamoUltimos != null) {
                System.err.println("CONEXION cReclamoUltimos CERRADA");
                cReclamoUltimos.close();
            } else {
                System.err.println("CONEXION cReclamoUltimos NO CERRADA");
            }

        }
        return listaReclamos;
    }

    private final static String SQL_RECLAMO_ENCABEZADO_ULTIMOS2 = "SELECT id_reclamos_encabezado, fecha_emision, fecha_recepcion, reclamos_encabezado.id_cliente, reclamos_encabezado.aprobado, clientes.nombre, id_user, user_nombre, user_firma FROM reclamos_encabezado JOIN clientes ON clientes.id_cliente = reclamos_encabezado.id_cliente JOIN usuarios ON id_user = usuarios.user_id ORDER BY id_reclamos_encabezado DESC LIMIT 15";
    private final static String SQL_RECLAMO_DETALLE_ULTIMOS2 = "SELECT reclamos_encabezado.id_cliente, reclamos_encabezado.id_reclamos_encabezado, reclamos_encabezado.fecha_emision, reclamos_encabezado.fecha_recepcion, reclamos_detalle.id_reclamos_detalle_item, diseno.dis_nombre, proceso.proc_nombre, falla_materiales.falla_mat_nombre, origenes.origen, productos.codigo_producto, verificaciones.tipo, resultados.resultado, proveedores.prov_nombre, reclamos_detalle.id_producto, reclamos_detalle.id_verificacion, reclamos_detalle.cantidad_total, clientes.nombre, reclamos_detalle.observacion, reclamos_detalle.motivo, reclamos_detalle.lote, reclamos_detalle.cant_infundados, reclamos_detalle.cant_fundados, reclamos_detalle.cant_tema_comercial FROM reclamos_detalle INNER JOIN reclamos_encabezado ON reclamos_encabezado.id_reclamos_encabezado = reclamos_detalle.id_reclamos_detalle_item JOIN diseno ON reclamos_detalle.id_diseno = diseno.dis_id JOIN proceso ON reclamos_detalle.id_proceso = proceso.proc_id JOIN falla_materiales ON reclamos_detalle.id_falla_mat = falla_materiales.falla_mat_id JOIN origenes ON reclamos_detalle.id_origen = origenes.id_origen JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN verificaciones ON reclamos_detalle.id_verificacion = verificaciones.id JOIN resultados ON reclamos_detalle.id_resultado = resultados.id_resultado JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id JOIN clientes ON reclamos_encabezado.id_cliente = clientes.id_cliente WHERE reclamos_encabezado.id_reclamos_encabezado = ?";

    public ArrayList<Reclamos> obtenerUltimos2(String IdSession) throws SQLException {
        ArrayList<Reclamos> listaReclamos = new ArrayList();
        ReclamosEncabezado miEncabezado = null;
        ReclamoDetalle miDetalleProductos = null;
        ArrayList<ReclamoDetalle> miDetalleProductosListado = null;

        Connection cEncabazado = null;
        PreparedStatement ptsmtEncabezado = null;
        ResultSet rsEncabezado = null;

        Connection cDetalle = null;
        PreparedStatement ptsmtDetalle = null;
        ResultSet rsDetalle = null;

        try {
            cEncabazado = DB.getInstance().getConnection();
            ptsmtEncabezado = cEncabazado.prepareStatement(SQL_RECLAMO_ENCABEZADO_ULTIMOS2);
            rsEncabezado = ptsmtEncabezado.executeQuery();
            while (rsEncabezado.next()) {
                System.out.println("ID encabezado: " + rsEncabezado.getString("id_reclamos_encabezado"));
                // System.err.println("Reclamo" + rsDetalle.getString("id_reclamos_encabezado") + " " + rsDetalle.getString("fecha_emision") + rsDetalle.getString("codigo_producto"));
                miEncabezado = new ReclamosEncabezado();
                miEncabezado.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                miEncabezado.setFecha_recepcion(rsEncabezado.getString("fecha_recepcion"));
                miEncabezado.setCliente(rsEncabezado.getString("id_cliente"));
                miEncabezado.setCli_nombre(rsEncabezado.getString("nombre"));
                miEncabezado.setFecha_emision(rsEncabezado.getString("fecha_emision"));
                miEncabezado.setAprobado(rsEncabezado.getInt("aprobado"));
                miEncabezado.setLogedUser(MiUsuario.getUsuario(IdSession));
                miEncabezado.setUserOwn(rsEncabezado.getString("id_user"));
                miEncabezado.setUserName(rsEncabezado.getString("user_nombre"));
                miEncabezado.setUserFirma(rsEncabezado.getString("user_firma"));
                //              miEncabezado.setLogedUser(MiUsuario.getUsuario(IdSession));
//
////miEncabezado.add(miEncabezado);
//                //listaProductos.add(miEncabezado);
//                System.out.println("Encabezado: " + rsEncabezado.getString("id_reclamos_encabezado"));
//                System.out.println("Encabezado: " + rsEncabezado.getString("fecha_recepcion"));
//                // System.out.println("APROBADO: " + rsEncabezado.getString("aprobado"));
                Reclamos reclamoCero = new Reclamos();
                //Ciclo por cada reclamo
                try {
                    cDetalle = DB.getInstance().getConnection();
                    ptsmtDetalle = cDetalle.prepareStatement(SQL_RECLAMO_DETALLE_ULTIMOS2);
                    ptsmtDetalle.setString(1, rsEncabezado.getString("id_reclamos_encabezado"));

                    rsDetalle = ptsmtDetalle.executeQuery();
                    //System.out.println("rsEncabezado: " + rsEncabezado);

                    while (rsDetalle.next()) {
                        System.out.println("ENTRO AL WHILE");
                        miDetalleProductos = new ReclamoDetalle();
                        System.out.println("ID encabezado: " + rsEncabezado.getString("id_reclamos_encabezado") + " Producto: " + rsDetalle.getString("codigo_producto"));

//
                        miDetalleProductos.setId_reclamo(rsEncabezado.getString("id_reclamos_encabezado"));
                        //miDetalleProductos.setFecha(rs.getString("fecha_emision"));
                        //miDetalleProductos.setCliente(rsDetalle.getString("id_cliente"));
                        miDetalleProductos.setCodigo_producto(rsDetalle.getString("id_producto"), 0);
                        miDetalleProductos.setNombre_producto(rsDetalle.getString("codigo_producto"));
                        miDetalleProductos.setVerificacion(rsDetalle.getString("id_verificacion"), 0);
                        miDetalleProductos.setCantidad_total(rsDetalle.getString("cantidad_total"), 0);
                        miDetalleProductos.setObservacion(rsDetalle.getString("observacion"));
                        miDetalleProductos.setMotivo(rsDetalle.getString("motivo"));
                        miDetalleProductos.setOrigen(rsDetalle.getString("origen"), 0);
                        miDetalleProductos.setLote(rsDetalle.getString("lote"));
//                miDetalleProductos.setResultado(rsDetalle.getString("id_resultado"), 0);
                        miDetalleProductos.setCant_infundados(rsDetalle.getString("cant_infundados"), 0);
                        miDetalleProductos.setCant_fundados(rsDetalle.getString("cant_fundados"), 0);
                        miDetalleProductos.setCant_tema_comercial(rsDetalle.getString("cant_tema_comercial"), 0);
//                miDetalleProductos.setId_diseno(rsEncabezado.getString("id_diseno"), 0);
//                miDetalleProductos.setId_proceso(rsEncabezado.getString("id_proceso"), 0);
//                miDetalleProductos.setId_falla_mat(rsEncabezado.getString("id_falla_mat"), 0);
//                miDetalleProductos.setId_provee(rsEncabezado.getString("id_provee"), 0);
                        miDetalleProductos.setNombre_diseno(rsDetalle.getString("dis_nombre"));
                        miDetalleProductos.setNombre_falla_mat(rsDetalle.getString("falla_mat_nombre"));
                        miDetalleProductos.setNombre_origen(rsDetalle.getString("origen"));
                        miDetalleProductos.setNombre_proceso(rsDetalle.getString("proc_nombre"));
                        miDetalleProductos.setNombre_verificacion(rsDetalle.getString("tipo"));
                        miDetalleProductos.setNombre_resultado(rsDetalle.getString("resultado"));
                        miDetalleProductos.setNombre_provee(rsDetalle.getString("prov_nombre"));
//
//                System.out.println("Detalle producto" + miDetalleProductos);
//
//                System.out.println("Encabezado: " + miEncabezado);

                        //System.out.println("Encabezado: " + miEncabezado);
                        System.out.println("--Detalle Producto: " + miDetalleProductos);
                        System.out.println("FIN DEL WHILE");
                        reclamoCero.setDetalle(miDetalleProductos);
                        System.out.println("Pasa para DetalleProducto: ");
                        //miDetalleProductosListado.add(miDetalleProductos);
                        //reclamoCero.setListadoDetalle(listaReclamos);
                    }

                    reclamoCero.setEncabezado(miEncabezado);
                    //reclamoCero.setListadoDetalle(miDetalleProductosListado);

                    listaReclamos.add(reclamoCero);

                } catch (Exception ex) {
                    System.out.println("Exeption: " + ex.getMessage());
                } finally {
                    cDetalle.close();

                }

            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Alerta: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Alerta: " + ex.getMessage());
        } finally {
            cEncabazado.close();
            if (cDetalle != null) {
                //cDetalle.close();
                System.err.println("CONEXION cDetalle CERRADA");
            } else {
                System.err.println("CONEXION cDetalle NO CERRADA");
            }
        }

        return listaReclamos;
    }
}
