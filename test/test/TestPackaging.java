package test;

import ingresos.entidades.Packaging;

public class TestPackaging {
    public static void main(String[] args) throws Exception {
        System.out.println("[...] TestPackaging");
        
        Packaging miPck1 = new Packaging("1","2","1","2","3","1");
        System.out.println("Mi packaging: " + miPck1);
        
        Packaging miPck2 = new Packaging();
        miPck2.setAplicaciones("2");
        System.out.println("Packaging: " + miPck2);
        
        System.out.println("[OK] TestPackaging");
    }
}
