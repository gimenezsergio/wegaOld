package test;


import entidades.DB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDB {

    public static void main(String[] args) {
        System.out.println("Test DB: [...]");

        //Connection miConexion;
        Connection laConexion;

        try {
            System.out.println("Conexion exitosa: [...]");
            laConexion = DB.getInstance().getConnection();
            System.out.println("Conexion exitosa: [OK]");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: ClassNotFoundException" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: IOException" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: SQLException" + ex.getMessage());
        }

        //Comprobar consulta a la base
        try {
            System.out.println("Recuperando datos de la base: [...]");
            Connection laConexion2 = DB.getInstance().getConnection();
            PreparedStatement consulta = laConexion2.prepareStatement("SELECT * FROM accesos");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                System.out.println("Dato recuperado: " + rs.getString(1));
            }
            System.out.println("Recuperando datos de la base: [OK]");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: ClassNotFoundException" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: IOException" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: SQLException" + ex.getMessage());
        }

        System.out.println("Test DB: [OK]");
    }
}
