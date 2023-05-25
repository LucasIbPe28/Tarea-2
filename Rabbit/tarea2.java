package com.tarea2;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import javax.print.DocFlavor.STRING;

public class tarea2 
{
 
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Numero de dispositivos: ");
        int numDispositivo = scan.nextInt();
        disp[] device = new disp[numDispositivo];
        for(int i=0;i<numDispositivo;i++){
            String nombre = "device"+i;
            device[i] = new disp(nombre);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Tiempo entre cada mensaje del dispositivo "+i+" en milisegundos");
            int tiempo = scanner.nextInt();
            device[i].esperaMensaje(tiempo);   
            //System.out.println("Indicar tipo de dispositivo (celular, tablet, TV, camara, computador)");//PARA EL PUNTO 5
            //String tipo = scanner.next(); //PARA EL PUNTO 5
            //device[i].categoria = tipo;  //PARA EL PUNTO 5      
        }
        for(int i=0;i<numDispositivo;i++){
            device[i].start();
        }

}
}
