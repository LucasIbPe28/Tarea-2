package com.tarea2;

import java.lang.Thread;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConsumer extends Thread  {
    private final static String EXCHANGE_NAME = "prueba.exch";
    private final static String ROUTING_KEY = "guardar";
    private final static String QUEUE_NAME = "guardador";


    private Connection connection;
    private Channel channel;
    int numero;
    public RabbitConsumer(String nombreDispositivo) throws IOException, TimeoutException {
        super(nombreDispositivo);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
    }

    @Override
    public void run() {
        try {
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Dispositivo ["+ numero+"] :3" + " recibió el mensaje: " + message);
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            channel.basicConsume(QUEUE_NAME, false, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void numt(int valor){
        this.numero = valor;
    }
    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

}