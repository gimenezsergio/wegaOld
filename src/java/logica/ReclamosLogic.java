package logica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.*;
import persistencia.ReclamoDao;

public class ReclamosLogic {

    public static void alta(Map parametros) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("CLIE: " + parametros.get("encabezado"));
        System.out.println("PROD: " + parametros.get("productos"));
        Iterator objIterator = ((List) parametros.get("productos")).iterator();
        while (objIterator.hasNext()) {
            Map elproducto = (Map) objIterator.next();
            Iterator claves;
            claves = elproducto.keySet().iterator();
            while (claves.hasNext()) {
                Object unaclave = claves.next();
                Object unvalor = elproducto.get(unaclave);

            }
        }
        try {
            ReclamoDao.insertar(parametros);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
