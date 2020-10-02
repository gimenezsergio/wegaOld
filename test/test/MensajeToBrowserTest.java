package test;

import entidades.MensajeBrowser;

public class MensajeToBrowserTest {
    public static void main(String[] args) {
        System.out.println("[...] MensajeToBrowserTest");
        MensajeBrowser mensajeReclamo = new MensajeBrowser("Este es el titulo", "Este es el mensaje largo", "warning");
        System.out.println(mensajeReclamo);
        System.out.println("[OK] MensajeToBrowserTest");
    }
}
