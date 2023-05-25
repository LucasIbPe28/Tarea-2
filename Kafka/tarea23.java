package tarea2;
import java.util.*;
import java.io.IOException;

public class tarea23 
{
 
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Numero de dispositivos: ");
        int numDispositivo = scan.nextInt();
        dispkfk[] device = new dispkfk[numDispositivo];
        Scanner scanner = new Scanner(System.in);
            System.out.println("Tiempo entre cada mensaje del dispositivo  en milisegundos");
            int tiempo = scanner.nextInt();
        for(int i=0;i<numDispositivo;i++){
            String nombre = "device"+i;
            device[i] = new dispkfk(nombre);
        
            device[i].esperaMensaje(tiempo);           
        }
        for(int i=0;i<numDispositivo;i++){
            device[i].start();
        }
        








      
}
}
