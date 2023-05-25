package com.tarea2;
import java.util.Scanner;
import java.io.IOException;

public class RabbitConsumerMain {
 
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Numero de dispositivos: ");
            int numDispositivo = scan.nextInt();
            RabbitConsumer[] device = new RabbitConsumer[numDispositivo];
            
            for (int i = 0; i < numDispositivo; i++) {
                String nombre = "Receptor" + i;
                device[i] = new RabbitConsumer(nombre);
                device[i].start();
                device[i].numt(i);
            }
            for (int i = 0; i < numDispositivo; i++) {
                device[i].join();
               
            }         
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}