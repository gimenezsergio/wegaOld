/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author educacion
 */
public class Origenes {
    
    private String id_origen;
    private String origen;

    /**
     * @return the id_origen
     */
    public String getId_origen() {
        return id_origen;
    }

    /**
     * @param id_origen the id_origen to set
     */
    public void setId_origen(String id_origen) {
        this.id_origen = id_origen;
    }

    /**
     * @return the origenes
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origenes to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

     @Override
    public String toString() {
        return "origenes: " + origen ;
    }

}
