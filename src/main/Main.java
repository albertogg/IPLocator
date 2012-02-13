/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.webservicex.GeoIP;

/**
 *
 * @author albertogg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
                // Interfaz

        System.out.println("    Write quit to exit. ");
        System.out.println("IP            Codigo_Pais  Nombre_Pais  Codigo_Regresado");
        System.err.println("--------------------------------------------------------");
        String message = "";
        Boolean flag = true;
        while (flag == true) {

            // Lectura de la entrada estandar del teclado/archivo de texto.

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));


            // Ciclamos en la entrada estandar
            while ((message = stdin.readLine()) != null) {
                if (message.equals("quit")) {
                    break;

                } else {

                    // Llamada al metodo paso de ip, el cual imprime la informacion
                    // del ip.

                    pasoDeIP(message);
                }
            }
            break;

        }
    }

    // Metodo implementado del webservice, arroja la informacion del ip que
    // se le pasa por teclado o por archivo de texto.
    public static void pasoDeIP(String ip) {

        if (getGeoIP(ip).getReturnCode() != 0) {
            
            System.out.println(getGeoIP(ip).getIP().toString()
                    + "    " + getGeoIP(ip).getCountryCode().toString()
                    + "        " + getGeoIP(ip).getCountryName().toString()
                    + "      " + getGeoIP(ip).getReturnCodeDetails().toString()
                    + "(" + getGeoIP(ip).getReturnCode() + ")");
        } else {

            System.out.println(getGeoIP(ip).getIP().toString()
                    + "    " + "     null      " + "      null    " 
                    + getGeoIP(ip).getReturnCodeDetails().toString()
                    + "(" + getGeoIP(ip).getReturnCode() + ")");

        }
    }


    // Metodo del webservice.
    private static GeoIP getGeoIP(java.lang.String ipAddress) {
        net.webservicex.GeoIPService service = new net.webservicex.GeoIPService();
        net.webservicex.GeoIPServiceSoap port = service.getGeoIPServiceSoap12();
        return port.getGeoIP(ipAddress);
    }
}
