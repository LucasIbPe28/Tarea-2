package tarea2;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.*;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.text.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class dispkfk extends Thread {
    JsonObject dato = new JsonObject();
    int esperaentremensajes;

    public dispkfk(String nombreDispositivo){
        super(nombreDispositivo);
    }
    @Override
    public void run(){
    while(true){


    String letras = "abcdefghijklmnopqrstuvwxyz123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String palabra = "";
    String palabraprueba= "";
    Random random = new Random();
    int largorandom = random.nextInt(21);
    for(int i=0;i<largorandom;i++){
        Random random1 = new Random();
        int aleatorio = random1.nextInt(letras.length());
        palabra += letras.charAt(aleatorio);
        palabraprueba = palabra;
    }
    Hashtable hash = new Hashtable<>();
    hash.put("data", palabraprueba);

    dato.put("value", hash);

    Date hora = new Date();
    DateFormat miliseg = new SimpleDateFormat("HH:mm:ss.SSS");
    String stringhora = miliseg.format(hora);
    dato.put("timestamp", stringhora);

    System.out.println(this.getName()+" sending: "+dato);
    //INICIA RABBIT
    /* 
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try{
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    try {
        
        channel.queueDeclare("guardador1", true, false, false, null);
        String enviar = dato.toString();
        channel.basicPublish("", "guardador1", null, enviar.getBytes());
        

    } finally {
        channel.close();
        connection.close();
    }
}catch(IOException |TimeoutException e){
    System.out.println("Error");
}
 */
//TERMINA RABBIT 


//INICIA KAFKA

Properties properties = new Properties();
properties.put("bootstrap.servers", "localhost:9092");
properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
String enviarkafka = dato.toString();
ProducerRecord producerRecord = new ProducerRecord("channel","name",enviarkafka);
KafkaProducer kafkaProducer = new KafkaProducer<>(properties);

kafkaProducer.send(producerRecord);

kafkaProducer.close();

//TERMINA KAFKA
    try{
        Thread.sleep(esperaentremensajes);
    } catch(InterruptedException e){
        System.out.println("Interrupcion");
    }
}
}

    public void esperaMensaje(int valor){
        this.esperaentremensajes = valor;
    }
}