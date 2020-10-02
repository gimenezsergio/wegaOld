package persistencia;

import entidades.DB;
import entidades.InformesClienteParticulares;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InformeClienteParticularDao {

    private InformeClienteParticularDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static InformeClienteParticularDao INSTANCE = null;

    public static InformeClienteParticularDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new InformeClienteParticularDao();
        }
        return INSTANCE;
    }

    private final static String SQL_INFORME_INSERT = "INSERT INTO infPartClient (inf_par_Cli_cliente, inf_par_Cli_motivo, inf_par_Cli_fecha, inf_par_Cli_producto, inf_par_Cli_cantidad, inf_par_Cli_lote, inf_par_Cli_visual, inf_par_Cli_obs, inf_par_Cli_analisis_ensayos, inf_par_Cli_instrumentos, inf_par_Cli_conclusiones) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final static String SQL_INFORME_FOTOS = "INSERT INTO fotosInfoPartReclamos(fotos_reclamo_foto, fotos_reclamo_idInfTecnico) VALUES (?,?)";

    public static void insertar(InformesClienteParticulares parametroInforme) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Connection cFoto = null;
        PreparedStatement psmtFoto = null;
        int newId = 0;
        try {
            c = DB.getInstance().getConnection();
            psmt = c.prepareStatement(SQL_INFORME_INSERT, psmt.RETURN_GENERATED_KEYS);
            //System.out.println("Nombre producto: " + parametroInforme.getCodigo_producto());
            psmt.setString(1, parametroInforme.getCliente());
            psmt.setString(2, parametroInforme.getMotivo());
            psmt.setString(3, parametroInforme.getFecha());
            psmt.setString(4, parametroInforme.getProducto());
            psmt.setString(5, parametroInforme.getCantidad());
            psmt.setString(6, parametroInforme.getLote());
            psmt.setString(7, parametroInforme.getVisual());
            psmt.setString(8, parametroInforme.getObservaciones());
            psmt.setString(9, parametroInforme.getAnalisis_ensayos());
            psmt.setString(10, parametroInforme.getInst_medios_control());
            psmt.setString(11, parametroInforme.getConclusiones());
            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
            System.out.println("Nuevo ID es: " + newId);

            for (int i = 0; i < parametroInforme.getFotos().size(); i++) {
                try {
                    System.out.println("FOTO " + i + ": " + parametroInforme.getFotos().get(i));
                    cFoto = DB.getInstance().getConnection();
                    psmtFoto = cFoto.prepareStatement(SQL_INFORME_FOTOS);
                    psmtFoto.setString(1, parametroInforme.getFotos().get(i));
                    psmtFoto.setInt(2, newId);
                    psmtFoto.execute();
                    System.out.println("Fin de for");
                } catch (SQLException ex) {
                    System.out.println("exeption: " + ex.getMessage());

                } finally {
                    if (psmtFoto != null) {
                        try {
                            psmtFoto.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                    if (cFoto != null) {
                        try {
                            cFoto.close();
                        } catch (SQLException e) {
                            /* ignored */
                        }
                    }
                }

            }

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
    }

    private final static String SQL_INFORME_TECNICO_ULTIMOS = "SELECT *, clientes.nombre, productos.codigo_producto FROM infPartClient JOIN clientes ON clientes.id_cliente = infPartClient.inf_par_Cli_cliente JOIN productos ON productos.id_producto = infPartClient.inf_par_Cli_producto ORDER BY inf_par_Cli_codigo_id DESC LIMIT 10;";
//private final static String SQL_INFORME_TECNICO_ULTIMOS = "SELECT *, clientes.nombre, productos.codigo_producto, fotosInfoPartReclamos.fotos_reclamo_foto FROM infPartClient JOIN clientes ON clientes.id_cliente = infPartClient.inf_par_Cli_cliente JOIN productos ON productos.id_producto = infPartClient.inf_par_Cli_producto JOIN fotosInfoPartReclamos ON fotosInfoPartReclamos.fotos_reclamo_idInfTecnico = infPartClient.inf_par_Cli_codigo_id ORDER BY inf_par_Cli_codigo_id DESC LIMIT 10;";
    private final static String SQL_FOTOS = "SELECT fotos_reclamo_foto FROM fotosInfoPartReclamos WHERE fotos_reclamo_idInfTecnico = ?";

    public ArrayList<InformesClienteParticulares> obtenerUltimos() throws ClassNotFoundException, IOException, SQLException {
        ArrayList<InformesClienteParticulares> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        
        Connection cFoto = null;
        PreparedStatement psmtFoto = null;
        ResultSet rsFoto = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_INFORME_TECNICO_ULTIMOS);
            rs = ptsmt.executeQuery();
            InformesClienteParticulares a = null;
            while (rs.next()) {
                try {
                    ArrayList<String> listadoFotos = new ArrayList();
                    a = new InformesClienteParticulares();
                    a.setAnalisis_ensayos(rs.getString("inf_par_Cli_analisis_ensayos"));
                    a.setCantidad(rs.getString("inf_par_Cli_cantidad"));
                    a.setCliente(rs.getString("nombre"));
                    a.setCodigo(rs.getString("inf_par_Cli_codigo_id"));
                    a.setConclusiones(rs.getString("inf_par_Cli_conclusiones"));
                    a.setFecha(rs.getString("inf_par_Cli_fecha"));
                    a.setInst_medios_control(rs.getString("inf_par_Cli_instrumentos"));
                    a.setLote(rs.getString("inf_par_Cli_lote"));
                    a.setMotivo(rs.getString("inf_par_Cli_motivo"));
                    a.setObservaciones(rs.getString("inf_par_Cli_obs"));
                    a.setProducto(rs.getString("codigo_producto"));
                    a.setVisual(rs.getString("inf_par_Cli_visual"));
                    try {
                        cFoto = DB.getInstance().getConnection();
                        psmtFoto = cFoto.prepareStatement(SQL_FOTOS);
                        System.out.println("id de informe: " + rs.getString("inf_par_Cli_codigo_id"));
                        psmtFoto.setString(1, rs.getString("inf_par_Cli_codigo_id"));
                        rsFoto = psmtFoto.executeQuery();
                        while (rsFoto.next()) {
                            //listadoFotos.add(rsFoto.getString("fotos_reclamo_foto"));
                            //System.out.println("foto: " + rsFoto.getString("fotos_reclamo_foto"));
                            //System.out.println("una foto: " + rsFoto.getString("fotos_reclamo_foto"));
                            listadoFotos.add(rsFoto.getString("fotos_reclamo_foto"));
                            System.out.println("Dentro del while");
                        }

                        a.setFotos(listadoFotos);
                        System.out.println("listado de fotos: " +  listadoFotos);
                    } catch (Exception ex) {
                        System.out.println("Exeption: " + ex.getMessage());
                    } finally {
                        if (rsFoto != null) {
                            try {
                                rsFoto.close();
                            } catch (SQLException e) {
                                /* ignored */
                            }
                        }
                        if (psmtFoto != null) {
                            try {
                                psmtFoto.close();
                            } catch (SQLException e) {
                                /* ignored */
                            }
                        }
                        if (cFoto != null) {
                            try {
                                cFoto.close();
                            } catch (SQLException e) {
                                /* ignored */
                            }
                        }

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
